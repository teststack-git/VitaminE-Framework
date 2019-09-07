package Functions.ClickHelpers;

import ExtentReport.ExtentReportBuilder;
import Core.Configuration;
import Core.FileReadWrite;

import Functions.UtilityHelpers.UtilityHelpers;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;


/**
 * This class allows for ClickHelpers events to interact with elements such as buttons
 * Uses ObjectLibrary.xml for object references
 * Uses Datalibrary.xml for data references
 */


public class ClickObjectHelpers extends ExtentReportBuilder {

    static String configTimeout = FileReadWrite.loadConfigurationProperties().getProperty("timeout");
    static int timeout = Integer.parseInt(configTimeout);


    // ClickHelpers on an object using specified xpath
    public static void byXpathClick(WebDriver driver, String targetXpath) {
        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(By.xpath(targetXpath)));
        } catch (NotFoundException nfe) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + targetXpath);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + targetXpath);
                UtilityHelpers.adhocScreenCapture(driver, "Could not find element: " + targetXpath, "Failure");
                Assert.fail();
            }
        } catch (ElementNotInteractableException enie) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + targetXpath);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + targetXpath);
                UtilityHelpers.adhocScreenCapture(driver, "Could not find element: " + targetXpath, "Failure");
                Assert.fail();
            }
        }

        try {
            System.out.println("Going to Click: " + targetXpath);
            // Targets object specified within ObjectLibrary by xpath
            WebElement clickObject = driver.findElement(By.xpath(targetXpath));
            // ClickHelpers on the object
            clickObject.click();
        } catch (ElementNotInteractableException enie) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not click on element: " + targetXpath);
                Assert.fail();
            } else {
                localTest.get().fail("Could not click on element: " + targetXpath);
                UtilityHelpers.adhocScreenCapture(driver, "Could not find element: " + targetXpath, "Failure");
                Assert.fail();
            }
        }

        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
            System.out.println("Clicking on the element: " + targetXpath);
        } else {
            localTest.get().info("Clicking on the element: " + targetXpath);
        }

    }

    // ClickHelpers on an object using specified ID
    public static void byIdClick(WebDriver driver, String targetId) {

        // Added to prevent racing condition failure
        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(By.id(targetId)));
        } catch (NotFoundException nfe) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + targetId);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + targetId);
                UtilityHelpers.adhocScreenCapture(driver, "Could not find element: " + targetId, "Failure");
                Assert.fail();
            }
        } catch (ElementNotInteractableException enie) {
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
        System.out.println("Going to Click: " + targetId);
        // Targets object specified within ObjectLibrary by ID
        WebElement clickObject = driver.findElement(By.id(targetId));
        // ClickHelpers on the object
        clickObject.click();
        } catch (ElementNotInteractableException enie) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not click on element: " + targetId);
                Assert.fail();
            } else {
                localTest.get().fail("Could not click on element: " + targetId);
                UtilityHelpers.adhocScreenCapture(driver, "Could not find element: " + targetId, "Failure");
                Assert.fail();
            }
        }
        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
            System.out.println("Clicking on the element: " + targetId);
        } else {
            localTest.get().info("Clicking on the element: " + targetId);
        }

    }

    // ClickHelpers on an object using specified name
    public static void byNameClick(WebDriver driver, String targetName) {
        // Added to prevent racing condition failure
        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(By.name(targetName)));
        } catch (NotFoundException nfe) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + targetName);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + targetName);
                UtilityHelpers.adhocScreenCapture(driver, "Could not find element: " + targetName, "Failure");
                Assert.fail();
            }
        } catch (ElementNotInteractableException enie) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + targetName);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + targetName);
                UtilityHelpers.adhocScreenCapture(driver, "Could not find element: " + targetName, "Failure");
                Assert.fail();
            }
        }
        try {
        System.out.println("Going to Click: " + targetName);
        // Targets object specified within ObjectLibrary by name
        WebElement clickObject = driver.findElement(By.name(targetName));
        // ClickHelpers on the object
        clickObject.click();
        } catch (ElementNotInteractableException enie) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not click on element: " + targetName);
                Assert.fail();
            } else {
                localTest.get().fail("Could not click on element: " + targetName);
                UtilityHelpers.adhocScreenCapture(driver, "Could not find element: " + targetName, "Failure");
                Assert.fail();
            }
        }
        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
            System.out.println("Clicking on the element: " + targetName);
        } else {
            localTest.get().info("Clicking on the element: " + targetName);
        }

    }

    // ClickHelpers on a link based on its text value
    public static void byTagNameClick(WebDriver driver, String targetTagName) {

        // Added to prevent racing condition failure
        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(By.tagName(targetTagName)));

        } catch (NotFoundException nfe) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + targetTagName);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + targetTagName);
                UtilityHelpers.adhocScreenCapture(driver, "Could not find element: " + targetTagName, "Failure");
                Assert.fail();
            }
        } catch (ElementNotInteractableException enie) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + targetTagName);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + targetTagName);
                UtilityHelpers.adhocScreenCapture(driver, "Could not find element: " + targetTagName, "Failure");
                Assert.fail();
            }
        }
        try{
        System.out.println("Going to Click: " + targetTagName);
        // Targets object specified within ObjectLibrary by tagname
        WebElement clickObject = driver.findElement(By.tagName(targetTagName));
        // ClickHelpers on the object
        clickObject.click();
        } catch (ElementNotInteractableException enie) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not click on element: " + targetTagName);
                Assert.fail();
            } else {
                localTest.get().fail("Could not click on element: " + targetTagName);
                UtilityHelpers.adhocScreenCapture(driver, "Could not find element: " + targetTagName, "Failure");
                Assert.fail();
            }
        }
        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
            System.out.println("Clicking on the element: " + targetTagName);
        } else {
            localTest.get().info("Clicking on the element: " + targetTagName);
        }
    }

    // ClickHelpers on an element based on a css selector
    public static void byCssSelectorClick(WebDriver driver, String targetCssSelection) {
        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(By.cssSelector(targetCssSelection)));
        } catch (NotFoundException nfe) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + targetCssSelection);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + targetCssSelection);
                UtilityHelpers.adhocScreenCapture(driver, "Could not find element: " + targetCssSelection, "Failure");
                Assert.fail();
            }
        } catch (ElementNotInteractableException enie) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + targetCssSelection);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + targetCssSelection);
                UtilityHelpers.adhocScreenCapture(driver, "Could not find element: " + targetCssSelection, "Failure");
                Assert.fail();
            }
        }
        try{
        System.out.println("Going to Click: " + targetCssSelection);
        // Targets object specified within ObjectLibrary by css selector
        WebElement clickObject = driver.findElement(By.cssSelector(targetCssSelection));
        // ClickHelpers on the object
        clickObject.click();
        } catch (ElementNotInteractableException enie) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not click on element: " + targetCssSelection);
                Assert.fail();
            } else {
                localTest.get().fail("Could not click on element: " + targetCssSelection);
                UtilityHelpers.adhocScreenCapture(driver, "Could not find element: " + targetCssSelection, "Failure");
                Assert.fail();
            }
        }
        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
            System.out.println("Clicking on the element: " + targetCssSelection);

        } else {
            localTest.get().info("Clicking on the element: " + targetCssSelection);
        }
    }

    // ClickHelpers on an element based on a css selector
    public static void byClassNameClick(WebDriver driver, String targetClassName) {
        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(By.className(targetClassName)));
        } catch (NotFoundException nfe) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + targetClassName);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + targetClassName);
                UtilityHelpers.adhocScreenCapture(driver, "Could not find element: " + targetClassName, "Failure");
                Assert.fail();
            }
        } catch (ElementNotInteractableException enie) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + targetClassName);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + targetClassName);
                UtilityHelpers.adhocScreenCapture(driver, "Could not find element: " + targetClassName, "Failure");
                Assert.fail();
            }
        }
        try{
        System.out.println("Going to Click: " + targetClassName);
        // Targets object specified within ObjectLibrary by class name
        WebElement clickObject = driver.findElement(By.className(targetClassName));
        // ClickHelpers on the object
        clickObject.click();
        } catch (ElementNotInteractableException enie) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not click on element: " + targetClassName);
                Assert.fail();
            } else {
                localTest.get().fail("Could not click on element: " + targetClassName);
                UtilityHelpers.adhocScreenCapture(driver, "Could not find element: " + targetClassName, "Failure");
                Assert.fail();
            }
        }
        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
            System.out.println("Clicking on the element: " + targetClassName);
        } else {
            localTest.get().info("Clicking on the element: " + targetClassName);
        }
    }


    // Repeat click on object
    public static void byClassNameRepeatClick(WebDriver driver, String targetClassName) {

        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(By.className(targetClassName)));
        } catch (NotFoundException nfe) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + targetClassName);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + targetClassName);
                UtilityHelpers.adhocScreenCapture(driver, "Could not find element: " + targetClassName, "Failure");
                Assert.fail();
            }
        } catch (ElementNotInteractableException enie) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + targetClassName);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + targetClassName);
                UtilityHelpers.adhocScreenCapture(driver, "Could not find element: " + targetClassName, "Failure");
                Assert.fail();
            }
        }

        // Get object list
        List<WebElement> objectList = driver.findElements(By.className(targetClassName));
        // Get number of list items
        int numberOfObjects = objectList.size();
        // Set initial count
        int listCount = 1;
        // Set the first element to click. Starts at '0'
        int clickCount = 0;

        // Click element and increase count by one
        while (listCount <= numberOfObjects) {
            // Click first element
            objectList.get(clickCount).click();
            // Increase click counter by one
            clickCount++;
            // Increase list count by one
            listCount++;
        }

        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
            System.out.println("Clicking on the element: " + targetClassName);
        } else {
            localTest.get().info("Clicking on the element: " + targetClassName);
        }
    }

}