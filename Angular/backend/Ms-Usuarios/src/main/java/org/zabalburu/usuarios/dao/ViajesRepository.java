package org.zabalburu.usuarios.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zabalburu.usuarios.modelo.Reserva;
import org.zabalburu.usuarios.modelo.Viaje;

public interface ViajesRepository extends JpaRepository<Viaje, Integer> {
	@Query(value="Select v From Viaje v Where v.usuario.id=:id")
	public Page<Viaje> getViajeUsuario(@Param("id") Integer idUsuario,Pageable p);
}
