package contoller.centerPanel;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import db.UserDatabase;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import module.User;

import java.util.Optional;

public class EditDeleteUserContoller {
    public static AnchorPane CenterPanel;
    public RadioButton rdbReceptionist;
    public ToggleGroup Admin;
    public RadioButton rdbAdmin;
    public JFXTextField txtFullName;
    public JFXTextField txtUserName;
    public JFXPasswordField pwdPassword;
    public JFXPasswordField pwdReTypePassword;
    public JFXTextField txtDOB;
    public Button btnSearch;
    public JFXTextField txtIdNumber;
    public Button btnDelete;
    public Button btnSave;
    public Button btnClear;

    public void initialize(){
        btnSearch.setDisable(true);
        disable();
    }

    public void saveOnAction(ActionEvent actionEvent) {
        if(txtFullName.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Full name is can't empty...");
            alert.showAndWait();
            return;
        }

        if(txtIdNumber.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR,"User name is can't empty...");
            alert.showAndWait();
            return;
        }

        if(txtUserName.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR,"ID Number is can't empty...");
            alert.showAndWait();
            return;
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

        if(!pwdReTypePassword.getText().equals(pwdPassword.getText())){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Re type password is Wrong...");
            alert.showAndWait();
            return;
        }
        for (int i = 0; i <UserDatabase.users.size(); i++) {
            if(txtUserName.getText().equals(UserDatabase.users.get(i).getUserName())){

                UserDatabase.users.set(i,new User(
                        txtFullName.getText(),
                        txtIdNumber.getText(),
                        txtDOB.getText(),
                        txtUserName.getText(),
                        pwdPassword.getText(),
                        ((RadioButton)Admin.getSelectedToggle()).getText().equals("Receptionist")?0:1
                ));

                Alert alert = new Alert(Alert.AlertType.INFORMATION,"Welcome..\t\tsave successfully.");
                alert.showAndWait();
                return;
            }
        }
        clear();
    }

    public void clearOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Do you want to clear ?",ButtonType.YES ,ButtonType.NO);
        alert.setTitle("Clear");
        Optional<ButtonType> buttonType = alert.showAndWait();
        if(buttonType.get().equals(ButtonType.YES)) {
            clear();
            disable();
        }
    }

    public void closeOnActon(MouseEvent mouseEvent) {
        CenterPanel.getChildren().clear();
    }

    public void userNameEntedDitect(KeyEvent keyEvent) {

        btnSearch.setDisable(false);
        if(keyEvent.getCode().equals(KeyCode.ENTER)){
            search();
            enable();
        }
    }

    public void searchOnAction(ActionEvent actionEvent) {
            search();
    }

    private void search(){
        for (int i = 0; i <UserDatabase.users.size(); i++) {
            if(UserDatabase.users.get(i).getUserName() .equals(txtUserName.getText())){
                fill(UserDatabase.users.get(i));
                return;
            }
        }
        Alert alert = new Alert(Alert.AlertType.ERROR,"This user name is not Exists...");
        alert.showAndWait();
    }

    public void searchClick(MouseEvent mouseEvent) {
        enable();
    }

    public void DeleteOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Do you want Delete ?", ButtonType.YES ,ButtonType.NO);
        alert.setTitle("Delete");
        Optional<ButtonType> buttonType = alert.showAndWait();
        if(buttonType.get().equals(ButtonType.YES)) {
            for (int i = 0; i < UserDatabase.users.size(); i++) {
                if(UserDatabase.users.get(i).getUserName() .equals(txtUserName.getText())){
                    UserDatabase.users.remove(i);
                    clear();
                    disable();
                }
            }
        }
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

    private void disable(){
        btnDelete.setDisable(true);
        btnClear.setDisable(true);
        btnSave.setDisable(true);
        txtFullName.setDisable(true);
        txtIdNumber.setDisable(true);
        txtDOB.setDisable(true);
        pwdPassword.setDisable(true);
        pwdReTypePassword.setDisable(true);
        rdbReceptionist.setDisable(true);
        rdbAdmin.setDisable(true);
    }

    private void enable(){
        btnDelete.setDisable(false);
        btnClear.setDisable(false);
        btnSave.setDisable(false);
        txtFullName.setDisable(false);
        txtIdNumber.setDisable(false);
        txtDOB.setDisable(false);
        pwdPassword.setDisable(false);
        pwdReTypePassword.setDisable(false);
        rdbReceptionist.setDisable(false);
        rdbAdmin.setDisable(false);
    }

    private void fill(User user){
        txtFullName.setText(user.getFullName());
        txtIdNumber.setText(user.getIDNumber());
        txtDOB.setText(user.getDob());
        txtUserName.setText(user.getUserName());
        pwdPassword.setText(user.getPassword());
        pwdReTypePassword.setText(user.getPassword());
        if (user.getAdmin() == 1) {
            rdbAdmin.setSelected(true);
        } else {
            rdbReceptionist.setSelected(true);
        }
    }

    public void usernameChangDiteact(MouseEvent mouseEvent) {
        disable();
        clear();
    }
}
