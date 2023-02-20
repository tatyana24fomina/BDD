package ru.netology;

import page.objects.DashboardPage;
import data.DataHelper;
import page.objects.LoginPage;
import page.objects.TransferPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.objects.VerificationPage;

import static com.codeborne.selenide.Selenide.open;

class TransferBankTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    void shouldSuccessTransferSecondCardToFirstCard() {
        LoginPage loginPage = new LoginPage();
        loginPage.validLogin(DataHelper.getAuthInfo());
        VerificationPage verificationPage = new VerificationPage();
        verificationPage.validVerification(DataHelper.getVerificationCode());

        var amount = 100;
        DashboardPage dashboardPage = new DashboardPage();
        var balanceFirstCard = dashboardPage.getFirstCardBalance();
        var balanceSecondCard = dashboardPage.getSecondCardBalance();

        dashboardPage.transferTo(DataHelper.getIdFirstCard());

        TransferPage transferPage = new TransferPage();
        transferPage.transferFrom(DataHelper.getNumberSecondCard(), String.valueOf(amount));

        var actualBalanceFirstCard = dashboardPage.getFirstCardBalance();
        var actualBalanceSecondCard = dashboardPage.getSecondCardBalance();

        Assertions.assertEquals(balanceFirstCard + amount, actualBalanceFirstCard);
        Assertions.assertEquals(balanceSecondCard - amount, actualBalanceSecondCard);
    }

    @Test
    void shouldSuccessTransferFirstCardToSecondCard() {
        LoginPage loginPage = new LoginPage();
        loginPage.validLogin(DataHelper.getAuthInfo());
        VerificationPage verificationPage = new VerificationPage();
        verificationPage.validVerification(DataHelper.getVerificationCode());

        var amount = 100;
        DashboardPage dashboardPage = new DashboardPage();
        var balanceFirstCard = dashboardPage.getFirstCardBalance();
        var balanceSecondCard = dashboardPage.getSecondCardBalance();

        dashboardPage.transferTo(DataHelper.getIdSecondCard());

        TransferPage transferPage = new TransferPage();
        transferPage.transferFrom(DataHelper.getNumberFirstCard(), String.valueOf(amount));

        var actualBalanceFirstCard = dashboardPage.getFirstCardBalance();
        var actualBalanceSecondCard = dashboardPage.getSecondCardBalance();

        Assertions.assertEquals(balanceFirstCard - amount, actualBalanceFirstCard);
        Assertions.assertEquals(balanceSecondCard + amount, actualBalanceSecondCard);
    }
}
