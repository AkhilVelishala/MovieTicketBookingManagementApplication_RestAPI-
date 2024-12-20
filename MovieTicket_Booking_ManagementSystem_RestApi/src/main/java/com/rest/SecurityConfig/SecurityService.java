package com.rest.SecurityConfig;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rest.Entity.User;
import com.rest.Repository.UserRepository;

@Service
public class SecurityService implements UserDetailsService {
	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user    = userRepo.findByName(username);
		if(user==null)
		{
			System.out.println("User data not found for username: " + username);
			  throw new  UsernameNotFoundException("User not found: " + username);
		}
		return new ReturnUser(user);
	}

}
