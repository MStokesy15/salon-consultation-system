/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scs.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Date Time Converter
 * 
 * Provides methods for manipulating dates and times
 * 
 * @author matthewstokes
 */
public final class DTConverter {

    /**
     * Converts a String date input to the Java.util.Date type
     *
     * @param date
     * @return The converted date string.
     */
    public static Date string2date(String date) {
        Date resultOut = null;
        try {
            SimpleDateFormat sdfIN = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            resultOut = sdfIN.parse(date);
        } catch (ParseException pe) {
            System.out.println(date);
            System.out.println(pe);
        }
        return resultOut;
    }

    /**
     * Converts a Date input to a String of the form dd/MM/yy
     *
     * @param date
     * @return The converted date string.
     */
    public static String date2string(Date date) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            return sdf.format(date);
    }
}
