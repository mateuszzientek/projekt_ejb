package jsfcourse.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the zamowienia_szczegoly database table.
 * 
 */
@Entity
@Table(name="zamowienia_szczegoly")
@NamedQuery(name="ZamowieniaSzczegoly.findAll", query="SELECT z FROM ZamowieniaSzczegoly z")
public class ZamowieniaSzczegoly implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_zamowienia_szczegoly")
	private int idZamowieniaSzczegoly;

	private int ilosc;

	private int rozmiar;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="id_user_user")
	private User user;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="id_product_product")
	private Product product;

	//bi-directional many-to-one association to Zamowienia
	@ManyToOne
	@JoinColumn(name="id_zamowienia_zamowienia")
	private Zamowienia zamowienia;

	public ZamowieniaSzczegoly() {
	}

	public int getIdZamowieniaSzczegoly() {
		return this.idZamowieniaSzczegoly;
	}

	public void setIdZamowieniaSzczegoly(int idZamowieniaSzczegoly) {
		this.idZamowieniaSzczegoly = idZamowieniaSzczegoly;
	}

	public int getIlosc() {
		return this.ilosc;
	}

	public void setIlosc(int ilosc) {
		this.ilosc = ilosc;
	}

	public int getRozmiar() {
		return this.rozmiar;
	}

	public void setRozmiar(int rozmiar) {
		this.rozmiar = rozmiar;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Zamowienia getZamowienia() {
		return this.zamowienia;
	}

	public void setZamowienia(Zamowienia zamowienia) {
		this.zamowienia = zamowienia;
	}

}