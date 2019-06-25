package com.passnfly.sites.wmphvacations;

import com.github.webdriverextensions.WebSite;
import com.passnfly.sites.wmphvacations.page.WmphResultsPage;
import com.passnfly.sites.wmphvacations.page.WmphSearchPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.github.webdriverextensions.Bot.assertCurrentUrlStartsWith;

public class WmphSite extends WebSite {

    // Logger
    private static final Logger log = LoggerFactory.getLogger(WmphSite.class);

    // Url
    public static String url = "https://www.wmphvacations.com";

    // Pages
    public WmphSearchPage wmphSearchPage;
    public WmphResultsPage wmphResultsPage;
    // ...add your Site's WebPages here

    public void open(Object... arguments) {
        open(url);
    }

    @Override
    public void assertIsOpen(Object... arguments) throws Error {
        assertCurrentUrlStartsWith(url);
    }

    public static WmphSite site() {
        WmphSite wmphSite = new WmphSite();
        wmphSite.initElements();
        return wmphSite;
    }

}
