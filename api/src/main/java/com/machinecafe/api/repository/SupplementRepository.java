package com.machinecafe.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.machinecafe.api.model.Boisson;
import com.machinecafe.api.model.Supplement;

@Repository
public interface SupplementRepository extends CrudRepository<Supplement, Long>{

}
