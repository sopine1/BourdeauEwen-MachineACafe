package com.machinecafe.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.machinecafe.api.model.Boisson;
import com.machinecafe.api.service.BoissonService;

@RestController
public class BoissonController {

	@Autowired
	private BoissonService BoissonService;

	/**
	 * Create - Add a new Boisson
	 * @param Boisson An object Boisson
	 * @return The Boisson object saved
	 */
	@PostMapping("/boisson")
	public Boisson createBoissons(@RequestBody Boisson Boisson) {
		return BoissonService.saveBoissons(Boisson);
	}


	/**
	 * Read - Get one Boisson
	 * @param id The id of the Boisson
	 * @return An Boisson object full filled
	 */
	@GetMapping("/boisson/{id}")
	public Boisson getBoissons(@PathVariable("id") final Long id) {
		Optional<Boisson> Boisson = BoissonService.getBoissons(id);
		if(Boisson.isPresent()) {
			return Boisson.get();
		} else {
			return null;
		}
	}

	/**
	 * Read - Get all Boissonss
	 * @return - An Iterable object of Boisson full filled
	 */
	@GetMapping("/boissons")
	public Iterable<Boisson> getBoissonss() {
		return BoissonService.getBoissonss();
	}

	/**
	 * Update - Update an existing Boisson
	 * @param id - The id of the Boisson to update
	 * @param Boisson - The Boisson object updated
	 * @return
	 */
	@PutMapping("/boisson/{id}")
	public Boisson updateBoissons(@PathVariable("id") final Long id, @RequestBody Boisson Boisson) {
		Optional<Boisson> e = BoissonService.getBoissons(id);
		if(e.isPresent()) {
			Boisson currentBoissons = e.get();

			String name = Boisson.getName();
			if(name != null) {
				currentBoissons.setName(name);
			}
			Double price = Boisson.getPrice();
			if(price != null) {
				currentBoissons.setPrice(price);;
			}
			Long quantity = Boisson.getQuantity();
			if(quantity != null) {
				currentBoissons.setQuantity(quantity);
			}
			
			Integer time = Boisson.getTime();
			if(time != null) {
				currentBoissons.setTime(time);
			}
			BoissonService.saveBoissons(currentBoissons);
			return currentBoissons;
		} else {
			return null;
		}
	}


	/**
	 * Delete - Delete an Boisson
	 * @param id - The id of the Boisson to delete
	 */
	@DeleteMapping("/boisson/{id}")
	public void deleteBoissons(@PathVariable("id") final Long id) {
		BoissonService.deleteBoissons(id);
	}


}
