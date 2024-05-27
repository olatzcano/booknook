package org.zabalburu.usuarios.dto;

import java.util.List;

import org.zabalburu.usuarios.modelo.Reserva;

import lombok.Data;

@Data
public class ReservaDTO {
	  private Integer numPagina;
	  private Integer totalPaginas;
	  private Integer numReservasPagina;
	  private Long registros;
	  private List<Reserva> reserva;
}
