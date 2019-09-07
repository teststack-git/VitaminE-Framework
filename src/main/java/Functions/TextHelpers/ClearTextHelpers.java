package Functions.TextHelpers;

import ExtentReport.ExtentReportBuilder;

import Core.Configuration;
import Core.FileReadWrite;

import Functions.UtilityHelpers.UtilityHelpers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * This class allows for the clearing of text fields
 * Uses ObjectLibrary.xml for object references
 * Uses Datalibrary.xml for data references
 */


public class ClearTextHelpers extends ExtentReportBuilder {

    static String configTimeout = FileReadWrite.loadConfigurationProperties().getProperty("timeout");
    static int timeout = Integer.parseInt(configTimeout);

    // Clears text from an object using specified xpath
    public static void byXpathClear(WebDriver driver, String targetXpath) {
        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(targetXpath)));
        } catch (Exception e) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + targetXpath);
            } else {
                localTest.get().fail("Could not find element: " + targetXpath);
                UtilityHelpers.adhocScreenCapture(driver, "Could not find element: " + targetXpath, "Failure");
            }
        }
        System.out.println("Going to Clear: " + targetXpath);
        // Targets object specified with ObjectLibrary by xpath
        WebElement clearObject = driver.findElement(By.xpath(targetXpath));
        // Clear text from the object
        clearObject.clear();

        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
            System.out.println("Clearing the text field: " + targetXpath);
        } else {
            localTest.get().info("Clearing the text field: " + targetXpath);
        }
    }

    // Clears text from an object using specified ID
    public static void byIdClear(WebDriver driver, String targetId) {
        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(By.id(targetId)));
        } catch (Exception e) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + targetId);
            } else {
                localTest.get().fail("Could not find element: " + targetId);
                UtilityHelpers.adhocScreenCapture(driver, "Could not find element: " + targetId, "Failure");
            }
        }
        System.out.println("Going to Clear: " + targetId);
        // Targets object specified with ObjectLibrary by ID
        WebElement clearObject = driver.findElement(By.id(targetId));
        // Clear text from the object
        clearObject.clear();

        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
            System.out.println("Clearing the text field: " + targetId);
        } else {
            localTest.get().info("Clearing the text field: " + targetId);
        }
    }


    // Clears text from an object using specified name
    public static void byNameClear(WebDriver driver, String targetName) {
        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(By.name(targetName)));
        } catch (Exception e) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + targetName);
            } else {
                localTest.get().fail("Could not find element: " + targetName);
                UtilityHelpers.adhocScreenCapture(driver, "Could not find element: " + targetName, "Failure");
            }
        }
        System.out.println("Going to Clear: " + targetName);
        // Targets object specified with ObjectLibrary by name
        WebElement clearObject = driver.findElement(By.name(targetName));
        // Clear text from the object
        clearObject.clear();

        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
            System.out.println("Clearing the text field: " + targetName);
        } else {
            localTest.get().info("Clearing the text field: " + targetName);
        }
    }

}
