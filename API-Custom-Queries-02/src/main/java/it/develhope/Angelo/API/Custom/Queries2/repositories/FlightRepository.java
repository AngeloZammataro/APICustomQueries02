package it.develhope.Angelo.API.Custom.Queries2.repositories;

import it.develhope.Angelo.API.Custom.Queries2.entities.Flight;
import it.develhope.Angelo.API.Custom.Queries2.entities.FlightStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight,Long> {


    List<Flight> findByFlightStatus(FlightStatus status);

    @Query("select f from Flight f where f.flightStatus = :p1 OR f.flightStatus = :p2")
    List<Flight> getCustomFlight(@Param("p1") FlightStatus p1, @Param("p2") FlightStatus p2);

}
