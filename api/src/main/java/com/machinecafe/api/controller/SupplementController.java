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

import com.machinecafe.api.model.Supplement;
import com.machinecafe.api.service.SupplementService;

@RestController
public class SupplementController {

	@Autowired
	private SupplementService SupplementService;

	/**
	 * Create - Add a new Supplement
	 * @param Supplement An object Supplement
	 * @return The Supplement object saved
	 */
	@PostMapping("/supplement")
	public Supplement createSupplements(@RequestBody Supplement Supplement) {
		return SupplementService.saveSupplements(Supplement);
	}


	/**
	 * Read - Get one Supplement
	 * @param id The id of the Supplement
	 * @return An Supplement object full filled
	 */
	@GetMapping("/supplement/{id}")
	public Supplement getSupplements(@PathVariable("id") final Long id) {
		Optional<Supplement> Supplement = SupplementService.getSupplements(id);
		if(Supplement.isPresent()) {
			return Supplement.get();
		} else {
			return null;
		}
	}

	/**
	 * Read - Get all Supplementss
	 * @return - An Iterable object of Supplement full filled
	 */
	@GetMapping("/supplements")
	public Iterable<Supplement> getSupplements() {
		return SupplementService.getSupplements();
	}

	/**
	 * Update - Update an existing Supplement
	 * @param id - The id of the Supplement to update
	 * @param Supplement - The Supplement object updated
	 * @return
	 */
	@PutMapping("/supplement/{id}")
	public Supplement updateSupplements(@PathVariable("id") final Long id, @RequestBody Supplement Supplement) {
		Optional<Supplement> e = SupplementService.getSupplements(id);
		if(e.isPresent()) {
			Supplement currentSupplements = e.get();

			String name = Supplement.getName();
			if(name != null) {
				currentSupplements.setName(name);
			}
			Long quantity = Supplement.getQuantity();
			if(quantity != null) {
				currentSupplements.setQuantity(quantity);
			}
			SupplementService.saveSupplements(currentSupplements);
			return currentSupplements;
		} else {
			return null;
		}
	}


	/**
	 * Delete - Delete an Supplement
	 * @param id - The id of the Supplement to delete
	 */
	@DeleteMapping("/supplement/{id}")
	public void deleteSupplement(@PathVariable("id") final Long id) {
		SupplementService.deleteSupplement(id);
	}


}
