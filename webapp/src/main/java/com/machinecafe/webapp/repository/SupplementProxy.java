package com.machinecafe.webapp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.machinecafe.webapp.model.Supplement;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SupplementProxy {


	/**
	 * Get all supplements
	 * @return An iterable of all supplements
	 */
	public Iterable<Supplement> getSupplements() {

		String baseApiUrl = "http://localhost:9000";
		String getSupplementsUrl = baseApiUrl + "/supplements";

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Iterable<Supplement>> response = restTemplate.exchange(
				getSupplementsUrl, 
				HttpMethod.GET, 
				null,
				new ParameterizedTypeReference<Iterable<Supplement>>() {}
			);
		
		
		return response.getBody();
	}
	
	/**
	 * Get an supplement by the id
	 * @param id The id of the supplement
	 * @return The supplement which matches the id
	 */
	public Supplement getSupplement(int id) {
		String baseApiUrl = "http://localhost:9000";
		String getSupplementUrl = baseApiUrl + "/supplement/" + id;

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Supplement> response = restTemplate.exchange(
				getSupplementUrl, 
				HttpMethod.GET, 
				null,
				Supplement.class
			);
		
		
		return response.getBody();
	}
	
	/**
	 * Add a new supplement 
	 * @param e A new supplement (without an id)
	 * @return The supplement full filled (with an id)
	 */
	public Supplement createSupplement(Supplement e) {
		String baseApiUrl = "http://localhost:9000";
		String createSupplementUrl = baseApiUrl + "/supplement";
		
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Supplement> request = new HttpEntity<Supplement>(e);
		ResponseEntity<Supplement> response = restTemplate.exchange(
				createSupplementUrl, 
				HttpMethod.POST, 
				request, 
				Supplement.class);
		
		
		return response.getBody();
	}
	
	/**
	 * Update an supplement - using the PUT HTTP Method.
	 * @param e Existing supplement to update
	 */
	public Supplement updateSupplement(Supplement e) {
		String baseApiUrl = "http://localhost:9000";
		String updateSupplementUrl = baseApiUrl + "/supplement/" + e.getId();

		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Supplement> request = new HttpEntity<Supplement>(e);
		ResponseEntity<Supplement> response = restTemplate.exchange(
				updateSupplementUrl, 
				HttpMethod.PUT, 
				request, 
				Supplement.class);
		
		
		return response.getBody();
	}
	
	/**
	 * Delete an supplement using exchange method of RestTemplate
	 * instead of delete method in order to log the response status code.
	 * @param e The supplement to delete
	 */
	public void deleteSupplement(int id) {
		String baseApiUrl = "http://localhost:9000";
		String deleteSupplementUrl = baseApiUrl + "/supplement/" + id;
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Void> response = restTemplate.exchange(
				deleteSupplementUrl, 
				HttpMethod.DELETE, 
				null, 
				Void.class);
		
	}

}
