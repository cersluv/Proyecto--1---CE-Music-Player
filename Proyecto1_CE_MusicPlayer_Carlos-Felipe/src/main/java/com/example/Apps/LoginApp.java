package com.example.Apps;

import com.example.Controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginApp extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginApp.class.getResource("LoginScreen.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        LoginController controller = fxmlLoader.getController();
        controller.setStage(primaryStage);
        primaryStage.getIcons().add(new Image("C:\\Users\\Yoshant\\Desktop\\Proyecto--1---CE-Music-Player\\Proyecto1_CE_MusicPlayer_Carlos-Felipe\\src\\Imagenes\\icono.png"));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}