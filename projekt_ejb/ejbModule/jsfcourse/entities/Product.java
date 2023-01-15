package jsfcourse.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the product database table.
 * 
 */
@Entity
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_product")
	private int idProduct;

	private int cena;

	private String nazwa;

	private String opis;

	private String status;

	private String zdjecie;

	//bi-directional many-to-one association to Cart
	@OneToMany(mappedBy="product")
	private List<Cart> carts;

	//bi-directional many-to-one association to ZamowieniaSzczegoly
	@OneToMany(mappedBy="product")
	private List<ZamowieniaSzczegoly> zamowieniaSzczegolies;

	public Product() {
	}

	public int getIdProduct() {
		return this.idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public int getCena() {
		return this.cena;
	}

	public void setCena(int cena) {
		this.cena = cena;
	}

	public String getNazwa() {
		return this.nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public String getOpis() {
		return this.opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getZdjecie() {
		return this.zdjecie;
	}

	public void setZdjecie(String zdjecie) {
		this.zdjecie = zdjecie;
	}

	public List<Cart> getCarts() {
		return this.carts;
	}

	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}

	public Cart addCart(Cart cart) {
		getCarts().add(cart);
		cart.setProduct(this);

		return cart;
	}

	public Cart removeCart(Cart cart) {
		getCarts().remove(cart);
		cart.setProduct(null);

		return cart;
	}

	public List<ZamowieniaSzczegoly> getZamowieniaSzczegolies() {
		return this.zamowieniaSzczegolies;
	}

	public void setZamowieniaSzczegolies(List<ZamowieniaSzczegoly> zamowieniaSzczegolies) {
		this.zamowieniaSzczegolies = zamowieniaSzczegolies;
	}

	public ZamowieniaSzczegoly addZamowieniaSzczegoly(ZamowieniaSzczegoly zamowieniaSzczegoly) {
		getZamowieniaSzczegolies().add(zamowieniaSzczegoly);
		zamowieniaSzczegoly.setProduct(this);

		return zamowieniaSzczegoly;
	}

	public ZamowieniaSzczegoly removeZamowieniaSzczegoly(ZamowieniaSzczegoly zamowieniaSzczegoly) {
		getZamowieniaSzczegolies().remove(zamowieniaSzczegoly);
		zamowieniaSzczegoly.setProduct(null);

		return zamowieniaSzczegoly;
	}

}