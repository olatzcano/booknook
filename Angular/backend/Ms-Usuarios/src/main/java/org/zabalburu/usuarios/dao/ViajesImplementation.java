package org.zabalburu.usuarios.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.zabalburu.usuarios.dto.ViajeDTO;
import org.zabalburu.usuarios.modelo.Viaje;

@Repository
public class ViajesImplementation {
	@Autowired
	private ViajesRepository dao;
	
	public List<Viaje> getViajes(){
		return dao.findAll();
	}
	
	public Viaje getViaje(Integer id) {
		return dao.findById(id).orElse(null);	
	}
	
	public Viaje añadirViaje(Viaje v){
		return dao.save(v);
	}
	public void eliminarViaje(Integer id){
		dao.deleteById(id);
	}
	
	public ViajeDTO getViajesUsuario(Integer idUsuario,Integer pagina, Integer numPag){
		ViajeDTO dto = new ViajeDTO();
        Pageable p = PageRequest.of(pagina-1,numPag);
        Page pg = dao.getViajeUsuario(idUsuario,p);
        dto.setNumPagina(pagina);
        dto.setRegistros(pg.getTotalElements());
        dto.setTotalPaginas(pg.getTotalPages());
        dto.setViajes(pg.getContent());
        dto.setNumViajesPag(numPag);
        return dto;
	}
}
