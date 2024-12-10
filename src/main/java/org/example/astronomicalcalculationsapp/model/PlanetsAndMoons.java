package org.example.astronomicalcalculationsapp.model;

public class PlanetsAndMoons {
    private double planetMass;
    private double planetRadius;
    private double stdGravParam;

    public void planetCase(String planetName) {
        switch (planetName) {
            case "Earth":
                // Earth
                planetMass = 5.972e24;
                planetRadius = 6.371e6;
                stdGravParam = 3.986004418e14;
                break;
            case "Kerbin":
                // Kerbin
                planetMass = 5.2915793e22;
                planetRadius = 600000;
                stdGravParam = 3.5316000e12;
                break;
            case "Mun":
                // Mun
                planetMass = 9.7600236e20;
                planetRadius = 200000;
                stdGravParam = 6.5138398e10;
                break;
            case "Minmus":
                // Minmus
                planetMass = 2.6457897e19;
                planetRadius = 60000;
                stdGravParam = 1.7658000e9;
                break;
            case "Duna":
                // Duna
                planetMass = 4.5154812e21;
                planetRadius = 320000;
                stdGravParam = 3.0136321e11;
                break;
            case "Eve":
                // Eve
                planetMass = 8.1717302e22;
                planetRadius = 700000;
                stdGravParam = 8.1717302e12;
                break;
            case "Jool":
                // Jool
                planetMass = 4.2332632e24;
                planetRadius = 6000000;
                stdGravParam = 2.8252800e14;
                break;
            default:
                System.out.println("Invalid choice");
                // Earth
                planetMass = 5.972e24;
                planetRadius = 6.371e6;
                stdGravParam = 3.986004418e14;
                break;
        }
    }

    public double getPlanetMass() {
        return planetMass;
    }

    public double getPlanetRadius() {
        return planetRadius;
    }

    public double getStdGravParam() {
        return stdGravParam;
    }
}
