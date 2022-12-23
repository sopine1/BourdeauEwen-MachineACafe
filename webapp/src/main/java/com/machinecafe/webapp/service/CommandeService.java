package com.machinecafe.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.machinecafe.webapp.model.Boisson;
import com.machinecafe.webapp.model.Commande;
import com.machinecafe.webapp.model.Supplement;
import com.machinecafe.webapp.repository.BoissonProxy;
import com.machinecafe.webapp.repository.CommandeProxy;
import com.machinecafe.webapp.repository.SupplementProxy;

import lombok.Data;

@Data
@Service
public class CommandeService {
	
	@Autowired
	private CommandeProxy commandeProxy;
	
	@Autowired
	private BoissonProxy boissonProxy;
	
	@Autowired
	private SupplementProxy supplementProxy;
	
	public Commande getCommande(final int id) {
		return commandeProxy.getCommande(id);
	}
	
	public Iterable<Commande> getCommandes() {
		return commandeProxy.getCommandes();
	}
	
	public void deleteCommande(final int id) {
		commandeProxy.deleteCommande(id);
	}
	
	public Commande saveCommande(Commande commande) {
		Commande savedCommande;
		
		if(commande.getId() == null) {
			savedCommande = commandeProxy.createCommande(commande);
			
			Boisson b = boissonProxy.getBoisson(commande.getIdBoisson().intValue());
			b.setQuantity(b.getQuantity()-1);
			boissonProxy.updateBoisson(b);
			
			Supplement s;
			for (Long id : commande.getIdSupplements()) {
				s = supplementProxy.getSupplement(id.intValue());
				s.setQuantity(s.getQuantity()-1);
				supplementProxy.updateSupplement(s);
			}
		} else {
			savedCommande = commandeProxy.updateCommande(commande);
		}
		
		return savedCommande;
	}

}
