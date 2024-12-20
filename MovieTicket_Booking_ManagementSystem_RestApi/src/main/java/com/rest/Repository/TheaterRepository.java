package com.rest.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rest.Entity.Theater;




@Repository
public interface TheaterRepository extends JpaRepository<Theater, Integer> {
	Theater  findByName(String name);
	 Theater findByLocation(String location);

}
