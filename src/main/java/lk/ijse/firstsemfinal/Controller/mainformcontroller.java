package lk.ijse.firstsemfinal.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import lk.ijse.firstsemfinal.DTO.customerDTO;
import lk.ijse.firstsemfinal.DTO.employeeDTO;
import lk.ijse.firstsemfinal.DTO.supplierDTO;
import lk.ijse.firstsemfinal.Model.CustomerModle;
import lk.ijse.firstsemfinal.Model.EmployeeModle;
import lk.ijse.firstsemfinal.Model.supplierModle;

import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class mainformcontroller implements Initializable {

    public Label txtCustomer;

    @FXML
    private Label txtEmployee;
    @FXML
    private Label txtSupplier;
    @FXML
    private Label date;
    @FXML
    private BarChart<String, Integer> barChart;
    private CustomerModle customerModle=new CustomerModle();
    private EmployeeModle employeeModle=new EmployeeModle();
    private supplierModle supplierModle=new supplierModle();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");
        String timeNow = simpleDateFormat.format(date1);
        date.setText(timeNow);
        setBarGraphValues();

        ArrayList<customerDTO> allCustomers = CustomerModle.getAllCustomers();
        txtCustomer.setText(allCustomers.size()+"");

        ArrayList<employeeDTO> allEmployee = null;
        try {
            allEmployee = EmployeeModle.getAllEmployee();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        txtEmployee.setText(allEmployee.size()+"");

        ArrayList<supplierDTO> allSupplier = supplierModle.getAllSupplier();
        txtSupplier.setText(allSupplier.size()+"");

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
