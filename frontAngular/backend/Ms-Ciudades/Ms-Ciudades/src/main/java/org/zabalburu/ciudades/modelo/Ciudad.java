package org.zabalburu.ciudades.modelo;

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
@Table(name="ciudades")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Ciudad {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name ="idciudad")
	private Integer idCiudad;
	
	private String ciudad;
	
	private String descripcion;
	private String imagen;
	
}
