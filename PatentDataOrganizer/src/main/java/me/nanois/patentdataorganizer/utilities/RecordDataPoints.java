/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.nanois.patentdataorganizer.utilities;

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
    private Date[] reissuedDates;
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
     * Record Kind
     */
    private String kind;
    /**
     * Record Country
     */
    private String country;

    //</editor-fold>
    /**
     * Constructor
     */
    public RecordDataPoints() {
        issuedDate = new Date();
        filedDate = new Date();
        openToPubInsp = new Date();
        agent = "";
        applicationNumber = "";
        documentNumber = "";
        englishTitle = "";
        frenchTitle = "";
        examReq = "";
        kind = "";
        country = "";
    }

    //<editor-fold defaultstate="collapsed" desc=" Accessors and Mutators ">
    public Date getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(Date issuedDate) {
        this.issuedDate = issuedDate;
    }

    public Date[] getReissuedDates() {
        return reissuedDates;
    }

    public void setReissuedDates(Date[] reissuedDates) {
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

    public String getApplicationNumber() {
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

    //</editor-fold>
    @Override
    public String toString() {
        return "RecordDataPoints{" + "issuedDate=" + issuedDate + ", filedDate="
                + filedDate + ", openToPubInsp=" + openToPubInsp + ", agent="
                + agent + ", applicationNumber=" + applicationNumber + ", documentNumber="
                + documentNumber + ", englishTitle=" + englishTitle + ", frenchTitle="
                + frenchTitle + ", examReq=" + examReq + '}';
    }

}