package com.example.Controller;
/**
 * Sample Skeleton for 'LoginScreen.fxml' Controller Class
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;


public class LoginController implements Initializable {

    private Stage stage;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML
    private TextField contra;

    @FXML
    private Button login;

    @FXML
    private TextField user;
    @FXML
    private Label malo;

    @FXML
    private void eventKey(KeyEvent event) {

    }
    @FXML
    private void eventAction(ActionEvent event) {
        Object evt = event.getSource();

        }
    @FXML private javafx.scene.control.Button btncerrar;

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert contra != null : "fx:id=\"contra\" was not injected: check your FXML file 'LoginScreen.fxml'.";
        assert login != null : "fx:id=\"login\" was not injected: check your FXML file 'LoginScreen.fxml'.";
        assert user != null : "fx:id=\"user\" was not injected: check your FXML file 'LoginScreen.fxml'.";

    }
    @FXML
    private void btncerrar(ActionEvent event) {
        Node source = null;
        source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void obtener(ActionEvent event)  {
        String path = "C:\\Users\\Yoshant\\Desktop\\Users.txt", usuario = user.getText(), contraseña = contra.getText();
        File file = new File(path);
        try {
            Scanner inputBuffer = new Scanner((file));
            while (inputBuffer.hasNext()){
                String line = inputBuffer.nextLine();
                String[] lista = line.split(",");
                if (lista[0].equals(usuario) && lista[1].equals(contraseña)){
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Controller/MP3Screen.fxml"));
                    Parent root = loader.load();
                    MP3Controller controller = loader.getController();
                    Scene scene = new Scene (root);
                    Stage stage = new Stage ();
                    stage.setScene(scene);
                    controller.init(stage, this, lista);
                    stage.show();
                    this.stage.close();
                }
                else{
                    malo.setVisible(true);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    @FXML
    public void registrar(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Register/Registro.fxml"));
        Parent root = loader.load();
        RegisterController controller = loader.getController();
        Scene scene = new Scene (root);
        Stage stage = new Stage ();
        stage.setScene(scene);
        controller.init(stage, this);
        stage.show();
        this.stage.close();
    }
    public void setStage(Stage primaryStage){
        stage = primaryStage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
    }

    public void show() {
        stage.show();
    }
}