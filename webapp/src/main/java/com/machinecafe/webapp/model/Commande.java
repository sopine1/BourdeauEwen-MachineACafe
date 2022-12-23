package com.machinecafe.webapp.model;

import java.util.ArrayList;
import java.util.List;


import com.machinecafe.webapp.model.Boisson;
import com.machinecafe.webapp.model.Supplement;

import lombok.Data;

@Data
public class Commande {

	private Long id;

	private  Boisson boisson;
	
	private List<Supplement> supplement = new ArrayList<>();
	
	private List<Long> idSupplements = new ArrayList<>();
	
	private Double totalPrice;
	
	public List<Long> getIdSupplements() {
		return idSupplements;
	}

	public void setIdSupplements(List<Long> idSupplements) {
		this.idSupplements = idSupplements;
	}

	private Long idBoisson;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boisson getBoisson() {
		return boisson;
	}

	public void setBoisson(Boisson boisson) {
		this.boisson = boisson;
	}

	public List<Supplement> getSupplement() {
		return supplement;
	}

	public void setSupplement(List<Supplement> supplement) {
		this.supplement = supplement;
	}
	
	public void addSupplement(Supplement supplement) {
		this.supplement.add(supplement);
	}

	public Long getIdBoisson() {
		return idBoisson;
	}

	public void setIdBoisson(Long idBoisson) {
		this.idBoisson = idBoisson;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

}