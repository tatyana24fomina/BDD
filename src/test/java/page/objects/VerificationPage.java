package page.objects;

import data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    public void validVerification(DataHelper.VerificationCode verificationCode) {
        $("[data-test-id='code'] input").sendKeys(verificationCode.getCode());
        $("[data-test-id='action-verify']").click();
    }

}
