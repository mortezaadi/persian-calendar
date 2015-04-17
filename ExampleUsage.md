How to use Persian Calendar

# About #

Here is some example of how to use PersianCalendar library.

# How to use the library #

  1. download latest PersianCalendar Library from
> > http://code.google.com/p/persian-calendar/downloads/list



> 2) Add library to classPath


> 3) Import needed packages
```
      import com.sahandrc.calendar.*;
```

# Example codes #

```
   //construct new  PersianCalendar object
   PersianCalendar persianCal = PersianCalendar();
   
   //Assign Persian Date
   persianCal.setPersianDate(1391, 01, 01);
   //get Gregorian date
   System.out.println(persianCal.getTime());
   //get Persian short and long Date string
   System.out.println(persianCal.getPersianShortDate());
   System.out.println(persianCal.getPersianLongDate());
   
   //Assing gregorian date
   PersianCal.set(1982, Calendar.MAY, 22);
   //get Persian Date string
   System.out.println(persianCal.getPersianShortDate());
   
   //add 33 months to current date
   persianCal.addPersianDate(Calendar.MONTH, 33);
   System.out.println(persianCal.getPersianShortDate());

   //add 5 years to current year
   persianCal.addPersianDate(Calendar.YEAR, 5);
   System.out.println(persianCal.getPersianShortDate());

   //add 50 days to current year
   persianCal.addPersianDate(Calendar.DATE, 50);
   System.out.println(persianCal.getPersianShortDate());

   //Parse String and get the date
   persianCal.parse("1391/03/01");
   System.out.println(persianCal.getPersianShortDate());

   //Alternatively you can get the PersianCalendar object by parsing string
   PersianCalendar p =  new PersianDateParser("1361/3/1").getPersianDate();
   System.out.println(p.getPersianShortDate());
   System.out.println(p.getTime());

   // Parse String using specific delimiter
   PersianCalendar p =  new PersianDateParser("1361-3-1","-").getPersianDate();
   System.out.println(p.getPersianShortDate());
   System.out.println(p.getTime());
```