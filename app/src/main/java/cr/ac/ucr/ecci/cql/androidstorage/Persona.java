package cr.ac.ucr.ecci.cql.androidstorage;

/**
 * Created by Christian on 2/26/2017.  * Ultima modificacion by Christian on 2/26/2017.  * Esta clase define la superclase Persona
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

import java.util.Date;

// Clase persona que implementa Parceable para intercambio de objetos entre actividades public
public class Persona implements Parcelable {

    public static final String GENERO_MASCULINO = "M";
    public static final String GENERO_FEMENINO = "F";
    public static final String GENERO_OTRO = "O";

    public static final String TIPO_ADMINISTRATIVO = "A";
    public static final String TIPO_PROFESOR = "P";
    public static final String TIPO_ESTUDIANTE = "E";
    public static final String TIPO_EXTERNO = "X";
    public static final String TIPO_SIN_DEFINIR = "S";

    private String identificacion;
    private String correo;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String telefono;
    private String celular;
    private Date fechaNacimiento;
    private String tipo; //Profesor / Administrativo / Estudiante / Externo
    private String genero; //Masculino / Femenino / Otro

    public Persona() {
    }

    public Persona(String identificacion, String correo, String nombre, String primerApellido,
                   String segundoApellido, String telefono, String celular, Date fechaNacimiento,
                   String tipo, String genero) {
        this.identificacion = identificacion;
        this.correo = correo;
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.telefono = telefono;
        this.celular = celular;
        this.fechaNacimiento = fechaNacimiento;
        this.tipo = tipo;
        this.genero = genero;
    }

    public static String getGeneroMasculino() {
        return GENERO_MASCULINO;
    }

    public static String getGeneroFemenino() {
        return GENERO_FEMENINO;
    }

    public static String getGeneroOtro() {
        return GENERO_OTRO;
    }

    public static String getTipoAdministrativo() {
        return TIPO_ADMINISTRATIVO;
    }

    public static String getTipoProfesor() {
        return TIPO_PROFESOR;
    }

    public static String getTipoEstudiante() {
        return TIPO_ESTUDIANTE;
    }

    public static String getTipoExterno() {
        return TIPO_EXTERNO;
    }

    public static String getTipoSinDefinir() {
        return TIPO_SIN_DEFINIR;
    }

    public static Creator<Persona> getCREATOR() {
        return CREATOR;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    protected Persona(Parcel in) {
        identificacion = in.readString();
        correo = in.readString();
        nombre = in.readString();
        primerApellido = in.readString();
        segundoApellido = in.readString();
        telefono = in.readString();
        celular = in.readString();
        tipo = in.readString();
        genero = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(identificacion);
        dest.writeString(correo);
        dest.writeString(nombre);
        dest.writeString(primerApellido);
        dest.writeString(segundoApellido);
        dest.writeString(telefono);
        dest.writeString(celular);
        dest.writeString(tipo);
        dest.writeString(genero);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Persona> CREATOR = new Creator<Persona>() {
        @Override
        public Persona createFromParcel(Parcel in) {
            return new Persona(in);
        }

        @Override
        public Persona[] newArray(int size) {
            return new Persona[size];
        }
    };

    // insertar una persona en la base de datos
    public long insertar(Context context) {

        // usar la clase DataBaseHelper para realizar la operacion de insertar
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);

        // Obtiene la base de datos en modo escritura
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();

        // Crear un mapa de valores donde las columnas son las llaves
        ContentValues values = new ContentValues();
        values.put(DataBaseContract.DataBaseEntry._ID, getIdentificacion());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_CORREO, getCorreo());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_NOMBRE, getNombre());

        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_PRIMER_APELLIDO, getPrimerApellido());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_SEGUNDO_APELLIDO, getSegundoApellido());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_TELEFONO, getTelefono());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_CELULAR, getCelular());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_FECHA_NACIMIENTO, 
                UtilDates.DateToStringShort(getFechaNacimiento()));
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_TIPO, getTipo());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_GENERO, getGenero());

        // Insertar la nueva fila
        return db.insert(DataBaseContract.DataBaseEntry.TABLE_NAME_PERSONA, null, values);
    }

    // leer una persona desde la base de datos
    public void leer(Context context, String identificacion) {

        // usar la clase DataBaseHelper para realizar la operacion de leer
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);

        // Obtiene la base de datos en modo lectura
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();

        // Define cuales columnas quiere solicitar
        // en este caso todas las de la clase
        String[] projection = {
                DataBaseContract.DataBaseEntry._ID,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_CORREO,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_NOMBRE,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_PRIMER_APELLIDO,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_SEGUNDO_APELLIDO,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_TELEFONO,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_CELULAR,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_FECHA_NACIMIENTO,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_TIPO,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_GENERO
        };

        // Filtro para el WHERE
        String selection = DataBaseContract.DataBaseEntry._ID + " = ?";
        String[] selectionArgs = {identificacion};

        // Resultados en el cursor
        Cursor cursor = db.query(
                DataBaseContract.DataBaseEntry.TABLE_NAME_PERSONA, // tabla
                projection,                                        // columnas
                selection,                                         // where
                selectionArgs,                                     // valores del where
                null,                                              // agrupamiento
                null,                                              // filtros por grupo
                null                                               // orden
        );

        // recorrer los resultados y asignarlos a la clase // aca podria implementarse un ciclo si es necesario
        if (cursor.moveToFirst() && cursor.getCount() > 0) {
            setIdentificacion(cursor.getString(cursor.getColumnIndexOrThrow(
                    DataBaseContract.DataBaseEntry._ID)));
            setCorreo(cursor.getString(cursor.getColumnIndexOrThrow(DataBaseContract.DataBaseEntry.COLUMN_NAME_CORREO)));
            setNombre(cursor.getString(cursor.getColumnIndexOrThrow(DataBaseContract.DataBaseEntry.COLUMN_NAME_NOMBRE)));
            setPrimerApellido(cursor.getString(cursor.getColumnIndexOrThrow(DataBaseContract.DataBaseEntry.COLUMN_NAME_PRIMER_APELLIDO)));
            setSegundoApellido(cursor.getString(cursor.getColumnIndexOrThrow(DataBaseContract.DataBaseEntry.COLUMN_NAME_SEGUNDO_APELLIDO)));
            setTelefono(cursor.getString(cursor.getColumnIndexOrThrow(DataBaseContract.DataBaseEntry.COLUMN_NAME_TELEFONO)));
            setCelular(cursor.getString(cursor.getColumnIndexOrThrow(
                    DataBaseContract.DataBaseEntry.COLUMN_NAME_CELULAR)));
            setFechaNacimiento(UtilDates.StringToDate(cursor.getString(cursor.getColumnIndexOrThrow(
                    DataBaseContract.DataBaseEntry.COLUMN_NAME_FECHA_NACIMIENTO))));
            setTipo(cursor.getString(cursor.getColumnIndexOrThrow(
                    DataBaseContract.DataBaseEntry.COLUMN_NAME_TIPO)));
            setGenero(cursor.getString(cursor.getColumnIndexOrThrow(
                    DataBaseContract.DataBaseEntry.COLUMN_NAME_GENERO)));
        } else {
            setTipo(TIPO_SIN_DEFINIR);
        }
    }

    // eliminar una persona desde la base de datos
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
        db.delete(DataBaseContract.DataBaseEntry.TABLE_NAME_PERSONA, selection, selectionArgs);
    }

    // actualizar una persona en la base de datos
    public int actualizar(Context context) {

// usar la clase DataBaseHelper para realizar la operacion de actualizar
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);

// Obtiene la base de datos
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();

// Crear un mapa de valores para los datos a actualizar
        ContentValues values = new ContentValues();
        values.put(
                DataBaseContract.DataBaseEntry.COLUMN_NAME_CORREO, getCorreo());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_NOMBRE, getNombre());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_PRIMER_APELLIDO, getPrimerApellido());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_SEGUNDO_APELLIDO, getSegundoApellido());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_TELEFONO, getTelefono());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_CELULAR, getCelular());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_FECHA_NACIMIENTO, UtilDates.DateToStringShort(getFechaNacimiento()));
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_TIPO, getTipo());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_GENERO, getGenero());

        // Criterio de actualizacion
        String selection = DataBaseContract.DataBaseEntry._ID + " LIKE ?";

        // Se detallan los argumentos
        String[] selectionArgs = {getIdentificacion()};

        // Actualizar la base de datos
        return db.update(DataBaseContract.DataBaseEntry.TABLE_NAME_PERSONA, values, selection, selectionArgs);
    }

    // objeto de la clase a JSON
    public String toJson() {
    // objeto json
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
