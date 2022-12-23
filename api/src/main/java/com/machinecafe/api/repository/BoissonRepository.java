package com.machinecafe.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.machinecafe.api.model.Boisson;

@Repository
public interface BoissonRepository extends CrudRepository<Boisson, Long>{

}
