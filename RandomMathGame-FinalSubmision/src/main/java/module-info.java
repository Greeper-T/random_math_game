module com.example.general_template {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.general_template to javafx.fxml;
    exports com.example.general_template;
}