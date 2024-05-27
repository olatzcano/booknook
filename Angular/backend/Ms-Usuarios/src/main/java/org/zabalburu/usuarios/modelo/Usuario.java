package org.zabalburu.usuarios.modelo;

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
@Table(name="usuarios")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column (name = "idusuario")
	private Integer idUsuario;
	
	private String usuario;
	
	@JsonIgnore
	private String hash;
	
	@JsonIgnore
	private String salto;
	
	private String direccion;
	
	private String nombre;
	
	private String apellido;
	
	private String telefono;
	
	private String email;
	
	private Boolean admin;
	
	@JsonIgnore
	@OneToMany(mappedBy = "usuario")
	private List<Viaje> viajes;
}