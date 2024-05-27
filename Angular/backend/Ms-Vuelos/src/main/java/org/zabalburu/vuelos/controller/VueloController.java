package org.zabalburu.vuelos.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import org.zabalburu.vuelos.dao.AeropuertoImplementation;
import org.zabalburu.vuelos.dao.CompaniaImplementation;
import org.zabalburu.vuelos.dao.VueloImplementation;
import org.zabalburu.vuelos.dto.VueloDTO;
import org.zabalburu.vuelos.modelos.Aeropuerto;
import org.zabalburu.vuelos.modelos.Compania;
import org.zabalburu.vuelos.modelos.Vuelo;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class VueloController {

	@Autowired
	private VueloImplementation vdao;
	
	@Autowired
	private AeropuertoImplementation adao;
	
	@Autowired
	private CompaniaImplementation cdao;
	
	@GetMapping("/vuelos")
	public List<Vuelo> getUsuarios(){
		return vdao.getVuelos();
	}
	
	
	@GetMapping("/vuelos/{id}")
	public ResponseEntity<Vuelo> getVuelo(@PathVariable Integer id){
		Vuelo v= vdao.getVuelo(id);
				if(v==null) {
					return ResponseEntity.notFound().build();
				}else {
					return ResponseEntity.ok(v);
				}
	}
	
	
	
	@GetMapping("/vuelos/{idOrigen}/{idDestino}")
	public VueloDTO getVueloOrigenDestinoFecha(@PathVariable Integer idOrigen,@PathVariable Integer idDestino, @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha,@RequestParam(defaultValue ="1") Integer p, @RequestParam(defaultValue ="2") Integer pp){
		if(fecha==null) {
			fecha=new Date();
		}
		return vdao.getVueloOrigenDestinoFecha(idOrigen, idDestino, fecha,fecha, p, pp);
	}
	
	@PostMapping("/vuelos")
	public Vuelo añadirVuelo(@RequestBody Vuelo v){
		return vdao.añadirVuelo(v);
	}
	
	@PutMapping("/vuelos/modificar/{id}")
	public Vuelo modificarVuelo(@RequestBody Vuelo v,@PathVariable Integer id){
		v.setIdVuelo(id);
		return vdao.añadirVuelo(v);
	}
	
	@DeleteMapping("/vuelos/eliminar/{id}")
	public ResponseEntity<?> eliminarVuelos(@PathVariable Integer id){
		vdao.eliminarVuelo(id);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/aeropuertos")
	public List<Aeropuerto> getAeropuerto(){
		return adao.getAeropuerto();
	}
	
	
	@GetMapping("/aeropuertos/{id}")
	public ResponseEntity<Aeropuerto> getAeropuerto(@PathVariable Integer id){
		Aeropuerto a= adao.getAeropuerto(id);
				if(a==null) {
					return ResponseEntity.notFound().build();
				}else {
					return ResponseEntity.ok(a);
				}
	}
	
	@PostMapping("/aeropuertos")
	public Aeropuerto añadirAeropuerto(@RequestBody Aeropuerto a){
		return adao.añadirAeropuerto(a);
	}
	
	@PutMapping("/aeropuertos/modificar/{id}")
	public Aeropuerto modificarVuelo(@RequestBody Aeropuerto a,@PathVariable Integer id){
		a.setIdAeropuerto(id);
		return adao.añadirAeropuerto(a);
	}
	
	@DeleteMapping("/aeropuertos/eliminar/{id}")
	public ResponseEntity<?> eliminarAeropuerto(@PathVariable Integer id){
		adao.eliminarAeropuerto(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/aeropuertos/ciudad/{idCiudad}")
	public List<Aeropuerto> getAeropuetoCiudad(@PathVariable Integer idCiudad){
		
		return adao.getAeropuertoCiudad(idCiudad);
				
	}
	@GetMapping("/companias")
	public List<Compania> getCompania(){
		return cdao.getCompanias();
	}
	
	
	@GetMapping("/companias/{id}")
	public ResponseEntity<Compania> getCompania(@PathVariable Integer id){
		Compania c= cdao.getCompania(id);
				if(c==null) {
					return ResponseEntity.notFound().build();
				}else {
					return ResponseEntity.ok(c);
				}
	}
	
	@PostMapping("/companias")
	public Compania añadirCompania(@RequestBody Compania c){
		return cdao.añadirCompania(c);
	}
	
	@PutMapping("/companias/modificar/{id}")
	public Compania modificarCompania(@RequestBody Compania c,@PathVariable Integer id){
		c.setIdCompania(id);
		return cdao.añadirCompania(c);
	}
	
	@DeleteMapping("/companias/eliminar/{id}")
	public ResponseEntity<?> eliminarCompania(@PathVariable Integer id){
		cdao.eliminarCompania(id);
		return ResponseEntity.ok().build();
	}

}
