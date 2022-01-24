package contoller.centerPanel;

import db.BookingDataBase;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import module.Booking;
import module.Room;
import view.CenterPanel.tm.IncomeReportAnuwalyTM;
import view.CenterPanel.tm.IncomeReportsMonthlyTM;

import java.time.Month;
import java.time.Year;
import java.util.ArrayList;

public class IncomeReportsContoller {
    public static AnchorPane CenterOpenPanel;
    public TableColumn clmNo;
    public TableColumn clmDescription;
    public TableColumn lcmBookingCount;
    public TableColumn clmreceivableRs;
    public TableColumn clmreceivable$;
    public TableColumn clmReceivedRs;
    public TableColumn clmReceived$;
    public Label lblYear;
    public Label lblMonth;
    public ComboBox cmbYear;
    public ComboBox cmbMonth;
    public TableView tblIncomeReport;
    public TextField txtReceivableRs;
    public TextField txtReceivable$;
    public TextField txtReceivedRs;
    public TextField txtReceived$;
    public Label lblTotalBookings;
    public Label lblReceivableRs;
    public Label lblReceivedRs;
    public Label lblReceivable$;
    public Label lblReceived$;

    public void initialize(){
        loadYears();

        loadMonths();

       lableUpdate();

        clmNo.setCellValueFactory(new PropertyValueFactory<>("no"));
        clmDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        clmreceivableRs.setCellValueFactory(new PropertyValueFactory<>("receivableRs"));
        clmreceivable$.setCellValueFactory(new PropertyValueFactory<>("receivable$"));
        clmReceivedRs.setCellValueFactory(new PropertyValueFactory<>("receivedRs"));
        clmReceived$.setCellValueFactory(new PropertyValueFactory<>("received$"));

        dataLoad();
    }

    public void closeOnActon(MouseEvent mouseEvent) {
        CenterOpenPanel.getChildren().clear();
    }

    public void searchOnAction(ActionEvent actionEvent) {
        lableUpdate();
        dataLoad();
    }



    //---------private methodes-------------

    private void lableUpdate(){
        lblYear.setText(cmbYear.getSelectionModel().getSelectedItem().toString());
        lblMonth.setText( cmbMonth.getSelectionModel().getSelectedItem().toString());
    }

    private void loadYears(){
        ArrayList<Integer> years = new ArrayList<>();
      outer:  for (Booking booking : BookingDataBase.bookings){
            for (int year : years){
                if(year == booking.getBookingDate().getYear()) continue outer;
            }
            years.add(booking.getBookingDate().getYear());
        }

        cmbYear.getItems().addAll(years);
        cmbYear.getSelectionModel().select(0);
    }

    private void loadMonths(){
        cmbMonth.getItems().clear();
        ArrayList<Month> months = new ArrayList<>();
        cmbMonth.getItems().add("All-Month");
        outer:  for (Booking booking : BookingDataBase.bookings){
            if(cmbYear.getSelectionModel().getSelectedItem().toString().equals(String.valueOf(booking.getBookingDate().getYear()))) {
                for (Month month : months) {
                    if (month.toString().equals(booking.getBookingDate().getMonth().toString())) continue outer;
                }
                months.add(booking.getBookingDate().getMonth());
            }
        }
        cmbMonth.getItems().addAll(months);
        cmbMonth.getSelectionModel().select(0);
    }

    private void dataLoad(){
        ObservableList<IncomeReportsMonthlyTM> incomeReportsMonthlyTMS = FXCollections.observableArrayList();
        int number =1;
        double receivedRs = 0;
        double received$ = 0;
        double receivableRs = 0;
        double receivable$ = 0;


        for(Booking booking : BookingDataBase.bookings){

        if(booking.getBookingDate().getYear() == Integer.parseInt(cmbYear.getSelectionModel().getSelectedItem().toString())){

            if( booking.getBookingDate().getMonth().toString().equals(cmbMonth.getSelectionModel().getSelectedItem().toString())){
                //-----monthly data load----------

                String clm1,clm2,clm3,clm4;
                clm1 = clm2 = clm3 = clm4 = null;

                if (booking.isGest() ){
                    if (booking.isPaid()) {
                        clm4 = booking.getFullPrice().substring(0,booking.getFullPrice().length()-2);
                        received$ += Double.parseDouble(clm4);
                    }else{
                        clm2 = booking.getFullPrice().substring(0,booking.getFullPrice().length()-2);
                        receivable$ += Double.parseDouble(clm2);
                    }
                }else {
                    if(booking.isPaid()){
                        clm3 = booking.getFullPrice().substring(0,booking.getFullPrice().length()-3);
                        receivedRs += Double.parseDouble(clm3);
                    }else{
                        clm1 = booking.getFullPrice().substring(0,booking.getFullPrice().length()-3);
                        receivableRs += Double.parseDouble(clm1);
                    }
                }

                incomeReportsMonthlyTMS.add(new IncomeReportsMonthlyTM(
                        number,
                        "Bd- "+booking.getBookingDate().toString() + "\t\tBn- "+booking.getBookingNumber()+"\t\tCn- "+booking.getName(),
                        clm1,
                        clm2,
                        clm3,
                        clm4
                ));

                number++;

            }else if(cmbMonth.getSelectionModel().getSelectedItem().toString().equals("All-Month")){
                //---year data load---------
                int bookingCount=0;


                ObservableList<IncomeReportAnuwalyTM> incomeReportsAnuwalyTMS = FXCollections.observableArrayList();


                ArrayList<Month> selectedYearMonths = new ArrayList<>();
                outer:  for (Booking b : BookingDataBase.bookings){
                    if(cmbYear.getSelectionModel().getSelectedItem().toString().equals(String.valueOf(b.getBookingDate().getYear()))) {
                        for (Month month : selectedYearMonths) {
                            if (month.toString().equals(b.getBookingDate().getMonth().toString())) continue outer;
                        }
                        selectedYearMonths.add(b.getBookingDate().getMonth());
                    }
                }

                double[][] monthsdata = new double[selectedYearMonths.size()][4];       //data store array

                for(Booking booking2 : BookingDataBase.bookings){
                    if(booking2.getBookingDate().getYear() == Integer.parseInt(cmbYear.getSelectionModel().getSelectedItem().toString()) ){
                        bookingCount++;

                        for (int i = 0; i <selectedYearMonths.size(); i++) {
                            if(booking2.getBookingDate().getMonth().toString().equals(selectedYearMonths.get(i).toString())){
                                if (booking2.isGest() ){
                                    if (booking2.isPaid()) {
                                        monthsdata[i][3] += Double.parseDouble(booking2.getFullPrice().substring(0,booking2.getFullPrice().length()-2));
                                    }else{
                                        monthsdata[i][1] += Double.parseDouble(booking2.getFullPrice().substring(0,booking2.getFullPrice().length()-2));
                                    }
                                }else {
                                    if(booking2.isPaid()){
                                        monthsdata[i][2] += Double.parseDouble(booking2.getFullPrice().substring(0,booking2.getFullPrice().length()-3));
                                    }else{
                                        monthsdata[i][0] += Double.parseDouble(booking2.getFullPrice().substring(0,booking2.getFullPrice().length()-3));
                                    }
                                }
                                break;
                            }
                        }


                    }
                }

                double receivedRs2 = 0;
                double received$2 = 0;
                double receivableRs2 = 0;
                double receivable$2 = 0;

                for (int i = 0; i <selectedYearMonths.size(); i++) {

                    incomeReportsAnuwalyTMS.add(new IncomeReportAnuwalyTM(
                            i+1,
                            selectedYearMonths.get(i).toString(),
                            String.valueOf(monthsdata[i][0]),
                            String.valueOf(monthsdata[i][1]),
                            String.valueOf(monthsdata[i][2]),
                            String.valueOf(monthsdata[i][3])
                    ));

                    receivedRs2 += monthsdata[i][2];
                    received$2 += monthsdata[i][3];
                    receivableRs2 += monthsdata[i][0];
                    receivable$2 += monthsdata[i][1];
                }

                txtReceivable$.setText(receivable$2 +" $");
                txtReceivableRs.setText(receivableRs2 + " Rs");
                txtReceived$.setText(received$2 +" $");
                txtReceivedRs.setText(receivedRs2 + " Rs");

                lblReceivable$.setText(String.valueOf(receivable$2));
                lblReceivableRs.setText(String.valueOf(receivableRs2));
                lblReceived$.setText(String.valueOf(received$2));
                lblReceivedRs.setText(String.valueOf(receivedRs2));

                lblTotalBookings.setText(String.valueOf(bookingCount));

                tblIncomeReport.setItems(incomeReportsAnuwalyTMS);

                return;  //---1 time only run----
            }
        }
        }
        tblIncomeReport.setItems(incomeReportsMonthlyTMS);

        txtReceivable$.setText(receivable$ +" $");
        txtReceivableRs.setText(receivableRs + " Rs");
        txtReceived$.setText(received$ +" $");
        txtReceivedRs.setText(receivedRs + " Rs");

        lblReceivable$.setText(String.valueOf(receivable$));
        lblReceivableRs.setText(String.valueOf(receivableRs));
        lblReceived$.setText(String.valueOf(received$));
        lblReceivedRs.setText(String.valueOf(receivedRs));

        lblTotalBookings.setText(String.valueOf(number-1));
    }


    public void monthClickDitect(MouseEvent mouseEvent) {
        loadMonths();
    }
}