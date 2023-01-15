package com.jsf.dao;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;

import jsfcourse.entities.Zamowienia;
import jsfcourse.entities.Cart;
import jsfcourse.entities.Product;
import jsfcourse.entities.User;

@Stateless
public class ZamowieniaDAO {
	private final static String UNIT_NAME = "jsfcourseSklep";

	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;

	public void create(Zamowienia zamowienia) {
		em.persist(zamowienia);
	}

	public Zamowienia merge(Zamowienia zamowienia) {
		return em.merge(zamowienia);
	}

	public void remove(Zamowienia zamowienia) {
		em.remove(em.merge(zamowienia));
	}

	public List<Zamowienia> getList(User user) {
		List<Zamowienia> list = null;

		Query query = em.createQuery("SELECT p FROM Zamowienia p WHERE p.user = ?1");
		query.setParameter(1, user);

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public Boolean check_is_add(User user) {

		Boolean czy_jest = true;

		List<Zamowienia> list = null;

		try {
			Query query = em.createQuery("SELECT p FROM Zamowienia p WHERE p.user = ?1");
			query.setParameter(1, user);
			list = query.getResultList();

			if (list.isEmpty()) {
				czy_jest = false;
			}

		} catch (NoResultException nre) {
			// if not found, just return null
		}

		return czy_jest;
	}

	public Zamowienia get_last_zamowienie(int id) {

		Zamowienia entity = null;
		try {
			Query query = em.createQuery("SELECT p FROM Zamowienia p WHERE p.idZamowienia = ?1");
			query.setParameter(1, id);
			entity = (Zamowienia) query.getSingleResult();
		} catch (NoResultException nre) {
			// if not found, just return null
		}

		return entity;
	}
}