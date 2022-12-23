package com.machinecafe.api.controller;

import java.util.List;
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
import com.machinecafe.api.model.Commande;
import com.machinecafe.api.model.Supplement;
import com.machinecafe.api.service.CommandeService;

@RestController
public class CommandeController {

	@Autowired
	private CommandeService CommandeService;

	/**
	 * Create - Add a new Commande
	 * @param Commande An object Commande
	 * @return The Commande object saved
	 */
	@PostMapping("/commande")
	public Commande createCommandes(@RequestBody Commande Commande) {
		return CommandeService.saveCommandes(Commande);
	}


	/**
	 * Read - Get one Commande
	 * @param id The id of the Commande
	 * @return An Commande object full filled
	 */
	@GetMapping("/commande/{id}")
	public Commande getCommandes(@PathVariable("id") final Long id) {
		Optional<Commande> Commande = CommandeService.getCommandes(id);
		if(Commande.isPresent()) {
			return Commande.get();
		} else {
			return null;
		}
	}

	/**
	 * Read - Get all Commandess
	 * @return - An Iterable object of Commande full filled
	 */
	@GetMapping("/commandes")
	public Iterable<Commande> getCommandess() {
		return CommandeService.getCommandes();
	}

	/**
	 * Update - Update an existing Commande
	 * @param id - The id of the Commande to update
	 * @param Commande - The Commande object updated
	 * @return
	 */
	@PutMapping("/commande/{id}")
	public Commande updateCommandes(@PathVariable("id") final Long id, @RequestBody Commande Commande) {
		Optional<Commande> e = CommandeService.getCommandes(id);
		if(e.isPresent()) {
			Commande currentCommandes = e.get();

			Boisson boisson= Commande.getBoisson();
			if(boisson != null) {
				currentCommandes.setBoisson(boisson);
			}
			List<Supplement> supplement= Commande.getSupplement();
			if(supplement != null) {
				currentCommandes.setSupplement(supplement);
			}
			
			Double totalPrice= Commande.getTotalPrice();
			if(totalPrice != null) {
				currentCommandes.setTotalPrice(totalPrice);
			}
			
			CommandeService.saveCommandes(currentCommandes);
			return currentCommandes;
		} else {
			return null;
		}
	}


	/**
	 * Delete - Delete an Commande
	 * @param id - The id of the Commande to delete
	 */
	@DeleteMapping("/commande/{id}")
	public void deleteCommandes(@PathVariable("id") final Long id) {
		CommandeService.deleteCommandes(id);
	}


}
