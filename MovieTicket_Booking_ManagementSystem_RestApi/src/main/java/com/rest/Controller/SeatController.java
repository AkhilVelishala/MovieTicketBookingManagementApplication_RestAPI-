package com.rest.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.Dto.SeatDto;
import com.rest.Service.SeatService;

@RestController
@RequestMapping("/Seat")
@PreAuthorize("hasRole('ADMIN')")
public class SeatController {

	@Autowired
	private SeatService seatService;

	@PostMapping("/generate/{screenName}")
	public ResponseEntity<List<SeatDto>> generateSeats(@PathVariable String screenName) {

	    List<SeatDto> list = seatService.saveSeat(screenName);


	    if (list != null ) {
			return new ResponseEntity<>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}


	}
}



