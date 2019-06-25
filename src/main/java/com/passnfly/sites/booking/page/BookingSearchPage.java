package com.passnfly.sites.booking.page;

import com.github.webdriverextensions.WebDriverExtensionsContext;
import com.github.webdriverextensions.WebPage;
import com.passnfly.sites.booking.BookingSite;
import com.passnfly.sites.booking.data.BookingAccommodationData;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.IntStream;

import static com.github.webdriverextensions.Bot.assertCurrentUrlStartsWith;
import static com.passnfly.utilities.BotUtils.clickOn;
import static com.passnfly.utilities.BotUtils.type;
import static com.passnfly.utilities.Utils.createCssSelector;
import static com.passnfly.utilities.Utils.randomIntBetween;

public class BookingSearchPage extends WebPage {

    // Logger
    private static final Logger log = LoggerFactory.getLogger(BookingSearchPage.class);

    // Url
    public String url = BookingSite.url;

    // Model
    @Getter
    @FindBy(id = "ss")
    protected WebElement destination;

    @Getter
    @FindBy(css = "div[data-mode='checkin']")
    protected WebElement checkin;

    @Getter
    @FindBy(css = "div[data-mode='checkout']")
    protected WebElement checkout;

    @Getter
    @FindBy(css = "div.xp__input-group.xp__guests")
    protected WebElement guestsSection;

    @Getter
    @FindBy(css = "div.sb-group-children button.bui-stepper__add-button")
    protected WebElement addChildBtn;

    @Getter
    @FindBy(className = "sb-searchbox__button")
    protected WebElement searchBtn;


    @Override
    public void open(Object... arguments) {
        open(url);
        assertIsOpen();
    }

    @Override
    public void assertIsOpen(Object... arguments) throws Error {
        assertCurrentUrlStartsWith(url);
        // ...add your asserts that ensures that the page is loaded
    }

    public void fillAccommodationData(BookingAccommodationData data) {

        //filling destination field
        fillDestination(data.getDestination());

        //filling checkin field
        fillCheckin(data.getCheckin());

        //filling checkout field
        fillCheckout(data.getCheckout());

        //filling child info
        fillChildInfo(data.getNumOfChilds());

    }

    private void fillDestination(String dest) {
        type(dest, destination);
        log.info(String.format("Destination field filled with: %s", dest));
    }

    private void fillCheckin(String checkinDate) {
        clickOn(checkin);
        String cssSelector = String.format("td[data-date='%s']", checkinDate);
        clickOn(WebDriverExtensionsContext.getDriver().findElement(createCssSelector(cssSelector)));
        log.info(String.format("Checkin date filled with: %s", checkinDate));
    }

    private void fillCheckout(String checkoutDate) {
        //Bot.click(checkout);
        String dynamicCssSelector = String.format("td[data-date='%s']", checkoutDate);
        clickOn(WebDriverExtensionsContext.getDriver().findElement(createCssSelector(dynamicCssSelector)));
        log.info(String.format("Checkout date filled with: %s", checkoutDate));
    }

    private void fillChildInfo(int num) {
        clickOn(guestsSection);
        IntStream.range(0, num).forEach(this::fillChildSelectRandom);
    }

    private void fillChildSelectRandom(int index) {

        clickOn(addChildBtn);
        log.info("Button add child clicked!");

        String css = String.format("select[data-group-child-age='%d']", index);
        WebElement select = WebDriverExtensionsContext.getDriver().findElement(createCssSelector(css));
        Select dropDown = new Select(select);
        dropDown.selectByIndex(randomIntBetween(0,17));
        log.info(String.format("Dropdown for child %d selected!", index));

    }

    public void clickSearchButton() {
        clickOn(searchBtn);
        log.info("Search button clicked!");
    }
}
