/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.nanois.patentdataorganizer.utilities;

import java.util.ArrayList;

/**
 *
 * @author nano
 */
public class RecordDataPoints {

    //<editor-fold defaultstate="collapsed" desc=" Private class variables ">
    /**
     * (45) Issued Date
     */
    private Date issuedDate;
    /**
     * Reissued dates
     */
    private ArrayList<Date> reissuedDates;
    /**
     * (22) Filing Date
     */
    private Date filedDate;
    /**
     * (41) Open to Public Inspection Date
     */
    private Date openToPubInsp;
    /**
     * (74) Agent
     */
    private String agent;
    /**
     * (21) Application Number
     */
    private String applicationNumber;
    /**
     * (11)Document Number
     */
    private String documentNumber;
    /**
     * (54) English Title
     */
    private String englishTitle;
    /**
     * (54) French Title
     */
    private String frenchTitle;
    /**
     * Examination Requested
     */
    private String examReq;
    /**
     * 
     */
    private String reExamCert;
    /**
     * 
     */
    private String canPatentClass;
    /**
     * 
     */
    private String intPatentClass;
    /**
     * 
     */
    private String patentCoopTreaty;
    /**
     * 
     */
    private String appPrioData;
    /**
     * 
     */
    private Date pctPubDate;
    /**
     * Record Kind
     */
    private String kind;
    /**
     * Record Country
     */
    private String country;
    /**
     * 
     */
    private ArrayList<String> inventors;
    /**
     * 
     */
    private ArrayList<String> owners;
    /**
     * 
     */
    private ArrayList<String> applicants;
    /**
     * 
     */
    private String availOfLic;
    /**
     * 
     */
    private String docType;
    
    //</editor-fold>
    
    /**
     * Constructor
     */
    public RecordDataPoints() {
        issuedDate = new Date();
        filedDate = new Date();
        openToPubInsp = new Date();
        pctPubDate = new Date();
        agent = "";
        applicationNumber = "";
        documentNumber = "";
        englishTitle = "";
        frenchTitle = "";
        examReq = "";
        kind = "";
        country = "";
        inventors = new ArrayList<String>();
        owners = new ArrayList<String>();
        applicants = new ArrayList<String>();
        reissuedDates = new ArrayList<Date>();
        
    }

    //<editor-fold defaultstate="collapsed" desc=" Accessors and Mutators ">
    
    public Date getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(Date issuedDate) {
        this.issuedDate = issuedDate;
    }

    public ArrayList<Date> getReissuedDates() {
        return reissuedDates;
    }

    public void setReissuedDates(ArrayList<Date> reissuedDates) {
        this.reissuedDates = reissuedDates;
    }

    public Date getFiledDate() {
        return filedDate;
    }

    public void setFiledDate(Date filedDate) {
        this.filedDate = filedDate;
    }

    public Date getOpenToPubInsp() {
        return openToPubInsp;
    }

    public void setOpenToPubInsp(Date openToPubInsp) {
        this.openToPubInsp = openToPubInsp;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getAppNum() {
        return applicationNumber;
    }

    public void setApplicationNumber(String applicationNumber) {
        this.applicationNumber = applicationNumber;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getEnglishTitle() {
        return englishTitle;
    }

    public void setEnglishTitle(String englishTitle) {
        this.englishTitle = englishTitle;
    }

    public String getFrenchTitle() {
        return frenchTitle;
    }

    public void setFrenchTitle(String frenchTitle) {
        this.frenchTitle = frenchTitle;
    }

    public String getExamReq() {
        return examReq;
    }

    public void setExamReq(String examReq) {
        this.examReq = examReq;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public ArrayList<String> getInventors() {
        return inventors;
    }

    public void setInventors(ArrayList<String> inventors) {
        this.inventors = inventors;
    }

    public ArrayList<String> getOwners() {
        return owners;
    }

    public void setOwners(ArrayList<String> owners) {
        this.owners = owners;
    }

    public ArrayList<String> getApplicants() {
        return applicants;
    }

    public void setApplicants(ArrayList<String> applicants) {
        this.applicants = applicants;
    }

    public String getAvailOfLic() {
        return availOfLic;
    }

    public void setAvailOfLic(String availOfLic) {
        this.availOfLic = availOfLic;
    }

    public String getReExamCert() {
        return reExamCert;
    }

    public void setReExamCert(String reExamCert) {
        this.reExamCert = reExamCert;
    }

    public String getCanPatentClass() {
        return canPatentClass;
    }

    public void setCanPatentClass(String canPatentClass) {
        this.canPatentClass = canPatentClass;
    }

    public String getIntPatentClass() {
        return intPatentClass;
    }

    public void setIntPatentClass(String intPatentClass) {
        this.intPatentClass = intPatentClass;
    }

    public String getPatentCoopTreaty() {
        return patentCoopTreaty;
    }

    public void setPatentCoopTreaty(String patentCoopTreaty) {
        this.patentCoopTreaty = patentCoopTreaty;
    }

    public String getAppPrioData() {
        return appPrioData;
    }

    public void setAppPrioData(String appPrioData) {
        this.appPrioData = appPrioData;
    }

    public Date getPctPubDate() {
        return pctPubDate;
    }

    public void setPctPubDate(Date pctPubDate) {
        this.pctPubDate = pctPubDate;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }
    
    public String getInventorsString(){
        String out = "";
        if(inventors.size() > 0){
            out = inventors.get(0);
        }
        for(int i = 1; i < inventors.size() ;i++){
            out += ", " + inventors.get(i);
        }
        return out;
    }
    
    public String getOwnersString(){
        String out = "";
        if(owners.size() > 0){
            out = owners.get(0);
        }
        for(int i = 1; i < owners.size() ;i++){
            out += ", " + owners.get(i);
        }
        return out;
    }
    
    public String getApplicantsString(){
        String out = "";
        if(applicants.size() > 0){
            out = applicants.get(0);
        }
        for(int i = 1; i < applicants.size() ;i++){
            out += ", " + applicants.get(i);
        }
        return out;
    }
    
    public String getReissuedDatesString(){
        String out = "";
        if(reissuedDates.size() > 0){
            out = reissuedDates.get(0).getReadable();
        }
        for(int i = 1; i < reissuedDates.size();i++){
            out += ", " + reissuedDates.get(i).getReadable();
        }
        return out;
    }

    //</editor-fold>
    
    /**
     * 
     * @param alias
     * @return 
     */
    public String getDataPoint(String alias) {
        //System.out.println("Alias: " + alias);
        switch (alias) {
            case "Document Type":
                return docType;
            case "Document Number":
                return documentNumber;
            case "Application Number":
                return applicationNumber;
            case "English Title":
                return englishTitle;
            case "French Title":
                return frenchTitle;
            case "Inventors":
                return getInventorsString();
            case "Owners":
                return getOwnersString();
            case "Applicants":
                return getApplicantsString();
            case "Agent":
                return agent;
            case "Issued":
                return issuedDate.getReadable();
            case "Reissued":
                return getReissuedDatesString();
            case "Filed":
                return filedDate.getReadable();
            case "Open To Public Inspection":
                return openToPubInsp.getReadable();
            case "Examination Requested":
                return examReq;
            case "Re-examination Certificate":
                return reExamCert;
            case "Canadian Patent Classification":
                return canPatentClass;
            case "International Patent Classification":
                return intPatentClass;
            case "Patent Cooperation Treaty":
                return patentCoopTreaty;
            case "Application Priority Data":
                return appPrioData;
            case "Availability of Licence":
                return availOfLic;
            case "PCT Publication Date":
                return pctPubDate.getReadable();
        }
        return "";
    }

    @Override
    public String toString() {
        return "RecordDataPoints{" + "issuedDate=" + issuedDate + ", filedDate="
                + filedDate + ", openToPubInsp=" + openToPubInsp + ", agent="
                + agent + ", applicationNumber=" + applicationNumber + ", documentNumber="
                + documentNumber + ", englishTitle=" + englishTitle + ", frenchTitle="
                + frenchTitle + ", examReq=" + examReq + '}';
    }

    private class RecordDataPoint {

        Object data;
        boolean enabled;
    }

}
