package view.CenterPanel.tm;


import java.time.LocalDate;
import java.util.Date;

public class SeeBookingTM {
    private int bookingNumber;
    private LocalDate bookingDate;
    private int roomNumber;
    private String customerName;
    private String phoneNumber;
    private String mealPack;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    public SeeBookingTM() {
    }

    public SeeBookingTM(int bookingNumber, LocalDate bookingDate, int roomNumber, String customerName, String phoneNumber, String mealPack, LocalDate checkInDate, LocalDate checkOutDate) {
        this.bookingNumber = bookingNumber;
        this.bookingDate = bookingDate;
        this.roomNumber = roomNumber;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.mealPack = mealPack;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public int getBookingNumber() {
        return bookingNumber;
    }

    public void setBookingNumber(int bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMealPack() {
        return mealPack;
    }

    public void setMealPack(String mealPack) {
        this.mealPack = mealPack;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }
}
