module com.example.myversion {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.myversion to javafx.fxml;
    exports com.example.myversion;
}