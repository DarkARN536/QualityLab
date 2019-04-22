package ru.mail.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.mail.webtestbase.PageBase;

import java.time.Duration;
import java.util.List;
import org.testng.Reporter;

public class SendMailPage extends PageBase {

    private By inputLogin = By.id("mailbox:login");
    private By inputPass = By.id("mailbox:password");
    private By btnEnter = By.xpath("//*[@id='mailbox:submit']");
    private By boxMailDomain = By.id("mailbox:domain");
    private By elementMailDomainInboxRu = By.xpath("//option[contains(text(),'@inbox.ru')]");
    private By btnToWriteEmail = By.cssSelector("#b-toolbar__left > div > div > div.b-toolbar__group.b-toolbar__group_left > div > a > span");
    private By inputWhereSendEmail = By.xpath("//textarea[@tabindex='4']");
    private By inputThemeEmail = By.xpath("//input[@tabindex='7']");
    private By bodyTextEmail = By.id("tinymce");
    private By btnSendEmail = By.xpath("//div[@data-bem='b-toolbar__btn']//span[@class='b-toolbar__btn__text']");
    private By linkGoToInbox = By.xpath("//div[@class='message-sent__title']//a[@href='/messages/inbox/']");

    public String getUrlPage() {
        return "https://mail.ru";
    }

    public void selectMailDomain() {
        driver.findElement(boxMailDomain).click();
        driver.findElement(elementMailDomainInboxRu).click();
    }

    public void authenticateUser(String login, String pass) {
        Reporter.log("Выбрали домен почты", true);
        selectMailDomain();
        Reporter.log("Ввели логин: " + login, true);
        driver.findElement(inputLogin).sendKeys(login);
        Reporter.log("Ввели пароль: " + pass, true);
        driver.findElement(inputPass).sendKeys(pass);
//        driver.findElement(inputPass).sendKeys(pass, Keys.ENTER);
        Reporter.log("Ждем кнопку", true);
        wait.withTimeout(Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(btnEnter));
        Reporter.log("Нажали на кнопку 'Войти'", true);
        driver.findElement(btnEnter).click();
    }

    public String getTextFormLinkGoToInbox() {
        return driver.findElement(linkGoToInbox).getText();
    }

    public void sendMail(String where, String theme, String textEmail) {
        Reporter.log("Нажали на кнопку 'Написать письмо'", true);
        driver.findElement(btnToWriteEmail).click();
        Reporter.log("Ввели получателя: " + where, true);
        driver.findElement(inputWhereSendEmail).sendKeys(where);
        Reporter.log("Ввели тему: " + theme, true);
        driver.findElement(inputThemeEmail).sendKeys(theme);
        WebElement iframeMail = driver.findElement(By.xpath("//iframe"));
        driver.switchTo().frame(iframeMail);
        Reporter.log("Ввели текст: " + textEmail, true);
        driver.findElement(bodyTextEmail).sendKeys(textEmail);
        driver.switchTo().defaultContent();
        Reporter.log("Нажали 'Отправить'", true);
        List<WebElement> elements = driver.findElements(btnSendEmail);
        elements.get(0).click();
    }
}
