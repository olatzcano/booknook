package org.zabalburu.hoteles.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name="habitaciones")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Habitacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name = "idhabitacion")
	private Integer idHabitacion;
	@ManyToOne
	@JoinColumn(name = "idhotel")
	private Hotel hotel;
	@Column(name = "cantidad")
	private Integer cantidad;
	private String tipo;
	@Column(name = "precio")
	private Double precio;
	
	private String imagen;
	private String descripcion;

}
