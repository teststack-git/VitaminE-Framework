package Functions.ValidationHelpers;

import ExtentReport.ExtentReportBuilder;

import Core.Configuration;
import Core.FileReadWrite;

import Functions.UtilityHelpers.UtilityHelpers;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.List;
import org.junit.Assert;

/**
 * This class allows for the validation of the DOM
 * Uses ObjectLibrary.xml for object references
 * Uses Datalibrary.xml for data references
 */

///////////// Document Object Model ActionHelpers ////////////////

// These functions allow of the validation of the applications DOM

///////////////////////////////////////////////////////////


public class ValidateDomHelpers extends ExtentReportBuilder {

    static String configTimeout = FileReadWrite.loadConfigurationProperties().getProperty("timeout");
    static int timeout = Integer.parseInt(configTimeout);

    // Validates the target element does not contain the specified CSS
    public static void byXpathElementDoesNotContainExpectedCss(WebDriver driver, String targetXpath, String cssValue) throws IOException {
        try {
        // Define the target CSS
        WebElement element = driver.findElement(By.xpath(targetXpath));

        // Find all items by tagName
        List<WebElement> allTargetItems = element.findElements(By.tagName("span"));

        // Store all items in the list
        String allItemResults = "";
        for (int fieldItems = 0; fieldItems < allTargetItems.size(); fieldItems++) {
            //Store the element CSS value
            String tempItemResults = allItemResults + '\n';
            allItemResults = tempItemResults + (allTargetItems.get(fieldItems).getAttribute("class"));

        }
        System.out.println("Highlighted text class: " + allItemResults);

            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                if (!allItemResults.contains(cssValue)) {
                    System.out.println("Test passed");
                    Assert.assertTrue(!allItemResults.contains(cssValue));
                } else if (allItemResults.contains(cssValue)) {
                    System.out.println("Test Warn");
                    Assert.assertTrue(!allItemResults.contains(cssValue));
                }
            } else {

                if (!allItemResults.contains(cssValue)) {
                    System.out.println("Test passed");
                    localTest.get().pass("The element '" + targetXpath + "' does not contain the css value '" + cssValue + "'");
                } else if (allItemResults.contains(cssValue)) {
                    System.out.println("Test Warn");
                    localTest.get().fail("The element '" + targetXpath + "' does contain the css value '" + cssValue + "'");
                    UtilityHelpers.adhocScreenCapture(driver, "Element contains the CSS value.", "Warning");
                    Assert.fail();
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

    // Validates the target element has expected CSS
    public static void byIdCssValidation(WebDriver driver, String newCssValue, String targetId) {
        try {
        // Define the target element
        WebElement target = driver.findElement(By.id(targetId));

        // Stores the targets CSS
        String targetCss = target.getAttribute("class");

        // Assert the target element contains the new CSS
        System.out.println("Target CSS value: " + targetCss);

            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                if (targetCss.contains(newCssValue)) {
                    System.out.println("Test passed");
                    Assert.assertTrue(targetCss.contains(newCssValue));
                } else if (!targetCss.contains(newCssValue)) {
                    System.out.println("Test Warn");
                    Assert.assertTrue(targetCss.contains(newCssValue));
                }
            } else {

                if (targetCss.contains(newCssValue)) {
                    System.out.println("Test passed");
                    localTest.get().pass("Verify the element '" + targetId + "' does contains the css value '" + newCssValue + "'");
                } else if (!targetCss.contains(newCssValue)) {
                    System.out.println("Test Warn");
                    localTest.get().fail("Verify the element '" + targetId + "' does not contain the css value '" + newCssValue + "'");
                    UtilityHelpers.adhocScreenCapture(driver, "Element does not contain CSS value.", "Warning");
                    Assert.fail();
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

    // Validates the target element has expected CSS
    public static void byXpathCssValidation(WebDriver driver, String newCssValue, String targetXpath) {
        try {
        // Define the target element
        WebElement target = driver.findElement(By.xpath(targetXpath));

        // Stores the targets CSS
        String targetCss = target.getAttribute("class");

        // Assert the target element contains the new CSS
        System.out.println("Target CSS value: " + targetCss);

        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
            if (targetCss.contains(newCssValue)) {
                System.out.println("Test passed");
                 Assert.assertTrue(targetCss.contains(newCssValue));
            } else if (!targetCss.contains(newCssValue)) {
                System.out.println("Test Warn");
                 Assert.assertTrue(targetCss.contains(newCssValue));
            }
        } else {

            if (targetCss.contains(newCssValue)) {
                System.out.println("Test passed");
                localTest.get().pass("The element '" + targetXpath + "' does contains the css value '" + newCssValue + "'");
            } else if (!targetCss.contains(newCssValue)) {
                System.out.println("Test Warn");
                localTest.get().fail("The element '" + targetXpath + "' does NOT contain the css value '" + newCssValue + "'");
                UtilityHelpers.adhocScreenCapture(driver, "Element does not contain CSS value.", "Warning");
                Assert.fail();
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

    // Validates the target element has expected CSS
    public static void byClassNameCssValidation(WebDriver driver, String newCssValue, String targetClassName) {
        try {
        // Define the target element
        WebElement target = driver.findElement(By.className(targetClassName));

        // Stores the targets CSS
        String targetCss = target.getAttribute("class");

        // Assert the target element contains the new CSS
        System.out.println("Target CSS value: " + targetCss);

        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
            if (targetCss.contains(newCssValue)) {
                System.out.println("Test passed");
                 Assert.assertTrue(targetCss.contains(newCssValue));
            } else if (!targetCss.contains(newCssValue)) {
                System.out.println("Test Warn");
                 Assert.assertTrue(targetCss.contains(newCssValue));
            }
        } else {

            if (targetCss.contains(newCssValue)) {
                System.out.println("Test passed");
                localTest.get().pass("Verify the element '" + targetClassName + "' does contains the css value '" + newCssValue + "'");
            } else if (!targetCss.contains(newCssValue)) {
                System.out.println("Test Warn");
                localTest.get().fail("Verify the element '" + targetClassName + "' does not contain the css value '" + newCssValue + "'");
                UtilityHelpers.adhocScreenCapture(driver, "Element does not contain CSS value.", "Warning");
                Assert.fail();
            }
        }
    } catch (TimeoutException te) {
        te.printStackTrace();
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + targetClassName);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + targetClassName);
                UtilityHelpers.adhocScreenCapture(driver, "Element not found.", "Failure");
                Assert.fail();
            }
        }
    }

    // Validates the target element has expected CSS
    public static String byIdTagNameValidation(WebDriver driver, String tagNameToValidate, String gridId) {
        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(By.id(gridId)));
        } catch (Exception e) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + gridId);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + gridId);
                UtilityHelpers.adhocScreenCapture(driver, "Could not find element: " + gridId, "Failure");
                Assert.fail();
            }
        }
        // Finds grid to extract data
        WebElement mytable = driver.findElement(By.id(gridId));
        //To locate rows of table.
        List<WebElement> rows_table = mytable.findElements(By.tagName(tagNameToValidate));

        //Loop will execute till the last row of table.
        String allItemResults = "";
        for (int fieldItems = 0; fieldItems < rows_table.size(); fieldItems++) {
            //Store the element CSS value
            String tempItemResults = allItemResults + '\n';
            allItemResults = tempItemResults + (rows_table.get(fieldItems).getTagName());
        }

        // System.out.println("Line Number " + allItemResults);
        return allItemResults;
    }

    // Validates the target element has expected CSS
    public static String byXpathTagNameValidation(WebDriver driver, String tagNameToValidate, String gridXpath) {
        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(gridXpath)));
        } catch (Exception e) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + gridXpath);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + gridXpath);
                UtilityHelpers.adhocScreenCapture(driver, "Could not find element: " + gridXpath, "Failure");
                Assert.fail();
            }
        }
        // Finds grid to extract data
        WebElement mytable = driver.findElement(By.xpath(gridXpath));
        //To locate rows of table.
        List<WebElement> rows_table = mytable.findElements(By.tagName(tagNameToValidate));

        //Loop will execute till the last row of table.
        String allItemResults = "";
        for (int fieldItems = 0; fieldItems < rows_table.size(); fieldItems++) {
            //Store the element CSS value
            String tempItemResults = allItemResults + '\n';
            allItemResults = tempItemResults + (rows_table.get(fieldItems).getTagName());
        }

        // System.out.println("Line Number " + allItemResults);
        return allItemResults;
    }

    // Validates the target element has expected CSS
    public static String byClassNameTagNameValidation(WebDriver driver, String tagNameToValidate, String gridClassName) {
        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(By.className(gridClassName)));
        } catch (Exception e) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + gridClassName);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + gridClassName);
                UtilityHelpers.adhocScreenCapture(driver, "Could not find element: " + gridClassName, "Failure");
                Assert.fail();
            }
        }
        // Finds grid to extract data
        WebElement mytable = driver.findElement(By.className(gridClassName));
        //To locate rows of table.
        List<WebElement> rows_table = mytable.findElements(By.tagName(tagNameToValidate));

        //Loop will execute till the last row of table.
        String allItemResults = "";
        for (int fieldItems = 0; fieldItems < rows_table.size(); fieldItems++) {
            //Store the element CSS value
            String tempItemResults = allItemResults + '\n';
            allItemResults = tempItemResults + (rows_table.get(fieldItems).getTagName());
        }

        // System.out.println("Line Number " + allItemResults);
        return allItemResults;
    }


    // Identifies the presence of a specific class value of an element Xpath
    public static void byXpathClassNameIsPresent(WebDriver driver, String classNameValue, String targetXpath) {
        try {
        // Added to prevent racing condition failure
        new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(targetXpath)));
        // Finds specified object on page
        WebElement element = driver.findElement(By.xpath(targetXpath));

        // Identifies the targets class name
        String elementClass = element.getAttribute("class");

        // Highlights the element
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style','background-color: yellow');", element);

        System.out.println("Verifying the expected class name: \'" + classNameValue + "\' is within the target element " + elementClass);
        // Verifies the class name is present
        JavascriptExecutor js_remove = (JavascriptExecutor) driver;
        js_remove.executeScript("arguments[0].setAttribute('style','background-color: transparent');", element);

        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
            if (elementClass.contains(classNameValue)) {
                System.out.println("Test passed");
                 Assert.assertTrue(elementClass.contains(classNameValue));
            } else if (!elementClass.contains(classNameValue)) {
                System.out.println("Test Warn");
                 Assert.assertTrue(elementClass.contains(classNameValue));
            }
        } else {

            if (elementClass.contains(classNameValue)) {
                System.out.println("Test passed");
                localTest.get().pass("The classname: '"+classNameValue+"' is present within: " + targetXpath);
            } else if (!elementClass.contains(classNameValue)) {
                System.out.println("Test Warn");
                localTest.get().fail("The classname: '"+classNameValue+"' is not present within: " + targetXpath);
                UtilityHelpers.adhocScreenCapture(driver, "Classname is not present.", "Warning");
                Assert.fail();
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

    // Identifies the presence of a specific class value of an element ID
    public static void byIdClassNameIsPresent(WebDriver driver, String classNameValue, String targetId) {
        try {
        // Added to prevent racing condition failure
        new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(By.id(targetId)));
        // Finds specified object on page
        WebElement element = driver.findElement(By.id(targetId));

        // Identifies the targets class name
        String elementClass = element.getAttribute("class");

        // Highlights the element
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style','background-color: yellow');", element);

        System.out.println("Verifying the expected class name: \'" + classNameValue + "\' is within the target element " + elementClass);
        // Verifies the class name is present
        JavascriptExecutor js_remove = (JavascriptExecutor) driver;
        js_remove.executeScript("arguments[0].setAttribute('style','background-color: transparent');", element);

        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
            if (elementClass.contains(classNameValue)) {
                System.out.println("Test passed");
                 Assert.assertTrue(elementClass.contains(classNameValue));
            } else if (!elementClass.contains(classNameValue)) {
                System.out.println("Test Warn");
                Assert.assertTrue(elementClass.contains(classNameValue));

            }
        } else {

            if (elementClass.contains(classNameValue)) {
                System.out.println("Test passed");
                localTest.get().pass("The classname: '"+classNameValue+"' is present within: " + targetId);
            } else if (!elementClass.contains(classNameValue)) {
                System.out.println("Test Warn");
                localTest.get().fail("The classname: '"+classNameValue+"' is not present within: " + targetId);
                UtilityHelpers.adhocScreenCapture(driver, "Classname is not present.", "Warning");
                Assert.fail();
            }
        }
    } catch (TimeoutException te) {
        te.printStackTrace();
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + targetId);
            } else {
                localTest.get().fail("Could not find element: " + targetId);
                UtilityHelpers.adhocScreenCapture(driver, "Element not found.", "Failure");
            }
        }
    }

    // Identifies the presence of a specific class value of an element Xpath
    public static void byXpathClassNameNotIsPresent(WebDriver driver, String classNameValue, String targetXpath) {
        try {
        // Added to prevent racing condition failure
        new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(targetXpath)));
        // Finds specified object on page
        WebElement element = driver.findElement(By.xpath(targetXpath));

        // Identifies the targets class name
        String elementClass = element.getAttribute("class");

        // Highlights the element
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style','background-color: yellow');", element);

        System.out.println("Verifying the expected class name: \'" + classNameValue + "\' is NOT within the target element " + elementClass);
        // Verifies the class name is present
        JavascriptExecutor js_remove = (JavascriptExecutor) driver;
        js_remove.executeScript("arguments[0].setAttribute('style','background-color: transparent');", element);

        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
            if (!elementClass.contains(classNameValue)) {
                System.out.println("Test passed");
                 Assert.assertTrue(!elementClass.contains(classNameValue));
            } else if (elementClass.contains(classNameValue)) {
                System.out.println("Test Warn");
                 Assert.assertTrue(!elementClass.contains(classNameValue));
            }
        } else {

            if (!elementClass.contains(classNameValue)) {
                System.out.println("Test passed");
                localTest.get().pass("The element class " + elementClass + " was NOT found.");
            } else if (elementClass.contains(classNameValue)) {
                System.out.println("Test Warn");
                localTest.get().fail("The element class " + elementClass + " was found.");
                UtilityHelpers.adhocScreenCapture(driver, "Classname was found.", "Warning");
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

    // Identifies the presence of a specific class value of an element ID
    public static void byIdClassNameIsNotPresent(WebDriver driver, String classNameValue, String targetId) {
        try {
        // Added to prevent racing condition failure
        new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(By.id(targetId)));
        // Finds specified object on page
        WebElement element = driver.findElement(By.id(targetId));

        // Identifies the targets class name
        String elementClass = element.getAttribute("class");

        // Highlights the element
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style','background-color: yellow');", element);

        System.out.println("Verifying the expected class name: \'" + classNameValue + "\' is NOT within the target element " + elementClass);
        // Verifies the class name is present
        JavascriptExecutor js_remove = (JavascriptExecutor) driver;
        js_remove.executeScript("arguments[0].setAttribute('style','background-color: transparent');", element);

        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
            if (!elementClass.contains(classNameValue)) {
                System.out.println("Test passed");
                 Assert.assertTrue(!elementClass.contains(classNameValue));
            } else if (elementClass.contains(classNameValue)) {
                System.out.println("Test Warn");
                 Assert.assertTrue(!elementClass.contains(classNameValue));
            }
        } else {

            if (!elementClass.contains(classNameValue)) {
                System.out.println("Test passed");
                localTest.get().pass("The element class " + elementClass + " was NOT found.");
            } else if (elementClass.contains(classNameValue)) {
                System.out.println("Test Warn");
                localTest.get().fail("The element class " + elementClass + " was found.");
                UtilityHelpers.adhocScreenCapture(driver, "Classname was found.", "Warning");
                Assert.fail();
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


    // Identifies the class value of an element Xpath
    public static String byXpathClassNameIsPresent(WebDriver driver, String targetXpath) {

        // Added to prevent racing condition failure
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
        // Finds specified object on page
        WebElement element = driver.findElement(By.xpath(targetXpath));

        // Identifies the targets class name
        String elementClass = element.getAttribute("class");

        // Return results
        return elementClass;

    }

    // Identifies the class value of an element Id
    public static String byIdClassNameIsPresent(WebDriver driver, String targetId) {

        // Added to prevent racing condition failure
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
        // Finds specified object on page
        WebElement element = driver.findElement(By.id(targetId));

        // Identifies the targets class name
        String elementClass = element.getAttribute("class");

        // Return results
        return elementClass;

    }

    // Identifies the class value of an element Xpath
    public static String byXpathTagnameIsPresent(WebDriver driver, String targetXpath) {

        // Added to prevent racing condition failure
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
        // Finds specified object on page
        WebElement element = driver.findElement(By.xpath(targetXpath));

        // Identifies the targets class name
        String elementClass = element.getAttribute("tagname");

        // Return results
        return elementClass;

    }

    // Identifies the class value of an element Id
    public static String byIdTagnameIsPresent(WebDriver driver, String targetId) {

        // Added to prevent racing condition failure
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
        // Finds specified object on page
        WebElement element = driver.findElement(By.id(targetId));

        // Identifies the targets class name
        String elementClass = element.getAttribute("tagname");

        // Return results
        return elementClass;

    }

    // Identifies the class value of an element Name
    public static String byNameTagnameIsPresent(WebDriver driver, String targetName) {

        // Added to prevent racing condition failure
        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(By.name(targetName)));
        } catch (Exception e) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + targetName);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + targetName);
                UtilityHelpers.adhocScreenCapture(driver, "Could not find element: " + targetName, "Failure");
                Assert.fail();
            }
        }
        // Finds specified object on page
        WebElement element = driver.findElement(By.name(targetName));

        // Identifies the targets class name
        String elementClass = element.getAttribute("tagname");

        // Return results
        return elementClass;

    }

    // Identifies the class value of an element CSS Selector
    public static String byCssSelectorTagnameIsPresent(WebDriver driver, String targetCssSelector) {

        // Added to prevent racing condition failure
        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(targetCssSelector)));
        } catch (Exception e) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + targetCssSelector);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + targetCssSelector);
                UtilityHelpers.adhocScreenCapture(driver, "Could not find element: " + targetCssSelector, "Failure");
                Assert.fail();
            }
        }
        // Finds specified object on page
        WebElement element = driver.findElement(By.cssSelector(targetCssSelector));

        // Identifies the targets class name
        String elementClass = element.getAttribute("tagname");

        // Return results
        return elementClass;

    }

    // Identifies the class value of an element CSS Selector
    public static String byTagnameIsPresent(WebDriver driver, String targetTagName) {

        // Added to prevent racing condition failure
        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.presenceOfElementLocated(By.tagName(targetTagName)));
        } catch (Exception e) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + targetTagName);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + targetTagName);
                UtilityHelpers.adhocScreenCapture(driver, "Could not find element: " + targetTagName, "Failure");
                Assert.fail();
            }
        }
        // Finds specified object on page
        WebElement element = driver.findElement(By.tagName(targetTagName));

        // Identifies the targets class name
        String elementClass = element.getAttribute("tagname");

        // Return results
        return elementClass;

    }

}
