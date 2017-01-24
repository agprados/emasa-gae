/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emasagae.utils;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class UtilsMix {
    
    public static XMLGregorianCalendar convertDateToXMLGregorianCalendar(Date date) {        
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        XMLGregorianCalendar dategc = null;
        try {
            dategc = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(UtilsMix.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return dategc;        
    }
    
}
