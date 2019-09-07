package Functions.UtilityHelpers;

import ExtentReport.ExtentReportBuilder;

import Core.FileReadWrite;

import com.aventstack.extentreports.MediaEntityBuilder;

import org.imgscalr.Scalr;
import org.openqa.selenium.*;

import java.awt.image.BufferedImage;
import java.io.*;

import java.text.SimpleDateFormat;
import java.util.*;

import org.openqa.selenium.WebDriver;

import javax.imageio.ImageIO;


/**
 * This class provides various utilities
 * Uses ObjectLibrary.xml for object references
 * Uses Datalibrary.xml for data references
 */


public class UtilityHelpers extends ExtentReportBuilder {

    static String configTimeout = FileReadWrite.loadConfigurationProperties().getProperty("timeout");
    static int timeout = Integer.parseInt(configTimeout);


    public static final String USER_DIRECTORY = System.getProperty("user.dir");
    public static final String USER_OS = System.getProperty("os.name");
    public static final String SCREENSHOTS_FOLDER_MAC = USER_DIRECTORY + "/src/main/resources/screenshots/";
    public static final String SCREENSHOTS_FOLDER_WINDOWS = USER_DIRECTORY + "\\src\\main\\resources\\screenshots\\";

    public static String getTimestamp(Boolean is24) {
        String timestamp;
        if (is24) {
            timestamp = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
        } else {
            timestamp = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss").format(new Date());
        }
        return timestamp;
    }

    public static void highlightElement(WebDriver driver, WebElement target) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style','background-color: yellow');", target);
    }

    public static Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }

    public static void switchBrowserWindow(WebDriver driver) {
        // Define the original screen
        String firstWindow = driver.getWindowHandle();
        // Get all screen handles
        Set allWindows = driver.getWindowHandles();
        // Iterate the windows
        Iterator ite=allWindows.iterator();
        // Switch to new window
        while(ite.hasNext())
        {
            String secondWindow = ite.next().toString();
            if(!secondWindow.contains(firstWindow));
            {
                driver.switchTo().window(secondWindow);
            }
        }
    }

    public static void adhocScreenCapture(WebDriver driver, String description, String screenCaptureName) {

        if(USER_OS.contains("Mac")) {
            try {
                // Captures a screenshot
                FileReadWrite.captureScreenShot(driver, screenCaptureName);

                // Loads the screenshot
                File screenshotFile = new File(SCREENSHOTS_FOLDER_MAC + screenCaptureName + ".png");

                // Loads the screenshot into a buffered image to ready it for scaling
                BufferedImage srcImage = ImageIO.read(screenshotFile);
                // Uses the scalr library to reduce the size of the image and write it back to the source folder
                BufferedImage scaledImage = Scalr.resize(srcImage, Scalr.Method.ULTRA_QUALITY, Scalr.Mode.AUTOMATIC, 800, Scalr.OP_ANTIALIAS);
                ImageIO.write(scaledImage, "png", new File(SCREENSHOTS_FOLDER_MAC + screenCaptureName + "_scaled.png"));

                // Loads the new scaled screenshot
                File scaleFile = new File(SCREENSHOTS_FOLDER_MAC + screenCaptureName + "_scaled.png");
                // Converts the scaled screenshot to base64
                String encodeScaledScreenshot = FileReadWrite.encodeFileToBase64Binary(scaleFile);

                localTest.get().info(description, MediaEntityBuilder.createScreenCaptureFromBase64String(encodeScaledScreenshot).build());
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }

        } else if (USER_OS.contains("Windows")) {

            try {
                // Captures a screenshot
                FileReadWrite.captureScreenShot(driver, screenCaptureName);

                // Loads the screenshot
                File screenshotFile = new File(SCREENSHOTS_FOLDER_WINDOWS + screenCaptureName + ".png");

                // Loads the screenshot into a buffered image to ready it for scaling
                BufferedImage srcImage = ImageIO.read(screenshotFile);
                // Uses the scalr library to reduce the size of the image and write it back to the source folder
                BufferedImage scaledImage = Scalr.resize(srcImage, Scalr.Method.ULTRA_QUALITY, Scalr.Mode.AUTOMATIC, 800, Scalr.OP_ANTIALIAS);
                ImageIO.write(scaledImage, "png", new File(SCREENSHOTS_FOLDER_WINDOWS + screenCaptureName + "_scaled.png"));

                // Loads the new scaled screenshot
                File scaleFile = new File(SCREENSHOTS_FOLDER_WINDOWS + screenCaptureName + "_scaled.png");
                // Converts the scaled screenshot to base64
                String encodeScaledScreenshot = FileReadWrite.encodeFileToBase64Binary(scaleFile);

                localTest.get().info(description, MediaEntityBuilder.createScreenCaptureFromBase64String(encodeScaledScreenshot).build());
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }

        }
    }
}
