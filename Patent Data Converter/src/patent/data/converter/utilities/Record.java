/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patent.data.converter.utilities;

import java.util.HashMap;

/**
 *
 * @author Nano
 */
public class Record {
    enum DocumentType{PATENT, PATENT_APP};
    
    private String docNum;
    private String appNum;
    private String englishTitle;
    private String frenchTitle;
    
    
    private Inventor[] inventors;
    
}
