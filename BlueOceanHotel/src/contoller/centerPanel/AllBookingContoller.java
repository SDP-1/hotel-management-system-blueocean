package contoller.centerPanel;

import db.BookingDataBase;
import db.MealPackDataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import module.Booking;
import module.MealPack;
import view.CenterPanel.tm.SeeBookingTM;

public class AllBookingContoller {
    public static AnchorPane CenterOpenPanel;

    public TableView tableSeeAllBooking;
    public TableColumn clmBookingNumber;
    public TableColumn clmBookingDate;
    public TableColumn clmRoomNumber;
    public TableColumn clmCustomerName;
    public TableColumn clmPhoneNumber;
    public TableColumn clmMealpack;
    public TableColumn clmChackInDate;
    public TableColumn clmChackOutDate;

    public void initialize(){
        clmBookingNumber.setCellValueFactory(new PropertyValueFactory<>("bookingNumber"));
        clmBookingDate.setCellValueFactory(new PropertyValueFactory<>("bookingDate"));
        clmRoomNumber.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        clmCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        clmPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        clmMealpack.setCellValueFactory(new PropertyValueFactory<>("mealPack"));
        clmChackInDate.setCellValueFactory(new PropertyValueFactory<>("checkInDate"));
        clmChackOutDate.setCellValueFactory(new PropertyValueFactory<>("checkOutDate"));

        fillTable();
    }


    public void closeOnActon(MouseEvent mouseEvent) {
        CenterOpenPanel.getChildren().clear();
    }

    private void fillTable() {
        ObservableList<SeeBookingTM> bookings = FXCollections.observableArrayList();


        for (Booking booking : BookingDataBase.bookings) {
            String mealpack = "No-Meal Pack";
            int number = 1;
            if (booking.getMealPackNumber() != 0) {
                for (MealPack pack : MealPackDataBase.mealPacks) {
                    if (pack.isAvailbility()) {
                        if (booking.getMealPackNumber() == number) {
                            mealpack = pack.getMealPackName();
                            break;
                        }
                        number++;
                    }
                }
            }


            SeeBookingTM tm = new SeeBookingTM(
                    booking.getBookingNumber(),
                    booking.getBookingDate(),
                    booking.getRoomNumber(),
                    booking.getName(),
                    booking.getPhoneNo(),
                    mealpack,
                    booking.getStartDate(),
                    booking.getEndDate()
            );

            bookings.add(tm);
        }

        tableSeeAllBooking.setItems(bookings);
    }

}
