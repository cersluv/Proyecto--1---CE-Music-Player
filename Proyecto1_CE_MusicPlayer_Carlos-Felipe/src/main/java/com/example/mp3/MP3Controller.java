package com.example.mp3;


import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class MP3Controller {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="explote"
    private Button explote; // Value injected by FXMLLoader

    @FXML // fx:id="logout"
    private Button logout; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert explote != null : "fx:id=\"explote\" was not injected: check your FXML file 'MP3Screen.fxml'.";
        assert logout != null : "fx:id=\"logout\" was not injected: check your FXML file 'MP3Screen.fxml'.";

    }

}