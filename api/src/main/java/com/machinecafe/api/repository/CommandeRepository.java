package com.machinecafe.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.machinecafe.api.model.Commande;

@Repository
public interface CommandeRepository extends CrudRepository<Commande, Long>{

}
