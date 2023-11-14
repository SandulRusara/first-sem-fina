package lk.ijse.firstsemfinal.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import lk.ijse.firstsemfinal.DTO.itemDTO;
import lk.ijse.firstsemfinal.DTO.tm.BeveragesTm;
import lk.ijse.firstsemfinal.Model.EmployeeModle;
import lk.ijse.firstsemfinal.Model.ItemModle;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Beveragesformcontroller implements Initializable {

    @FXML
    private TableColumn<?,?> categoriescolum;

    @FXML
    private TableColumn<BeveragesTm, JFXButton> deleteButton1;

    @FXML
    private TableColumn<?,?> idcolum;

    @FXML
    private TableColumn<?,?> nameColum;

    @FXML
    private TableColumn<BeveragesTm, String> priceColum;

    @FXML
    private TableView<BeveragesTm> table;

    @FXML
    private ComboBox<String> txtJuiceCetagories;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPrice;

    @FXML
    private ComboBox<String> txtTableCategories;

    @FXML
    private TableColumn<BeveragesTm, JFXButton> updateButton;

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException {
        String cat = txtJuiceCetagories.getValue();
        String name = txtName.getText();
        String price = txtPrice.getText();

        itemDTO itemDTO=new itemDTO(0,cat,name,price);
        boolean b = ItemModle.saveItem(itemDTO);
        if (b){
            new Alert(Alert.AlertType.CONFIRMATION,"Item Deleted");
            loadValues();
        }
        txtName.setText("");
        txtPrice.setText("");


    }
    public void setValues(){
        idcolum.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColum.setCellValueFactory(new PropertyValueFactory<>("name"));
        categoriescolum.setCellValueFactory(new PropertyValueFactory< >("category"));
        priceColum.setCellValueFactory(new PropertyValueFactory<>("price"));
        updateButton.setCellValueFactory(new PropertyValueFactory<BeveragesTm,JFXButton>("updateButton"));
        deleteButton1.setCellValueFactory(new PropertyValueFactory<BeveragesTm,JFXButton>("deleteButton"));

    }
    public void loadValues() throws SQLException {
        ArrayList<itemDTO> allItem = ItemModle.getAllItem();
        ObservableList<BeveragesTm> object = FXCollections.observableArrayList();

        for (int i = 0; i < allItem.size(); i++) {
            String itmID = String.valueOf(allItem.get(i).getItemId());
            BeveragesTm beveragesTm = new BeveragesTm(
                    itmID,
                    allItem.get(i).getItemName(),
                    allItem.get(i).getItemCategory()
                    ,
                    allItem.get(i).getPrice(),
                    new JFXButton("Update"),
                    new JFXButton("Delete"));
            object.add(beveragesTm);
        }

        table.setItems(object);
        for (int i = 0; i < object.size(); i++) {
            object.get(i).getDeleteButton().setStyle("-fx-background-color: rgba(175,108,108,1)");
            object.get(i).getDeleteButton().setAlignment(Pos.CENTER);
            object.get(i).getDeleteButton().setTextFill(Color.WHITE);
        }
        for (int i = 0; i < object.size(); i++) {
            int ID = Integer.parseInt(object.get(i).getId());
            object.get(i).getDeleteButton().setOnAction(event -> {
                try {
                    boolean b = ItemModle.deleteButton(ID);
                    if (b){
                        new Alert(Alert.AlertType.CONFIRMATION,"Employee succefully Deleted");
                        loadValues();
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> observableList = FXCollections.observableArrayList("Fresh fruit juice","Lassi","Beverage and Juice powder","Mojitho","Milkshake");
        txtJuiceCetagories.setItems(observableList);
        setValues();
        try {
            loadValues();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
