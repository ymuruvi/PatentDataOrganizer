/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patent.data.converter.utilities;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Queue;

/**
 *
 * @author Nano
 */
public class Tools {

    public Date strToDate(String s) {
        Date date = new Date();

        return date;
    }

    /**
     * Returns the extension of a given file.
     * For files with no period being used to dictate the file extention
     * an empty string will be returned
     * 
     * @param fileName Name of the file for who's extension will be returned
     * @return String containing either the given file name's extension or
     * an empty string for files with no extension.
     */
    public static String getExtension(final String fileName) {

        LinkedList ext = new LinkedList();
        StringBuilder extension = new StringBuilder();
        boolean searching = true;

        if(fileName == null){
            return "";
        }
        for (int i = 1; i <= fileName.length() && searching; i++) {
            char c = fileName.charAt(fileName.length() - i);
            if (c == '.') {
                searching = false;
            } else {
                ext.push(c);
            }
        }
        if (searching) {
            return "";
        }

        Iterator iter = ext.iterator();

        for (int i = 0; iter.hasNext(); i++) {
            char c = (Character) iter.next();
            extension.insert(i, c);
        }

        return extension.toString();
    }
}
