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
import static javax.xml.ws.RespectBindingFeature.ID;
import static supermarketsystem.MyDatabase.conn;

/**
 *
 * @author pc
 */
public class ProductController implements Initializable {

    SupermarketSystem ss = new SupermarketSystem();
    ObservableList<Product> products = FXCollections.observableArrayList();
    Product product = new Product();

    @FXML
    private TableColumn<Product, String> catIDCol;

    @FXML
    private TextField name;

    @FXML
    private TableColumn<Product, String> nameCol;

    @FXML
    private TextField price;

    @FXML
    private TableColumn<Product, Integer> priceCol;

    @FXML
    private TableView<Product> productTable;

    @FXML
    private TableColumn<Product, Integer> quantCol;

    @FXML
    private TextField quantity;

    @FXML
    private TableColumn<Product, Integer> serCol;

    @FXML
    private TextField serNo;

    public void RefreshTable() throws SQLException, Exception {
        nameCol.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        serCol.setCellValueFactory(new PropertyValueFactory<Product, Integer>("serNo"));
        quantCol.setCellValueFactory(new PropertyValueFactory<Product, Integer>("quantity"));
        priceCol.setCellValueFactory(new PropertyValueFactory<Product, Integer>("price"));
        products = MyDatabase.getAllProducts();
        productTable.setItems(products);
    }

    public void FieldsData() {
        productTable.getSelectionModel().selectedItemProperty().addListener(listener -> {
            product = productTable.getSelectionModel().getSelectedItem();
            name.setText(product.getName());
            serNo.setText(String.valueOf(product.getSerNo()));
            quantity.setText(String.valueOf(product.getQuantity()));
            price.setText(String.valueOf(product.getPrice()));
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            RefreshTable();
            FieldsData();
        } catch (Exception ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void add(ActionEvent event) throws SQLException, ClassNotFoundException, Exception {
        MyDatabase.getConnection();
        PreparedStatement statement = conn.prepareStatement(
                "Insert into products values(?, ?, ?, ?)");
        statement.setString(1, name.getText());
        statement.setInt(2, Integer.valueOf(serNo.getText()));
        statement.setInt(3, Integer.valueOf(quantity.getText()));
        statement.setInt(4, Integer.valueOf(price.getText()));
        statement.executeUpdate();
        RefreshTable();
        name.clear();
        serNo.clear();
        quantity.clear();
        price.clear();
    }

    @FXML
    void delete(ActionEvent event) throws SQLException, ClassNotFoundException, Exception {
        MyDatabase.getConnection();
        PreparedStatement statement = conn.prepareStatement(
                "DELETE FROM products WHERE ID = ?");
        statement.setInt(1, Integer.valueOf(serNo.getText()));
        statement.executeUpdate();
        RefreshTable();
    }

    @FXML
    void printReport(ActionEvent event) {

    }

    @FXML
    void update(ActionEvent event) throws SQLException, ClassNotFoundException, Exception {
        MyDatabase.getConnection();
        PreparedStatement statement = conn.prepareStatement(
                "update products set name=?, quantity=?, price=? where serNo=?");
        statement.setString(1, name.getText());
        statement.setInt(2, Integer.valueOf(quantity.getText()));
        statement.setInt(3, Integer.valueOf(price.getText()));
        statement.setInt(4, Integer.valueOf(serNo.getText()));
        statement.executeUpdate();
        RefreshTable();
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
