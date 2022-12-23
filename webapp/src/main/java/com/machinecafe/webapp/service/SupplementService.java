package com.machinecafe.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.machinecafe.webapp.model.Supplement;
import com.machinecafe.webapp.repository.SupplementProxy;

import lombok.Data;

@Data
@Service
public class SupplementService {
	
	@Autowired
	private SupplementProxy supplementProxy;
	
	public Supplement getSupplement(final int id) {
		return supplementProxy.getSupplement(id);
	}
	
	public Iterable<Supplement> getSupplements() {
		return supplementProxy.getSupplements();
	}
	
	public void deleteSupplement(final int id) {
		supplementProxy.deleteSupplement(id);
	}
	
	public Supplement saveSupplement(Supplement supplement) {
		Supplement savedSupplement;
		
		// Functional rule : Last name must be capitalized.
//		supplement.setLastName(supplement.getLastName().toUpperCase());

		if(supplement.getId() == null) {
			// If id is null, then it is a new supplement.
			savedSupplement = supplementProxy.createSupplement(supplement);
		} else {
			savedSupplement = supplementProxy.updateSupplement(supplement);
		}
		
		return savedSupplement;
	}

}
