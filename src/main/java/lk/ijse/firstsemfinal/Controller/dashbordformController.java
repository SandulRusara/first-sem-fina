package lk.ijse.firstsemfinal.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.text.html.ImageView;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class dashbordformController implements Initializable {
    @FXML
    private JFXButton logout;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private AnchorPane subPane;


    @FXML
    void btnCusOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/customerFoam.fxml"));
        subPane.getChildren().setAll(root);

    }
    @FXML
    void btnorderAction(ActionEvent event) throws IOException {
        Parent parent=FXMLLoader.load(this.getClass().getResource("/view/orderform.fxml"));
        subPane.getChildren().setAll(parent);
    }

    @FXML
    void btnItemOnAction(ActionEvent event) throws IOException {
        Parent parent=FXMLLoader.load(this.getClass().getResource("/view/Itemform.fxml"));
        subPane.getChildren().setAll(parent);
        BevergedUpdatefromController.apane = subPane;

    }

    @FXML
    void btnSupplierOnAction(ActionEvent event) throws IOException {
        Parent parent=FXMLLoader.load(this.getClass().getResource("/view/supplierform.fxml"));
        subPane.getChildren().setAll(parent);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/view/mainform.fxml"));
            subPane.getChildren().setAll(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void btnemployeeAction(ActionEvent actionEvent) throws IOException {
        Parent parent=FXMLLoader.load(getClass().getResource("/view/employeeform.fxml"));
        subPane.getChildren().setAll(parent);
    }

    @FXML
    void btnLogOutOnAction(ActionEvent event) {
        Stage stage1 = (Stage) logout.getScene().getWindow();
        stage1.close();
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("/view/Login.form.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();

    }

    public void btnDashBordactionm(ActionEvent actionEvent) throws IOException {
        Parent parent=FXMLLoader.load(this.getClass().getResource("/view/mainform.fxml"));
        subPane.getChildren().setAll(parent);
    }
}
