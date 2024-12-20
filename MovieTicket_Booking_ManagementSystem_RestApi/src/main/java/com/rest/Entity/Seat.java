package com.rest.Entity;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rest.Enum.SeatModule;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="seat_Module123")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Seat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="serial_id")
	private int id;

	@Column(name="seat_row")
	private String row;

	@Column(name="seat_number")
	private int number;

	@Enumerated(value = EnumType.STRING)
	private SeatModule type;

	private boolean availability;

	/* for one Screen many seats avalible manytoone relationship */
	@ManyToOne
	@JoinColumn(name="screen_id")
	@JsonIgnore
	private Screen screen;

	@OneToMany(mappedBy = "seat") // This should be fine, as 'seat' exists in TicketEntity.
	private List<TicketEntity> tickets;





}
