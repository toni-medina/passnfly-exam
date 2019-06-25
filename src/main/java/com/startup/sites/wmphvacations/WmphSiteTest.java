package com.startup.sites.wmphvacations;

import com.github.webdriverextensions.junitrunner.WebDriverRunner;
import com.github.webdriverextensions.junitrunner.annotations.Chrome;
import com.startup.sites.wmphvacations.capabilities.WmphCapabilities;
import org.junit.runner.RunWith;

@RunWith(WebDriverRunner.class)
@Chrome(desiredCapabilitiesClass = WmphCapabilities.class)
public class WmphSiteTest {

    // Site
    public WmphSite site;

    // Pages
    // ...add your Site's WebPages here

}
