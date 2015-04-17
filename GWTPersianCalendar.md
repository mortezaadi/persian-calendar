Persian Calendar - GWT (Google Web Toolkit)

# About #

Here is some example of how to use PersianCalendar library in GWT.

# How to use the library #

  1. download latest PersianCalendar Library from
> > http://code.google.com/p/persian-calendar/downloads/list



> 2) Add library to classPath


> 3) Import needed packages
```
      import com.sahandrc.calendar.*;
```
> 4) inherit GWTPersianCalendar module
> > put the folowing definition in your gwt module (your-project.gwt.xml)

```
      <inherits name='com.sahandrc.calendar.GWTPersianCalendar' />
```


> see sample gwt project :
> http://code.google.com/p/persian-calendar/downloads/list

# Example codes #

```
     //construct new  GWTPersianCalendar object
        GWTPersianCalendar GWTPersianCal = new GWTPersianCalendar();
        
        //Assign Persian Date
        GWTPersianCal.setPersianDate(1391, 01, 01);

        //get Persian short and long Date string
        System.out.println(GWTPersianCal.getPersianShortDate());
        System.out.println(GWTPersianCal.getPersianLongDate());
        
        //Assing gregorian date
        GWTPersianCal = new GWTPersianCalendar(); // now
        //get Persian Date string
        System.out.println(GWTPersianCal.getPersianShortDate());
        
        //add 33 months to current date
        GWTPersianCal.addPersianDate(GWTPersianCalendar.MONTH, 33);
        System.out.println(GWTPersianCal.getPersianShortDate());

        //add 5 years to current year
        GWTPersianCal.addPersianDate(GWTPersianCalendar.YEAR, 5);
        System.out.println(GWTPersianCal.getPersianShortDate());

        //add 50 days to current year
        GWTPersianCal.addPersianDate(GWTPersianCalendar.DAY, 50);
        System.out.println(GWTPersianCal.getPersianShortDate());

        //Parse String and get the date
        GWTPersianCal.parsePersianDate("1391/03/01");
        System.out.println(GWTPersianCal.getPersianShortDate());

        //Alternatively you can get the GWTPersianCalendar object by parsing string
        GWTPersianCalendar p =  new GWTPersianDateParser("1361/3/1").getPersianDate();
        System.out.println(p.getPersianShortDate());
        System.out.println(p.getTime());

        // Parse String using specific delimiter
        GWTPersianCalendar p2 =  new GWTPersianDateParser("1361-3-1","-").getPersianDate();
        System.out.println(p2.getPersianShortDate());
        System.out.println(p2.getTime());
```

# Sample project #
sample gwt project is available in download page
http://code.google.com/p/persian-calendar/downloads/list