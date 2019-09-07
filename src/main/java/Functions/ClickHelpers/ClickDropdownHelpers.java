package Functions.ClickHelpers;

import ExtentReport.ExtentReportBuilder;

import Core.Configuration;
import Core.FileReadWrite;

import Functions.UtilityHelpers.UtilityHelpers;
import org.junit.Assert;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;


/**
 * This class allows for ClickHelpers events to interact with dropdown objects
 * Uses ObjectLibrary.xml for object references
 * Uses Datalibrary.xml for data references
 */


public class ClickDropdownHelpers extends ExtentReportBuilder {

    static String configTimeout = FileReadWrite.loadConfigurationProperties().getProperty("timeout");
    static int timeout = Integer.parseInt(configTimeout);

    // ClickHelpers on a dropdown selection using specified xpath
    public static void byXpathClickDropdown(WebDriver driver, String value, String dropdownTargetXpath) {
        try {
            WebElement dropdown = driver.findElement(By.xpath(dropdownTargetXpath));
            System.out.println("Tag Name:" + dropdown.getTagName());
            String dropdownTag = dropdown.getTagName();
            dropdown.click();
            switch (dropdownTag) {
                case "form":
                    try {
                        WebElement dropdownValueList = dropdown.findElement(By.tagName("ul"));
                        WebElement dropdownValueOptions = dropdownValueList.findElement(By.tagName("li"));
                        WebElement clickSelection = dropdownValueOptions.findElement(By.xpath("//li[contains(text(),'" + value + "')]"));
                        clickSelection.click();
                        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                            System.out.println("Select option: " + value);
                        } else {
                            localTest.get().info("Select option: " + value);
                        }
                    } catch (NotFoundException nfe) {
                        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                            System.out.println("Could not select option: " + value);
                            Assert.fail();
                        } else {
                            localTest.get().fail("Could not select option: " + value);
                            UtilityHelpers.adhocScreenCapture(driver, "Could not select option: " + value, "Failure");
                            Assert.fail();
                        }
                    }
                    break;
                case "select":
                    try {
                        Select select = new Select(dropdown);
                        select.selectByVisibleText(value);
                        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                            System.out.println("Select option: " + value);
                        } else {
                            localTest.get().info("Select option: " + value);
                        }
                    } catch (NotFoundException nfe) {
                        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                            System.out.println("Could not select option: " + value);
                            Assert.fail();
                        } else {
                            localTest.get().fail("Could not select option: " + value);
                            UtilityHelpers.adhocScreenCapture(driver, "Could not select option: " + value, "Failure");
                            Assert.fail();
                        }
                    }
                    break;
            }
        } catch (NotFoundException nfe) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + dropdownTargetXpath);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + dropdownTargetXpath);
                UtilityHelpers.adhocScreenCapture(driver, "Could not find element: " + dropdownTargetXpath, "Failure");
                Assert.fail();
            }
        } catch (ElementClickInterceptedException ecie) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not select option: " + value);
                Assert.fail();
            } else {
                localTest.get().fail("Could not select option: " + value);
                UtilityHelpers.adhocScreenCapture(driver, "Could not select option: " + value, "Failure");
                Assert.fail();
            }
        }
    }

    // ClickHelpers on a dropdown selection using specified Name
    public static void byNameClickDropdown(WebDriver driver, String value, String dropdownTargetName) {

        try {
            WebElement dropdown = driver.findElement(By.name(dropdownTargetName));
            System.out.println("Tag Name:" + dropdown.getTagName());
            String dropdownTag = dropdown.getTagName();
            dropdown.click();
            switch (dropdownTag) {
                case "form":
                    try {
                        WebElement dropdownValueList = dropdown.findElement(By.tagName("ul"));
                        WebElement dropdownValueOptions = dropdownValueList.findElement(By.tagName("li"));
                        WebElement clickSelection = dropdownValueOptions.findElement(By.xpath("//li[contains(text(),'" + value + "')]"));
                        clickSelection.click();
                        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                            System.out.println("Select option: " + value);
                        } else {
                            localTest.get().info("Select option: " + value);
                        }
                    } catch (NotFoundException nfe) {
                        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                            System.out.println("Could not select option: " + value);
                            Assert.fail();
                        } else {
                            localTest.get().fail("Could not select option: " + value);
                            UtilityHelpers.adhocScreenCapture(driver, "Could not select option: " + value, "Failure");
                            Assert.fail();
                        }
                    }
                    break;
                case "select":
                    try {
                        Select select = new Select(dropdown);
                        select.selectByVisibleText(value);
                        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                            System.out.println("Select option: " + value);
                        } else {
                            localTest.get().info("Select option: " + value);
                        }
                    } catch (NotFoundException nfe) {
                        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                            System.out.println("Could not select option: " + value);
                            Assert.fail();
                        } else {
                            localTest.get().fail("Could not select option: " + value);
                            UtilityHelpers.adhocScreenCapture(driver, "Could not select option: " + value, "Failure");
                            Assert.fail();
                        }
                    }
                    break;
            }
        } catch (NotFoundException nfe) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + dropdownTargetName);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + dropdownTargetName);
                UtilityHelpers.adhocScreenCapture(driver, "Could not find element: " + dropdownTargetName, "Failure");
                Assert.fail();
            }
        } catch (ElementClickInterceptedException ecie) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not select option: " + value);
                Assert.fail();
            } else {
                localTest.get().fail("Could not select option: " + value);
                UtilityHelpers.adhocScreenCapture(driver, "Could not select option: " + value, "Failure");
                Assert.fail();
            }
        }
    }

    // ClickHelpers on a dropdown selection using specified ID
    public static void byIdClickDropdown(WebDriver driver, String value, String dropdownTargetId) {

        try {
            WebElement dropdown = driver.findElement(By.id(dropdownTargetId));
            System.out.println("Tag Name:" + dropdown.getTagName());
            String dropdownTag = dropdown.getTagName();
            dropdown.click();
            switch (dropdownTag) {
                case "form":
                    try {
                        WebElement dropdownValueList = dropdown.findElement(By.tagName("ul"));
                        WebElement dropdownValueOptions = dropdownValueList.findElement(By.tagName("li"));
                        WebElement clickSelection = dropdownValueOptions.findElement(By.xpath("//li[contains(text(),'" + value + "')]"));
                        clickSelection.click();
                        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                            System.out.println("Select option: " + value);
                        } else {
                            localTest.get().info("Select option: " + value);
                        }
                    } catch (NotFoundException nfe) {
                        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                            System.out.println("Could not select option: " + value);
                            Assert.fail();
                        } else {
                            localTest.get().fail("Could not select option: " + value);
                            UtilityHelpers.adhocScreenCapture(driver, "Could not select option: " + value, "Failure");
                            Assert.fail();
                        }
                    }
                    break;
                case "select":
                    try {
                        Select select = new Select(dropdown);
                        select.selectByVisibleText(value);
                        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                            System.out.println("Select option: " + value);
                        } else {
                            localTest.get().info("Select option: " + value);
                        }
                    } catch (NotFoundException nfe) {
                        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                            System.out.println("Could not select option: " + value);
                            Assert.fail();
                        } else {
                            localTest.get().fail("Could not select option: " + value);
                            UtilityHelpers.adhocScreenCapture(driver, "Could not select option: " + value, "Failure");
                            Assert.fail();
                        }
                    }
                    break;
            }
        } catch (NotFoundException nfe) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + dropdownTargetId);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + dropdownTargetId);
                UtilityHelpers.adhocScreenCapture(driver, "Could not find element: " + dropdownTargetId, "Failure");
                Assert.fail();
            }
        } catch (ElementClickInterceptedException ecie) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not select option: " + value);
                Assert.fail();
            } else {
                localTest.get().fail("Could not select option: " + value);
                UtilityHelpers.adhocScreenCapture(driver, "Could not select option: " + value, "Failure");
                Assert.fail();
            }
        }
    }

    // ClickHelpers on a dropdown selection using specified CSS Selector
    public static void byCssSelectorClickDropdown(WebDriver driver, String value, String dropdownTargetCssSelector) {

        try {
            WebElement dropdown = driver.findElement(By.cssSelector(dropdownTargetCssSelector));
            System.out.println("Tag Name:" + dropdown.getTagName());
            String dropdownTag = dropdown.getTagName();
            dropdown.click();
            switch (dropdownTag) {
                case "form":
                    try {
                        WebElement dropdownValueList = dropdown.findElement(By.tagName("ul"));
                        WebElement dropdownValueOptions = dropdownValueList.findElement(By.tagName("li"));
                        WebElement clickSelection = dropdownValueOptions.findElement(By.xpath("//li[contains(text(),'" + value + "')]"));
                        clickSelection.click();
                        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                            System.out.println("Select option: " + value);
                        } else {
                            localTest.get().info("Select option: " + value);
                        }
                    } catch (NotFoundException nfe) {
                        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                            System.out.println("Could not select option: " + value);
                            Assert.fail();
                        } else {
                            localTest.get().fail("Could not select option: " + value);
                            UtilityHelpers.adhocScreenCapture(driver, "Could not select option: " + value, "Failure");
                            Assert.fail();
                        }
                    }
                    break;
                case "select":
                    try {
                        Select select = new Select(dropdown);
                        select.selectByVisibleText(value);
                        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                            System.out.println("Select option: " + value);
                        } else {
                            localTest.get().info("Select option: " + value);
                        }
                    } catch (NotFoundException nfe) {
                        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                            System.out.println("Could not select option: " + value);
                            Assert.fail();
                        } else {
                            localTest.get().fail("Could not select option: " + value);
                            UtilityHelpers.adhocScreenCapture(driver, "Could not select option: " + value, "Failure");
                            Assert.fail();
                        }
                    }
                    break;
            }
        } catch (NotFoundException nfe) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + dropdownTargetCssSelector);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + dropdownTargetCssSelector);
                UtilityHelpers.adhocScreenCapture(driver, "Could not find element: " + dropdownTargetCssSelector, "Failure");
                Assert.fail();
            }
        } catch (ElementClickInterceptedException ecie) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not select option: " + value);
                Assert.fail();
            } else {
                localTest.get().fail("Could not select option: " + value);
                UtilityHelpers.adhocScreenCapture(driver, "Could not select option: " + value, "Failure");
                Assert.fail();
            }
        }
    }

    // ClickHelpers on a dropdown selection using specified Class Name
    public static void byClassNameClickDropdown(WebDriver driver, String value, String dropdownTargetClassName) {

        try {
            WebElement dropdown = driver.findElement(By.className(dropdownTargetClassName));
            System.out.println("Tag Name:" + dropdown.getTagName());
            String dropdownTag = dropdown.getTagName();
            dropdown.click();
            switch (dropdownTag) {
                case "form":
                    try {
                        WebElement dropdownValueList = dropdown.findElement(By.tagName("ul"));
                        WebElement dropdownValueOptions = dropdownValueList.findElement(By.tagName("li"));
                        WebElement clickSelection = dropdownValueOptions.findElement(By.xpath("//li[contains(text(),'" + value + "')]"));
                        clickSelection.click();
                        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                            System.out.println("Select option: " + value);
                        } else {
                            localTest.get().info("Select option: " + value);
                        }
                    } catch (NotFoundException nfe) {
                        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                            System.out.println("Could not select option: " + value);
                            Assert.fail();
                        } else {
                            localTest.get().fail("Could not select option: " + value);
                            UtilityHelpers.adhocScreenCapture(driver, "Could not select option: " + value, "Failure");
                            Assert.fail();
                        }
                    }
                    break;
                case "select":
                    try {
                        Select select = new Select(dropdown);
                        select.selectByVisibleText(value);
                        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                            System.out.println("Select option: " + value);
                        } else {
                            localTest.get().info("Select option: " + value);
                        }
                    } catch (NotFoundException nfe) {
                        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                            System.out.println("Could not select option: " + value);
                            Assert.fail();
                        } else {
                            localTest.get().fail("Could not select option: " + value);
                            UtilityHelpers.adhocScreenCapture(driver, "Could not select option: " + value, "Failure");
                            Assert.fail();
                        }
                    }
                    break;
            }
        } catch (NotFoundException nfe) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + dropdownTargetClassName);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + dropdownTargetClassName);
                UtilityHelpers.adhocScreenCapture(driver, "Could not find element: " + dropdownTargetClassName, "Failure");
                Assert.fail();
            }
        } catch (ElementClickInterceptedException ecie) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not select option: " + value);
                Assert.fail();
            } else {
                localTest.get().fail("Could not select option: " + value);
                UtilityHelpers.adhocScreenCapture(driver, "Could not select option: " + value, "Failure");
                Assert.fail();
            }
        }
    }
}
