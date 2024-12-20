package com.rest.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.Dto.ScreenDto;
import com.rest.Entity.Screen;
import com.rest.Entity.Theater;
import com.rest.Repository.ScreenRepository;
import com.rest.Repository.TheaterRepository;

@Service
public class ScreenService implements ScreenInterface {

	@Autowired
	private ScreenRepository screenRepo;

	@Autowired
	private TheaterRepository theaterRepo;

	@Override
	public ScreenDto saveScreen(ScreenDto screenDto) {
	    String name = screenDto.getName();

	    // Check if screen with the same name already exists
	    Screen existingScreen = screenRepo.findByName(name);

	    if (existingScreen == null) {

	        // Create a new Screen entity using data from screenDto
	        Screen newScreen = new Screen();
	        newScreen.setName(screenDto.getName());
	        newScreen.setCapacity(screenDto.getCapacity());


	        Screen savedScreen = screenRepo.save(newScreen);


	        ScreenDto dto = new ScreenDto();
	        dto.setId(savedScreen.getId());
	        dto.setCapacity(savedScreen.getCapacity());
	        dto.setName(savedScreen.getName());

	        return dto;
	    } else {

	        return null;
	    }
	}


	@Override
	public ScreenDto updateScreen(ScreenDto screenDto, String name) {
	    // Find the screen by its name
	    Screen screen2 = screenRepo.findByName(name);

	    // If the screen exists, update its properties
	    if (screen2 != null) {

	        screen2.setName(screenDto.getName());
	        screen2.setCapacity(screenDto.getCapacity());

	        // Save the updated screen
	        Screen updatedScreen = screenRepo.save(screen2);


	        ScreenDto dto = new ScreenDto();
	        dto.setId(updatedScreen.getId());
	        dto.setName(updatedScreen.getName());
	        dto.setCapacity(updatedScreen.getCapacity());


	        return dto;
	    } else {

	        return null;
	    }
	}


	@Override
	public String deleteScreen(String name) {
		Screen screen = screenRepo.findByName(name);
		if (screen != null) {
			screenRepo.delete(screen);
			return "screen is deleted from DataBase";
		} else {
			return "Screen Not found in DataBase";
		}
	}

	@Override
	public Screen addScreensToTheater(String name, String theaterName) {
		Screen screen = screenRepo.findByName(name);

		Theater theater = theaterRepo.findByName(theaterName);

		if (screen != null && theater != null) {
			theater.getScreens().add(screen);
			theaterRepo.save(theater);
			screen.setTheater(theater);

			screenRepo.save(screen);
			return screen;
		} else {
			return null;
		}
	}

}
