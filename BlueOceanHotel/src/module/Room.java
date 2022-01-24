package module;

public class Room {
    private final int number;
    private double guestPrice;
    private String guestPriceType;
    private double localPrice;
    private String localPriceType;
    private boolean AC;
    private String roomCatagary;
    private String roomLocation;
    private String note;
    private boolean maintenance;
    private boolean availability;


    public Room(int number, double guestPrice, String guestPriceType, double localPrice, String localPriceType, boolean AC, String roomCatagary, String roomLocation, String note, boolean maintenance, boolean availability) {
        this.number = number;
        this.guestPrice = guestPrice;
        this.guestPriceType = guestPriceType;
        this.localPrice = localPrice;
        this.localPriceType = localPriceType;
        this.AC = AC;
        this.roomCatagary = roomCatagary;
        this.roomLocation = roomLocation;
        this.note = note;
        this.maintenance = maintenance;
        this.availability = availability;
    }

    public Room(int number, double guestPrice, String guestPriceType, double localPrice, String localPriceType, boolean AC, String roomCatagary, String roomLocation, String note) {
        this.number = number;
        this.guestPrice = guestPrice;
        this.guestPriceType = guestPriceType;
        this.localPrice = localPrice;
        this.localPriceType = localPriceType;
        this.AC = AC;
        this.roomCatagary = roomCatagary;
        this.roomLocation = roomLocation;
        this.note = note;
        this.maintenance = false;
        this.availability = true;
    }


    public boolean isMaintenance() {
        return maintenance;
    }

    public void setMaintenance(boolean maintenance) {
        this.maintenance = maintenance;
    }
    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
    public int getNumber() {
        return number;
    }

    public double getGuestPrice() {
        return guestPrice;
    }

    public void setGuestPrice(double guestPrice) {
        this.guestPrice = guestPrice;
    }

    public String getGuestPriceType() {
        return guestPriceType;
    }

    public void setGuestPriceType(String guestPriceType) {
        this.guestPriceType = guestPriceType;
    }

    public double getLocalPrice() {
        return localPrice;
    }

    public void setLocalPrice(double localPrice) {
        this.localPrice = localPrice;
    }

    public String getLocalPriceType() {
        return localPriceType;
    }

    public void setLocalPriceType(String localPriceType) {
        this.localPriceType = localPriceType;
    }

    public boolean isAC() {
        return AC;
    }

    public void setAC(boolean AC) {
        this.AC = AC;
    }

    public String getRoomCatagary() {
        return roomCatagary;
    }

    public void setRoomCatagary(String roomCatagary) {
        this.roomCatagary = roomCatagary;
    }

    public String getRoomLocation() {
        return roomLocation;
    }

    public void setRoomLocation(String roomLocation) {
        this.roomLocation = roomLocation;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Room{" +
                "number=" + number +
                ", guestPrice=" + guestPrice +
                ", guestPriceType=" + guestPriceType +
                ", localPrice=" + localPrice +
                ", localPriceType=" + localPriceType +
                ", AC=" + AC +
                ", roomCatagary='" + roomCatagary + '\'' +
                ", roomLocation='" + roomLocation + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
