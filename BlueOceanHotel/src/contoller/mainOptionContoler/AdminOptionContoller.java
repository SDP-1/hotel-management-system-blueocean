package contoller.mainOptionContoler;

import contoller.centerPanel.AddNewRoomContoller;
import contoller.centerPanel.ModifyDeleteRoomContoller;
import contoller.subViewContoller.*;
import contoller.util.Loader;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import contoller.MainPageContoller;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;


public class AdminOptionContoller implements Loader {

    public static AnchorPane topPane;
    public static AnchorPane centerPane;
    public static Class<? extends MainPageContoller> aClass ;

    public void initialize(){
        ManageRoomButtonsContoller.CenterOpenPanel = centerPane;
        ManageRoomButtonsContoller.aClass = aClass;

        ManageMealPackButtonsContoller.CenterOpenPanel=centerPane;
        ManageMealPackButtonsContoller.aClass=aClass;

        ManageUsersButtonContoller.CenterOpenPanel=centerPane;
        ManageUsersButtonContoller.aClass=aClass;

        MarkRoomInMaintenanceContoller.CenterOpenPanel =centerPane;
        MarkRoomInMaintenanceContoller.aClass=aClass;

        ReserveRoomContoller.CenterOpenPanel = centerPane;
        ReserveRoomContoller.aClass=aClass;

        IncomeReportsButtonsContoller.CenterOpenPanel = centerPane;
        IncomeReportsButtonsContoller.aClass = aClass;
    }

    public void manageRoomsOnAction(ActionEvent actionEvent) throws IOException {
        topUiLoder("../view/subView/ManageRoomButtons.fxml");
    }

    public void manageMealPackOnAction(ActionEvent actionEvent) throws IOException {
        topUiLoder("../view/subView/ManageMealPackButtons.fxml");
    }

    public void incomeReportOnAction(ActionEvent actionEvent) throws IOException {
        topUiLoder("../view/subView/IncomeReportsButtons.fxml");
    }

    public void ReserveARoomOnAction(ActionEvent actionEvent) throws IOException {
        topUiLoder("../view/subView/ReserveRoom.fxml");
    }

    public void RoomInMaintenanceOnAction(ActionEvent actionEvent) throws IOException {
        topUiLoder("../view/subView/MarkRoomInMaintenance.fxml");
    }

    public void manageUsesOnAction(ActionEvent actionEvent) throws IOException {
        topUiLoder("../view/subView/ManageUsersButton.fxml");
    }

    @Override
    public void topUiLoder(String location) throws IOException {
        topPane.getChildren().clear();
        centerPane.getChildren().clear();
        Parent load = FXMLLoader.load(aClass.getResource(location));
        topPane.getChildren().add(load);
    }


}
