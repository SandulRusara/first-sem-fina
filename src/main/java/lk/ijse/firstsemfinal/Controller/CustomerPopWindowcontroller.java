package lk.ijse.firstsemfinal.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.firstsemfinal.DTO.customerDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import static java.lang.Integer.parseInt;


import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import lk.ijse.firstsemfinal.Model.CustomerModle;

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

        customerDTO customerDTO = new customerDTO(id,name,address,contact,dat,email);
        boolean b = false ;
        try {
             b = CustomerModle.saveCustomer(customerDTO);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (b){
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
