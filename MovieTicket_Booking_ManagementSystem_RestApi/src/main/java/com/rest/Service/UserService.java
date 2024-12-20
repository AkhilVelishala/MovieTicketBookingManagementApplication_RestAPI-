package com.rest.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rest.Dto.UserDto;
import com.rest.Entity.User;
import com.rest.Repository.UserRepository;

@Service
public class UserService implements UserInterface {

	@Autowired
	private UserRepository userRepo;

	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

	@Override
	public UserDto saveUser(User user) {
		// Encoding Password
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		User user2 = userRepo.save(user);

		UserDto dto = new UserDto();
		dto.setId(user2.getId());
		dto.setEmail(user2.getEmail());
		dto.setName(user2.getName());
		dto.setPhoneNumber(user.getPhoneNumber());
		dto.setUser(user2.getUser());
		dto.setPassword(user2.getPassword());
		return dto;

	}

}
