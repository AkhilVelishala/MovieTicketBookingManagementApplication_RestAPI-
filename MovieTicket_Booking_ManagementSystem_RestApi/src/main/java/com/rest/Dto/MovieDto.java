package com.rest.Dto;

import java.time.LocalDate;

import com.rest.Enum.MovieModule;

import lombok.Data;

@Data
public class MovieDto {

	private Long id;

	private String title;

	private String description;

	private MovieModule genre;

	private String duration;

	private LocalDate releaseDate;

	private double rating;






}
