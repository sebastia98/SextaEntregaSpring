package org.formacio.repositori;

import java.util.List;

import org.formacio.domain.Client;
import org.formacio.domain.Factura;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface FacturesRepositori extends CrudRepository<Factura, Long>{
	
	
	/*
	 * Hasta que no escribo la consulta la aplicación no detecta que esto es un repositorio y falla todo.
	 */
	@Query("SELECT sum(linfact.total) FROM Factura fact JOIN fact.linies linfact where fact.client.nom = ?1")
	public Number totalClient(String client);
	
	public List<Factura> findByClientNom(String nom);
	
	
	
	
	
	
}
