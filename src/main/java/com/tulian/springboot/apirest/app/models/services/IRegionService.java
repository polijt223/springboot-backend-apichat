package com.tulian.springboot.apirest.app.models.services;

import java.util.List;
import com.tulian.springboot.apirest.app.models.entity.Region;

public interface IRegionService {
	
	public List<Region> findAll();
	
}
