package com.jsf.dao;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import jsfcourse.entities.User;

//DAO - Data Access Object for Person entity
//Designed to serve as an interface between higher layers of application and data.
//Implemented as stateless Enterprise Java bean - server side code that can be invoked even remotely.

@Stateless
public class UserDAO {
	private final static String UNIT_NAME = "jsfcourseSklep";
	private String czy_jest;

	// Dependency injection (no setter method is needed)
	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;

	public void create(User user) {
		em.persist(user);
	}

	public User merge(User user) {
		return em.merge(user);
	}

	public void remove(User user) {
		em.remove(em.merge(user));
	}

	public User find(Object id) {
		return em.find(User.class, id);
	}

	public List<User> getList(String login) {
		List<User> list = null;

		Query query = em.createQuery("SELECT p FROM User p WHERE p.login = ?1");
		query.setParameter(1, login);

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public User getUser(String login) {

		User entity = null;
		try {
			Query query = em.createQuery("SELECT p FROM User p WHERE p.login = ?1");
			query.setParameter(1, login);
			entity = (User) query.getSingleResult();
		} catch (NoResultException nre) {
			// if not found, just return null
		}

		return entity;
	}

	public User get_login_pass(String login, String haslo) {

		User entity = null;
		try {
			Query query = em.createQuery("SELECT p FROM User p WHERE p.login = ?1 AND p.haslo = ?2");
			query.setParameter(1, login);
			query.setParameter(2, haslo);
			entity = (User) query.getSingleResult();
		} catch (NoResultException nre) {
			// if not found, just return null
		}

		return entity;
	}

	public String sprawdzenie_imiona(String login) {

		User entity = null;
		try {
			Query query = em.createQuery("SELECT p FROM User p WHERE p.login = ?1");
			query.setParameter(1, login);
			entity = (User) query.getSingleResult();
			czy_jest = entity.getImie();
		} catch (NoResultException nre) {
			// if not found, just return null
		}

		return czy_jest;
	}

}
