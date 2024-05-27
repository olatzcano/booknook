package org.zabalburu.vuelos.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.zabalburu.vuelos.modelos.Aeropuerto;

@Repository
public class AeropuertoImplementation {
	@Autowired
	private AeropuertoRepository dao;
	

	public List<Aeropuerto> getAeropuerto(){
		return dao.findAll();
	}
	
	public Aeropuerto getAeropuerto(Integer id){
		return dao.findById(id).orElse(null);
	}
	
	public Aeropuerto añadirAeropuerto(Aeropuerto a){
		return dao.save(a);
	}
	
	public Aeropuerto modificarAeropuerto(Aeropuerto a){
		return dao.save(a);
	}
	
	public void eliminarAeropuerto(Integer id){
		dao.deleteById(id);
	}
	
	public List<Aeropuerto> getAeropuertoCiudad(@Param("idCiudad") Integer idCiudad){
		return dao.getAeropuertoCiudad(idCiudad);
	}
}
