package contoller.centerPanel;

import db.MealPackDataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import module.MealPack;
import view.CenterPanel.tm.MealpackTM;

public class MealPackDetailsContoller {
    public TableColumn clmPackInclude;
    public TableView tableMealpackDeatils;
    public TableColumn clmMealpackname;
    public TableColumn clmMealPackNo;
    public TableColumn clmGuestPrice;
    public TableColumn clmlocalPrice;
    public TableColumn clmAvailble;
    public TableColumn clmMoreDeatils;

    public static AnchorPane CenterOpenPanel;

    public void initialize(){
        clmMealPackNo.setCellValueFactory(new PropertyValueFactory<>("mealPackNo"));
        clmMealpackname.setCellValueFactory(new PropertyValueFactory<>("mealPackName"));
        clmPackInclude.setCellValueFactory(new PropertyValueFactory<>("include"));
        clmGuestPrice.setCellValueFactory(new PropertyValueFactory<>("guestPrice"));
        clmAvailble.setCellValueFactory(new PropertyValueFactory<>("availbility"));
        clmlocalPrice.setCellValueFactory(new PropertyValueFactory<>("localPrice"));
        clmMoreDeatils.setCellValueFactory(new PropertyValueFactory<>("btn"));

        loadAll();
    }

    public void closeOnActon(MouseEvent mouseEvent) {
        CenterOpenPanel.getChildren().clear();
    }

    private void loadAll(){
        ObservableList<MealpackTM> observableList = FXCollections.observableArrayList();

        for (MealPack pack : MealPackDataBase.mealPacks){
            Button btn = new Button();
            btn.setPrefSize(192,31);
            btn.setTextFill(Color.WHITE);
            btn.setText("More Details");

            if(pack.isAvailbility()){
                btn.setStyle("-fx-background-color: Green");
            }else{
                btn.setStyle("-fx-background-color: Red");
            }

            MealpackTM tm = new MealpackTM(pack.getMealPackNo(),pack.getMealPackName(),pack.getGuestPrice()+" "+pack.getGuestPriceType(),pack.getLocalPrice()+" "+pack.getLocalPriceType(),pack.isAvailbility()?"Available":"Not Available",pack.getInclude(),btn);
            observableList.add(tm);

            btn.setOnAction((event) -> {
                roomDetailsButton(btn , pack);
            });
        }

        tableMealpackDeatils.setItems(observableList);
    }

    private void roomDetailsButton(Button btn,MealPack pack){
        btn.setOnAction((event) -> {
            Alert alert = new Alert(Alert.AlertType.NONE,
                    "Meal Pack No \t:- "+pack.getMealPackNo()+
                            "\n\nName \t\t:-"+pack.getMealPackName()+
                            "\n\nGuest Price \t:-"+pack.getGuestPrice()+" "+pack.getGuestPriceType()+
                            "\n\nLocal Price \t:-"+pack.getLocalPrice()+" "+pack.getLocalPriceType()+
                            "\n\nAvailability \t:-"+ (pack.isAvailbility()?"Available":"NoAvailable")+
                            "\n\nInclude \t\t:-"+pack.getInclude()+
                            "\n\nNote \t\t:-"+pack.getNote(),
                    ButtonType.OK );

            alert.setTitle("Meal pack Details");
            alert.show();
        });
    }
}
