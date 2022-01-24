package contoller.centerPanel;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import db.MealPackDataBase;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import module.MealPack;


import java.util.Optional;


public class AddNewMealPackContoller {
    public JFXComboBox dolerOrRsGuest;
    public JFXTextField txtguestPrice;
    public JFXComboBox dolerOrRsLocal;
    public JFXTextField txtLocalPrice;
    public RadioButton rdBtnAvailable;
    public RadioButton rdBtnNoAvailable;
    public JFXTextField txtIncludeDeatils;
    public TextField txtMealPackNo;
    public TextArea txtnote;
    public ToggleGroup Available;
    public JFXTextField txtMealPackName;

    public static AnchorPane CenterPane;

    public void initialize(){
        dolerOrRsGuest.getItems().addAll("$","Rs");
        dolerOrRsGuest.getSelectionModel().select(0);
        dolerOrRsLocal.getItems().addAll("$","Rs");
        dolerOrRsLocal.getSelectionModel().select(1);
    }

    public void saveOnAction(ActionEvent actionEvent) {
        int mealPacknumber;
        double guestPrice;
        double localPrice;

        try{
            mealPacknumber = Integer.parseInt(txtMealPackNo.getText());
            for (int i = 0; i < MealPackDataBase.mealPacks.size(); i++) {
                if(MealPackDataBase.mealPacks.get(i).getMealPackNo() == mealPacknumber){
                    Alert alert = new Alert(Alert.AlertType.ERROR,"Meal Pack Number is already exists... ");
                    alert.showAndWait();
                    return;
                }
            }
        }catch(NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Meal Pack Number is Invalid ");
            alert.showAndWait();
            return;
        }

        if(txtMealPackName.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Meal Pack name can't empty");
            alert.showAndWait();
            return;
        }

        try{
            guestPrice = Double.parseDouble(txtguestPrice.getText());
        }catch(NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Guest Price is Invalid ");
            alert.showAndWait();
            return;
        }

        try{
            localPrice = Double.parseDouble(txtLocalPrice.getText());
        }catch(NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Local Price is Invalid ");
            alert.showAndWait();
            return;
        }

        MealPackDataBase.mealPacks.add(new MealPack(
                mealPacknumber,
                txtMealPackName.getText(),
                Double.parseDouble(txtguestPrice.getText()),
                Double.parseDouble(txtLocalPrice.getText()),
                (String)dolerOrRsGuest.getValue(),
                (String) dolerOrRsLocal.getValue(),
                ((RadioButton)Available.getSelectedToggle()).getText().equals("Available"),
                txtIncludeDeatils.getText(),
                txtnote.getText()
        ));


        Alert alert = new Alert(Alert.AlertType.INFORMATION,"Welcome..\t\tsave successfully.");
        alert.showAndWait();
        clear();
    }

    public void clearOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Do you want to clear ?",ButtonType.YES ,ButtonType.NO);
        alert.setTitle("Clear");
        Optional<ButtonType> buttonType = alert.showAndWait();
        if(buttonType.get().equals(ButtonType.YES)) {
            clear();
        }
    }

    public void closeOnActon(MouseEvent mouseEvent) {
        CenterPane.getChildren().clear();
    }


    private void clear(){
        dolerOrRsGuest.getSelectionModel().select(0);
        dolerOrRsLocal.getSelectionModel().select(1);
        rdBtnAvailable.setSelected(true);
        txtMealPackNo.clear();
        txtMealPackName.clear();
        txtLocalPrice.clear();
        txtguestPrice.clear();
        txtIncludeDeatils.clear();
        txtnote.clear();
    }
}
