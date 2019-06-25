package com.passnfly.utilities;

import com.github.webdriverextensions.Bot;
import com.github.webdriverextensions.WebComponent;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BotUtils extends Bot {

    public static void waitForElementToClickable(WebElement webElement, long timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver(), timeOutInSeconds);
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public static void waitForElementToClickable(WebElement webElement) {
        waitForElementToClickable(webElement, 10);
    }

    public static void waitForElementToDisplay(By by) {
        waitForElementToDisplay(by, 10);
    }

    public static void waitForElementNotDisplay(WebElement webElement) {
        waitForElementNotDisplay(webElement, 10);
    }

    public static void waitForElementNotDisplay(WebElement webElement, long secondsToWait) {
        WebDriverWait wait = new WebDriverWait(driver(), secondsToWait);
        wait.until(ExpectedConditions.invisibilityOf(webElement));
    }

    public static WebElement waitForElementToDisplay(By by, long timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver(), timeOutInSeconds);
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static void clickOn(WebElement webElement) {
        scrollTo(webElement);
        click(webElement);
    }

    public static Object scrollTo(WebElement webElement) {
        if (webElement instanceof WebComponent) {
            return executeJavascript("arguments[0].scrollIntoView(true);", ((WebComponent) webElement).getWrappedWebElement());
        }
        return executeJavascript("arguments[0].scrollIntoView(true);", webElement);
    }

    public static boolean elementExists(By by) {
        List<WebElement> items = driver().findElements(by);
        return items.size() > 0;
    }

}
