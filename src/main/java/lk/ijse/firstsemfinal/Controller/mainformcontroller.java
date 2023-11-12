package lk.ijse.firstsemfinal.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class mainformcontroller implements Initializable {

    @FXML
    private Label date;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");
        String timeNow = simpleDateFormat.format(date1);
        date.setText(timeNow);
    }
}
