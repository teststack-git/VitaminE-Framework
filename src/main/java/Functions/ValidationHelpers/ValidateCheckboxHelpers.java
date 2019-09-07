package Functions.ValidationHelpers;

import ExtentReport.ExtentReportBuilder;

import Core.Configuration;
import Core.FileReadWrite;

import Functions.UtilityHelpers.UtilityHelpers;
import org.junit.Assert;
import org.openqa.selenium.*;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * This class allows for the validation of checkboxes
 * Uses ObjectLibrary.xml for object references
 * Uses Datalibrary.xml for data references
 */


public class ValidateCheckboxHelpers extends ExtentReportBuilder {

    static String configTimeout = FileReadWrite.loadConfigurationProperties().getProperty("timeout");
    static int timeout = Integer.parseInt(configTimeout);

    // Validate Checkbox is enabled
    public static void byXpathCheckboxEnableValidation(WebDriver driver, String targetXpath) throws IOException {
        try {
        // Define the target element
        WebElement target = driver.findElement(By.xpath(targetXpath));

        System.out.println("Verifying the checkbox is enabled");
        // Validate checkbox status

        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
            if (target.isEnabled()==true) {
                assertThat(target.isEnabled()).isEqualTo(true);
                System.out.println("Test passed");
            } else if (target.isEnabled()==false) {
                System.out.println("Test Warn");
                assertThat(target.isEnabled()).isEqualTo(true);
            }

        } else {
            if (target.isEnabled()==true) {
                System.out.println("Test passed");
                localTest.get().pass("The following element is enabled: " + targetXpath);
            } else if (target.isEnabled()==false) {
                System.out.println("Test Warn");
                localTest.get().warning("The following element is NOT enabled: " + targetXpath);
                UtilityHelpers.adhocScreenCapture(driver, "Checkbox is not enabled.", "Warning");
            }
        }
    } catch (TimeoutException te) {
        te.printStackTrace();
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

    // Validate Checkbox is enabled
    public static void byIdCheckboxEnableValidation(WebDriver driver, String targetId) throws IOException {
        try {
        // Define the target element
        WebElement target = driver.findElement(By.id(targetId));

        System.out.println("Verifying the checkbox is enabled");
        // Validate checkbox status
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                if (target.isEnabled()==true) {
                    assertThat(target.isEnabled()).isEqualTo(true);
                    System.out.println("Test passed");
                } else if (target.isEnabled()==false) {
                    System.out.println("Test Warn");
                    assertThat(target.isEnabled()).isEqualTo(true);
                }
            } else {

                if (target.isEnabled()==true) {
                    System.out.println("Test passed");
                    localTest.get().pass("The following element is enabled: " + targetId);
                } else if (target.isEnabled()==false) {
                    System.out.println("Test Warn");
                    localTest.get().fail("The following element is NOT enabled: " + targetId);
                    UtilityHelpers.adhocScreenCapture(driver, "Checkbox is not enabled.", "Warning");
                }
            }
        } catch (TimeoutException te) {
            te.printStackTrace();
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

    // Validate Checkbox is unenabled
    public static void byIdCheckboxUnenableValidation(WebDriver driver, String checkboxId) throws IOException {
        try {
        // Define the target element
        WebElement target = driver.findElement(By.xpath(checkboxId));

        System.out.println("Verifying the checkbox is enabled");

        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
            if (target.isEnabled()==false) {
                assertThat(!target.isEnabled());
                System.out.println("Test passed");
            } else if (target.isEnabled()==true) {
                System.out.println("Test Warn");
                assertThat(!target.isEnabled());
            }
        } else {

            if (target.isEnabled()==false) {
                System.out.println("Test passed");
                localTest.get().pass("The following element is unenabled: " + checkboxId);
            } else if (target.isEnabled()==true) {
                System.out.println("Test Warn");
                localTest.get().fail("The following element is enabled: " + checkboxId);
                UtilityHelpers.adhocScreenCapture(driver, "Checkbox is enabled.", "Warning");
            }
        }
    } catch (TimeoutException te) {
        te.printStackTrace();
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + checkboxId);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + checkboxId);
                UtilityHelpers.adhocScreenCapture(driver, "Element not found.", "Failure");
                Assert.fail();
            }
        }
    }
}
