package com.rest.Service;

import java.util.List;

import com.rest.Dto.MovieDto;
import com.rest.Dto.ScreenDto;
import com.rest.Dto.SeatDto;
import com.rest.Dto.TheaterDto;
import com.rest.Dto.TicketDto;
import com.rest.Dto.TicketResponseDTO;
import com.rest.Enum.SeatModule;

public interface TicketInferace {

	/* get All theater */
	public List<TheaterDto> getAllTheater();

	/* selected theaterName with Movies url */
	public List<MovieDto> getAllMovieswithAssociateTheater(String theaterName);

	/* selected movieTitle with screenName */
	public List<ScreenDto> getAllScreenswithAssociateMovie(String movieTitle);


	/* selected screenName with available seats */
	public List<SeatDto> getAllSeatswithScreenName(String screenName);

	/* select A type of Seats depends on premiumAndRegular */
	public List<SeatDto>getSeatDependsOnPremium_Regular(String screenName, SeatModule type);


	/* ticket Booking help seat type, row ,seatNumber */
	 public TicketResponseDTO bookTicket(TicketDto ticketDto) throws Exception;


		/* ticket deleted method based on TicketId */
	 public String CancelTicket(int ticketId);


		/* get ticket by Id */
	 public TicketResponseDTO getTicketById(int ticketId);


		/* get All ticket */
	 public List<TicketResponseDTO>getAll();













}
