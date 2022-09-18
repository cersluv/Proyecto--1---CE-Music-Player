module com.example.proyecto1_ce_musicplayer_carlosfelipe {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.login to javafx.fxml;
    opens com.example.mp3 to javafx.fxml;
    exports com.example.login;

}