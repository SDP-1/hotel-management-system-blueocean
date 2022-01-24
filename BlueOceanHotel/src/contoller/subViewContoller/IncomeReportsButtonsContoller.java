package contoller.subViewContoller;

import contoller.MainPageContoller;
import contoller.centerPanel.IncomeReportsContoller;
import contoller.util.Loader;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class IncomeReportsButtonsContoller implements Loader {
    public static AnchorPane CenterOpenPanel;
    public static Class<? extends MainPageContoller> aClass ;

    public void initialize() throws IOException {
        IncomeReportsContoller.CenterOpenPanel = CenterOpenPanel;
        topUiLoder("../view/CenterPanel/IncomeReports.fxml");
    }

    public void viewIncomeReportsOnAction(ActionEvent actionEvent) throws IOException {
        topUiLoder("../view/CenterPanel/IncomeReports.fxml");
    }

    @Override
    public void topUiLoder(String location) throws IOException {
        CenterOpenPanel.getChildren().clear();
        Parent load = FXMLLoader.load(aClass.getResource(location));
        CenterOpenPanel.getChildren().add(load);
    }
}
