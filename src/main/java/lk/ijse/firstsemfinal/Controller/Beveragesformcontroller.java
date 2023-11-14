package lk.ijse.firstsemfinal.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.firstsemfinal.DTO.itemDTO;
import lk.ijse.firstsemfinal.DTO.tm.BeveragesTm;
import lk.ijse.firstsemfinal.Model.ItemModle;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Beveragesformcontroller implements Initializable {

    @FXML
    private TableColumn<BeveragesTm, String> categoriescolum;

    @FXML
    private TableColumn<BeveragesTm, JFXButton> deleteButton1;

    @FXML
    private TableColumn<BeveragesTm, Integer> idcolum;

    @FXML
    private TableColumn<BeveragesTm, String> nameColum;

    @FXML
    private TableColumn<BeveragesTm, String> priceColum;

    @FXML
    private TableView<String> table;

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
        String cat = (String) txtJuiceCetagories.getValue();
        String name = txtName.getText();
        String price = txtPrice.getText();

        itemDTO itemDTO=new itemDTO(0,cat,name,price);
        boolean b = ItemModle.saveItem(itemDTO);
        txtName.setText("");
        txtPrice.setText("");


    }
    public void setValues(){
        idcolum.setCellValueFactory(new PropertyValueFactory<BeveragesTm,Integer>("id"));
        categoriescolum.setCellValueFactory(new PropertyValueFactory<BeveragesTm,String >("name"));
        nameColum.setCellValueFactory(new PropertyValueFactory<BeveragesTm,String>(" price"));
        categoriescolum.setCellValueFactory(new PropertyValueFactory<BeveragesTm,String>("categories"));
        updateButton.setCellValueFactory(new PropertyValueFactory<BeveragesTm,JFXButton>("updateButton"));
        deleteButton1.setCellValueFactory(new PropertyValueFactory<BeveragesTm,JFXButton>("deleteButton"));

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> observableList = FXCollections.observableArrayList("Fresh fruit juice","Lassi","Beverage and Juice powder","Mojitho","Milkshake");
        txtJuiceCetagories.setItems(observableList);
    }
}
