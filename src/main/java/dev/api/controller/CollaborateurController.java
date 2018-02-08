package dev.api.controller;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dev.api.entite.Collaborateur;
import dev.api.entite.Compte;
import dev.api.repository.CollaborateurRepository;
import dev.api.repository.CompteRepository;
import dev.api.repository.DepartementRepository;

@Controller
public class CollaborateurController {

	@Autowired
	private CollaborateurRepository cr;
	@Autowired
	private DepartementRepository dr;
	@Autowired
	private CompteRepository cptr;

	@RequestMapping(value = "/api/collaborateurs", method = RequestMethod.GET)
	@ResponseBody
	public List<Collaborateur> findCollabByDepartement(
			@RequestParam(value = "departement") Optional<Integer> departementId) {
		if (departementId.isPresent()) {
			return cr.findByDepartement(dr.findOne(departementId.get()));
		}
		return cr.findAll();
	}

	@RequestMapping(value = "/api/collaborateurs/{matricule}", method = RequestMethod.GET)
	@ResponseBody
	public Collaborateur findCollabByMatricule(@PathVariable String matricule) {
		return cr.findByMatricule(matricule);
	}

	@RequestMapping(value = "/api/collaborateurs/{matricule}", method = RequestMethod.PUT)
	@ResponseBody
	public Collaborateur editCollabByMatricule(@PathVariable String matricule, @RequestBody Collaborateur collab) {
		if (cr.findByMatricule(matricule) != null) {
			cr.save(collab);
		}
		return cr.findByMatricule(matricule);
	}

	@RequestMapping(value = "/api/collaborateurs/{matricule}/banque", method = RequestMethod.GET)
	@ResponseBody
	public Compte findBanqueByMatricule(@PathVariable String matricule) {
		return cr.findByMatricule(matricule).getCompte();
	}

	@RequestMapping(value = "/api/collaborateurs/{matricule}/banque", method = RequestMethod.PUT)
	@ResponseBody
	@Transactional
	public Compte editCompteByMatricule(@PathVariable String matricule, @RequestBody Compte compte) {
		Collaborateur collab = cr.findByMatricule(matricule);
		if (collab != null) {
			cptr.save(compte);
			collab.setCompte(compte);
			cr.save(collab);
		}
		return cr.findByMatricule(matricule).getCompte();
	}

}
