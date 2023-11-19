package lk.ijse.firstsemfinal.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import lk.ijse.firstsemfinal.DTO.itemDTO;
import lk.ijse.firstsemfinal.DTO.tm.BeveragesTm;
import lk.ijse.firstsemfinal.DTO.tm.FoodsTM;
import lk.ijse.firstsemfinal.Model.ItemModle;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Foodsformcontroller implements Initializable {

    @FXML
    public AnchorPane pane;

    @FXML
    private TableColumn<FoodsTM, JFXButton > deleteButtoncolumn;

    @FXML
    private TableColumn<FoodsTM, String> txtCategoriescolumn;

    @FXML
    private ComboBox<String> txtFoodCategoris;

    @FXML
    private TextField txtName;


    @FXML
    private TableColumn<FoodsTM, String> txtIdcolumn;

    @FXML
    private TableColumn<FoodsTM, String> txtNamecolumn;

    @FXML
    private TextField txtPrice;

    @FXML
    private TableColumn<FoodsTM, String> txtPricecolumn;

    @FXML
    private ComboBox<String> txtTableCategoris;

    @FXML
    private TableColumn<FoodsTM, JFXButton> updateButtoncolumn;

    @FXML
    private TableView<FoodsTM> table;



    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException {

        String cat = txtFoodCategoris.getValue();
        String name = txtName.getText();
        String price = txtPrice.getText();
        String type = "Food";

        itemDTO itemDTO=new itemDTO(0,cat,name,price,type);
        boolean b = ItemModle.saveItem(itemDTO);
        if (b){
            new Alert(Alert.AlertType.CONFIRMATION,"Item Saved").show();
            loadValues();

        }
        txtName.setText("");
        txtPrice.setText("");

    }
    public void setValues(){
        txtIdcolumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        txtNamecolumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        txtCategoriescolumn.setCellValueFactory(new PropertyValueFactory<>("Categories"));
        txtPricecolumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        deleteButtoncolumn.setCellValueFactory(new PropertyValueFactory<FoodsTM, JFXButton>("deleteButton"));
        updateButtoncolumn.setCellValueFactory(new PropertyValueFactory<FoodsTM,JFXButton>("updateButton"));
    }

    public void loadValues() throws SQLException {
        ArrayList<itemDTO> allItem = ItemModle.getAllItem("Food");
        ObservableList<FoodsTM> objects = FXCollections.observableArrayList();

        for (int i = 0; i < allItem.size(); i++) {
            String itmID = String.valueOf(allItem.get(i).getItemId());
            FoodsTM foodsTM  = new FoodsTM(
                    itmID,
                    allItem.get(i).getItemName(),
                    allItem.get(i).getItemCategory()
                    ,
                    allItem.get(i).getPrice(),
                    new JFXButton("Delete"),
                    new JFXButton("Update"));
            objects.add(foodsTM);
        }
    table.setItems(objects);
        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).getDeleteButton().setStyle("-fx-background-color: rgba(175,108,108,1)");
            objects.get(i).getDeleteButton().setAlignment(Pos.CENTER);
            objects.get(i).getDeleteButton().setTextFill(Color.WHITE);

            objects.get(i).getUpdateButton().setStyle("-fx-background-color: rgba(96, 120, 205, 0.97)");
            objects.get(i).getUpdateButton().setTextFill(Color.WHITE);
        }
        for (int i = 0; i < objects.size(); i++) {
            int ID = Integer.parseInt(objects.get(i).getId());
            objects.get(i).getDeleteButton().setOnAction(event -> {
                try {
                    boolean b = ItemModle.deleteButton(ID);
                    if (b){
                        new Alert(Alert.AlertType.CONFIRMATION,"foods succefully Deleted");
                        loadValues();
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
            String id = String.valueOf(allItem.get(i).getItemId());
            String name = allItem.get(i).getItemName();
            String price = allItem.get(i).getPrice();
            String category = allItem.get(i).getItemCategory();
            objects.get(i).getUpdateButton().setOnAction(event ->{
                FoodsUpdateformController.itemID  = Integer.parseInt(id);
                FoodsUpdateformController.itemName = name;
                FoodsUpdateformController.itemPrice = price ;
                FoodsUpdateformController.itemcat = category;
                FoodsUpdateformController.pane = this.pane;
                Parent parent = null;
                try {
                    parent = FXMLLoader.load(getClass().getResource("/view/foodsUpdateform.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Scene scene = new Scene(parent);
                Stage stage = new Stage();
                stage.setResizable(false);
                stage.setScene(scene);
                stage.show();
            });


        }
    }
    public void initialize(URL url, ResourceBundle resourceBundle){
        ObservableList<String> observableList = FXCollections.observableArrayList("Hot Bun","Lenumes","Beked goods","Meat","Snacks");
        txtFoodCategoris.setItems(observableList);
        setValues();
        try {
            loadValues();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }


}
