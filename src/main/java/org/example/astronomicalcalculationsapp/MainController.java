package org.example.astronomicalcalculationsapp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.astronomicalcalculationsapp.model.AstronomicalCalculations;
import org.example.astronomicalcalculationsapp.model.PlanetsAndMoons;

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
    private Label label5;
    @FXML
    private Label label6;
    @FXML
    private Label label7;
    @FXML
    private Label label8;
    @FXML
    private Button calculateButton;
    @FXML
    private ChoiceBox<String> planetDropdown;
    @FXML
    private TextField orbitAltitudeField;
    @FXML
    private TextField relayAntennaPowerField1;
    @FXML
    private TextField relayAntennaPowerField2;
    @FXML
    private TextField orbitalPeriodField;

    private double orbitAltitude;
    private double completeOrbitAltitude;
    private double relayAntennaPower1;
    private double relayAntennaPower2;
    private double periodTime;
    private String planetName;

    private PlanetsAndMoons planetsAndMoons;

    public void initialize() {
        orbitAltitudeField.setText("120");
        relayAntennaPowerField1.setText("500");
        relayAntennaPowerField2.setText("5000");
        orbitalPeriodField.setText("30");

        label1.setText("Orbital velocity (m/s):");
        label2.setText("Escape velocity (m/s):");
        label3.setText("Orbital period (s):");
        label4.setText("Orbit altitude (m):");
        label5.setText("Dark time (s):");
        label6.setText("Relay orbit altitude (m):");
        label7.setText("Max separation (m):");
        label8.setText("Recommended relay orbit altitude (m):");
        calculateButton.setText("Calculate");
        planetsAndMoons = new PlanetsAndMoons();
    }

    @FXML
    protected void onCalculateButtonClick() {

        if (planetDropdown.getValue() == null) {
            planetName = "Earth";
        } else {
            planetName = planetDropdown.getValue();
        }

        if (orbitAltitudeField.getText().isEmpty()) {
            orbitAltitude = 120;
        } else {
            orbitAltitude = 1000 * Double.parseDouble(orbitAltitudeField.getText());
        }

        if (relayAntennaPowerField1.getText().isEmpty()) {
            relayAntennaPower1 = 500;
        } else {
            relayAntennaPower1 = Double.parseDouble(relayAntennaPowerField1.getText());
        }

        if (relayAntennaPowerField2.getText().isEmpty()) {
            relayAntennaPower2 = 5000;
        } else {
            relayAntennaPower2 = Double.parseDouble(relayAntennaPowerField2.getText());
        }

        if (orbitalPeriodField.getText().isEmpty()) {
            periodTime = 30;
        } else {
            periodTime = Double.parseDouble(orbitalPeriodField.getText());
        }

        planetsAndMoons.planetCase(planetName);
        completeOrbitAltitude = orbitAltitude + planetsAndMoons.getPlanetRadius();

        double orbitalVelocity = AstronomicalCalculations.calculateOrbitalVelocity(planetsAndMoons.getPlanetMass(), completeOrbitAltitude);
        double escapeVelocity = AstronomicalCalculations.calculateEscapeVelocity(planetsAndMoons.getPlanetMass(), completeOrbitAltitude);
        double orbitalPeriod = AstronomicalCalculations.calculateOrbitalPeriod(completeOrbitAltitude, planetsAndMoons.getStdGravParam());
        double calculatedOrbitAltitude = AstronomicalCalculations.calculateOrbitalByPeriod(periodTime, planetsAndMoons.getStdGravParam()) - planetsAndMoons.getPlanetRadius();
        double darkTime = AstronomicalCalculations.calculateDarkTime(completeOrbitAltitude, planetsAndMoons.getStdGravParam(), planetsAndMoons.getPlanetRadius());
        double relayOrbitAltitude = AstronomicalCalculations.calculateRelayOrbitAltitude(relayAntennaPower1, relayAntennaPower2, planetsAndMoons.getPlanetRadius());
        double maxSeparation = AstronomicalCalculations.calculateMaxSeparation(relayAntennaPower1, relayAntennaPower2);
        double recommendedRelayOrbitAltitude = AstronomicalCalculations.calculateRecommendedRelayOrbitAltitude(planetsAndMoons.getPlanetRadius());


        label1.setText("Orbital velocity (m/s): " + orbitalVelocity);
        label2.setText("Escape velocity (m/s): " + escapeVelocity);
        label3.setText("Orbital period (s): " + orbitalPeriod);
        label4.setText("Orbit altitude (m): " + calculatedOrbitAltitude);
        label5.setText("Dark time (s): " + darkTime);
        label6.setText("Relay orbit altitude (m): " + relayOrbitAltitude);
        label7.setText("Max separation (m): " + maxSeparation);
        label8.setText("Recommended relay orbit altitude (m): " + recommendedRelayOrbitAltitude);

    }

    @FXML
    protected void onPlanetSelected() {
        planetName = planetDropdown.getValue();
        planetsAndMoons.planetCase(planetName);
    }
}