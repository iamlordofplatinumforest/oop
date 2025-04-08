module com.example.myversion {
    requires static lombok;
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.myversion to javafx.fxml;
    exports com.example.myversion;
}