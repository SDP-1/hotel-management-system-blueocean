package contoller.subViewContoller;

import contoller.MainPageContoller;
import contoller.centerPanel.AddUserContoller;
import contoller.centerPanel.EditDeleteUserContoller;
import contoller.centerPanel.MarkAndDisMarkMaintainsContoller;
import contoller.util.Loader;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MarkRoomInMaintenanceContoller implements Loader {

    public static AnchorPane CenterOpenPanel;
    public static Class<? extends MainPageContoller> aClass ;

    public void initialize() throws IOException {
        MarkAndDisMarkMaintainsContoller.CenterOpenPanel=CenterOpenPanel;
        topUiLoder("../view/CenterPanel/MarkAndDisMarkMaintains.fxml");
    }

    public void markMaintenanceRoomsOnAction(ActionEvent actionEvent) throws IOException {
        topUiLoder("../view/CenterPanel/MarkAndDisMarkMaintains.fxml");
    }

    @Override
    public void topUiLoder(String location) throws IOException {
        CenterOpenPanel.getChildren().clear();
        Parent load = FXMLLoader.load(aClass.getResource(location));
        CenterOpenPanel.getChildren().add(load);
    }


}
