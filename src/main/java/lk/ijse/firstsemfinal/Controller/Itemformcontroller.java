package lk.ijse.firstsemfinal.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;



public class Itemformcontroller {

    private Object subPane;

    public void btnFoodsOnAction(ActionEvent actionEvent) throws IOException {
      Parent parent=FXMLLoader.load(this.getClass().getResource("/view/foodsform.fxml"));
        Scene scene=new Scene(parent);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
    }

    public void btnBeveragesOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent=FXMLLoader.load(this.getClass().getResource("/view/beveragesform.fxml"));
        Scene scene=new Scene(parent);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();

    }
}
