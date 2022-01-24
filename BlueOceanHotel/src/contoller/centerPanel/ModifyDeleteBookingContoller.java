package contoller.centerPanel;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import db.BookingDataBase;
import db.MealPackDataBase;
import db.RoomDatabase;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import module.Booking;
import module.MealPack;
import module.Room;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.DAYS;

public class ModifyDeleteBookingContoller {
    public static AnchorPane CenterPane;
    public JFXTextField txtName;
    public JFXTextField txtPhoneNumber;
    public JFXTextField txtIdNumber;
    public JFXTextField txtAddress;
    public JFXTextField txtEmailAddress;
    public JFXComboBox cmbRoomNumber;
    public JFXComboBox cmbMealPack;
    public ToggleGroup guestOrLocal;
    public ToggleGroup PaidOrUnPaid;
    public Label lblBookingDateAndTime;
    public JFXDatePicker tdatepicChakIndate;
    public JFXDatePicker tdatepicChakOutdate;
    public TextArea txtNote;
    public Label lblOneNightPrice;
    public Label lblMealPackPrice;
    public Label lblDays;
    public Label lblFullPrice;
    public Label lblPaidStatment;
    public Button btnSearch;
    public TextField txtBookingNumber;
    public Button btnDelete;
    public JFXRadioButton rdbGuest;
    public JFXRadioButton rdbAnPaid;
    public JFXRadioButton rdbLocal;
    public JFXRadioButton rdbPaid;
    public Button btnSave;
    public Button btnClear;
    public JFXTextField txtBookingDate;

    public void initialize(){
        getSystemTime();
     btnSearch.setDisable(true);
     disable();
     txtBookingDate.setEditable(false);

        cmbMealPack.getItems().add("No-MealPack");
        cmbMealPack.getSelectionModel().select(0);
        for(MealPack pack : MealPackDataBase.mealPacks){
            if(pack.isAvailbility()){
                cmbMealPack.getItems().add(pack.getMealPackName());
            }
        }
    }

    public void saveOnAction(ActionEvent actionEvent) {
        int bookingNumber;

        if(txtName.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Customer name is can't empty...");
            alert.showAndWait();
            return;
        }

        if(txtIdNumber.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Customer Id number is can't empty...");
            alert.showAndWait();
            return;
        }

        if(txtEmailAddress.getText().equals("") && txtPhoneNumber.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Phone number , Email both fields can't empty...");
            alert.showAndWait();
            return;
        }

        if(cmbRoomNumber.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Room Number can't empty...");
            alert.showAndWait();
            return;
        }

        if(tdatepicChakIndate.getValue() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Chack-In Date can't empty...");
            alert.showAndWait();
            return;
        }

        if(tdatepicChakOutdate.getValue() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Chack-Out Date can't empty...");
            alert.showAndWait();
            return;
        }

        if(howManyDays()<=0){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Dats are invalided...\t\trechack");
            alert.showAndWait();
            return;
        }

        try{
            bookingNumber = Integer.parseInt(txtBookingNumber.getText());
            for (int i = 0; i < BookingDataBase.bookings.size(); i++) {
                if(BookingDataBase.bookings.get(i).getBookingNumber() == bookingNumber){
                    BookingDataBase.bookings.set(i,new Booking(

                            bookingNumber,
                            txtName.getText(),
                            txtIdNumber.getText(),
                            txtPhoneNumber.getText(),
                            txtEmailAddress.getText(),
                            txtAddress.getText(),
                            (Integer)cmbRoomNumber.getValue(),
                            cmbMealPack.getSelectionModel().getSelectedIndex(),
                            ((RadioButton)guestOrLocal.getSelectedToggle()).getText().equals("guest"),
                            ((RadioButton)PaidOrUnPaid.getSelectedToggle()).getText().equals("paid"),
                            BookingDataBase.bookings.get(i).getBookingDate(),
                            tdatepicChakIndate.getValue(),
                            tdatepicChakOutdate.getValue(),
                            txtNote.getText(),
                            lblFullPrice.getText()
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

    public void chachAllAreFill(ActionEvent actionEvent) {
        if(tdatepicChakIndate.getValue() != null && tdatepicChakOutdate.getValue() != null){
            if (howManyDays() <= 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Dats are invalided...\t\trechack");
                alert.showAndWait();
                return;
            }
        }

        if (!cmbRoomNumber.getSelectionModel().isEmpty() && tdatepicChakIndate.getValue() != null && tdatepicChakOutdate.getValue() != null) {
            billDataAdd();
        }
    }

    public void searchOnAction(ActionEvent actionEvent) {
        search();
    }

    public void clickSearchEnableAll(MouseEvent mouseEvent) {
    enable();
    }

    public void bookingNumberChangDitect(KeyEvent keyEvent) {
        btnSearch.setDisable(false);
        if(keyEvent.getCode().equals(KeyCode.ENTER)){
            search();
        }
    }

    public void bookingNumberClick(MouseEvent mouseEvent) {
        disable();
        clear();
    }

    public void DeleteOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Do you want Delete ?",ButtonType.YES ,ButtonType.NO);
        alert.setTitle("Delete");
        Optional<ButtonType> buttonType = alert.showAndWait();
        if(buttonType.get().equals(ButtonType.YES)) {
            markRoomAvalbleTrue();
            for (int i = 0; i < BookingDataBase.bookings.size(); i++) {
               try {
                   if (BookingDataBase.bookings.get(i).getBookingNumber() == Integer.parseInt(txtBookingNumber.getText())) {
                       BookingDataBase.bookings.remove(i);
                       clear();
                       disable();
                   }
               }catch (NumberFormatException e){}
            }
        }
    }

    private void disable(){
          txtName.setDisable(true);
          txtPhoneNumber.setDisable(true);
          txtIdNumber.setDisable(true);
          txtAddress.setDisable(true);
          txtEmailAddress.setDisable(true);
          cmbRoomNumber.setDisable(true);
          cmbMealPack.setDisable(true);
          tdatepicChakIndate.setDisable(true);
          tdatepicChakOutdate.setDisable(true);
          rdbGuest.setDisable(true);
          rdbAnPaid.setDisable(true);
          rdbLocal.setDisable(true);
          rdbPaid.setDisable(true);
          txtNote.setDisable(true);
          btnDelete.setDisable(true);
          btnSave.setDisable(true);
          btnClear.setDisable(true);
        txtBookingDate.setDisable(true);
    }

    private void enable(){
        txtName.setDisable(false);
        txtPhoneNumber.setDisable(false);
        txtIdNumber.setDisable(false);
        txtAddress.setDisable(false);
        txtEmailAddress.setDisable(false);
        cmbRoomNumber.setDisable(false);
        cmbMealPack.setDisable(false);
        tdatepicChakIndate.setDisable(false);
        tdatepicChakOutdate.setDisable(false);
        rdbGuest.setDisable(false);
        rdbAnPaid.setDisable(false);
        rdbLocal.setDisable(false);
        rdbPaid.setDisable(false);
        txtNote.setDisable(false);
        btnDelete.setDisable(false);
        btnSave.setDisable(false);
        btnClear.setDisable(false);
        txtBookingDate.setDisable(false);
    }

    private void clear(){
        txtName.clear();
        txtIdNumber.clear();
        txtPhoneNumber.clear();
        txtEmailAddress.clear();
        txtAddress.clear();

        cmbRoomNumber.getSelectionModel().clearSelection();
        cmbRoomNumber.getItems().clear();
        cmbRoomNumber.setDisable(true);

        cmbMealPack.getSelectionModel().select(0);
//        tdatepicChakIndate.getEditor().clear();
//        tdatepicChakOutdate.getEditor().clear();
        tdatepicChakIndate.setValue(null);
        tdatepicChakOutdate.setValue(null);
        txtNote.clear();
        rdbGuest.setSelected(true);
        rdbAnPaid.setSelected(true);
        lblOneNightPrice.setText("");
        lblMealPackPrice.setText("");
        lblDays.setText("");
        lblFullPrice.setText("");
        lblPaidStatment.setText("");
        txtBookingNumber.clear();
        txtBookingDate.clear();
    }

    private void getSystemTime() {
        final DateFormat format = DateFormat.getInstance();
        final Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler() {
            @Override
            public void handle(Event event) {
                final Calendar cal = Calendar.getInstance();
                lblBookingDateAndTime.setText(new SimpleDateFormat("yyyy/MM/dd  hh:mm:ss aa").format(Calendar.getInstance().getTime()));
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void billDataAdd() {

        double price = 0;
        String priceType = "";

        for (int i = 0; i < RoomDatabase.rooms.size(); i++) {
            if (RoomDatabase.rooms.get(i).getNumber() == (Integer) cmbRoomNumber.getValue()) {

//                RoomDatabase.rooms.get(i).setAvailability(false);
//                RoomDatabase.rooms.get(i).setMaintenance(true);

                if (((RadioButton) guestOrLocal.getSelectedToggle()).getText().equals("guest")) {
                    price += RoomDatabase.rooms.get(i).getGuestPrice();
                    priceType = RoomDatabase.rooms.get(i).getGuestPriceType();
                    lblOneNightPrice.setText(RoomDatabase.rooms.get(i).getGuestPrice() + " " + RoomDatabase.rooms.get(i).getGuestPriceType());
                } else {
                    price += RoomDatabase.rooms.get(i).getLocalPrice();
                    priceType = RoomDatabase.rooms.get(i).getLocalPriceType();
                    lblOneNightPrice.setText(RoomDatabase.rooms.get(i).getLocalPrice() + " " + RoomDatabase.rooms.get(i).getLocalPriceType());
                }

                break;
            }
        }


        if(((String)cmbMealPack.getValue()).equals("No-MealPack")){
            lblMealPackPrice.setText(" --- ");
        }else{
            for (MealPack pack : MealPackDataBase.mealPacks) {
                if(((String)cmbMealPack.getValue()).equals(pack.getMealPackName())){
                    if(((RadioButton)guestOrLocal.getSelectedToggle()).getText().equals("guest")){
                        price += pack.getGuestPrice();
                        lblMealPackPrice.setText(pack.getGuestPrice()+" "+pack.getGuestPriceType());
                    }else{
                        price += pack.getLocalPrice();
                        lblMealPackPrice.setText(pack.getLocalPrice()+" "+pack.getLocalPriceType());
                    }
                    break;
                }
            }
        }

        lblDays.setText(howManyDays()+"");
        lblFullPrice.setText((price*howManyDays())+" "+priceType);
        lblPaidStatment.setText(rdbAnPaid.isSelected()?"UnPaid":"Paid");

    }

    private long howManyDays(){
        return (DAYS.between(tdatepicChakIndate.getValue(), tdatepicChakOutdate.getValue()));
    }

    private void markRoomAvalbleTrue(){
        for (int i = 0; i < RoomDatabase.rooms.size(); i++) {
            if (RoomDatabase.rooms.get(i).getNumber() == (Integer) cmbRoomNumber.getValue()) {
                RoomDatabase.rooms.get(i).setAvailability(true);
                RoomDatabase.rooms.get(i).setMaintenance(false);
                break;
            }
        }
    }


    private void search(){
        try{
            int searchNumber =Integer.parseInt(txtBookingNumber.getText());
            for (int i = 0; i <BookingDataBase.bookings.size(); i++) {
                if(BookingDataBase.bookings.get(i).getBookingNumber() == searchNumber){
                    fill(BookingDataBase.bookings.get(i));
                    enable();
                    return;
                }
            }
            Alert alert = new Alert(Alert.AlertType.ERROR,"Booking Number is not Exists...");
            alert.showAndWait();
        }catch(NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Booking Number is Invalid.");
            alert.showAndWait();
            return;
        }
    }

    private void fill(Booking booking){
        txtName.setText(booking.getName());
        txtPhoneNumber.setText(booking.getPhoneNo());
        txtIdNumber.setText(booking.getIdNumber());
        txtAddress.setText(booking.getAddress());
        txtEmailAddress.setText(booking.getEmail());

        cmbRoomNumber.getItems().addAll(booking.getRoomNumber());
        cmbRoomNumber.getSelectionModel().select(0);

        cmbMealPack.getSelectionModel().select(booking.getMealPackNumber());

        tdatepicChakIndate.setValue(booking.getStartDate());
        tdatepicChakOutdate.setValue(booking.getEndDate());

        txtBookingDate.setText(booking.getBookingDate().toString());

        if(booking.isGest()){
            rdbGuest.setSelected(true);
        }else{
            rdbLocal.setSelected(true);
        }

        if(booking.isPaid()){
            rdbPaid.setSelected(true);
        }else{
            rdbAnPaid.setSelected(true);
        }
        txtNote.setText(booking.getNote());
        billDataAdd();
    }

}
