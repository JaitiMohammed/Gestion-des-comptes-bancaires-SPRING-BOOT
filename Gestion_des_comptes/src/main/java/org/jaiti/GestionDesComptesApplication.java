package org.jaiti;

import java.util.Date;

import org.jaiti.dao.ClientRepository;
import org.jaiti.dao.CompteRepository;
import org.jaiti.dao.OperationRepository;
import org.jaiti.entities.Client;
import org.jaiti.entities.Compte;
import org.jaiti.entities.CompteCourant;
import org.jaiti.entities.CompteEpargne;
import org.jaiti.entities.Retrait;
import org.jaiti.entities.Versement;
import org.jaiti.metier.IBanqueMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GestionDesComptesApplication implements CommandLineRunner{

	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private CompteRepository compteRepository;
	@Autowired
	private OperationRepository operationRepository;
	@Autowired
	private IBanqueMetier banqueMetier;
	
	public static void main(String[] args) {
		SpringApplication.run(GestionDesComptesApplication.class, args);
		
//		ApplicationContext ctx = SpringApplication.run(VotreBanqueApplication.class, args);
//		ClientRepository clientRepository =  ctx.getBean(ClientRepository.class);
//		clientRepository.save(new Client("Hassan", "hassan@gmail.com"));
		
		
	}

	@Override
	public void run(String... arg0) throws Exception {	
		Client c1 = clientRepository.save(new Client("Hassan", "hassan@gmail.com"))	;	
		Client c2 = clientRepository.save(new Client("Rachid", "rachid@gmail.com"))	;
		Client client = clientRepository.save(new Client("Jaiti Mohammed", "jaitimohammed@yahoo.com"));
//		
		Compte cp1 = compteRepository.save(new CompteCourant("cp1", new Date(), 90000, c1, 6000));
		Compte cp2 = compteRepository.save(new CompteEpargne("cp2", new Date(), 6000, c2, 5.5));
		Compte D0192C11199C2005=compteRepository.save(new CompteCourant("D0192C11199C2005",new Date(),10000,client,7800));
//		
		operationRepository.save(new Versement(new Date(),9000 , cp1) );
		operationRepository.save(new Versement(new Date(),6000 , cp1) );
		operationRepository.save(new Versement(new Date(),2300 , cp1) );
		operationRepository.save(new Retrait(new Date(),9000 , cp1) );
//		
		operationRepository.save(new Versement(new Date(),2300 , cp2) );
		operationRepository.save(new Versement(new Date(),400 , cp2) );
		operationRepository.save(new Versement(new Date(),2300 , cp2) );
		operationRepository.save(new Retrait(new Date(),3000 , cp2) );
//
		operationRepository.save(new Versement(new Date(),8500 , D0192C11199C2005) );
		operationRepository.save(new Versement(new Date(),6070 , D0192C11199C2005) );
		operationRepository.save(new Versement(new Date(),2400 , D0192C11199C2005) );
		operationRepository.save(new Retrait(new Date(),1000 , D0192C11199C2005) );
//		
		banqueMetier.verser("cp1", 111111);
		banqueMetier.verser("D0192C11199C2005",2050);
	}
}
