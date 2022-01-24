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

public class ChackRoomAvailbilityContoller{

    public TableView tableRoomDeatils;
    public TableColumn clmRoomNumber;
    public TableColumn clmRoomCatagary;
    public TableColumn clmLocation;
    public TableColumn clmAvailble;
    public TableColumn clmEnableDisable;
    public TableColumn clmAC;
    public CheckBox showOnlyAvailableRooms;

    public static AnchorPane CenterOpenPanel;


    public void initialize(){
        clmRoomNumber.setCellValueFactory(new PropertyValueFactory<>("roomNumbe"));
        clmRoomCatagary.setCellValueFactory(new PropertyValueFactory<>("roomCatagary"));
        clmLocation.setCellValueFactory(new PropertyValueFactory<>("roomLocation"));
        clmAC.setCellValueFactory(new PropertyValueFactory<>("ac"));
        clmAvailble.setCellValueFactory(new PropertyValueFactory<>("maintain"));
        clmEnableDisable.setCellValueFactory(new PropertyValueFactory<>("btn"));

        showOnlyAvailableRooms.setSelected(true);
        getAction();
    }

    public void closeOnActon(MouseEvent mouseEvent) {
        CenterOpenPanel.getChildren().clear();
    }

    public void showOnlyAvailableRoomsOnAction(ActionEvent actionEvent) {
        getAction();
    }

    private void loadAll(){
        ObservableList<RoomMaintainTM> observableList = FXCollections.observableArrayList();

        for (Room room : RoomDatabase.rooms){
            Button btn = new Button();
            btn.setPrefSize(192,31);
            btn.setTextFill(Color.WHITE);
            btn.setText("Room Details");

            if(room.isAvailability() && room.isMaintenance() == false){
                btn.setStyle("-fx-background-color: Green");
            }else{
                btn.setStyle("-fx-background-color: Red");
            }

            RoomMaintainTM tm = new RoomMaintainTM(room.getNumber(),room.getRoomCatagary(),room.getRoomLocation(),room.isAC()?"A/C":"non A/C",room.isMaintenance()?room.isAvailability() ? "Maintain" : "Not Available" :room.isAvailability() ? "Available" : "Not Available",btn);
            observableList.add(tm);

            btn.setOnAction((event) -> {
                roomDetailsButton(btn , room);
            });
        }

        tableRoomDeatils.setItems(observableList);
    }

    private void loadOnlyAvailble() {
        ObservableList<RoomMaintainTM> observableList = FXCollections.observableArrayList();

        for (Room room : RoomDatabase.rooms) {
            Button btn = new Button();
            btn.setPrefSize(192, 31);
            btn.setTextFill(Color.WHITE);
            btn.setText("Room Details");
            btn.setStyle("-fx-background-color: Green");

            if (room.isAvailability() && room.isMaintenance() == false) {
                RoomMaintainTM tm = new RoomMaintainTM(room.getNumber(), room.getRoomCatagary(), room.getRoomLocation(), room.isAC() ? "A/C" : "non A/C", room.isMaintenance()?room.isAvailability() ? "Maintain" : "Not Available" :room.isAvailability() ? "Available" : "Not Available", btn);
                observableList.add(tm);
            }
            roomDetailsButton(btn , room);
        }
        tableRoomDeatils.setItems(observableList);
    }

    private void roomDetailsButton(Button btn,Room room){
        btn.setOnAction((event) -> {
            Alert alert = new Alert(Alert.AlertType.NONE,
                    "Room Number \t:- "+room.getNumber()+
                            "\n\nguest Price \t\t:-"+room.getGuestPrice()+" "+room.getGuestPriceType()+
                            "\n\nLocal Price \t\t:-"+room.getLocalPrice()+" "+room.getLocalPriceType()+
                            "\n\nA/C \t\t\t\t:-"+(room.isAC()?"Available":"NoAvailable")+
                            "\n\nRoom category \t:-"+ room.getRoomCatagary()+
                            "\n\nRoom Location \t:-"+room.getRoomLocation()+
                            "\n\nNote \t\t\t:-"+room.getNote(),
                    ButtonType.OK );

            alert.setTitle("Room Details");
            alert.show();
        });
    }


    private void getAction(){
        if(showOnlyAvailableRooms.isSelected()){
            loadOnlyAvailble();
        }else{
            loadAll();
        }
    }


}
