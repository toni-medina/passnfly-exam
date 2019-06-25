package com.passnfly.sites.wmphvacations.page;

import com.github.webdriverextensions.WebPage;
import com.passnfly.sites.wmphvacations.WmphSite;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.github.webdriverextensions.Bot.assertCurrentUrlStartsWith;
import static com.github.webdriverextensions.Bot.driver;
import static com.passnfly.utilities.Utils.captureScreenshot;

public class WmphSearchPage extends WebPage {

    // Logger
    private static final Logger log = LoggerFactory.getLogger(WmphSearchPage.class);

    // Url
    public String url = WmphSite.url;

    // Model
    @Getter
    @FindBy(css = "div.top-nav]")
    protected WebElement topNav;

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


    public void doSomeStuff() {
        captureScreenshot(driver());
    }
}
