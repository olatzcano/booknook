package org.zabalburu.vuelos.modelos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name="aeropuertos")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Aeropuerto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name="idaeropuerto")
	private Integer idAeropuerto;
	@Column(name="nombre")
	private String nombre;
	@Column(name = "idciudad")
	private Integer idCiudad;
	
	@JsonIgnore
	@OneToMany(mappedBy = "origen")
	private List<Vuelo> origenes;
	
	@JsonIgnore
	@OneToMany(mappedBy = "destino")
	private List<Vuelo> destinos;
}
