package module;

public class MealPack {

    private int mealPackNo;
    private String mealPackName;
    private double guestPrice;
    private double localPrice;
    private String guestPriceType;
    private String localPriceType;
    private boolean availbility;
    private String include;
    private String note;

    public MealPack(int mealPackNo, String mealPackName, double guestPrice, double localPrice, String guestPriceType, String localPriceType, boolean availbility, String include, String note) {
        this.mealPackNo = mealPackNo;
        this.mealPackName = mealPackName;
        this.guestPrice = guestPrice;
        this.localPrice = localPrice;
        this.guestPriceType = guestPriceType;
        this.localPriceType = localPriceType;
        this.availbility = availbility;
        this.include = include;
        this.note = note;
    }

    public MealPack(int mealPackNo, String mealPackName, double guestPrice, double localPrice, String guestPriceType, String localPriceType, boolean availbility) {
        this.mealPackNo = mealPackNo;
        this.mealPackName = mealPackName;
        this.guestPrice = guestPrice;
        this.localPrice = localPrice;
        this.guestPriceType = guestPriceType;
        this.localPriceType = localPriceType;
        this.availbility = availbility;
        this.include ="";
        this.note = "";
    }

    public MealPack(int mealPackNo, String mealPackName, double guestPrice, double localPrice, String guestPriceType, String localPriceType, boolean availbility, String include) {
        this.mealPackNo = mealPackNo;
        this.mealPackName = mealPackName;
        this.guestPrice = guestPrice;
        this.localPrice = localPrice;
        this.guestPriceType = guestPriceType;
        this.localPriceType = localPriceType;
        this.availbility = availbility;
        this.include = include;
    }

    public MealPack() {
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

    public double getGuestPrice() {
        return guestPrice;
    }

    public void setGuestPrice(double guestPrice) {
        this.guestPrice = guestPrice;
    }

    public double getLocalPrice() {
        return localPrice;
    }

    public void setLocalPrice(double localPrice) {
        this.localPrice = localPrice;
    }

    public String getGuestPriceType() {
        return guestPriceType;
    }

    public void setGuestPriceType(String guestPriceType) {
        this.guestPriceType = guestPriceType;
    }

    public String getLocalPriceType() {
        return localPriceType;
    }

    public void setLocalPriceType(String localPriceType) {
        this.localPriceType = localPriceType;
    }

    public boolean isAvailbility() {
        return availbility;
    }

    public void setAvailbility(boolean availbility) {
        this.availbility = availbility;
    }

    public String getInclude() {
        return include;
    }

    public void setInclude(String include) {
        this.include = include;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "MealPack{" +
                "mealPackNo=" + mealPackNo +
                ", mealPackName='" + mealPackName + '\'' +
                ", guestPrice=" + guestPrice +
                ", localPrice=" + localPrice +
                ", guestPriceType='" + guestPriceType + '\'' +
                ", localPriceType='" + localPriceType + '\'' +
                ", availbility=" + availbility +
                ", include='" + include + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
