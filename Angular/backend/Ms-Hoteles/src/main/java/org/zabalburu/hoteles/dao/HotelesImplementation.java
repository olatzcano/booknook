package org.zabalburu.hoteles.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.zabalburu.hoteles.dto.HotelDTO;
import org.zabalburu.hoteles.modelo.Hotel;

@Repository
public class HotelesImplementation {
	@Autowired
	private HotelRepository dao;
	
	public List<Hotel> getHoteles(){
		return dao.findAll();
	}
	
	public Hotel getHotel(Integer id) {
		return dao.findById(id).orElse(null);	
	}
	
	public Hotel añadirHotel(Hotel h){
		return dao.save(h);
	}
	public void eliminarHotel(Integer id){
		dao.deleteById(id);
	}
	
	public HotelDTO getHoteles(Integer pagina, Integer numPag){
		HotelDTO dto = new HotelDTO();
        Pageable p = PageRequest.of(pagina-1,numPag);
        Page pg = dao.getHoteles(p);
        dto.setNumPagina(pagina);
        dto.setRegistros(pg.getTotalElements());
        dto.setTotalPaginas(pg.getTotalPages());
        dto.setHoteles(pg.getContent());
        dto.setNumHotelesPag(numPag);
        return dto;
	}
	
	public HotelDTO getHotelesCiudad(Integer idCiudad,Integer pagina, Integer numPag){
		HotelDTO dto = new HotelDTO();
        Pageable p = PageRequest.of(pagina-1,numPag);
        Page pg = dao.getHotelesCiudad(idCiudad,p);
        dto.setNumPagina(pagina);
        dto.setRegistros(pg.getTotalElements());
        dto.setTotalPaginas(pg.getTotalPages());
        dto.setHoteles(pg.getContent());
        dto.setNumHotelesPag(numPag);
        return dto;
	}
}
