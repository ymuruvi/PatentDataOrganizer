/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patent.data.converter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import patent.data.converter.utilities.Tools;

import org.xml.sax.*;
import patent.data.converter.utilities.Record;

/**
 *
 * @author Nano
 */
public class PatentDataConverter {

    static BufferedReader br;
    static FileReader fr;

    public static void main(String[] args) {

        System.out.println("Where are the files located?");
        String folderPath = new Scanner(System.in).nextLine();

        File folder = new File(folderPath);
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            for (File f : files) {
                if (f.isFile()) {
                    String filePath = f.getAbsolutePath();
                    if("xml".equals(Tools.getExtension(filePath))){
                        Record r = new Record();
                        r.parse(filePath);
                    }else{
                        System.out.println(filePath+" is not an XML File ("+Tools.getExtension(filePath)+")");
                    }
                    
                }
            }
        }
    }
}
