package dev.api.listener;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import dev.api.entite.Collaborateur;
import dev.api.entite.Compte;
import dev.api.entite.Departement;
import dev.api.repository.CollaborateurRepository;
import dev.api.repository.CompteRepository;
import dev.api.repository.DepartementRepository;

@Component
public class StartUpAppListener {

	@Autowired
	private CollaborateurRepository cr;
	@Autowired
	private CompteRepository cptr;
	@Autowired
	private DepartementRepository dr;

	@EventListener(ContextRefreshedEvent.class)
	@Transactional
	public void contextRefreshedEvent() {
		ClassPathXmlApplicationContext contextData = new ClassPathXmlApplicationContext("departements.xml",
				"comptes.xml", "collaborateurs.xml");
		Collection<Departement> departements = (Collection<Departement>) contextData.getBeansOfType(Departement.class)
				.values();

		Collection<Compte> comptes = contextData.getBeansOfType(Compte.class).values();

		Collection<Collaborateur> collaborateurs = contextData.getBeansOfType(Collaborateur.class).values();

		dr.save(departements);
		cptr.save(comptes);
		cr.save(collaborateurs);

		contextData.close();
	}
}
