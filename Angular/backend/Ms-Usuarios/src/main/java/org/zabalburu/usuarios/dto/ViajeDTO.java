package org.zabalburu.usuarios.dto;

import java.util.List;

import org.zabalburu.usuarios.modelo.Viaje;

import lombok.Data;

@Data
public class ViajeDTO {
	  private Integer numPagina;
	  private Integer totalPaginas;
	  private Integer numViajesPag;
	  private Long registros;
	  private List<Viaje> viajes;
}
