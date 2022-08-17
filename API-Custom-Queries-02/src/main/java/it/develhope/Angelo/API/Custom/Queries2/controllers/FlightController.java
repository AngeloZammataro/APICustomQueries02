package it.develhope.Angelo.API.Custom.Queries2.controllers;

import it.develhope.Angelo.API.Custom.Queries2.entities.Flight;
import it.develhope.Angelo.API.Custom.Queries2.entities.FlightStatus;
import it.develhope.Angelo.API.Custom.Queries2.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/flights") //mapped on flights
public class FlightController {

    //all the string values are randomly generated (using random.ints())
    public String generateRandomValueForFlight() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

    @Autowired
    private FlightRepository flightRepository;


    //provisioning of n flights (where n is an optional query param; if absent, n=100)
    @GetMapping("/provisioning")
    public void provisionFlights(@RequestParam(required = false) Integer n){
        if(n == null) n=100;
        List<Flight> newFlights = new ArrayList<>();
        for(int i = 0; i < n; i++){
            Flight flight = new Flight();
            flight.setDescription(generateRandomValueForFlight());
            flight.setFromAirport(generateRandomValueForFlight());
            flight.setToAirport(generateRandomValueForFlight());
            flight.setFlightStatus(FlightStatus.randomFlightStatus());
            newFlights.add(flight);
        }
        flightRepository.saveAll(newFlights);
    }

    //getting the flights using pagination
    @GetMapping("")
    public Page<Flight> getAllFlights(@RequestParam int page, @RequestParam int size){
        return flightRepository.findAll(PageRequest.of(page, size, Sort.by("fromAirport").ascending()));
    }

    @GetMapping("/status")
    public List<Flight> getAllFlightsByStatus(){
        return flightRepository.findByFlightStatus(FlightStatus.ONTIME);
    }

    @GetMapping("/custom")
    public List<Flight> getCustomFlight(@RequestParam FlightStatus p1, @RequestParam FlightStatus p2){
        return flightRepository.getCustomFlight(p1, p2);
    }
}

