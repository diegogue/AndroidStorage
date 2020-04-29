package cr.ac.ucr.ecci.cql.androidstorage;

/**
 * Created by Christian on 2/26/2017.  * Ultima modificacion by Christian on 2/26/2017.  * Actividad principal de la app
 */

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileNotFoundException;

import cql.ecci.ucr.ac.cr.R;
import cr.ac.ucr.ecci.cql.androidstorage.Estudiante;
import cr.ac.ucr.ecci.cql.androidstorage.Persona;
import cr.ac.ucr.ecci.cql.androidstorage.UtilDates;
import cr.ac.ucr.ecci.cql.androidstorage.UtilFiles;

public class MainActivity extends AppCompatActivity {
    public MainActivity() {
    }

    public MainActivity(int contentLayoutId) {
        super(contentLayoutId);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // almacenamiento de archivos internos
        Button buttonGrabarArchivo = (Button) findViewById(R.id.buttonGrabarArchivo);
        Button buttonLeerArchivo = (Button) findViewById(R.id.buttonLeerArchivo);

        buttonGrabarArchivo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                grabarArchivo();
            }
        });

        buttonLeerArchivo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    leerArchivo();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

            // Instanciar los botones del layout activity_main.xml
        Button buttonDataBaseInsert = (Button) findViewById(R.id.buttonDataBaseInsert);
        Button buttonDataBaseSelect = (Button) findViewById(R.id.buttonDataBaseSelect);
        Button buttonDataBaseUpdate = (Button) findViewById(R.id.buttonDataBaseUpdate);
        Button buttonDataBaseDelete = (Button) findViewById(R.id.buttonDataBaseDelete);

        // Asocia los eventos clic a cada uno de los botones
        buttonDataBaseInsert.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                insertarEstudiante();
            }
        });

        buttonDataBaseSelect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                leerEstudiante();
            }
        });

        buttonDataBaseDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                eliminarEstudiante();
            }
        });

        buttonDataBaseUpdate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                actualizarEstudiante();
            }
        });


    }

    // grabar un archivo en almacenamiento interno
    public void grabarArchivo() {

    // crear la persona
        Persona persona = new Persona("1-1000-1000", "estudiante01@ucr.ac.cr", "Juan",
                "Perez", "Soto*", "2511-0000", "8890-0000", UtilDates.StringToDate("01 01 1995"),
                Persona.TIPO_ESTUDIANTE, Persona.GENERO_MASCULINO);

    // grabar la persona en un archivo interno
        UtilFiles.guardarArchivoInterno(getApplicationContext(), "PersonaAndroidStorage.json", persona.toJson());

    // mensaje al usuario
        Toast.makeText(getApplicationContext(), "Archivo creado: " + "PersonaAndroidStorage.json",
                Toast.LENGTH_LONG).show();
    }

    // leer un archivo en almacenamiento interno
    public void leerArchivo() throws FileNotFoundException {

    // leer el archivo
        String datosArchivo = datosArchivo = UtilFiles.leerArchivoInterno(getApplicationContext(),
                "PersonaAndroidStorage.json");

        if (datosArchivo.isEmpty()) {
            Toast.makeText(getApplicationContext(), "No hay datos para: " +
                    "PersonaAndroidStorage.json", Toast.LENGTH_LONG).show();
        }         else {
            Toast.makeText(getApplicationContext(), "Archivo: " + "PersonaAndroidStorage.json" +                     "Contenido: " + datosArchivo, Toast.LENGTH_LONG).show();         }     }

    private void insertarEstudiante() {

        // Instancia la clase Estudiante y realiza la inserción de datos
        Estudiante estudiante = new Estudiante("1-1000-1000", "estudiante01@ucr.ac.cr", "Juan",
                "Perez", "Soto", "2511-0000", "8890-0000",
                UtilDates.StringToDate("01 01 1995"),

                Persona.TIPO_ESTUDIANTE, Persona.GENERO_MASCULINO, "A99148", 1, 8.0);

        // inserta el estudiante, se le pasa como parametro el contexto de la app
        long newRowId = estudiante.insertar(getApplicationContext());

        // Mostrar un mensaje para el usuario
        Toast.makeText(getApplicationContext(), "Insertar Estudiante: " + newRowId + " Id: " + estudiante.getIdentificacion() +
                " Carnet: " + estudiante.getCarnet() + " Nombre: " + estudiante.getNombre() +
                " " + estudiante.getPrimerApellido() + " " + estudiante.getSegundoApellido() +
                " Correo: " + estudiante.getCorreo() + " Tipo: " +
                estudiante.getTipo() + " Promedio: " +
                estudiante.getPromedioPonderado(), Toast.LENGTH_LONG).show();
    }

    private void leerEstudiante() {

        // Instancia la clase Estudiante y realiza la lectura de datos
        Estudiante estudiante = new Estudiante();

        // leer el estudiante, se le pasa como parametro el contexto de la app y ls identificacion
        estudiante.leer(getApplicationContext(), "1-1000-1000");

        // si lee al estudiante
        if (estudiante.getTipo().equals(Persona.TIPO_ESTUDIANTE)) {
            // Mostrar un mensaje para el usuario
            Toast.makeText(getApplicationContext(), "Leer Estudiante: " + estudiante.getIdentificacion() +
                    " Carnet: " + estudiante.getCarnet() + " Nombre: " + estudiante.getNombre() +
                    " " + estudiante.getPrimerApellido() + " " + estudiante.getSegundoApellido() +
                    " Correo: " + estudiante.getCorreo() + " Tipo: " + estudiante.getTipo() + " Promedio: " +
                    estudiante.getPromedioPonderado(), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "No hay datos para: " + "1-10001000", Toast.LENGTH_LONG).show();
        }
    }

    private void eliminarEstudiante() {

        // Instancia la clase Estudiante y realiza el borrado de datos
        Estudiante estudiante = new Estudiante();

        // leer el estudiante, se le pasa como parametro el contexto de la app y ls identificacion
        estudiante.eliminar(getApplicationContext(), "1-1000-1000");

        // Mostrar un mensaje para el usuario
        Toast.makeText(getApplicationContext(), "Eliminar Estudiante.", Toast.LENGTH_LONG).show();
    }

    private void actualizarEstudiante() {

        // Instancia la clase Estudiante y realiza la actualización de datos
        Estudiante estudiante = new Estudiante("1-1000-1000", "estudiante01@ucr.ac.cr*", "Juan*",
                "Perez*", "Soto*", "2511-0000*", "8890-0000*", UtilDates.StringToDate("01 01 1995"),
                Persona.TIPO_ESTUDIANTE, Persona.GENERO_MASCULINO, "A99148*", 1, 8.0);

        // actualiza el estudiante, se le pasa como parametro el contexto de la app
        int contador = estudiante.actualizar(getApplicationContext());

        // si actualiza al estudiante
        if (contador > 0) {
            // Mostrar un mensaje para el usuario
            Toast.makeText(getApplicationContext(), "Actualizar Estudiante: "
                    + contador + " Id: " + estudiante.getIdentificacion() +
                    " Carnet: " + estudiante.getCarnet() + " Nombre: " + estudiante.getNombre() +
                    " " + estudiante.getPrimerApellido() + " " + estudiante.getSegundoApellido() +
                    " Correo: " + estudiante.getCorreo() + " Tipo: " + estudiante.getTipo() + " Promedio: " +
                    estudiante.getPromedioPonderado(), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "No hay datos para: " + estudiante.getIdentificacion(), Toast.LENGTH_LONG).show();
        }
    }




}