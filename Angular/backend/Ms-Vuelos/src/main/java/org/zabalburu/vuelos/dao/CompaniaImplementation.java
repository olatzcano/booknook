package org.zabalburu.vuelos.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.zabalburu.vuelos.modelos.Compania;

@Repository
public class CompaniaImplementation {
	@Autowired
	private CompaniaDAO dao;
	

	public List<Compania> getCompanias(){
		return dao.findAll();
	}
	
	public Compania getCompania(Integer id){
		return dao.findById(id).orElse(null);
	}
	
	public Compania añadirCompania(Compania c){
		return dao.save(c);
	}
	
	public Compania modificarCompania(Compania c){
		return dao.save(c);
	}
	
	public void eliminarCompania(Integer id){
		dao.deleteById(id);
	}
	
}
