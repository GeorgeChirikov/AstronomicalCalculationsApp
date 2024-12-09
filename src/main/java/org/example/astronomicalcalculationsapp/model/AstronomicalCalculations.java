package org.example.astronomicalcalculationsapp.model;

public class AstronomicalCalculations {
    private static final double G = 6.67430e-11;

    private AstronomicalCalculations() {
        throw new IllegalStateException("Utility class");
    }

    public static double calculateOrbitalVelocity(double mass, double radius) {
        return Math.sqrt(G * mass / radius);
    }

    public static double calculateEscapeVelocity(double mass, double radius) {
        return Math.sqrt(2 * G * mass / radius);
    }

    // Might need to be changed
    public static double calculateOrbitalPeriod(double radius, double stdGravParam) {
        return 2 * Math.PI * Math.sqrt(Math.pow(radius, 3) / stdGravParam);
    }

    public static double calculateOrbitalByPeriod(double period, double stdGravParam) {
        return Math.pow((Math.pow(period, 2) * stdGravParam) / (4 * Math.pow(Math.PI, 2)), 1.0 / 3);
    }

    public static double calculateDarkTime(double orbitAltitudeRadius, double stdGravParam, double planetRadius) {
        return 2 * Math.sqrt(Math.pow(
                orbitAltitudeRadius, 3)
                /
                stdGravParam)
                *
                Math.asin(planetRadius
                        /
                        orbitAltitudeRadius);
    }

    // In progress
    public static double calculateRelayOrbitAltitude(double power1, double power2, double planetRadius) {
        return calculateMaxSeparation(power1, power2) - planetRadius;
    }

    // In progress
    public static double calculateMaxRange(double power1, double power2) {
        return Math.sqrt(power1 * power2);
    }

    // In progress
    public static double calculateMaxSeparation(double power1, double power2) {
        double x = 0.71;
        double range = calculateMaxRange(power1, power2);
        return range * (1 - x) * 1000;
    }

    public static double calculateRecommendedRelayOrbitAltitude(double planetRadius) {
        return planetRadius * 1.25;
    }
}
