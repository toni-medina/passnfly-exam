package com.startup.sites.booking.component;

import com.github.webdriverextensions.WebComponent;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BookingSearchComponent extends WebComponent {

    @Getter
    @FindBy(id = "ss")
    protected WebElement destination;

    @Getter
    @FindBy(css = "div[data-mode='checkin']")
    protected WebElement checkin;

    @Getter
    @FindBy(css = "div[data-mode='checkout']")
    protected WebElement checkout;


}
