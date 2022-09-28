package com.example.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
/**
 * Controlador para agregar gente a la base de datos [.txt]
 * Crea objetos del tipo usuario.
 */
public class RegisterController implements Initializable {
    private Stage stage;
    private LoginController controlladorLogin;

    @FXML
    private TextField contraseñas;

    @FXML
    private TextField users;
    private String provincia = "";
    @FXML
    private TextField correo;


    public void init(Stage stage, LoginController loginController) {
        this.controlladorLogin = loginController;
        this.stage = stage;
    }
    public String province1(ActionEvent event){
        provincia = "San José";
        return provincia;
    }
    public String province2(ActionEvent event){
        provincia = "Alajuela";
        return provincia;
    }
    public String province3(ActionEvent event){
        provincia = "Cartago";
        return provincia;
    }
    public String province4(ActionEvent event){
        provincia = "Heredia";
        return provincia;
    }
    public String province5(ActionEvent event){
        provincia = "Guanacaste";
        return provincia;
    }
    public String province6(ActionEvent event){
        provincia = "Puntarenas";
        return provincia;
    }
    public String province7(ActionEvent event){
        provincia = "Limón";
        return provincia;
    }
    /**
     * Método el cual crea objetos del tipo usuario y los agrega a la base de datos.
     */
    public void escribirtxt(ActionEvent event) throws IOException {
        String user = users.getText();
        String pass = contraseñas.getText();
        String mail = correo.getText();
        try {
            FileWriter w = new FileWriter("C:\\Users\\Yoshant\\Desktop\\Users.txt", true);
            w.write("\n"+user+","+pass+","+provincia+","+mail);
            w.close();
            File directorio = new File(String.valueOf(users.getText()));
            if (!directorio.exists()) {
                if (directorio.mkdirs()) {
                    System.out.println("Directorio creado");
                }
                }else {
                    System.out.println("Error al crear directorio");
            }

        }catch(IOException e){
            e.printStackTrace(System.out);
        }
        controlladorLogin.show();
        stage.close();
        }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
