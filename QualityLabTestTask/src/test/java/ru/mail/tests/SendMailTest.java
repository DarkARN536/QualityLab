package ru.mail.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.mail.pages.SendMailPage;
import ru.mail.webtestbase.TestBase;

public class SendMailTest extends TestBase {

    SendMailPage sendMailPage;

    @BeforeClass
    protected void BeforeClass() {
        sendMailPage = new SendMailPage();
    }

    @BeforeMethod
    protected void BeforeMethod() {
        sendMailPage.openPage();
        sendMailPage.authenticateUser("ivantest99", "123NaviTset99");
    }

    @Test(priority = 0)
    public void CheckSendEmail() {
        sendMailPage.sendMail("DarkARN@mail.ru", "TestMail", "Hello! It's me TestNGrio");
        Assert.assertTrue(sendMailPage.getTextFormLinkGoToInbox().contains("Перейти во Входящие"),"Не отправили письмо");
    }
}
