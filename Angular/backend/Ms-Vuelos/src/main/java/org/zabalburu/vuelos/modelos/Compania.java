package org.zabalburu.vuelos.modelos;

import java.util.Date;
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
@Table(name="compania")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Compania {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name="idcompania")
	private Integer idCompania;
	@Column(name="nombre")
	private String nombre;
	
	@JsonIgnore
	@OneToMany(mappedBy = "compania")
	private List<Vuelo> vuelos;

}
