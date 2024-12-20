package com.rest.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.Dto.MovieDto;
import com.rest.Dto.ScreenDto;
import com.rest.Dto.SeatDto;
import com.rest.Dto.TheaterDto;
import com.rest.Dto.TicketDto;
import com.rest.Dto.TicketResponseDTO;
import com.rest.Entity.Movie;
import com.rest.Entity.Screen;
import com.rest.Entity.Seat;
import com.rest.Entity.Theater;
import com.rest.Entity.TicketEntity;
import com.rest.Entity.User;
import com.rest.Enum.SeatModule;
import com.rest.Enum.TicketStatus;
import com.rest.Repository.MovieRespository;
import com.rest.Repository.ScreenRepository;
import com.rest.Repository.SeatRepository;
import com.rest.Repository.TheaterRepository;
import com.rest.Repository.TicketRepository;
import com.rest.Repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class TicketService implements TicketInferace {

	@Autowired
	private MovieRespository movieRepo;

	@Autowired
	private ScreenRepository screenRepo;

	@Autowired
	private SeatRepository seatRepo;

	@Autowired
	private TheaterRepository theaterRepo;

	@Autowired
	private TicketRepository ticketRepo;

	@Autowired
	private UserRepository userRepo;

	// Get all theaters
	@Override

	public List<TheaterDto> getAllTheater() {
		List<Theater> theaters = theaterRepo.findAll();
		List<TheaterDto> theaterDtos = new ArrayList<>();
		for (Theater theater : theaters) {
			TheaterDto dto = new TheaterDto();
			dto.setId(theater.getId());
			dto.setName(theater.getName());
			dto.setLocation(theater.getLocation());
			theaterDtos.add(dto);
		}
		return theaterDtos;
	}

	// Get all movies with associated theater
	@Override
	public List<MovieDto> getAllMovieswithAssociateTheater(String theaterName) {
		Theater theater = theaterRepo.findByName(theaterName);
		if (theater != null) {
			List<Movie> movies = theater.getMovies();
			List<MovieDto> movieDtos = new ArrayList<>();
			for (Movie movie : movies) {
				MovieDto dto = new MovieDto();
				dto.setId(movie.getId());
				dto.setTitle(movie.getTitle());
				dto.setGenre(movie.getGenre());
				dto.setReleaseDate(movie.getReleaseDate());
				dto.setDescription(movie.getDescription());
				dto.setDuration(movie.getDuration());
				dto.setRating(movie.getRating());
				movieDtos.add(dto);
			}
			return movieDtos;
		}
		return null;
	}

	// Get all screens with associated movie
	@Override
	public List<ScreenDto> getAllScreenswithAssociateMovie(String movieTitle) {
		Movie movie = movieRepo.findByTitle(movieTitle);
		if (movie != null) {
			List<Screen> screens = movie.getScreens();
			List<ScreenDto> screenDtos = new ArrayList<>();
			for (Screen screen : screens) {
				ScreenDto dto = new ScreenDto();
				dto.setId(screen.getId());
				dto.setName(screen.getName());
				dto.setCapacity(screen.getCapacity());
				screenDtos.add(dto);
			}
			return screenDtos;
		}
		return null;
	}

	// Get all seats for a specific screen
	@Override
	public List<SeatDto> getAllSeatswithScreenName(String screenName) {
		Screen screen = screenRepo.findByName(screenName);
		if (screen != null) {
			List<Seat> seats = screen.getSeats();
			List<SeatDto> seatDtos = new ArrayList<>();
			for (Seat seat : seats) {
				SeatDto dto = new SeatDto();
				dto.setId(seat.getId());
				dto.setNumber(seat.getNumber());
				dto.setRow(seat.getRow());
				dto.setType(seat.getType());
				dto.setAvailability(seat.isAvailability());
				seatDtos.add(dto);
			}
			return seatDtos;
		}
		return null;
	}

	// Get seats depending on Premium or Regular type
	@Override
	public List<SeatDto> getSeatDependsOnPremium_Regular(String screenName, SeatModule type) {
		Screen screen = screenRepo.findByName(screenName);
		if (screen != null) {
			List<Seat> allSeats = seatRepo.findByScreen(screen);
			List<Seat> filteredSeats = new ArrayList<>();
			for (Seat seat : allSeats) {
				if (seat.getType() == type) {
					filteredSeats.add(seat);
				}
			}
			List<SeatDto> seatDtos = new ArrayList<>();
			for (Seat seat : filteredSeats) {
				SeatDto dto = new SeatDto();
				dto.setId(seat.getId());
				dto.setNumber(seat.getNumber());
				dto.setAvailability(seat.isAvailability());
				dto.setRow(seat.getRow());
				dto.setType(seat.getType());
				seatDtos.add(dto);
			}
			return seatDtos;
		}
		return null;
	}

	@Override
	@Transactional
	public TicketResponseDTO bookTicket(TicketDto ticketDto) throws Exception {

		User user = userRepo.findById(ticketDto.getUserId()).orElseThrow(() -> new Exception("User not found"));

		Movie movie = movieRepo.findById(ticketDto.getMovieId()).orElseThrow(() -> new Exception("Movie not found"));

		Theater theater = theaterRepo.findById(ticketDto.getTheaterId())
				.orElseThrow(() -> new Exception("Theater not found"));

		Screen screen = screenRepo.findById(ticketDto.getScreenId())
				.orElseThrow(() -> new Exception("Screen not found"));

		// Validate and process the seat
		Seat selectedSeat = seatRepo
				.findByScreenIdAndRowAndNumber(ticketDto.getScreenId(), ticketDto.getSeatSelections().get(0).getRow(),
						ticketDto.getSeatSelections().get(0).getNumber())
				.orElseThrow(() -> new Exception("Seat not found: Row " + ticketDto.getSeatSelections().get(0).getRow()
						+ ", Number " + ticketDto.getSeatSelections().get(0).getNumber()));

		// Check if the seat is available
		if (!selectedSeat.isAvailability()) {
			throw new Exception("Seat already booked: Row " + ticketDto.getSeatSelections().get(0).getRow()
					+ ", Number " + ticketDto.getSeatSelections().get(0).getNumber());
		}

		// Validate seat type
		String seatType = ticketDto.getSeatSelections().get(0).getType().name();
		if (!selectedSeat.getType().name().equals(seatType)) {
			throw new Exception("Seat type mismatch for Row " + ticketDto.getSeatSelections().get(0).getRow()
					+ ", Number " + ticketDto.getSeatSelections().get(0).getNumber() + ". Expected: "
					+ selectedSeat.getType().name() + ", Found: " + seatType);
		}

		// Update seat availability and calculate price Mark seat as booked Save the
		// seat availability update
		selectedSeat.setAvailability(false);
		seatRepo.save(selectedSeat);

		double totalPrice = selectedSeat.getType().getPrice();

		// Create and save the ticket entity
		TicketEntity ticket = new TicketEntity();
		ticket.setUser(user);
		ticket.setMovie(movie);
		ticket.setTheater(theater);
		ticket.setScreen(screen);
		ticket.setSeat(selectedSeat);
		ticket.setPrice(totalPrice);

		TicketEntity savedTicket = ticketRepo.save(ticket);

		TicketResponseDTO response = new TicketResponseDTO();

		response.setId(savedTicket.getId());

		response.setUserName(user.getName());
		response.setMovieTitle(movie.getTitle());

		response.setTheaterName(theater.getName());

		response.setScreenName(screen.getName());
		response.setSeatId(selectedSeat.getId());
		response.setBookingTime(savedTicket.getBookingTime());
		response.setPrice(totalPrice);

		TicketResponseDTO.SeatDto seatDto = new TicketResponseDTO.SeatDto();
		seatDto.setRow(ticketDto.getSeatSelections().get(0).getRow());
		seatDto.setNumber(ticketDto.getSeatSelections().get(0).getNumber());
		seatDto.setType(ticketDto.getSeatSelections().get(0).getType());
		seatDto.setStatus(TicketStatus.BOOKED);

		ticket.setStatus(seatDto.getStatus());

		response.setSeats(List.of(seatDto));

		return response;
	}

	@Override
	public String CancelTicket(int ticketId) {
		TicketEntity entity = ticketRepo.findById(ticketId).get();

		if (entity.getStatus().equals(TicketStatus.CANCELLED)) {
			return "Ticket is already cancelled.";

		}

		entity.setStatus(TicketStatus.CANCELLED);
		ticketRepo.save(entity);

// Get the associated seat and make it available
		Seat bookedSeat = entity.getSeat();
		bookedSeat.setAvailability(true);
		seatRepo.save(bookedSeat);

		return "Ticket status updated to CANCELLED and seat marked as available.";
	}

	@Override
	public TicketResponseDTO getTicketById(int ticketId) {
	    TicketEntity dto = ticketRepo.findById(ticketId).get();


	    TicketResponseDTO dto2 = new TicketResponseDTO();
	    dto2.setId(dto.getId());
	    dto2.setUserName(dto.getUser().getName());
	    dto2.setMovieTitle(dto.getMovie().getTitle());
	    dto2.setTheaterName(dto.getTheater().getName());
	    dto2.setScreenName(dto.getScreen().getName());
	    dto2.setBookingTime(dto.getBookingTime());
	    dto2.setSeatId(dto.getSeat().getId());


	    TicketResponseDTO.SeatDto seatDto = new TicketResponseDTO.SeatDto();
	    seatDto.setRow(dto.getSeat().getRow());
	    seatDto.setNumber(dto.getSeat().getNumber());
	    seatDto.setType(dto.getSeat().getType());
	    seatDto.setStatus(dto.getStatus());


	    dto2.setSeats(List.of(seatDto));

	    dto2.setPrice(dto.getPrice());
	    return dto2;
	}

	@Override
	public List<TicketResponseDTO> getAll() {
	    List<TicketEntity> all = ticketRepo.findAll();


	    List<TicketResponseDTO> response = new ArrayList<>();

	    for (TicketEntity ticket : all) {
	        TicketResponseDTO dto = new TicketResponseDTO();
	        dto.setId(ticket.getId());
	        dto.setUserName(ticket.getUser().getName());
	        dto.setMovieTitle(ticket.getMovie().getTitle());
	        dto.setTheaterName(ticket.getTheater().getName());
	        dto.setScreenName(ticket.getScreen().getName());
	        dto.setSeatId(ticket.getSeat().getId());
	        dto.setBookingTime(ticket.getBookingTime());
	        dto.setPrice(ticket.getPrice());


	        TicketResponseDTO.SeatDto seatDto = new TicketResponseDTO.SeatDto();
	        seatDto.setRow(ticket.getSeat().getRow());
	        seatDto.setNumber(ticket.getSeat().getNumber());
	        seatDto.setType(ticket.getSeat().getType());
	      seatDto.setStatus(ticket.getStatus());

	        dto.setSeats(List.of(seatDto));

	        response.add(dto);
	    }

	    return response;
	}


	}

