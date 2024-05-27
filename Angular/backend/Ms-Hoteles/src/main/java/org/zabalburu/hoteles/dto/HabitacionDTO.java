package org.zabalburu.hoteles.dto;

import java.util.List;

import org.zabalburu.hoteles.modelo.Habitacion;

import lombok.Data;
@Data
public class HabitacionDTO {
	  private Integer numPagina;
	  private Integer totalPaginas;
	  private Integer numHabitacionesPag;
	  private Long registros;
	  private List<Habitacion> habitaciones;

}
