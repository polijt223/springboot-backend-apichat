package com.tulian.springboot.apirest.app.models.services;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tulian.springboot.apirest.app.models.dao.IRegionDao;
import com.tulian.springboot.apirest.app.models.entity.Region;

@Service
public class RegionServiceImpl implements IRegionService {

	@Autowired
	private IRegionDao regionDao; 
	
	@Override
	@Transactional(readOnly=true)
	public List<Region> findAll() {
		return (List<Region>) regionDao.findAll();
	}

}
