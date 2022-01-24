package contoller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import db.UserDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import module.User;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class LoginPageContoller {
    public JFXTextField txtUserName;
    public JFXPasswordField pswField;
    public Label lblHide;
    public Button btnLogin;
    public static User user;


    public void initialize(){
       colorSet();
    }

    private void colorSet() {
        txtUserName.setStyle(" -fx-prompt-text-fill: white");
        pswField.setStyle(" -fx-prompt-text-fill: white");
    }


    public void loginOnAction(ActionEvent actionEvent) throws IOException {
        login();
    }



    int attemps =5;
    private void login() throws IOException {
        attemps--;
        ArrayList<User> users = UserDatabase.users;     //get database
        if(attemps >=0) {

            for (int i = 0; i < users.size(); i++) {
                if (txtUserName.getText().equals(users.get(i).getUserName()) && pswField.getText().equals(users.get(i).getPassword())) {

                        user = users.get(i);            //----login Account Catch--------
                        Stage stage = (Stage) btnLogin.getScene().getWindow();
                        stage.close();
                        Stage newStage = new Stage();
                        newStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/MainPage.fxml"))));
                        newStage.initStyle(StageStyle.UNDECORATED);
                        newStage.setMaximized(true);
                        newStage.show();
                        return;
                }
                }
            if(attemps > 0) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Wrong username or password.\nCan be tried "+ attemps+" times.");
                alert.setTitle("Somthing Worng");
                alert.show();
                return;
            }else if(attemps==0){
                Alert alert = new Alert(Alert.AlertType.WARNING, "try other time");
                alert.setTitle("Faill login");
                Optional<ButtonType> buttonType = alert.showAndWait();
                alert.show();
                alert.close();
                System.exit(0);
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING, "try other time");
            alert.setTitle("Faill login");
            Optional<ButtonType> buttonType = alert.showAndWait();
            alert.show();
            alert.close();
            System.exit(0);
        }
    }

    public void clickCancel(MouseEvent mouseEvent) {
        System.exit(0);
    }

    //------hide show-----

    public void showPassword(MouseEvent mouseEvent) {

        Image img = new Image("Image/hide.png");
        ImageView view = new ImageView(img);
        view.setFitHeight(20);
        lblHide.setGraphic(view);

        pswField.setPromptText(pswField.getText());
        pswField.setText("");
        pswField.setDisable(true);
    }

    public void hidePassword(MouseEvent mouseEvent) {

        Image img = new Image("Image/noHide.png");
        ImageView view = new ImageView(img);
        view.setFitHeight(20);
        view.setFitWidth(20);
        lblHide.setGraphic(view);

        pswField.setText(pswField.getPromptText());
        pswField.setPromptText("");
        pswField.setDisable(false);

        pswField.requestFocus();
    }

    //-----------Short cut------

    public void usernameEnterPress(KeyEvent keyEvent) {
        if(keyEvent.getCode().equals(KeyCode.ENTER)){
            pswField.requestFocus();
        }
    }

    public void enterKeyPressed(KeyEvent keyEvent) throws IOException {
        if(keyEvent.getCode().equals(KeyCode.ENTER)) login();
    }


}
