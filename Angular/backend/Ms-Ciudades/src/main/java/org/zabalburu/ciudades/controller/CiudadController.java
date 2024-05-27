package org.zabalburu.ciudades.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.zabalburu.ciudades.dao.CiudadImplementation;
import org.zabalburu.ciudades.modelo.Ciudad;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CiudadController {
	@Autowired
	private CiudadImplementation dao;
	
	@GetMapping("/ciudades")
	public List<Ciudad> getCiudades(){
		return dao.getDestinos();
	}
	
	@GetMapping("/ciudades/nombre/{nombre}")
	public List<Ciudad> getCiudadNombre(@PathVariable String nombre){
		return dao.getCiudadNombre(nombre);
	}
	
	@GetMapping("/ciudades/{id}")
	public ResponseEntity<Ciudad> getCiudad(@PathVariable Integer id){
		Ciudad c= dao.getDestino(id);
				if(c==null) {
					return ResponseEntity.notFound().build();
				}else {
					return ResponseEntity.ok(c);
				}
	}

	@PostMapping("/ciudades")
	public Ciudad añadirDestino(@RequestBody Ciudad c){
		return dao.añadirDestino(c);
	}
	
	@DeleteMapping("/ciudades/eliminar/{id}")
	public ResponseEntity<?> eliminarDestino(@PathVariable Integer id){
		dao.eliminarDestino(id);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/ciudades/modificar/{id}")
	public Ciudad modificarCiudad(@RequestBody Ciudad c,@PathVariable Integer id){
		c.setIdCiudad(id);
		return dao.añadirDestino(c);
	}
}
