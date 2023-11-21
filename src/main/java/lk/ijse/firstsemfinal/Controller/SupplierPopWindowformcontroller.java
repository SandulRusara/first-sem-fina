package lk.ijse.firstsemfinal.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.firstsemfinal.DTO.supplierDTO;
import lk.ijse.firstsemfinal.Model.CustomerModle;
import lk.ijse.firstsemfinal.Model.supplierModle;

import java.io.IOException;
import java.sql.SQLException;

public class SupplierPopWindowformcontroller {

    public static AnchorPane pane;

    @FXML
    private JFXButton saveButton;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private JFXButton updateButton;

    public void btnSupplierSaveOnAction(ActionEvent actionEvent) {
        int id = 0;
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();

        supplierDTO supplierDTO=new supplierDTO(0,name,address,contact);
        boolean b = false ;
        try {
            b = supplierModle.saveSupplier(supplierDTO);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (b){
            new Alert(Alert.AlertType.CONFIRMATION, "supplier Saved").show();
        }
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("/view/supplierform.fxml"));
            pane.getChildren().clear();
            pane.getChildren().add(parent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
