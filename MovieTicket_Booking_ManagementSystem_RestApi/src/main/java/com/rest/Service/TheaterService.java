package com.rest.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.Dto.TheaterDto;
import com.rest.Entity.Movie;
import com.rest.Entity.Theater;
import com.rest.Repository.MovieRespository;
import com.rest.Repository.TheaterRepository;

@Service
public class TheaterService implements TheaterInterface {

	@Autowired
	private TheaterRepository theaterRepo;

	@Autowired
	private MovieRespository movieRepo;

	@Override
	public TheaterDto saveTheater(TheaterDto theaterDto) {
		String name = theaterDto.getName();

		// Check if the theater already exists
		Theater existingTheater = theaterRepo.findByName(name);

		if (existingTheater == null) {

			Theater theater = new Theater();

			theater.setName(theaterDto.getName());
			theater.setLocation(theaterDto.getLocation());

			// Save the Theater entity
			Theater savedTheater = theaterRepo.save(theater);

			TheaterDto dto = new TheaterDto();
			dto.setId(savedTheater.getId());
			dto.setName(savedTheater.getName());
			dto.setLocation(savedTheater.getLocation());

			return dto; // Return the saved TheaterDto
		} else {

			return null;
		}
	}

	@Override
	public TheaterDto getTheater(String name) {
		Theater theater = theaterRepo.findByName(name);

		TheaterDto dto = new TheaterDto();
		dto.setId(theater.getId());
		dto.setName(theater.getName());
		dto.setLocation(theater.getLocation());
		return dto;
	}

	@Override
	public List<TheaterDto> getAllTheater() {

		// Fetch all theaters from the repository
		List<Theater> theaters = theaterRepo.findAll();

		// Map each Theater entity to a TheaterDto
		List<TheaterDto> theaterDtos = theaters.stream().map(theater -> {
			TheaterDto dto = new TheaterDto();
			dto.setId(theater.getId());
			dto.setName(theater.getName());
			dto.setLocation(theater.getLocation());
			return dto;
		}).collect(Collectors.toList());

		return theaterDtos;
	}

	@Override
	public String deleteTheater(String name) {
		Theater theater = theaterRepo.findByName(name);
		if (theater == null) {
			return "TheaterName Doesn't Exist In DataBase";
		} else {
			theaterRepo.delete(theater);
		}
		return "Theater Deleted From  DataBase";

	}

	@Override
	public TheaterDto getTheaterLocation(String location) {

		Theater theater = theaterRepo.findByLocation(location);

		TheaterDto dto = new TheaterDto();
		dto.setId(theater.getId());
		dto.setName(theater.getName());
		dto.setLocation(theater.getLocation());
		return dto;
	}

	@Override
	public TheaterDto updateTheater(TheaterDto theaterDto, String name) {

		// Fetch the existing Theater by name
		Theater theater2 = theaterRepo.findByName(name);

		if (theater2 != null) {
			// Update the fields of the existing Theater with the values from the DTO
			theater2.setLocation(theaterDto.getLocation());
			theater2.setName(theaterDto.getName());

			Theater updatedTheater = theaterRepo.save(theater2);

			TheaterDto dto = new TheaterDto();
			dto.setId(updatedTheater.getId());
			dto.setLocation(updatedTheater.getLocation());
			dto.setName(updatedTheater.getName());

			return dto;
		} else {

			return null;
		}
	}

	@Override
	public Theater AddMoviesToTheater(String name, String title) {
		// Fetch the theater by name
		Theater theater = theaterRepo.findByName(name);
		if (theater == null) {
			throw new IllegalArgumentException("Theater with name " + name + " not found.");
		}

		// Fetch the movie by title
		Movie movie = movieRepo.findByTitle(title);
		if (movie == null) {
			throw new IllegalArgumentException("Movie with title " + title + " not found.");
		}

		// Add movie to the theater's movie list
		theater.getMovies().add(movie);
		movie.getTheater().add(theater);
		movieRepo.save(movie);

		return theaterRepo.save(theater);

	}
}
