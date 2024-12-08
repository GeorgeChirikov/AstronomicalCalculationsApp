package org.example.astronomicalcalculationsapp;

import org.example.astronomicalcalculationsapp.model.AstronomicalCalculations;

import java.util.Scanner;

public class CLIApp {
    private int calculationChoice = 1, paramChoice = 1;
    private double orbitAltitude;
    private double orbitAltitudeRadius;
    private double planetMass;
    private double planetRadius;
    private double stdGravParam;
    private double power1;
    private double power2;

    private final Scanner scanner;

    public CLIApp() {
        scanner = new Scanner(System.in);
    }

    public void scannerLoop() {

        System.out.println("Welcome to the Astronomical Calculator!\n");

        while (true) {

            getParamChoice();

            if (paramChoice == 0) {
                break;
            }

            while (true) {

                getCalculationChoice();

                if (calculationChoice == 0) {
                    break;
                }

                planetCase(paramChoice);

                calculationCase(calculationChoice, planetMass);

            }
        }
    }

    private void getCalculationChoice() {
        System.out.println("""
                ---------------------
                Enter calculation:\s
                1. Orbital velocity
                2. Escape velocity
                3. Orbital period
                4. Orbit altitude by period
                5. Dark time
                6. Relay orbit altitude
                7. Max separation
                8. Recommended relay orbit altitude
                0. Exit
                ---------------------
                """);
        System.out.print("Enter your choice: ");

        calculationChoice = scanner.nextInt();
    }

    private void getParamChoice() {
        System.out.println("""
                
                ---------------------
                Enter parameter:\s
                1. Earth
                2. Kerbin
                3. Mun
                4. Minmus
                5. Duna
                6. Eve
                7. Jool
                0. Exit
                ---------------------
                """);
        System.out.print("Enter your choice: ");

        paramChoice = scanner.nextInt();
    }

    private void getOrbitAltitude() {
        System.out.print("""
                
                --------------------------------
                Enter the orbit altitude (m):\s""");

        orbitAltitude = scanner.nextDouble();

        planetCase(paramChoice);
    }

    private void getAntennaPower() {
        System.out.print("""
                
                --------------------------------
                Enter the power of the first relay:\s""");

        power1 = scanner.nextDouble();

        System.out.print("Enter the power of the second relay: ");
        power2 = scanner.nextDouble();
    }

    private void planetCase(int paramChoice) {
        switch (paramChoice) {
            case 1:
                // Earth
                planetMass = 5.972e24;
                planetRadius = 6.371e6;
                stdGravParam = 3.986004418e14;
                orbitAltitudeRadius = planetRadius + orbitAltitude;
                break;
            case 2:
                // Kerbin
                planetMass = 5.2915793e22;
                planetRadius = 600000;
                stdGravParam = 3.5316000e12;
                orbitAltitudeRadius = planetRadius + orbitAltitude;
                break;
            case 3:
                // Mun
                planetMass = 9.7600236e20;
                planetRadius = 200000;
                stdGravParam = 6.5138398e10;
                orbitAltitudeRadius = planetRadius + orbitAltitude;
                break;
            case 4:
                // Minmus
                planetMass = 2.6457897e19;
                planetRadius = 60000;
                stdGravParam = 1.7658000e9;
                orbitAltitudeRadius = planetRadius + orbitAltitude;
                break;
            case 5:
                // Duna
                planetMass = 4.5154812e21;
                planetRadius = 320000;
                stdGravParam = 3.0136321e11;
                orbitAltitudeRadius = planetRadius + orbitAltitude;
                break;
            case 6:
                // Eve
                planetMass = 8.1717302e22;
                planetRadius = 700000;
                stdGravParam = 8.1717302e12;
                orbitAltitudeRadius = planetRadius + orbitAltitude;
                break;
            case 7:
                // Jool
                planetMass = 4.2332632e24;
                planetRadius = 6000000;
                stdGravParam = 2.8252800e14;
                orbitAltitudeRadius = planetRadius + orbitAltitude;
                break;
            case 0:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
    }

    private void calculationCase(int functionChoice, double planetMass) {
        double darkTime;
        switch (functionChoice) {
            case 1:
                getOrbitAltitude();

                double orbitalVelocity = AstronomicalCalculations.calculateOrbitalVelocity(planetMass, orbitAltitudeRadius);
                printResults("Orbital velocity: ", orbitalVelocity, " m/s");
                break;
            case 2:
                getOrbitAltitude();

                double escapeVelocity = AstronomicalCalculations.calculateEscapeVelocity(planetMass, orbitAltitudeRadius);
                printResults("Escape velocity: ", escapeVelocity, " m/s");
                break;
            case 3:
                getOrbitAltitude();

                double orbitalPeriod = AstronomicalCalculations.calculateOrbitalPeriod(orbitAltitudeRadius, stdGravParam);
                //printResults("Orbital period: ", orbitalPeriod, " seconds");
                printResults("Orbital period: ", orbitalPeriod / 60, " minutes");
                printResults("Orbital period: ", orbitalPeriod / 3600, " hours");
                // Day in Kerbal Space Program is 6 hours
                printResults("Orbital period (Kerbal): ", orbitalPeriod / 21600, " days");
                printResults("Orbital period (Earth): ", orbitalPeriod / 86400, " days");
                break;
            case 4:
                System.out.print("Enter period (h): ");
                double period = scanner.nextDouble();
                double orbitAltitude = AstronomicalCalculations.calculateOrbitalByPeriod(period * 3600, stdGravParam) - planetRadius;
                printResults("Orbit altitude: ", orbitAltitude/1000, " km");
                break;
            case 5:
                getOrbitAltitude();

                darkTime = AstronomicalCalculations.calculateDarkTime(this.orbitAltitudeRadius, stdGravParam, planetRadius);
                printResults("Dark time: ", darkTime, " seconds");
                printResults("Dark time: ", darkTime / 60, " minutes");
                break;
            case 6:
                getAntennaPower();

                double relayOrbitAltitude = AstronomicalCalculations.calculateRelayOrbitAltitude(power1, power2, planetRadius);
                printResults("Relay orbit altitude: ", relayOrbitAltitude/1000, " km");
                double recommendedRelayOrbitAltitude = AstronomicalCalculations.calculateRecommendedRelayOrbitAltitude(planetRadius);
                printResults("Recommended relay orbit altitude: ", recommendedRelayOrbitAltitude/1000, " km");
                break;
            case 7:
                getAntennaPower();

                printResults("Max separation: ", AstronomicalCalculations.calculateMaxSeparation(power1, power2)/1000, " km");
                break;
            case 8:
                double recommendedRelayAltitude = AstronomicalCalculations.calculateRecommendedRelayOrbitAltitude(planetRadius);
                printResults("Recommended relay orbit altitude: ", recommendedRelayAltitude/1000, " km");
                break;
            case 0:
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
    }

    private void printResults(String message, double result, String unit) {
        System.out.println("\n---------------------\n" +
                message + result + unit
                + "\n---------------------\n");
    }
}
