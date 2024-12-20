package com.rest.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.Dto.MovieDto;
import com.rest.Entity.Movie;
import com.rest.Service.MovieService;

@RestController
@RequestMapping("/Movie")
@PreAuthorize("hasRole('ADMIN')")
public class MovieController {

	@Autowired
	private MovieService movieService;

	@PostMapping("/Save")
	public ResponseEntity<String> saveMovie(@RequestBody MovieDto movieDto)

	{
		MovieDto dto = movieService.saveMovie(movieDto);

		if (dto != null) {
			return new ResponseEntity<>("Movie Added in DataBase", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Movie Already Exist in DataBase", HttpStatus.BAD_REQUEST);
		}
	}
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/update/{title}")
	public ResponseEntity<String>UpdateMovie(@RequestBody MovieDto movieDto,@PathVariable String title)
	{
		     MovieDto dto   =movieService.updateMovie(movieDto, title);

		     if(dto!=null) {
				return new ResponseEntity<>("Movie is Updated Succesfully",HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Movie is not Updated",HttpStatus.BAD_REQUEST);
			}
	}
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/delete/{title}")

	public ResponseEntity<String>deleteMapping(@PathVariable String title)
	{
		     String movie   = movieService.deleteMovie(title);
		     if(movie.contains("movie is deleted")) {
				return new ResponseEntity<>(movie,HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
	}

	/* movies add to screens */
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/addMoviesToScreens/{movietitle}/{screenName}")
	public ResponseEntity<Movie>addMoviesToTheater(@PathVariable String movietitle,@PathVariable String screenName )
	{
		Movie movie      = movieService.addMoviesToScreen(movietitle, screenName);
		   if(movie!=null) {
			return new ResponseEntity<>(movie,HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}



}
