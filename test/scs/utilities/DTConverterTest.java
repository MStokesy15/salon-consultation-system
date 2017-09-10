/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scs.utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author matthewstokes
 */
public class DTConverterTest {

    Date date = null;

    @Before
    public void setUp() {
        String startDateString = "04/01/2017";
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        try {
            //Initialise the test date
            date = df.parse(startDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test of string2date method, of class DTConverter.
     */
    @Test
    public void testString2date() {
        System.out.println("string2date");
        //Create a test string date
        String strDate = "2017-04-01T00:00";
        Date expResult = date;
        Date result = DTConverter.string2date(strDate);
        //Assert the result
        assertEquals(expResult, result);
    }

    /**
     * Test of date2string method, of class DTConverter.
     */
    @Test
    public void testDate2string() {
        System.out.println("date2string");
        //Create a test string date
        String expResult = "01/04/2017";
        String result = DTConverter.date2string(date);
        //Assert the result
        assertEquals(expResult, result);
    }
}
