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

import jsfcourse.entities.Product;
import jsfcourse.entities.User;

@Stateless
public class ProductDAO {
	private final static String UNIT_NAME = "jsfcourseSklep";

	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;

	public void create(Product product) {
		em.persist(product);
	}

	public Product merge(Product product) {
		return em.merge(product);
	}

	public void remove(Product product) {
		em.remove(em.merge(product));
	}

	public List<Product> getList(Map<String, Object> searchParams) {
		List<Product> list = null;

		// 1. Build query string with parameters
		String select = "select p ";
		String from = "from Product p ";
		String where = "";

		// search for surname
		String nazwa = (String) searchParams.get("nazwa");
		if (nazwa != null) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "p.nazwa like :nazwa ";
		}

		// ... other parameters ...

		// 2. Create query object
		Query query = em.createQuery(select + from + where);

		// 3. Set configured parameters
		if (nazwa != null) {
			query.setParameter("nazwa", "%" + nazwa + "%");
		}

		// ... other parameters ...

		// 4. Execute query and retrieve list of Person objects
		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public Product getProduct(Integer id) {

		Product entity = null;
		try {
			Query query = em.createQuery("SELECT p FROM Product p WHERE p.idProduct = ?1");
			query.setParameter(1, id);
			entity = (Product) query.getSingleResult();
		} catch (NoResultException nre) {
			// if not found, just return null
		}

		return entity;
	}

}