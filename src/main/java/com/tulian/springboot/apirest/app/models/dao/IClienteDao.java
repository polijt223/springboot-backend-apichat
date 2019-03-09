package com.tulian.springboot.apirest.app.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tulian.springboot.apirest.app.models.entity.Cliente;

public interface IClienteDao extends JpaRepository<Cliente, Long> {

}
