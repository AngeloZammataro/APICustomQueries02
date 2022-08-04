package it.develhope.Angelo.API.Custom.Queries2.entities;

import java.util.Random;

public enum FlightStatus {
    ON_TIME,
    DELAYED,
    CANCELLED;

    //the status is generated randomly
    private static final Random PRNG = new Random();
    public static FlightStatus randomFlightStatus()  {
        FlightStatus[] flightStatuses = values();
        return flightStatuses[PRNG.nextInt(flightStatuses.length)];
    }
}
