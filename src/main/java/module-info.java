module org.example.astronomicalcalculationsapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.astronomicalcalculationsapp to javafx.fxml;
    exports org.example.astronomicalcalculationsapp;
}