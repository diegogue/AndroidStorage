package cr.ac.ucr.ecci.cql.androidstorage;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Locale;

/**
 * Created by Christian on 2/26/2017.
 */

public class UtilDates {

    public static String DateToStringShort(Date fecha) {
        SimpleDateFormat df = new SimpleDateFormat("dd MM yyyy", Locale.getDefault());
        return df.format(fecha);
    }

    public static Date StringToDate(String fecha) {
        SimpleDateFormat df = new SimpleDateFormat("dd MM yyyy", Locale.getDefault());
        Date convertedDate = new Date();
        try {
            convertedDate = df.parse(fecha);
        } catch (ParseException e) {
            convertedDate = StringtoDateSQL(fecha);
        }
        return convertedDate;
    }

    public static Date StringtoDateSQL(String fecha) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date convertedDate;
        try {
            convertedDate = df.parse(fecha);
        } catch (ParseException e) {
            convertedDate = null;
        }
        return convertedDate;
    }
}
