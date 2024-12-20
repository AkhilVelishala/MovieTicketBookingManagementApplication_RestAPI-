package com.rest.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.Dto.TheaterDto;
import com.rest.Entity.Theater;
import com.rest.Service.TheaterService;

@RestController
@RequestMapping("/Theater")
@PreAuthorize("hasRole('ADMIN')")
public class TheaterController {

	@Autowired
	private TheaterService theaterService;

	/* save theater endpoint url */
	@PostMapping("/Save")
	public ResponseEntity<String> saveTheater(@RequestBody TheaterDto theaterDto) {
		TheaterDto theaterDt = theaterService.saveTheater(theaterDto);
		if (theaterDto != null) {
			return new ResponseEntity<>("Data Added successfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Already present in DB", HttpStatus.BAD_REQUEST);
		}
	}

	/* get theater endpoint url by name */
	@GetMapping("/get/{name}")
	public ResponseEntity<TheaterDto> getTheater(@PathVariable String name) {
		TheaterDto dto = theaterService.getTheater(name);
		if (dto != null) {
			return new ResponseEntity<>(dto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	/* get theater endpoint url by location */
	@GetMapping("/getlocation/{location}")
	public ResponseEntity<TheaterDto> getTheaterLocation(@PathVariable String location) {
		TheaterDto dto = theaterService.getTheaterLocation(location);
		if (dto != null) {
			return new ResponseEntity<>(dto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	/*
	 * delete Theater endpoint url by theaterName
	 */

	@DeleteMapping("/delete/{name}")
	public ResponseEntity<String> deleteTheater(@PathVariable String name)
	{
		String theater      = theaterService.deleteTheater(name);
		if(theater.contains("Theater Doesn't")) {
			return new ResponseEntity<>(theater,HttpStatus.BAD_REQUEST);
		} else {
			return  new ResponseEntity<>(HttpStatus.OK);
		}

	}

	/* Get All Theater endpoint url */

	@GetMapping("/getAll")
	public ResponseEntity<List<TheaterDto>> getAllTheater()
	{
		   List<TheaterDto>dtos   = theaterService.getAllTheater();
		   if(dtos!=null) {
			return new ResponseEntity<>(dtos,HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	/* Update Theater Details endpoint Url */
	@PutMapping("/update/{name}")
	public ResponseEntity<TheaterDto>updateTheater(@RequestBody TheaterDto theaterDto ,@PathVariable String name)
	{
		        TheaterDto dto  = theaterService.updateTheater(theaterDto, name);

		        if(dto!=null) {
					return new ResponseEntity<>(dto,HttpStatus.OK);
				} else {
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
	}




	@PostMapping("/addMovieToTheater/{name}/{title}")
	public ResponseEntity<Theater>addMoviesToTheater(@PathVariable String name, @PathVariable String title)
	{
		    Theater theater      = theaterService.AddMoviesToTheater(name, title);
		    if(theater!=null) {
				return new ResponseEntity<>(theater,HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}

	}







}
