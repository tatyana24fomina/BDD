package PageObjects;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    public void validLogin(String login, String password, String pushMessage) {
        $("[data-test-id='login'] input").sendKeys(login);
        $("[data-test-id='password'] input").sendKeys(password);
        $("[data-test-id='action-login']").click();
        $("[data-test-id='code'] input").sendKeys(pushMessage);
        $("[data-test-id='action-verify']").click();
    }
}
