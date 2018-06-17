package me.nanois.patentdataorganizer.utilities;

import java.util.Objects;

/**
 *
 * @author nano
 */
public class Date {

    private int year;
    private int month;
    private int day;

    private String stringDate;

    /**
     *
     */
    public Date() {
        this(0, 0, 0);
        stringDate = "";
    }

    /**
     *
     * @param str
     */
    public Date(String str) {
        int month;
        int day;
        int year;
        stringDate = str;
        if (str.length() == 8) {
            month = Integer.parseInt(str.substring(4, 6));
            day = Integer.parseInt(str.substring(6, 8));
            year = Integer.parseInt(str.substring(0, 4));
        } else if (str.length() == 6) {
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
     * Returns a positive value when <code>b<code> is greater than <code>a<code>.
     * A negative value when <code>b<code> is less than <code>a<code>, and 0 when
     * they are equal.
     * @param a
     * @param b
     * @return 
     */
    public static int compareDays(Date a, Date b){
        if(a == null && b == null){
            return 0;
        }else{
            if(a == null){
                return 100;
            }else if(b == null){
                return -100;
            }else{
                return b.day - a.day;
            }
        }
    }
    
    /**
     * Returns a positive value when <code>b<code> is greater than <code>a<code>.
     * A negative value when <code>b<code> is less than <code>a<code>, and 0 when
     * they are equal.
     * @param a
     * @param b
     * @return 
     */
    public static int compareMonths(Date a, Date b){
        if(a == null && b == null){
            return 0;
        }else{
            if(a == null){
                return 1;
            }else if(b == null){
                return -1;
            }else{
                return b.month - a.month;
            }
        }
    }
    
    /**
     * Returns a positive value when <code>b<code> is greater than <code>a<code>.
     * A negative value when <code>b<code> is less than <code>a<code>, and 0 when
     * they are equal.
     * @param a
     * @param b
     * @return 
     */
    public static int compareYears(Date a, Date b){
        if(a == null && b == null){
            return 0;
        }else{
            if(a == null){
                return 1;
            }else if(b == null){
                return -1;
            }else{
                return b.year - a.year;
            }
        }
    }
    
    /**
     * Returns a positive value when <code>b<code> is greater than <code>a<code>.
     * A negative value when <code>b<code> is less than <code>a<code>, and 0 when
     * they are equal.
     * @param a
     * @param b
     * @return 
     */
    public static int compareDates(Date a, Date b){
        if(a == null && b == null){
            return 0;
        }else{
            if(a == null){
                return 1;
            }else if(b == null){
                return -1;
            }else{
                int y = compareYears(a, b);
                int m = compareMonths(a, b);
                if(y == 0){
                    if(m == 0){
                        return compareDays(a, b);
                    }else{
                        return m;
                    }
                }else{
                    return y;
                }
            }
        }
    }
    
    public boolean isNewerThan(Date d){
        if(compareDates(d,this)> 0){
            return true;
        }
        return false;
    }
    
    public boolean isOlderThan(Date d){
        if(compareDates(d, this) < 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Date other = (Date) obj;
        if (this.year != other.year) {
            return false;
        }
        if (this.month != other.month) {
            return false;
        }
        if (this.day != other.day) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.year;
        hash = 97 * hash + this.month;
        hash = 97 * hash + this.day;
        hash = 97 * hash + Objects.hashCode(this.stringDate);
        return hash;
    }

    /**
     *
     * @return
     */
    public String getReadable() {
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
     * @param stringDate
     */
    public void setStringDate(String stringDate) {
        this.stringDate = stringDate;
    }

    /**
     *
     * @return
     */
    public String getStringDate() {
        return stringDate;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Date{" + "year=" + year + ", month=" + month + ", day=" + day + ", strDate=" + stringDate + '}';
    }

}
