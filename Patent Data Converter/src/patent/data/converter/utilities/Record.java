package patent.data.converter.utilities;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;


/**
 *
 * @author Nano
 */
public class Record {

    //<editor-fold defaultstate="collapsed" desc=" Private class variables ">
    /**
     * Type of document.
     */
    private enum DocumentType {
        PATENT, PATENT_APP
    };

    /**
     * Variable indicating whether a search is being done
     */
    private boolean searchingForNode;

    private RecordDataPoints dataPoints;
    
    private String recordID;
    
    //</editor-fold>
    
    /**
     *
     */
    public Record() {
        dataPoints = new RecordDataPoints();
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
                
                listOfFields = xmlDoc.getElementsByTagName("ca-bibliographic-data");

                //nl = getElementAndAttribute(listOfFields, "invention-title", "lang");
                documentID = getElementNodeList(listOfFields, "publication-reference");

                n = null;

                SearchNode[] searchNodes = {new SearchNode("publication-reference"),
                    new SearchNode("classifications-ipcr"), new SearchNode("classification-national"),
                    new SearchNode("application-reference"), new SearchNode("language-of-filing"),
                    new SearchNode("priority-claims"), new SearchNode("dates-of-public-availability"),
                    new SearchNode("pct-or-regional-filing-data"), new SearchNode("pct-or-regional-publishing-data"),
                    new SearchNode("ca-office-specific-bib-data"), new SearchNode("invention-title", 0),
                    new SearchNode("invention-title", 1)};

                for (SearchNode sn : searchNodes) {
                    sn.setNode(searchForNode(listOfFields, sn.getSearchTerm(), sn.getOccurance()));
                    //System.out.println(Tools.Contstants.ANSI_YELLOW + "Name: " + sn.getNodeName() + Tools.Contstants.ANSI_RESET);
                    handleDataNode(sn);
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

    /**
     *
     * @param docString
     * @return
     */
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
     *
     * @param node
     * @param searchTerm
     * @return
     */
    private Node searchForNode(Node node, String searchTerm) {
        return searchForNode(node, searchTerm, 0);
    }

    /**
     *
     * @param node
     * @param searchTerm
     * @param occurance
     * @return
     */
    private Node searchForNode(Node node, String searchTerm, int occurance) {
        return searchForNode(node.getChildNodes(), searchTerm, occurance);
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
     * Returns the <code>occuranceIndex</code>th node whose name matches the
     * given <code>searchTerm</code> from the tree of nodes in the
     * <code> listOfFields</code>.
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
            
            for (int i = 0; i < listOfFields.getLength() && searchingForNode; i++) {
                
                node = listOfFields.item(i);
                nodeName = node.getNodeName();
                numChildNodes = node.getChildNodes().getLength();
                if (node.getNodeName().equals(searchTerm)) {
                    if (occuranceIndex == 0) {
                        
                        if (searchingForNode) {
                            searchingForNode = false;
                            return node;
                        }
                    } else {
                        occuranceIndex--;
                    }
                } else {
                    
                    if (numChildNodes > 0 && searchingForNode) {
                        
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

    private String getNodeAttributeValue(Node n, String attr) {
        if (n != null) {
            Element e = (Element) n;
            if (e.hasAttribute(attr)) {
                return e.getAttribute(attr);
            }
        }
        return "";
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

    /**
     *
     * @param n
     * @return
     */
    private String getNodeText(Node n) {
        if (n == null) {
            return "";
        } else {
            if(n.getChildNodes().getLength() > 0){
                return n.getChildNodes().item(0).getNodeValue().trim();
            }else{
                return "";
            }
            
        }
    }

    /**
     * 
     * @return 
     */
    public RecordDataPoints getDataPoints() {
        return dataPoints;
    }

    /**
     * 
     * @param dataPoints 
     */
    public void setDataPoints(RecordDataPoints dataPoints) {
        this.dataPoints = dataPoints;
    }

    /**
     * 
     * @return 
     */
    public String getRecordID() {
        return getDataPoints().getDocumentNumber();
    }

    /**
     * 
     * @param recordID 
     */
    public void setRecordID(String recordID) {
        getDataPoints().setDocumentNumber(recordID);
    }
    
    /**
     *
     * @param searchNode
     */
    public void handleDataNode(SearchNode searchNode) {

        switch (searchNode.getNodeName()) {
            case "publication-reference":
                handlePubRef(searchNode);
                break;
            case "classifications-ipcr":
                handleCLassIPCR(searchNode);
                break;
            case "classification-national":
                handleClassNat(searchNode);
                break;
            case "application-reference":
                handleAppRef(searchNode);
                break;
            case "language-of-filing":
                handleLangOfFiling(searchNode);
                break;
            case "priority-claims":
                handlePriorityClaims(searchNode);
                break;
            case "dates-of-public-availability":
                handleDateOfPubAvail(searchNode);
                break;
            case "pct-or-regional-filing-data":
                handlePctRegFilingData(searchNode);
                break;
            case "pct-or-regional-publishing-data":
                handlePctRegPubData(searchNode);
                break;
            case "ca-office-specific-bib-data":
                handleCaOfficeSpecBibData(searchNode);
                break;
            case "invention-title":
                boolean eng = false;
                handleInventionTitleEN(searchNode);
                handleInventionTitleFR(searchNode);
                break;
        }

    }

    /**
     *
     * @param sn
     */
    private void handlePubRef(SearchNode sn) {
        Node docID;
        Node dateNode;
        Node docNumNode;
        Date issuedDate;
        String date;
        String docNum;
        if (sn.getNode() != null) {
            docID = searchForNode(sn.getNode(), "document-id");
            if (docID != null) {
                dateNode = searchForNode(docID, "date");
                docNumNode = searchForNode(docID, "doc-number");
                date = getNodeText(dateNode);
                docNum = getNodeText(docNumNode);
                issuedDate = new Date(date);
                dataPoints.setIssuedDate(issuedDate);
                dataPoints.setDocumentNumber(docNum);
            }
        }
    }

    /**
     *
     * @param sn
     */
    private void handleCLassIPCR(SearchNode sn) {
    }

    /**
     *
     * @param sn
     */
    private void handleClassNat(SearchNode sn) {
    }

    /**
     *
     * @param sn
     */
    private void handleAppRef(SearchNode sn) {
    }

    /**
     *
     * @param sn
     */
    private void handleLangOfFiling(SearchNode sn) {
        if (sn.getNode() != null) {
            Node lof = sn.getNode();
            if (lof != null) {
            }
        }

    }

    /**
     *
     * @param sn
     */
    private void handlePriorityClaims(SearchNode sn) {

    }

    /**
     *
     * @param sn
     */
    private void handleInventionTitleEN(SearchNode sn) {
        if (getNodeAttributeValue(sn.getNode(), "lang").toLowerCase().equals("en")) {
            dataPoints.setEnglishTitle(getNodeText(sn.getNode()));
        }
    }

    /**
     *
     * @param sn
     */
    private void handleInventionTitleFR(SearchNode sn) {
        if (getNodeAttributeValue(sn.getNode(), "lang").toLowerCase().equals("fr")) {
            dataPoints.setFrenchTitle(getNodeText(sn.getNode()));
        }
    }

    /**
     *
     * @param sn
     */
    private void handleDateOfPubAvail(SearchNode sn) {

    }

    /**
     *
     * @param sn
     */
    private void handlePctRegFilingData(SearchNode sn) {
        if (sn.getNode() != null) {
            Node dateNode = searchForNode(sn.getNode(), "date");
            String date = getNodeText(dateNode);
            dataPoints.setFiledDate(new Date(date));
        }
    }

    /**
     *
     * @param sn
     */
    private void handlePctRegPubData(SearchNode sn) {
    }

    /**
     *
     * @param sn
     */
    private void handleCaOfficeSpecBibData(SearchNode sn) {
    }

    /**
     *
     */
    protected class SearchNode {

        private int occurance;
        private Node node;
        private String searchTerm;

        /**
         *
         * @param searchTerm
         * @param occurance
         */
        public SearchNode(String searchTerm, int occurance) {
            this.searchTerm = searchTerm;
            this.occurance = occurance;
        }

        /**
         *
         * @param searchTerm
         */
        public SearchNode(String searchTerm) {
            this(searchTerm, 0);
        }

        /**
         *
         */
        public SearchNode() {
            this("", 0);
        }

        /**
         *
         * @return
         */
        public int getOccurance() {
            return occurance;
        }

        /**
         *
         * @param occurance
         */
        public void setOccurance(int occurance) {
            this.occurance = occurance;
        }

        /**
         *
         * @return
         */
        public Node getNode() {
            return node;
        }

        /**
         *
         * @return
         */
        public String getNodeName() {
            if (node == null) {
                return "";
            } else {
                return node.getNodeName();
            }
        }

        /**
         *
         * @param node
         */
        public void setNode(Node node) {
            this.node = node;
        }

        /**
         *
         * @return
         */
        public String getSearchTerm() {
            return searchTerm;
        }

        /**
         *
         * @param searchTerm
         */
        public void setSearchTerm(String searchTerm) {
            this.searchTerm = searchTerm;
        }

    }

    @Override
    public String toString() {
        return "Record{" + "searchingForNode=" + searchingForNode + ", dataPoints=" + dataPoints + '}';
    }
    
}
