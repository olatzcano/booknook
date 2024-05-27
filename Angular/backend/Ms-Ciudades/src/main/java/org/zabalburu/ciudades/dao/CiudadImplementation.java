package org.zabalburu.ciudades.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.zabalburu.ciudades.modelo.Ciudad;

@Repository
public class CiudadImplementation {
	@Autowired
	CiudadRepository dao;
	
	public List<Ciudad> getDestinos(){
		return dao.findAll();
	}
	
	public Ciudad getDestino(Integer id) {
		return dao.findById(id).orElse(null);
	}
	
	public Ciudad añadirDestino(Ciudad d){
		return dao.save(d);
	}
	
	public void eliminarDestino(Integer id){
		dao.deleteById(id);
	}
	
	public List<Ciudad> getCiudadNombre(String nombre) {
		return dao.getCiudadNombre(nombre);
	}
}
