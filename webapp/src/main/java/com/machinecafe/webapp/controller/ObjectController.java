package com.machinecafe.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.machinecafe.webapp.model.Boisson;
import com.machinecafe.webapp.model.Commande;
import com.machinecafe.webapp.model.Supplement;
import com.machinecafe.webapp.service.BoissonService;
import com.machinecafe.webapp.service.CommandeService;
import com.machinecafe.webapp.service.SupplementService;

import lombok.Data;

@Data
@Controller
public class ObjectController {

	@Autowired
	private BoissonService boissonService;
	
	@Autowired
	private SupplementService supplementService;
	
	@Autowired
	private CommandeService commandeService;
	
	@GetMapping("/admin")
	public String admin(Model model) {
		Iterable<Boisson> listBoisson = boissonService.getBoissons();
		model.addAttribute("boissons", listBoisson);
		Iterable<Supplement> listSupplement = supplementService.getSupplements();
		model.addAttribute("supplements", listSupplement);
		return "admin";
	}
	
	@GetMapping("/")
	public String home(Model model) {
		Iterable<Boisson> listBoisson = boissonService.getBoissons();
		model.addAttribute("boissons", listBoisson);
		Iterable<Supplement> listSupplement = supplementService.getSupplements();
		model.addAttribute("supplements", listSupplement);
		return "home";
	}
	
	@GetMapping("/{time}")
	public String homeTime(@PathVariable("time") final int time, Model model) {
		Iterable<Boisson> listBoisson = boissonService.getBoissons();
		model.addAttribute("boissons", listBoisson);
		Iterable<Supplement> listSupplement = supplementService.getSupplements();
		model.addAttribute("supplements", listSupplement);
		model.addAttribute("timeRemaining", time);
		return "home";		
	}
	
	@GetMapping("/showCommande")
	public String showCommande(Model model) {
		Double totalCommandes = 0.0;
		Iterable<Commande> listCommande = commandeService.getCommandes();
		model.addAttribute("commandes", listCommande);
		for (Commande commande : listCommande) {
			totalCommandes += commande.getTotalPrice();
		}
		model.addAttribute("totalCommandes", totalCommandes);
		return "showCommande";
	}
	
	@GetMapping("/addBoisson")
	public String createBoisson(Model model) {
		Boisson e = new Boisson();
		model.addAttribute("boisson", e);
		return "formNewBoisson";
	}
	
	@GetMapping("/updateBoisson/{id}")
	public String updateBoisson(@PathVariable("id") final int id, Model model) {
		Boisson e = boissonService.getBoisson(id);		
		model.addAttribute("boisson", e);	
		return "formUpdateBoisson";		
	}
	
	@GetMapping("/deleteBoisson/{id}")
	public ModelAndView deleteBoisson(@PathVariable("id") final int id) {
		boissonService.deleteBoisson(id);
		return new ModelAndView("redirect:/");		
	}
	
	@PostMapping("/saveBoisson")
	public ModelAndView saveBoisson(@ModelAttribute Boisson boisson) {

		boissonService.saveBoisson(boisson);
		return new ModelAndView("redirect:/");	
	}
	
	@GetMapping("/addSupplement")
	public String createSupplement(Model model) {
	  Supplement e = new Supplement();
	  model.addAttribute("supplement", e);
	  return "formNewSupplement";
	}

	@GetMapping("/updateSupplement/{id}")
	public String updateSupplement(@PathVariable("id") final int id, Model model) {
	  Supplement e = supplementService.getSupplement(id);		
	  model.addAttribute("supplement", e);	
	  return "formUpdateSupplement";		
	}

	@GetMapping("/deleteSupplement/{id}")
	public ModelAndView deleteSupplement(@PathVariable("id") final int id) {
	  supplementService.deleteSupplement(id);
	  return new ModelAndView("redirect:/");		
	}

	@PostMapping("/saveSupplement")
	public ModelAndView saveSupplement(@ModelAttribute Supplement supplement) {

	  supplementService.saveSupplement(supplement);
	  return new ModelAndView("redirect:/");	
	}
	
	@GetMapping("/addCommande")
	public String createCommande(Model model) {
		Iterable<Boisson> listBoisson = boissonService.getBoissons();
		model.addAttribute("boissons", listBoisson);
		Iterable<Supplement> listSupplement = supplementService.getSupplements();
		model.addAttribute("supplements", listSupplement);
		Commande e = new Commande();
		model.addAttribute("commande", e);
		return "formNewCommande";
	}

	@GetMapping("/updateCommande/{id}")
	public String updateCommande(@PathVariable("id") final int id, Model model) {
	  Commande e = commandeService.getCommande(id);		
	  model.addAttribute("commande", e);	
	  return "formUpdateCommande";		
	}

	@GetMapping("/deleteCommande/{id}")
	public ModelAndView deleteCommande(@PathVariable("id") final int id) {
	  commandeService.deleteCommande(id);
	  return new ModelAndView("redirect:/");		
	}

	@PostMapping("/saveCommande")
	public ModelAndView saveCommande(@ModelAttribute Commande commande) {
	  commandeService.saveCommande(commande);
	  Integer time = boissonService.getBoisson(commande.getIdBoisson().intValue()).getTime();
	  return new ModelAndView("redirect:/"+time);	
	}
	
}