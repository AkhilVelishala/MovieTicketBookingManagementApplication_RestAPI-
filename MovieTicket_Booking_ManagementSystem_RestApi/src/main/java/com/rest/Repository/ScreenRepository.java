package com.rest.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rest.Entity.Movie;
import com.rest.Entity.Screen;



@Repository
public interface ScreenRepository extends JpaRepository<Screen, Integer> {

	Screen findByName(String name);

	@Query("SELECT u FROM Screen u JOIN u.movies m WHERE u.name = :name AND m = :movie")
	Screen findByNameAndMovies(String name, Movie movie);


}
