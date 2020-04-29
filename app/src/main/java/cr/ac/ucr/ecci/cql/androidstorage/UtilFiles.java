package cr.ac.ucr.ecci.cql.androidstorage;

/**
 * Created by Christian on 2/26/2017.
 * * Ultima modificacion by Christian on 2/26/2017.
 * * Esta clase define funcioalidades para el almacenamiento de archivos
 */

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class UtilFiles {

    // guardar archivo privado en el almacenamiento interno del dispositivo
    public static void guardarArchivoInterno(Context contexto, String nombre, String contenido) {

        try {
            FileOutputStream fos = contexto.openFileOutput(nombre, Context.MODE_PRIVATE);
            fos.write(contenido.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // leer archivo privado del almacenamiento interno del dispositivo
    public static String leerArchivoInterno(Context contexto, String nombre) throws FileNotFoundException {

        String readString = "";

        StringBuffer datax = new StringBuffer("");
        try {
            FileInputStream fis = contexto.openFileInput(nombre);
            int size;
            // read inside if it is not null (-1 means empty)
            while ((size = fis.read()) != -1) {
                // add & append content
                readString += Character.toString((char) size);
            }


            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return readString;
    }
}
