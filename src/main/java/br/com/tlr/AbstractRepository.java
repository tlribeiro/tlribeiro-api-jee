package br.com.tlr;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * Base para o reposit�rio.
 * 
 * @author Thiago Ribeiro
 * @since 31.07.2019
 * @param <T>
 *            Entidade para o reposit�rio.
 */
public abstract class AbstractRepository<T> {
	// Intância do entity manager.
	protected EntityManager entity;
	// Objeto da query.
	protected Query query;
	// String da consulta.
	protected String hql;
	// Classe da entidade mapeada.
	@SuppressWarnings("rawtypes")
	protected Class entityClass;
	// Nome da classe da entidade mapeada.
	protected String entityClassName;

	/**
	 * Construtor para receber o tipo da classe.
	 * 
	 * @param entityClass
	 *            Classe do reposit�rio.
	 */
	public AbstractRepository(Class<T> entityClass) {
		entityClassName = entityClass.getName();
		this.entityClass = entityClass;
	}

	/**
	 * Set para a cria��o do reposit�rio por inje��o de dep�ndencia.
	 * 
	 * @param em
	 *            EntityManager.
	 */
	@Inject
	public void setEntityManager(EntityManager em) {
		this.setEm(em);
	}

	/**
	 * Adiciona ou atualizada um objeto no banco de dados.
	 * 
	 * @param obj
	 *            Objeto para realizar a opera��o.
	 * @return Objecto atualizado depois de realizar a opera��o.
	 */
	public T save(T obj) {
		obj = this.entity.merge(obj);
		this.entity.flush(); // Sdicionado o flush.
		return obj;
	}

	/**
	 * Deleta o registro do banco de dados.
	 * 
	 * @param id
	 *            Identificador �nico do objeto.
	 * @return <b>True</b> caso delete com sucesso.
	 */
	public boolean delete(Long id) {
		boolean result = false;
		// Procura a entidade.
		T object = this.findById(id);
		// Valida se encontrou a entidade.
		if (object != null) {
			// Realiza a remoção.
			this.entity.remove(object);
			result = true;
		}
		return result;
	}

	/**
	 * Realiza a busca pelo id.
	 * 
	 * @param id
	 *            Identificador �nico da entidade.
	 * @return Objeto encontrado.
	 */
	@SuppressWarnings("unchecked")
	public T findById(Long id) {
		T result = null;
		try {
			this.hql = "select m from " + entityClassName + " as m where m.id = :id ";
			Query query = entity.createQuery(this.hql);
			query.setParameter("id", id);
			List<T> resultList = (List<T>) query.getResultList();
			// Valida se encontrou resultado.
			if (resultList != null && !resultList.isEmpty()) {
				// Pega o primeiro item da lista.
				result = resultList.get(0);
			}
		} catch (Exception e) {
			System.out.println("Erro" + e.getMessage());
		}
		return result;
	}

	/**
	 * Retorna a lista de objetos.
	 * 
	 * @return Lista de objetos.
	 */
	@SuppressWarnings("unchecked")
	public List<T> getList() {
		this.hql = "select m from " + entityClassName + " as m ";
		List<T> resultSet = entity.createQuery(this.hql).getResultList();
		return resultSet;
	}

	/**
	 * Cria a instância da consulta.
	 * 
	 * @param jpql
	 *            JPQL da consulta.
	 * @return Instância da query.
	 */
	protected Query createQuery(String jpql) {
		return this.entity.createQuery(jpql);
	}

	/**
	 * Cria uma consulta tipada.
	 * 
	 * @param jpql
	 *            JPQL da consulta.
	 * @return Inst�ncia da query tipada.
	 */
	@SuppressWarnings("unchecked")
	protected TypedQuery<T> createTypeQuery(String jpql) {
		return this.entity.createQuery(jpql, entityClass);
	}

	/**
	 * Define o entity manager.
	 * 
	 * @param em
	 *            Entity manager.
	 */
	public void setEm(EntityManager em) {
		this.entity = em;
	}

	/**
	 * Retorna o entity manager.
	 * 
	 * @return Entity manager.
	 */
	public EntityManager getEM() {
		return this.entity;
	}
}
