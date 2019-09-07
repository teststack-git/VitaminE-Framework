package Functions.ValidationHelpers;

import ExtentReport.ExtentReportBuilder;

import Core.Configuration;
import Core.FileReadWrite;

import Functions.UtilityHelpers.UtilityHelpers;
import com.aventstack.extentreports.Status;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * This class allows for the validation of time formats
 * Uses ObjectLibrary.xml for object references
 * Uses Datalibrary.xml for data references
 */


public class ValidateTimeFormatHelpers extends ExtentReportBuilder {

    static String configTimeout = FileReadWrite.loadConfigurationProperties().getProperty("timeout");
    static int timeout = Integer.parseInt(configTimeout);

    // Verify the date format (Date format examples: https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html)
    public static void byIdDateFormatValidation(WebDriver driver, String expectedDateFormat, String calendarTextFieldID) {
        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(By.id(calendarTextFieldID)));
        } catch (Exception e) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + calendarTextFieldID);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + calendarTextFieldID);
                Assert.fail();
            }
        }

        // Define the calendar text field
        WebElement calendarField = driver.findElement(By.id(calendarTextFieldID));

        // Store today's date
        Date today = new Date();

        // Format the date
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(expectedDateFormat);
        String date = DATE_FORMAT.format(today);
        System.out.println("Today in: " + expectedDateFormat + " " + date);

        // ClickHelpers within the calendar text field
        calendarField.click();

        // Store the date from the calendar text field
        String calendarFieldValue = calendarField.getAttribute("value");
        System.out.println("TimeDatesHelpers Field Value: " + calendarFieldValue);

        // Verify the date format and the calendar date are the same
        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
            try {
                if (date.equals(calendarFieldValue)) {
                    assertThat(date).isEqualTo(calendarFieldValue);
                } else {
                    assertThat(date).isEqualTo(calendarFieldValue);
                }
            } catch (Exception e) {
                e.printStackTrace();
                Assert.fail();
            }
        } else {
            try {
                if (date.equals(calendarFieldValue)) {
                    localTest.get().pass("Validate the date '" + calendarFieldValue + "' matches the expected format: " + expectedDateFormat);
                } else {
                    localTest.get().fail("Validate the date '" + calendarFieldValue + "' matches the expected format: " + expectedDateFormat);
                    UtilityHelpers.adhocScreenCapture(driver, "Date does not match expected format.", "Failure");
                    Assert.fail();
                }
            } catch (Exception e) {
                if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                    System.out.println("Could not find element: " + calendarTextFieldID);
                    Assert.fail();
                } else {
                    localTest.get().fail("Field not found: " + calendarTextFieldID);
                    UtilityHelpers.adhocScreenCapture(driver, "Field not found.", "Failure");
                    Assert.fail();
                }
            }
        }
    }

    // Verify the date format (Date format examples: https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html)
    public static void byXpathDateFormatValidation(WebDriver driver, String expectedDateFormat, String calendarTextFieldXpath){
        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(calendarTextFieldXpath)));
        } catch (Exception e) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + calendarTextFieldXpath);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + calendarTextFieldXpath);
                Assert.fail();
            }
        }
        // Define the calendar text field
        WebElement calendarField = driver.findElement(By.xpath(calendarTextFieldXpath));

        // Store today's date
        Date today = new Date();

        // Format the date
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(expectedDateFormat);
        String date = DATE_FORMAT.format(today);
        System.out.println("Today in: " + expectedDateFormat + " " + date);

        // ClickHelpers within the calendar text field
        calendarField.click();

        // Store the date from the calendar text field
        String calendarFieldValue = calendarField.getAttribute("value");
        System.out.println("TimeDatesHelpers Field Value: " + calendarFieldValue);

        // Verify the date format and the calendar date are the same
        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
            try {
                if (date.equals(calendarFieldValue)) {
                    assertThat(date).isEqualTo(calendarFieldValue);
                } else {
                    assertThat(date).isEqualTo(calendarFieldValue);
                }
            } catch (Exception e) {
                e.printStackTrace();
                Assert.fail();
            }
        } else {
            try {
                if (date.equals(calendarFieldValue)) {
                    localTest.get().pass("Validate the date '" + calendarFieldValue + "' matches the expected format: " + expectedDateFormat);
                } else {
                    localTest.get().fail("Validate the date '" + calendarFieldValue + "' matches the expected format: " + expectedDateFormat);
                    UtilityHelpers.adhocScreenCapture(driver, "Date does not match expected format.", "Failure");
                    Assert.fail();
                }
            } catch (Exception e) {
                if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                    System.out.println("Could not find element: " + calendarTextFieldXpath);
                    Assert.fail();
                } else {
                    localTest.get().fail("Field not found: " + calendarTextFieldXpath);
                    UtilityHelpers.adhocScreenCapture(driver, "Field not found.", "Failure");
                    Assert.fail();
                }
            }
        }
    }



    // Validates the calendar displays in military time
    public static void byIdValidateCalendarMilitaryTime(WebDriver driver, String tableClassName, String calendarId) {
        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(By.id(calendarId)));
        } catch (Exception e) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + calendarId);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + calendarId);
                Assert.fail();
            }
        }
        // Define the calendar
        WebElement calendar = driver.findElement(By.id(calendarId));

        // Find all items by tagName
        List<WebElement> calendarItems = calendar.findElements(By.className(tableClassName));

        // Store all items in the list

        for (int fieldItems = 0; fieldItems < calendarItems.size(); fieldItems++) {
            //Store the text field text
            String calendarItemResults = calendarItems.get(fieldItems).getText();

            // Removes the am/pm text from the numerical values and removes any spaces
            calendarItemResults = calendarItemResults.replaceAll("[^-?0-9]+", " ").trim();

            // Define the minimum and maximum times
            int min = 0;
            int max = 23;
            // Converts the times to strings
            Integer.toString(min);
            Integer.toString(max);

            // Splits the results into separate numbers
            for (String itemResults: calendarItemResults.split(" ")) {
                int results = Integer.parseInt(itemResults);
                System.out.println("The number: " + results + " is greater than or equal to: " + min + " and less than or equal to: " + max);
                // Asserts the results fall into the expected ranges
                assertThat(results).isGreaterThanOrEqualTo(min).isLessThanOrEqualTo(max);
            }
        }
        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
        } else {
            if (localTest.get().getStatus() == Status.PASS) {
                localTest.get().pass("Validate the element '" + calendarId + "' displays in military time.");

            } else {
                localTest.get().fail("Validate the element '" + calendarId + "' displays in military time.");
                UtilityHelpers.adhocScreenCapture(driver, "Element not found.", "Failure");
                Assert.fail();
            }
        }
    }


    // Show the time within the calendar
    public static void byIdValidateCalendarHoursMinutes(WebDriver driver, String tableClassName, String calendarId) {
        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(By.id(calendarId)));
        } catch (Exception e) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + calendarId);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + calendarId);
                Assert.fail();
            }
        }
        // Define the calendar
        WebElement calendar = driver.findElement(By.id(calendarId));

        // Find all items by tagName
        List<WebElement> calendarItems = calendar.findElements(By.className(tableClassName));

        // Store all items in the list

        for (int fieldItems = 0; fieldItems < calendarItems.size(); fieldItems++) {
            //Store the text field text
            String calendarItemResults = calendarItems.get(fieldItems).getText();

            // Removes the am/pm text from the numerical values and removes any spaces
            calendarItemResults = calendarItemResults.replaceAll("[^-?0-9]+", " ").trim();

            // Define the minimum and maximum times
            int min = 0;
            int max = 12;
            // Converts the times to strings
            Integer.toString(min);
            Integer.toString(max);

            // Splits the results into separate numbers
            for (String itemResults: calendarItemResults.split(" ")) {
                int results = Integer.parseInt(itemResults);
                System.out.println("The number: " + results + " is greater than or equal to: " + min + " and less than or equal to: " + max);
                // Asserts the results fall into the expected ranges
                assertThat(results).isGreaterThanOrEqualTo(min).isLessThanOrEqualTo(max);
            }

            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
            } else {
                if (localTest.get().getStatus() == Status.PASS) {
                    localTest.get().pass("Validate the element '" + calendarId + "' displays in military time.");
                } else {
                    localTest.get().fail("Validate the element '" + calendarId + "' displays in military time.");
                    UtilityHelpers.adhocScreenCapture(driver, "Hours and minutes do not match expected format.", "Failure");
                    Assert.fail();
                }
            }
        }

    }


    // Stores the current date and time
    // Unable to capture milliseconds properly between the opening of popup and the system clock
    // May fail due to popup and system clock time discrepancy
    public static void currentDateTimeValidation (WebDriver driver, String dateFormat, String targetXpath) {
        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(targetXpath)));
        } catch (Exception e) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + targetXpath);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + targetXpath);
                Assert.fail();
            }
        }
        // Store current time
        Date currentTime = new Date();
        // Define the time element
        WebElement timeElement = driver.findElement(By.xpath(targetXpath));

        // Store popup time
        String popupTime = timeElement.getText();

        // Format the date
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(dateFormat);
        String date = DATE_FORMAT.format(currentTime);
        System.out.println("Today in: " + date);
        System.out.println("Popup time: " + popupTime);

        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
            try {
                if (popupTime.contains(date)) {
                    assertThat(popupTime).contains(date);
                } else {
                    assertThat(popupTime).contains(date);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                if (popupTime.contains(date)) {
                    localTest.get().pass("Validate the current date and time for element: " + targetXpath);

                } else {
                    localTest.get().fail("Validate the current date and time for element: " + targetXpath);
                    UtilityHelpers.adhocScreenCapture(driver, "Time does not match expected format.", "Failure");
                    Assert.fail();
                }
            } catch (Exception e) {
                if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                    System.out.println("Field not found.");
                    Assert.fail();
                } else {
                    localTest.get().fail("Field not found.");
                    UtilityHelpers.adhocScreenCapture(driver, "Field not found.", "Failure");
                    Assert.fail();
                }
            }
        }
    }

}
