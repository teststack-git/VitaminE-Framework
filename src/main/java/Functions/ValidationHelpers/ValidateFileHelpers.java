package Functions.ValidationHelpers;

import ExtentReport.ExtentReportBuilder;

import Core.FileReadWrite;

import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.WildcardFileFilter;

import org.junit.Assert;
import org.openqa.selenium.*;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;



/**
 * This helper class provides validation of files
 * Uses ObjectLibrary.xml for object references
 * Uses Datalibrary.xml for data references
 */


public class ValidateFileHelpers extends ExtentReportBuilder {

    static String configTimeout = FileReadWrite.loadConfigurationProperties().getProperty("timeout");
    static int timeout = Integer.parseInt(configTimeout);

    // Validates the size of a file
    public static void validateFileSize(String downloadFileName, String expectedFileSize) {

        // Define the file location
        String downloadedFileLocation = FileReadWrite.loadConfigurationProperties().getProperty("fileDownloadFolderLocation");
        // Identify the newest file name within the folder
        File theNewestFile = null;

        try {
            // Define the file name within the folder location
            File dir = new File(downloadedFileLocation);
            FileFilter fileFilter = new WildcardFileFilter(downloadFileName);
            File[] files = dir.listFiles(fileFilter);

            // Add all documents within folder to an array and sort in descending order
            if(files.length > 0) {
                /* The newest file comes first */
                Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
                theNewestFile = files[0];
                String fileName = files[0].getName();
                System.out.println("fileName:  "+fileName);
            }

            // Get the file length
            long expectedByteSize = Long.valueOf(expectedFileSize);
            long fileSize = theNewestFile.length();

            // Print out both the actual file size and expected file size
            System.out.println("Downloaded file size: "+fileSize+" bytes");
            System.out.println("Expected file size: "+expectedFileSize+" bytes");

            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                if (fileSize==expectedByteSize) {
                    assertThat(fileSize==expectedByteSize);
                    System.out.println("The file size matches.");
                } else {
                    assertThat(fileSize==expectedByteSize);
                    System.out.println("The file size does not match.");
                    Assert.fail();
                }
            } else {
                if (fileSize==expectedByteSize) {
                    assertThat(fileSize==expectedByteSize);
                    localTest.get().pass("The file size matches.");

                } else {
                    assertThat(fileSize==expectedByteSize);
                    localTest.get().fail("The file size does not match.");
                    Assert.fail();
                }
            }

        } catch (NotFoundException err) {
            err.printStackTrace();
            System.out.println("File not found");
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("File not found.");
            } else {
                localTest.get().fail("File not found.");
                Assert.fail();
            }
        }

    }


}
