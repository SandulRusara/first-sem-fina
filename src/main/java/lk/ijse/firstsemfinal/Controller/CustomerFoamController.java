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
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import lk.ijse.firstsemfinal.DTO.customerDTO;
import lk.ijse.firstsemfinal.DTO.tm.customerTM;
import lk.ijse.firstsemfinal.Model.CustomerModle;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

public class CustomerFoamController implements Initializable {

    @FXML
    private JFXButton ReportButton;

    @FXML
    private TableColumn<customerTM, String> addresscolumn;

    @FXML
    private TableColumn<customerTM, String> contactcolumn;

    @FXML
    private TextField customerContact;

    @FXML
    private TextField customerId;

    @FXML
    private TextField customerName;

    @FXML
    private TextField customeraddress;

    @FXML
    private TableColumn<customerTM, String> datecolumn;

    @FXML
    private JFXButton deleteButton;

    @FXML
    private TableColumn<customerTM, String> idcolumn;

    @FXML
    private TableColumn<customerTM, String> namecolumn;

    @FXML
    private JFXButton saveButton;

    @FXML
    private  TableView<customerTM> table;

    @FXML
    private JFXButton updateButton;


    @FXML
    private AnchorPane subpane;

    @FXML
    private TableColumn<customerTM,JFXButton> actionColum;

    private CustomerModle customerModel=new CustomerModle();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
      setValues();loadValues();

    }

    public void btnAddCustomerOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/view/customerPopWindow.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        CustomerPopWindowcontroller.pane = subpane;
        stage.show();

    }
    public void setValues(){
        //idcolumn.setCellValueFactory(new PropertyValueFactory<customerTM,String>("custId"));
        namecolumn.setCellValueFactory(new PropertyValueFactory<customerTM,String>("name"));
        addresscolumn.setCellValueFactory(new PropertyValueFactory<customerTM,String>("address"));
        contactcolumn.setCellValueFactory(new PropertyValueFactory<customerTM,String>("contact"));
        datecolumn.setCellValueFactory(new PropertyValueFactory<customerTM,String>("date"));
        actionColum.setCellValueFactory(new PropertyValueFactory<customerTM,JFXButton>("button"));

    }
    public  void loadValues(){
        ArrayList<customerDTO> arrayList = CustomerModle.getAllCustomers();


        ObservableList<customerTM> observableList = FXCollections.observableArrayList();

        for (int i = 0; i < arrayList.size(); i++) {
            LocalDate date = LocalDate.now();
            customerTM customerTM = new customerTM(
                    arrayList.get(i).getCustomerId(),
                    arrayList.get(i).getCustomerName(),
                    arrayList.get(i).getCustAddrss(),
                    arrayList.get(i).getCustomerContact(),
                    String.valueOf(date),
                    new JFXButton("Delete"),
                    arrayList.get(i).getEmail()
                    );
            observableList.add(customerTM);

        }
        table.setItems(observableList);
        for (int i = 0; i < observableList.size(); i++) {
            observableList.get(i).getButton().setStyle("-fx-background-color: rgba(175,108,108,1)");
            observableList.get(i).getButton().setAlignment(Pos.CENTER);
            observableList.get(i).getButton().setTextFill(Color.WHITE);
        }

        for (int i = 0; i < observableList.size(); i++) {
            int custId = observableList.get(i).getCustId();
            observableList.get(i).getButton().setOnAction(event->{
                boolean b = CustomerModle.deleteButton(custId);
                if (b){
                    new Alert(Alert.AlertType.CONFIRMATION,"customer deleted").show();
                    loadValues();
                }else{
                   new Alert(Alert.AlertType.ERROR,"Error!!").show();
                }

            });


        }
    }

}
