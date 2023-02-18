package ru.netology;

import PageObjects.DashboardPage;
import PageObjects.DataHelper;
import PageObjects.LoginPage;
import PageObjects.TransferPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

class TransferBankTest {
    DashboardPage dashboardPage = new DashboardPage();
    LoginPage loginPage = new LoginPage();
    DataHelper dataHelper = new DataHelper();
    TransferPage transferPage = new TransferPage();

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    void shouldSuccessTransferSecondCardToFirstCard() {
        loginPage.validLogin(dataHelper.getValidLogin(), dataHelper.getValidPassword(),
                dataHelper.getValidPushMessage());

        var amount = 100;
        var balanceFirstCard = dashboardPage.getFirstCardBalance();
        var balanceSecondCard = dashboardPage.getSecondCardBalance();

        dashboardPage.TransferTo(dataHelper.getIdFirstCard());
        transferPage.TransferFrom(dataHelper.getNumberSecondCard(), String.valueOf(amount));

        var actualBalanceFirstCard = dashboardPage.getFirstCardBalance();
        var actualBalanceSecondCard = dashboardPage.getSecondCardBalance();

        Assertions.assertEquals(balanceFirstCard + amount, actualBalanceFirstCard);
        Assertions.assertEquals(balanceSecondCard - amount, actualBalanceSecondCard);
    }

    @Test
    void shouldSuccessTransferFirstCardToSecondCard() {
        loginPage.validLogin(dataHelper.getValidLogin(), dataHelper.getValidPassword(),
                dataHelper.getValidPushMessage());

        var amount = 100;
        var balanceFirstCard = dashboardPage.getFirstCardBalance();
        var balanceSecondCard = dashboardPage.getSecondCardBalance();

        dashboardPage.TransferTo(dataHelper.getIdSecondCard());
        transferPage.TransferFrom(dataHelper.getNumberFirstCard(), String.valueOf(amount));

        var actualBalanceFirstCard = dashboardPage.getFirstCardBalance();
        var actualBalanceSecondCard = dashboardPage.getSecondCardBalance();

        Assertions.assertEquals(balanceFirstCard - amount, actualBalanceFirstCard);
        Assertions.assertEquals(balanceSecondCard + amount, actualBalanceSecondCard);
    }
}
