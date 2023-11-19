package lk.ijse.firstsemfinal.Controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Supplierformcontroller {

    public void btnAddOnSupplier(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(this.getClass().getResource("/view/supplierPopWindowform.fxml"));
        Scene scene=new Scene(parent);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
