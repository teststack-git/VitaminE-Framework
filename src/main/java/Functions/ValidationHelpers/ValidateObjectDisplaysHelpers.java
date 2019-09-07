package Functions.ValidationHelpers;

import ExtentReport.ExtentReportBuilder;

import Core.Configuration;
import Core.FileReadWrite;

import Functions.UtilityHelpers.UtilityHelpers;
import com.aventstack.extentreports.Status;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * This class provides validation for the displaying of objects
 * Uses ObjectLibrary.xml for object references
 * Uses Datalibrary.xml for data references
 */


public class ValidateObjectDisplaysHelpers extends ExtentReportBuilder {

    static String configTimeout = FileReadWrite.loadConfigurationProperties().getProperty("timeout");
    static int timeout = Integer.parseInt(configTimeout);

    // Identifies the presence of an object on the screen by Xpath
    public static void byXpathElementIsPresent(WebDriver driver, String targetXpath) {
        try {
        System.out.println("Looking for the following object: " + targetXpath);

        // Finds specified object on page
        int NumOfElements = driver.findElements(By.xpath(targetXpath)).size();

            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                if (NumOfElements == 0) {
                    Assert.assertTrue(NumOfElements == 1);
                    System.out.println("Element not found");
                } else {
                    WebElement target = driver.findElement(By.xpath(targetXpath));
                    target.isDisplayed();
                    Assert.assertTrue(NumOfElements == 1);
                }
            } else {
                if (NumOfElements == 0) {
                    localTest.get().fail("The element' " + targetXpath +"' was NOT found.");
                    UtilityHelpers.adhocScreenCapture(driver, "Element not found.", "Failure");
                } else {
                    WebElement target = driver.findElement(By.xpath(targetXpath));
                    target.isDisplayed();
                    localTest.get().pass("The element '" + targetXpath +"' was found.");
                }

            }
        } catch (Exception te) {
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



    // Identifies the presence of an object on the screen by ID
    public static void byIdElementIsPresent(WebDriver driver, String targetId) {
        try {
            System.out.println("Looking for the following object: " + targetId);

            // Finds specified object on page
            int NumOfElements = driver.findElements(By.id(targetId)).size();

            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                if (NumOfElements == 0) {
                    Assert.assertTrue(NumOfElements == 1);
                    System.out.println("Element not found");
                } else {
                    WebElement target = driver.findElement(By.id(targetId));
                    target.isDisplayed();
                    Assert.assertTrue(NumOfElements == 1);
                }
            } else {
                if (NumOfElements == 0) {
                    localTest.get().fail("The element' " + targetId +"' was NOT found.");
                    UtilityHelpers.adhocScreenCapture(driver, "Element not found.", "Failure");
                } else {
                    WebElement target = driver.findElement(By.id(targetId));
                    target.isDisplayed();
                    localTest.get().pass("The element '" + targetId +"' was found.");
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


    // Identifies the presence of an object on the screen by Class Name
    public static void byClassNameElementIsPresent(WebDriver driver, String targetClassName){
        try {
            System.out.println("Looking for the following object: " + targetClassName);

            // Finds specified object on page
            int NumOfElements = driver.findElements(By.className(targetClassName)).size();

            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                if (NumOfElements == 0) {
                    Assert.assertTrue(NumOfElements == 1);
                    System.out.println("Element not found");
                } else {
                    WebElement target = driver.findElement(By.className(targetClassName));
                    target.isDisplayed();
                    Assert.assertTrue(NumOfElements == 1);
                }
            } else {
                if (NumOfElements == 0) {
                    localTest.get().fail("The element' " + targetClassName +"' was NOT found.");
                    UtilityHelpers.adhocScreenCapture(driver, "Element not found.", "Failure");
                } else {
                    WebElement target = driver.findElement(By.className(targetClassName));
                    target.isDisplayed();
                    localTest.get().pass("The element '" + targetClassName +"' was found.");
                }

            }
        } catch (TimeoutException te) {
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

    // Identifies the absence of an object on the screen by ID
    public static void byIdElementIsNOTPresen(WebDriver driver, String targetId){
        try {
            System.out.println("Looking for the following object: " + targetId);

            // Finds specified object on page
            int NumOfElements = driver.findElements(By.id(targetId)).size();

            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                if (NumOfElements == 0) {
                    Assert.assertTrue(NumOfElements == 1);
                    System.out.println("Element not found");
                } else {
                    WebElement target = driver.findElement(By.id(targetId));
                    target.isDisplayed();
                    Assert.assertTrue(NumOfElements == 1);

                }
            } else {
                if (NumOfElements == 0) {
                    localTest.get().pass("The element' " + targetId +"' was NOT found.");
                } else {
                    WebElement target = driver.findElement(By.id(targetId));
                    target.isDisplayed();
                    localTest.get().fail("The element '" + targetId +"' was found.");
                    UtilityHelpers.adhocScreenCapture(driver, "Element found.", "Failure");
                }

            }
        } catch (TimeoutException te) {
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

    // Identifies the absence of an object on the screen by Xpath
    public static void byXpathElementIsNOTPresent(WebDriver driver, String targetXpath) {
        try {
            System.out.println("Looking for the following object: " + targetXpath);

            // Finds specified object on page
            int NumOfElements = driver.findElements(By.xpath(targetXpath)).size();

            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                if (NumOfElements == 0) {
                    Assert.assertTrue(NumOfElements == 1);
                    System.out.println("Element not found");
                } else {
                    WebElement target = driver.findElement(By.xpath(targetXpath));
                    target.isDisplayed();
                    Assert.assertTrue(NumOfElements == 1);

                }
            } else {
                if (NumOfElements == 0) {
                    localTest.get().pass("The element' " + targetXpath +"' was NOT found.");
                } else {
                    WebElement target = driver.findElement(By.xpath(targetXpath));
                    target.isDisplayed();
                    localTest.get().fail("The element '" + targetXpath +"' was found.");
                    UtilityHelpers.adhocScreenCapture(driver, "Element found.", "Failure");
                }

            }
        } catch (TimeoutException te) {
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

    // Identifies the absence of an object on the screen by Xpath
    public static void byXpathElementIsNOTVisible(WebDriver driver, String targetXpath) {
        try {
            System.out.println("Looking for the following object: " + targetXpath);

            // Finds specified object on page
            WebElement element = driver.findElement(By.xpath(targetXpath));

            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                if (!element.isDisplayed()) {
                     Assert.assertTrue(!element.isDisplayed());
                    System.out.println("Element not found");
                } else {
                    element.isDisplayed();
                    Assert.assertTrue(!element.isDisplayed());
                    System.out.println("Element found");
                }
            } else {
                if (!element.isDisplayed()) {
                    localTest.get().pass("The element' " + targetXpath +"' was NOT visible.");
                } else {
                    element.isDisplayed();
                    localTest.get().fail("The element '" + targetXpath +"' was visible.");
                    UtilityHelpers.adhocScreenCapture(driver, "Element visible.", "Failure");
                }

            }
        } catch (TimeoutException te) {
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

    // Identifies the absence of an object on the screen by ID
    public static void byIdElementIsNOTVisible(WebDriver driver, String targetId) {
        try {
            System.out.println("Looking for the following object: " + targetId);

            // Finds specified object on page
            WebElement element = driver.findElement(By.id(targetId));

            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                if (!element.isDisplayed()) {
                     Assert.assertTrue(!element.isDisplayed());
                    System.out.println("Element not found");
                } else {
                    element.isDisplayed();
                    Assert.assertTrue(!element.isDisplayed());
                    System.out.println("Element found");
                }
            } else {
                if (!element.isDisplayed()) {
                    localTest.get().pass("The element' " + targetId +"' was NOT visible.");
                } else {
                    element.isDisplayed();
                    localTest.get().fail("The element '" + targetId +"' was visible.");
                    UtilityHelpers.adhocScreenCapture(driver, "Element visible.", "Failure");

                }

            }
        } catch (TimeoutException te) {
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

    // Identifies the presence of an object on the screen by CSS Selector
    public static void byCssSelectorElementIsPresent(WebDriver driver, String targetCssSelector){
        try {
            System.out.println("Looking for the following object: " + targetCssSelector);

            // Finds specified object on page
            int NumOfElements = driver.findElements(By.cssSelector(targetCssSelector)).size();

            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                if (NumOfElements == 0) {
                     Assert.assertTrue(NumOfElements == 1);
                    System.out.println("Element not found");
                } else {
                    WebElement target = driver.findElement(By.cssSelector(targetCssSelector));
                    Assert.assertTrue(NumOfElements == 1);
                    target.isDisplayed();
                }
            } else {
                if (NumOfElements == 0) {
                    localTest.get().fail("The CSS Selector '" + targetCssSelector +"' was NOT found.");
                    UtilityHelpers.adhocScreenCapture(driver, "CSS Selector not found.", "Failure");
                } else {
                    WebElement target = driver.findElement(By.cssSelector(targetCssSelector));
                    target.isDisplayed();
                    localTest.get().pass("The CSS Selector '" + targetCssSelector +"' was found.");
                }

            }
        } catch (TimeoutException te) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + targetCssSelector);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + targetCssSelector);
                UtilityHelpers.adhocScreenCapture(driver, "Element not found.", "Failure");
                Assert.fail();
            }
        }
    }

    // Return True or False if Element identified by Xpath is displayed
    public static Boolean boolByXpathElementIsDisplayed(WebDriver driver, String targetXpath) {
        boolean found = false;
        try {
            new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(targetXpath)));
        } catch (Exception e) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + targetXpath);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + targetXpath);
                UtilityHelpers.adhocScreenCapture(driver, "Element not found.", "Failure");
                Assert.fail();
            }
        }
        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
        System.out.println("Verifying if element is present with Xpath: " + targetXpath);
        } else {
            localTest.get().info("Verifying if element is present with Xpath: " + targetXpath);
        }

        try {

            if (driver.findElement(By.xpath(targetXpath)).isDisplayed()){
                if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                    System.out.println("Element with Xpath '" + targetXpath + "' is displayed");
                } else {
                    localTest.get().log(Status.PASS, "Element with Xpath '" + targetXpath + "' is displayed");
                    found = true;
                }
            } else {
                if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                    System.out.println("Element with Xpath '" + targetXpath + "' is NOT displayed");
                } else {
                    localTest.get().log(Status.FAIL, "Element with Xpath '" + targetXpath + "' is NOT displayed");
                    UtilityHelpers.adhocScreenCapture(driver, "Element is not displayed.", "Failure");
                    Assert.fail();
                    }
                }
        } catch (Exception e) {
            Assert.fail();
        }

        return found;

    }

    // Return True or False if Element identified by Id is displayed
    public static Boolean boolByIdElementIsDisplayed(WebDriver driver, String targetId) {
        boolean found = false;

        try {
            new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(By.id(targetId)));
        } catch (Exception e) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + targetId);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + targetId);
                UtilityHelpers.adhocScreenCapture(driver, "Element not found.", "Failure");
                Assert.fail();
            }
        }

        try {
            if (driver.findElement(By.id(targetId)).isDisplayed()){
                if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                    System.out.println("Element with Id '" + targetId + "' is displayed");
                } else {
                    localTest.get().log(Status.PASS, "Element with Id '" + targetId + "' is displayed");
                    found = true;
                }
            } else {
                if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                    System.out.println("Element with Id '" + targetId + "' is NOT displayed");
                } else {
                    localTest.get().log(Status.FAIL, "Element with Id '" + targetId + "' is NOT displayed");
                    UtilityHelpers.adhocScreenCapture(driver, "Element is not displayed.", "Failure");
                    Assert.fail();
                }
            }
        } catch (Exception e) {
            Assert.fail();
        }

        return found;

    }

    // Return True or False if Element identified by CssSelector is displayed
    public static Boolean boolByCssSelectorElementIsDisplayed(WebDriver driver, String targetCssSelector) {
        boolean found = false;

        try {
            new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(targetCssSelector)));
        } catch (Exception e) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + targetCssSelector);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + targetCssSelector);
                UtilityHelpers.adhocScreenCapture(driver, "Element not found.", "Failure");
                Assert.fail();
            }
        }

        try {
            if (driver.findElement(By.cssSelector(targetCssSelector)).isDisplayed()){
                if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                    System.out.println("Element with CssSelector '" + targetCssSelector + "' is displayed");
                } else {
                    localTest.get().log(Status.PASS, "Element with CssSelector '" + targetCssSelector + "' is displayed");
                    found = true;
                }
            } else {
                if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                    System.out.println("Element with CssSelector '" + targetCssSelector + "' is NOT displayed");
                } else {
                    localTest.get().log(Status.FAIL, "Element with CssSelector '" + targetCssSelector + "' is NOT displayed");
                    UtilityHelpers.adhocScreenCapture(driver, "Element is not displayed.", "Failure");
                    Assert.fail();
                }
            }
        } catch (Exception e) {
            Assert.fail();
        }

        return found;

    }

    // Return True or False if Element identified by Name is displayed
    public static Boolean boolByNameElementIsDisplayed(WebDriver driver, String targetName) {
        boolean found = false;

        try {
            new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(By.name(targetName)));
        } catch (Exception e) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + targetName);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + targetName);
                UtilityHelpers.adhocScreenCapture(driver, "Element not found.", "Failure");
                Assert.fail();
            }
        }

        try {
            if (driver.findElement(By.name(targetName)).isDisplayed()){
                if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                    System.out.println("Element with Name '" + targetName + "' is displayed");
                } else {
                    localTest.get().log(Status.PASS, "Element with Name '" + targetName + "' is displayed");
                    found = true;
                }
            } else {
                if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                    System.out.println("Element with Name '" + targetName + "' is NOT displayed");
                } else {
                    localTest.get().log(Status.FAIL, "Element with Name '" + targetName + "' is NOT displayed");
                    UtilityHelpers.adhocScreenCapture(driver, "Element is not displayed.", "Failure");
                    Assert.fail();
                }
            }
        } catch (Exception e) {
            Assert.fail();
        }

        return found;

    }

    // Return True or False if Element identified by Class Name is displayed
    public static Boolean boolByClassNameElementIsDisplayed(WebDriver driver, String targetClassName) {
        boolean found = false;

        try {
            new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(By.className(targetClassName)));
        } catch (Exception e) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + targetClassName);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + targetClassName);
                UtilityHelpers.adhocScreenCapture(driver, "Element not found.", "Failure");
                Assert.fail();
            }
        }

        try {
            if (driver.findElement(By.className(targetClassName)).isDisplayed()){
                if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                    System.out.println("Element with Class Name '" + targetClassName + "' is displayed");
                } else {
                    localTest.get().log(Status.PASS, "Element with Class Name '" + targetClassName + "' is displayed");
                    found = true;
                }
            } else {
                if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                    System.out.println("Element with Class Name '" + targetClassName + "' is NOT displayed");
                    Assert.fail();
                } else {
                    localTest.get().log(Status.FAIL, "Element with Class Name '" + targetClassName + "' is NOT displayed");
                    UtilityHelpers.adhocScreenCapture(driver, "Element is not displayed.", "Failure");
                    Assert.fail();
                }
            }
        } catch (Exception e) {
        }

        return found;

    }

    // Return True or False if Element identified by Tagname is displayed
    public static Boolean boolByTagnameElementIsDisplayed(WebDriver driver, String targetName) {
        boolean found = false;

        try {
            new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(By.tagName(targetName)));
        } catch (Exception e) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + targetName);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + targetName);
                UtilityHelpers.adhocScreenCapture(driver, "Element not found.", "Failure");
                Assert.fail();
            }
        }

        try {
            if (driver.findElement(By.tagName(targetName)).isDisplayed()){
                if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                    System.out.println("Element with Tagname '" + targetName + "' is displayed");
                } else {
                    localTest.get().log(Status.PASS, "Element with Tagname '" + targetName + "' is displayed");
                    found = true;
                }
            } else {
                if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                    System.out.println("Element with Tagname '" + targetName + "' is NOT displayed");
                    Assert.fail();
                } else {
                    localTest.get().log(Status.FAIL, "Element with Tagname '" + targetName + "' is NOT displayed");
                    UtilityHelpers.adhocScreenCapture(driver, "Element is not displayed.", "Failure");
                    Assert.fail();
                }
            }
        } catch (Exception e) {
            Assert.fail();
        }

        return found;

    }


}
