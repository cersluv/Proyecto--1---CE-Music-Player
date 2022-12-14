package com.example.Controller;
/**
 * Sample Skeleton for 'LoginScreen.fxml' Controller Class
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * Controlador de la pantalla de loggeo, acá se ponen los datos del usuario y se inicia sesión con su cuenta.
 * También lo envía a la pantalla de registro para agregar personas a la "base de datos"
 */
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
    /**
     * Finalizar el programa, cerrandolo.
     */
    @FXML
    private void btncerrar(ActionEvent event) {
        Node source = null;
        source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    /**
     * Autenticador de usuarios, utiliza un .txt para revisar que esté registrado.
     */
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
    /**
     * Cierra la pantalla de loggeo, y envía al usuario a hacerse una cuenta.
     */
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
    /**
     * Método el cual define en cual stage se está trabajando
     */
    public void setStage(Stage primaryStage){
        stage = primaryStage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
    }
    /**
     * Método el cual muestra la stage actual
     */
    public void show() {
        stage.show();
    }
}