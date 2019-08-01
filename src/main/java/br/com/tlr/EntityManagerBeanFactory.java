package br.com.tlr;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 * Fabrica de entity manager.
 * 
 * @author Thiago Ribeiro
 * @since 31.07.2019 22:46
 */
@ApplicationScoped
public class EntityManagerBeanFactory {

	@PersistenceUnit(unitName = "br.com.tlr")
	private EntityManagerFactory guiaEntityManagerFactory;

	/**
	 * Produtor do entity manager.
	 *
	 * @return Entity mananger.
	 */
	@RequestScoped
	@Produces
	public EntityManager getEm() {
		return guiaEntityManagerFactory.createEntityManager();
	}

	/**
	 * Fecha o entitymanager.
	 * 
	 * @param em
	 *            Instancia entitymananger
	 */
	public void closeEm(@Disposes EntityManager em) {
		// Encerra a conexão.
		if (em != null && em.isOpen()) {
			em.close();
		}
	}

}