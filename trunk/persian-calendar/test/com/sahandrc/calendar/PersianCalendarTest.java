/**
 * Persian Calendar see: http://code.google.com/p/persian-calendar/
   Copyright (C) 2012  Mortezaadi@gmail.com
   PersianCalendarTest.java
   
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
package com.sahandrc.calendar;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import com.sahandrc.calendar.utils.PersianCalendarConstants;

public class PersianCalendarTest {

    private PersianCalendar persianCal;
    @Before
    public void setUp() throws Exception {
        persianCal = new PersianCalendar();
        
    }

    @Test
    public void testIsPersianLeapYear() {
        persianCal.setPersianDate(1391, 01, 01);
        assertTrue(persianCal.isPersianLeapYear());
    }

    @Test
    public void testSetPersianDate() {
        persianCal.setPersianDate(1361, 3, 1);
        assertEquals(1361, persianCal.getPersianYear());
        assertEquals(3, persianCal.getPersianMonth());
        assertEquals(1, persianCal.getPersianDay());
    }

  
    @Test
    public void testGetPersianMonthName() {
        persianCal.setPersianDate(1361, 3, 1);
        assertEquals("خرداد", persianCal.getPersianMonthName());
    }

    @Test
    public void testGetPersianDay() {
        persianCal.setPersianDate(1361, 7, 1);
        assertEquals(1, persianCal.getPersianDay());
    }

    @Test
    public void testGetPersianWeekDayName() {
        persianCal.setPersianDate(1361, 3, 1);
        assertEquals(PersianCalendarConstants.persianWeekDays[0], persianCal.getPersianWeekDayName());
        persianCal.addPersianDate(Calendar.DATE, 1);
        assertEquals(PersianCalendarConstants.persianWeekDays[1], persianCal.getPersianWeekDayName());
        persianCal.addPersianDate(Calendar.DATE, 1);
        assertEquals(PersianCalendarConstants.persianWeekDays[2], persianCal.getPersianWeekDayName());
        persianCal.addPersianDate(Calendar.DATE, 1);
        assertEquals(PersianCalendarConstants.persianWeekDays[3], persianCal.getPersianWeekDayName());
        persianCal.addPersianDate(Calendar.DATE, 1);
        assertEquals(PersianCalendarConstants.persianWeekDays[4], persianCal.getPersianWeekDayName());
        persianCal.addPersianDate(Calendar.DATE, 1);
        assertEquals(PersianCalendarConstants.persianWeekDays[5], persianCal.getPersianWeekDayName());
        persianCal.addPersianDate(Calendar.DATE, 1);
        assertEquals(PersianCalendarConstants.persianWeekDays[6], persianCal.getPersianWeekDayName());
    }

    @Test
    public void testGetPersianShortDate() {
       persianCal.set(1982, Calendar.MAY, 22);
       assertEquals("1361/03/01", persianCal.getPersianShortDate());  
    }

    @Test(expected=IllegalArgumentException.class)
    public void checkExceptionsThrownCorrectly() {
        persianCal.add(-1, 10);
    }
    
    @Test
    public void testAddPersianDate() {
        persianCal.set(1982, Calendar.MAY, 22);
        
        persianCal.add(Calendar.MONTH, 0);
        
        
        
        persianCal.addPersianDate(Calendar.MONTH, 3);
        assertEquals(6, persianCal.getPersianMonth());
        
        persianCal.addPersianDate(Calendar.MONTH, 33);
     
        assertEquals(1364, persianCal.getPersianYear());
        
        assertEquals(3, persianCal.getPersianMonth());
        assertEquals(1, persianCal.getPersianDay());
        
        
        persianCal.addPersianDate(Calendar.YEAR, 5);
        assertEquals(1369, persianCal.getPersianYear());
        

        
        persianCal.addPersianDate(Calendar.DATE, 5);
        assertEquals(1369, persianCal.getPersianYear());
        assertEquals(6, persianCal.getPersianDay());
        

      
        persianCal.addPersianDate(Calendar.DATE, 50); 
        assertEquals(4, persianCal.getPersianMonth());
        assertEquals(25, persianCal.getPersianDay());
        
    }

    @Test
    public void testParse() {
        persianCal.parse("1391/03/01");
        assertEquals(1391, persianCal.getPersianYear());
        assertEquals(3, persianCal.getPersianMonth());
        assertEquals(1, persianCal.getPersianDay());
    }

}
