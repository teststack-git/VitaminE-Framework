package Functions.WaitHelpers;

import ExtentReport.ExtentReportBuilder;

import Core.Configuration;
import Core.FileReadWrite;

import Functions.UtilityHelpers.UtilityHelpers;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



/**
 * This class provides wait times based on the applications objects
 * Uses ObjectLibrary.xml for object references
 * Uses Datalibrary.xml for data references
 */


public class WaitObjectHelpers extends ExtentReportBuilder {

    static String configTimeout = FileReadWrite.loadConfigurationProperties().getProperty("timeout");
    static int timeout = Integer.parseInt(configTimeout);


    public static void byIdWaitForElementToUnload (WebDriver driver, String targetId) {
        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.not(ExpectedConditions.visibilityOfElementLocated(By.id(targetId))));
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
    }

    public static void byXpathWaitForElementToUnload (WebDriver driver, String targetXpath) {

        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.not(ExpectedConditions.visibilityOfElementLocated(By.xpath(targetXpath))));
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
    }

    public static void byIdWaitForElementToLoad (WebDriver driver, String targetId) {

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
    }

    public static void byXpathWaitForElementToLoad (WebDriver driver, String targetXpath) {

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
    }

    public static void byTagnameWaitForElementToLoad (WebDriver driver, String targetTagname) {

        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(By.tagName(targetTagname)));
        } catch (Exception e) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + targetTagname);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + targetTagname);
                UtilityHelpers.adhocScreenCapture(driver, "Could not find element: " + targetTagname, "Failure");
                Assert.fail();
            }
        }
    }

    public static void byTagnameWaitForElementToUnLoad (WebDriver driver, String targetTagname) {

        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.invisibilityOfElementLocated(By.tagName(targetTagname)));
        } catch (Exception e) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + targetTagname);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + targetTagname);
                UtilityHelpers.adhocScreenCapture(driver, "Could not find element: " + targetTagname, "Failure");
                Assert.fail();
            }
        }
    }
}
