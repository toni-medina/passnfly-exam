package com.passnfly;

import com.passnfly.sites.booking.BookingSiteTest;
import com.passnfly.sites.booking.data.BookingAccommodationData;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

import static com.passnfly.sites.booking.BookingSite.site;
import static com.passnfly.utilities.Utils.*;

public class BookingPageTest extends BookingSiteTest {

    // Logger
    private static final Logger log = LoggerFactory.getLogger(BookingPageTest.class);

    @Test
    public void openMainPageTest() throws Exception {

        //Nasty creation of data info here!!!! But It's only an exam ;-)
        BookingAccommodationData data = BookingAccommodationData.with()
                .destination("Barcelona")
                .checkin(formatDate(getTodayDate()))
                .checkout(formatDate(getDaysLater(3)))
                .numOfChilds(2)
                .create();

        List<String> filters = Arrays.asList(
                "Hoteles", "Muy bien: 8 o más", "Aire acondicionado", "Centro de Barcelona");


        // 1.- Navigate to https://www.booking.com/
        site().open();

        // 2.- Fill accommodation data
        site().bookingSearchPage.fillAccommodationData(data);

        // 3.- Click on "Search"
        site().bookingSearchPage.clickSearchButton();

        // 4.- Verify we landed on results page
        site().bookingResultsPage.assertIsOpen();

        // 5.- Sort by 'Estrellas' desc
        site().bookingResultsPage.sortResultsByEstrellasPrecio();

        // 6.- Wait until results are displayed
        site().bookingResultsPage.filterResults(filters);

        // 7.- Print results
        site().bookingResultsPage.printResults();

    }

}

