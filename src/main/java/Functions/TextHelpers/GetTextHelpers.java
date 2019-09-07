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

import java.util.List;


/**
 * This class gets text and values from fields and objects
 * Uses ObjectLibrary.xml for object references
 * Uses Datalibrary.xml for data references
 */


public class GetTextHelpers extends ExtentReportBuilder {
    static String configTimeout = FileReadWrite.loadConfigurationProperties().getProperty("timeout");
    static int timeout = Integer.parseInt(configTimeout);

    public static String byXpathGetText(WebDriver driver, String targetXpath) {
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
        WebElement target = driver.findElement(By.xpath(targetXpath));
        String targetValue = target.getText();

        return targetValue;
    }

    public static String byIdGetText(WebDriver driver, String targetId) {
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
        WebElement target = driver.findElement(By.id(targetId));
        String targetValue = target.getText();

        return targetValue;
    }

    public static String byXpathGetTextValue(WebDriver driver, String targetXpath) {
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
        WebElement target = driver.findElement(By.xpath(targetXpath));
        String targetValue = target.getAttribute("value");
        return targetValue;
    }

    public static String byIdGetTextValue(WebDriver driver, String targetId) {
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
        WebElement target = driver.findElement(By.id(targetId));
        String targetValue = target.getAttribute("value");
        return targetValue;
    }

    // Extracts data from specified table and validates specified user data
    public static String byIdGridDataExtract(WebDriver driver, String gridElementId) {
        // WaitHelpers for data grid to display
        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(By.id(gridElementId)));
        } catch (Exception e) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + gridElementId);
            } else {
                localTest.get().fail("Could not find element: " + gridElementId);
                UtilityHelpers.adhocScreenCapture(driver, "Could not find element: " + gridElementId, "Failure");
            }
        }
        // Finds grid to extract data
        WebElement mytable = driver.findElement(By.id(gridElementId));
        //To locate rows of table.
        List<WebElement> rows_table = mytable.findElements(By.tagName("span"));

        //Loop will execute till the last row of table.
        String allItemResults = "";
        for (int fieldItems = 0; fieldItems < rows_table.size(); fieldItems++) {
            //Store the results
            String tempItemResults = allItemResults + '\n';
            allItemResults = tempItemResults + (rows_table.get(fieldItems).getAttribute("textContent"));
        }
        // Get all text within the table
        System.out.println("Results" + allItemResults);
        // Make text public so it can be compared against
        return allItemResults;

    }

    // Extracts data from specified table and validates specified user data
    public static String byXpathGridDataExtract(WebDriver driver, String gridElementXpath) {

        // WaitHelpers for data grid to display
        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(gridElementXpath)));
        } catch (Exception e) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + gridElementXpath);
            } else {
                localTest.get().fail("Could not find element: " + gridElementXpath);
                UtilityHelpers.adhocScreenCapture(driver, "Could not find element: " + gridElementXpath, "Failure");
            }
        }
        // Finds grid to extract data
        WebElement mytable = driver.findElement(By.xpath(gridElementXpath));
        //To locate rows of table.
        List<WebElement> rows_table = mytable.findElements(By.tagName("span"));

        //Loop will execute till the last row of table.
        String allItemResults = "";
        for (int fieldItems = 0; fieldItems < rows_table.size(); fieldItems++) {
            //Store the results
            String tempItemResults = allItemResults + '\n';
            allItemResults = tempItemResults + (rows_table.get(fieldItems).getAttribute("textContent"));
        }
        // Get all text within the table
        System.out.println("Results" + allItemResults);
        // Make text public so it can be compared against
        return allItemResults;

    }

    // Extracts data from specified table and validates specified user data
    public static String byClassNameGridDataExtract(WebDriver driver, String gridElementClassName) {
        // WaitHelpers for data grid to display
        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(By.className(gridElementClassName)));
        } catch (Exception e) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + gridElementClassName);
            } else {
                localTest.get().fail("Could not find element: " + gridElementClassName);
                UtilityHelpers.adhocScreenCapture(driver, "Could not find element: " + gridElementClassName, "Failure");
            }
        }
        // Finds grid to extract data
        WebElement mytable = driver.findElement(By.className(gridElementClassName));
        //To locate rows of table.
        List<WebElement> rows_table = mytable.findElements(By.tagName("span"));

        //Loop will execute till the last row of table.
        String allItemResults = "";
        for (int fieldItems = 0; fieldItems < rows_table.size(); fieldItems++) {
            //Store the results
            String tempItemResults = allItemResults + '\n';
            allItemResults = tempItemResults + (rows_table.get(fieldItems).getAttribute("textContent"));
        }
        // Get all text within the table
        System.out.println("Results" + allItemResults);
        // Make text public so it can be compared against
        return allItemResults;

    }

    public static String byXpathDataExtract(WebDriver driver, String targetXpath) {

        // WaitHelpers for data grid to display
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
        // Get data
        WebElement target = driver.findElement(By.xpath(targetXpath));
        String itemResults = target.getText();

        // Get text
        System.out.println("Value is: '" + itemResults + "'.");

        return itemResults;

    }

    public static String byCssSelectorDataExtract(WebDriver driver, String targetCssSelector) {

        // WaitHelpers for data grid to display
        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(targetCssSelector)));
        } catch (Exception e) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + targetCssSelector);
            } else {
                localTest.get().fail("Could not find element: " + targetCssSelector);
                UtilityHelpers.adhocScreenCapture(driver, "Could not find element: " + targetCssSelector, "Failure");
            }
        }
        // Get data
        WebElement target = driver.findElement(By.cssSelector(targetCssSelector));
        String itemResults = target.getText();

        // Get text
        System.out.println("Value is: '" + itemResults + "'.");

        return itemResults;

    }

    public static String byClassNameDataExtract(WebDriver driver, String targetClassName) {

        // WaitHelpers for data grid to display
        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(By.className(targetClassName)));
        } catch (Exception e) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + targetClassName);
            } else {
                localTest.get().fail("Could not find element: " + targetClassName);
                UtilityHelpers.adhocScreenCapture(driver, "Could not find element: " + targetClassName, "Failure");
            }
        }
        // Get data
        WebElement target = driver.findElement(By.className(targetClassName));
        String itemResults = target.getText();

        // Get text
        System.out.println("Value is: '" + itemResults + "'.");

        return itemResults;

    }

    public static String byLinkTextDataExtract(WebDriver driver, String targetLinkText) {

        // WaitHelpers for data grid to display
        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(By.linkText(targetLinkText)));
        } catch (Exception e) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + targetLinkText);
            } else {
                localTest.get().fail("Could not find element: " + targetLinkText);
                UtilityHelpers.adhocScreenCapture(driver, "Could not find element: " + targetLinkText, "Failure");
            }
        }
        // Get data
        WebElement target = driver.findElement(By.linkText(targetLinkText));
        String itemResults = target.getText();

        // Get text
        System.out.println("Value is: '" + itemResults + "'.");

        return itemResults;

    }

    public static String byIdDataExtract(WebDriver driver, String targetId) {

        // WaitHelpers for data grid to display
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
        // Get data
        WebElement target = driver.findElement(By.id(targetId));
        String itemResults = target.getText();

        // Get text
        System.out.println("Value is: '" + itemResults + "'.");

        return itemResults;

    }

}
