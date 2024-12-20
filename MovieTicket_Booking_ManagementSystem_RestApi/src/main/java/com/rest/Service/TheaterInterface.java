package com.rest.Service;

import java.util.List;

import com.rest.Dto.TheaterDto;
import com.rest.Entity.Theater;

public interface TheaterInterface {

	/* save theater */
	public TheaterDto saveTheater(TheaterDto theaterDto);

	/* get theater by theaterName */
	public TheaterDto getTheater(String name);

	/* get theater by theaterLocation */
	public TheaterDto getTheaterLocation(String location);

	/* get all theater avaliable */
	public List<TheaterDto> getAllTheater();

	/* update the theater */
	public TheaterDto updateTheater(TheaterDto theaterDto,String name);


	/* delete theater by name */
	public String deleteTheater(String name);

	/* Add movies to theater */
	public Theater AddMoviesToTheater(String name,String title);



}
