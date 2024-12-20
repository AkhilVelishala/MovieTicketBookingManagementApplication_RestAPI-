package com.rest.Service;

import com.rest.Dto.ScreenDto;
import com.rest.Entity.Screen;

public interface ScreenInterface {

	/* saving screen */
	public ScreenDto saveScreen(ScreenDto screenDto);

	/* Update Screen */
	public ScreenDto updateScreen(ScreenDto screenDto,String name);

	/* Delete Screen by name */
	public String deleteScreen(String name);

	/* Adding Screen to Theater by theatername,ScreenName */
	public Screen addScreensToTheater(String name,String theaterName);


}
