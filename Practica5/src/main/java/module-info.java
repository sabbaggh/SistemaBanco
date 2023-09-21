module com.example.practica5 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.practica5 to javafx.fxml;
    exports com.example.practica5;
}