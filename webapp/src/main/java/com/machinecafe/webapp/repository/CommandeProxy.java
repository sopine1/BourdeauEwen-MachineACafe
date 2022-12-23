package com.machinecafe.webapp.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.machinecafe.webapp.model.Boisson;
import com.machinecafe.webapp.model.Commande;
import com.machinecafe.webapp.model.Supplement;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CommandeProxy {


	/**
	 * Get all commandes
	 * @return An iterable of all commandes
	 */
	
	@Autowired
	private BoissonProxy boissonProxy;
	
	@Autowired
	private SupplementProxy supplementProxy;
	
	public Iterable<Commande> getCommandes() {

		String baseApiUrl = "http://localhost:9000";
		String getCommandesUrl = baseApiUrl + "/commandes";

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Iterable<Commande>> response = restTemplate.exchange(
				getCommandesUrl, 
				HttpMethod.GET, 
				null,
				new ParameterizedTypeReference<Iterable<Commande>>() {}
			);
		
		
		return response.getBody();
	}
	
	/**
	 * Get an commande by the id
	 * @param id The id of the commande
	 * @return The commande which matches the id
	 */
	public Commande getCommande(int id) {
		String baseApiUrl = "http://localhost:9000";
		String getCommandeUrl = baseApiUrl + "/commande/" + id;

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Commande> response = restTemplate.exchange(
				getCommandeUrl, 
				HttpMethod.GET, 
				null,
				Commande.class
			);
		
		
		return response.getBody();
	}
	
	/**
	 * Add a new commande 
	 * @param e A new commande (without an id)
	 * @return The commande full filled (with an id)
	 */
	public Commande createCommande(Commande e) {
		String baseApiUrl = "http://localhost:9000";
		String createCommandeUrl = baseApiUrl + "/commande";
		String updateBoissonUrl = baseApiUrl + "/boisson";
		
		Double totalPrice = 0.0;
		
		Boisson b = new Boisson();
		b.setId(e.getIdBoisson());
		e.setBoisson(b);
		
		

		List<Long> idsSupplements = e.getIdSupplements();
		if(!idsSupplements.isEmpty()) {
			List<Supplement> supplements = new ArrayList<>();
			for (Long id : idsSupplements) {
				Supplement s = new Supplement();
				s.setId(id);
				supplements.add(s);
				totalPrice += 0.1;
			}
			e.setSupplement(supplements);
		}
		
		e.setTotalPrice(boissonProxy.getBoisson(e.getIdBoisson().intValue()).getPrice()+totalPrice);
		
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Commande> request = new HttpEntity<Commande>(e);
		ResponseEntity<Commande> response = restTemplate.exchange(
				createCommandeUrl, 
				HttpMethod.POST, 
				request, 
				Commande.class);
		
		return response.getBody();
	}
	
	/**
	 * Update an commande - using the PUT HTTP Method.
	 * @param e Existing commande to update
	 */
	public Commande updateCommande(Commande e) {
		String baseApiUrl = "http://localhost:9000";
		String updateCommandeUrl = baseApiUrl + "/commande/" + e.getId();

		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Commande> request = new HttpEntity<Commande>(e);
		ResponseEntity<Commande> response = restTemplate.exchange(
				updateCommandeUrl, 
				HttpMethod.PUT, 
				request, 
				Commande.class);
		
		
		return response.getBody();
	}
	
	/**
	 * Delete an commande using exchange method of RestTemplate
	 * instead of delete method in order to log the response status code.
	 * @param e The commande to delete
	 */
	public void deleteCommande(int id) {
		String baseApiUrl = "http://localhost:9000";
		String deleteCommandeUrl = baseApiUrl + "/commande/" + id;
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Void> response = restTemplate.exchange(
				deleteCommandeUrl, 
				HttpMethod.DELETE, 
				null, 
				Void.class);
		
	}

}
