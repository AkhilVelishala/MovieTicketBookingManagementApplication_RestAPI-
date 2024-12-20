package com.rest.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.Entity.User;


public interface UserRepository extends JpaRepository<User, Integer> {
	User findByName(String name);

}
