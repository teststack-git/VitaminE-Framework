package Functions.ValidationHelpers;

import ExtentReport.ExtentReportBuilder;

import Core.Configuration;
import Core.FileReadWrite;

import static org.assertj.core.api.Assertions.assertThat;

import Functions.UtilityHelpers.UtilityHelpers;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * This class provides validation for the displaying of text
 * Uses ObjectLibrary.xml for object references
 * Uses Datalibrary.xml for data references
 */


public class ValidateTextDisplaysHelpers extends ExtentReportBuilder {

    static String configTimeout = FileReadWrite.loadConfigurationProperties().getProperty("timeout");
    static int timeout = Integer.parseInt(configTimeout);

    // Identifies the presence of text on the screen by Xpath
    public static void byXpathTextIsPresent(WebDriver driver, String value, String targetXpath) {
        try {
            // Added to prevent racing condition failure
            // Finds specified object on page
            new WebDriverWait(driver, timeout).until(ExpectedConditions.presenceOfElementLocated(By.xpath(targetXpath)));
            WebElement element = driver.findElement(By.xpath(targetXpath));

            // Identifies the text and removes extra spaces
            String elementText = element.getText().trim();
            System.out.println("Verifying the expected value: \'" + value + "\' matches the target text \'" + elementText + "\'");

            // Highlights the text
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].setAttribute('style','background-color: yellow');", element);

            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                Assert.assertTrue(elementText.contains(value));
            } else {

                if (elementText.contains(value)) {
                    System.out.println("Test passed");
                    localTest.get().pass("Verify text value '" + value + "' is present within: " + targetXpath);
                } else if (!elementText.contains(value)) {
                    System.out.println("Test Warn");
                    localTest.get().warning("Verify text value '" + value + "' is Not present within: " + targetXpath);
                    UtilityHelpers.adhocScreenCapture(driver, "Text is not present.", "Warning");

                }
            }
        } catch (TimeoutException te) {
            te.printStackTrace();
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + targetXpath);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + targetXpath);
                UtilityHelpers.adhocScreenCapture(driver, "Text not found.", "Failure");
                Assert.fail();
            }
        }
    }

    // Identifies the presence of text on the screen by Tagname
    public static void byTagnameTextIsPresent(WebDriver driver, String value, String targetTagname) {
        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.presenceOfElementLocated(By.tagName(targetTagname)));
            // Finds specified object on page
            WebElement element = driver.findElement(By.tagName(targetTagname));

            // Identifies the text and removes extra spaces
            String elementText = element.getText().trim();
            System.out.println("Verifying the expected value: \'" + value + "\' matches the target text \'" + elementText + "\'");

            // Highlights the text
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].setAttribute('style','background-color: yellow');", element);

            JavascriptExecutor js_remove = (JavascriptExecutor) driver;
            js_remove.executeScript("arguments[0].setAttribute('style','background-color: transparent');", element);

            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                Assert.assertTrue(elementText.contains(value));
            } else {

                if (elementText.contains(value)) {
                    System.out.println("Test passed");
                    localTest.get().pass("Verify text value '" + value + "' is present within: " + targetTagname);
                } else if (!elementText.contains(value)) {
                    System.out.println("Test Warn");
                    localTest.get().warning("Verify text value '" + value + "' is Not present within: " + targetTagname);
                    UtilityHelpers.adhocScreenCapture(driver, "Text is not present.", "Warning");
                }
            }
        } catch (TimeoutException te) {
            te.printStackTrace();
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + targetTagname);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + targetTagname);
                UtilityHelpers.adhocScreenCapture(driver, "Text not found.", "Failure");
                Assert.fail();
            }
        }
    }


    // Identifies the presence of text on the screen by Class Name
    public static void byClassNameTextIsPresent(WebDriver driver, String value, String targetClassName) {
        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.presenceOfElementLocated(By.className(targetClassName)));
            System.out.println("Looking for the following object: " + targetClassName);
            // Finds specified object on page
            WebElement element = driver.findElement(By.className(targetClassName));

            // Identifies the text and removes extra spaces
            String elementText = element.getText().trim();
            System.out.println("Verifying the expected value: \'" + value + "\' matches the target text \'" + elementText + "\'");

            // Highlights the text
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].setAttribute('style','background-color: yellow');", element);

            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                Assert.assertTrue(elementText.contains(value));
            } else {

                if (elementText.contains(value)) {
                    System.out.println("Test passed");
                    localTest.get().pass("Verify text value '" + value + "' is present within: " + targetClassName);
                } else if (!elementText.contains(value)) {
                    System.out.println("Test Warn");
                    localTest.get().warning("Verify text value '" + value + "' is Not present within: " + targetClassName);
                    UtilityHelpers.adhocScreenCapture(driver, "Text is not present.", "Warning");
                }
            }
        } catch (TimeoutException te) {
            te.printStackTrace();
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + targetClassName);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + targetClassName);
                UtilityHelpers.adhocScreenCapture(driver, "Text not found.", "Failure");
                Assert.fail();
            }
        }
    }

    // Identifies the visibility of text on the screen by Xpath
    public static void byXpathTextIsVisible(WebDriver driver, String value, String targetXpath) {
        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(targetXpath)));
            // Finds specified object on page
            WebElement element = driver.findElement(By.xpath(targetXpath));

            // Identifies the text and removes extra spaces
            String elementText = element.getText().trim();
            System.out.println("Verifying the expected value: \'" + value + "\' matches the target text \'" + elementText + "\'");

            // Highlights the text
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].setAttribute('style','background-color: yellow');", element);

            JavascriptExecutor js_remove = (JavascriptExecutor) driver;
            js_remove.executeScript("arguments[0].setAttribute('style','background-color: transparent');", element);

            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                Assert.assertTrue(elementText.contains(value));
            } else {

                if (elementText.contains(value)) {
                    System.out.println("Test passed");
                    localTest.get().pass("Verify text value '" + value + "' is visible within: " + targetXpath);
                } else if (!elementText.contains(value)) {
                    System.out.println("Test Warn");
                    localTest.get().warning("Verify text value '" + value + "' is Not visible within: " + targetXpath);
                    UtilityHelpers.adhocScreenCapture(driver, "Text is not visible.", "Warning");
                }
            }
        } catch (TimeoutException te) {
            te.printStackTrace();
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + targetXpath);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + targetXpath);
                UtilityHelpers.adhocScreenCapture(driver, "Text not found.", "Failure");
                Assert.fail();
            }
        }
    }

    // Identifies the presence of text on the screen by ID
    public static void byIDTextIsPresent(WebDriver driver, String value, String targetId) {
        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.presenceOfElementLocated(By.id(targetId)));
            // Finds specified object on page
            WebElement element = driver.findElement(By.id(targetId));

            // Identifies the text and removes extra spaces
            String elementText = element.getText().trim();

            // Highlights the text
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].setAttribute('style','background-color: yellow');", element);

            System.out.println("Verifying the expected value: \'" + value + "\' is within the target text \'" + elementText + "\'");
            JavascriptExecutor js_remove = (JavascriptExecutor) driver;
            js_remove.executeScript("arguments[0].setAttribute('style','background-color: transparent');", element);

            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                Assert.assertTrue(elementText.contains(value));
            } else {

                if (elementText.contains(value)) {
                    System.out.println("Test passed");
                    localTest.get().pass("Verify text value '" + value + "' is present within: " + targetId);
                } else if (!elementText.contains(value)) {
                    System.out.println("Test Warn");
                    localTest.get().warning("Verify text value '" + value + "' is Not present within: " + targetId);
                    UtilityHelpers.adhocScreenCapture(driver, "Text is not present.", "Warning");
                }
            }
        } catch (TimeoutException te) {
            te.printStackTrace();
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + targetId);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + targetId);
                UtilityHelpers.adhocScreenCapture(driver, "Text not found.", "Failure");
                Assert.fail();
            }
        }
    }

    // Identifies the presence of a specific value of an element Xpath
    public static void byXpathValueIsPresent(WebDriver driver, String value, String targetXpath) {
        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.presenceOfElementLocated(By.xpath(targetXpath)));
            // Finds specified object on page
            WebElement element = driver.findElement(By.xpath(targetXpath));

            // Identifies the text and removes extra spaces
            String elementText = element.getAttribute("value").trim();

            // Highlights the text
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].setAttribute('style','background-color: yellow');", element);

            System.out.println("Verifying the expected value: \'" + value + "\' is within the target text \'" + elementText + "\'");
            JavascriptExecutor js_remove = (JavascriptExecutor) driver;
            js_remove.executeScript("arguments[0].setAttribute('style','background-color: transparent');", element);

            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                Assert.assertTrue(elementText.contains(value));
            } else {

                if (elementText.contains(value)) {
                    System.out.println("Test passed");
                    localTest.get().pass("Verify text value '" + value + "' is present within: " + targetXpath);
                } else if (!elementText.contains(value)) {
                    System.out.println("Test Warn");
                    localTest.get().warning("Verify text value '" + value + "' is Not present within: " + targetXpath);
                    UtilityHelpers.adhocScreenCapture(driver, "Value is not present.", "Warning");
                }
            }
        } catch (TimeoutException te) {
            te.printStackTrace();
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + targetXpath);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + targetXpath);
                UtilityHelpers.adhocScreenCapture(driver, "Text not found.", "Failure");
                Assert.fail();
            }
        }
    }

    // Identifies the presence of a specific value of an element ID
    public static void byIDValueIsPresent(WebDriver driver, String value, String targetId) {
        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.presenceOfElementLocated(By.id(targetId)));
            // Finds specified object on page
            WebElement element = driver.findElement(By.id(targetId));

            // Identifies the text and removes extra spaces
            String elementText = element.getAttribute("value").trim();

            // Highlights the text
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].setAttribute('style','background-color: yellow');", element);

            System.out.println("Verifying the expected value: \'" + value + "\' is within the target text \'" + elementText + "\'");
            // Highlights the text
            JavascriptExecutor js_remove = (JavascriptExecutor) driver;
            js_remove.executeScript("arguments[0].setAttribute('style','background-color: transparent');", element);

            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                Assert.assertTrue(elementText.contains(value));
            } else {

                if (elementText.contains(value)) {
                    System.out.println("Test passed");
                    localTest.get().pass("Verify text value '" + value + "' is present within: " + targetId);
                } else if (!elementText.contains(value)) {
                    System.out.println("Test Warn");
                    localTest.get().warning("Verify text value '" + value + "' is Not present within: " + targetId);
                    UtilityHelpers.adhocScreenCapture(driver, "Value is not present.", "Warning");
                }
            }
        } catch (TimeoutException te) {
            te.printStackTrace();
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + targetId);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + targetId);
                UtilityHelpers.adhocScreenCapture(driver, "Text not found.", "Failure");
                Assert.fail();
            }
        }
    }

    // Identifies the presence a specific text value
    public static void byTextValueIsPresent(WebDriver driver, String textValue) {
        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), '" + textValue +"')]")));
            // Finds specified object on page
            WebElement element = driver.findElement(By.xpath("//*[contains(text(), '" + textValue +"')]"));

            // Identifies the text and removes extra spaces
            String elementText = element.getAttribute("value").trim();

            // Highlights the text
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].setAttribute('style','background-color: yellow');", element);

            System.out.println("Verifying the expected value: \'" + textValue + "\' is within the target text \'" + elementText + "\'");

            // Highlights the text
            JavascriptExecutor js_remove = (JavascriptExecutor) driver;
            js_remove.executeScript("arguments[0].setAttribute('style','background-color: transparent');", element);

            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                Assert.assertTrue(elementText.contains(textValue));
            } else {

                if (elementText.contains(textValue)) {
                    System.out.println("Test passed");
                    localTest.get().pass("Value '"+textValue+"' is present.");
                } else if (!elementText.contains(textValue)) {
                    System.out.println("Test Warn");
                    localTest.get().warning("The "+textValue+" is NOT present.");
                    UtilityHelpers.adhocScreenCapture(driver, "Value is not present.", "Warning");
                }
            }
        } catch (TimeoutException te) {
            te.printStackTrace();
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + textValue);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + textValue);
                UtilityHelpers.adhocScreenCapture(driver, "Text not found.", "Failure");
                Assert.fail();
            }
        }

    }

    // Identifies the absence of text on the screen by ID
    public static void byIDTextIsNOTPresent(WebDriver driver, String notPresentTextValue, String targetId) {
        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(By.id(targetId)));
            // Finds specified object on page
            WebElement element = driver.findElement(By.id(targetId));
            String elementText = element.getText().trim();
            System.out.println("Verifying the expected value: \'" + notPresentTextValue + "\' does not contain the following: \'" + elementText + "\'");

            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                Assert.assertTrue(!elementText.contains(notPresentTextValue));
            } else {

                if (!elementText.contains(notPresentTextValue)) {
                    System.out.println("Test passed");
                    localTest.get().pass("Text is NOT present within: " + targetId);
                } else if (!elementText.contains(notPresentTextValue)) {
                    System.out.println("Test Warn");
                    localTest.get().warning("Text is present within: " + targetId);
                    UtilityHelpers.adhocScreenCapture(driver, "Text is present.", "Warning");
                }
            }
        } catch (TimeoutException te) {
            te.printStackTrace();
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + targetId);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + targetId);
                UtilityHelpers.adhocScreenCapture(driver, "Text not found.", "Failure");
                Assert.fail();
            }
        }
    }

    // Identifies the absence of text on the screen by Xpath
    public static void byXpathTextIsNOTPresent(WebDriver driver, String notPresentTextValue, String targetXpath) {
        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(targetXpath)));
            // Finds specified object on page
            WebElement element = driver.findElement(By.xpath(targetXpath));
            String elementText = element.getText().trim();
            System.out.println("Verifying the expected value: \'" + notPresentTextValue + "\' is not present.");

            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                Assert.assertTrue(!elementText.contains(notPresentTextValue));
            } else {

                if (!elementText.contains(notPresentTextValue)) {
                    System.out.println("Test passed");
                    localTest.get().pass("Text is NOT present within: " + targetXpath);
                } else if (!elementText.contains(notPresentTextValue)) {
                    System.out.println("Test Warn");
                    localTest.get().warning("Text is present within: " + targetXpath);
                    UtilityHelpers.adhocScreenCapture(driver, "Text is present.", "Warning");
                }
            }
        } catch (TimeoutException te) {
            te.printStackTrace();
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + targetXpath);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + targetXpath);
                UtilityHelpers.adhocScreenCapture(driver, "Text not found.", "Failure");
                Assert.fail();
            }
        }
    }

    // Identifies the presence of an object on the screen by CSS Selector
    public static void byCssSelectorTextIsPresent(WebDriver driver, String value, String targetCssSelector) {
        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(targetCssSelector)));
            // Finds specified object on page
            WebElement element = driver.findElement(By.cssSelector(targetCssSelector));

            // Identifies the text and removes extra spaces
            String elementText = element.getAttribute("value").trim();

            System.out.println("Verifying the expected value: \'" + value + "\' is within the target text \'" + elementText + "\'");

            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                Assert.assertTrue(elementText.contains(value));
            } else {

                if (elementText.contains(value)) {
                    System.out.println("Test passed");
                    localTest.get().pass("Verifying text is present within: " + targetCssSelector);
                } else if (!elementText.contains(value)) {
                    System.out.println("Test Warn");
                    localTest.get().warning("Verifying text is NOT present within: " + targetCssSelector);
                    UtilityHelpers.adhocScreenCapture(driver, "Text is not present.", "Warning");
                }
            }
        } catch (TimeoutException te) {
            te.printStackTrace();
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + targetCssSelector);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + targetCssSelector);
                UtilityHelpers.adhocScreenCapture(driver, "Text not found.", "Failure");
                Assert.fail();
            }
        }
    }


    // Identifies the presence of an object on the screen by ID
    public static void byLinkTextTextIsPresent(WebDriver driver, String value, String targetLinkText) {
        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(By.linkText(targetLinkText)));
            // Finds specified object on page
            WebElement element = driver.findElement(By.linkText(targetLinkText));

            // Identifies the text and removes extra spaces
            String elementText = element.getAttribute("value").trim();

            System.out.println("Verifying the expected value: \'" + value + "\' is within the target text \'" + elementText + "\'");

            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                Assert.assertTrue(elementText.contains(value));
            } else {

                if (elementText.contains(value)) {
                    System.out.println("Test passed");
                    localTest.get().pass("The text is present within: " + targetLinkText);
                } else if (!elementText.contains(value)) {
                    System.out.println("Test Warn");
                    localTest.get().warning("The text is NOT present within: " + targetLinkText);
                    UtilityHelpers.adhocScreenCapture(driver, "Text is present.", "Warning");
                }
            }
        } catch (TimeoutException te) {
            te.printStackTrace();
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + targetLinkText);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + targetLinkText);
                UtilityHelpers.adhocScreenCapture(driver, "Text not found.", "Failure");
                Assert.fail();
            }
        }
    }

    // Return True or False if Element identified by LinkText is displayed
    public static Boolean boolByLinkTextElementIsDisplayed(WebDriver driver, String targetLinkText) {
        boolean found = false;
        try {
            new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(By.linkText(targetLinkText)));

            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                Assert.assertTrue(driver.findElement(By.linkText(targetLinkText)).isDisplayed());
            } else {

                if (driver.findElement(By.linkText(targetLinkText)).isDisplayed()) {
                    System.out.println("Test passed");
                    localTest.get().pass("Element with LinkText '" + targetLinkText + "' is displayed");
                    found = true;
                } else if (!driver.findElement(By.linkText(targetLinkText)).isDisplayed()) {
                    System.out.println("Test Warn");
                    localTest.get().warning("Element with LinkText '" + targetLinkText + "' is NOT displayed");
                    UtilityHelpers.adhocScreenCapture(driver, "Text does not display.", "Warning");
                }

            }
        } catch (TimeoutException te) {
            te.printStackTrace();
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + targetLinkText);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + targetLinkText);
                UtilityHelpers.adhocScreenCapture(driver, "Text not found.", "Failure");
                Assert.fail();
            }
        }
        return found;
    }



    // Compares text object with an expected value
    public static void byXpathTextEquals(WebDriver driver, String expectedText, String targetXpath) {
        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(targetXpath)));

            // Identifies the text object
            WebElement validationTarget = driver.findElement(By.xpath(targetXpath));
            String dataValidation = validationTarget.getText();

            System.out.println("This is the data displayed: " + dataValidation);
            System.out.println("This is the comparison data: " + expectedText);

            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                Assert.assertTrue(dataValidation.equals(expectedText));
            } else {

                if (dataValidation.equals(expectedText)) {
                    System.out.println("Test passed");
                    localTest.get().pass("The text '" + dataValidation + "' equals the text '" + expectedText + "'");
                } else if (!dataValidation.equals(expectedText)) {
                    System.out.println("Test Warn");
                    localTest.get().warning("The text '" + dataValidation + "' does NOT equal the text '" + expectedText + "'");
                    UtilityHelpers.adhocScreenCapture(driver, "Text does not match.", "Warning");
                }
            }
        } catch (TimeoutException te) {
            te.printStackTrace();
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + targetXpath);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + targetXpath);
                UtilityHelpers.adhocScreenCapture(driver, "Text not found.", "Failure");
                Assert.fail();
            }
        }
    }

    // Compares text object with an expected value
    public static void byIdTextEquals(WebDriver driver, String expectedText, String targetId) {
        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(By.id(targetId)));

            // Identifies the text object
            WebElement validationTarget = driver.findElement(By.id(targetId));
            String dataValidation = validationTarget.getText();

            System.out.println("This is the data displayed: " + dataValidation);
            System.out.println("This is the comparison data: " + expectedText);

            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                Assert.assertTrue(dataValidation.equals(expectedText));
            } else {

                if (dataValidation.equals(expectedText)) {
                    System.out.println("Test passed");
                    localTest.get().pass("The text '" + dataValidation + "' equals the value '" + expectedText + "'");
                } else if (!dataValidation.equals(expectedText)) {
                    System.out.println("Test Warn");
                    localTest.get().warning("The text '" + dataValidation + "' does NOT match the value '" + expectedText + "'");
                    UtilityHelpers.adhocScreenCapture(driver, "Text does not match.", "Warning");
                }
            }
        } catch (TimeoutException te) {
            te.printStackTrace();
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + targetId);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + targetId);
                UtilityHelpers.adhocScreenCapture(driver, "Text not found.", "Failure");
                Assert.fail();
            }
        }
    }

    // Compares text object with an expected value
    public static void byCssSelectorTextEquals(WebDriver driver, String expectedText, String targetCssSelector) {
        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(targetCssSelector)));

            // Identifies the text object
            WebElement validationTarget = driver.findElement(By.cssSelector(targetCssSelector));
            String dataValidation = validationTarget.getText();

            System.out.println("This is the data displayed: " + dataValidation);
            System.out.println("This is the comparison data: " + expectedText);
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                Assert.assertTrue(dataValidation.equals(expectedText));
            } else {

                if (dataValidation.equals(expectedText)) {
                    System.out.println("Test passed");
                    localTest.get().pass("The text '" + dataValidation + "' equals the text '" + expectedText + "'");
                } else if (!dataValidation.equals(expectedText)) {
                    System.out.println("Test Warn");
                    localTest.get().warning("The text '" + dataValidation + "' does NOT match the text '" + expectedText + "'");
                    UtilityHelpers.adhocScreenCapture(driver, "Text does not match.", "Warning");
                }
            }
        } catch (TimeoutException te) {
            te.printStackTrace();
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + targetCssSelector);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + targetCssSelector);
                UtilityHelpers.adhocScreenCapture(driver, "Text not found.", "Failure");
                Assert.fail();
            }
        }
    }

    // Compares text object with an expected value
    public static void byXpathTextContains(WebDriver driver, String expectedText, String targetXpath) {
        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(targetXpath)));

            // Identifies the text object
            WebElement validationTarget = driver.findElement(By.xpath(targetXpath));
            String dataValidation = validationTarget.getText();

            System.out.println("This is the data displayed: " + dataValidation);
            System.out.println("This is the comparison data: " + expectedText);

            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                Assert.assertTrue(dataValidation.contains(expectedText));
            } else {

                if (dataValidation.contains(expectedText)) {
                    System.out.println("Test passed");
                    localTest.get().pass("The text '" + dataValidation + "' contains the the '" + expectedText + "'");
                } else if (!dataValidation.contains(expectedText)) {
                    System.out.println("Test Warn");
                    localTest.get().warning("The text '" + dataValidation + "' does NOT contain the text '" + expectedText + "'");
                    UtilityHelpers.adhocScreenCapture(driver, "Text does not contain.", "Warning");
                }
            }
        } catch (TimeoutException te) {
            te.printStackTrace();
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + targetXpath);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + targetXpath);
                UtilityHelpers.adhocScreenCapture(driver, "Text not found.", "Failure");
                Assert.fail();
            }
        }
    }

    // Compares text object with an expected value
    public static void byIdTextContains(WebDriver driver, String expectedText, String targetId) {
        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(By.id(targetId)));

            // Identifies the text object
            WebElement validationTarget = driver.findElement(By.id(targetId));
            String dataValidation = validationTarget.getText();

            System.out.println("This is the data displayed: " + dataValidation);
            System.out.println("This is the comparison data: " + expectedText);

            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                Assert.assertTrue(dataValidation.contains(expectedText));
            } else {

                if (dataValidation.contains(expectedText)) {
                    System.out.println("Test passed");
                    localTest.get().pass("The text '" + dataValidation + "' equals the text '" + expectedText + "'");
                } else if (!dataValidation.contains(expectedText)) {
                    System.out.println("Test Warn");
                    localTest.get().warning("The text '" + dataValidation + "' does NOT contains the text '" + expectedText + "'");
                    UtilityHelpers.adhocScreenCapture(driver, "Text does not match.", "Warning");
                }
            }
        } catch (TimeoutException te) {
            te.printStackTrace();
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + targetId);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + targetId);
                UtilityHelpers.adhocScreenCapture(driver, "Text not found.", "Failure");
                Assert.fail();
            }
        }
    }

    // Compares text object with an expected value
    public static void byCssSelectorTextContains(WebDriver driver, String expectedText, String targetCssSelector) {
        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(targetCssSelector)));

            // Identifies the text object
            WebElement validationTarget = driver.findElement(By.cssSelector(targetCssSelector));
            String dataValidation = validationTarget.getText();

            System.out.println("This is the data displayed: " + dataValidation);
            System.out.println("This is the comparison data: " + expectedText);
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                Assert.assertTrue(dataValidation.contains(expectedText));
            } else {

                if (dataValidation.contains(expectedText)) {
                    System.out.println("Test passed");
                    localTest.get().pass("The text '" + dataValidation + "' contains the text '" + expectedText + "'");
                } else if (!dataValidation.contains(expectedText)) {
                    System.out.println("Test Warn");
                    localTest.get().warning("The text '" + dataValidation + "' does NOT contain the text '" + expectedText + "'");
                    UtilityHelpers.adhocScreenCapture(driver, "Text does not match.", "Warning");
                }
            }
        } catch (TimeoutException te) {
            te.printStackTrace();
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + targetCssSelector);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + targetCssSelector);
                UtilityHelpers.adhocScreenCapture(driver, "Text not found.", "Failure");
                Assert.fail();
            }
        }
    }

    // Compares text object with an expected value
    public static void byXpathValueEquals(WebDriver driver, String expectedText, String targetXpath) {
        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(targetXpath)));

            // Identifies the text object
            WebElement validationTarget = driver.findElement(By.xpath(targetXpath));
            String dataValidation = validationTarget.getAttribute("value");

            System.out.println("This is the data displayed: " + dataValidation);
            System.out.println("This is the comparison data: " + expectedText);

            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                Assert.assertTrue(dataValidation.equals(expectedText));
            } else {

                if (dataValidation.equals(expectedText)) {
                    System.out.println("Test passed");
                    localTest.get().pass("The value '" + dataValidation + "' equals the value '" + expectedText + "'");
                } else if (!dataValidation.equals(expectedText)) {
                    System.out.println("Test Warn");
                    localTest.get().warning("The value '" + dataValidation + "' does NOT equal the value '" + expectedText + "'");
                    UtilityHelpers.adhocScreenCapture(driver, "Value does not match.", "Warning");
                }
            }
        } catch (TimeoutException te) {
            te.printStackTrace();
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + targetXpath);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + targetXpath);
                UtilityHelpers.adhocScreenCapture(driver, "Text not found.", "Failure");
                Assert.fail();
            }
        }
    }

    // Compares text object with an expected value
    public static void byIdValueEquals(WebDriver driver, String expectedText, String targetId) {
        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(By.id(targetId)));

            // Identifies the text object
            WebElement validationTarget = driver.findElement(By.id(targetId));
            String dataValidation = validationTarget.getAttribute("value");

            System.out.println("This is the data displayed: " + dataValidation);
            System.out.println("This is the comparison data: " + expectedText);

            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                Assert.assertTrue(dataValidation.equals(expectedText));

            } else {

                if (dataValidation.equals(expectedText)) {
                    System.out.println("Test passed");
                    localTest.get().pass("The value '" + dataValidation + "' equals the value '" + expectedText + "'");
                } else if (!dataValidation.equals(expectedText)) {
                    System.out.println("Test Warn");
                    localTest.get().warning("The value '" + dataValidation + "' does NOT equal the value '" + expectedText + "'");
                    UtilityHelpers.adhocScreenCapture(driver, "Value does not match.", "Warning");
                }
            }
        } catch (TimeoutException te) {
            te.printStackTrace();
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + targetId);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + targetId);
                UtilityHelpers.adhocScreenCapture(driver, "Text not found.", "Failure");
                Assert.fail();
            }
        }
    }



    // Compares text object with an expected value
    public static void byXpathValueContains(WebDriver driver, String expectedText, String targetXpath) {
        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(targetXpath)));

            // Identifies the text object
            WebElement validationTarget = driver.findElement(By.xpath(targetXpath));
            String dataValidation = validationTarget.getAttribute("value");

            System.out.println("This is the data displayed: " + dataValidation);
            System.out.println("This is the comparison data: " + expectedText);

            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                Assert.assertTrue(dataValidation.contains(expectedText));
            } else {

                if (dataValidation.contains(expectedText)) {
                    System.out.println("Test passed");
                    localTest.get().pass("The value '" + dataValidation + "' contains the value '" + expectedText + "'");
                } else if (!dataValidation.contains(expectedText)) {
                    System.out.println("Test Warn");
                    localTest.get().warning("The value '" + dataValidation + "' does NOT contain the value '" + expectedText + "'");
                    UtilityHelpers.adhocScreenCapture(driver, "Value does not match.", "Warning");
                }
            }
        } catch (TimeoutException te) {
            te.printStackTrace();
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + targetXpath);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + targetXpath);
                UtilityHelpers.adhocScreenCapture(driver, "Text not found.", "Failure");
                Assert.fail();
            }
        }
    }

    // Compares text object with an expected value
    public static void byIdValueContains(WebDriver driver, String expectedText, String targetId) {
        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(By.id(targetId)));

            // Identifies the text object
            WebElement validationTarget = driver.findElement(By.id(targetId));
            String dataValidation = validationTarget.getAttribute("value");

            System.out.println("This is the data displayed: " + dataValidation);
            System.out.println("This is the comparison data: " + expectedText);

            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                Assert.assertTrue(dataValidation.contains(expectedText));

            } else {

                if (dataValidation.contains(expectedText)) {
                    System.out.println("Test passed");
                    localTest.get().pass("The value '" + dataValidation + "' contains the value '" + expectedText + "'");
                } else if (!dataValidation.contains(expectedText)) {
                    System.out.println("Test Warn");
                    localTest.get().warning("The value '" + dataValidation + "' does NOT contain the value '" + expectedText + "'");
                    UtilityHelpers.adhocScreenCapture(driver, "Value does not match.", "Warning");
                }
            }
        } catch (TimeoutException te) {
            te.printStackTrace();
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + targetId);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + targetId);
                UtilityHelpers.adhocScreenCapture(driver, "Text not found.", "Failure");
                Assert.fail();
            }
        }
    }

}
