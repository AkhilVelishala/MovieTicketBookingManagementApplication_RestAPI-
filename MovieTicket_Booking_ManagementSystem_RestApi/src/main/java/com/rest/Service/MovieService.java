package com.rest.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.Dto.MovieDto;
import com.rest.Entity.Movie;
import com.rest.Entity.Screen;
import com.rest.Repository.MovieRespository;
import com.rest.Repository.ScreenRepository;

@Service
public class MovieService implements MovieInterface {

	@Autowired
	private MovieRespository movieRepo;

	@Autowired
	private ScreenRepository screenRepo;

	@Override
	public MovieDto saveMovie(MovieDto movieDto) {
	    String title = movieDto.getTitle();
	    Movie movie2 = movieRepo.findByTitle(title);

	    if (movie2 == null) {
	        // Convert MovieDto to Movie entity
	        Movie movie = new Movie();
	        movie.setTitle(movieDto.getTitle());
	        movie.setDescription(movieDto.getDescription());
	        movie.setGenre(movieDto.getGenre());
	        movie.setDuration(movieDto.getDuration());
	        movie.setReleaseDate(movieDto.getReleaseDate());
	        movie.setRating(movieDto.getRating());

	        // Save the Movie entity
	        Movie savedMovie = movieRepo.save(movie);

	        // Convert the saved Movie entity back to MovieDto
	        MovieDto dto = new MovieDto();
	        dto.setId(savedMovie.getId());
	        dto.setDescription(savedMovie.getDescription());
	        dto.setDuration(savedMovie.getDuration());
	        dto.setGenre(savedMovie.getGenre());
	        dto.setRating(savedMovie.getRating());
	        dto.setReleaseDate(savedMovie.getReleaseDate());
	        dto.setTitle(savedMovie.getTitle());

	        return dto;
	    } else {
	        return null; // Movie already exists
	    }


	}

	@Override
	public MovieDto updateMovie(MovieDto movieDto, String title) {
		Movie movie2 = movieRepo.findByTitle(title);
		if (movie2 != null) {
			movie2.setTitle(movieDto.getTitle());
			movie2.setDescription(movieDto.getDescription());
			movie2.setGenre(movieDto.getGenre());
			movie2.setRating(movieDto.getRating());
			movie2.setReleaseDate(movieDto.getReleaseDate());
			movie2.setDuration(movieDto.getDuration());

			Movie movie3 = movieRepo.save(movie2);
			MovieDto dto = new MovieDto();

			dto.setDescription(movie3.getDescription());
			dto.setDuration(movie3.getDuration());
			dto.setGenre(movie3.getGenre());
			dto.setId(movie3.getId());
			dto.setRating(movie3.getRating());
			dto.setReleaseDate(movie3.getReleaseDate());
			dto.setTitle(movie3.getTitle());
			return dto;

		} else {
			return null;
		}
	}

	@Override
	public String deleteMovie(String title) {
		Movie movie = movieRepo.findByTitle(title);
		if (movie != null) {
			movieRepo.delete(movie);
			return "movie is deleted from DataBase";
		}
		return "movie is doesn't exist in DataBase";
	}

	@Override
	public Movie addMoviesToScreen(String movietitle, String screenName) {
		Movie movie = movieRepo.findByTitle(movietitle);
		Screen screen = screenRepo.findByName(screenName);

		if(movie!=null)
		{
			movie.getScreens().add(screen);

			screen.getMovies().add(movie);
			screenRepo.save(screen);

		return	movieRepo.save(movie);
		} else {
			return null;
		}
	}

}
