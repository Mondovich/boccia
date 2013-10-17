package com.nttdata.boccia.model.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.nttdata.boccia.em.EMF;

public class GenericDaoImpl<T, ID extends Serializable> implements
		GenericDao<T, ID> {

	private final Class<T> persistentClass;
	private final EntityManagerFactory emf = EMF.get();

	@SuppressWarnings("unchecked")
	public GenericDaoImpl() {
		persistentClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		EntityManager em = null;

		try {
			em = emf.createEntityManager();
			Query query = em.createQuery("select from " + persistentClass.getSimpleName() + " t");
			return query.getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return new ArrayList<T>();
	}

	@Override
	public T findById(ID id) {
		T result = null;

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			result = em.find(persistentClass, id);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
		}

		return result;
	}

	@Override
	public void save(T entity) {
		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			em.persist(entity);
			em.flush();
			tx.commit();
		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
		}
	}

	@Override
	public void update(T entity) {
		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			em.merge(entity);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
		}
	}

	@Override
	public void delete(T entity) {
		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			em.remove(entity);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
		}
	}

}
