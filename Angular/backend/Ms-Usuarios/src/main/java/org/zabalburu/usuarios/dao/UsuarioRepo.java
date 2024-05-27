package org.zabalburu.usuarios.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zabalburu.usuarios.modelo.Usuario;

public interface UsuarioRepo extends JpaRepository<Usuario, Integer> {

	@Query(value="Select u from Usuario u where u.usuario=:usuario and u.hash=:hash")
	public Usuario login(@Param("usuario") String usuario,@Param("hash") String hash);
	
	@Query(value="Select u from Usuario u where u.usuario=:usuario")
	public Usuario getUsuario(@Param("usuario") String usuario);
}
