package com.machinecafe.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.machinecafe.webapp.model.Boisson;
import com.machinecafe.webapp.repository.BoissonProxy;

import lombok.Data;

@Data
@Service
public class BoissonService {
	
	@Autowired
	private BoissonProxy boissonProxy;
	
	public Boisson getBoisson(final int id) {
		return boissonProxy.getBoisson(id);
	}
	
	public Iterable<Boisson> getBoissons() {
		return boissonProxy.getBoissons();
	}
	
	public void deleteBoisson(final int id) {
		boissonProxy.deleteBoisson(id);
	}
	
	public Boisson saveBoisson(Boisson boisson) {
		Boisson savedBoisson;
		


		if(boisson.getId() == null) {
			// If id is null, then it is a new boisson.
			savedBoisson = boissonProxy.createBoisson(boisson);
		} else {
			savedBoisson = boissonProxy.updateBoisson(boisson);
		}
		
		return savedBoisson;
	}

}
