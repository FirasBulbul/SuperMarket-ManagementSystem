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
import javafx.scene.control.Button;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class AboutusController implements Initializable {
    
    SupermarketSystem ss = new SupermarketSystem();

    @FXML
    private Button backBtn;

    @FXML
    private WebView FrirasBulbul;

    @FXML
    private WebView MohammadAlkhodary;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        WebEngine firasEngine = FrirasBulbul.getEngine();
        firasEngine.load("https://www.linkedin.com/in/firas-abdulrahman-bulbul-aab92b199/");

        WebEngine mohammadEngine = MohammadAlkhodary.getEngine();
        mohammadEngine.load("https://www.facebook.com/mohammad.alkhudary");
    }

    @FXML
    void Back(ActionEvent event) throws IOException {
        ss.ShowScenes("HomeScreen.fxml");
    }

}
