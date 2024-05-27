package org.zabalburu.hoteles.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zabalburu.hoteles.dao.HabitacionesImplementation;
import org.zabalburu.hoteles.dao.HotelesImplementation;
import org.zabalburu.hoteles.dto.HabitacionDTO;
import org.zabalburu.hoteles.dto.HotelDTO;
import org.zabalburu.hoteles.modelo.Habitacion;
import org.zabalburu.hoteles.modelo.Hotel;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HotelesController {
	@Autowired
	private HotelesImplementation htdao;
	
	@Autowired
	private HabitacionesImplementation hbdao;
	
	@GetMapping("/hoteles")
	public HotelDTO getHotel(@RequestParam(defaultValue ="1") Integer p, @RequestParam(defaultValue ="2") Integer pp){
		return htdao.getHoteles(p,pp);
	}
	
	
	@GetMapping("/hoteles/{id}")
	public ResponseEntity<Hotel> getHotel(@PathVariable Integer id){
		Hotel h= htdao.getHotel(id);
				if(h==null) {
					return ResponseEntity.notFound().build();
				}else {
					return ResponseEntity.ok(h);
				}
	}
	
	@GetMapping("hoteles/ciudad/{id}")
	public HotelDTO getHotelCiudad(@PathVariable Integer id,@RequestParam(defaultValue ="1") Integer p, @RequestParam(defaultValue ="2") Integer pp){
		return htdao.getHotelesCiudad(id, p,pp);
	}
	

	@PostMapping("/hoteles")
	public Hotel añadirHotel(@RequestBody Hotel h){
		return htdao.añadirHotel(h);
	}
	
	@DeleteMapping("/hoteles/eliminar/{id}")
	public ResponseEntity<?> eliminarHotel(@PathVariable Integer id){
		htdao.eliminarHotel(id);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("hoteles/modificar/{id}")
	public Hotel modificarHotel(@RequestBody Hotel h,@PathVariable Integer id){
		h.setIdHotel(id);
		return htdao.añadirHotel(h);
	}
	
	@GetMapping("/habitaciones")
	public List<Habitacion> getHabitaciones(){
		return hbdao.getHabitaciones();
	}
	
	
	@GetMapping("/habitaciones/{id}")
	public ResponseEntity<Habitacion> getHabitacion(@PathVariable Integer id){
		Habitacion h= hbdao.getHabitacion(id);
				if(h==null) {
					return ResponseEntity.notFound().build();
				}else {
					return ResponseEntity.ok(h);
				}
	}

	@PostMapping("/hoteles/{idHotel}/habitaciones")
	public ResponseEntity<?> añadirHabitacion(@PathVariable Integer idHotel,@RequestBody Habitacion h){
		Hotel hotel = htdao.getHotel(idHotel);
		if(hotel==null) {
			return ResponseEntity.notFound().build();
		}else {
			h.setHotel(hotel);
			hbdao.añadirHabitacion(h);
			return ResponseEntity.ok(h);
		}
	}
	
	@DeleteMapping("/habitaciones/eliminar/{id}")
	public ResponseEntity<?> eliminarHabitacion(@PathVariable Integer id){
		hbdao.eliminarHabitacion(id);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("habitaciones/modificar/{id}")
	public Habitacion modificarHabitacion(@RequestBody Habitacion h,@PathVariable Integer id){
		h.setIdHabitacion(id);
		return hbdao.añadirHabitacion(h);
	}
	
	@GetMapping("hoteles/{id}/habitaciones")
	public HabitacionDTO getHabitacionesHotel(@PathVariable Integer id,@RequestParam(defaultValue ="1") Integer p, @RequestParam(defaultValue ="2") Integer pp){
		return hbdao.getHabitacionesHotel(id, p,pp);
	}
}
