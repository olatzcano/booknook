package org.zabalburu.puntosinteres.controller;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zabalburu.puntosinteres.dao.PuntoInteresImplementation;
import org.zabalburu.puntosinteres.dto.PuntoInteresDTO;
import org.zabalburu.puntosinteres.modelo.PuntoInteres;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PuntoInteresController {

	@Autowired
	private PuntoInteresImplementation dao;
	
	
	
	@GetMapping("/interes")
	public PuntoInteresDTO getPuntosInteres(@RequestParam(defaultValue ="1") Integer p, @RequestParam(defaultValue ="2") Integer pp){
		return dao.getPuntosInteres(p,pp);
	}
	
	
	@GetMapping("/interes/{id}")
	public ResponseEntity<PuntoInteres> getInteres(@PathVariable Integer id){
		PuntoInteres p= dao.getPuntoInteres(id);
				if(p==null) {
					return ResponseEntity.notFound().build();
				}else {
					return ResponseEntity.ok(p);
				}
	}
	
	@GetMapping("/interes/ciudad/{id}")
	public PuntoInteresDTO getInteresCiudad(@PathVariable Integer id,@RequestParam(defaultValue ="1") Integer p, @RequestParam(defaultValue ="2") Integer pp){
		return dao.getPuntosInteresCiudad(id,p,pp);
	}

	
	@PostMapping("/interes")
	public ResponseEntity<PuntoInteres>  añadirReserva(@RequestBody PuntoInteres pi) throws URISyntaxException{
		pi=dao.añadirPuntoInteres(pi);
		return  ResponseEntity.created(new URI("http://localhost:8084/interes/" + pi.getIdInteres())).body(pi);
	}
	
	
	@PutMapping("/interes/modificar/{id}")
	public PuntoInteres modificarReserva(@RequestBody PuntoInteres pi,@PathVariable Integer id){
		pi.setIdInteres(id);
		return dao.añadirPuntoInteres(pi);
	}
	
	@DeleteMapping("/interes/eliminar/{id}")
	public ResponseEntity<?> eliminarReserva(@PathVariable Integer id){
		dao.eliminarPuntoInteres(id);
		return ResponseEntity.ok().build();
	}
	
}
