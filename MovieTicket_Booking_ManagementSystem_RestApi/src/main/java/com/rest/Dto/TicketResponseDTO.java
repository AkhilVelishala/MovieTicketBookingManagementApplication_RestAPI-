package com.rest.Dto;

import java.time.LocalDateTime;
import java.util.List;

import com.rest.Enum.SeatModule;
import com.rest.Enum.TicketStatus;

import lombok.Data;

@Data
public class TicketResponseDTO {
	 private int id;
	    private String userName;
	    private String movieTitle;
	    private String theaterName;
	    private String screenName;
	    private Double price;
	    private int seatId;
	    private List<SeatDto> seats;
	    private LocalDateTime bookingTime;

	    @Data
	    public static class SeatDto {

	        private String row;
	        private int number;
	        private SeatModule type;
	        private TicketStatus status;
	    }
}
