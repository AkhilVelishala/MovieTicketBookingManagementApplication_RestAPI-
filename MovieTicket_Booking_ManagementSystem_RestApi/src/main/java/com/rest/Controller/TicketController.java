package com.rest.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.Dto.MovieDto;
import com.rest.Dto.ScreenDto;
import com.rest.Dto.SeatDto;
import com.rest.Dto.TheaterDto;
import com.rest.Dto.TicketDto;
import com.rest.Dto.TicketResponseDTO;
import com.rest.Enum.SeatModule;
import com.rest.Service.TicketService;

@RestController
@RequestMapping("/Ticket")
public class TicketController {

	@Autowired
	private TicketService ticketService;

	/* get All theater */
	@PreAuthorize("hasRole('User')")
	@GetMapping("/ShowAllTheaters")
	public ResponseEntity<List<TheaterDto>> getAllTheaters() {

		List<TheaterDto> theaters = ticketService.getAllTheater();
		if (theaters != null) {
			return new ResponseEntity<>(theaters, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	/* selected theaterName with Movies url */
	@PreAuthorize("hasRole('User')")
	@GetMapping("/getAllMovies/{theaterName}")
	public ResponseEntity<List<MovieDto>> getMoviesForTheater(@PathVariable String theaterName) {
		List<MovieDto> movies = ticketService.getAllMovieswithAssociateTheater(theaterName);
		if (movies != null) {
			return new ResponseEntity<>(movies, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	/* selected movieTitle with screenName */
	@PreAuthorize("hasRole('User')")
	@GetMapping("/getAllScreens/{movieTitle}")
	public ResponseEntity<List<ScreenDto>> getScreensForSelectMovie(@PathVariable String movieTitle) {
		List<ScreenDto> screenDtos = ticketService.getAllScreenswithAssociateMovie(movieTitle);
		if (screenDtos != null) {
			return new ResponseEntity<>(screenDtos, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	/* selected screenName with available seats */
	@GetMapping("/getAllSeats/{screenName}")
	@PreAuthorize("hasRole('User')")
	public ResponseEntity<List<SeatDto>> getSeatsForSelectScreen(@PathVariable String screenName) {
		List<SeatDto> seatDtos = ticketService.getAllSeatswithScreenName(screenName);

		if (seatDtos != null) {
			return new ResponseEntity<>(seatDtos, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	/* select seatType with SeatName */
	@PreAuthorize("hasRole('User')")
	@GetMapping("/getselectedSeats/{seatType}/{screenName}")
	public ResponseEntity<List<SeatDto>> getSeatsforDependsUponPremium_Regular(@PathVariable String screenName,
			@PathVariable SeatModule seatType) {
		List<SeatDto> seatDtos = ticketService.getSeatDependsOnPremium_Regular(screenName, seatType);

		if (seatDtos != null) {
			return new ResponseEntity<>(seatDtos, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping("/book")
	@PreAuthorize("hasRole('User')")
	public ResponseEntity<TicketResponseDTO> BooKASeat(@RequestBody TicketDto ticketDto) throws Exception {
		TicketResponseDTO dto = ticketService.bookTicket(ticketDto);
		if (dto != null) {
			return new ResponseEntity<>(dto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/cancel/{ticketId}")
	@PreAuthorize("hasRole('User')")
	public ResponseEntity<String> cancelTicket(@PathVariable int ticketId) {

		String response = ticketService.CancelTicket(ticketId);

		if ("Ticket status updated to CANCELLED and seat marked as available.".equals(response)) {
			return new ResponseEntity<>("Ticket canceled and seat availability updated successfully.", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Ticket cancellation failed or ticket already cancelled.",
					HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping("/get/{ticketId}")
	@PreAuthorize("hasRole('User')")
	public ResponseEntity<TicketResponseDTO>getTicket(@PathVariable int ticketId)
	{
		    TicketResponseDTO dto      = ticketService.getTicketById(ticketId);
		    if(dto!=null) {
				return new  ResponseEntity<>(dto,HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
	}
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/getAll")
	public ResponseEntity<List<TicketResponseDTO>>getAllTickets()
	{
		List<TicketResponseDTO> all      = ticketService.getAll();
		    if(all!=null) {
				return new  ResponseEntity<>(all,HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
	}


}
