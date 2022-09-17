package com.example.proyecto1_ce_musicplayer_carlosfelipe;
/**
 * Sample Skeleton for 'LoginScreen.fxml' Controller Class
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="contra"
    private TextField contra; // Value injected by FXMLLoader

    @FXML // fx:id="login"
    private Button login; // Value injected by FXMLLoader

    @FXML // fx:id="user"
    private TextField user; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert contra != null : "fx:id=\"contra\" was not injected: check your FXML file 'LoginScreen.fxml'.";
        assert login != null : "fx:id=\"login\" was not injected: check your FXML file 'LoginScreen.fxml'.";
        assert user != null : "fx:id=\"user\" was not injected: check your FXML file 'LoginScreen.fxml'.";

    }

}