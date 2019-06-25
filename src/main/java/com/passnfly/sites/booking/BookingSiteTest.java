package com.passnfly.sites.booking;

import com.github.webdriverextensions.junitrunner.WebDriverRunner;
import com.github.webdriverextensions.junitrunner.annotations.Chrome;
import com.passnfly.sites.booking.page.BookingSearchPage;
import org.junit.runner.RunWith;

@RunWith(WebDriverRunner.class)
//@Firefox
@Chrome
public class BookingSiteTest {

    // Site
    public BookingSite site;

    // Pages
    public BookingSearchPage bookingSearchPage;
    // ...add your Site's WebPages here

}
