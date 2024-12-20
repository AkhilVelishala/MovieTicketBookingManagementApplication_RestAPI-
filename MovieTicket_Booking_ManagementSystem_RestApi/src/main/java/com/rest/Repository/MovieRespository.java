package com.rest.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rest.Entity.Movie;



@Repository
public interface MovieRespository extends JpaRepository<Movie, Integer> {

	       Movie  findByTitle(String title);

	@Query("SELECT m FROM Movie m JOIN m.theater t WHERE m.title = :title AND t = :theater")
	 Movie  findByMovieTitleAndTheaterName(String title,String theaterName );









}
