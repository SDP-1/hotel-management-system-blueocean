package module;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Booking {

    private int bookingNumber;
    private String name;
    private String idNumber;
    private String phoneNo;
    private String email;

    private String address;

    private int roomNumber;
    private int mealPackNumber;

    private boolean gest;
    private boolean paid;
    private LocalDate bookingDate;
    private LocalDate startDate;
    private LocalDate endDate;
    private String note;

    private String fullPrice;

    public Booking() {
    }

    public Booking(int bookingNumber, String name, String idNumber, String phoneNo, String email, String address, int roomNumber, int mealPackNumber, boolean gest, boolean paid, LocalDate bookingDate, LocalDate startDate, LocalDate endDate, String note, String fullPrice) {
        this.bookingNumber = bookingNumber;
        this.name = name;
        this.idNumber = idNumber;
        this.phoneNo = phoneNo;
        this.email = email;
        this.address = address;
        this.roomNumber = roomNumber;
        this.mealPackNumber = mealPackNumber;
        this.gest = gest;
        this.paid = paid;
        this.bookingDate = bookingDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.note = note;
        this.fullPrice = fullPrice;
    }

    public String getFullPrice() {
        return fullPrice;
    }

    public void setFullPrice(String fullPrice) {
        this.fullPrice = fullPrice;
    }

    public int getBookingNumber() {
        return bookingNumber;
    }

    public void setBookingNumber(int bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getMealPackNumber() {
        return mealPackNumber;
    }

    public void setMealPackNumber(int mealPackNumber) {
        this.mealPackNumber = mealPackNumber;
    }

    public boolean isGest() {
        return gest;
    }

    public void setGest(boolean gest) {
        this.gest = gest;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
