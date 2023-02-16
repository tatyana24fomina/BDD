package ru.netology;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class TransferBankTest {
    String idFirstCard = "92df3f1c-a033-48e6-8390-206f6b1f56c0";
    String idSecondCard = "0f3f5c2a-249e-4c3d-8287-09f7a039391d";
    String numberFirstCard = "5559 0000 0000 0001";
    String numberSecondCard = "5559 0000 0000 0002";
    DashboardPage page = new DashboardPage();

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    void shouldSuccessTransferSecondCardToFirstCard() {
        login();

        int amount = 100;
        int balanceFirstCard = page.getFirstCardBalance();
        int balanceSecondCard = page.getSecondCardBalance();

        $("[data-test-id='" + idFirstCard + "'] button").click();
        $("[data-test-id='amount'] input").sendKeys(String.valueOf(amount));
        $("[data-test-id='from'] input").sendKeys(numberSecondCard);
        $("[data-test-id='action-transfer']").click();

        var actualBalanceFirstCard = page.getFirstCardBalance();
        var actualBalanceSecondCard = page.getSecondCardBalance();

        Assertions.assertEquals(balanceFirstCard + amount, actualBalanceFirstCard);
        Assertions.assertEquals(balanceSecondCard - amount, actualBalanceSecondCard);
    }

    @Test
    void shouldSuccessTransferFirstCardToSecondCard() {
        login();

        var amount = 100;
        var balanceFirstCard = page.getFirstCardBalance();
        var balanceSecondCard = page.getSecondCardBalance();

        $("[data-test-id='" + idSecondCard + "'] button").click();
        $("[data-test-id='amount'] input").sendKeys(String.valueOf(amount));
        $("[data-test-id='from'] input").sendKeys(numberFirstCard);
        $("[data-test-id='action-transfer']").click();

        var actualBalanceFirstCard = page.getFirstCardBalance();
        var actualBalanceSecondCard = page.getSecondCardBalance();

        Assertions.assertEquals(balanceFirstCard - amount, actualBalanceFirstCard);
        Assertions.assertEquals(balanceSecondCard + amount, actualBalanceSecondCard);
    }

    void login() {
        $("[data-test-id='login'] input").sendKeys("vasya");
        $("[data-test-id='password'] input").sendKeys("qwerty123");
        $("[data-test-id='action-login']").click();
        $("[data-test-id='code'] input").sendKeys("12345");
        $("[data-test-id='action-verify']").click();
    }
}
