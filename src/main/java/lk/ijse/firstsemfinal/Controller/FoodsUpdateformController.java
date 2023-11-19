package lk.ijse.firstsemfinal.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.firstsemfinal.DTO.itemDTO;
import lk.ijse.firstsemfinal.Model.ItemModle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FoodsUpdateformController implements Initializable {
    public static int itemID;
    public static String itemName;
    public static String itemPrice;
    public static String itemcat;
    public static AnchorPane pane;
    @FXML
    private JFXButton canselButton;

    @FXML
    private JFXComboBox<String> categoryCombo;

    @FXML
    private TextField foodsName;

    @FXML
    private TextField foodsPrice;

    @FXML
    private JFXButton saveButton;

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        int itemID1 = itemID;
        String name = foodsName.getText();
        String price = foodsPrice.getText();
        Object cat = categoryCombo.getValue();
        String type = "Food";

        itemDTO itemDTO=new itemDTO(itemID1, (String) cat,name,price,type);
        boolean b=ItemModle.updateItem(itemDTO);
        if(b){
            new Alert(Alert.AlertType.CONFIRMATION,"update successfully");
            Stage stage = (Stage) saveButton.getScene().getWindow();
            stage.close();
            Parent parent = null;
            try {
                parent = FXMLLoader.load(getClass().getResource("/view/foodsform.fxml"));
                pane.getChildren().clear();
                pane.getChildren().add(parent);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

    }

    @FXML
    void onCanselClick(ActionEvent event) {
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> observableList = FXCollections.observableArrayList("Hot Bun", "Lenumes", "Beked goods", "Meat", "Snacks");
        categoryCombo.setItems(observableList);
        foodsPrice.setText(itemName);
        foodsPrice.setText(itemPrice);
        categoryCombo.setValue(itemcat);
    }
}
