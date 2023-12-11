package lk.ijse.firstsemfinal.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.firstsemfinal.DTO.customerDTO;
import lk.ijse.firstsemfinal.DTO.employeeDTO;
import lk.ijse.firstsemfinal.Model.CustomerModle;
import lk.ijse.firstsemfinal.Model.EmployeeModle;
import lombok.SneakyThrows;

import java.util.regex.Pattern;

public class EmployeePopWindowformcontroller {
    public static int UserID;

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
    private TextField txtNic;

    @FXML
    private JFXButton updateButton;


    EmployeeModle employeeModle=new EmployeeModle();



    @FXML
    @SneakyThrows
    void btnSaveOnAction(ActionEvent event) {
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();
        String nic = txtNic.getText();

        if (validationEmployee()) {
            employeeDTO employeeDTO = new employeeDTO(0, name, address, contact, UserID, nic);
            boolean b = EmployeeModle.saveEmployee(employeeDTO);
            Stage stage = (Stage) updateButton.getScene().getWindow();
            stage.close();
            Parent parent = FXMLLoader.load(getClass().getResource("/view/employeeform.fxml"));
            pane.getChildren().clear();
            pane.getChildren().add(parent);
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {

    }

    public boolean validationEmployee(){
        boolean matches1 = Pattern.matches("^([ \\u00c0-\\u01ffa-zA-Z'\\-]{3,})+$", txtName.getText());
        if (!matches1){
            Alert alert = new Alert(Alert.AlertType.ERROR, "invalid name");
            alert.showAndWait();
            return false;
        }

      /*  boolean matches2 = Pattern.matches("^(\\d+\\w*),?\\s*([a-zA-Z\\s]+),?\\s*([a-zA-Z\\s]+),?$", txtAddress.getText());
        if(!matches2){
            Alert alert = new Alert(Alert.AlertType.ERROR, "invalid address");
            alert.showAndWait();
            return false;
        }*/

        boolean matches3 = Pattern.matches("^(?:\\+94|0)([1-9])\\d{8}$", txtContact.getText());
        if(!matches3){
            Alert alert = new Alert(Alert.AlertType.ERROR, "invalid contact");
            alert.showAndWait();
            return false;
        }

       /* boolean matches4 = Pattern.matches("^[0-9]{9}[vV]$", txtNic.getText());
        if(!matches4){
            Alert alert = new Alert(Alert.AlertType.ERROR, "invalid nic");
            alert.showAndWait();
            return false;
        }*/
        return true;
    }
}
