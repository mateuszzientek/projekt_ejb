package com.jsf.dao;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import jsfcourse.entities.Status;
import jsfcourse.entities.User;

//DAO - Data Access Object for Person entity
//Designed to serve as an interface between higher layers of application and data.
//Implemented as stateless Enterprise Java bean - server side code that can be invoked even remotely.

@Stateless
public class StatusDAO {
	private final static String UNIT_NAME = "jsfcourseSklep";

	// Dependency injection (no setter method is needed)
	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;

	public void create(Status status) {
		em.persist(status);
	}

	public Status merge(Status status) {
		return em.merge(status);
	}

	public void remove(Status status) {
		em.remove(em.merge(status));
	}

	public Status find(Object id) {
		return em.find(Status.class, id);
	}

	public Status getStatus(String nazwa) {
		Status entity = null;

		try {
			Query query = em.createQuery("SELECT p FROM Status p WHERE p.nazwa = ?1");
			query.setParameter(1, nazwa);
			entity = (Status) query.getSingleResult();
		} catch (NoResultException nre) {
			// if not found, just return null
		}

		return entity;
	}

}
