package org.zabalburu.usuarios.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.zabalburu.usuarios.modelo.Usuario;

@Repository
public class UsuarioImplementation {

	@Autowired
	private UsuarioRepo dao;
	
	public List<Usuario> getUsuarios(){
		return dao.findAll();
	}
	
	public Usuario getUsuario(Integer id) {
		return dao.findById(id).orElse(null);	
	}
	
	public Usuario getUsuario(String usuario) {
		return dao.getUsuario(usuario);	
	}
	
	public Usuario login(String usuario, String hash) {
		return dao.login(usuario,hash);
	}
	
	public Usuario añadirUsuario(Usuario u){
		return dao.save(u);
	}
	public void eliminarUsuario(Integer id){
		dao.deleteById(id);
	}
}
