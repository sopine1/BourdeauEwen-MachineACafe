package com.machinecafe.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.machinecafe.api.model.Supplement;
import com.machinecafe.api.repository.SupplementRepository;

import lombok.Data;

@Data
@Service
public class SupplementService {

	@Autowired
	private SupplementRepository SupplementRepository;

	public Optional<Supplement> getSupplements(final Long id) {
		return SupplementRepository.findById(id);
	}

	public Iterable<Supplement> getSupplements() {
		return SupplementRepository.findAll();
	}

	public void deleteSupplement(final Long id) {
		SupplementRepository.deleteById(id);
	}

	public Supplement saveSupplements(Supplement Supplement) {
		Supplement savedSupplements = SupplementRepository.save(Supplement);
		return savedSupplements;
	}

}
