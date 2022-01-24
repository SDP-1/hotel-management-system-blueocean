package contoller;

import contoller.mainOptionContoler.AdminOptionContoller;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;

import javafx.util.Duration;
import module.User;
import contoller.mainOptionContoler.ReceptionistOptionContoller;

public class MainPageContoller{
    public AnchorPane mainPane;
    public AnchorPane loadOptionPane;
    public AnchorPane topPane;
    public AnchorPane CenterOpenPanel;
    public Label lblTime;
    private User user= LoginPageContoller.user;


    public void initialize() throws IOException {
        getSystemTime();
        if(user.getAdmin()==1){
            AdminOptionContoller.topPane = topPane;
            AdminOptionContoller.centerPane = CenterOpenPanel;
            AdminOptionContoller.aClass = getClass();
        }else{
            ReceptionistOptionContoller.topPane = topPane;
            ReceptionistOptionContoller.CenterOpenPanel = CenterOpenPanel;
            ReceptionistOptionContoller.aClass = getClass();
        }


        loadOptionPane(user.getAdmin());
    }

    private void loadOptionPane(int type) throws IOException {
        String location = type == 1 ? "../view/mainOption/adminOption.fxml" : "../view/mainOption/ReceptionistOption.fxml";
        loadOptionPane.getChildren().clear();
        CenterOpenPanel.getChildren().clear();
        Parent load = FXMLLoader.load(getClass().getResource(location));
        loadOptionPane.getChildren().setAll(load);
    }

    public void clickCancel(MouseEvent mouseEvent) {
        exsitProgram();
    }

    public void menuCloseOnAction(ActionEvent actionEvent) {
        exsitProgram();
    }

    private void exsitProgram(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Do you want to exist ?",ButtonType.YES ,ButtonType.NO);
        alert.setTitle("Exsit");
        Optional<ButtonType> buttonType = alert.showAndWait();
        if(buttonType.get().equals(ButtonType.YES)){
            System.exit(0);
        }
    }

    public void menuLogOutOnAction(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Do you want to Logout ?",ButtonType.YES ,ButtonType.NO);
        alert.setTitle("Exsit");
        Optional<ButtonType> buttonType = alert.showAndWait();
        if(buttonType.get().equals(ButtonType.YES)){
            Stage stage = (Stage) mainPane.getScene().getWindow();
            stage.close();
            uiLoad("../view/LoginPage.fxml");
        }
    }

    public void accoutDeatilsOnAction(ActionEvent actionEvent) throws IOException {
        uiLoad("../view/subView/menubarView/AccountDeatils.fxml");
    }

    public void aboutOnAction(ActionEvent actionEvent) throws IOException {
        uiLoad("../view/subView/menubarView/About.fxml");
    }

    private void uiLoad(String location) throws IOException {
        Stage newStage = new Stage();
        newStage.setScene(new Scene(FXMLLoader.load(getClass().getResource(location))));
        newStage.initStyle(StageStyle.UNDECORATED);
        newStage.centerOnScreen();
        newStage.show();
    }

    private void getSystemTime() {
        final DateFormat format = DateFormat.getInstance();
        final Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler() {
            @Override
            public void handle(Event event) {
                final Calendar cal = Calendar.getInstance();
                lblTime.setText(new SimpleDateFormat(" yyyy/MM/dd \n hh:mm:ss aa").format(Calendar.getInstance().getTime()));
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
}
