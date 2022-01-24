package contoller.centerPanel;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import db.MealPackDataBase;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import module.MealPack;

import java.util.Optional;

public class ModyfyDeleteMealPackContoller {
    public JFXComboBox dolerOrRsGuest;
    public JFXTextField txtguestPrice;
    public JFXComboBox dolerOrRsLocal;
    public JFXTextField txtLocalPrice;
    public RadioButton rdBtnAvailable;
    public ToggleGroup Available;
    public RadioButton rdBtnNoAvailable;
    public JFXTextField txtIncludeDeatils;
    public TextField txtMealPackNo;
    public TextArea txtnote;
    public JFXTextField txtMealPackName;

    public static AnchorPane CenterPane;
    public Button btnDelete;
    public Button btnClear;
    public Button btnSave;
    public Button btnSearch;

    public void initialize(){
        dolerOrRsGuest.getItems().addAll("$","Rs");
        dolerOrRsGuest.getSelectionModel().select(0);
        dolerOrRsLocal.getItems().addAll("$","Rs");
        dolerOrRsLocal.getSelectionModel().select(1);

            btnSearch.setDisable(true);
            disable();

    }

    private void disable() {
        btnDelete.setDisable(true);
        btnClear.setDisable(true);
        btnSave.setDisable(true);
        dolerOrRsGuest.setDisable(true);
        dolerOrRsLocal.setDisable(true);
        rdBtnAvailable.setDisable(true);
        rdBtnNoAvailable.setDisable(true);
        txtMealPackName.setDisable(true);
        txtLocalPrice.setDisable(true);
        txtguestPrice.setDisable(true);
        txtIncludeDeatils.setDisable(true);
        txtnote.setDisable(true);
    }

    private void enable() {
        btnDelete.setDisable(false);
        btnClear.setDisable(false);
        btnSave.setDisable(false);
        dolerOrRsGuest.setDisable(false);
        dolerOrRsLocal.setDisable(false);
        rdBtnAvailable.setDisable(false);
        rdBtnNoAvailable.setDisable(false);
        txtMealPackName.setDisable(false);
        txtLocalPrice.setDisable(false);
        txtguestPrice.setDisable(false);
        txtIncludeDeatils.setDisable(false);
        txtnote.setDisable(false);
    }



    public void saveOnAction(ActionEvent actionEvent) {
        int mealPacknumber;

        try{
           double guestPrice = Double.parseDouble(txtguestPrice.getText());
        }catch(NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Guest Price is Invalid ");
            alert.showAndWait();
            return;
        }

        if(txtMealPackName.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Meal Pack name can't empty");
            alert.showAndWait();
            return;
        }

        try{
            double localPrice = Double.parseDouble(txtLocalPrice.getText());
        }catch(NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Local Price is Invalid ");
            alert.showAndWait();
            return;
        }

        try{
            mealPacknumber = Integer.parseInt(txtMealPackNo.getText());
            for (int i = 0; i < MealPackDataBase.mealPacks.size(); i++) {
                if(MealPackDataBase.mealPacks.get(i).getMealPackNo() == mealPacknumber){
                    MealPackDataBase.mealPacks.set(i,new MealPack(
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
                    disable();
                    return;
                }
            }
        }catch(NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Meal Pack Number is Invalid ");
            alert.showAndWait();
            return;
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
        CenterPane.getChildren().clear();
    }

    public void mealpackNoEnted(KeyEvent keyEvent) {
        btnSearch.setDisable(false);
        if(keyEvent.getCode().equals(KeyCode.ENTER)){
            search();
        }
    }

    public void clickSearchEnableAll(MouseEvent mouseEvent) {
        enable();
    }

    public void mealPackNoChangDitect(MouseEvent mouseEvent) {
        disable();
        clear();
    }

    public void searchOnAction(ActionEvent actionEvent) {
        search();
    }

    private void search(){
        try{
            int searchNumber = Integer.parseInt(txtMealPackNo.getText());
            for (int i = 0; i < MealPackDataBase.mealPacks.size(); i++) {
                if(MealPackDataBase.mealPacks.get(i).getMealPackNo() == searchNumber){
                    fill(MealPackDataBase.mealPacks.get(i));
                    enable();
                    return;
                }
            }
            Alert alert = new Alert(Alert.AlertType.ERROR,"Meal pack Number is not Exists...");
            alert.showAndWait();
        }catch(NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Meal pack Number is Invalid ");
            alert.showAndWait();
            return;
        }
    }

    public void DeleteOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Do you want Delete ?",ButtonType.YES ,ButtonType.NO);
        alert.setTitle("Delete");
        Optional<ButtonType> buttonType = alert.showAndWait();
        if(buttonType.get().equals(ButtonType.YES)) {
            for (int i = 0; i <MealPackDataBase.mealPacks.size(); i++) {
                if(MealPackDataBase.mealPacks.get(i).getMealPackNo() == Integer.parseInt(txtMealPackNo.getText())){
                    MealPackDataBase.mealPacks.remove(i);
                    clear();
                    disable();
                }
            }
        }
    }

    private void fill(MealPack pack) {
        if(pack.getGuestPriceType().equals("$")) {
            dolerOrRsGuest.getSelectionModel().select(0);
        }else{
            dolerOrRsGuest.getSelectionModel().select(1);
        }

        if(pack.getLocalPriceType().equals("Rs")){
            dolerOrRsLocal.getSelectionModel().select(1);
        }else{
            dolerOrRsLocal.getSelectionModel().select(0);
        }


        if(pack.isAvailbility()){
            rdBtnAvailable.setSelected(true);
        }else{
            rdBtnNoAvailable.setSelected(true);
        }

        txtMealPackNo.setText(String.valueOf(pack.getMealPackNo()));
        txtMealPackName.setText(pack.getMealPackName());
        txtguestPrice.setText(String.valueOf(pack.getGuestPrice()));
        txtLocalPrice.setText(String.valueOf(pack.getLocalPrice()));
        txtIncludeDeatils.setText(pack.getInclude());
        txtnote.setText(pack.getNote());
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
