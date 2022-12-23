package com.machinecafe.webapp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.machinecafe.webapp.model.Boisson;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BoissonProxy {


	/**
	 * Get all boissons
	 * @return An iterable of all boissons
	 */
	public Iterable<Boisson> getBoissons() {

		String baseApiUrl = "http://localhost:9000";
		String getBoissonsUrl = baseApiUrl + "/boissons";

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Iterable<Boisson>> response = restTemplate.exchange(
				getBoissonsUrl, 
				HttpMethod.GET, 
				null,
				new ParameterizedTypeReference<Iterable<Boisson>>() {}
			);
		
		
		return response.getBody();
	}
	
	/**
	 * Get an boisson by the id
	 * @param id The id of the boisson
	 * @return The boisson which matches the id
	 */
	public Boisson getBoisson(int id) {
		String baseApiUrl = "http://localhost:9000";
		String getBoissonUrl = baseApiUrl + "/boisson/" + id;

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Boisson> response = restTemplate.exchange(
				getBoissonUrl, 
				HttpMethod.GET, 
				null,
				Boisson.class
			);
		
		
		return response.getBody();
	}
	
	/**
	 * Add a new boisson 
	 * @param e A new boisson (without an id)
	 * @return The boisson full filled (with an id)
	 */
	public Boisson createBoisson(Boisson e) {
		String baseApiUrl = "http://localhost:9000";
		String createBoissonUrl = baseApiUrl + "/boisson";
		
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Boisson> request = new HttpEntity<Boisson>(e);
		ResponseEntity<Boisson> response = restTemplate.exchange(
				createBoissonUrl, 
				HttpMethod.POST, 
				request, 
				Boisson.class);
		
		
		return response.getBody();
	}
	
	/**
	 * Update an boisson - using the PUT HTTP Method.
	 * @param e Existing boisson to update
	 */
	public Boisson updateBoisson(Boisson e) {
		String baseApiUrl = "http://localhost:9000";
		String updateBoissonUrl = baseApiUrl + "/boisson/" + e.getId();

		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Boisson> request = new HttpEntity<Boisson>(e);
		ResponseEntity<Boisson> response = restTemplate.exchange(
				updateBoissonUrl, 
				HttpMethod.PUT, 
				request, 
				Boisson.class);
		
		
		return response.getBody();
	}
	
	/**
	 * Delete an boisson using exchange method of RestTemplate
	 * instead of delete method in order to log the response status code.
	 * @param e The boisson to delete
	 */
	public void deleteBoisson(int id) {
		String baseApiUrl = "http://localhost:9000";
		String deleteBoissonUrl = baseApiUrl + "/boisson/" + id;
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Void> response = restTemplate.exchange(
				deleteBoissonUrl, 
				HttpMethod.DELETE, 
				null, 
				Void.class);
		
	}

}
