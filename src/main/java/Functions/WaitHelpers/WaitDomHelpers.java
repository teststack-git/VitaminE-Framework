package Functions.WaitHelpers;

import ExtentReport.ExtentReportBuilder;

import Core.FileReadWrite;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * This class provides wait times based on the applications DOM
 * Uses ObjectLibrary.xml for object references
 * Uses Datalibrary.xml for data references
 */


public class WaitDomHelpers extends ExtentReportBuilder {

    static String configTimeout = FileReadWrite.loadConfigurationProperties().getProperty("timeout");
    static int timeout = Integer.parseInt(configTimeout);

    public static void byIdWaitForClassToChange (WebDriver driver, String classValue, String targetId) {
        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.attributeContains(By.id(targetId),"class",classValue));
        } catch (Exception e) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + targetId);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + targetId);
                Assert.fail();
            }
        }

    }


}
