package com.machinecafe.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.machinecafe.api.model.Boisson;
import com.machinecafe.api.repository.BoissonRepository;

import lombok.Data;

@Data
@Service
public class BoissonService {

	@Autowired
	private BoissonRepository BoissonRepository;

	public Optional<Boisson> getBoissons(final Long id) {
		return BoissonRepository.findById(id);
	}

	public Iterable<Boisson> getBoissonss() {
		return BoissonRepository.findAll();
	}

	public void deleteBoissons(final Long id) {
		BoissonRepository.deleteById(id);
	}

	public Boisson saveBoissons(Boisson Boisson) {
		Boisson savedBoissons = BoissonRepository.save(Boisson);
		return savedBoissons;
	}

}
