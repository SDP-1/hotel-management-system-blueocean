package contoller.subViewContoller;

import contoller.MainPageContoller;
import contoller.centerPanel.*;
import contoller.util.Loader;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ReserveRoomContoller implements Loader {

    public static AnchorPane CenterOpenPanel;
    public static Class<? extends MainPageContoller> aClass ;

    public void initialize(){
        ChackRoomAvailbilityContoller.CenterOpenPanel = CenterOpenPanel;
        MealPackDetailsContoller.CenterOpenPanel = CenterOpenPanel;
        AddBookingContoller.CenterOpenPanel=CenterOpenPanel;
        ModifyDeleteBookingContoller.CenterPane = CenterOpenPanel;
        AllBookingContoller.CenterOpenPanel = CenterOpenPanel;
    }

    public void bookingRoomOnAction(ActionEvent actionEvent) throws IOException {
        topUiLoder("../view/CenterPanel/AddBooking.fxml");
    }

    public void ChackRoomAvailabilityOnAction(ActionEvent actionEvent) throws IOException {
        topUiLoder("../view/CenterPanel/ChackRoomAvailability.fxml");
    }

    public void mealPackDetailsOnAction(ActionEvent actionEvent) throws IOException {
        topUiLoder("../view/CenterPanel/MealPackDetails.fxml");
    }

    public void modyfyBookingOnAction(ActionEvent actionEvent) throws IOException {
        topUiLoder("../view/CenterPanel/ModifyDeleteBooking.fxml");
    }

    public void allBookingOnAction(ActionEvent actionEvent) throws IOException {
        topUiLoder("../view/CenterPanel/AllBooking.fxml");
    }

    @Override
    public void topUiLoder(String location) throws IOException {
        CenterOpenPanel.getChildren().clear();
        Parent load = FXMLLoader.load(aClass.getResource(location));
        CenterOpenPanel.getChildren().add(load);
    }



}
