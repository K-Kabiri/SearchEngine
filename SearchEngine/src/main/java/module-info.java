module com.example.searchengine {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.searchengine to javafx.fxml;
    exports com.example.searchengine;
    exports controller;
    opens controller to javafx.fxml;
}