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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import lk.ijse.firstsemfinal.DTO.customerDTO;
import lk.ijse.firstsemfinal.DTO.supplierDTO;
import lk.ijse.firstsemfinal.DTO.tm.customerTM;
import lk.ijse.firstsemfinal.DTO.tm.supplierTM;
import lk.ijse.firstsemfinal.Model.CustomerModle;
import lk.ijse.firstsemfinal.Model.ItemModle;
import lk.ijse.firstsemfinal.Model.supplierModle;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class Supplierformcontroller implements Initializable {
    @FXML
    private TableColumn<supplierTM, String> addressColum;

    @FXML
    private TableColumn<supplierTM, String> contactColum;

    @FXML
    private TableColumn<supplierTM, JFXButton> deleteColum;

    @FXML
    private TableColumn<supplierTM, String> idColum;

    @FXML
    private TableColumn<supplierTM, String> nameColum;

    @FXML
    private  TableView<supplierTM> table;
    @FXML
    private  AnchorPane apane;

    @FXML
    private TableColumn<supplierTM, JFXButton> updateColum;


    public void btnAddOnSupplier(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(this.getClass().getResource("/view/supplierPopWindowform.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        SupplierPopWindowformcontroller.pane = apane;
    }

    public void setValues() {
        idColum.setCellValueFactory(new PropertyValueFactory<supplierTM, String>("id"));
        nameColum.setCellValueFactory(new PropertyValueFactory<supplierTM, String>("name"));
        addressColum.setCellValueFactory(new PropertyValueFactory<supplierTM, String>("address"));
        contactColum.setCellValueFactory(new PropertyValueFactory<supplierTM, String>("contact"));
        deleteColum.setCellValueFactory(new PropertyValueFactory<supplierTM, JFXButton>("deleteButton"));
        updateColum.setCellValueFactory(new PropertyValueFactory<supplierTM, JFXButton>("updateButton"));

    }

    public  void loadValues() {
        ArrayList<supplierDTO> arrayList = supplierModle.getAllSupplier();


        ObservableList<supplierTM> observableList = FXCollections.observableArrayList();

        for (int i = 0; i < arrayList.size(); i++) {
            supplierTM supplierTM = new supplierTM(
                    arrayList.get(i).getSupplierId(),
                    arrayList.get(i).getSupplierName(),
                    arrayList.get(i).getSupplierAddress(),
                    arrayList.get(i).getSupplierContact(),
                    new JFXButton("Delete"),
                    new JFXButton("update")

            );
            observableList.add(supplierTM);

        }
        table.setItems(observableList);
        for (int i = 0; i < observableList.size(); i++) {
            observableList.get(i).getDeleteButton().setStyle("-fx-background-color: rgba(175,108,108,1)");
           observableList.get(i).getDeleteButton().setAlignment(Pos.CENTER);
           observableList.get(i).getDeleteButton().setTextFill(Color.WHITE);
           observableList.get(i).getUpdateButton().setStyle("-fx-background-color: rgba(96, 120, 205, 0.97)");
           observableList.get(i).getUpdateButton().setTextFill(Color.WHITE);

            for (int j = 0; j < observableList.size(); j++) {
                int ID = Integer.parseInt(String.valueOf(observableList.get(i).getId()));
                observableList.get(i).getDeleteButton().setOnAction(event ->{
                    boolean b = supplierModle.deleteButton(ID);
                    if (b){
                        new Alert(Alert.AlertType.CONFIRMATION,"supplier succefully Deleted").show();
                        loadValues();
                    }
                });
                int id = observableList.get(i).getId();
                String name = observableList.get(i).getName();
                String address=observableList.get(i).getAddress();
                String contact=observableList.get(i).getContact();


                observableList.get(i).getUpdateButton().setOnAction(event ->{
                    SupplierUpdateformcontroller.id = id;
                    SupplierUpdateformcontroller.name=name;
                    SupplierUpdateformcontroller.address=address;
                    SupplierUpdateformcontroller.contact=contact;
                    SupplierUpdateformcontroller.apane = apane;
                    Parent parent = null;
                    try {
                        parent = FXMLLoader.load(getClass().getResource("/view/supplierUpdateform.fxml"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Scene scene = new Scene(parent);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.show();

                });

            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setValues();
        loadValues();
    }
}




