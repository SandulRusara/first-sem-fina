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
import lk.ijse.firstsemfinal.DTO.customerDTO;
import lk.ijse.firstsemfinal.Model.CustomerModle;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class CustomerUpdateformcontroller implements Initializable {
    public static int id;
    public static String name;
    public static String address;
    public static String con;
    public static String mail;

    @FXML
    private TextField customerContact;

    @FXML
    private TextField customerEmail;

    @FXML
    private TextField customerName;

    @FXML
    private TextField customeraddress;

    @FXML
    private JFXButton saveButton;

    public static AnchorPane apane;


    @FXML
    void oncustomerSave(ActionEvent event) throws IOException {
        String name = customerName.getText();
        String address = customeraddress.getText();
        String con = customerContact.getText();
        String mail = customerEmail.getText();
        LocalDate now = LocalDate.now();
        customerDTO customerDTO = new customerDTO(id,name,address,con,String.valueOf(now),mail);
        boolean b = CustomerModle.updateCustomer(customerDTO);
        if (b){
            Stage stage = (Stage) saveButton.getScene().getWindow();
            stage.close();
            new Alert(Alert.AlertType.CONFIRMATION,"Update Succefully!").show();
            Parent parent = FXMLLoader.load(getClass().getResource("/view/customerFoam.fxml"));
            apane.getChildren().clear();
            apane.getChildren().add(parent);
        }

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customerName.setText(name);
        customeraddress.setText(address);
        customerContact.setText(con);
        customerEmail.setText(mail);
    }

    public void txtEmailOnAction(ActionEvent actionEvent) {
    }

    public void txtNameOnAction(ActionEvent actionEvent) {
    }

    public void txtAddressOnAction(ActionEvent actionEvent) {
    }

    public void txtOnContactActoin(ActionEvent actionEvent) {
    }
}
