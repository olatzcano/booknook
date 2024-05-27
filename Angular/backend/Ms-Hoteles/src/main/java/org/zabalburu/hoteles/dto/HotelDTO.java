package org.zabalburu.hoteles.dto;

import java.util.List;

import org.zabalburu.hoteles.modelo.Hotel;

import lombok.Data;

@Data
public class HotelDTO {
	 private Integer numPagina;
	  private Integer totalPaginas;
	  private Integer numHotelesPag;
	  private Long registros;
	  private List<Hotel> hoteles;

}
