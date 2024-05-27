package org.zabalburu.vuelos.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.zabalburu.vuelos.dto.VueloDTO;
import org.zabalburu.vuelos.modelos.Vuelo;

@Repository
public class VueloImplementation {
	@Autowired
	private VueloRepository dao;
	

	public List<Vuelo> getVuelos(){
		return dao.findAll();
	}
	
	public Vuelo getVuelo(Integer id){
		return dao.findById(id).orElse(null);
	}
	
	public Vuelo añadirVuelo(Vuelo v){
		return dao.save(v);
	}
	
	public Vuelo modificarVuelo(Vuelo v){
		return dao.save(v);
	}
	
	public void eliminarVuelo(Integer id){
		dao.deleteById(id);
	}
	
	public VueloDTO getVueloOrigenDestinoFecha(Integer idOrigen,Integer idDestino,Date fecha,Date fFinal,Integer pagina, Integer numPag){
		VueloDTO dto = new VueloDTO();
        Pageable p = PageRequest.of(pagina-1,numPag);
        Calendar calendar=Calendar.getInstance();
		calendar.setTime(fFinal);
		calendar.add(Calendar.HOUR_OF_DAY, 23);
		calendar.add(Calendar.MINUTE, 59);
		fFinal=calendar.getTime();	
        Page pg = dao.getVueloOrigenDestino(idOrigen,idDestino,fecha,fFinal,p);
        dto.setNumPagina(pagina);
        dto.setRegistros(pg.getTotalElements());
        dto.setTotalPaginas(pg.getTotalPages());
        dto.setVuelos(pg.getContent());
        dto.setNumVuelosPag(numPag);
        return dto;
	}
}
