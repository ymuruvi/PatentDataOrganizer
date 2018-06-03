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

import patent.data.converter.utilities.Tools;

/**
 *
 * @author Nano
 */
public class Record {

    enum DocumentType {
        PATENT, PATENT_APP
    };
    
    private HashMap dataNodes;

    private String docNum;
    private String appNum;
    private String englishTitle;
    private String frenchTitle;

    private static int s = 0;
    private Inventor[] inventors;

    private boolean searchingForNode;
    
    
    public Record(){
        dataNodes = new HashMap();
    }

    /**
     * Parses the .xml file at the given location looking for key data points
     * for this <code>Record</code>.
     *
     * @param fileName
     */
    public void parse(String fileName) {
        
        Document xmlDoc;
        NodeList listOfFields;
        NodeList nl;
        Node n;
        NodeList documentID;
        
        if (fileName != null) {
            
            xmlDoc = null;
            for (int i = 0; i < 5 && (xmlDoc == null); i++) {
                xmlDoc = getDocument(fileName);
            }

            if (xmlDoc != null) {

                System.out.println(Tools.Contstants.ANSI_GREEN + fileName
                        + " => Root: " + xmlDoc.getDocumentElement().getNodeName()
                        + Tools.Contstants.ANSI_RESET);
                
                listOfFields = xmlDoc.getElementsByTagName("ca-bibliographic-data");
                
                /*
                nl = getElementAndAttribute(listOfFields, "invention-title", "lang");
                documentID = getElementNodeList(listOfFields, "publication-reference");
                */
                
                n = null;
                
                SearchNode[] searchNodes = { new SearchNode("publication-reference")
                        , new SearchNode("classifications-ipcr"), new SearchNode("classification-national")
                        , new SearchNode("application-reference"), new SearchNode("language-of-filing")
                        , new SearchNode("priority-claims"), new SearchNode("dates-of-public-availability")
                        , new SearchNode("pct-or-regional-filing-data"), new SearchNode("pct-or-regional-publishing-data")
                        , new SearchNode("ca-office-specific-bib-data"), new SearchNode("invention-title",0)
                        , new SearchNode("invention-title",1)};
                
                for(SearchNode sn : searchNodes){
                    sn.setNode(searchForNode(listOfFields, sn.getSearchTerm(),sn.getOccurance()));
                    System.out.println(Tools.Contstants.ANSI_YELLOW+"Name: " + sn.getNodeName() + Tools.Contstants.ANSI_RESET);
                }
                
                if (n != null) {
                }
            } else {
                System.out.println(Tools.Contstants.ANSI_RED + "Error reading file: "
                        + fileName + Tools.Contstants.ANSI_RESET);
            }
        } else {

        }
    }

    private static Document getDocument(String docString) {
        DocumentBuilderFactory factory;
        DocumentBuilder builder;
        try {
            factory = DocumentBuilderFactory.newInstance();
            factory.setIgnoringComments(true);
            factory.setIgnoringElementContentWhitespace(true);
            factory.setValidating(true);
            
            builder = factory.newDocumentBuilder();
            return builder.parse(new InputSource(docString));

        } catch (Exception e) {
            System.out.println(Tools.Contstants.ANSI_RED + e.getMessage() + Tools.Contstants.ANSI_RESET);
        }
        return null;
    }
    
    /**
     * Returns the first occurrence of a node with a given name.
     *
     * @param listOfFields The list of nodes for which a node will be searched
     * for.
     * @param searchTerm The name for which a node with a matching name will be
     * searched for.
     * @return The node with the name matching the <code>name</code> given, or
     * null if no such node is found.
     */
    private Node searchForNode(NodeList listOfFields, String searchTerm) {
        return searchForNode(listOfFields, searchTerm, 0);
    }
    
    /**
     * 
     * @param listOfFields
     * @param searchTerm
     * @param occuranceIndex
     * @return 
     */
    private Node searchForNode(NodeList listOfFields, String searchTerm, int occuranceIndex) {
        
        Node node;
        Node out = null;
        Element element;
        String nodeName;
        int numChildNodes;

        searchingForNode = true;
        try {
            //System.out.println("Trying search for: " + name);
            for (int i = 0; i < listOfFields.getLength() && searchingForNode; i++) {
                //System.out.println("Trying Deeper: " + i);
                node = listOfFields.item(i);
                nodeName = node.getNodeName();
                numChildNodes = node.getChildNodes().getLength();
                if (node.getNodeName().equals(searchTerm)) {
                    if(occuranceIndex == 0){
                        System.out.println(Tools.Contstants.ANSI_CYAN + "Found: " + searchTerm + Tools.Contstants.ANSI_RESET);
                        if (searchingForNode) {
                            searchingForNode = false;
                            return node;
                        }
                    }else{
                        occuranceIndex--;
                    }
                } else {
                    //System.out.println(Tools.Contstants.ANSI_PURPLE + "Search Unsuccessful (" + node.getNodeName() + " " + searchingForNode + ")");
                    if (numChildNodes > 0 && searchingForNode) {
                        //System.out.println("Going In Fam");
                        element = (Element) node;
                        NodeList list = element.getChildNodes();
                        out = searchForNode(list, searchTerm, occuranceIndex);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(Tools.Contstants.ANSI_RED + "Error: " + e.getMessage() + Tools.Contstants.ANSI_RESET);
        }
        return out;
    }

    /**
     *
     * @param listOfFields
     * @param elementName
     * @param searchAttr
     * @return
     */
    private NodeList getElementAndAttribute(NodeList listOfFields, String elementName, String searchAttr) {
        
        NodeList out = null;
        Node node;
        Element elem;
        Element nElem;
        NodeList list;
        NodeList eList;
        
        

        try {
            for (int i = 0; i < listOfFields.getLength(); i++) {

                node = listOfFields.item(i);
                elem = (Element) node;
                list = elem.getElementsByTagName(elementName);
                nElem = (Element) list.item(1);
                eList = nElem.getChildNodes();

                if (nElem.hasAttribute(searchAttr)) {
                    System.out.println(elementName + " : \""
                            + ((Node) eList.item(0)).getNodeValue().trim()
                            + "\" has attribute \"" + nElem.getAttribute(searchAttr) + "\"");
                } else {
                    System.out.println(elementName + " : "
                            + ((Node) eList.item(0)).getNodeValue().trim());
                }
                return out;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return out;
    }

    /**
     *
     * @param listOfFields
     * @param elementName
     * @return
     */
    private NodeList getElementNodeList(NodeList listOfFields, String elementName) {
        NodeList out = null;
        Node node;
        Element elem;
        NodeList list;
        Element nElem;
        NodeList eList;
        try {

            for (int i = 0; i < listOfFields.getLength(); i++) {

                node = listOfFields.item(i);
                elem = (Element) node;
                list = elem.getElementsByTagName(elementName);
                nElem = (Element) list.item(0);
                eList = nElem.getChildNodes();
                out = eList;

                return out;
            }
        } catch (Exception e) {
            System.out.println(Tools.Contstants.ANSI_RED + "Error: "
                    + e + Tools.Contstants.ANSI_RESET);
        }
        return out;
    }

    private String getNodeAttribute(Node node) {
        String attr = null;
        attr = node.getNodeValue();
        return attr;
    }
    
    private class SearchNode{
        
        private int occurance;
        private Node node;
        private String searchTerm;
        
        public SearchNode(String searchTerm, int occurance){
            this.searchTerm = searchTerm;
            this.occurance = occurance;
        }
        
        public SearchNode (String searchTerm){
            this(searchTerm,0);
        }
        
        public SearchNode(){
            this("",0);
        }

        public int getOccurance() {
            return occurance;
        }

        public void setOccurance(int occurance) {
            this.occurance = occurance;
        }

        public Node getNode() {
            return node;
        }
        
        public String getNodeName(){
            if(node == null){
                return "";
            }else{
                return node.getNodeName();
            }
        }

        public void setNode(Node node) {
            this.node = node;
        }

        public String getSearchTerm() {
            return searchTerm;
        }

        public void setSearchTerm(String searchTerm) {
            this.searchTerm = searchTerm;
        }
        
    }
}
