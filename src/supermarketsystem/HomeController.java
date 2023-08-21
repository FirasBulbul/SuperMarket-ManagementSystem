/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarketsystem;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 *
 * @author pc
 */
public class HomeController implements Initializable {

    SupermarketSystem ss = new SupermarketSystem();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    @FXML
    void Employees(ActionEvent event) throws IOException {
        ss.ShowScenes("EmployeeScreen.fxml");
    }

    @FXML
    void aboutus(ActionEvent event) throws IOException {
        ss.ShowScenes("aboutUsScreen.fxml");
    }

    @FXML
    void customers(ActionEvent event) throws IOException {
        ss.ShowScenes("CustomerScreen.fxml");
    }

    @FXML
    void products(ActionEvent event) throws IOException {
        ss.ShowScenes("ProductsScreen.fxml");
     }

    @FXML
    void logOut(ActionEvent event) throws IOException {
        ss.ShowScenes("LoginScreen.fxml");
    }

}
