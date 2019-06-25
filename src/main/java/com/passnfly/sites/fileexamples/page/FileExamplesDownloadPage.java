package com.passnfly.sites.fileexamples.page;

import com.github.webdriverextensions.WebPage;
import com.passnfly.sites.fileexamples.FileExamplesSite;
import com.passnfly.utilities.Utils;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

import static com.github.webdriverextensions.Bot.assertCurrentUrlStartsWith;

public class FileExamplesDownloadPage extends WebPage {

    // Logger
    private static final Logger log = LoggerFactory.getLogger(FileExamplesDownloadPage.class);

    // Url
    public String url = FileExamplesSite.url;

    // Model
    @Getter
    @FindBy(css = "td[class$='file-link'] a")
    protected List<WebElement> buttons;

    @Override
    public void open(Object... arguments) {
        open(url);
        assertIsOpen();
    }

    @Override
    public void assertIsOpen(Object... arguments) throws Error {
        assertCurrentUrlStartsWith(url);
        log.info(String.format("The URL '%s' is open", url));
        // ...add your asserts that ensures that the page is loaded
    }

    public void downloadFiles() {
        buttons.forEach(button -> {
            String strUrl = button.getAttribute("href");
            try {
                log.info(String.format("File '%s' downloaded", Utils.downloadFileFromUrl(strUrl)));
            } catch (IOException e) {
                //TODO:
                e.printStackTrace();
            }
        });
    }
}
