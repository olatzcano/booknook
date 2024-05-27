package org.zabalburu.puntosinteres.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.zabalburu.puntosinteres.dto.PuntoInteresDTO;
import org.zabalburu.puntosinteres.modelo.PuntoInteres;

@Repository
public class PuntoInteresImplementation {

	@Autowired
	private PuntoInteresRepository dao;
	
	
	public PuntoInteres getPuntoInteres(Integer id) {
		return dao.findById(id).orElse(null);	
	}
	
	public PuntoInteres añadirPuntoInteres(PuntoInteres p){
		return dao.save(p);
	}
	
	public void eliminarPuntoInteres(Integer id){
		dao.deleteById(id);
	}
	
	public PuntoInteresDTO getPuntosInteres(Integer pagina, Integer numPag){
		PuntoInteresDTO dto = new PuntoInteresDTO();
        Pageable p = PageRequest.of(pagina-1,numPag);
        Page pg = dao.getPuntoInteres(p);
        dto.setNumPagina(pagina);
        dto.setRegistros(pg.getTotalElements());
        dto.setTotalPaginas(pg.getTotalPages());
        dto.setPInteres(pg.getContent());
        dto.setNumPInteresPagina(numPag);
        return dto;
	}
	
	public PuntoInteresDTO getPuntosInteresCiudad(Integer id,Integer pagina, Integer numPag){
		PuntoInteresDTO dto = new PuntoInteresDTO();
        Pageable p = PageRequest.of(pagina-1,numPag);
        Page pg = dao.getPuntosInteresCiudad(id,p);
        dto.setNumPagina(pagina);
        dto.setRegistros(pg.getTotalElements());
        dto.setTotalPaginas(pg.getTotalPages());
        dto.setPInteres(pg.getContent());
        dto.setNumPInteresPagina(numPag);
        return dto;
	}
}
