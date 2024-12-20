package com.rest.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.Dto.UserDto;
import com.rest.Entity.User;
import com.rest.Service.UserService;

@RestController
@RequestMapping("/User")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/Save")
	public ResponseEntity<UserDto>saveUser(@RequestBody User user)
	{
		     UserDto user2   = userService.saveUser(user);
		     if(user2!=null) {
				return new ResponseEntity<>(user2,HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
	}

}
