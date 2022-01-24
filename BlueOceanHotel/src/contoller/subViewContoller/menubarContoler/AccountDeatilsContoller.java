package contoller.subViewContoller.menubarContoler;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import contoller.LoginPageContoller;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import module.User;

public class AccountDeatilsContoller {
    public Label lblFullName;
    public Label lblIDNumber;
    public Label lblDOB;
    public Label lblUserName;
    public Label lblCategory;
    public Button btnOk;
    User user = LoginPageContoller.user;

    public void initialize(){
        lblFullName.setText(user.getFullName());
        lblUserName.setText(user.getUserName());
        lblIDNumber.setText(user.getIDNumber());
        lblDOB.setText(user.getDob());
        if(user.getAdmin() == 1){
            lblCategory.setText("Admin Account");
        }else{
            lblCategory.setText("Receptionist Account");
        }
    }

    //----ok--btn ans --close------

    public void btnOkOnAction(ActionEvent actionEvent) {
        close();
    }

    public void pressEnterKeyClose(KeyEvent keyEvent) {
        if(keyEvent.getCode().equals(KeyCode.ENTER)) close();
    }

    private void close(){
        Stage stage = (Stage) btnOk.getScene().getWindow();
        stage.close();
    }
}
