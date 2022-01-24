package contoller.subViewContoller;

import contoller.MainPageContoller;
import contoller.centerPanel.AddNewRoomContoller;
import contoller.centerPanel.ModifyDeleteRoomContoller;
import contoller.util.Loader;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ManageRoomButtonsContoller implements Loader {

    public static AnchorPane CenterOpenPanel;
    public static Class<? extends MainPageContoller> aClass ;

    public void initialize(){
        AddNewRoomContoller.centerPane=CenterOpenPanel;
        ModifyDeleteRoomContoller.centerPane=CenterOpenPanel;
    }

    public void addRoomOnAction(ActionEvent actionEvent) throws IOException {
        topUiLoder("../view/CenterPanel/AddNewRoom.fxml");
    }

    public void modifyRoomOnAction(ActionEvent actionEvent) throws IOException {
        topUiLoder("../view/CenterPanel/ModifyDeleteRoom.fxml");
    }



    @Override
    public void topUiLoder(String location) throws IOException {
        CenterOpenPanel.getChildren().clear();
        Parent load = FXMLLoader.load(aClass.getResource(location));
        CenterOpenPanel.getChildren().add(load);
    }
}
