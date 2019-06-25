package com.passnfly.sites.fileexamples;

import com.github.webdriverextensions.WebSite;
import com.passnfly.sites.fileexamples.page.FileExamplesDownloadPage;
import com.passnfly.sites.fileexamples.page.FileExamplesHomePage;
import com.passnfly.sites.fileexamples.page.FileExamplesSampleDocumentsPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.github.webdriverextensions.Bot.assertCurrentUrlStartsWith;

public class FileExamplesSite extends WebSite {

    // Logger
    private static final Logger log = LoggerFactory.getLogger(FileExamplesSite.class);

    // Url
    public static String url = "https://file-examples.com";

    // Pages
    public FileExamplesHomePage fileExamplesHomePage;
    public FileExamplesSampleDocumentsPage fileExamplesSampleDocumentsPage;
    public FileExamplesDownloadPage fileExamplesDownloadPage;
    // ...add your Site's WebPages here

    public void open(Object... arguments) {
        open(url);
    }

    @Override
    public void assertIsOpen(Object... arguments) throws Error {
        assertCurrentUrlStartsWith(url);
    }

    public static FileExamplesSite site() {
        FileExamplesSite wmphSite = new FileExamplesSite();
        wmphSite.initElements();
        return wmphSite;
    }

}
