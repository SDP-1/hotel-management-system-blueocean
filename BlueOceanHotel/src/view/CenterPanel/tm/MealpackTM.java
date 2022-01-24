package view.CenterPanel.tm;

import javafx.scene.control.Button;

public class MealpackTM {
    private int mealPackNo;
    private String mealPackName;
    private String guestPrice;
    private String localPrice;
    private String availbility;
    private String include;
    private Button btn;

    public MealpackTM() {
    }

    public MealpackTM(int mealPackNo, String mealPackName, String guestPrice, String localPrice, String availbility, String include, Button btn) {
        this.mealPackNo = mealPackNo;
        this.mealPackName = mealPackName;
        this.guestPrice = guestPrice;
        this.localPrice = localPrice;
        this.availbility = availbility;
        this.include = include;
        this.btn = btn;
    }

    public int getMealPackNo() {
        return mealPackNo;
    }

    public void setMealPackNo(int mealPackNo) {
        this.mealPackNo = mealPackNo;
    }

    public String getMealPackName() {
        return mealPackName;
    }

    public void setMealPackName(String mealPackName) {
        this.mealPackName = mealPackName;
    }

    public String getGuestPrice() {
        return guestPrice;
    }

    public void setGuestPrice(String guestPrice) {
        this.guestPrice = guestPrice;
    }

    public String getLocalPrice() {
        return localPrice;
    }

    public void setLocalPrice(String localPrice) {
        this.localPrice = localPrice;
    }

    public String getAvailbility() {
        return availbility;
    }

    public void setAvailbility(String availbility) {
        this.availbility = availbility;
    }

    public String getInclude() {
        return include;
    }

    public void setInclude(String include) {
        this.include = include;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
