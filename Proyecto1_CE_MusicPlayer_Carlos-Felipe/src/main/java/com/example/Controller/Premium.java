package com.example.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Premium implements Initializable {
    private MP3Controller controlladorMP3;
    private Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void init(Stage stage, MP3Controller mp3Controller) {
        this.controlladorMP3 = mp3Controller;
        this.stage = stage;
        stage.getIcons().add(new Image("C:\\Users\\Yoshant\\Desktop\\Proyecto--1---CE-Music-Player\\Proyecto1_CE_MusicPlayer_Carlos-Felipe\\src\\Imagenes\\icono.png"));

    }
    public void cerrar(ActionEvent event){
        controlladorMP3.show();
        stage.close();
    }


}
