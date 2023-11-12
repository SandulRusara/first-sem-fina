package lk.ijse.firstsemfinal.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lk.ijse.firstsemfinal.DTO.userDTO;
import lk.ijse.firstsemfinal.Model.UserModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

public class LoginFormController {

    @FXML
    private Button loginButton;

    @FXML
    private TextField password;

    @FXML
    private TextField userName;


    @FXML
    void onloginClick(ActionEvent event) {
        System.out.println("adssaddaad");
        ArrayList<userDTO> allUser = UserModel.getAllUser();
        String uname = userName.getText();
        String pass = password.getText();
        for (int i = 0; i < allUser.size(); i++) {
            if (allUser.get(i).getUseName().equals(uname) && allUser.get(i).getPassword().equals(pass)){
                try {
                    Parent parent = FXMLLoader.load(getClass().getResource("/view/dashbord.form.fxml"));
                    Scene scene = new Scene(parent);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    //stage.initStyle(StageStyle.UNDECORATED);
                    stage.show();

                    Stage stage1 = (Stage) loginButton.getScene().getWindow();
                    stage1.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }

}
