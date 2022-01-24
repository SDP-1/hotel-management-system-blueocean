package contoller.centerPanel;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import db.UserDatabase;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import module.User;

public class AddUserContoller {

    public static AnchorPane CenterPanel;
    public RadioButton rdbReceptionist;
    public ToggleGroup Admin;
    public RadioButton rdbAdmin;
    public JFXTextField txtFullName;
    public JFXTextField txtUserName;
    public JFXPasswordField pwdPassword;
    public JFXPasswordField pwdReTypePassword;
    public JFXTextField txtDOB;
    public JFXTextField txtIdNumber;

    public void saveOnAction(ActionEvent actionEvent) {
        if(txtFullName.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Full name is can't empty...");
            alert.showAndWait();
            return;
        }

        if(txtIdNumber.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR,"User Name is can't empty...");
            alert.showAndWait();
            return;
        }

        if(txtUserName.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR,"ID Number is can't empty...");
            alert.showAndWait();
            return;
        }

        for (int i = 0; i < UserDatabase.users.size(); i++) {
            if(txtUserName.getText().equals(UserDatabase.users.get(i).getUserName())){
                Alert alert = new Alert(Alert.AlertType.ERROR,"User name allRady Exist.");
                alert.showAndWait();
                return;
            }
        }

        if(txtDOB.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR,"DOB can't empty...");
            alert.showAndWait();
            return;
        }

        if(pwdPassword.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Please set Password");
            alert.showAndWait();
            return;
        }

        if(pwdReTypePassword.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Please set reenter Password");
            alert.showAndWait();
            return;
        }

        if(!pwdPassword.getText().equals(pwdReTypePassword.getText())){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Password not match...\t\tplease recheck");
            alert.showAndWait();
            return;
        }

        UserDatabase.users.add(new User(
                txtFullName.getText(),
                txtIdNumber.getText(),
                txtDOB.getText(),
                txtUserName.getText(),
                pwdPassword.getText(),
                ((RadioButton)Admin.getSelectedToggle()).getText().equals("Receptionist")?0:1
        ));

        Alert alert = new Alert(Alert.AlertType.INFORMATION,"Welcome..\t\tsave successfully.");
        alert.showAndWait();
        clear();
    }

    public void clearOnAction(ActionEvent actionEvent) {
        clear();
    }

    public void closeOnActon(MouseEvent mouseEvent) {
        CenterPanel.getChildren().clear();
    }

    private void clear(){
        txtFullName.clear();
        txtIdNumber.clear();
        txtUserName.clear();
        txtDOB.clear();
        pwdPassword.clear();
        pwdReTypePassword.clear();
        rdbReceptionist.setSelected(true);
    }
}
