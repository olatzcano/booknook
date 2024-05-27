package org.zabalburu.vuelos.modelos;

import java.util.Date;

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
@Table(name="vuelos")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Vuelo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name="idvuelo")
	private Integer idVuelo;
	@Column(name="fecha")
	private Date fecha;
	@Column(name = "precio")
	private Double precio;
	@JsonIgnore
	private String empresa;
	@ManyToOne
	@JoinColumn(name = "idorigen")
	private Aeropuerto origen;
	@ManyToOne
	@JoinColumn(name = "iddestino")
	private Aeropuerto destino;
	
	@ManyToOne
	@JoinColumn(name = "idcompania")
	private Compania compania;
	@Column(name="numpasajeros")
	private Integer numPasajeros;
}
