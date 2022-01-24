package contoller.subViewContoller.menubarContoler;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class AboutContoller {
    public Button btnOk;

    ////----ok--btn ans --close------

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
