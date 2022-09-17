module com.example.proyecto1_ce_musicplayer_carlosfelipe {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.proyecto1_ce_musicplayer_carlosfelipe to javafx.fxml;
    exports com.example.proyecto1_ce_musicplayer_carlosfelipe;
}