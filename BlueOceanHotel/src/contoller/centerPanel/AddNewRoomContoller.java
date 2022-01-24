package contoller.centerPanel;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import module.Room;
import db.RoomDatabase;

import java.util.Optional;

public class AddNewRoomContoller{

    public JFXComboBox <String>dolerOrRsLocal;
    public JFXComboBox <String>dolerOrRsGuest;
    public ToggleGroup Available;
    public JFXComboBox roomCatagory;
    public JFXTextField txtRoomLocation;
    public TextField txtRoomNumber;
    public TextArea txtnote;
    public JFXTextField txtguestPrice;
    public JFXTextField txtLocalPrice;
    public RadioButton rdBtnAvailable;

    public static AnchorPane centerPane;

    public void initialize(){
        dolerOrRsGuest.getItems().addAll("$","Rs");
        dolerOrRsGuest.getSelectionModel().select(0);
        dolerOrRsLocal.getItems().addAll("$","Rs");
        dolerOrRsLocal.getSelectionModel().select(1);
        roomCatagory.getItems().addAll("Single Room","Double Room","Triple Room","Quad Room");
        roomCatagory.getSelectionModel().select(0);
    }

    public void saveOnAction(ActionEvent actionEvent) {
        int roomNumber;
        double guestPrice;
        double localPrice;

        try{
            roomNumber = Integer.parseInt(txtRoomNumber.getText());
            for (int i = 0; i < RoomDatabase.rooms.size(); i++) {
                if(RoomDatabase.rooms.get(i).getNumber() == roomNumber){
                    Alert alert = new Alert(Alert.AlertType.ERROR,"Room Number is already exists... ");
                    alert.showAndWait();
                    return;
                }
            }
        }catch(NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Room Number is Invalid ");
            alert.showAndWait();
            return;
        }

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

        RoomDatabase.rooms.add(new Room(
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
    }

    public void clearOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Do you want to clear ?",ButtonType.YES ,ButtonType.NO);
        alert.setTitle("Clear");
        Optional<ButtonType> buttonType = alert.showAndWait();
        if(buttonType.get().equals(ButtonType.YES)) {
            clear();
        }
    }

    public void closeOnActon(MouseEvent mouseEvent) {
        centerPane.getChildren().clear();
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



}
