package com.tulian.springboot.apirest.app.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tulian.springboot.apirest.app.models.entity.Region;

public interface IRegionDao extends JpaRepository<Region, Long> {

}
