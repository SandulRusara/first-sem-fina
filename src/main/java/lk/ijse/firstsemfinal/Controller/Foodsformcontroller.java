package lk.ijse.firstsemfinal.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

public class Foodsformcontroller {
    @FXML
    private TableColumn<?, ?> deleteButton;

    @FXML
    private TableColumn<?, ?> txtCategories;

    @FXML
    private ComboBox<?> txtFoodCategoris;

    @FXML
    private TextField txtId;

    @FXML
    private TableColumn<?, ?> txtName;

    @FXML
    private TextField txtPrice;

    @FXML
    private ComboBox<?> txtTableCategoris;

    @FXML
    private TableColumn<?, ?> updateButton;

    @FXML
    void btnSaveOnAction(ActionEvent event) {

    }

}
