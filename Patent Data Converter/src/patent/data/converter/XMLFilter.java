/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patent.data.converter;

import java.io.File;
import javax.swing.filechooser.FileFilter;
import patent.data.converter.utilities.Tools;

/**
 *
 * @author nano
 */
public class XMLFilter extends FileFilter{

    //Accept all directories and all gif, jpg, tiff, or png files.
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }

        String extension = Tools.getExtension(f);
        if (extension != null) {
            if (extension.equals(Tools.Contstants.XML)) {
                    return true;
            } else {
                return false;
            }
        }

        return false;
    }

    //The description of this filter
    public String getDescription() {
        return "Just Images";
    }
    
}
