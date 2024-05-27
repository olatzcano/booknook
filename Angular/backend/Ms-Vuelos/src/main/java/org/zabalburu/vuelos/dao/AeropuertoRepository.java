package org.zabalburu.vuelos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zabalburu.vuelos.modelos.Aeropuerto;

public interface AeropuertoRepository extends JpaRepository<Aeropuerto, Integer> {
	@Query(value="Select a From Aeropuerto a Where a.idCiudad=:idCiudad")
	public List<Aeropuerto> getAeropuertoCiudad(@Param("idCiudad") Integer idCiudad);
}
