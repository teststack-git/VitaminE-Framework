package Functions.ValidationHelpers;

import ExtentReport.ExtentReportBuilder;

import Core.FileReadWrite;
import Core.Configuration;

import Functions.UtilityHelpers.UtilityHelpers;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.assertj.core.api.Assertions.assertThat;

import static org.testng.Assert.assertEquals;

/**
 * This class allows for the validation of text fields
 * Uses ObjectLibrary.xml for object references
 * Uses Datalibrary.xml for data references
 */


public class ValidateTextFieldsHelpers extends ExtentReportBuilder {

    static String configTimeout = FileReadWrite.loadConfigurationProperties().getProperty("timeout");
    static int timeout = Integer.parseInt(configTimeout);


    // Verifies the "clear" button (x) within text fields does not display
    public static void byIdNotClearableValidation(WebDriver driver, String textFieldId) {

        //  Find the targeted text field that will be cleared
        WebElement targetTextField = driver.findElement(By.id(textFieldId));

        // Stores the class of the text field prior the clicking of the "Not Clearable" checkbox
        String defaultFieldClass = targetTextField.getAttribute("class");
        // Invokes the clear button (x) within the text field
        targetTextField.sendKeys("a");

        // Stores the class of the text field after the "Not Clearable" checkbox has been selected
        String notClearableFieldClass = targetTextField.getAttribute("class");

        System.out.println("Not clearable field class: " + notClearableFieldClass);
        System.out.println("Default field class: " + defaultFieldClass);

        //Asserts that the target text field does NOT contain the word 'clearable' within its class
        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
            try {
                if (!targetTextField.getAttribute("class").contains("clearable")) {
                    assertThat(!targetTextField.getAttribute("class").contains("clearable"));
                } else {
                    assertThat(!targetTextField.getAttribute("class").contains("clearable"));
                }
            } catch (Exception e) {
                e.printStackTrace();
                Assert.fail();
            }
        } else {
            try {
                if (!targetTextField.getAttribute("class").contains("clearable")) {
                    localTest.get().pass("The following element is not clearable: " + textFieldId);

                } else {
                    localTest.get().fail("The following element is clearable: " + textFieldId);
                    UtilityHelpers.adhocScreenCapture(driver, "Field is not clearable.", "Failure");
                    Assert.fail();
                }
            } catch (Exception e) {
                if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                    System.out.println("Field not found: " + textFieldId);
                    Assert.fail();
                } else {
                    localTest.get().fail("Field not found: " + textFieldId);
                    UtilityHelpers.adhocScreenCapture(driver, "Field not found.", "Failure");
                    Assert.fail();
                }
            }
        }
    }


    // Verify text is case insensitive
    public static void byXpathCaseInsensitiveValidation(WebDriver driver, String targetXpath, String comparedText) {

        // Find the target
        WebElement targetTextField = driver.findElement(By.xpath(targetXpath));

        // Get text from field
        String textFieldValue = targetTextField.getAttribute("value");
        System.out.println(textFieldValue);

        // Assert that both Case Insensitive and Case Sensitive values are contained within the results
        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
            try {
                if (textFieldValue.equalsIgnoreCase(comparedText)) {
                    assertThat(textFieldValue.equalsIgnoreCase(comparedText));
                } else {
                    assertThat(textFieldValue.equalsIgnoreCase(comparedText));
                }
            } catch (Exception e) {
                Assert.fail();
            }
        } else {
            try {
                if (textFieldValue.equalsIgnoreCase(comparedText)) {
                    localTest.get().pass("The following element is case insensitive: " + targetXpath);
                } else {
                    localTest.get().fail("The following element is case sensitive: " + targetXpath);
                    UtilityHelpers.adhocScreenCapture(driver, "Case is sensitive.", "Failure");
                    Assert.fail();
                }
            } catch (Exception e) {
                if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                    System.out.println("Field not found: " + targetXpath);
                    Assert.fail();
                } else {
                    localTest.get().fail("Field not found: " + targetXpath);
                    UtilityHelpers.adhocScreenCapture(driver, "Field not found.", "Failure");
                    Assert.fail();
                }
            }
        }
    }

    // Verify text is case insensitive
    public static void byIdCaseInsensitiveValidation(WebDriver driver, String targetId, String comparedText){

        // Find the target
        WebElement targetTextField = driver.findElement(By.xpath(targetId));

        // Get text from field
        String textFieldValue = targetTextField.getAttribute("value");
        System.out.println(textFieldValue);

        // Assert that both Case Insensitive and Case Sensitive values are contained within the results
        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
            try {
                if (textFieldValue.equalsIgnoreCase(comparedText)) {
                    assertThat(textFieldValue.equalsIgnoreCase(comparedText));
                } else {
                    assertThat(textFieldValue.equalsIgnoreCase(comparedText));
                }
            } catch (Exception e) {
                e.printStackTrace();

            }
        } else {
            try {
                if (textFieldValue.equalsIgnoreCase(comparedText)) {
                    localTest.get().pass("The following element is case insensitive: " + targetId);
                } else {
                    localTest.get().fail("The following element is case sensitive: " + targetId);
                    UtilityHelpers.adhocScreenCapture(driver, "Case is sensitive.", "Failure");
                    Assert.fail();
                }
            } catch (Exception e) {
                if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                    System.out.println("Field not found: " + targetId);
                    Assert.fail();
                } else {
                    localTest.get().fail("Field not found: " + targetId);
                    UtilityHelpers.adhocScreenCapture(driver, "Field not found.", "Failure");
                    Assert.fail();
                }
            }
        }
    }


    // Verify text is case sensitive
    public static void byXpathCaseSensitiveValidation(WebDriver driver, String targetXpath, String comparedText) {

        // Find the target
        WebElement targetTextField = driver.findElement(By.xpath(targetXpath));

        // Get text from field
        String textFieldValue = targetTextField.getAttribute("value");
        System.out.println(textFieldValue);

        // Assert that Case Sensitive values are contained within the results
        // Assert that both Case Insensitive and Case Sensitive values are contained within the results
        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
            try {
                if (textFieldValue.equals(comparedText)) {
                    assertThat(textFieldValue.equals(comparedText));
                } else {
                    assertThat(textFieldValue.equals(comparedText));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                if (textFieldValue.equals(comparedText)) {
                    localTest.get().pass("The following element is case sensitive: " + targetXpath);
                } else {
                    localTest.get().fail("The following element is case insensitive: " + targetXpath);
                    UtilityHelpers.adhocScreenCapture(driver, "Case is insensitive.", "Failure");
                    Assert.fail();
                }
            } catch (Exception e) {
                if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                    System.out.println("Field not found: " + targetXpath);
                    Assert.fail();
                } else {
                    localTest.get().fail("Field not found: " + targetXpath);
                    UtilityHelpers.adhocScreenCapture(driver, "Field not found.", "Failure");
                    Assert.fail();
                }
            }
        }
    }

    // Verify text is case sensitive
    public static void byIdCaseSensitiveValidation(WebDriver driver, String targetId, String comparedText) {

        // Find the target
        WebElement targetTextField = driver.findElement(By.id(targetId));

        // Get text from field
        String textFieldValue = targetTextField.getAttribute("value");
        System.out.println(textFieldValue);

        // Assert that Case Sensitive values are contained within the results
        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
            try {
                if (textFieldValue.equals(comparedText)) {
                    assertThat(textFieldValue.equals(comparedText));
                } else {
                    assertThat(textFieldValue.equals(comparedText));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                if (textFieldValue.equals(comparedText)) {
                    localTest.get().pass("The following element is case sensitive: " + targetId);
                } else {
                    localTest.get().fail("The following element is case insensitive: " + targetId);
                    UtilityHelpers.adhocScreenCapture(driver, "Case is insensitive.", "Failure");
                    Assert.fail();
                }
            } catch (Exception e) {
                if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                    System.out.println("Field not found: " + targetId);
                    Assert.fail();
                } else {
                    localTest.get().fail("Field not found: " + targetId);
                    UtilityHelpers.adhocScreenCapture(driver, "Field not found.", "Failure");
                    Assert.fail();
                }
            }
        }
    }

    // TextHelpers output placeholder will match Placeholder text field
    public static void byXpathPlaceholderValidation(WebDriver driver, String placeholderFieldXpath, String expectedTextValue) {

        // Define the placeholder field
        WebElement placeholder = driver.findElement(By.xpath(placeholderFieldXpath));

        // Store text field Placeholder attribute
        String placeholderFieldValue = placeholder.getAttribute("placeholder");

        // Write to console placeholder text
        System.out.println("Placeholder text: " + placeholderFieldValue);

        // Validate the placeholder text equals the output field placeholder text
        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
            try {
                if (placeholderFieldValue.equals(expectedTextValue)) {
                    assertEquals(placeholderFieldValue, expectedTextValue);
                } else {
                    assertEquals(placeholderFieldValue, expectedTextValue);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                if (placeholderFieldValue.equals(expectedTextValue)) {
                    localTest.get().pass("Validate the following placeholder '" + placeholderFieldXpath + "' has value of '" + expectedTextValue + "'");
                } else {
                    localTest.get().fail("Validate the following placeholder '" + placeholderFieldXpath + "' has value of '" + expectedTextValue + "'");
                    UtilityHelpers.adhocScreenCapture(driver, "Placeholder text is incorrect.", "Failure");
                    Assert.fail();
                }
            } catch (Exception e) {
                if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                    System.out.println("Field not found: " + placeholderFieldXpath);
                    Assert.fail();
                } else {
                    localTest.get().fail("Field not found: " + placeholderFieldXpath);
                    UtilityHelpers.adhocScreenCapture(driver, "Field not found.", "Failure");
                    Assert.fail();
                }
            }
        }
    }

    // TextHelpers output placeholder will match Placeholder text field
    public static void byIdPlaceholderValidation(WebDriver driver, String placeholderFieldId, String expectedTextValue) {

        // Define the placeholder field
        WebElement placeholder = driver.findElement(By.id(placeholderFieldId));

        // Store text field Placeholder attribute
        String placeholderFieldValue = placeholder.getAttribute("placeholder");

        // Write to console placeholder text
        System.out.println("Placeholder text is: " + placeholderFieldValue);

        // Validate the placeholder text equals the output field placeholder text
        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
            try {
                if (placeholderFieldValue.equals(expectedTextValue)) {
                    assertEquals(placeholderFieldValue, expectedTextValue);
                } else {
                    assertEquals(placeholderFieldValue, expectedTextValue);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                if (placeholderFieldValue.equals(expectedTextValue)) {
                    localTest.get().pass("Validate the following placeholder '" + placeholderFieldId + "' has value of '" + expectedTextValue + "'");
                } else {
                    localTest.get().fail("Validate the following placeholder '" + placeholderFieldId + "' has value of '" + expectedTextValue + "'");
                    UtilityHelpers.adhocScreenCapture(driver, "Placeholder text is incorrect.", "Failure");
                    Assert.fail();
                }
            } catch (Exception e) {
                if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                    System.out.println("Field not found: " + placeholderFieldId);
                    Assert.fail();
                } else {
                    localTest.get().fail("Field not found: " + placeholderFieldId);
                    UtilityHelpers.adhocScreenCapture(driver, "Field not found.", "Failure");
                    Assert.fail();
                }
            }
        }
    }


    // Verify the width of an element
    public static void byXpathElementWidthValidation(WebDriver driver, String targetXpath, String expectedWidthSizeValue) {

        // Define the element
        WebElement targetElementWidth = driver.findElement(By.xpath(targetXpath));

        // Identify the width value of the target element
        String targetElemenWidthValue = targetElementWidth.getAttribute("width");

        System.out.println("The elements actual width is :" + targetElemenWidthValue);

        // Verify the elements actual width is the same as the expected width
        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
            try {
                if (expectedWidthSizeValue.equals(targetElemenWidthValue)) {
                    assertEquals(expectedWidthSizeValue, targetElemenWidthValue);
                } else {
                    assertEquals(expectedWidthSizeValue, targetElemenWidthValue);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                if (expectedWidthSizeValue.equals(targetElemenWidthValue)) {
                    localTest.get().pass("The element '" + targetXpath + "' has a width of '" + expectedWidthSizeValue + "'");
                } else {
                    localTest.get().fail("The element '" + targetXpath + "' has a width of '" + expectedWidthSizeValue + "'");
                    UtilityHelpers.adhocScreenCapture(driver, "Field width does not match expected width.", "Failure");
                    Assert.fail();
                }
            } catch (Exception e) {
                if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                    System.out.println("Field not found: " + targetXpath);
                    Assert.fail();
                } else {
                    localTest.get().fail("Field not found: " + targetXpath);
                    UtilityHelpers.adhocScreenCapture(driver, "Field not found.", "Failure");
                    Assert.fail();
                }
            }
        }
    }

    // Verify the width of an element
    public static void byIdElementWidthValidation(WebDriver driver, String targetId, String expectedWidthSizeValue) {

        // Define the element
        WebElement targetElementWidth = driver.findElement(By.id(targetId));

        // Identify the width value of the target element
        String targetElemenWidthValue = targetElementWidth.getAttribute("width");

        System.out.println("The elements actual width is :" + targetElemenWidthValue);

        // Verify the elements actual width is the same as the expected width
        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
            try {
                if (expectedWidthSizeValue.equals(targetElemenWidthValue)) {
                    assertEquals(expectedWidthSizeValue, targetElemenWidthValue);
                } else {
                    assertEquals(expectedWidthSizeValue, targetElemenWidthValue);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                if (expectedWidthSizeValue.equals(targetElemenWidthValue)) {
                    localTest.get().pass("The element '" + targetId + "' has a width of '" + expectedWidthSizeValue + "'");
                } else {
                    localTest.get().fail("The element '" + targetId + "' has a width of '" + expectedWidthSizeValue + "'");
                    UtilityHelpers.adhocScreenCapture(driver, "Element width does not match expected width.", "Failure");
                    Assert.fail();
                }
            } catch (Exception e) {
                if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                    System.out.println("Field not found: " + targetId);
                    Assert.fail();
                } else {
                    localTest.get().fail("Field not found: " + targetId);
                    UtilityHelpers.adhocScreenCapture(driver, "Field not found.", "Failure");
                    Assert.fail();
                }
            }
        }
    }

    // Verify the text field is disabled
    public static void byXpathDisableFieldValidation(WebDriver driver, String targetXpath) {

        // Define the output text field
        WebElement target = driver.findElement(By.xpath(targetXpath));

        // Identifies the targets attribute
        String targetDisabled = target.getAttribute("disabled");
        System.out.println("Checking if the following text field is disabled: " + targetXpath);

        // Verifies the target contains the "disabled" attribute
        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
            try {
                if (targetDisabled.contains("true")) {
                    assertThat(targetDisabled).contains("true");
                } else {
                    assertThat(targetDisabled).contains("true");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                if (targetDisabled.contains("true")) {
                    localTest.get().pass("The following element is disabled: " + targetXpath);
                } else {
                    localTest.get().fail("The following element is NOT disabled: " + targetXpath);
                    UtilityHelpers.adhocScreenCapture(driver, "Element is NOT disabled.", "Failure");
                    Assert.fail();
                }
            } catch (Exception e) {
                if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                    System.out.println("Field not found: " + targetXpath);
                    Assert.fail();
                } else {
                    localTest.get().fail("Field not found: " + targetXpath);
                    UtilityHelpers.adhocScreenCapture(driver, "Field not found.", "Failure");
                    Assert.fail();
                }
            }
        }
    }

    // Verify the text field is disabled
    public static void byIdDisableFieldValidation(WebDriver driver, String targetId) {

        // Define the output text field
        WebElement target = driver.findElement(By.id(targetId));

        // Identifies the targets attribute
        String targetDisabled = target.getAttribute("disabled");

        // Verifies the target contains the "disabled" attribute
        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
            try {
                if (targetDisabled.contains("true")) {
                    assertThat(targetDisabled).contains("true");
                } else {
                    assertThat(targetDisabled).contains("true");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                if (targetDisabled.contains("true")) {
                    localTest.get().pass("The following element is disabled: " + targetId);
                } else {
                    localTest.get().fail("The following element is NOT disabled: " + targetId);
                    UtilityHelpers.adhocScreenCapture(driver, "Element is NOT disabled.", "Failure");
                    Assert.fail();
                }
            } catch (Exception e) {
                if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                    System.out.println("Field not found: " + targetId);
                    Assert.fail();
                } else {
                    localTest.get().fail("Field not found: " + targetId);
                    UtilityHelpers.adhocScreenCapture(driver, "Field not found.", "Failure");
                    Assert.fail();
                }
            }
        }
    }

    // Verify the height of an element
    public static void byXpathElementHeightValidation(WebDriver driver, String targetXpath, String expectedHeightSizeValue) {

        // Define the element
        WebElement targetElementWidth = driver.findElement(By.xpath(targetXpath));

        // Identify the height value of the target element
        String targetElementHeightValue = targetElementWidth.getAttribute("height");

        System.out.println("The elements actual height is :" + targetElementHeightValue);

        // Verify the elements actual height is the same as the expected height

        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
            try {
                if (expectedHeightSizeValue.equals(targetElementHeightValue)) {
                    assertEquals(expectedHeightSizeValue, targetElementHeightValue);
                } else {
                    assertEquals(expectedHeightSizeValue, targetElementHeightValue);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                if (expectedHeightSizeValue.equals(targetElementHeightValue)) {
                    localTest.get().pass("The element '" + targetXpath + "' has a height of '" + expectedHeightSizeValue + "'");
                } else {
                    localTest.get().fail("The element '" + targetXpath + "' does NOT have a height of '" + expectedHeightSizeValue + "'");
                    UtilityHelpers.adhocScreenCapture(driver, "Element height does not match expected height.", "Failure");
                    Assert.fail();
                }
            } catch (Exception e) {
                if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                    System.out.println("Field not found: " + targetXpath);
                    Assert.fail();
                } else {
                    localTest.get().fail("Field not found: " + targetXpath);
                    UtilityHelpers.adhocScreenCapture(driver, "Field not found.", "Failure");
                    Assert.fail();
                }
            }
        }
    }


    // Verify the height of an element
    public static void byIdElementHeightValidation(WebDriver driver, String targetId, String expectedHeightSizeValue) {

        // Define the element
        WebElement targetElementWidth = driver.findElement(By.id(targetId));

        // Identify the height value of the target element
        String targetElementHeightValue = targetElementWidth.getAttribute("height");

        System.out.println("The elements actual height is :" + targetElementHeightValue);

        // Verify the elements actual height is the same as the expected height
        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
            try {
                if (expectedHeightSizeValue.equals(targetElementHeightValue)) {
                    assertEquals(expectedHeightSizeValue, targetElementHeightValue);
                } else {
                    assertEquals(expectedHeightSizeValue, targetElementHeightValue);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                if (expectedHeightSizeValue.equals(targetElementHeightValue)) {
                    localTest.get().pass("The element '" + targetId + "' has a height of '" + expectedHeightSizeValue + "'");
                } else {
                    localTest.get().fail("The element '" + targetId + "' does NOT have a height of '" + expectedHeightSizeValue + "'");
                    UtilityHelpers.adhocScreenCapture(driver, "Element height does not match expected height.", "Failure");
                    Assert.fail();
                }
            } catch (Exception e) {
                if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                    System.out.println("Field not found: " + targetId);
                    Assert.fail();
                } else {
                    localTest.get().fail("Field not found: " + targetId);
                    UtilityHelpers.adhocScreenCapture(driver, "Field not found.", "Failure");
                    Assert.fail();
                }
            }
        }
    }

    // Verify the element is in read-only status
    public static void byXpathReadOnlyValidation(WebDriver driver, String targetXpath) {

        // Define the output text field
        WebElement target = driver.findElement(By.xpath(targetXpath));

        // Identifies the targets attribute
        String targetReadOnly = target.getAttribute("class");
        System.out.println("Checking if the following element is read-only: " + targetXpath);

        // Verifies the target contains the "read-only" attribute
        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
            try {
                if (targetReadOnly.contains("twrte_disabled")) {
                    assertThat(targetReadOnly).contains("twrte_disabled");
                } else {
                    assertThat(targetReadOnly).contains("twrte_disabled");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                if (targetReadOnly.contains("twrte_disabled")) {
                    localTest.get().pass("The following element is read-only: " + targetXpath);
                } else {
                    localTest.get().fail("The following element is NOT read-only: " + targetXpath);
                    UtilityHelpers.adhocScreenCapture(driver, "Element is NOT read-only.", "Failure");
                    Assert.fail();
                }
            } catch (Exception e) {
                if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                    System.out.println("Field not found: " + targetXpath);
                    Assert.fail();
                } else {
                    localTest.get().fail("Field not found: " + targetXpath);
                    UtilityHelpers.adhocScreenCapture(driver, "Field not found.", "Failure");
                    Assert.fail();
                }
            }
        }
    }

    // Verify the element is in read-only status
    public static void byIdReadOnlyValidation(WebDriver driver, String targetId) {

        // Define the output text field
        WebElement target = driver.findElement(By.id(targetId));

        // Identifies the targets attribute
        String targetReadOnly = target.getAttribute("class");
        System.out.println("Checking if the following element is read-only: " + targetId);

        // Verifies the target contains the "read-only" attribute
        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
            try {
                if (targetReadOnly.contains("twrte_disabled")) {
                    assertThat(targetReadOnly).contains("twrte_disabled");
                } else {
                    assertThat(targetReadOnly).contains("twrte_disabled");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                if (targetReadOnly.contains("twrte_disabled")) {
                    localTest.get().pass("The following element is read-only: " + targetId);
                } else {
                    localTest.get().fail("The following element is NOT read-only: " + targetId);
                    UtilityHelpers.adhocScreenCapture(driver, "Element is NOT read-only.", "Failure");
                    Assert.fail();
                }
            } catch (Exception e) {
                if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                    System.out.println("Field not found: " + targetId);
                    Assert.fail();
                } else {
                    localTest.get().fail("Field not found: " + targetId);
                    UtilityHelpers.adhocScreenCapture(driver, "Field not found.", "Failure");
                    Assert.fail();
                }
            }
        }
    }

    // Verify the element is in required status
    public static void byXpathRequiredValidation(WebDriver driver, String targetXpath) {

        // Define the output text field
        WebElement target = driver.findElement(By.xpath(targetXpath));

        // Identifies the targets attribute
        String targetRequired = target.getAttribute("class");
        System.out.println("Checking if the following element is required: " + targetXpath);

        // Verifies the target contains the "required" attribute
        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
            try {
                if (targetRequired.contains("field-invalid")) {
                    assertThat(targetRequired).contains("field-invalid");
                } else {
                    assertThat(targetRequired).contains("field-invalid");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                if (targetRequired.contains("field-invalid")) {
                    localTest.get().pass("The following element is required: " + targetXpath);
                } else {
                    localTest.get().fail("The following element is NOT required: " + targetXpath);
                    UtilityHelpers.adhocScreenCapture(driver, "Element is NOT required.", "Failure");
                    Assert.fail();
                }
            } catch (Exception e) {
                if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                    System.out.println("Field not found: " + targetXpath);
                    Assert.fail();
                } else {
                    localTest.get().fail("Field not found: " + targetXpath);
                    UtilityHelpers.adhocScreenCapture(driver, "Field not found.", "Failure");
                    Assert.fail();
                }
            }
        }
    }

    // Verify the element is in required status
    public static void byIdRequiredValidation(WebDriver driver, String targetId) {

        // Define the output text field
        WebElement target = driver.findElement(By.id(targetId));

        // Identifies the targets attribute
        String targetRequired = target.getAttribute("class");
        System.out.println("Checking if the following element is required: " + targetId);

        // Verifies the target contains the "required" attribute
        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
            try {
                if (targetRequired.contains("field-invalid")) {
                    assertThat(targetRequired).contains("field-invalid");
                } else {
                    assertThat(targetRequired).contains("field-invalid");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                if (targetRequired.contains("field-invalid")) {
                    localTest.get().pass("The following element is required: " + targetId);
                } else {
                    localTest.get().fail("The following element is NOT required: " + targetId);
                    UtilityHelpers.adhocScreenCapture(driver, "Element is NOT required.", "Failure");
                    Assert.fail();
                }
            } catch (Exception e) {
                if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                    System.out.println("Field not found: " + targetId);
                    Assert.fail();
                } else {
                    localTest.get().fail("Field not found: " + targetId);
                    UtilityHelpers.adhocScreenCapture(driver, "Field not found.", "Failure");
                    Assert.fail();
                }
            }
        }
    }


    // Verify the element does NOT have a required status
    public static void byXpathNotRequiredValidation(WebDriver driver, String targetXpath) {

        // Define the output text field
        WebElement target = driver.findElement(By.xpath(targetXpath));

        // Identifies the targets attribute
        String targetRequired = target.getAttribute("class");
        System.out.println("Checking if the following element is required: " + targetXpath);

        // Verifies the target does not contain the "required" attribute
        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
            try {
                if (!targetRequired.contains("field-invalid")) {
                    assertThat(targetRequired).doesNotContain("field-invalid");
                } else {
                    assertThat(targetRequired).doesNotContain("field-invalid");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                if (!targetRequired.contains("field-invalid")) {
                    localTest.get().pass("The following element NOT is required: " + targetXpath);
                } else {
                    localTest.get().fail("The following element is required: " + targetXpath);
                    UtilityHelpers.adhocScreenCapture(driver, "Element is required.", "Failure");
                    Assert.fail();
                }
            } catch (Exception e) {
                if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                    System.out.println("Field not found: " + targetXpath);
                    Assert.fail();
                } else {
                    localTest.get().fail("Field not found: " + targetXpath);
                    UtilityHelpers.adhocScreenCapture(driver, "Field not found.", "Failure");
                    Assert.fail();
                }
            }
        }
    }

    // Verify the element does NOT have a required status
    public static void byIdNotRequiredValidation(WebDriver driver, String targetId) {

        // Define the output text field
        WebElement target = driver.findElement(By.id(targetId));

        // Identifies the targets attribute
        String targetRequired = target.getAttribute("class");
        System.out.println("Checking if the following element is required: " + targetId);

        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
            try {
                if (!targetRequired.contains("field-invalid")) {
                    assertThat(targetRequired).doesNotContain("field-invalid");
                } else {
                    assertThat(targetRequired).doesNotContain("field-invalid");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                if (!targetRequired.contains("field-invalid")) {
                    localTest.get().pass("The following element NOT is required: " + targetId);
                } else {
                    localTest.get().fail("The following element is required: " + targetId);
                    UtilityHelpers.adhocScreenCapture(driver, "Element is required.", "Failure");
                    Assert.fail();
                }
            } catch (Exception e) {
                if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                    System.out.println("Field not found: " + targetId);
                    Assert.fail();
                } else {
                    localTest.get().fail("Field not found: " + targetId);
                    UtilityHelpers.adhocScreenCapture(driver, "Field not found.", "Failure");
                    Assert.fail();
                }
            }
        }
    }

}
