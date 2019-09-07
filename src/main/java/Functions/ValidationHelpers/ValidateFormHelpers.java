package Functions.ValidationHelpers;

import ExtentReport.ExtentReportBuilder;

import Core.Configuration;
import Core.FileReadWrite;

import Functions.UtilityHelpers.UtilityHelpers;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

import org.apache.commons.validator.routines.EmailValidator;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.junit.Assert;



/**
 * This helper class allows for the validation of the forms elements such as e-mail and phone number formatting validation
 * Uses ObjectLibrary.xml for object references
 * Uses Datalibrary.xml for data references
 */


    public class ValidateFormHelpers extends ExtentReportBuilder {

        static String configTimeout = FileReadWrite.loadConfigurationProperties().getProperty("timeout");
        static int timeout = Integer.parseInt(configTimeout);


    // Verify the email address in a valid format
    public static void byXpathEmailFormatValidation(WebDriver driver, String targetXpath) {
        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(targetXpath)));
        } catch (Exception e) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + targetXpath);
            } else {
                localTest.get().fail("Could not find element: " + targetXpath);
                UtilityHelpers.adhocScreenCapture(driver, "Could not find element: " + targetXpath, "Failure");
                Assert.fail();
            }
        }
        try {
            // Define the output text field
            WebElement target = driver.findElement(By.xpath(targetXpath));

            // Identifies the targets email string value
            String email = target.getAttribute("value");

            // Call apache commons email address validator class
            EmailValidator validator = EmailValidator.getInstance();

            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                if (validator.isValid(email)) {
                    Assert.assertTrue(validator.isValid(email));
                } else {
                    Assert.assertTrue(validator.isValid(email));
                }
            } else {
                if (validator.isValid(email)) {
                    localTest.get().pass("The following email address is properly formatted: " + email);
                } else {
                    localTest.get().fail("The following email address is NOT properly formatted: " + email);
                    UtilityHelpers.adhocScreenCapture(driver, "Email address is not properly formatted.", "Failure");
                    Assert.fail();
                }
            }
        } catch (TimeoutException te) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + targetXpath);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + targetXpath);
                UtilityHelpers.adhocScreenCapture(driver, "Element not found.", "Failure");
                Assert.fail();
            }
        }
    }

    public static void byIdEmailFormatValidation(WebDriver driver, String targetId) {
        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(By.id(targetId)));
        } catch (Exception e) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + targetId);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + targetId);
                UtilityHelpers.adhocScreenCapture(driver, "Could not find element: " + targetId, "Failure");
                Assert.fail();
            }
        }
        try {
            // Define the output text field
            WebElement target = driver.findElement(By.id(targetId));

            // Identifies the targets email string value
            String email = target.getAttribute("value");

            // Call apache commons email address validator class
            EmailValidator validator = EmailValidator.getInstance();
            // Assert whether or not the email is in a valid format
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                if (validator.isValid(email)) {
                    Assert.assertTrue(validator.isValid(email));
                } else {
                    Assert.assertTrue(validator.isValid(email));
                }
            } else {
                if (validator.isValid(email)) {
                    localTest.get().pass("The following email address is properly formatted: " + email);
                } else {
                    localTest.get().fail("The following email address is NOT properly formatted: " + email);
                    UtilityHelpers.adhocScreenCapture(driver, "Email address is not properly formatted.", "Failure");
                    Assert.fail();
                }

            }
        } catch (TimeoutException te) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + targetId);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + targetId);
                UtilityHelpers.adhocScreenCapture(driver, "Element not found.", "Failure");
                Assert.fail();
            }
        }

    }

    public static void byXpathPhoneNumberFormatValidation(WebDriver driver, String twoLetterCountryCode, String targetXpath) {
        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(targetXpath)));
        } catch (Exception e) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + targetXpath);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + targetXpath);
                UtilityHelpers.adhocScreenCapture(driver, "Could not find element: " + targetXpath, "Failure");
                Assert.fail();
            }
        }
        // Define the output text field
        WebElement target = driver.findElement(By.xpath(targetXpath));
        // Identifies the targets email string value
        String phoneNumber = target.getAttribute("value");
        // Define the Phone UtilityHelpers from Google
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        try {
            // Parse the phone number along with the country code
            Phonenumber.PhoneNumber swissNumberProto = phoneUtil.parse(phoneNumber, twoLetterCountryCode);
            //System.out.println("This is a phone number: " + swissNumberProto);
            // Validate the phone number is in a proper format based on country code
            boolean isValid = phoneUtil.isValidNumber(swissNumberProto);
            //System.out.println("Boolean: " + isValid);
            // Assert the format is valid

            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                if (isValid == true) {
                    Assert.assertTrue(isValid);
                    System.out.println("The following phone number is properly formatted: " + phoneNumber);
                } else {
                    Assert.assertTrue(isValid);
                    System.out.println("The following phone number is NOT properly formatted: " + phoneNumber);
                }
            } else {
                if (isValid == true) {
                    localTest.get().pass("The following phone number is properly formatted: " + phoneNumber);
                } else {
                    localTest.get().fail("The following phone number is NOT properly formatted: " + phoneNumber);
                    UtilityHelpers.adhocScreenCapture(driver, "Phone number is not formatted", "Failure");
                    Assert.fail();
                }
            }

        } catch (NumberParseException e) {
            System.out.println("NumberParseException was thrown: " + e.toString());
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + targetXpath);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + targetXpath);
                UtilityHelpers.adhocScreenCapture(driver, "Element not found.", "Failure");
                Assert.fail();
            }
        }
    }


    public static void byIdPhoneNumberFormatValidation(WebDriver driver, String twoLetterCountryCode, String targetId) {
        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(By.id(targetId)));
        } catch (Exception e) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + targetId);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + targetId);
                UtilityHelpers.adhocScreenCapture(driver, "Could not find element: " + targetId, "Failure");
                Assert.fail();
            }
            }
        // Define the output text field
        WebElement target = driver.findElement(By.id(targetId));
        // Identifies the targets email string value
        String phoneNumber = target.getAttribute("value");
        // Define the Phone UtilityHelpers from Google
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        try {
            // Parse the phone number along with the country code
            Phonenumber.PhoneNumber swissNumberProto = phoneUtil.parse(phoneNumber, twoLetterCountryCode);
            //System.out.println("This is a phone number: " + swissNumberProto);
            // Validate the phone number is in a proper format based on country code
            boolean isValid = phoneUtil.isValidNumber(swissNumberProto);
            //System.out.println("Boolean: " + isValid);
            // Assert the format is valid

            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                if (isValid == true) {
                    Assert.assertTrue(isValid);
                    System.out.println("The following phone number is properly formatted: " + phoneNumber);
                } else {
                    Assert.assertTrue(isValid);
                    System.out.println("The following phone number is NOT properly formatted: " + phoneNumber);
                }
            } else {
                if (isValid == true) {
                    localTest.get().pass("The following phone number is properly formatted: " + phoneNumber);
                } else {
                    localTest.get().fail("The following phone number is NOT properly formatted: " + phoneNumber);
                    UtilityHelpers.adhocScreenCapture(driver, "Phone number is not formatted", "Failure");
                    Assert.fail();
                }
            }

        } catch (NumberParseException e) {
            System.out.println("NumberParseException was thrown: " + e.toString());
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + targetId);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + targetId);
                UtilityHelpers.adhocScreenCapture(driver, "Element not found.", "Failure");
                Assert.fail();
            }
        }
    }


}
