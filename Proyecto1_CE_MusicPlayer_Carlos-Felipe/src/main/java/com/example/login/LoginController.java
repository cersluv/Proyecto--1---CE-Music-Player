package com.example.login;
/**
 * Sample Skeleton for 'LoginScreen.fxml' Controller Class
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class LoginController implements Initializable {

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
    @FXML
    private void eventKey(KeyEvent event) {

    }
    @FXML
    private void eventAction(ActionEvent event) {
        Object evt = event.getSource();


        }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert contra != null : "fx:id=\"contra\" was not injected: check your FXML file 'LoginScreen.fxml'.";
        assert login != null : "fx:id=\"login\" was not injected: check your FXML file 'LoginScreen.fxml'.";
        assert user != null : "fx:id=\"user\" was not injected: check your FXML file 'LoginScreen.fxml'.";

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

    }
}