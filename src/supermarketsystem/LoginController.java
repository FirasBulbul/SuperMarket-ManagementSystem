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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
//import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import static supermarketsystem.MyDatabase.conn;

/**
 *
 * @author pc
 */
public class LoginController implements Initializable {

    SupermarketSystem ss = new SupermarketSystem();
//    EmployeeController employeeController = new EmployeeController();

    @FXML
    private TextField username;

    @FXML
    private Label validationLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void signIn(ActionEvent event) throws Exception {        MyDatabase.getConnection();
        PreparedStatement statement = conn.prepareStatement("select id from employees where type = 'Admin' ");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int Username = resultSet.getInt("id");
            if (Integer.valueOf(username.getText()) == Username) {
                validationLabel.setStyle("-fx-text-fill: green;");
                validationLabel.setText("Login Success");
                ss.ShowScenes("HomeScreen.fxml");
                break;
            } else {
                validationLabel.setText("Login Failed");
            }
        }
    }

    @FXML
    void signUp(ActionEvent event) throws IOException {
        ss.ShowScenes("EmployeeScreen");
//        employeeController.backBtn.setVisible(false);
    }

}
