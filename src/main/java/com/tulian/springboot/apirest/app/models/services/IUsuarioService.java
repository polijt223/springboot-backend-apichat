package com.tulian.springboot.apirest.app.models.services;

import com.tulian.springboot.apirest.app.models.entity.Usuario;

public interface IUsuarioService {

	public Usuario findByUsername(String username);
}
