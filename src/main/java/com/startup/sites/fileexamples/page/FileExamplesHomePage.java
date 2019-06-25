package com.startup.sites.fileexamples.page;

import com.github.webdriverextensions.WebPage;
import com.startup.sites.fileexamples.FileExamplesSite;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.github.webdriverextensions.Bot.assertCurrentUrlStartsWith;
import static com.startup.utilities.BotUtils.clickOn;

public class FileExamplesHomePage extends WebPage {

    // Logger
    private static final Logger log = LoggerFactory.getLogger(FileExamplesHomePage.class);

    // Url
    public String url = FileExamplesSite.url;

    // Model
    @Getter
    @FindBy(css = "ul[class*='navbar-nav'] li a")
    protected List<WebElement> menuOptions;

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


    public void clickOnMenuOption(String menuText) {
        WebElement selectedOption = menuOptions.stream().filter(item -> menuText.equalsIgnoreCase(item.getText().trim())).findAny().orElse(null);
        if (selectedOption != null) {
            clickOn(selectedOption);
            log.info(String.format("The menu option '%s' is clicked.", menuText));
        } else {
            log.error(String.format("The text '%s' is not a valid menu option.", menuText));
        }
    }
}
