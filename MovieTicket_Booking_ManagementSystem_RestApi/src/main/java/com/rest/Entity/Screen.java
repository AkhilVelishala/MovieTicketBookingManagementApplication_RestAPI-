package com.rest.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "theater_Screen123")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Screen {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "screen_id")
	private int id;

	@Column(name = "screen_name")
	private String name;

	@Column(name = "screen_capacity")
	private int capacity;

	/* one Theater Many Screens Available many to one RelationShip */
	@ManyToOne
	@JoinColumn(name = "theater_id")
	@JsonIgnore
	private Theater theater;

	 @ManyToMany(mappedBy = "screens")
	 @JsonIgnore
	    private List<Movie> movies;

	/* for one Screen many seats are available one to many realtionShip */
	@OneToMany(mappedBy = "screen", orphanRemoval = true)
	@JsonIgnore
	private List<Seat> seats;




}
