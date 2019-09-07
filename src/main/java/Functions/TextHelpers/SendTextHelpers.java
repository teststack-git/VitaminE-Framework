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
 * This class provides the ability to send text to elements
 * Uses ObjectLibrary.xml for object references
 * Uses Datalibrary.xml for data references
 */


public class SendTextHelpers extends ExtentReportBuilder {

    static String configTimeout = FileReadWrite.loadConfigurationProperties().getProperty("timeout");
    static int timeout = Integer.parseInt(configTimeout);

    // Sends text to an object using elements xpath
    public static void byXpathSend(WebDriver driver, String sentText, String targetXpath) {
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
        System.out.println("Going to TextHelpers: " + sentText + " to " + targetXpath);
        // Targets object specified with ObjectLibrary by xpath
        WebElement sendObject = driver.findElement(By.xpath(targetXpath));
        // Sends text to the object
        sendObject.clear();
        sendObject.sendKeys(sentText);

        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
            System.out.println("Sending the value '" + sentText + "' to " + targetXpath);
        } else {
            localTest.get().info("Sending the value '" + sentText + "' to " + targetXpath);
        }

    }

    // Sends text to an object using elements ID
    public static void byIdSend(WebDriver driver, String sentText, String targetId) {
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
        System.out.println("Going to TextHelpers: " + sentText + " to " + targetId);
        // Targets object specified with ObjectLibrary by ID
        WebElement sendObject = driver.findElement(By.id(targetId));
        sendObject.clear();
        // Sends text to the object
        sendObject.sendKeys(sentText);

        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
            System.out.println("Sending the value '" + sentText + "' to " + targetId);
        } else {
            localTest.get().info("Sending the value '" + sentText + "' to " + targetId);
        }

    }


    // Sends text to an object using elements name
    public static void byNameSend(WebDriver driver, String sentText, String targetName) {
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
        System.out.println("Going to TextHelpers: " + sentText + " to " + targetName);
        // Targets object specified with ObjectLibrary by name
        WebElement sendObject = driver.findElement(By.name(targetName));
        // Sends text to the object
        sendObject.clear();
        sendObject.sendKeys(sentText);

        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
            System.out.println("Sending the value '" + sentText + "' to " + targetName);
        } else {
            localTest.get().info("Sending the value '" + sentText + "' to " + targetName);
        }

    }

    // Sends text to an object using specified name
    public static void byXpathExpressionTextAreaSend(WebDriver driver, String content, String twID, String inputID) {
        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tw-multi-auto-complete[@id='"+twID+"']/descendant::textarea[@id='"+inputID+"']")));
        } catch (Exception e) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + inputID);
            } else {
                localTest.get().fail("Could not find element: " + inputID);
                UtilityHelpers.adhocScreenCapture(driver, "Could not find element: " + inputID, "Failure");
            }
        }
        System.out.println("Going to TextHelpers: " + content + " to " + twID);
        // Targets object specified with ObjectLibrary by name
        WebElement sendObject = driver.findElement(By.xpath("//tw-multi-auto-complete[@id='"+twID+"']/descendant::textarea[@id='"+inputID+"']"));
        // Sends text to the object
        sendObject.clear();
        sendObject.sendKeys(content);

        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
            System.out.println("Sending the value '" + content + "' to " + inputID);
        } else {
            localTest.get().fail("Sending the value '" + content + "' to " + inputID);
        }
    }




}
