package org.zabalburu.hoteles.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.zabalburu.hoteles.dto.HabitacionDTO;
import org.zabalburu.hoteles.modelo.Habitacion;

@Repository
public class HabitacionesImplementation {
	@Autowired
	private HabitacionesRepository dao;
	
	public List<Habitacion> getHabitaciones(){
		return dao.findAll();
	}
	
	public Habitacion getHabitacion(Integer id) {
		return dao.findById(id).orElse(null);	
	}
	
	public Habitacion añadirHabitacion(Habitacion h){
		return dao.save(h);
	}
	public void eliminarHabitacion(Integer id){
		dao.deleteById(id);
	}
	public HabitacionDTO getHabitacionesHotel(Integer idHotel,Integer pagina, Integer numPag){
		HabitacionDTO dto = new HabitacionDTO();
        Pageable p = PageRequest.of(pagina-1,numPag);
        Page pg = dao.getHabitacionesHotel(idHotel,p);
        dto.setNumPagina(pagina);
        dto.setRegistros(pg.getTotalElements());
        dto.setTotalPaginas(pg.getTotalPages());
        dto.setHabitaciones(pg.getContent());
        dto.setNumHabitacionesPag(numPag);
        return dto;
	}
}
