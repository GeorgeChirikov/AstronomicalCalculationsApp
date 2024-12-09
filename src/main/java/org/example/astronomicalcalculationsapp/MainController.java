package org.example.astronomicalcalculationsapp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MainController {
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Label label3;
    @FXML
    private Label label4;
    @FXML
    private TextField orbitAltitudeField;
    @FXML
    private TextField relayAntennaPowerField1;
    @FXML
    private TextField relayAntennaPowerField2;

    public void initialize() {
        label1.setText("Orbital velocity (m/s):");
        label2.setText("Escape velocity (m/s):");
        label3.setText("Orbital period (s):");
        label4.setText("Recommended relay orbit altitude (m):");
    }
}