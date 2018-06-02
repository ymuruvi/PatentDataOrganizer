/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patent.data.converter.utilities;

import com.sun.org.apache.xerces.internal.util.DOMUtil;
import java.util.HashMap;

import org.xml.sax.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;


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
    
    public void parse(String file){
        Document xmlDoc = getDocument(file);
        System.out.println(file + " => Root: "+
                xmlDoc.getDocumentElement().getNodeName());
        NodeList listOfFields = xmlDoc.getElementsByTagName("ca-bibliographic-data");
        
        
        getElementAndAttribute(listOfFields, "invention-title", "lang");
    }
    
    private static Document getDocument(String docString) {
        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            
            factory.setIgnoringComments(true);
            factory.setIgnoringElementContentWhitespace(true);
            factory.setValidating(true);
            
            DocumentBuilder builder = factory.newDocumentBuilder();
            
            return builder.parse(new InputSource(docString));
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    private NodeList getElementAndAttribute(NodeList listOfFields, String elementName, String searchAttr) {
        NodeList out = null;
        try{
            
            for(int i=0;i < listOfFields.getLength();i++){
                Node node = listOfFields.item(i);
                
                Element elem = (Element) node;
                
                NodeList list = elem.getElementsByTagName(elementName);
                
                Element nElem = (Element) list.item(0);
                
                NodeList eList = nElem.getChildNodes();
                
                if(nElem.hasAttribute(searchAttr)){
                    System.out.println(elementName + " : " 
                            + ((Node) eList.item(0)).getNodeValue().trim() 
                            + " has attribute \"" + nElem.getAttribute(searchAttr) + "\"");
                }else{
                    System.out.println(elementName + " : " 
                            + ((Node)eList.item(0)).getNodeValue().trim());
                }
                return out;
            }
        }catch(Exception e){
            System.out.println("Error: "+e);
        }
        return out;
    }
    
}
