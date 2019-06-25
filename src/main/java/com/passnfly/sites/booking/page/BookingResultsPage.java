package com.passnfly.sites.booking.page;

import com.github.webdriverextensions.Bot;
import com.github.webdriverextensions.WebPage;
import com.passnfly.sites.booking.BookingSite;
import com.passnfly.utilities.BotUtils;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.IntStream;

import static com.github.webdriverextensions.Bot.assertCurrentUrlContains;
import static com.github.webdriverextensions.Bot.driver;
import static com.passnfly.utilities.BotUtils.*;
import static com.passnfly.utilities.Utils.captureScreenshot;

public class BookingResultsPage extends WebPage {

    // Logger
    private static final Logger log = LoggerFactory.getLogger(BookingResultsPage.class);

    // Url
    public String url = BookingSite.url;

    // Model
    @Getter
    @FindBy(css = "li.sort_class_and_price")
    protected WebElement tabEstrellasPrecio;

    @Getter
    @FindBy(className = "sort_more_options")
    protected WebElement moreOptions;

    @Getter
    @FindBy(css = "div[data-block-id='heading'] h2")
    protected WebElement searchResultsMsg;

    @Getter
    @FindBy(css = "div.js-mode-chooser input[value='1']")
    protected WebElement totalNights;

    @Override
    public void open(Object... arguments) {
        open(url);
        assertIsOpen();
    }

    @Override
    public void assertIsOpen(Object... arguments) throws Error {
        assertCurrentUrlContains("www.booking.com/searchresults");
    }

    public void sortResultsByEstrellasPrecio() {

        if (!Bot.isDisabled(tabEstrellasPrecio)) {
            clickOn(moreOptions);
        }
        clickOn(tabEstrellasPrecio);
        log.info("Results sorted by 'Estrellas y precio' in DESC order");

    }


    public void filterResults(List<String> filtersList) {

        filterByTotalNights();
        selectFiltersLeftColum(filtersList);

    }

    public void filterByTotalNights() {

        waitUntilLoadingMessageDisappears();

        if (BotUtils.isDisplayed(totalNights)) {
            clickOn(totalNights);
            log.info("Filter by 'Total nights' applied");
        } else {
            log.warn("Filter by 'Total nights' is not present");
            captureScreenshot(driver());
        }
    }

    private void waitUntilLoadingMessageDisappears() {

        String loadingMsgClassName = "sr-usp-overlay";
        if (elementExists(By.className(loadingMsgClassName))) {
            WebElement loadingMessage = driver().findElement(By.className(loadingMsgClassName));
            waitForElementNotDisplay(loadingMessage);
        }

    }

    public void selectFiltersLeftColum(List<String> filtersList) {

        filtersList.forEach(filter -> selectFilter(filter));

    }

    private void selectFilter(String filter) {
        waitUntilLoadingMessageDisappears();

        List<WebElement> filters = driver().findElements(By.cssSelector("div.filteroptions a span.filter_label"));

        WebElement theFilter = filters.stream().filter(
                item -> (filter.equals(item.getText().trim()) && item.isDisplayed())).findAny().orElse(null);

        if (theFilter != null) {
            clickOn(theFilter);
            log.info(String.format("Filter '%s' selected", filter));
        } else {
            log.warn(String.format("Filter '%s' not available to be selected", filter));
        }
    }

    public void printResults() {
        waitUntilLoadingMessageDisappears();

        int numResults = 5;

        log.info("*********************************************************");
        log.info("*************** INFORME DE RESULTADOS *******************");
        log.info("*********************************************************");
        log.info(searchResultsMsg.getText());
        log.info(String.format("*************** Printando los %s primeros ***************", numResults));

        IntStream.rangeClosed(1, numResults).forEach(i -> {
            printTheResult(i);
        });

        log.info("*********************************************************");

    }

    private void printTheResult(int i) {
        log.info(String.format(">>>>> RESULTADO %d", i));

        String xpathBase = String.format("//div[@id='hotellist_inner']/div[starts-with(@class, 'sr_item') and not(contains(@class, 'soldout_property'))][%d]", i);

        String xpathNombre = xpathBase + "//span[starts-with(@class, 'sr-hotel__name')]";
        String xpathPrecio = xpathBase + "//div[starts-with(@class, 'bui-price-display__value')]";
        String xpathReviews = xpathBase + "//div[starts-with(@class, 'bui-review-score__text')]";
        String xpathScore = xpathBase + "//div[starts-with(@class, 'bui-review-score__badge')]";
        String xpathLocation = xpathBase + "//div[contains(@class, 'address')]//a";
        String xpathImageUrl = xpathBase + "//div[@class='sr_item_photo']//img";

        WebElement nombre = driver().findElement(By.xpath(xpathNombre));
        WebElement precio = driver().findElement(By.xpath(xpathPrecio));
        WebElement reviews = driver().findElement(By.xpath(xpathReviews));
        WebElement score = driver().findElement(By.xpath(xpathScore));
        WebElement location = driver().findElement(By.xpath(xpathLocation));
        WebElement imageUrl = driver().findElement(By.xpath(xpathImageUrl));

        log.info(String.format("Nombre: %s", nombre.getText()));
        log.info(String.format("Precio: %s", precio.getText()));
        log.info(String.format("Reviews: %s", reviews.getText()));
        log.info(String.format("Score: %s", score.getText()));
        log.info(String.format("Location (coords): %s", location.getAttribute("data-coords")));
        log.info(String.format("Image URL: %s", imageUrl.getAttribute("src")));
        log.info("-------------------------------------------------");

    }
}
