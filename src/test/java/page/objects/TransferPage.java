package page.objects;

import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    public void transferFrom(String numberCard, String amount) {
        $("[data-test-id='amount'] input").sendKeys(String.valueOf(amount));
        $("[data-test-id='from'] input").sendKeys(numberCard);
        $("[data-test-id='action-transfer']").click();
    }
}
