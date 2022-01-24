package contoller.centerPanel;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

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
import javafx.scene.image.ImageView;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.DAYS;


public class AddBookingContoller {

    public static AnchorPane CenterOpenPanel;
    private static int bookingNumber= 1995;
    public Label lblBookingDateAndTime;
    public JFXTextField txtName;
    public JFXTextField txtIdNumber;
    public Label lblBookingNumber;
    public JFXTextField txtPhoneNumber;
    public JFXTextField txtAddress;
    public JFXTextField txtEmailAddress;
    public JFXComboBox <String>cmbRoomNumber;
    public JFXComboBox <String>cmbMealPack;
    public JFXDatePicker tdatepicChakIndate;
    public JFXDatePicker tdatepicChakOutdate;
    public ToggleGroup guestOrLocal;
    public ToggleGroup PaidOrUnPaid;
    public TextArea txtNote;
    public JFXRadioButton rdbGuest;
    public JFXRadioButton rdbAnPaid;
    public Label lblOneNightPrice;
    public Label lblMealPackPrice;
    public Label lblDays;
    public Label lblFullPrice;
    public Label lblPaidStatment;


    public void initialize(){
       getSystemTime();
        lblBookingNumber.setText((bookingNumber+=5)+"");
        loadRoomNumbers();

        cmbMealPack.getItems().add("No-MealPack");
        cmbMealPack.getSelectionModel().select(0);
        for(MealPack pack : MealPackDataBase.mealPacks){
            if(pack.isAvailbility()){
                cmbMealPack.getItems().add(pack.getMealPackName());
            }
        }
    }

    private void loadRoomNumbers(){
        cmbRoomNumber.getItems().clear();
        for (Room room : RoomDatabase.rooms){
            if (room.isAvailability() && room.isMaintenance() ==false){
                cmbRoomNumber.getItems().add(String.valueOf(room.getNumber()));
            }
        }
    }

    public void saveOnAction(ActionEvent actionEvent) {

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

        for (int i = 0; i < RoomDatabase.rooms.size(); i++) {
            if (RoomDatabase.rooms.get(i).getNumber() == Integer.parseInt((String) cmbRoomNumber.getValue())) {
                RoomDatabase.rooms.get(i).setAvailability(false);
                RoomDatabase.rooms.get(i).setMaintenance(true);
            }
        }
        save();
        lblBookingNumber.setText((bookingNumber+=5)+"");
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
        CenterOpenPanel.getChildren().clear();
    }

    public void AddNewOnAction(ActionEvent actionEvent) {
        lblBookingNumber.setText(String.valueOf(bookingNumber+=5));
        clear();
    }

    private void save(){
        BookingDataBase.bookings.add(0,new Booking(

                bookingNumber,
                txtName.getText(),
                txtIdNumber.getText(),
                txtPhoneNumber.getText(),
                txtEmailAddress.getText(),
                txtAddress.getText(),
                Integer.parseInt((String)cmbRoomNumber.getValue()),
                cmbMealPack.getSelectionModel().getSelectedIndex(),
                ((RadioButton)guestOrLocal.getSelectedToggle()).getText().equals("guest"),
                ((RadioButton)PaidOrUnPaid.getSelectedToggle()).getText().equals("paid"),
                LocalDate.now( Clock.systemUTC()),
                tdatepicChakIndate.getValue(),
                tdatepicChakOutdate.getValue(),
                txtNote.getText(),
                lblFullPrice.getText()
        ));
        loadRoomNumbers();
    }

    private void clear(){
        txtName.clear();
        txtIdNumber.clear();
        txtPhoneNumber.clear();
        txtEmailAddress.clear();
        txtAddress.clear();
        cmbRoomNumber.getSelectionModel().clearSelection();
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
            if (RoomDatabase.rooms.get(i).getNumber() == Integer.parseInt((String) cmbRoomNumber.getValue())) {

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

}
