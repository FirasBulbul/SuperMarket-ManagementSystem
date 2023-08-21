/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarketsystem;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Observable;
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
public class InvoiceController implements Initializable {

    SupermarketSystem ss = new SupermarketSystem();
    ObservableList<Invoice> invoices = FXCollections.observableArrayList();
    Invoice invoice = new Invoice();

    @FXML
    private TableView<Invoice> InvoiceTable;

    @FXML
    private TextField custName;

    @FXML
    private TableColumn<Invoice, String> custNameCol;

    @FXML
    private TableColumn<Invoice, Date> dateCol;

    @FXML
    private TextField id;

    @FXML
    private TableColumn<Invoice, Integer> idCol;

    @FXML
    private TableColumn<Invoice, Integer> netCol;

    @FXML
    private TableColumn<Invoice, Integer> totalCol;

    public void RefreshTable() throws SQLException, Exception {
        idCol.setCellValueFactory(new PropertyValueFactory<Invoice, Integer>("id"));
        custNameCol.setCellValueFactory(new PropertyValueFactory<Invoice, String>("customerName"));
        dateCol.setCellValueFactory(new PropertyValueFactory<Invoice, Date>("date"));
        totalCol.setCellValueFactory(new PropertyValueFactory<Invoice, Integer>("total"));
        netCol.setCellValueFactory(new PropertyValueFactory<Invoice, Integer>("net"));
        invoices = MyDatabase.getAllInvoices();
        InvoiceTable.setItems(invoices);
    }

    public void FieldsData() {
        InvoiceTable.getSelectionModel().selectedItemProperty().addListener(listener -> {
            invoice = InvoiceTable.getSelectionModel().getSelectedItem();
            id.setText(String.valueOf(invoice.getId()));
            custName.setText(invoice.getCustName());
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            RefreshTable();
            FieldsData();
        } catch (Exception ex) {
            Logger.getLogger(InvoiceController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void add(ActionEvent event) throws SQLException, ClassNotFoundException, Exception {
        MyDatabase.getConnection();
        PreparedStatement statement = conn.prepareStatement(
                "Insert into invoices (id, customername) values(?, ?)");
        statement.setInt(1, Integer.valueOf(id.getText()));
        statement.setString(2, custName.getText());
        statement.executeUpdate();
        RefreshTable();
        id.clear();
        custName.clear();
    }

    @FXML
    void delete(ActionEvent event) throws SQLException, ClassNotFoundException, Exception {
        MyDatabase.getConnection();
        PreparedStatement statement = conn.prepareStatement(
                "DELETE FROM invoices WHERE id = ?");
        statement.setInt(1, Integer.valueOf(id.getText()));
        statement.executeUpdate();
        RefreshTable();
//        id.clear(); custName.clear();
    }

    @FXML
    void addProducts(ActionEvent event) throws IOException {
        ss.ShowScenes("OrderScreen.fxml");
    }

    @FXML
    void update(ActionEvent event) throws SQLException, ClassNotFoundException, Exception {
        MyDatabase.getConnection();
        PreparedStatement statement = conn.prepareStatement(
                "update invoices set customerName=? where id=?");
        statement.setString(1, custName.getText());
        statement.setInt(2, Integer.valueOf(id.getText()));
        statement.executeUpdate();
        RefreshTable();
        id.clear();
        custName.clear();
    }

    @FXML
    void Back(ActionEvent event) throws IOException {
        ss.ShowScenes("CustomerScreen.fxml");
    }

    @FXML
    void Exit(ActionEvent event) {
        SupermarketSystem.stage.close();
    }

}
