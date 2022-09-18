package com.example.Controller;


import com.example.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MP3Controller {
    private Stage stage;
    private LoginController controlladorLogin;


    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="explote"
    private Button cerrar2; // Value injected by FXMLLoader

    @FXML // fx:id="logout"
    private Button logout; // Value injected by FXMLLoader
    @FXML
    private Label user1;
    @FXML
    private Label correo1;
    @FXML
    private Label prov1;

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert cerrar2 != null : "fx:id=\"cerrar2\" was not injected: check your FXML file 'MP3Screen.fxml'.";
        assert logout != null : "fx:id=\"logout\" was not injected: check your FXML file 'MP3Screen.fxml'.";

    }

    public void btncerrar2(ActionEvent event) {
        stage.close();
    }

    public void regresar(ActionEvent event) {
        controlladorLogin.show();
        stage.close();
    }

    public void init(Stage stage, LoginController loginController, String[] list) {
        user1.setText(list[0]);
        correo1.setText(list[3]);
        prov1.setText(list[2]);
        this.controlladorLogin = loginController;
        this.stage = stage;
            }



}