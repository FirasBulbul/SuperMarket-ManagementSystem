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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import static supermarketsystem.MyDatabase.conn;

/**
 *
 * @author pc
 */
public class CustomerController implements Initializable {

    SupermarketSystem ss = new SupermarketSystem();
    ObservableList<Customer> customers = FXCollections.observableArrayList();

    @FXML
    private TableView<Customer> CustomerTable;

    @FXML
    private TextField ID;

    @FXML
    private TableColumn<Customer, Integer> IDCol;

    @FXML
    private TextField address;

    @FXML
    private TextField balance;

    @FXML
    private TableColumn<Customer, Integer> balanceCol;

    @FXML
    private TableColumn<Customer, Integer> cardCol;

    @FXML
    private TextField cardID;

    @FXML
    private TableColumn<Customer, String> cityCol;

    @FXML
    private TextField name;

    @FXML
    private TableColumn<Customer, String> nameCol;

    public void RefreshTable() throws SQLException, Exception {
        nameCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
        IDCol.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("id"));
        cityCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("address"));
        cardCol.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("cardID"));
        balanceCol.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("balance"));
        customers = MyDatabase.getAllCustomers();
        CustomerTable.setItems(customers);
    }

    public void FieldsData() {
        CustomerTable.getSelectionModel().selectedItemProperty().addListener(listener -> {
            Customer customer = CustomerTable.getSelectionModel().getSelectedItem();
            name.setText(customer.getName());
            ID.setText(String.valueOf(customer.getId()));
            address.setText(customer.getAddress());
            cardID.setText(String.valueOf(customer.getCardID()));
            balance.setText(String.valueOf(customer.getBalance()));
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            RefreshTable();
            FieldsData();
        } catch (Exception ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void add(ActionEvent event) throws SQLException, ClassNotFoundException, Exception {
        MyDatabase.getConnection();
        PreparedStatement statement = conn.prepareStatement(
                "Insert into customers values(?, ?, ?, ?, ?)");
        statement.setString(1, name.getText());
        statement.setInt(2, Integer.valueOf(ID.getText()));
        statement.setString(3, address.getText());
        statement.setInt(5, Integer.valueOf(cardID.getText()));
        statement.setInt(5, Integer.valueOf(balance.getText()));
        statement.executeUpdate();
        RefreshTable();
        name.clear();
        ID.clear();
        address.clear();
        cardID.clear();
        balance.clear();
    }

    @FXML
    void delete(ActionEvent event) throws SQLException, ClassNotFoundException, Exception {
        MyDatabase.getConnection();
        PreparedStatement statement = conn.prepareStatement(
                "DELETE FROM customers WHERE ID = ?");
        statement.setInt(1, Integer.valueOf(ID.getText()));
        statement.executeUpdate();
        RefreshTable();
    }

    @FXML
    void newInvoice(ActionEvent event) throws IOException {
        ss.ShowScenes("InvoiceScreen.fxml");
    }

    @FXML
    void printReport(ActionEvent event) {

    }

    @FXML
    void update(ActionEvent event) throws SQLException, ClassNotFoundException, Exception {
        MyDatabase.getConnection();
        PreparedStatement statement = conn.prepareStatement(
                "update customers set name=?, address=?, cardID=?, balance=? where id=?");
        statement.setString(1, name.getText());
        statement.setString(1, address.getText());
        statement.setInt(2, Integer.valueOf(cardID.getText()));
        statement.setInt(3, Integer.valueOf(balance.getText()));
        statement.setInt(4, Integer.valueOf(ID.getText()));
        statement.executeUpdate();
        RefreshTable();
        name.clear();
        ID.clear();
        address.clear();
        cardID.clear();
        balance.clear();
    }

    @FXML
    void Back(ActionEvent event) throws IOException {
        ss.ShowScenes("HomeScreen.fxml");
    }

    @FXML
    void Exit(ActionEvent event) {
        SupermarketSystem.stage.close();
    }

}
