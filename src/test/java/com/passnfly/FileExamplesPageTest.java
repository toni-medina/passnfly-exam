package com.passnfly;

import com.passnfly.sites.fileexamples.FileExamplesSiteTest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.github.webdriverextensions.Bot.driver;
import static com.passnfly.sites.fileexamples.FileExamplesSite.site;
import static com.passnfly.utilities.Utils.captureScreenshot;

public class FileExamplesPageTest extends FileExamplesSiteTest {

    // Logger
    private static final Logger log = LoggerFactory.getLogger(FileExamplesPageTest.class);

    @Test
    public void downloadFilesTest() throws Exception {

        // 1.- Navigate to https://file-examples.com/
        site().open();

        // 2.- Verify we landed on home page
        site().fileExamplesHomePage.assertIsOpen();

        // 3.- Select "Sample documents" menu option
        site().fileExamplesHomePage.clickOnMenuOption("Sample documents");

        // 4.- Verify we landed on home page
        site().fileExamplesSampleDocumentsPage.assertIsOpen();

        // 5.- Click PDF button
        site().fileExamplesSampleDocumentsPage.clickOnButton("PDF");

        // 6.- Downlad files
        site().fileExamplesDownloadPage.downloadFiles();



        captureScreenshot(driver());

    }
}

