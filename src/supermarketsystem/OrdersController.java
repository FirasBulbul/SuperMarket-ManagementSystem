/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarketsystem;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import static supermarketsystem.MyDatabase.conn;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class OrdersController implements Initializable {

    SupermarketSystem ss = new SupermarketSystem();

    @FXML
    private ComboBox<String> productInvoice;

    @FXML
    private ComboBox<String> custInvoice;

    @FXML
    private TextField quantity;

    ObservableList<String> prodList = FXCollections.observableArrayList();
    ObservableList<String> custList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            prodCombo();
            custCombo();
        } catch (Exception ex) {
            Logger.getLogger(OrdersController.class.getName()).log(Level.SEVERE, null, ex);
        }
        productInvoice.setItems(prodList);
        custInvoice.setItems(custList);
    }

    @FXML
    void save(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {
        MyDatabase.getConnection();
        PreparedStatement statement = conn.prepareStatement(
                "Insert into orders values(?, ?, ?)");
        statement.setString(1, productInvoice.getSelectionModel().getSelectedItem());
        statement.setString(2, custInvoice.getSelectionModel().getSelectedItem());
        statement.setInt(3, Integer.valueOf(quantity.getText()));
        statement.executeUpdate();
        productInvoice.getSelectionModel().clearSelection();
        custInvoice.getSelectionModel().clearSelection();
        quantity.clear();
        ss.ShowScenes("InvoiceScreen.fxml");
    }

    public void prodCombo() throws SQLException, ClassNotFoundException {
        MyDatabase.getConnection();
        PreparedStatement statement = conn.prepareStatement(
                "Select name from products");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            prodList.add(name);
        }
    }

    public void custCombo() throws SQLException, ClassNotFoundException {
        MyDatabase.getConnection();
        PreparedStatement statement = conn.prepareStatement(
                "Select customerName from invoices");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            String name = resultSet.getString("customerName");
            custList.add(name);
        }
    }
}
