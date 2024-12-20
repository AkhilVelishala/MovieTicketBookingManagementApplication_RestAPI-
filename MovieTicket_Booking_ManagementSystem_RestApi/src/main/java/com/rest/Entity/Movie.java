package com.rest.Entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rest.Enum.MovieModule;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="movie_Module123")
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="movie_id")
	private Long id;

	@Column(name = "movie_title", nullable = false)
	private String title;

	@Column(name = "movie_description", nullable = false)
	private String description;

	@Enumerated(value = EnumType.STRING)
	private MovieModule genre;

	@Column(name = "movie_duration", nullable = false)
	private String duration;

	@Temporal(TemporalType.DATE)
	private LocalDate releaseDate;

	@Column(name = "movie_rating")
	private double rating;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "movie_Theater12",
	joinColumns = @JoinColumn(name = "movie_id"),
	inverseJoinColumns = @JoinColumn(name = "theater_id"))
	@JsonIgnore
	private List<Theater> theater=new ArrayList<>();



	 @ManyToMany
	    @JoinTable(
	        name = "screen_movie12",
	        joinColumns = @JoinColumn(name = "movie_id"),
	        inverseJoinColumns = @JoinColumn(name = "screen_id")
	    )
	 @JsonIgnore
	    private List<Screen> screens;










}
