package model.dao;

import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.utils.EntityManagerFactoryUtils;

public class GenericDAOHibernateJPA<T> implements GenericDAO<T> {

	protected Class<T> persistentClass;

	public GenericDAOHibernateJPA(Class<T> clazz) {
		persistentClass = clazz;
	}

	@Override
	public T create(T entity) {
		EntityManager entityManager = EntityManagerFactoryUtils.getEntityManagerFactory().createEntityManager();
		EntityTransaction entityTransaction = null;
		try {
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			entityManager.persist(entity);
			entityTransaction.commit();
		}
		catch (RuntimeException e) {
			if (entityTransaction != null && entityTransaction.isActive()) entityTransaction.rollback();
			throw e;
		}
		finally {
			entityManager.close();
		}
		return entity;
	}

	@Override
	public T retrieve(Serializable serializable) {
		EntityManager entityManager = EntityManagerFactoryUtils.getEntityManagerFactory().createEntityManager();
		T entity = entityManager.find(persistentClass, serializable);
		entityManager.close();
		return entity;
	}

	@Override
	public void delete(T entity) {
		EntityManager entityManager = EntityManagerFactoryUtils.getEntityManagerFactory().createEntityManager();
		EntityTransaction entityTransaction = null;
		try {
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			entityManager.remove(entity);
			entityTransaction.commit();
		}
		catch (RuntimeException e) {
			if (entityTransaction != null && entityTransaction.isActive()) entityTransaction.rollback();
			throw e;
		}
		finally {
			entityManager.close();
		}
	}

	@Override
	public T update(Serializable serializable, T entity) {
		EntityManager entityManager = EntityManagerFactoryUtils.getEntityManagerFactory().createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		T mergedEntity = entityManager.merge(entity);
		entityTransaction.commit();
		entityManager.close();
		return mergedEntity;
	}
}
