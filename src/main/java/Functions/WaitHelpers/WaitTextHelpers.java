package Functions.WaitHelpers;

import ExtentReport.ExtentReportBuilder;

import Core.Configuration;
import Core.FileReadWrite;

import Functions.UtilityHelpers.UtilityHelpers;
import com.aventstack.extentreports.Status;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.regex.Pattern;


/**
 * This class provides wait times based on the applications text
 * Uses ObjectLibrary.xml for object references
 * Uses Datalibrary.xml for data references
 */


public class WaitTextHelpers extends ExtentReportBuilder {

    static String configTimeout = FileReadWrite.loadConfigurationProperties().getProperty("timeout");
    static int timeout = Integer.parseInt(configTimeout);

    public static void waitForTextToLoad (WebDriver driver, String textValue) {

        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), '" + textValue +"')]")));
        } catch (Exception e) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find text: " + textValue);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find text: " + textValue);
                UtilityHelpers.adhocScreenCapture(driver, "Could not find text: " + textValue, "Failure");
                Assert.fail();
            }
        }
    }

    public static void byXpathWaitForTextToUnload (WebDriver driver, String textValue, String targetXpath) {

        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.invisibilityOfElementWithText(By.xpath(targetXpath), textValue));
        } catch (Exception e) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find text: " + textValue);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find text: " + textValue);
                UtilityHelpers.adhocScreenCapture(driver, "Could not find text: " + textValue, "Failure");
                Assert.fail();
            }
        }
    }

    public static void byIdWaitForTextToUnload (WebDriver driver, String textValue, String targetId) {

        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.invisibilityOfElementWithText(By.id(targetId), textValue));
        } catch (Exception e) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find text: " + textValue);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find text: " + textValue);
                UtilityHelpers.adhocScreenCapture(driver, "Could not find text: " + textValue, "Failure");
                Assert.fail();
            }
        }
        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
        System.out.println("Waiting for the following text to unload: " + targetId);
        } else {
            if (localTest.get().getStatus() == Status.PASS) {
                localTest.get().pass("Waiting for the following text to unload: " + targetId);
            } else {
                localTest.get().fail("Waiting for the following text to unload: " + targetId);
                Assert.fail();
            }
        }
    }

    public static void byIdWaitForTextToLoad (WebDriver driver, String textValue, String targetId) {

        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.textMatches(By.id(targetId), Pattern.compile(textValue)));
        } catch (Exception e) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find text: " + textValue);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find text: " + textValue);
                UtilityHelpers.adhocScreenCapture(driver, "Could not find text: " + textValue, "Failure");
                Assert.fail();
            }
        }
    }

    public static void byXpathWaitForTextToLoad (WebDriver driver, String textValue, String targetXpath) {

        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.textMatches(By.xpath(targetXpath), Pattern.compile(textValue)));
        } catch (Exception e) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find text: " + textValue);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find text: " + textValue);
                UtilityHelpers.adhocScreenCapture(driver, "Could not find text: " + textValue, "Failure");
                Assert.fail();
            }
        }
    }
}
