package Functions.ClickHelpers;

import ExtentReport.ExtentReportBuilder;

import Core.Configuration;
import Core.FileReadWrite;

import Functions.UtilityHelpers.UtilityHelpers;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;



/**
 * This class allows for ClickHelpers events to interact with text elements
 * Uses ObjectLibrary.xml for object references
 * Uses Datalibrary.xml for data references
 */


public class ClickTextHelpers extends ExtentReportBuilder {

    static String configTimeout = FileReadWrite.loadConfigurationProperties().getProperty("timeout");
    static int timeout = Integer.parseInt(configTimeout);

    // ClickHelpers on element that contains a text value
    public static void byTextValueClick(WebDriver driver, String textValue) {

        System.out.println("Going to Click: " + textValue);

        try {
            // Find the number of elements present
            int NumOfElements = driver.findElements(By.xpath("//span[contains(text(), '" + textValue + "')]")).size();
            // If element is found, then click on text
            if (NumOfElements==1) {
                WebElement clickObject = driver.findElement(By.xpath("//span[contains(text(), '" + textValue +"')]"));
                clickObject.click();
                if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                    System.out.println("Clicking on the text value: " + textValue);
                } else {
                    localTest.get().info("Clicking on the text value: " + textValue);
                }
                // If element is NOT found, then write error
            } else if (NumOfElements==0) {
                if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                    System.out.println("Could not find element: " + textValue);
                    Assert.fail();
                } else {
                    System.out.println("Could not find element: " + textValue);
                    localTest.get().fail("Could not find text: " + textValue);
                    UtilityHelpers.adhocScreenCapture(driver, "Could not find text: " + textValue, "Failure");
                    Assert.fail();
                }
            }
        } catch (Exception e) {
            // Find the number of elements present
            int NumOfElements = driver.findElements(By.xpath("//div[contains(text(), '" + textValue + "')]")).size();
            // If element is found, then click on text
            if (NumOfElements==1) {
                WebElement clickObject = driver.findElement(By.xpath("//div[contains(text(), '" + textValue +"')]"));
                clickObject.click();
                if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                    System.out.println("Clicking on the text value: " + textValue);
                } else {
                    localTest.get().info("Clicking on the text value: " + textValue);
                }
                // If element is NOT found, then write error
            } else if (NumOfElements==0) {
                if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                    System.out.println("Could not find element: " + textValue);
                    Assert.fail();
                } else {
                    localTest.get().fail("Could not find text: " + textValue);
                    UtilityHelpers.adhocScreenCapture(driver, "Could not find text: " + textValue, "Failure");
                    Assert.fail();
                }
            }
        }

    }

    // ClickHelpers on a link based on its text value
    public static void byLinkTextClick(WebDriver driver, String linkText) {
        try {
            // Find the number of elements present
            int NumOfElements = driver.findElements(By.linkText(linkText)).size();
            if (NumOfElements==1) {
                // If element is found, then click on text
                WebElement clickObject = driver.findElement(By.linkText(linkText));
                clickObject.click();
                if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                    System.out.println("Clicking on the element: " + linkText);
                } else {
                    localTest.get().info("Clicking on the element: " + linkText);
                }
                // If element is NOT found, then write error
            } else if (NumOfElements==0) {
                if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                    System.out.println("Could not find element: " + linkText);
                    Assert.fail();
                } else {
                    System.out.println("Could not find element: " + linkText);
                    localTest.get().fail("Could not find text: " + linkText);
                    UtilityHelpers.adhocScreenCapture(driver, "Could not find text: " + linkText, "Failure");
                    Assert.fail();
                }
            }
        } catch (Exception e) {

        }
    }

    public static void byIdClickTextValue(WebDriver driver, String textValue, String targetId) {
        try {
            // Find the number of elements present
            int NumOfElements = driver.findElements(By.id(targetId)).size();
            if (NumOfElements==1) {
                WebElement targetArea = driver.findElement(By.id(targetId));
                // If element is found, then click on text
                WebElement clickObject = targetArea.findElement(By.xpath("//div[contains(text(),'"+textValue+"')]"));
                clickObject.click();
                if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                    System.out.println("Clicking on the element: " + targetId);
                } else {
                    localTest.get().info("Clicking on the element: " + targetId);
                }
                // If element is NOT found, then write error
            } else if (NumOfElements==0) {
                if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                    System.out.println("Could not find element: " + targetId);
                    Assert.fail();
                } else {
                    System.out.println("Could not find element: " + targetId);
                    localTest.get().fail("Could not find text: " + targetId);
                    UtilityHelpers.adhocScreenCapture(driver, "Could not find text: " + textValue, "Failure");
                    Assert.fail();
                }
            }
        } catch (Exception e) {

        }
    }

    // ClickHelpers on a text value within a specified area
    public static void byXpathClickTextValue(WebDriver driver, String textValue, String targetXpath) {
        try {
            // Find the number of elements present
            int NumOfElements = driver.findElements(By.xpath(targetXpath)).size();
            if (NumOfElements==1) {
                WebElement targetArea = driver.findElement(By.xpath(targetXpath));
                // If element is found, then click on text
                WebElement clickObject = targetArea.findElement(By.xpath("//div[contains(text(),'"+textValue+"')]"));
                clickObject.click();
                if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                    System.out.println("Clicking on the element: " + targetXpath);
                } else {
                    localTest.get().info("Clicking on the element: " + targetXpath);
                }
                // If element is NOT found, then write error
            } else if (NumOfElements==0) {
                if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                    System.out.println("Could not find element: " + targetXpath);
                    Assert.fail();
                } else {
                    System.out.println("Could not find element: " + targetXpath);
                    localTest.get().fail("Could not find text: " + targetXpath);
                    UtilityHelpers.adhocScreenCapture(driver, "Could not find text: " + textValue, "Failure");
                    Assert.fail();
                }
            }
        } catch (Exception e) {

        }
    }


    // ClickHelpers on a text value within a specified area
    public static void byClassNameClickTextValue(WebDriver driver, String textValue, String targetClass) {
        try {
            // Find the number of elements present
            int NumOfElements = driver.findElements(By.className(targetClass)).size();
            if (NumOfElements==1) {
                WebElement targetArea = driver.findElement(By.className(targetClass));
                // If element is found, then click on text
                WebElement clickObject = targetArea.findElement(By.xpath("//div[contains(text(),'"+textValue+"')]"));
                clickObject.click();
                if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                    System.out.println("Clicking on the element: " + targetClass);
                } else {
                    localTest.get().info("Clicking on the element: " + targetClass);
                }
                // If element is NOT found, then write error
            } else if (NumOfElements==0) {
                if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                    System.out.println("Could not find element: " + targetClass);
                    Assert.fail();
                } else {
                    System.out.println("Could not find element: " + targetClass);
                    localTest.get().fail("Could not find text: " + targetClass);
                    UtilityHelpers.adhocScreenCapture(driver, "Could not find text: " + textValue, "Failure");
                    Assert.fail();
                }
            }
        } catch (Exception e) {

        }
    }

    // ClickHelpers on a text value within a specified area
    public static void byCssSelectorClickTextValue(WebDriver driver, String textValue, String targetCssSelector) {
        try {
            // Find the number of elements present
            int NumOfElements = driver.findElements(By.cssSelector(targetCssSelector)).size();
            if (NumOfElements==1) {
                WebElement targetArea = driver.findElement(By.cssSelector(targetCssSelector));
                // If element is found, then click on text
                WebElement clickObject = targetArea.findElement(By.cssSelector("//div[contains(text(),'"+textValue+"')]"));
                clickObject.click();
                if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                    System.out.println("Clicking on the element: " + targetCssSelector);
                } else {
                    localTest.get().info("Clicking on the element: " + targetCssSelector);
                }
                // If element is NOT found, then write error
            } else if (NumOfElements==0) {
                if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                    System.out.println("Could not find element: " + targetCssSelector);
                    Assert.fail();
                } else {
                    System.out.println("Could not find element: " + targetCssSelector);
                    localTest.get().fail("Could not find text: " + targetCssSelector);
                    UtilityHelpers.adhocScreenCapture(driver, "Could not find text: " + textValue, "Failure");
                    Assert.fail();
                }
            }
        } catch (Exception e) {

        }
    }

    // ClickHelpers on a text value within a specified area
    public static void byClassClickValue(WebDriver driver, String textValue, String targetClass) {
        // Added to prevent racing condition failure
        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(By.className(targetClass)));
        } catch (NotFoundException nfe) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find text: " + textValue);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find text: " + textValue);
                UtilityHelpers.adhocScreenCapture(driver, "Could not find text: " + textValue, "Failure");
                Assert.fail();
            }
        }
        System.out.println("Going to Click within the target area: " + targetClass);
        // Targets object specified within ObjectLibrary by name
        WebElement targetArea = driver.findElement(By.className(targetClass));

        // ClickHelpers on the specified option
        WebElement clickSelection = targetArea.findElement(By.xpath("//div[contains(value(),'"+textValue+"')]"));
        clickSelection.click();

        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
            System.out.println("Selecting the value '" + textValue + "' from: " + targetClass);
        } else {
            localTest.get().info("Selecting the value '" + textValue + "' from: " + targetClass);
        }
    }

}
