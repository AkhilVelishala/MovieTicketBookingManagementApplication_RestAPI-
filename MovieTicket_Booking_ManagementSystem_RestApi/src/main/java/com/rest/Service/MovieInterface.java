package com.rest.Service;

import com.rest.Dto.MovieDto;
import com.rest.Entity.Movie;

public interface MovieInterface {


	/* add movie in dataBase */
	public MovieDto saveMovie(MovieDto movieDto);



	/* update movie in database */
	public MovieDto updateMovie(MovieDto movieDto,String title);


	/* delete movie from database */
	public String deleteMovie(String title);


	/* Adding movies to screeens */
	public Movie addMoviesToScreen(String movietitle,String screenName);



}
