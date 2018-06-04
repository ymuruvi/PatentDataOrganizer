/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patent.data.converter.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nano
 */
public class Tools {

    public class Contstants {

        public static final String ANSI_RESET = "\u001B[0m";
        public static final String ANSI_BLACK = "\u001B[30m";
        public static final String ANSI_RED = "\u001B[31m";
        public static final String ANSI_GREEN = "\u001B[32m";
        public static final String ANSI_YELLOW = "\u001B[33m";
        public static final String ANSI_BLUE = "\u001B[34m";
        public static final String ANSI_PURPLE = "\u001B[35m";
        public static final String ANSI_CYAN = "\u001B[36m";
        public static final String ANSI_WHITE = "\u001B[37m";
    }

    /**
     * Returns the extension of a given file. For files with no period being
     * used to dictate the file extention an empty string will be returned
     *
     * @param fileName Name of the file for who's extension will be returned
     * @return String containing either the given file name's extension or an
     * empty string for files with no extension.
     */
    public static String getExtension(final String fileName) {

        LinkedList ext = new LinkedList();
        StringBuilder extension = new StringBuilder();
        boolean searching = true;

        if (fileName == null) {
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


