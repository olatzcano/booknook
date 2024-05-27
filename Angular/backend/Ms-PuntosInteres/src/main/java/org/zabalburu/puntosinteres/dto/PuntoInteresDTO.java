package org.zabalburu.puntosinteres.dto;

import java.util.List;

import org.zabalburu.puntosinteres.modelo.PuntoInteres;

import lombok.Data;

@Data
public class PuntoInteresDTO {
	private Integer numPagina;
	  private Integer totalPaginas;
	  private Integer numPInteresPagina;
	  private Long registros;
	  private List<PuntoInteres> pInteres;
}
