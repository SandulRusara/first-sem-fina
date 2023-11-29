package lk.ijse.firstsemfinal.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.firstsemfinal.DTO.customerDTO;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;


import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import lk.ijse.firstsemfinal.Model.CustomerModle;
import lk.ijse.firstsemfinal.mail.Mail;

public class CustomerPopWindowcontroller {
    public static AnchorPane pane;

    @FXML
    private TextField customerContact;

    @FXML
    private TextField customerName;

    @FXML
    private TextField customeraddress;

    @FXML
    private TextField customerEmail;

    @FXML
    private JFXButton saveButton;

    @FXML
    private JFXButton updateButton;

    @FXML
    void oncustomerSave(ActionEvent event) {
        int id = 0;
        String name = customerName.getText();
        String address = customeraddress.getText();
        String contact = customerContact.getText();
        LocalDate date = LocalDate.now();
        String dat = String.valueOf(date);
        String email = customerEmail.getText();

        if(validateCustomer()) {
            customerDTO customerDTO = new customerDTO(id, name, address, contact, dat, email);
            boolean b = false;
            try {
                b = CustomerModle.saveCustomer(customerDTO);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if (b) {


                Mail mail = new Mail();
                mail.setMsg("Hello"+
                        "\nTime : " +
                        Time.valueOf(LocalTime.now()) + "\n" +
                        "Date : " +
                        Date.valueOf(LocalDate.now()));//email message
                mail.setTo(customerEmail.getText()); //receiver's mail
                mail.setSubject("Alert"); //email subject

                Thread thread = new Thread(mail);
                thread.start();


                new Alert(Alert.AlertType.CONFIRMATION, "Customre Saved").show();
            }

            Stage stage = (Stage) saveButton.getScene().getWindow();
            stage.close();
            Parent parent = null;
            try {
                parent = FXMLLoader.load(getClass().getResource("/view/customerFoam.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            pane.getChildren().clear();
            pane.getChildren().add(parent);
        }





    }

    private boolean validateCustomer(){
        boolean matches1 = Pattern.matches("^([ \\u00c0-\\u01ffa-zA-Z'\\-]{3,})+$", customerName.getText());
        if(!matches1){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Invalid name");
            alert.showAndWait();
            return false;
        }

        boolean matches2 = Pattern.matches("^(\\d+\\w*),?\\s*([a-zA-Z\\s]+),?\\s*([a-zA-Z\\s]+),?$", customeraddress.getText());
        if(!matches2){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Invalid address");
            alert.showAndWait();
            return false;
        }

        boolean matches3 = Pattern.matches("^(?:\\+94|0)([1-9])\\d{8}$", customerContact.getText());
        if (!matches3) {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Invalid contact number");
            alert.showAndWait();
            return false;
        }

        boolean matches4 = Pattern.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])", customerEmail.getText());
        if (!matches4) {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Invalid email");
            alert.showAndWait();
            return false;
        }
        return true;
    }

    public void txtNameOnAction(ActionEvent actionEvent) {
        customerContact.requestFocus();
    }

    public void txtAddressOnAction(ActionEvent actionEvent) {
        customerEmail.requestFocus();
    }

    public void txtOnContactActoin(ActionEvent actionEvent) {
        customeraddress.requestFocus();
    }

    public void txtEmailOnAction(ActionEvent actionEvent) {
        saveButton.requestFocus();
    }
}
