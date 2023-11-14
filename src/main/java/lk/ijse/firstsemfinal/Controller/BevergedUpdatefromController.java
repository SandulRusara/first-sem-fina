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

public class BevergedUpdatefromController implements Initializable {
    public static int itemID;
    public static String itemName;
    public static String itemPrice;
    public static String itemcat;
    public static AnchorPane apane;


    @FXML
    private TextField bevergesName;

    @FXML
    private TextField bevergesPrice;

    @FXML
    private JFXButton canselButton;

    @FXML
    private JFXComboBox<String> categoryCombo;

    @FXML
    private JFXButton saveButton;

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        int item= itemID;
        String nameText = bevergesName.getText();
        String priceText = bevergesPrice.getText();
        String cat = categoryCombo.getValue();

        itemDTO itemDTO = new itemDTO(item,cat,nameText,priceText);
        boolean b = ItemModle.updateItem(itemDTO);
        if (b){
            new Alert(Alert.AlertType.CONFIRMATION,"Update successfully");
            Stage stage = (Stage) saveButton.getScene().getWindow();
            stage.close();
            Parent parent = null;
            try {
                parent = FXMLLoader.load(getClass().getResource("/view/beveragesform.fxml"));
                apane.getChildren().clear();
                apane.getChildren().add(parent);
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
        ObservableList<String> observableList = FXCollections.observableArrayList("Fresh fruit juice","Lassi","Beverage and Juice powder","Mojitho","Milkshake");
        categoryCombo.setItems(observableList);
        bevergesName.setText(itemName);
        bevergesPrice.setText(itemPrice);
        categoryCombo.setValue(itemcat);



    }
}
