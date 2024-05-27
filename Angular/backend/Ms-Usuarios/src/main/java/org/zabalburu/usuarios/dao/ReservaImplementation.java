package org.zabalburu.usuarios.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.zabalburu.usuarios.dto.ReservaDTO;
import org.zabalburu.usuarios.modelo.Reserva;

@Repository
public class ReservaImplementation {
	@Autowired
	private ReservaRepository dao;
	
	
	public List<Reserva> getReservas(){
		return dao.findAll();
	}
	
	public Reserva getReserva(Integer id){
		return dao.findById(id).orElse(null);
	}
	
	public List<Reserva> getReservaViaje(Integer idViaje){
        return dao.getReservasViaje(idViaje);
	}
	
	public Reserva añadirReserva(Reserva r){
		if(r.getFFin()==null) {
			r.setFFin(r.getFInicio());
		}
		return dao.save(r);
	}
	
	public void eliminarReserva(Integer id){
		dao.deleteById(id);
	}
	
	public ReservaDTO getReservasFecha(Date fInicial, Date fFinal,Integer idUsuario,Integer pagina, Integer numPag){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(fFinal);
		calendar.add(Calendar.HOUR_OF_DAY, 23);
		calendar.add(Calendar.MINUTE, 59);
		fFinal=calendar.getTime();	
		ReservaDTO dto = new ReservaDTO();
        Pageable p = PageRequest.of(pagina-1,numPag);
        Page pg = dao.getReservasFecha(fInicial,fFinal,idUsuario,p);
        dto.setNumPagina(pagina);
        dto.setRegistros(pg.getTotalElements());
        dto.setTotalPaginas(pg.getTotalPages());
        dto.setReserva(pg.getContent());
        dto.setNumReservasPagina(numPag);
        return dto;		
	}
	
}
