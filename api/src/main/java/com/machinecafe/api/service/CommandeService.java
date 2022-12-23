package com.machinecafe.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.machinecafe.api.model.Commande;
import com.machinecafe.api.repository.CommandeRepository;

import lombok.Data;

@Data
@Service
public class CommandeService {

	@Autowired
	private CommandeRepository CommandeRepository;

	public Optional<Commande> getCommandes(final Long id) {
		return CommandeRepository.findById(id);
	}

	public Iterable<Commande> getCommandes() {
		return CommandeRepository.findAll();
	}

	public void deleteCommandes(final Long id) {
		CommandeRepository.deleteById(id);
	}

	public Commande saveCommandes(Commande Commande) {
		Commande savedCommandes = CommandeRepository.save(Commande);
		return savedCommandes;
	}

}
