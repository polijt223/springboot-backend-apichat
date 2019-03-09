package com.tulian.springboot.apirest.app.models.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tulian.springboot.apirest.app.models.dao.IClienteDao;
import com.tulian.springboot.apirest.app.models.entity.Cliente;

@Service
public class ClienteServiceImpl implements IClienteService {

	
	@Autowired
	private IClienteDao clienteDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		return (List<Cliente>) clienteDao.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<Cliente> findAll(Pageable pageable) {
		return clienteDao.findAll(pageable);
	}
	
	@Transactional(readOnly = true)
	@Override
	public Cliente findById(Long id) {
		if (clienteDao.findById(id).isPresent()) {
			return clienteDao.findById(id).get();
		}
		return null;
	}

	@Transactional
	@Override
	public Cliente save(Cliente cliente) {
		// TODO Auto-generated method stub
		return clienteDao.save(cliente);
	}

	@Transactional
	@Override
	public void delete(Long id) {
		clienteDao.deleteById(id);
		
	}

	

}
