package org.zabalburu.puntosinteres.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zabalburu.puntosinteres.modelo.PuntoInteres;

public interface PuntoInteresRepository extends JpaRepository<PuntoInteres, Integer> {
	@Query(value="Select pi From PuntoInteres pi")
	public Page<PuntoInteres> getPuntoInteres(Pageable p);
	
	@Query(value="Select pi From PuntoInteres pi where pi.idCiudad=:idCiudad")
	public Page<PuntoInteres> getPuntosInteresCiudad(@Param("idCiudad") Integer idCiudad,Pageable p) ;
}
