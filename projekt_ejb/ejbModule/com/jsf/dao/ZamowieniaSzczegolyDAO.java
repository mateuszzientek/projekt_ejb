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

import jsfcourse.entities.User;
import jsfcourse.entities.Zamowienia;
import jsfcourse.entities.ZamowieniaSzczegoly;

@Stateless
public class ZamowieniaSzczegolyDAO {
	private final static String UNIT_NAME = "jsfcourseSklep";

	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;

	public void create(ZamowieniaSzczegoly zamowieniaSzczegoly) {
		em.persist(zamowieniaSzczegoly);
	}

	public ZamowieniaSzczegoly merge(ZamowieniaSzczegoly zamowieniaSzczegoly) {
		return em.merge(zamowieniaSzczegoly);
	}

	public void remove(ZamowieniaSzczegoly zamowieniaSzczegoly) {
		em.remove(em.merge(zamowieniaSzczegoly));
	}

	public List<ZamowieniaSzczegoly> getList_id(int id) {
		List<ZamowieniaSzczegoly> list = null;

		Query query = em.createQuery("SELECT p FROM ZamowieniaSzczegoly p WHERE p.zamowienia.idZamowienia = ?1");
		query.setParameter(1, id);

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<ZamowieniaSzczegoly> getList(Zamowienia zam) {
		List<ZamowieniaSzczegoly> list = null;

		Query query = em.createQuery("SELECT p FROM ZamowieniaSzczegoly p WHERE p.zamowienia = ?1");
		query.setParameter(1, zam);

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

}