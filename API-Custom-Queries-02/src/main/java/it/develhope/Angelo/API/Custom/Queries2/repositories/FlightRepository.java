package it.develhope.Angelo.API.Custom.Queries2.repositories;

import it.develhope.Angelo.API.Custom.Queries2.entities.Flight;
import it.develhope.Angelo.API.Custom.Queries2.entities.FlightStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight,Long> {
    @Query("select f from Flight f where f.flightStatus = ?0")
    Page<Flight> findAllByStatus(FlightStatus flightStatus, Pageable pageable);

    @Query("select f from Flight f where f.flightStatus = p1 OR f.flightStatus = p2")
    List<Flight> getCustomFlight(FlightStatus p1, FlightStatus p2);


}
