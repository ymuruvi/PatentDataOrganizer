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

    private String docNum;
    private String appNum;
    private String englishTitle;
    private String frenchTitle;

    private static int s = 0;
    private Inventor[] inventors;

    private boolean searchingForNode;

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
                nl = getElementAndAttribute(listOfFields, "invention-title", "lang");
                documentID = getElementNodeList(listOfFields, "publication-reference");
                
                n = null;
                getNode(n, listOfFields, "ca-administrative-status-change");
                if (n != null) {
                    System.out.println(n.getNodeName());
                }
            } else {
                System.out.println(Tools.Contstants.ANSI_RED + "Error reading file: "
                        + fileName + Tools.Contstants.ANSI_RESET);
            }
        } else {

        }
    }

    private static Document getDocument(String docString) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            factory.setIgnoringComments(true);
            factory.setIgnoringElementContentWhitespace(true);
            factory.setValidating(true);

            DocumentBuilder builder = factory.newDocumentBuilder();

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
     * @param name The name for which a node with a matching name will be
     * searched for.
     * @return The node with the name matching the <code>name</code> given, or
     * null if no such node is found.
     */
    private Node getNode(Node outNode, NodeList listOfFields, String name) {
        Node n = null;
        Node node;
        Element element;
        String nodeName;
        int numChildNodes;

        outNode = null;
        searchingForNode = true;
        try {
            for (int i = 0; i < listOfFields.getLength() && searchingForNode; i++) {
                node = listOfFields.item(i);
                nodeName = node.getNodeName();
                numChildNodes = node.getChildNodes().getLength();
                if (node.getNodeName().equals(name)) {
                    System.out.println(Tools.Contstants.ANSI_CYAN
                            + "Found: " + name + Tools.Contstants.ANSI_RESET);
                    if (searchingForNode) {
                        outNode = node;
                        searchingForNode = false;
                    }
                } else {
                    System.out.println(Tools.Contstants.ANSI_PURPLE + "Search Unsuccessful (" + node.getNodeName() + " " + searchingForNode + ")");
                    if (numChildNodes > 0 && searchingForNode) {
                        element = (Element) node;
                        NodeList list = element.getChildNodes();
                        getNode(outNode, list, name);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(Tools.Contstants.ANSI_RED + "Error: "
                    + e.getMessage() + Tools.Contstants.ANSI_RESET);
        }
        return n;
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
        NodeList list;
        Element nElem;
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
}
