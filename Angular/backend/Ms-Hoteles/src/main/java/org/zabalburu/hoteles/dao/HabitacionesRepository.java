package org.zabalburu.hoteles.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zabalburu.hoteles.modelo.Habitacion;

public interface HabitacionesRepository extends JpaRepository<Habitacion, Integer> {
	@Query(value="Select h From Habitacion h Where h.hotel.id=:id")
	public Page<Habitacion> getHabitacionesHotel(@Param("id") Integer idHotel,Pageable p);
	
}
