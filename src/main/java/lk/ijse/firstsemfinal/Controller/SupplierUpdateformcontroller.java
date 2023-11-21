package lk.ijse.firstsemfinal.Controller;

import com.jfoenix.controls.JFXButton;
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
import lk.ijse.firstsemfinal.DTO.supplierDTO;
import lk.ijse.firstsemfinal.Model.CustomerModle;
import lk.ijse.firstsemfinal.Model.EmployeeModle;
import lk.ijse.firstsemfinal.Model.ItemModle;
import lk.ijse.firstsemfinal.Model.supplierModle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SupplierUpdateformcontroller implements Initializable {
    public static int id;
    public static String name;
    public static String address;
    public static String contact;

    @FXML
    private JFXButton saveButton;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtName;

    public static AnchorPane apane;

    @FXML
    void btnSupplierCanselOnAction(ActionEvent event) {
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();

    }


    @FXML
    void btnSupplierSaveOnAction(ActionEvent event) {
        String name = txtName.getText();
        String address = txtAddress.getText();
        String con = txtContact.getText();


        supplierDTO supplierDTO = new supplierDTO(id,name,address,con);
        boolean b = supplierModle.updateSupplier(supplierDTO);
        if (b){
            new Alert(Alert.AlertType.CONFIRMATION,"Update successfully");
            Stage stage = (Stage) saveButton.getScene().getWindow();
            stage.close();
//            Parent parent = null;
//            try {
//                parent = FXMLLoader.load(getClass().getResource("/view/foodsUpdateform.fxml"));
//                apane.getChildren().clear();
//                apane.getChildren().add(parent);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtName.setText(name);
        txtAddress.setText(address);
        txtContact.setText(contact);
    }
}
