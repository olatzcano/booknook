package org.zabalburu.ciudades.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zabalburu.ciudades.modelo.Ciudad;

public interface CiudadRepository extends JpaRepository<Ciudad, Integer> {
	@Query(value="Select c From Ciudad c where lower(c.ciudad) like lower(concat(:ciudad,'%'))")
	public List<Ciudad> getCiudadNombre(@Param("ciudad") String ciudad) ;
}
