package org.zabalburu.vuelos.dao;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zabalburu.vuelos.modelos.Vuelo;

public interface VueloRepository extends JpaRepository<Vuelo, Integer> {
	@Query(value="Select v From Vuelo v Where v.origen.idAeropuerto=:idOrigen AND v.destino.idAeropuerto=:idDestino AND v.fecha >=:fecha AND v.fecha<=:fFin ")
	public Page<Vuelo> getVueloOrigenDestino(@Param("idOrigen") Integer idOrigen,@Param("idDestino")Integer idDestino,@Param("fecha") Date fecha ,@Param("fFin") Date ffin ,Pageable p);

}
