package contoller.centerPanel;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import db.RoomDatabase;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import module.Room;

import java.util.Optional;

public class ModifyDeleteRoomContoller {

    public JFXComboBox dolerOrRsGuest;
    public JFXTextField txtguestPrice;
    public JFXComboBox dolerOrRsLocal;
    public JFXTextField txtLocalPrice;
    public RadioButton rdBtnAvailable;
    public ToggleGroup Available;
    public JFXComboBox roomCatagory;
    public JFXTextField txtRoomLocation;
    public TextField txtRoomNumber;
    public TextArea txtnote;
    public RadioButton rdBtnNoAvailable;

    public static AnchorPane centerPane;
    public Button btnSave;
    public Button btnClear;
    public Button btnSearch;
    public Button btnDelete;

    public void initialize(){
        dolerOrRsGuest.getItems().addAll("$","Rs");
        dolerOrRsGuest.getSelectionModel().select(0);
        dolerOrRsLocal.getItems().addAll("$","Rs");
        dolerOrRsLocal.getSelectionModel().select(1);
        roomCatagory.getItems().addAll("Single Room","Double Room","Triple Room","Quad Room");
        roomCatagory.getSelectionModel().select(0);

        btnSearch.setDisable(true);
        disable();
    }

    private void disable() {
        btnDelete.setDisable(true);
        btnClear.setDisable(true);
        btnSave.setDisable(true);
        dolerOrRsGuest.setDisable(true);
        dolerOrRsLocal.setDisable(true);
        roomCatagory.setDisable(true);
        rdBtnAvailable.setDisable(true);
        txtRoomLocation.setDisable(true);
        txtnote.setDisable(true);
        txtguestPrice.setDisable(true);
        txtLocalPrice.setDisable(true);
        rdBtnNoAvailable.setDisable(true);
    }

    private void enable(){
        btnDelete.setDisable(false);
        btnClear.setDisable(false);
        btnSave.setDisable(false);
        dolerOrRsGuest.setDisable(false);
        dolerOrRsLocal.setDisable(false);
        roomCatagory.setDisable(false);
        rdBtnAvailable.setDisable(false);
        txtRoomLocation.setDisable(false);
        txtRoomNumber.setDisable(false);
        txtnote.setDisable(false);
        txtguestPrice.setDisable(false);
        txtLocalPrice.setDisable(false);
        rdBtnNoAvailable.setDisable(false);
    }

    public void saveOnAction(ActionEvent actionEvent) {
        int roomNumber;
        double guestPrice;
        double localPrice;

        try{
            guestPrice = Double.parseDouble(txtguestPrice.getText());
        }catch(NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Guest Price is Invalid ");
            alert.showAndWait();
            return;
        }

        try{
            localPrice = Double.parseDouble(txtLocalPrice.getText());
        }catch(NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Local Price is Invalid ");
            alert.showAndWait();
            return;
        }

        try{
            roomNumber = Integer.parseInt(txtRoomNumber.getText());
            for (int i = 0; i < RoomDatabase.rooms.size(); i++) {
                if(RoomDatabase.rooms.get(i).getNumber() == roomNumber){
                    RoomDatabase.rooms.set(i,new Room(
                            roomNumber,
                            guestPrice,
                            (String) dolerOrRsGuest.getValue(),
                            localPrice,
                            (String) dolerOrRsLocal.getValue(),
                            ((RadioButton)Available.getSelectedToggle()).getText().equals("Available"),
                            (String) roomCatagory.getValue(),
                            txtRoomLocation.getText(),
                            txtnote.getText()));


                    Alert alert = new Alert(Alert.AlertType.INFORMATION,"Welcome..\t\tsave successfully.");
                    alert.showAndWait();
                    clear();
                    disable();
                    return;
                }
            }
        }catch(NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Room Number is Invalid ");
            alert.showAndWait();
            return;
        }
        clear();
    }

    public void clearOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Do you want to clear ?",ButtonType.YES ,ButtonType.NO);
        alert.setTitle("Clear");
        Optional<ButtonType> buttonType = alert.showAndWait();
        if(buttonType.get().equals(ButtonType.YES)) {
            clear();
            disable();
        }
    }

    public void closeOnActon(MouseEvent mouseEvent) {
        centerPane.getChildren().clear();
    }

    private void fill(Room room) {
        if(room.getGuestPriceType().equals("$")) {
            dolerOrRsGuest.getSelectionModel().select(0);
        }else{
            dolerOrRsGuest.getSelectionModel().select(1);
        }

        if(room.getLocalPriceType().equals("Rs")){
            dolerOrRsLocal.getSelectionModel().select(1);
        }else{
            dolerOrRsLocal.getSelectionModel().select(0);
        }

        if(room.getRoomCatagary().equals("Single Room")){
            roomCatagory.getSelectionModel().select(0);
        }else if(room.getRoomCatagary().equals("Double Room")){
            roomCatagory.getSelectionModel().select(1);
        }else if(room.getRoomCatagary().equals("Triple Room")){
            roomCatagory.getSelectionModel().select(2);
        }else if(room.getRoomCatagary().equals("Quad Room")){
            roomCatagory.getSelectionModel().select(3);
        }

       if(room.isAC()){
           rdBtnAvailable.setSelected(true);
       }else{
           rdBtnNoAvailable.setSelected(true);
       }

        txtRoomLocation.setText(room.getRoomLocation());
        txtRoomNumber.setText(String.valueOf(room.getNumber()));
        txtnote.setText(room.getNote());
        txtguestPrice.setText(String.valueOf(room.getGuestPrice()));
        txtLocalPrice.setText(String.valueOf(room.getLocalPrice()));
    }

    private void clear(){
        dolerOrRsGuest.getSelectionModel().select(0);
        dolerOrRsLocal.getSelectionModel().select(1);
        roomCatagory.getSelectionModel().select(0);
        rdBtnAvailable.setSelected(true);
        txtRoomLocation.clear();
        txtRoomNumber.clear();
        txtnote.clear();
        txtguestPrice.clear();
        txtLocalPrice.clear();
    }


    public void searchOnAction(ActionEvent actionEvent) {
        search();
    }
    private void search(){
        try{
            int searchNumber = Integer.parseInt(txtRoomNumber.getText());
            for (int i = 0; i < RoomDatabase.rooms.size(); i++) {
                if(RoomDatabase.rooms.get(i).getNumber() == searchNumber){
                    fill(RoomDatabase.rooms.get(i));
                    return;
                }
            }
            Alert alert = new Alert(Alert.AlertType.ERROR,"Room Number is not Exists...");
            alert.showAndWait();
        }catch(NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Room Number is Invalid ");
            alert.showAndWait();
            return;
        }
    }


    public void rommNumberChangDitect(MouseEvent mouseEvent) {
        disable();
        clear();
    }

    public void DeleteOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Do you want Delete ?",ButtonType.YES ,ButtonType.NO);
        alert.setTitle("Delete");
        Optional<ButtonType> buttonType = alert.showAndWait();
        if(buttonType.get().equals(ButtonType.YES)) {
            for (int i = 0; i < RoomDatabase.rooms.size(); i++) {
                if(RoomDatabase.rooms.get(i).getNumber() == Integer.parseInt(txtRoomNumber.getText())){
                    RoomDatabase.rooms.remove(i);
                    clear();
                    disable();
                }
            }
        }

    }

    public void roomNumberEnted(KeyEvent keyEvent) {

        btnSearch.setDisable(false);
        if(keyEvent.getCode().equals(KeyCode.ENTER)){
            enable();
            search();
        }
    }

    public void searchClick(MouseEvent mouseEvent) {
        enable();
    }
}
