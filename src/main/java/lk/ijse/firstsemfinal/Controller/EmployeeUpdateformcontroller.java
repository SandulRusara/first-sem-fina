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
import lk.ijse.firstsemfinal.DTO.employeeDTO;
import lk.ijse.firstsemfinal.DTO.supplierDTO;
import lk.ijse.firstsemfinal.Model.EmployeeModle;
import lk.ijse.firstsemfinal.Model.supplierModle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static lk.ijse.firstsemfinal.Controller.SupplierUpdateformcontroller.id;

public class EmployeeUpdateformcontroller implements Initializable {

    public static int  id;
    public static String name;
    public static String address;
    public static String contact;
    public static int userID;

    public static String nic;


  public static AnchorPane apane;

    @FXML
    private JFXButton saveButton;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtNic;

    @FXML
    void btnSaveOnAction(ActionEvent event) throws IOException {
        String nameText = txtName.getText();
        String addressText = txtAddress.getText();
        String contactText = txtContact.getText();
        String nicText = txtNic.getText();

        employeeDTO employeeDTO = new employeeDTO(id,nameText,addressText,contactText,userID,nicText);
        boolean b = EmployeeModle.updateEmployee(employeeDTO);
        System.out.println(b);
        if (b) {
            new Alert(Alert.AlertType.CONFIRMATION, "Update successfully").show();
            Stage stage = (Stage) saveButton.getScene().getWindow();
            stage.close();
            Parent parent  = FXMLLoader.load(getClass().getResource("/view/employeeform.fxml"));
            apane.getChildren().clear();
            apane.getChildren().add(parent);

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
      txtName.setText(name);
      txtAddress.setText(address);
      txtContact.setText(contact);
      txtNic.setText(nic);
    }
}
