package ru.mail.webtestbase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

public class TestBase {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeSuite
    protected void BeforeSuite() {
        driver = WebDriverBase.getDriver();
        wait = WebDriverBase.getWait();

        /**
         * CHROME or FIREFOX
         */
        WebDriverBase.selectBrowser("CHROME");
        /**
         * Enter the window size (height, width) or enter "max" or "full" for the full window
         */
        WebDriverBase.windowSize("full");
    }

    @AfterSuite(alwaysRun = true)
    protected void AfterSuite() {
        WebDriverBase.finishBrowser();
    }
}
