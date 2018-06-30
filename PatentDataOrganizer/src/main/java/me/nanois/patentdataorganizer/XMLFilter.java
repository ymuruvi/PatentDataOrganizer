package me.nanois.patentdataorganizer;

import java.io.File;
import javax.swing.filechooser.FileFilter;
import me.nanois.patentdataorganizer.utilities.Tools;

/**
 *
 * @author nano
 */
public class XMLFilter extends FileFilter{

    /**
     * Don't really recall where this was in 
     * spired from but I don't think I'm using 
     * it anymore.
     * @deprecated 
     */
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }

        String extension = Tools.getExtension(f);
        if (extension != null) {
            if (extension.equals(Tools.Constants.XML)) {
                    return true;
            } else {
                return false;
            }
        }

        return false;
    }

    //The description of this filter
    public String getDescription() {
        return "XML Files";
    }
    
}
