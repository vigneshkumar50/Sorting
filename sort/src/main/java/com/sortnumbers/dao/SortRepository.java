package com.sortnumbers.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sortnumbers.entity.SortedEntity;

@Repository
public interface SortRepository extends CrudRepository<SortedEntity, Integer> {

}
