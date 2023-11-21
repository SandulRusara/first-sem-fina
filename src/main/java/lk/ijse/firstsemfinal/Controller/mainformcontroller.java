package lk.ijse.firstsemfinal.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class mainformcontroller implements Initializable {

    @FXML
    private Label date;
    @FXML
    private BarChart<String, Integer> barChart;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");
        String timeNow = simpleDateFormat.format(date1);
        date.setText(timeNow);
        setBarGraphValues();
    }
    private void setBarGraphValues() {
        XYChart.Series<String, Integer> series = new XYChart.Series();
        series.setName("No. of Customers");
        series.getData().add(new XYChart.Data("JAN", 10));
        series.getData().add(new XYChart.Data("FEB", 15));
        series.getData().add(new XYChart.Data("MAR", 30));
        series.getData().add(new XYChart.Data("APR", 45));
        series.getData().add(new XYChart.Data("MAY", 10));
        series.getData().add(new XYChart.Data("JUN", 25));
        series.getData().add(new XYChart.Data("JUL", 50));
        series.getData().add(new XYChart.Data("AUG", 10));
        series.getData().add(new XYChart.Data("SEP", 10));
        series.getData().add(new XYChart.Data("OCT", 320));
        series.getData().add(new XYChart.Data("NOV", 320));
        series.getData().add(new XYChart.Data("DEC", 210));

        barChart.getData().addAll(series);
    }

}
