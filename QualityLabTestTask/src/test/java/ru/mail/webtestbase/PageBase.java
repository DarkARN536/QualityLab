package ru.mail.webtestbase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class PageBase{
    protected WebDriver driver;
    protected WebDriverWait wait;
    private String url;

    protected PageBase() {
        driver = WebDriverBase.getDriver();
        wait = WebDriverBase.getWait();
    }

    public void openPage() {
        url = getUrlPage();
        driver.get(url);
    }

    public abstract String getUrlPage();
}
