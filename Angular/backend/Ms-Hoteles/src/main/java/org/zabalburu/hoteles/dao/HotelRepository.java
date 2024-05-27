package org.zabalburu.hoteles.dao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zabalburu.hoteles.modelo.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {
	@Query(value="Select h From Hotel h")
	public Page<Hotel> getHoteles(Pageable p);
	
	@Query(value="Select h From Hotel h where h.idCiudad=:idCiudad")
	public Page<Hotel> getHotelesCiudad(@Param("idCiudad") Integer idCiudad,Pageable p) ;
}
