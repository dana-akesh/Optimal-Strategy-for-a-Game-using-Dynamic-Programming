module com.example.project1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.project1 to javafx.fxml;
    exports com.example.project1;
    exports com.example.project1.fx;
    opens com.example.project1.fx to javafx.fxml;
    exports com.example.project1.logic;
    opens com.example.project1.logic to javafx.fxml;
    exports com.example.project1.fx.alerts;
    opens com.example.project1.fx.alerts to javafx.fxml;
}