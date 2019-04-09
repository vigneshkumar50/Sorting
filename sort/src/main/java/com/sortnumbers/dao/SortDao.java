package com.sortnumbers.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sortnumbers.entity.SortedEntity;

@Service
public class SortDao {

	@Autowired
	SortRepository sortRepo;

	/**
	 * Retrieve All Values from database
	 * 
	 * @return
	 */
	@Transactional
	public List<SortedEntity> getAllSortNumbers() {
		List<SortedEntity> entityResult = new ArrayList<SortedEntity>();
		sortRepo.findAll().
		forEach(resultRow -> entityResult
		.add(resultRow));
		return entityResult;
	}

	/**
	 * @param SortedEntity
	 */
	@Transactional
	public void saveOrUpdate(SortedEntity sortNumber) {
		sortRepo.save(sortNumber);
	}

}
