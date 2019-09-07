package Core;

import ExtentReport.ExtentReportBuilder;

import org.openqa.selenium.BuildInfo;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import org.testng.annotations.*;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;


public abstract class Configuration extends ExtentReportBuilder {

    public WebDriver driver;
    public String baseUrl;

    public static final String USER_DIRECTORY = System.getProperty("user.dir");
    public static final String USER_OS = System.getProperty("os.name");
    public static final String RESOURCES_FOLDER_WINDOWS = USER_DIRECTORY + "\\src\\main\\resources\\";
    public static final String RESOURCES_FOLDER_MAC = USER_DIRECTORY + "/src/main/resources/";
    public static final String DRIVERS_FOLDER_WINDOWS = USER_DIRECTORY + "\\src\\main\\Drivers\\";
    public static final String DRIVERS_FOLDER_MAC = USER_DIRECTORY + "/src/main/Drivers/";
    public static final String SCREENSHOTS_FOLDER_MAC = USER_DIRECTORY + "/src/main/resources/screenshots/";
    public static final String SCREENSHOTS_FOLDER_WINDOWS = USER_DIRECTORY + "\\src\\main\\resources\\screenshots\\";
    public FileReadWrite objectLibrary;
    public FileReadWrite dataLibrary;
    public FileReadWrite configuration;

    // Switches between browser types set from configuration.properties
    public void browserType(String browser) {

        // Firefox settings
        if (browser.equalsIgnoreCase("firefox")) {

            FirefoxProfile profile = new FirefoxProfile();
            FirefoxOptions options = new FirefoxOptions();
            Map<String, Object> prefs = new HashMap<String, Object>();
            prefs.put("profile.default_content_settings.popups", 0);
            prefs.put("download.default_directory", FileReadWrite.loadConfigurationProperties().getProperty("fileDownloadFolderLocation"));
            // Sets Gecko driver based on operating system ("Mac" or "Windows")
            if (USER_OS.contains("Mac")) {
                System.setProperty("webdriver.gecko.driver", DRIVERS_FOLDER_MAC + "geckodriver");

                profile.setPreference("browser.download.folderList", 2);
                profile.setPreference("browser.download.manager.showWhenStarting", false);
                profile.setPreference("browser.download.useDownloadDir", true);
                profile.setPreference("browser.download.dir", FileReadWrite.loadConfigurationProperties().getProperty("fileDownloadFolderLocation"));
                profile.setPreference("browser.helperApps.neverAsk.openFile", "application/octet-stream,application/pdf,application/excel,application/json,application/vnd.openxmlformats-officedocument.wordprocessingml.document,application/x-bzip2,application/zip,application/x-gzip,text/xml,text/html,text/csv,text/plain,image/jpeg,image/bmp,image/tiff,image/png,image/gif");
                profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream,application/pdf,application/excel,application/json,application/vnd.openxmlformats-officedocument.wordprocessingml.document,application/x-bzip2,application/zip,application/x-gzip,text/xml,text/html,text/csv,text/plain,image/jpeg,image/bmp,image/tiff,image/png,image/gif");
                profile.setPreference("browser.helperApps.alwaysAsk.force", false);
                profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
                profile.setPreference("browser.download.manager.focusWhenStarting", false);
                profile.setPreference("browser.download.manager.useWindow", false);
                profile.setPreference("browser.download.manager.showAlertOnComplete", false);
                profile.setPreference("browser.download.manager.closeWhenDone", false);
                profile.setPreference("pdfjs.disabled", true);
                profile.setPreference("browser.download.panel.shown",false);

                DesiredCapabilities capabilities = DesiredCapabilities.firefox();
                capabilities.setCapability(FirefoxDriver.PROFILE, profile);
                options.setProfile(profile);
                if (FileReadWrite.loadConfigurationProperties().getProperty("enableHeadlessMode").equalsIgnoreCase("yes")) {
                    options.setHeadless(true);
                } else {
                    options.setHeadless(false);
                }

            }
            else if (USER_OS.contains("Windows")) {
                System.setProperty("webdriver.gecko.driver", DRIVERS_FOLDER_WINDOWS + "geckodriver.exe");

                profile.setPreference("browser.download.folderList", 2);
                profile.setPreference("browser.download.manager.showWhenStarting", false);
                profile.setPreference("browser.download.useDownloadDir", true);
                profile.setPreference("browser.download.dir", FileReadWrite.loadConfigurationProperties().getProperty("fileDownloadFolderLocation"));
                profile.setPreference("browser.helperApps.neverAsk.openFile", "application/octet-stream,application/pdf,application/excel,application/json,application/x-bzip2,application/zip,application/x-gzip,text/xml,text/html,text/csv,text/plain,image/jpeg,image/bmp,image/tiff,image/png,image/gif,application/vnd.openxmlformats-officedocument.wordprocessingml.document,application/vnd.openxmlformats-officedocument.wordprocessingml.template,application/vnd.openxmlformats-officedocument.presentationml.slideshow,application/vnd.openxmlformats-officedocument.presentationml.presentation,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream,application/pdf,application/excel,application/json,application/x-bzip2,application/zip,application/x-gzip,text/xml,text/html,text/csv,text/plain,image/jpeg,image/bmp,image/tiff,image/png,image/gif,application/vnd.openxmlformats-officedocument.wordprocessingml.document,application/vnd.openxmlformats-officedocument.wordprocessingml.template,application/vnd.openxmlformats-officedocument.presentationml.slideshow,application/vnd.openxmlformats-officedocument.presentationml.presentation,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                profile.setPreference("browser.helperApps.alwaysAsk.force", false);
                profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
                profile.setPreference("browser.download.manager.focusWhenStarting", false);
                profile.setPreference("browser.download.manager.useWindow", false);
                profile.setPreference("browser.download.manager.showAlertOnComplete", false);
                profile.setPreference("browser.download.manager.closeWhenDone", false);
                profile.setPreference("pdfjs.disabled", true);
                profile.setPreference("browser.download.panel.shown",false);

                DesiredCapabilities capabilities = DesiredCapabilities.firefox();
                capabilities.setCapability(FirefoxDriver.PROFILE, profile);
                options.setProfile(profile);
                if (FileReadWrite.loadConfigurationProperties().getProperty("enableHeadlessMode").equalsIgnoreCase("yes")) {
                    options.setHeadless(true);
                } else {
                    options.setHeadless(false);
                }

            }
            this.driver = new FirefoxDriver(options);
            // Sets URL
            this.baseUrl = configuration.getPropertyValue("url");
            // Sets timeout limit
            driver.manage().window().maximize();
            // Update config file with Firefox browser version
            Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
            String firefoxVersion = (String)cap.getCapability("browserVersion");
            FileReadWrite.updateConfigurationPropFile("browserVersionFF", firefoxVersion);

        }
        // Chrome Settings
        if (browser.equalsIgnoreCase("chrome")) {

            // Sets Chrome driver based on operating system ("Mac" or "Windows")
                if (USER_OS.contains("Mac")) {
                    System.setProperty("webdriver.chrome.driver", DRIVERS_FOLDER_MAC + "chromedriver");
                }
                else if (USER_OS.contains("Windows")) {
                    System.setProperty("webdriver.chrome.driver", DRIVERS_FOLDER_WINDOWS + "chromedriver.exe");
                }

            ChromeOptions options = new ChromeOptions();
            Map<String, Object> prefs = new HashMap<String, Object>();
            prefs.put("download.default_directory", FileReadWrite.loadConfigurationProperties().getProperty("fileDownloadFolderLocation"));
            prefs.put("directory_upgrade", true);
            options.setCapability("credentials_enable_service", false);
            options.setCapability("download.prompt_for_download", false);
            options.setCapability("safebrowsing.enabled", true);
            options.setCapability("directory_upgrade", true);
            options.setCapability("profile.default_content_settings.popups", 0);
            options.setCapability("download.default_directory", FileReadWrite.loadConfigurationProperties().getProperty("fileDownloadFolderLocation"));
            options.addArguments("disable-infobars");
            options.addArguments("safebrowsing-disable-download-protection");
            options.addArguments("--disable-features=VizDisplayCompositor");
            options.setExperimentalOption("prefs", prefs);

            // HEADLESS MODE
            if (FileReadWrite.loadConfigurationProperties().getProperty("enableHeadlessMode").equalsIgnoreCase("yes")) {
                options.setHeadless(true);
            } else {
                options.setHeadless(false);
            }
            // CHROME FULL SCREEN
            if (FileReadWrite.loadConfigurationProperties().getProperty("chromeFullScreen").equalsIgnoreCase("yes")) {
                // Maximize screen
                options.addArguments("--kiosk");
                // Remove URL and tab bar
                options.addArguments("--app="+configuration.getPropertyValue("url"));
                this.driver = new ChromeDriver(options);
                // Sets URL
                this.baseUrl = configuration.getPropertyValue("url");
                // Sets timeout limit
                this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            }
            // CHROME MAXIMIZED SCREEN
            else if (FileReadWrite.loadConfigurationProperties().getProperty("chromeMaximized").equalsIgnoreCase("yes")) {

                if (FileReadWrite.loadConfigurationProperties().getProperty("platform").equalsIgnoreCase(
                        "Mac")) {
                    // Maximize screen
                    options.addArguments("--kiosk");
                } else {
                    options.addArguments("--start-maximized");
                }
                this.driver = new ChromeDriver(options);
                // Sets URL
                this.baseUrl = configuration.getPropertyValue("url");
                // Sets timeout limit
                this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            }

            // Update config file with Chrome browser version
            Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
            String chromeVersion = cap.getVersion();
            FileReadWrite.updateConfigurationPropFile("browserVersionChrome", chromeVersion);

        }
    }

    // Selects the objectLibrary and dataLibrary based on environment and platform
    public void environmentUnderTest(String environment) {

            System.out.println("Environment: " + environment);
            if (USER_OS.contains("Mac")) {
                this.objectLibrary = new FileReadWrite(RESOURCES_FOLDER_MAC + "objectLibrary.properties");
                this.dataLibrary = new FileReadWrite(RESOURCES_FOLDER_MAC + "dataLibrary.properties");
            }
            else if (USER_OS.contains("Windows")) {
                this.objectLibrary = new FileReadWrite(RESOURCES_FOLDER_WINDOWS + "objectLibrary.properties");
                this.dataLibrary = new FileReadWrite(RESOURCES_FOLDER_WINDOWS + "dataLibrary.properties");
            }
    }


    @BeforeTest(alwaysRun = true)
    public void setup() throws Exception {
        // Loads the configuration property file based on platform
        if (USER_OS.contains("Mac")) {
            this.configuration = new FileReadWrite(RESOURCES_FOLDER_MAC + "configuration.properties");
        }
        else if (USER_OS.contains("Windows")) {
            System.out.println("Configuration Used: Windows");
            this.configuration = new FileReadWrite(RESOURCES_FOLDER_WINDOWS + "configuration.properties");
        }
        // Sets the browser and platform based on configuration.properties
        this.browserType(configuration.getPropertyValue("browserType"));
        // Sets the environment based on configuration.properties
        this.environmentUnderTest(configuration.getPropertyValue("environment"));
        FileReadWrite.updateConfigurationPropFile("seleniumVersion", new BuildInfo().getReleaseLabel());
    }


    // Closes the browser
    @AfterTest(alwaysRun = true)
    public void tearDown() throws IOException {
        if (FileReadWrite.loadConfigurationProperties().getProperty("browserType").equalsIgnoreCase("Firefox")) {
            this.driver.close();
        } else {
            this.driver.quit();
        }

    }


}