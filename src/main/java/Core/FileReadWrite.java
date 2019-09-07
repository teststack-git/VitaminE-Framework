package Core;

import java.io.*;
import java.util.Properties;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.PropertiesConfigurationLayout;

/**
 * This helper class is used to parse property files and write to them
 */

public class FileReadWrite extends Configuration {

    Properties properties;

    public static final String USER_DIRECTORY = System.getProperty("user.dir");
    public static final String USER_OS = System.getProperty("os.name");
    public static final String RESOURCES_FOLDER_WINDOWS = USER_DIRECTORY + "\\src\\main\\resources\\";
    public static final String RESOURCES_FOLDER_MAC = USER_DIRECTORY + "/src/main/resources/";

/////////////////////// Read Property File //////////////////////

// These methods are used to read from property files

///////////////////////////////////////////////////////////

    public FileReadWrite(String fpath) {
        String filepath = fpath;

        //Load the properties file
        properties = new Properties();

        try {
            FileReader reader = new FileReader(filepath);
            properties.load(reader);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Unable to load property file.");
        }
    }

    public static Properties loadPropertyFile(String fileName) {
        Properties prop = new Properties();
        FileInputStream fis = null;

        if (USER_OS.contains("Mac")) {
            try {
                fis = new FileInputStream(RESOURCES_FOLDER_MAC + fileName);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                System.out.println("Unable to load property file.");
            }

            try {
                prop.load(fis);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                System.out.println("Unable to load property file.");
            }
        } else if (USER_OS.contains("Windows")) {
            try {
                fis = new FileInputStream(RESOURCES_FOLDER_WINDOWS + fileName);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                System.out.println("Unable to load property file.");
            }

            try {
                prop.load(fis);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                System.out.println("Unable to load property file.");
            }
        }
        return prop;
    }

    public String getPropertyValue(String key) {
        return properties.getProperty(key);
    }

    public static String getDataPropertyValue(String key) {
        String environment = loadConfigurationProperties().getProperty("environment");

        if (environment.isEmpty()) {
            return loadPropertyFile("dataLibrary.properties").getProperty(key);
        } else {
            return loadPropertyFile("dataLibrary_" + environment + ".properties").getProperty(key);
        }

    }

    public static Properties loadCommonComponentObjectLibrary() {
        Properties prop = new Properties();
        FileInputStream fis = null;
        String environment = loadConfigurationProperties().getProperty("environment");
        if (USER_OS.contains("Mac")) {
            try {
                fis = new FileInputStream(RESOURCES_FOLDER_MAC + "objectLibrary"+environment+".properties");
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                System.out.println("Unable to load object property file.");
            }
            try {
                prop.load(fis);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                System.out.println("Unable to load object property file.");
            }
        } else if (USER_OS.contains("Windows")) {
            try {
                fis = new FileInputStream(RESOURCES_FOLDER_WINDOWS + "objectLibrary"+environment+".properties");
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                System.out.println("Unable to load object property file.");
            }
            try {
                prop.load(fis);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                System.out.println("Unable to load object property file.");
            }
        }
        return prop;
    }

    public static Properties loadCommonComponentDataLibrary() {
        Properties prop = new Properties();
        FileInputStream fis = null;

        Properties config = loadPropertyFile("configuration.properties");
        String environment = config.getProperty("environment");

            try {
                if (USER_OS.contains("Mac")) {
                    fis = new FileInputStream(RESOURCES_FOLDER_MAC + "dataLibrary_" + environment + ".properties");
                } else if (USER_OS.contains("Windows")) {
                    fis = new FileInputStream(RESOURCES_FOLDER_WINDOWS + "dataLibrary_" + environment + ".properties");
                }
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                System.out.println("Unable to load data property file.");
            }
            try {
                prop.load(fis);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                System.out.println("Unable to load data property file.");
            }

        return prop;
    }

    public static Properties loadConfigurationProperties() {
        Properties prop = new Properties();
        FileInputStream fis = null;

        if (USER_OS.contains("Mac")) {
            try {
                fis = new FileInputStream(RESOURCES_FOLDER_MAC + "configuration.properties");
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                System.out.println("Unable to load configuration property file.");
            }
            try {
                prop.load(fis);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                System.out.println("Unable to load configuration property file.");
            }
        } else if (USER_OS.contains("Windows")) {
            try {
                fis = new FileInputStream(RESOURCES_FOLDER_WINDOWS + "configuration.properties");
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                System.out.println("Unable to load configuration property file.");
            }
            try {
                prop.load(fis);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                System.out.println("Unable to load configuration property file.");
            }
        }
        return prop;
    }

    public static void byXpathUpdatePropFile(WebDriver driver, String propertyName, String targetXpath) {
        // Identifies the target value
        WebElement updateValue = driver.findElement(By.xpath(targetXpath));

        String value = null;
        try {
            value = updateValue.getAttribute("value");

            if (value == null) {
                try {
                    value = updateValue.getText();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (USER_OS.contains("Mac")) {
                String environment = loadConfigurationProperties().getProperty("environment");

                if (environment.isEmpty()) {
                    File file = new File(RESOURCES_FOLDER_MAC + "dataLibrary.properties");

                    PropertiesConfiguration config = new PropertiesConfiguration();
                    config.setDelimiterParsingDisabled(true);
                    PropertiesConfigurationLayout layout = new PropertiesConfigurationLayout(config);
                    layout.load(new InputStreamReader(new FileInputStream(file)));

                    config.setProperty(propertyName, value);
                    layout.save(new FileWriter(RESOURCES_FOLDER_MAC + "dataLibrary.properties", false));
                } else {
                    File file = new File(RESOURCES_FOLDER_MAC + "dataLibrary_" + environment + ".properties");

                    PropertiesConfiguration config = new PropertiesConfiguration();
                    config.setDelimiterParsingDisabled(true);
                    PropertiesConfigurationLayout layout = new PropertiesConfigurationLayout(config);
                    layout.load(new InputStreamReader(new FileInputStream(file)));

                    config.setProperty(propertyName, value);
                    layout.save(new FileWriter(RESOURCES_FOLDER_MAC + "dataLibrary_" + environment + ".properties", false));
                }

            } else if (USER_OS.contains("Windows")) {
                String environment = loadConfigurationProperties().getProperty("environment");

                if (environment.isEmpty()) {
                    File file = new File(RESOURCES_FOLDER_WINDOWS + "dataLibrary.properties");

                    PropertiesConfiguration config = new PropertiesConfiguration();
                    config.setDelimiterParsingDisabled(true);
                    PropertiesConfigurationLayout layout = new PropertiesConfigurationLayout(config);
                    layout.load(new InputStreamReader(new FileInputStream(file)));

                    config.setProperty(propertyName, value);
                    layout.save(new FileWriter(RESOURCES_FOLDER_WINDOWS + "dataLibrary.properties", false));
                } else {
                    File file = new File(RESOURCES_FOLDER_WINDOWS + "dataLibrary_" + environment + ".properties");

                    PropertiesConfiguration config = new PropertiesConfiguration();
                    config.setDelimiterParsingDisabled(true);
                    PropertiesConfigurationLayout layout = new PropertiesConfigurationLayout(config);
                    layout.load(new InputStreamReader(new FileInputStream(file)));

                    config.setProperty(propertyName, value);
                    layout.save(new FileWriter(RESOURCES_FOLDER_WINDOWS + "dataLibrary_" + environment + ".properties", false));
                }
            }
        } catch (ConfigurationException ce) {
            ce.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void byIdUpdatePropFile(WebDriver driver, String propertyName, String targetId) {

        // Identifies the target value
        WebElement updateValue = driver.findElement(By.id(targetId));

        String value = null;
        try {
            value = updateValue.getAttribute("value");

            if (value == null) {
                try {
                    value = updateValue.getText();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (USER_OS.contains("Mac")) {
                String environment = loadConfigurationProperties().getProperty("environment");

                if (environment.isEmpty()) {
                    File file = new File(RESOURCES_FOLDER_MAC + "dataLibrary.properties");

                    PropertiesConfiguration config = new PropertiesConfiguration();
                    config.setDelimiterParsingDisabled(true);
                    PropertiesConfigurationLayout layout = new PropertiesConfigurationLayout(config);
                    layout.load(new InputStreamReader(new FileInputStream(file)));

                    config.setProperty(propertyName, value);
                    layout.save(new FileWriter(RESOURCES_FOLDER_MAC + "dataLibrary.properties", false));
                } else {
                    File file = new File(RESOURCES_FOLDER_MAC + "dataLibrary_" + environment + ".properties");

                    PropertiesConfiguration config = new PropertiesConfiguration();
                    config.setDelimiterParsingDisabled(true);
                    PropertiesConfigurationLayout layout = new PropertiesConfigurationLayout(config);
                    layout.load(new InputStreamReader(new FileInputStream(file)));

                    config.setProperty(propertyName, value);
                    layout.save(new FileWriter(RESOURCES_FOLDER_MAC + "dataLibrary_" + environment + ".properties", false));
                }

            } else if (USER_OS.contains("Windows")) {
                String environment = loadConfigurationProperties().getProperty("environment");

                if (environment.isEmpty()) {
                    File file = new File(RESOURCES_FOLDER_WINDOWS + "dataLibrary.properties");

                    PropertiesConfiguration config = new PropertiesConfiguration();
                    config.setDelimiterParsingDisabled(true);
                    PropertiesConfigurationLayout layout = new PropertiesConfigurationLayout(config);
                    layout.load(new InputStreamReader(new FileInputStream(file)));

                    config.setProperty(propertyName, value);
                    layout.save(new FileWriter(RESOURCES_FOLDER_WINDOWS + "dataLibrary.properties", false));
                } else {
                    File file = new File(RESOURCES_FOLDER_WINDOWS + "dataLibrary_" + environment + ".properties");

                    PropertiesConfiguration config = new PropertiesConfiguration();
                    config.setDelimiterParsingDisabled(true);
                    PropertiesConfigurationLayout layout = new PropertiesConfigurationLayout(config);
                    layout.load(new InputStreamReader(new FileInputStream(file)));

                    config.setProperty(propertyName, value);
                    layout.save(new FileWriter(RESOURCES_FOLDER_WINDOWS + "dataLibrary_" + environment + ".properties", false));
                }
            }
        } catch (ConfigurationException ce) {
            ce.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void updatePropFile(String propertyName, String value) {
        // Identifies the target value

        try {
            if (USER_OS.contains("Mac")) {
                String environment = loadConfigurationProperties().getProperty("environment");

                if (environment.isEmpty()) {
                    File file = new File(RESOURCES_FOLDER_MAC + "dataLibrary.properties");

                    PropertiesConfiguration config = new PropertiesConfiguration();
                    config.setDelimiterParsingDisabled(true);
                    PropertiesConfigurationLayout layout = new PropertiesConfigurationLayout(config);
                    layout.load(new InputStreamReader(new FileInputStream(file)));

                    config.setProperty(propertyName, value);
                    layout.save(new FileWriter(RESOURCES_FOLDER_MAC + "dataLibrary.properties", false));
                } else {
                    File file = new File(RESOURCES_FOLDER_MAC + "dataLibrary_" + environment + ".properties");

                    PropertiesConfiguration config = new PropertiesConfiguration();
                    config.setDelimiterParsingDisabled(true);
                    PropertiesConfigurationLayout layout = new PropertiesConfigurationLayout(config);
                    layout.load(new InputStreamReader(new FileInputStream(file)));

                    config.setProperty(propertyName, value);
                    layout.save(new FileWriter(RESOURCES_FOLDER_MAC + "dataLibrary_" + environment + ".properties", false));
                }

            } else if (USER_OS.contains("Windows")) {
                String environment = loadConfigurationProperties().getProperty("environment");

                if (environment.isEmpty()) {
                    File file = new File(RESOURCES_FOLDER_WINDOWS + "dataLibrary.properties");

                    PropertiesConfiguration config = new PropertiesConfiguration();
                    config.setDelimiterParsingDisabled(true);
                    PropertiesConfigurationLayout layout = new PropertiesConfigurationLayout(config);
                    layout.load(new InputStreamReader(new FileInputStream(file)));

                    config.setProperty(propertyName, value);
                    layout.save(new FileWriter(RESOURCES_FOLDER_WINDOWS + "dataLibrary.properties", false));
                } else {
                    File file = new File(RESOURCES_FOLDER_WINDOWS + "dataLibrary_" + environment + ".properties");

                    PropertiesConfiguration config = new PropertiesConfiguration();
                    config.setDelimiterParsingDisabled(true);
                    PropertiesConfigurationLayout layout = new PropertiesConfigurationLayout(config);
                    layout.load(new InputStreamReader(new FileInputStream(file)));

                    config.setProperty(propertyName, value);
                    layout.save(new FileWriter(RESOURCES_FOLDER_WINDOWS + "dataLibrary_" + environment + ".properties", false));
                }
            }
        } catch (ConfigurationException ce) {
            ce.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void updateConfigurationPropFile(String propertyName, String value) {

        try {
            if (USER_OS.contains("Mac")) {

                File file = new File(RESOURCES_FOLDER_MAC + "configuration.properties");

                PropertiesConfiguration config = new PropertiesConfiguration();
                config.setDelimiterParsingDisabled(true);
                PropertiesConfigurationLayout layout = new PropertiesConfigurationLayout(config);
                layout.load(new InputStreamReader(new FileInputStream(file)));

                config.setProperty(propertyName, value);
                layout.save(new FileWriter(RESOURCES_FOLDER_MAC + "configuration.properties", false));

            } else if (USER_OS.contains("Windows")) {

                File file = new File(RESOURCES_FOLDER_WINDOWS + "configuration.properties");

                PropertiesConfiguration config = new PropertiesConfiguration();
                config.setDelimiterParsingDisabled(true);
                PropertiesConfigurationLayout layout = new PropertiesConfigurationLayout(config);
                layout.load(new InputStreamReader(new FileInputStream(file)));

                config.setProperty(propertyName, value);
                layout.save(new FileWriter(RESOURCES_FOLDER_WINDOWS + "configuration.properties", false));
            }
        } catch (ConfigurationException ce) {
            ce.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }


    public static void captureScreenShot(WebDriver driver, String screenshotName){
        String extentReportImage = "";
        if (USER_OS.contains("Mac")) {
            String screenshot = extentReportImage + SCREENSHOTS_FOLDER_MAC +  screenshotName + ".png";
            extentReportImage = screenshot;

        }
        else if (USER_OS.contains("Windows")) {
            String screenshot = extentReportImage + SCREENSHOTS_FOLDER_WINDOWS +  screenshotName + ".png";
            extentReportImage = screenshot;
        }

        // Take screenshot and store as a file format
        File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            // now copy the screenshot to desired location using copyFile method
            FileUtils.copyFile(src, new File(extentReportImage));
        } catch (IOException e)
        {
            System.out.println("Unable to capture screenshot: " + e.getMessage());
        }
    }

    public static String encodeFileToBase64Binary(File file){
        String encodedfile = null;
        try {
            FileInputStream fileInputStreamReader = new FileInputStream(file);
            byte[] bytes = new byte[(int)file.length()];
            fileInputStreamReader.read(bytes);
            encodedfile = new String(Base64.encodeBase64(bytes), "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return encodedfile;
    }

    }