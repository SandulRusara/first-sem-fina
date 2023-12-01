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
import lk.ijse.firstsemfinal.DTO.employeeDTO;
import lk.ijse.firstsemfinal.DTO.tm.EmployeeTM;
import lk.ijse.firstsemfinal.Model.EmployeeModle;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Employeeformcontroller implements Initializable {
    @FXML
    private TableView<EmployeeTM> table;

    @FXML
    private TableColumn<EmployeeTM, String> AddressColum;

    @FXML
    private TableColumn<EmployeeTM, Integer> ContactColum;

    @FXML
    private TableColumn<EmployeeTM, String> NicColum;

    @FXML
    private TableColumn<EmployeeTM, JFXButton> deleteColum;

    @FXML
    private TableColumn<EmployeeTM, Integer> idcolum;

    @FXML
    private TableColumn<EmployeeTM, String> nameColum;

    @FXML
    private AnchorPane subPane;

    @FXML
    private TableColumn<EmployeeTM, JFXButton> updateColum;




    private EmployeeModle employeeModle = new EmployeeModle();


    @FXML
    void btnAddEmployeeAction(ActionEvent event) throws IOException {

        EmployeePopWindowformcontroller.pane = subPane;
        Parent parent = FXMLLoader.load(this.getClass().getResource("/view/employeePopWindowform.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }


    public void setValues() {

        idcolum.setCellValueFactory(new PropertyValueFactory<EmployeeTM, Integer>("idcolum"));
        nameColum.setCellValueFactory(new PropertyValueFactory<EmployeeTM, String>("nameColum"));
        AddressColum.setCellValueFactory(new PropertyValueFactory<EmployeeTM, String>("AddressColum"));
        ContactColum.setCellValueFactory(new PropertyValueFactory<EmployeeTM, Integer>("contactColumn"));
        NicColum.setCellValueFactory(new PropertyValueFactory<EmployeeTM, String>("NicColum"));
        deleteColum.setCellValueFactory(new PropertyValueFactory<EmployeeTM, JFXButton>("deleteColum"));
        updateColum.setCellValueFactory(new PropertyValueFactory<EmployeeTM,JFXButton>("updateColum"));

    }

    public void loadValues() {
        try {
            ArrayList<employeeDTO> arrayList = EmployeeModle.getAllEmployee();
            ObservableList<EmployeeTM> observableList = FXCollections.observableArrayList();
            for (int i = 0; i < arrayList.size(); i++) {
                String employeeId = String.valueOf(arrayList.get(i).getEmployeeId());
                EmployeeTM employeeTM=new EmployeeTM(
                        employeeId,
                        arrayList.get(i).getEmployeeName(),
                        arrayList.get(i).getEmployeeAddress(),
                        arrayList.get(i).getEmployeeContact(),
                        arrayList.get(i).getNic(),
                        new JFXButton("Delete"),
                        new JFXButton("update")
                );
                    observableList.add(employeeTM);

                table.setItems(observableList);
            }

            for (int i = 0; i < observableList.size(); i++) {
                observableList.get(i).getDeleteColum().setStyle("-fx-background-color: rgba(175,108,108,1)");
                observableList.get(i).getDeleteColum().setAlignment(Pos.CENTER);
                observableList.get(i).getDeleteColum().setTextFill(Color.WHITE);
                observableList.get(i).getUpdateColum().setStyle("-fx-background-color: rgba(96, 120, 205, 0.97)");
                observableList.get(i).getUpdateColum().setTextFill(Color.WHITE);

            }
            for (int i = 0; i < observableList.size(); i++) {
                int empID = Integer.parseInt(observableList.get(i).getIdcolum());
                observableList.get(i).getDeleteColum().setOnAction(event-> {
                    try {
                        boolean b = EmployeeModle.deleteButton(empID);
                        if (b){
                            new Alert(Alert.AlertType.CONFIRMATION,"Employee Deleted").show();
                            loadValues();
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                });
                int emppID = Integer.parseInt(observableList.get(i).getIdcolum());
                String name = observableList.get(i).getNameColum();
                String address = observableList.get(i).getAddressColum();
                String contact = observableList.get(i).getContactColumn();
                int userNmae = arrayList.get(i).getUserId();
                String nic = observableList.get(i).getNicColum();

                observableList.get(i).getUpdateColum().setOnAction(actionEvent -> {
                    try {
                        EmployeeUpdateformcontroller.id = emppID;
                        EmployeeUpdateformcontroller.userID = userNmae;
                        EmployeeUpdateformcontroller.name = name;
                        EmployeeUpdateformcontroller.address = address;
                        EmployeeUpdateformcontroller.contact = contact;
                        EmployeeUpdateformcontroller.nic = nic;
                        EmployeeUpdateformcontroller.apane = subPane;
                        Parent parent = FXMLLoader.load(getClass().getResource("/view/employeeUpdateform.fxml"));
                        Scene scene = new Scene(parent);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.setResizable(false);
                        stage.show();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });


            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setValues();
        loadValues();
    }
}
