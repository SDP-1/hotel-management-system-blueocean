package contoller.subViewContoller;

import contoller.MainPageContoller;
import contoller.centerPanel.AddNewMealPackContoller;
import contoller.centerPanel.ModyfyDeleteMealPackContoller;
import contoller.util.Loader;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ManageMealPackButtonsContoller implements Loader {
    public static AnchorPane CenterOpenPanel;
    public static Class<? extends MainPageContoller> aClass ;

    public void initialize(){
        AddNewMealPackContoller.CenterPane=CenterOpenPanel;
        ModyfyDeleteMealPackContoller.CenterPane=CenterOpenPanel;
    }

    public void addNewPackOnAction(ActionEvent actionEvent) throws IOException {
        topUiLoder("../view/CenterPanel/AddNewMealPack.fxml");
    }

    public void modifyDeletePackOnAction(ActionEvent actionEvent) throws IOException {
        topUiLoder("../view/CenterPanel/ModyfyDeleteMealPack.fxml");
    }

    @Override
    public void topUiLoder(String location) throws IOException {
        CenterOpenPanel.getChildren().clear();
        Parent load = FXMLLoader.load(aClass.getResource(location));
        CenterOpenPanel.getChildren().add(load);
    }
}
