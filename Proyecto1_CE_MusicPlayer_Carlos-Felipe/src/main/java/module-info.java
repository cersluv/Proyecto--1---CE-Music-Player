module com.example.proyecto1_ce_musicplayer_carlosfelipe {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.Apps to javafx.fxml;
    exports com.example.Apps;
    exports com.example.Controller;
    opens com.example.Controller to javafx.fxml;

}