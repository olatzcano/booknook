package org.zabalburu.usuarios.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zabalburu.usuarios.modelo.Reserva;

public interface ReservaRepository  extends JpaRepository<Reserva, Integer> {
	@Query(value="Select r From Reserva r Where r.viaje.id=:id")
	public List<Reserva> getReservasViaje(@Param("id") Integer idViaje);
	
	@Query(value="Select r From Reserva r Where r.fInicio >=:fInicio AND r.fFin<=:fFin AND r.viaje.usuario.idUsuario=:idUsuario")
	public  Page<Reserva> getReservasFecha(@Param("fInicio") Date fInicio,@Param("fFin") Date fFin,@Param("idUsuario") Integer idUsuario,Pageable p);
	
}
