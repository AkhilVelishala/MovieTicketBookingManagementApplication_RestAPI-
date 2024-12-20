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

import com.rest.Dto.ScreenDto;
import com.rest.Entity.Screen;
import com.rest.Service.ScreenService;

@RestController
@RequestMapping("/Screen")
@PreAuthorize("hasRole('ADMIN')")
public class ScreenController {

	@Autowired
	private ScreenService screenService;

	@PostMapping("/Save")
	public ResponseEntity<String> saveScreen(@RequestBody ScreenDto screenDto) {
		ScreenDto dto = screenService.saveScreen(screenDto);
		if (dto != null) {
			return new ResponseEntity<>("Screen is Added in DataBase", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Screen Already exist in DataBase", HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/Update/{name}")
	public ResponseEntity<String> updateScreen(@RequestBody ScreenDto screenDto, @PathVariable String name) {
		ScreenDto dto = screenService.updateScreen(screenDto, name);
		if (dto != null) {
			return new ResponseEntity<>("Screen Updated Successfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Screen doesn't exist in the DataBase", HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping("/Delete/{name}")
	public ResponseEntity<String> deleteScreen(@PathVariable String name) {
		String dto = screenService.deleteScreen(name);

		if (dto.contains("screen is deleted")) {
			return new ResponseEntity<>(dto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/addScreenToTheater/{ScreenName}/{theaterName}")
	public ResponseEntity<Screen> addScreenToTheater(@PathVariable String ScreenName, @PathVariable String theaterName) {
		Screen screen = screenService.addScreensToTheater(ScreenName, theaterName);
		if(screen!=null) {
			return new ResponseEntity<>(screen,HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}
}
