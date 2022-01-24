package contoller.mainOptionContoler;

import contoller.MainPageContoller;
import contoller.centerPanel.MarkAndDisMarkMaintainsContoller;
import contoller.subViewContoller.MarkRoomInMaintenanceContoller;
import contoller.subViewContoller.ReserveRoomContoller;
import contoller.util.Loader;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ReceptionistOptionContoller implements Loader {

    public static AnchorPane topPane;
    public static AnchorPane CenterOpenPanel;
    public static Class<? extends MainPageContoller> aClass ;

    public void initialize(){

        MarkRoomInMaintenanceContoller.CenterOpenPanel = CenterOpenPanel;
        MarkRoomInMaintenanceContoller.aClass = aClass;

        ReserveRoomContoller.CenterOpenPanel = CenterOpenPanel;
        ReserveRoomContoller.aClass = aClass;
    }

    public void ReserveARoomOnAction(ActionEvent actionEvent) throws IOException {
        topUiLoder("../view/subView/ReserveRoom.fxml");
    }

    public void RoomInMaintenanceOnAction(ActionEvent actionEvent) throws IOException {
        topUiLoder("../view/subView/MarkRoomInMaintenance.fxml");
    }

    @Override
    public void topUiLoder(String location) throws IOException {
        topPane.getChildren().clear();
        Parent load = FXMLLoader.load(aClass.getResource(location));
        topPane.getChildren().add(load);
    }
}
