package dev.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.api.entite.Compte;
import dev.api.repository.CompteRepository;

@RestController
@RequestMapping("/api/comptes")
public class CompteController {

	@Autowired
	private CompteRepository cptr;

	@GetMapping
	public List<Compte> listerComptes() {
		return this.cptr.findAll();
	}

}
