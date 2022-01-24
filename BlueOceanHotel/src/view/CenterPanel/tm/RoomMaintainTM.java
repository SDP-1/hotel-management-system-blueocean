package view.CenterPanel.tm;


import javafx.scene.control.Button;

public class RoomMaintainTM {
        private int roomNumbe;
    private String roomCatagary;
    private String roomLocation;
    private String ac;
    private String maintain;
    private Button btn;

    public RoomMaintainTM(int roomNumbe, String roomCatagary, String roomLocation, String ac, String maintain, Button btn) {
        this.roomNumbe = roomNumbe;
        this.roomCatagary = roomCatagary;
        this.roomLocation = roomLocation;
        this.ac = ac;
        this.maintain = maintain;
        this.btn = btn;
    }

    public RoomMaintainTM() {
    }

    public int getRoomNumbe() {
        return roomNumbe;
    }

    public void setRoomNumbe(int roomNumbe) {
        this.roomNumbe = roomNumbe;
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

    public String getAc() {
        return ac;
    }

    public void setAc(String ac) {
        this.ac = ac;
    }

    public String getMaintain() {
        return maintain;
    }

    public void setMaintain(String maintain) {
        this.maintain = maintain;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }


}
