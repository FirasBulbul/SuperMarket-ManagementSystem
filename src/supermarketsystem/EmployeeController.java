/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarketsystem;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import static supermarketsystem.MyDatabase.conn;

/**
 *
 * @author pc
 */
public class EmployeeController implements Initializable {

    SupermarketSystem ss = new SupermarketSystem();
    ObservableList<Employee> employees = FXCollections.observableArrayList();
//    Employee employee = new Employee();

    @FXML
    private TableView<Employee> EmployeeTable;

    @FXML
    private RadioButton female;

    @FXML
    public Button backBtn;

    @FXML
    private RadioButton male;

    @FXML
    private TextField ID;

    @FXML
    private TableColumn<Employee, Integer> IDCol;

    @FXML
    private TextField address;

    @FXML
    private TableColumn<Employee, String> cityCol;

    @FXML
    private TableColumn<Employee, String> genderCol;

    @FXML
    private TextField name;

    @FXML
    private TableColumn<Employee, String> nameCol;

    @FXML
    private TextField salary;

    @FXML
    private TableColumn<Employee, Integer> salaryCol;

    @FXML
    private ComboBox<String> type;

    @FXML
    private TableColumn<Employee, String> typeCol;

    public void RefreshTable() throws SQLException, Exception {

        nameCol.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
        IDCol.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("id"));
        cityCol.setCellValueFactory(new PropertyValueFactory<Employee, String>("address"));
        typeCol.setCellValueFactory(new PropertyValueFactory<Employee, String>("type"));
        salaryCol.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("salary"));
        genderCol.setCellValueFactory(new PropertyValueFactory<Employee, String>("gender"));
        employees = MyDatabase.getAllEmployees();
        EmployeeTable.setItems(employees);
    }

    public void FieldsData() throws Exception {
        EmployeeTable.getSelectionModel().selectedItemProperty().addListener(listener -> {
            Employee employee = EmployeeTable.getSelectionModel().getSelectedItem();
            name.setText(employee.getName());
            ID.setText(String.valueOf(employee.getId()));
            address.setText(employee.getAddress());
            type.getSelectionModel().select(employee.getType());
            salary.setText(String.valueOf(employee.getSalary()));
            if (employee.getGender().equals("Male")) {
                male.setSelected(true);
            } else if (employee.getGender().equals("Female")) {
                female.setSelected(true);
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        type.setItems(FXCollections.observableArrayList("Admin", "User"));
        try {
            RefreshTable();
            FieldsData();
        } catch (Exception ex) {
            Logger.getLogger(EmployeeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void add(ActionEvent event) throws SQLException, Exception {
        MyDatabase.getConnection();
        PreparedStatement statement = conn.prepareStatement(
                "Insert into employees values(?, ?, ?, ?, ?, ?)");
        statement.setString(1, name.getText());
        statement.setInt(2, Integer.valueOf(ID.getText()));
        statement.setString(3, address.getText());
        statement.setString(4, (String) type.getSelectionModel().getSelectedItem());
        statement.setInt(5, Integer.valueOf(salary.getText()));
        if (male.isSelected()) {
            statement.setString(6, "Male");
        } else if (female.isSelected()) {
            statement.setString(6, "Female");
        }
        statement.executeUpdate();
        RefreshTable();
        name.clear();
        ID.clear();
        address.clear();
        salary.clear();
    }

    @FXML
    void delete(ActionEvent event) throws SQLException, Exception {
        MyDatabase.getConnection();
        PreparedStatement statement = conn.prepareStatement(
                "DELETE FROM employees WHERE ID = ?");
        statement.setInt(1, Integer.valueOf(ID.getText()));
        statement.executeUpdate();
        RefreshTable();
    }

    @FXML
    void printReport(ActionEvent event) {

    }

    @FXML
    void update(ActionEvent event) throws SQLException, Exception {
        MyDatabase.getConnection();
        PreparedStatement statement = conn.prepareStatement(
                "update employees set name=?, address=?, type=?, salary=?, gender=? where id=?");
        statement.setString(1, name.getText());
        statement.setString(2, address.getText());
        statement.setString(3, (String) type.getSelectionModel().getSelectedItem());
        statement.setInt(4, Integer.valueOf(salary.getText()));
        if (male.isSelected()) {
            statement.setString(5, "Male");
        } else if (female.isSelected()) {
            statement.setString(5, "Female");
        }
        statement.setInt(6, Integer.valueOf(ID.getText()));
        statement.executeUpdate();
        RefreshTable();
        name.clear();
        ID.clear();
        address.clear();
        salary.clear();
    }

    @FXML
    void Back(ActionEvent event) throws IOException {
        ss.ShowScenes("HomeScreen.fxml");
    }

    @FXML
    void Exit(ActionEvent event) throws IOException {
        SupermarketSystem.stage.close();
    }

}
