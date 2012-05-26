/**
 * Persian Calendar see: http://code.google.com/p/persian-calendar/
   Copyright (C) 2012  Mortezaadi@gmail.com
   GWTPersianCalendar.java
   
   Persian Calendar is free software: you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation, either version 3 of the License, or
   (at your option) any later version.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.sahandrc.calendar.gwt;

import java.util.Date;

import com.sahandrc.calendar.PersianDateParser;
import com.sahandrc.calendar.utils.PersianCalendarConstants;
import com.sahandrc.calendar.utils.PersianCalendarUtils;

public class GWTPersianCalendar extends Date {

    public static int YEAR=1;
    public static int MONTH=2;
    public static int DAY=3;
    public static int ZONE_OFFSET = 3;
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private int persianYear;
    private int persianMonth;
    private int persianDay;
    // use to seperate PersianDate's field and also Parse the DateString based on this delimiter
    private String delimiter = "/";
    
    private long convertToMilis(long julianDate) {
        return PersianCalendarConstants.MILLIS_JULIAN_EPOCH + julianDate * PersianCalendarConstants.MILLIS_OF_A_DAY
                + PersianCalendarUtils.ceil(getTime()  - PersianCalendarConstants.MILLIS_JULIAN_EPOCH, PersianCalendarConstants.MILLIS_OF_A_DAY);
    }
    
 
    
   
    
    /**
     * Calculate persian date from current Date and
     * populates the corresponding fields(persianYear, persianMonth, persianDay)
     */
    protected void calculatePersianDate() {
        long julianDate = ((long) Math.floor((getTime() - PersianCalendarConstants.MILLIS_JULIAN_EPOCH ))/
                PersianCalendarConstants.MILLIS_OF_A_DAY);
        long PersianRowDate = PersianCalendarUtils.julianToPersian(julianDate);
        long year = PersianRowDate >> 16;
        int month = (int) (PersianRowDate & 0xff00) >> 8;
        int day = (int) (PersianRowDate & 0xff);
        this.persianYear = (int) (year > 0 ? year : year - 1);
        this.persianMonth = month;
        this.persianDay = day;
    }
    
    /**
     * 
     * Determines if the given year is a leap year in persian calendar.
     * Returns true if the given year is a leap year.
     * 
     * @return boolean
     */
    public boolean isPersianLeapYear() {
        //calculatePersianDate();       
        return PersianCalendarUtils.isPersianLeapYear(this.persianYear);
    }

    

    /**
     * set the persian date 
     * it converts PersianDate to the Julian and assigned equivalent milliseconds to the instance
     * @param persianYear
     * @param persianMonth
     * @param persianDay
     */
    public void setPersianDate(int persianYear, int persianMonth, int persianDay) {
        this.persianYear = persianYear;
        this.persianMonth = persianMonth;
        this.persianDay = persianDay;
        setTime(convertToMilis(PersianCalendarUtils.persianToJulian(this.persianYear > 0 ? this.persianYear
                : this.persianYear + 1, this.persianMonth-1, this.persianDay)));
    }

    public int getPersianYear() {
        //calculatePersianDate();
        return this.persianYear ;
    }

    /**
     * 
     * @return int    persian month number 
     */
    public int getPersianMonth() {
        //calculatePersianDate();
        return this.persianMonth+1;
    }

    /**
     * 
     * @return String   persian month name
     */
    public String getPersianMonthName() {
        //calculatePersianDate();
        return PersianCalendarConstants.persianMonthNames[this.persianMonth];
    }

    /**
     * 
     * @return int Persian day in month
     */
    public int getPersianDay() {
        //calculatePersianDate();
        return this.persianDay;
    }

    /**
     * 
     * @return String Name of the day in week
     */
    public String getPersianWeekDayName() {
        //value (0 = Sunday, 1 = Monday, 2 = Tuesday, 3 = Wednesday, 4 = Thursday, 5 = Friday, 6 = Saturday)
        switch(getDay())
        {
        case 6:  return PersianCalendarConstants.persianWeekDays[0];
        case 0:    return PersianCalendarConstants.persianWeekDays[1];
        case 1:    return PersianCalendarConstants.persianWeekDays[2];
        case 2:   return PersianCalendarConstants.persianWeekDays[3];
        case 3: return PersianCalendarConstants.persianWeekDays[4];
        case 4:  return PersianCalendarConstants.persianWeekDays[5];
        default :       return PersianCalendarConstants.persianWeekDays[6];
        }
        
    }

   

   /**
    * 
    * @return String of Persian Date
    *           ex: شنبه  01  خرداد  1361
    */
   public String getPersianLongDate(){
       return  getPersianWeekDayName() + "  " + formatToMilitary(this.persianDay) + "  "
              +  getPersianMonthName() + "  " + this.persianYear ;
       
   }
    /**
     * 
     * @return String of persian date formatted by 'YYYY[delimiter]mm[delimiter]dd'
     *          default delimiter is '/'
     */
    public String getPersianShortDate(){
        //calculatePersianDate();
        return "" + formatToMilitary(this.persianYear) + delimiter + formatToMilitary(getPersianMonth()) + delimiter
                + formatToMilitary(this.persianDay);
    }
    
    private String formatToMilitary(int i){
        return (i<9)? "0"+i:String.valueOf(i);
    }
    
    /**
     * add specific amout of fields to the current date
     * for now doesnt handle before 1 farvardin hejri (before epoch)
     * @param field 
     * @param amount
     * <pre>
     *  Usage:
     *  {@code
     *  addPersianDate(Calendar.YEAR, 2);
     *  addPersianDate(Calendar.MONTH, 3);
     *  }
     * </pre>
     * u can also use Calendar.HOUR_OF_DAY,Calendar.MINUTE, Calendar.SECOND, Calendar.MILLISECOND etc
     */
    // 
    public void addPersianDate(int field , int amount){
        if (amount == 0) {
            return;   // Do nothing!
        }

        if (field < 0 || field >= ZONE_OFFSET) {
            throw new IllegalArgumentException();
        }
        
        if(field==YEAR){
            setPersianDate(this.persianYear + amount, getPersianMonth() , this.persianDay);
            return;
        }else if(field==MONTH){
                setPersianDate(this.persianYear + ((getPersianMonth()+ amount)/12) , (getPersianMonth()  + amount)%12 , this.persianDay);
                return;
        }
        
        calculatePersianDate();
    }
    
    /**<pre>
     *    use <code>{@link PersianDateParser}</code> to parse string 
     *    and get the Persian Date.
     * </pre>
     * @see PersianDateParser
     * @param dateString
     */
    public void parsePersianDate(String dateString){
        GWTPersianCalendar p =  new GWTPersianDateParser(dateString,delimiter).getPersianDate();
        setPersianDate(p.getPersianYear(), p.getPersianMonth(), p.getPersianDay());
    }

    public GWTPersianCalendar() {
       super();
       calculatePersianDate();
    }

    /**
     * 
     * @return String delimiter
     */
    public String getDelimiter() {
        return delimiter;
    }


    /**
     * assign delimiter to use as a separator of date fields.
     * @param delimiter
     */
    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }
    
    
    @Override
    public String toString() {
        String str = super.toString();
        return str.substring(0, str.length()-1) + ",PersianDate=" 
                + getPersianShortDate() + "]";
    }
    
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
                    
    }
    
    @Override
    public int hashCode() {
        return super.hashCode();
    }
    
    @Override
    public void setDate(int date) {
        // TODO Auto-generated method stub
        super.setDate(date);
        calculatePersianDate();
    }
    
    @Override
    public long getTime() {
        // TODO Remove timeZoneoffset like /*- (getTimezoneOffset()*60000) */
        return super.getTime();
    }
    
    @Override
    public void setTime(long time) {
     // TODO Remove timeZoneoffset like /*- (getTimezoneOffset()*60000) */
        super.setTime(time);
        calculatePersianDate();
    }
    @Override
    public void setHours(int hours) {
        super.setHours(hours);
        calculatePersianDate();
    }
    @Override
    public void setMinutes(int minutes) {
        super.setMinutes(minutes);
        calculatePersianDate();
    }
    @Override
    public void setMonth(int month) {
        super.setMonth(month);
        calculatePersianDate();
    }
    @Override
    public void setSeconds(int seconds) {
        super.setSeconds(seconds);
        calculatePersianDate();
    }
    @Override
    public void setYear(int year) {
        super.setYear(year);
        calculatePersianDate();
    }
  
    
}
