package com.startup.sites.booking;

import com.github.webdriverextensions.WebSite;
import com.startup.sites.booking.page.BookingResultsPage;
import com.startup.sites.booking.page.BookingSearchPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.github.webdriverextensions.Bot.assertCurrentUrlStartsWith;

public class BookingSite extends WebSite {

    // Logger
    private static final Logger log = LoggerFactory.getLogger(BookingSite.class);

    // Url
    public static String url = "https://www.booking.com";

    // Pages
    public BookingSearchPage bookingSearchPage;
    public BookingResultsPage bookingResultsPage;
    // ...add your Site's WebPages here

    public void open(Object... arguments) {
        open(url);
    }

    @Override
    public void assertIsOpen(Object... arguments) throws Error {
        assertCurrentUrlStartsWith(url);
    }

    public static BookingSite site() {
        BookingSite bookingSite = new BookingSite();
        bookingSite.initElements();
        return bookingSite;
    }

}
