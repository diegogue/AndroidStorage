package cr.ac.ucr.ecci.cql.androidstorage;

import android.provider.BaseColumns;

import java.sql.Date;

public final class DataBaseContract {

// Para asegurar que no se instancie la clase hacemos el constructor privado
private DataBaseContract() {}

    // Definimos una clase interna que define las tablas y columnas
    // Implementa la interfaz BaseColumns para heredar campos estandar del marco de Android _ID
    public static class DataBaseEntry implements BaseColumns {

    // Clase Persona
    public static final String TABLE_NAME_PERSONA = "Persona";
        //private String identificacion; Utilizamos DataBaseEntry._ID de BaseColumns
        private String correo;
        public static final String COLUMN_NAME_CORREO = "correo";
        private String Nombre;
        public static final String COLUMN_NAME_NOMBRE = "nombre";
        private String primerApellido;
        public static final String COLUMN_NAME_PRIMER_APELLIDO = "primerApellido";
        private String segundoApellido;
        public static final String COLUMN_NAME_SEGUNDO_APELLIDO = "segundoApellido";
        private String telefono;
        public static final String COLUMN_NAME_TELEFONO = "telefono";
        private String celular;
        public static final String COLUMN_NAME_CELULAR = "celular";
        private Date fechaNacimiento;
        public static final String COLUMN_NAME_FECHA_NACIMIENTO = "fechaNacimiento";
        private String tipo;         public static final String COLUMN_NAME_TIPO = "tipo";
        private String genero;         public static final String COLUMN_NAME_GENERO = "genero";

    // Clase Estudiante
        public static final String TABLE_NAME_ESTUDIANTE = "Estudiante";
        private String carnet;
        public static final String COLUMN_NAME_CARNET = "carnet";
        private int carreraBase;
        public static final String COLUMN_NAME_CARRERA_BASE = "carreraBase";
        private double promedioPonderado;
        public static final String COLUMN_NAME_PROMEDIO_PONDERADO = "promedioPonderado";

    // Clase Funcionario
        public static final String TABLE_NAME_FUNCIONARIO = "Funcionario";
        private int unidadBase;
        public static final String COLUMN_NAME_UNIDAD_BASE = "unidadBase";
        private int puestoBase;
        public static final String COLUMN_NAME_PUESTO_BASE = "puestoBase";
        private double salarioBase;
        public static final String COLUMN_NAME_SALARIO_BASE = "salarioBase";     }

    // Construir las tablas de la base de datos
    private static final String TEXT_TYPE = " TEXT";
private static final String INTEGER_TYPE = " INTEGER";
private static final String REAL_TYPE = " REAL";
private static final String COMMA_SEP = ",";

    // Creacion de tablas Persona, Estudiante, Funcionario

    public static final String SQL_CREATE_PERSONA = "CREATE TABLE " + DataBaseEntry.TABLE_NAME_PERSONA + " (" + DataBaseEntry._ID + TEXT_TYPE + "PRIMARY KEY," + DataBaseEntry.COLUMN_NAME_CORREO + TEXT_TYPE + COMMA_SEP + DataBaseEntry.COLUMN_NAME_NOMBRE + TEXT_TYPE + COMMA_SEP + DataBaseEntry.COLUMN_NAME_PRIMER_APELLIDO + TEXT_TYPE + COMMA_SEP +
            DataBaseEntry.COLUMN_NAME_SEGUNDO_APELLIDO + TEXT_TYPE + COMMA_SEP + DataBaseEntry.COLUMN_NAME_TELEFONO + TEXT_TYPE + COMMA_SEP + DataBaseEntry.COLUMN_NAME_CELULAR + TEXT_TYPE + COMMA_SEP + DataBaseEntry.COLUMN_NAME_FECHA_NACIMIENTO + TEXT_TYPE + COMMA_SEP + DataBaseEntry.COLUMN_NAME_TIPO + TEXT_TYPE + COMMA_SEP + DataBaseEntry.COLUMN_NAME_GENERO + TEXT_TYPE + " )";

    public static final String SQL_DELETE_PERSONA = "DROP TABLE IF EXISTS " + DataBaseEntry.TABLE_NAME_PERSONA;

    public static final String SQL_CREATE_ESTUDIANTE = "CREATE TABLE " + DataBaseEntry.TABLE_NAME_ESTUDIANTE + " (" + DataBaseEntry._ID + TEXT_TYPE + "PRIMARY KEY," + DataBaseEntry.COLUMN_NAME_CARNET + TEXT_TYPE + COMMA_SEP + DataBaseEntry.COLUMN_NAME_CARRERA_BASE + INTEGER_TYPE + COMMA_SEP + DataBaseEntry.COLUMN_NAME_PROMEDIO_PONDERADO + REAL_TYPE + COMMA_SEP + "FOREIGN KEY(" + DataBaseEntry._ID + ") REFERENCES " + DataBaseEntry.TABLE_NAME_PERSONA + "(" + DataBaseEntry._ID + "))";

    public static final String SQL_DELETE_ESTUDIANTE = "DROP TABLE IF EXISTS " + DataBaseEntry.TABLE_NAME_ESTUDIANTE;

    public static final String SQL_CREATE_FUNCIONARIO = "CREATE TABLE " + DataBaseEntry.TABLE_NAME_FUNCIONARIO + " (" + DataBaseEntry._ID + TEXT_TYPE + "PRIMARY KEY," + DataBaseEntry.COLUMN_NAME_UNIDAD_BASE + INTEGER_TYPE + COMMA_SEP + DataBaseEntry.COLUMN_NAME_PUESTO_BASE + INTEGER_TYPE + COMMA_SEP + DataBaseEntry.COLUMN_NAME_SALARIO_BASE + REAL_TYPE + COMMA_SEP + "FOREIGN KEY(" + DataBaseEntry._ID + ") REFERENCES " + DataBaseEntry.TABLE_NAME_PERSONA + "(" + DataBaseEntry._ID + "))";

    public static final String SQL_DELETE_FUNCIONARIO = "DROP TABLE IF EXISTS " + DataBaseEntry.TABLE_NAME_FUNCIONARIO;
}