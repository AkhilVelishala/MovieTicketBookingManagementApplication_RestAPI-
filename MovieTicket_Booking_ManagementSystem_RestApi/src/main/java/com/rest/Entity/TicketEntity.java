package com.rest.Entity;

import java.time.LocalDateTime;

import com.rest.Enum.TicketStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="Ticket_module123")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketEntity {



	   @Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   @Column(name="ticket_Id")
	    private int id;

        @Column(name="ticket_price")
	    private double price;

        @Column(name="ticket_bookintime")
	    private LocalDateTime bookingTime;

        @Column(name="ticket_Status")
        @Enumerated(EnumType.STRING)
        private TicketStatus status;


	    @ManyToOne
	    @JoinColumn(name = "user_id")
	    private User user;

	    @ManyToOne
	    @JoinColumn(name = "movie_id", nullable = false)
	    private Movie movie;

	    @ManyToOne
	   @JoinColumn(name = "theater_id")
	    private Theater theater;

	    @ManyToOne
	    @JoinColumn(name = "screen_id", nullable = false)
	    private Screen screen;

	    // One ticket corresponds to one seat (Many tickets per seat)
	    @ManyToOne
	    @JoinColumn(name = "seat_id")  // Make sure the join column name matches the column in your database
	    private Seat seat;

	    @PrePersist
	    protected void onCreate() {
	        this.bookingTime = LocalDateTime.now();
	    }




}
