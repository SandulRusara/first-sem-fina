package lk.ijse.firstsemfinal.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import lk.ijse.firstsemfinal.DTO.OrderItemDTO;
import lk.ijse.firstsemfinal.DTO.customerDTO;
import lk.ijse.firstsemfinal.DTO.itemDTO;
import lk.ijse.firstsemfinal.DTO.ordersDTO;
import lk.ijse.firstsemfinal.DTO.tm.orderDetailTM;
import lk.ijse.firstsemfinal.DbConnection.Dbconnection;
import lk.ijse.firstsemfinal.Model.CustomerModle;
import lk.ijse.firstsemfinal.Model.ItemModle;
import lk.ijse.firstsemfinal.Model.OrderDetailModel;
import lk.ijse.firstsemfinal.Model.OrderModel;
import lombok.SneakyThrows;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Orderformcontroller implements Initializable {

    @FXML
    private TextField customerCOntactField;

    @FXML
    private TextField customerNameField;

    @FXML
    private TextField itemCodeFeld;

    @FXML
    private TextField itemPriceFeld;

    @FXML
    private ComboBox<String> foodNameFeld;

    @FXML
    private ComboBox<String> bevegrsNameFeld;
    @FXML
    private Label dateField;
    @FXML
    private TextField qtyField;
    @FXML
    private JFXButton addcartButton;
    @FXML
    private TableColumn<orderDetailTM, String> qutyColum;

    @FXML
    private TableColumn<orderDetailTM, String> totalColum;
    @FXML
    private TableColumn<orderDetailTM, String> nameColum;

    @FXML
    private TableColumn<orderDetailTM, String> priceColum;
    @FXML
    private TableColumn<orderDetailTM, Integer> idcolum;
    @FXML
    private TableColumn<orderDetailTM, JFXButton> actionColum;
    @FXML
    private JFXButton placeOrderButton;
    @FXML
    private TextField totalll;
    ObservableList<orderDetailTM> observableList = FXCollections.observableArrayList();
    String foodname  = null;
    @FXML
    private TableView<orderDetailTM> table;
    int alltotal = 0;
    customerDTO customerbyContact ;
    public int clrID;
    @FXML
    void onPlaceOrderClick(ActionEvent event) {
        int oID = 0;

        ordersDTO ordersDTO = new ordersDTO(0,totalll.getText(),"Beverages", dateField.getText(),customerbyContact.getCustomerId());
        boolean b = OrderModel.saveOrder(ordersDTO);
        if (b){
            ArrayList<lk.ijse.firstsemfinal.DTO.ordersDTO> allOrdes = OrderModel.getAllOrdes();
            oID = allOrdes.get(allOrdes.size()-1).getOrderId();
        }
        for (int i = 0; i < observableList.size(); i++) {
            OrderItemDTO orderItemDTO = new OrderItemDTO(oID,observableList.get(i).getItemID());
            boolean b1 = OrderDetailModel.saveOrderDetail(orderItemDTO);
            if (b1){
                clear();
                clearFiled();
                table.setItems(FXCollections.observableArrayList());
            }
        }


    }

    @FXML
    void onaddCartClick(ActionEvent event) {
        setItem();
        loadValues();

    }


    private void loadValues() {
        String itemCodeFeldText = itemCodeFeld.getText();
        String priceFeldText = itemPriceFeld.getText();
        String qtyFieldText = qtyField.getText();
        int pri = Integer.parseInt(priceFeldText);
        int qty = Integer.parseInt(qtyFieldText);
        int total = pri * qty;
        alltotal += total;
        totalll.setText(String.valueOf(alltotal));

        orderDetailTM orderDetailTM = new orderDetailTM(Integer.parseInt(itemCodeFeldText),foodname,priceFeldText,String.valueOf(qty),String.valueOf(total),new JFXButton("Delete"));
        observableList.add(orderDetailTM);

        for (int i = 0; i < observableList.size(); i++) {
            int itemID = observableList.get(i).getItemID();
            observableList.get(i).getDeleteAction().setOnAction(event ->{
                deleteList(itemID);
                observableList.remove(clrID);
                loadValues();
            });
            observableList.get(i).getDeleteAction().setStyle("-fx-background-color: rgba(175,108,108,1)");
            observableList.get(i).getDeleteAction().setTextFill(Color.WHITE);
        }


        table.setItems(observableList);
        clearFiled();
        clear();

    }

    private void deleteList(int itemID) {
        for (int i = 0; i < observableList.size(); i++) {
            if (observableList.get(i).getItemID() == itemID){
                clrID = i;
                break;
            }
        }
    }

    private void setItem() {
        idcolum.setCellValueFactory(new PropertyValueFactory<orderDetailTM, Integer>("itemID"));
        nameColum.setCellValueFactory(new PropertyValueFactory<orderDetailTM,String>("itemName"));
        priceColum.setCellValueFactory(new PropertyValueFactory<orderDetailTM,String>("itemPrice"));
        qutyColum.setCellValueFactory(new PropertyValueFactory<orderDetailTM,String>("qty"));
        totalColum.setCellValueFactory(new PropertyValueFactory<orderDetailTM,String>("total"));
        actionColum.setCellValueFactory(new PropertyValueFactory<orderDetailTM,JFXButton>("deleteAction"));


    }
@SneakyThrows
    @FXML
    void btnNewCustomerOnAction(ActionEvent event) {
       Parent parent= FXMLLoader.load(this.getClass().getResource("/view/customerPopWindow.fxml"));
        Scene scene=new Scene(parent);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }
    @FXML
    void oncontactTypeCustomer(KeyEvent event) {
    if (event.getCode().equals(KeyCode.ENTER)){
        String text = customerCOntactField.getText();
       customerbyContact = CustomerModle.getCustomerbyContact(Integer.parseInt(text));
        customerNameField.setText(customerbyContact.getCustomerName());
    }
    }

    @FXML
    void onFoodSelected(ActionEvent event) {
        clearFiled();
        String foodNameFeldValue = foodNameFeld.getValue();
        foodname = foodNameFeldValue;
        itemDTO itemByName = ItemModle.getItemByName(foodNameFeldValue);
        itemCodeFeld.setText(String.valueOf(itemByName.getItemId()));
        itemPriceFeld.setText(itemByName.getPrice());


    }

    @FXML
    void onbevergesSelected(ActionEvent event) {
        clearFiled();
        String bevegrsNameFeldValue = bevegrsNameFeld.getValue();
        foodname = bevegrsNameFeldValue;
        itemDTO itemByName = ItemModle.getItemByName(bevegrsNameFeldValue);
        itemCodeFeld.setText(String.valueOf(itemByName.getItemId()));
        itemPriceFeld.setText(itemByName.getPrice());
    }
    public void clearFiled(){
        itemCodeFeld.setText("");
        itemPriceFeld.setText("");
        qtyField.setText("");
    }
    public void clear(){
        bevegrsNameFeld.setValue("");
        foodNameFeld.setValue("");
        customerNameField.setText("");
        customerCOntactField.setText("");
        //totalll.setText("");
    }


    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<itemDTO> allItem = ItemModle.getAllItem("Food");
        ArrayList<itemDTO> allItem1 = ItemModle.getAllItem("Drink");
        ObservableList<String> observableList = FXCollections.observableArrayList();
        ObservableList<String> observableList1 = FXCollections.observableArrayList();

        for (int i = 0; i < allItem.size(); i++) {
            observableList.add(allItem.get(i).getItemName());
        }
        foodNameFeld.setItems(observableList);
        for (int i = 0; i < allItem1.size(); i++) {
            observableList1.add(allItem1.get(i).getItemName());
        }
        bevegrsNameFeld.setItems(observableList1);

        LocalDate date = LocalDate.now();
        dateField.setText(String.valueOf(date));

    }

    public void btnPrintOnAction(ActionEvent actionEvent) throws JRException, SQLException {
        InputStream resourceAsStream = getClass().getResourceAsStream("/report/ordersbill.jrxml");
        JasperDesign load = JRXmlLoader.load(resourceAsStream);
        JasperReport jasperReport = JasperCompileManager.compileReport(load);
        JasperPrint jasperPrint = JasperFillManager.fillReport(
                jasperReport,
                null,
                Dbconnection.getInstance().getConnection()
        );

        JasperViewer.viewReport(jasperPrint,false);
    }

}

