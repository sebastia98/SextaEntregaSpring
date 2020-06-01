package org.formacio.servei;

import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.formacio.domain.Factura;
import org.formacio.domain.LiniaFactura;
import org.formacio.repositori.FacturesRepositori;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional // Siempre que hay un componente Servicio debe suponer una transacción
public class FacturesService {
	
	@Autowired
	private FacturesRepositori repo;
	
	@Autowired
	private FidalitzacioService serv;

	
	/*
	 * Aquest metode ha de carregar la factura amb id idFactura i afegir una nova linia amb les dades
	 * passades (producte i totalProducte)
	 * 
	 * S'ha de retornar la factura modificada
	 * 
	 * Per implementar aquest metode necessitareu una referencia (dependencia) a FacturesRepositori
	 */
	public Factura afegirProducte (long idFactura, String producte, int totalProducte) {
		
		Optional<Factura> fact = repo.findById(idFactura);
		if (fact.isPresent()) {
			
			LiniaFactura linia = new LiniaFactura();
			
			linia.setProducte(producte);
			linia.setTotal(totalProducte);
			
			fact.get().getLinies().add(linia);
			
			notifica(fact.get());
			
			repo.save(fact.get());
		}
		
		return fact.get();
	}
	
	public void notifica(Factura fact) {
		if (fact.getLinies().size() >= 4) {
			serv.notificaRegal(fact.getClient().getEmail());
		}
	}
}
