package com.tulian.springboot.apirest.app.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tulian.springboot.apirest.app.models.entity.Usuario;

public interface IUsuarioDao extends JpaRepository<Usuario, Long> {

	
	public Usuario findByUsername(String username);
	
	@Query("select u from Usuario u where u.username=?1")
	public Usuario BuscarPorUsuario(String username);
	
	
}
