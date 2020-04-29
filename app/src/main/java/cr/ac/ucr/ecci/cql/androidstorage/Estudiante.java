package cr.ac.ucr.ecci.cql.androidstorage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcel;

import java.util.Date;

public class Estudiante extends Persona {

    private String carnet;
    private int carreraBase;
    private double promedioPonderado;

    public Estudiante() {
        super();
    }

    public Estudiante(String identificacion, String correo, String nombre, String primerApellido, String segundoApellido, String telefono, String celular, Date fechaNacimiento, String tipo, String genero, String carnet, int carreraBase, double promedioPonderado) {

        super(identificacion, correo, nombre, primerApellido, segundoApellido, telefono, celular, fechaNacimiento, tipo, genero);

        this.carnet = carnet;
        this.carreraBase = carreraBase;
        this.promedioPonderado = promedioPonderado;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public int getCarreraBase() {
        return carreraBase;
    }

    public void setCarreraBase(int carreraBase) {
        this.carreraBase = carreraBase;
    }

    public double getPromedioPonderado() {
        return promedioPonderado;
    }

    public void setPromedioPonderado(double promedioPonderado) {
        this.promedioPonderado = promedioPonderado;
    }

    public Estudiante(Parcel in, String carnet, int carreraBase, double promedioPonderado) {
        super(in);
        this.carnet = carnet;
        this.carreraBase = carreraBase;
        this.promedioPonderado = promedioPonderado;
    }

    // insertar un estudiante en la base de datos
    public long insertar(Context context) {

        // inserta la persona antes del estudiante
        long newRowId = super.insertar(context);

        // si inserta la Persona inserto el estudiante
        SQLiteDatabase db = null;
        if (newRowId > 0) {

            // usar la clase DataBaseHelper para realizar la operacion de insertar
            DataBaseHelper dataBaseHelper = new DataBaseHelper(context);

            // Obtiene la base de datos en modo escritura
           
            db = dataBaseHelper.getWritableDatabase();
        }
        // Crear un mapa de valores donde las columnas son las llaves
        ContentValues values = new ContentValues();
        values.put(
                DataBaseContract.DataBaseEntry._ID, getIdentificacion());
        values.put(
                DataBaseContract.DataBaseEntry.COLUMN_NAME_CARNET, getCarnet());
        values.put(
                DataBaseContract.DataBaseEntry.COLUMN_NAME_CARRERA_BASE, getCarreraBase());
        values.put(
                DataBaseContract.DataBaseEntry.COLUMN_NAME_PROMEDIO_PONDERADO, getPromedioPonderado());

        // Insertar la nueva fila
        newRowId = db.insert(DataBaseContract.DataBaseEntry.TABLE_NAME_ESTUDIANTE, null, values);
             return newRowId;
}

    // leer un estudiante desde la base de datos
    public void leer(Context context, String identificacion) {

        // leer la persona antes del estudiante
        super.leer(context, identificacion);

        // si lee a la persona, leo el estudiante
        if (getTipo().equals(Persona.TIPO_ESTUDIANTE)) {

            // usar la clase DataBaseHelper para realizar la operacion de select
            DataBaseHelper dataBaseHelper = new DataBaseHelper(context);

            // Obtiene la base de datos en modo lectura
            SQLiteDatabase db = dataBaseHelper.getReadableDatabase();

            // Define cuales columnas quiere solicitar // en este caso todas las de la clase
            String[] projection = {
                    DataBaseContract.DataBaseEntry._ID,
                    DataBaseContract.DataBaseEntry.COLUMN_NAME_CARNET,
                    DataBaseContract.DataBaseEntry.COLUMN_NAME_CARRERA_BASE,
                    DataBaseContract.DataBaseEntry.COLUMN_NAME_PROMEDIO_PONDERADO
            };

            // Filtro para el WHERE
            String selection = DataBaseContract.DataBaseEntry._ID + " = ?";
            String[] selectionArgs = {identificacion};

            // Resultados en el cursor
            Cursor cursor = db.query(
                    DataBaseContract.DataBaseEntry.TABLE_NAME_ESTUDIANTE, // tabla
                    projection,                                   // columnas
                    selection,                                    // where
                    selectionArgs,                                     // valores del where
                    null,                                              // agrupamiento
                    null,                                              // filtros por grupo
                    null                                               // orden
            );

            // recorrer los resultados y asignarlos a la clase // aca podria
            //implementarse un ciclo si es necesario
            if (cursor.moveToFirst() && cursor.getCount() > 0) {
                setCarnet(cursor.getString(cursor.getColumnIndexOrThrow(DataBaseContract.DataBaseEntry.COLUMN_NAME_CARNET)));
                setCarreraBase(cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseContract.DataBaseEntry.COLUMN_NAME_CARRERA_BASE)));
                setPromedioPonderado(cursor.getDouble(cursor.getColumnIndexOrThrow(DataBaseContract.DataBaseEntry.COLUMN_NAME_PROMEDIO_PONDERADO)));
            }
        }
    }

    // eliminar un estudiante desde la base de datos
    public void eliminar(Context context, String identificacion) {

// usar la clase DataBaseHelper para realizar la operacion de eliminar
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);

// Obtiene la base de datos en modo escritura
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();

// Define el where para el borrado
        String selection = DataBaseContract.DataBaseEntry._ID + " LIKE ?";

// Se detallan los argumentos
        String[] selectionArgs = {identificacion};

// Realiza el SQL de borrado
        db.delete(DataBaseContract.DataBaseEntry.TABLE_NAME_ESTUDIANTE, selection, selectionArgs);

// eliminar la persona despues del estudiante
        super.eliminar(context, identificacion);
    }

    // actualizar un estudiante en la base de datos
    public int actualizar(Context context) {

// usar la clase DataBaseHelper para realizar la operacion de actualizar
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);

// Obtiene la base de datos
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();

// Crear un mapa de valores para los datos a actualizar
        ContentValues values = new ContentValues();
        values.put(
                DataBaseContract.DataBaseEntry.COLUMN_NAME_CARNET, getCarnet());
        values.put(
                DataBaseContract.DataBaseEntry.COLUMN_NAME_CARRERA_BASE, getCarreraBase());
        values.put(
                DataBaseContract.DataBaseEntry.COLUMN_NAME_PROMEDIO_PONDERADO, getPromedioPonderado());

// Criterio de actualizacion
        String selection = DataBaseContract.DataBaseEntry._ID + " LIKE ?";

// Se detallan los argumentos
        String[] selectionArgs = {getIdentificacion()};

// Actualizar la base de datos
        int contador = db.update(DataBaseContract.DataBaseEntry.TABLE_NAME_ESTUDIANTE, values, selection, selectionArgs);

        // actualizar la persona despues del estudiante         contador += super.actualizar(context);

        return contador;
    }
}