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
import javax.swing.JFileChooser;
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

    public static void chooseFiles() {
        final JFileChooser fc = new JFileChooser();
    }

    public static void main(String[] args) {

        String folderPath;
        File folder;
        File[] files;
        Record r;
        String filePath;

        chooseFiles();

        System.out.println("Where are the files located?");
        folderPath = new Scanner(System.in).nextLine();
        folder = new File(folderPath);

        if (folder.isDirectory()) {
            files = folder.listFiles();
            System.out.println(files.length + " files in " + folderPath);
            for (File f : files) {
                if (f.isFile()) {
                    filePath = f.getAbsolutePath();
                    if ("xml".equals(Tools.getExtension(filePath))) {
                        System.out.println(Tools.Contstants.ANSI_GREEN + "Reading: " + filePath + Tools.Contstants.ANSI_RESET);
                        r = new Record();
                        r.parse(filePath);

                    } else {
                        System.out.println(Tools.Contstants.ANSI_RED + filePath
                                + " is not an XML File (" + Tools.getExtension(filePath)
                                + ")" + Tools.Contstants.ANSI_RESET);
                    }

                }
            }
        }
    }
}
