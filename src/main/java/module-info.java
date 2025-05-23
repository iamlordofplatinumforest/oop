module com.example.myversion {
    requires static lombok;
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;

    uses com.example.myversion.Models.Figures.ShapePlugin;

    opens com.example.myversion to javafx.fxml;
    exports com.example.myversion;
    opens com.example.myversion.Models.Figures to com.fasterxml.jackson.databind;
    opens com.example.myversion.Models.Utils to com.fasterxml.jackson.databind;
    exports com.example.myversion.Models.Figures;
    exports com.example.myversion.Models.Utils;
}