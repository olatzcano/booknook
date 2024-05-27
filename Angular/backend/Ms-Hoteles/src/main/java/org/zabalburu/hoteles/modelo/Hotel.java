package org.zabalburu.hoteles.modelo;

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
@Table(name="hoteles")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Hotel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name = "idhotel")
	private Integer idHotel;
	private String nombre;
	private Integer estrellas;
	@Column(name = "idciudad")
	private Integer idCiudad;
	
	private String imagen;
	private String descripcion;
	@JsonIgnore
	@OneToMany(mappedBy = "hotel")
	private List<Habitacion> habitaciones;
}
