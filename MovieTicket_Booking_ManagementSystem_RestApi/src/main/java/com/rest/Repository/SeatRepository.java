package com.rest.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rest.Entity.Screen;
import com.rest.Entity.Seat;
import com.rest.Enum.SeatModule;




@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {

	Seat  findByAvailability(boolean availability);
	List<Seat>  findByNumber(int number);
	List<Seat>  findByRow(String row);
	List<Seat> findByType(SeatModule type);

	List<Seat> findByScreen(Screen screen);




	@Query("SELECT s FROM Seat s WHERE s.screen.id = :screenId AND s.row = :row AND s.number = :number")
    Optional<Seat> findByScreenIdAndRowAndNumber(int screenId, String row, int number);


}





