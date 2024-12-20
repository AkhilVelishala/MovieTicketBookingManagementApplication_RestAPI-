package com.rest.Dto;

import java.time.LocalDateTime;
import java.util.List;

import com.rest.Enum.SeatModule;
import com.rest.Enum.TicketStatus;

import lombok.Data;

@Data
public class TicketDto {

	    private int id;
	    private double price;
	    private LocalDateTime bookingTime;
	    private int userId;
	    private int movieId;
	    private int theaterId;
	    private int screenId;
	    private int seatId;
	    private List<SeatSelectionDTO> seatSelections;

	    @Data
	    public static class SeatSelectionDTO {
	        private String row;
	        private int number;
	        private SeatModule type;
	        private TicketStatus status;



	        public double getPrice() {
	            return type.getPrice();
	        }


	    }


}
