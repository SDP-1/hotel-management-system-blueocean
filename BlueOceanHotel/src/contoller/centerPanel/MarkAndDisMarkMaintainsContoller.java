package contoller.centerPanel;

import db.RoomDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import module.Room;
import view.CenterPanel.tm.RoomMaintainTM;


import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class MarkAndDisMarkMaintainsContoller {
    public static AnchorPane CenterOpenPanel;
    public TableView <RoomMaintainTM>tableRoomDeatils;
    public TableColumn clmRoomNumber;
    public TableColumn clmRoomCatagary;
    public TableColumn clmLocation;
    public TableColumn clmAC;
    public TableColumn clmEnableDisable;
    public CheckBox showOnlyAvailableRooms;
    public TableColumn clmMaintain;

    public void initialize(){
        clmRoomNumber.setCellValueFactory(new PropertyValueFactory<>("roomNumbe"));
        clmRoomCatagary.setCellValueFactory(new PropertyValueFactory<>("roomCatagary"));
        clmLocation.setCellValueFactory(new PropertyValueFactory<>("roomLocation"));
        clmAC.setCellValueFactory(new PropertyValueFactory<>("ac"));
        clmMaintain.setCellValueFactory(new PropertyValueFactory<>("maintain"));
        clmEnableDisable.setCellValueFactory(new PropertyValueFactory<>("btn"));

        showOnlyAvailableRooms.setSelected(true);
        getAction();
    }

    private void loadAll(){
        ObservableList<RoomMaintainTM> observableList = FXCollections.observableArrayList();

        for (Room room : RoomDatabase.rooms){
            if(room.isAvailability()) {
                Button btn = new Button();
                btn.setPrefSize(192, 31);
                btn.setTextFill(Color.WHITE);
                // btn.setFont(new Font(bolt));

                if (room.isAvailability() && room.isMaintenance() == false) {
                    btn.setText("On");
                    btn.setStyle("-fx-background-color: Blue");
                } else {
                    btn.setText("Off");
                    btn.setStyle("-fx-background-color: Red");
                }

                RoomMaintainTM tm = new RoomMaintainTM(room.getNumber(), room.getRoomCatagary(), room.getRoomLocation(), room.isAC() ? "A/C" : "non A/C", room.isMaintenance() ? "Yes" : "No", btn);
                observableList.add(tm);

                btn.setOnAction((event) -> {

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure?", ButtonType.YES, ButtonType.NO);
                    Optional<ButtonType> buttonType = alert.showAndWait();

                    if (buttonType.get().equals(ButtonType.YES)) {
                        if (btn.getText().equals("On")) {
                            room.setMaintenance(true);
                            btn.setStyle("-fx-background-color: Red");
                        } else {
                            room.setMaintenance(false);
                            btn.setStyle("-fx-background-color: Blue");
                        }
                        loadAll();
                    }
                });
            }
        }
        tableRoomDeatils.setItems(observableList);
    }

    private void loadOnlyAvailble() {
        ObservableList<RoomMaintainTM> observableList = FXCollections.observableArrayList();

        for (Room room : RoomDatabase.rooms) {
            Button btn = new Button();
            btn.setPrefSize(192, 31);
            btn.setTextFill(Color.WHITE);
            // btn.setFont(new Font(bolt));

            btn.setText("On");
            btn.setStyle("-fx-background-color: Blue");

            if (room.isAvailability() && room.isMaintenance()==false) {
                RoomMaintainTM tm = new RoomMaintainTM(room.getNumber(), room.getRoomCatagary(), room.getRoomLocation(), room.isAC() ? "A/C" : "non A/C", room.isMaintenance() ? "Yes" : "No", btn);
                observableList.add(tm);
            }

                btn.setOnAction((event) -> {

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure?", ButtonType.YES, ButtonType.NO);
                    Optional<ButtonType> buttonType = alert.showAndWait();

                    if (buttonType.get().equals(ButtonType.YES)) {
                            room.setMaintenance(true);
                            loadOnlyAvailble();
                    }
                });
            }
        tableRoomDeatils.setItems(observableList);
    }


    public void showOnlyAvailableRoomsOnAction(ActionEvent actionEvent) {
        getAction();
    }

    private void getAction(){
        if(showOnlyAvailableRooms.isSelected()){
            loadOnlyAvailble();
        }else{
            loadAll();
        }
    }

    public void closeOnActon(MouseEvent mouseEvent) {
        CenterOpenPanel.getChildren().clear();
    }


}
