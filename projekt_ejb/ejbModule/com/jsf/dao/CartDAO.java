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

import jsfcourse.entities.Cart;
import jsfcourse.entities.User;
import jsfcourse.entities.Product;

@Stateless
public class CartDAO {
	private final static String UNIT_NAME = "jsfcourseSklep";
	private String czy_jest;

	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;

	public void create(Cart cart) {
		em.persist(cart);
	}

	public Cart merge(Cart cart) {
		return em.merge(cart);
	}

	public void remove(Cart cart) {
		em.remove(em.merge(cart));
	}

	public Cart check(User user, Product product, int rozmiar) {

		Cart entity = null;
		try {
			Query query = em
					.createQuery("SELECT p FROM Cart p WHERE p.user = ?1 AND p.product = ?2 AND p.rozmiar = ?3");
			query.setParameter(1, user);
			query.setParameter(2, product);
			query.setParameter(3, rozmiar);
			entity = (Cart) query.getSingleResult();
		} catch (NoResultException nre) {
			// if not found, just return null
		}

		return entity;
	}

	public Boolean check_is_add(User user) {

		Boolean czy_jest = false;

		List<Cart> list = null;

		try {
			Query query = em.createQuery("SELECT p FROM Cart p WHERE p.user = ?1");
			query.setParameter(1, user);
			list = query.getResultList();

			if (list.isEmpty()) {
				czy_jest = true;
			}

		} catch (NoResultException nre) {
			// if not found, just return null
		}

		return czy_jest;
	}

	public List<Cart> getList(User user) {
		List<Cart> list = null;

		Query query = em.createQuery("SELECT p FROM Cart p WHERE p.user = ?1");
		query.setParameter(1, user);

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<Product> getList_product(User user) {
		List<Product> list = null;

		Query query = em.createQuery("SELECT product FROM Cart p WHERE p.user = ?1");
		query.setParameter(1, user);

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

}