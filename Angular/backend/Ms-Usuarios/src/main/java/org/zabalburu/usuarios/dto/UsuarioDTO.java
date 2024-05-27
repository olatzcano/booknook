package org.zabalburu.usuarios.dto;

import lombok.Data;

@Data
public class UsuarioDTO {

	private String password;
	private String usuario;
	private String telefono;
	private String email;
	private String direccion;
	private String nombre;
	private String apellido;

}
