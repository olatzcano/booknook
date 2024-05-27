package org.zabalburu.puntosinteres.modelo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name="puntosdeinteres")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PuntoInteres {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name="idinteres")
	private Integer idInteres;
	@Column(name = "precio")
	private Double precio;
	@Column(name="idciudad")
	private Integer idCiudad;
	@Column(name="nombre")
	private String nombre;
	private String imagen;
	private String descripcion;
}
