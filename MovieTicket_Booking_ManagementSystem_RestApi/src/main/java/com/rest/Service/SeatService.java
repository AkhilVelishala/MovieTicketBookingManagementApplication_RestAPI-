package com.rest.Service;



import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.Dto.SeatDto;
import com.rest.Entity.Screen;
import com.rest.Entity.Seat;
import com.rest.Enum.SeatModule;
import com.rest.Repository.ScreenRepository;
import com.rest.Repository.SeatRepository;

@Service
public class SeatService implements SeatInterface {


	@Autowired
	private ScreenRepository screenRepo;


	@Autowired
	private SeatRepository seatRepo;



	@Override
	public List<SeatDto> saveSeat(String screenName) {
	    Screen screen = screenRepo.findByName(screenName);
	    if (screen != null) {
	        // Get the screen's capacity
	        int totalCapacity = screen.getCapacity();
	        int rows = (int) Math.ceil(totalCapacity / 10.0); // Assuming 10 seats per row
	        int seatsPerRow = 10;

	        // Calculate how many regular and premium seats there should be
	        int regularSeatsCount = (int) (totalCapacity * 2.0 / 3.0);  // 2/3 of total capacity
	        int premiumSeatsCount = totalCapacity - regularSeatsCount;  // Remaining seats will be premium

	        List<Seat> seats = new ArrayList<>(); // To hold seats before saving

	        // Create and save seats for each row and seat number
	        int seatCounter = 1;
	        for (int rowIndex = 0; rowIndex < rows; rowIndex++) {
	            for (int seatIndex = 1; seatIndex <= seatsPerRow && seatCounter <= totalCapacity; seatIndex++) {
	                Seat seat = new Seat();
	                seat.setRow(String.valueOf((char) ('A' + rowIndex)));  // Row names: A, B, C...
	                seat.setNumber(seatIndex);  // Seat numbers: 1 to 10

	                // Assign seat type based on regular or premium count
	                if (seatCounter <= regularSeatsCount) {
	                    seat.setType(SeatModule.REGULAR);  // First 2/3 are regular
	                } else {
	                    seat.setType(SeatModule.PREMIUM);  // Remaining 1/3 are premium
	                }

	                seat.setAvailability(true);  // Seats are available by default
	                seat.setScreen(screen);  // Associate seat with the screen
	                seats.add(seat);  // Add seat to the list
	                seatCounter++;  // Increment seat counter
	            }
	        }

	        // Save all seats at once
	        seatRepo.saveAll(seats);

	     // Convert saved seats to DTOs and return
	        List<SeatDto> seatDtos = seats.stream()
	            .map(seat -> {
	                SeatDto seatDto = new SeatDto();
	                seatDto.setId(seat.getId());
	                seatDto.setRow(seat.getRow());
	                seatDto.setNumber(seat.getNumber());
	                seatDto.setType(seat.getType());
	                seatDto.setAvailability(seat.isAvailability());
	                return seatDto;
	            })
	            .collect(Collectors.toList());


	        return seatDtos;
	    } else {
	        return null;
	    }


	}

}
