package contoller.subViewContoller;

import contoller.MainPageContoller;
import contoller.centerPanel.AddNewRoomContoller;
import contoller.centerPanel.AddUserContoller;
import contoller.centerPanel.EditDeleteUserContoller;
import contoller.centerPanel.ModifyDeleteRoomContoller;
import contoller.util.Loader;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ManageUsersButtonContoller implements Loader {

    public static AnchorPane CenterOpenPanel;
    public static Class<? extends MainPageContoller> aClass ;

    public void initialize(){
        AddUserContoller.CenterPanel=CenterOpenPanel;
        EditDeleteUserContoller.CenterPanel=CenterOpenPanel;
    }

    public void AddUserOnAction(ActionEvent actionEvent) throws IOException {
        topUiLoder("../view/CenterPanel/AddUser.fxml");
    }

    public void editDeleteOnAction(ActionEvent actionEvent) throws IOException {
        topUiLoder("../view/CenterPanel/editDeleteUser.fxml");
    }

    @Override
    public void topUiLoder(String location) throws IOException {
        CenterOpenPanel.getChildren().clear();
        Parent load = FXMLLoader.load(aClass.getResource(location));
        CenterOpenPanel.getChildren().add(load);
    }
}
