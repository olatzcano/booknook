package org.zabalburu.vuelos.dto;

import java.util.List;

import org.zabalburu.vuelos.modelos.Vuelo;

import lombok.Data;

@Data
public class VueloDTO {
	 private Integer numPagina;
	  private Integer totalPaginas;
	  private Integer numVuelosPag;
	  private Long registros;
	  private List<Vuelo> vuelos;
}
