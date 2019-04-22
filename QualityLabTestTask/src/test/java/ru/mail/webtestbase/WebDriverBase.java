package ru.mail.webtestbase;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class WebDriverBase {
    protected static WebDriver driver;
    protected static WebDriverWait webDriverWait;

    public static WebDriver getDriver() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
        return driver;
    }

    public static void selectBrowser(String browser) {
        if (browser == "CHROME") {
            driver = new ChromeDriver();
        }
        if (browser == "FIREFOX") {
            driver = new FirefoxDriver();
        }
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriverWait = new WebDriverWait(driver, 10);
    }

    public static void windowSize(int width, int height) {
        driver.manage().window().setSize(new Dimension(width, height));
    }

    public static void windowSize(String size) {
        if (size == "max" | size == "full") {
            driver.manage().window().maximize();
        }
        else {
            driver.manage().window().setSize(new Dimension(1280, 720));
        }
    }

    public static void finishBrowser() {
        driver.quit();
    }

    public static WebDriverWait getWait() {
        return webDriverWait;
    }
}
