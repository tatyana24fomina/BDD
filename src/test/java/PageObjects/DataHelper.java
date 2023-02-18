package PageObjects;

public class DataHelper {

    private String idFirstCard = "92df3f1c-a033-48e6-8390-206f6b1f56c0";
    private String idSecondCard = "0f3f5c2a-249e-4c3d-8287-09f7a039391d";
    private String numberFirstCard = "5559 0000 0000 0001";
    private String numberSecondCard = "5559 0000 0000 0002";

    public String getIdFirstCard() {
        return idFirstCard;
    }

    public String getIdSecondCard() {
        return idSecondCard;
    }

    public String getNumberFirstCard() {
        return numberFirstCard;
    }

    public String getNumberSecondCard() {
        return numberSecondCard;
    }

    public String getValidLogin() {
        return "vasya";
    }

    public String getValidPassword() {
        return "qwerty123";
    }

    public String getValidPushMessage() {
        return "12345";
    }
}
