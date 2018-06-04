/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patent.data.converter.utilities;

/**
 *
 * @author nano
 */
public class Date {

    int year;
    int month;
    int day;
    
    public Date(){
        this(0,0,0);
    }

    /**
     *
     * @param str
     */
    public Date(String str) {
        int month;
        int day;
        int year;

        if (str.length() == 6) {
            month = Integer.parseInt(str.substring(4, 6));
            day = Integer.parseInt(str.substring(6, 8));
            year = Integer.parseInt(str.substring(0, 4));
        } else if (str.length() == 4) {
            month = Integer.parseInt(str.substring(4, 6));
            year = Integer.parseInt(str.substring(0, 4));
            day = 0;
        } else {
            month = day = year = 0;
        }

        this.month = month;
        this.day = day;
        this.year = year;
    }

    /**
     *
     * @param month
     * @param year
     */
    public Date(int month, int year) {
        this(year, month, 1);
    }

    /**
     *
     * @param month
     * @param day
     * @param year
     */
    public Date(int month, int day, int year) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    /**
     * Converts a string from format YYYYMMDD to a standard Java date object.
     *
     * @param str
     * @return
     */
    public Date strToDate(String str) {

        int month;
        int day;
        int year;
        if (str.length() == 6) {
            month = Integer.parseInt(str.substring(4, 6));
            day = Integer.parseInt(str.substring(6, 8));
            year = Integer.parseInt(str.substring(0, 4));
        } else {
            month = day = year = 0;
        }

        return new Date(month, day, year);
    }

    /**
     *
     * @return
     */
    public String getStrDate() {
        return month + "/" + day + "/" + year;
    }

    /**
     *
     * @return
     */
    public int getYear() {
        return year;
    }

    /**
     *
     * @param year
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     *
     * @return
     */
    public int getMonth() {
        return month;
    }

    /**
     *
     * @param month
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     *
     * @return
     */
    public int getDay() {
        return day;
    }

    /**
     *
     * @param day
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Date{" + "year=" + year + ", month=" + month + ", day=" + day + '}';
    }

}