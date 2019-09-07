package Functions.TimeDatesHelpers;

import ExtentReport.ExtentReportBuilder;

import Core.FileReadWrite;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * This class provides helpers to add and subtract dates and times
 * Uses ObjectLibrary.xml for object references
 * Uses Datalibrary.xml for data references
 *
 * NOTE:  Objects may differ between environments.  A general object library cannot be used due to the jar file structure.  Because of this, hardcoded locators are required.
 */


public class TimeDateHelpers extends ExtentReportBuilder {

    static String configTimeout = FileReadWrite.loadConfigurationProperties().getProperty("timeout");
    static int timeout = Integer.parseInt(configTimeout);

    // Return the current date and time based on format
    public static String displayCurrentDateTime (String dateFormat) {

        // Store current time
        Date currentTime = new Date();
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(dateFormat);
        String date = DATE_FORMAT.format(currentTime);
        return date;
    }

    // Add seconds to the current time
    public static String addSecondsToCurrentDateTime (String dateFormat, int additionalTime) {

        Date currentDate = new Date();

        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(currentDate);

        calendar.add(java.util.Calendar.SECOND, additionalTime);

        Date currentDatePlusAdditional = calendar.getTime();

        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(dateFormat);

        String date = DATE_FORMAT.format(currentDatePlusAdditional);

        return date;
    }

    // Add minutes to the current time
    public static String addMinutesToCurrentDateTime (String dateFormat, int additionalTime) {

        Date currentDate = new Date();

        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(currentDate);

        calendar.add(java.util.Calendar.MINUTE, additionalTime);

        Date currentDatePlusAdditional = calendar.getTime();

        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(dateFormat);

        String date = DATE_FORMAT.format(currentDatePlusAdditional);

        return date;
    }

    // Add hours to the current time
    public static String addHoursToCurrentDateTime (String dateFormat, int additionalTime) {

        Date currentDate = new Date();

        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(currentDate);

        calendar.add(java.util.Calendar.HOUR, additionalTime);

        Date currentDatePlusAdditional = calendar.getTime();

        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(dateFormat);

        String date = DATE_FORMAT.format(currentDatePlusAdditional);

        return date;
    }

    // Add days to the current time
    public static String addDaysToCurrentDateTime (String dateFormat, int additionalTime) {

        Date currentDate = new Date();

        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(currentDate);

        calendar.add(java.util.Calendar.DATE, additionalTime);

        Date currentDatePlusAdditional = calendar.getTime();

        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(dateFormat);

        String date = DATE_FORMAT.format(currentDatePlusAdditional);

        return date;
    }

    // Add months to the current time
    public static String addMonthsToCurrentDateTime (String dateFormat, int additionalTime) {

        Date currentDate = new Date();

        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(currentDate);

        calendar.add(java.util.Calendar.MONTH, additionalTime);

        Date currentDatePlusAdditional = calendar.getTime();

        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(dateFormat);

        String date = DATE_FORMAT.format(currentDatePlusAdditional);

        return date;
    }

    // Add years to the current time
    public static String addYearsToCurrentDateTime (String dateFormat, int additionalTime) {

        Date currentDate = new Date();

        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(currentDate);

        calendar.add(java.util.Calendar.YEAR, additionalTime);

        Date currentDatePlusAdditional = calendar.getTime();

        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(dateFormat);

        String date = DATE_FORMAT.format(currentDatePlusAdditional);

        return date;
    }
}
