package jsfcourse.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_user")
	private int idUser;

	private String adres;

	private String email;

	private String haslo;

	private String imie;

	private String login;

	private String nazwisko;

	private String rola;

	private String telefon;

	//bi-directional many-to-one association to Cart
	@OneToMany(mappedBy="user")
	private List<Cart> carts;

	//bi-directional many-to-one association to Zamowienia
	@OneToMany(mappedBy="user")
	private List<Zamowienia> zamowienias;

	//bi-directional many-to-one association to ZamowieniaSzczegoly
	@OneToMany(mappedBy="user")
	private List<ZamowieniaSzczegoly> zamowieniaSzczegolies;

	public User() {
	}

	public int getIdUser() {
		return this.idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getAdres() {
		return this.adres;
	}

	public void setAdres(String adres) {
		this.adres = adres;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHaslo() {
		return this.haslo;
	}

	public void setHaslo(String haslo) {
		this.haslo = haslo;
	}

	public String getImie() {
		return this.imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNazwisko() {
		return this.nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	public String getRola() {
		return this.rola;
	}

	public void setRola(String rola) {
		this.rola = rola;
	}

	public String getTelefon() {
		return this.telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public List<Cart> getCarts() {
		return this.carts;
	}

	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}

	public Cart addCart(Cart cart) {
		getCarts().add(cart);
		cart.setUser(this);

		return cart;
	}

	public Cart removeCart(Cart cart) {
		getCarts().remove(cart);
		cart.setUser(null);

		return cart;
	}

	public List<Zamowienia> getZamowienias() {
		return this.zamowienias;
	}

	public void setZamowienias(List<Zamowienia> zamowienias) {
		this.zamowienias = zamowienias;
	}

	public Zamowienia addZamowienia(Zamowienia zamowienia) {
		getZamowienias().add(zamowienia);
		zamowienia.setUser(this);

		return zamowienia;
	}

	public Zamowienia removeZamowienia(Zamowienia zamowienia) {
		getZamowienias().remove(zamowienia);
		zamowienia.setUser(null);

		return zamowienia;
	}

	public List<ZamowieniaSzczegoly> getZamowieniaSzczegolies() {
		return this.zamowieniaSzczegolies;
	}

	public void setZamowieniaSzczegolies(List<ZamowieniaSzczegoly> zamowieniaSzczegolies) {
		this.zamowieniaSzczegolies = zamowieniaSzczegolies;
	}

	public ZamowieniaSzczegoly addZamowieniaSzczegoly(ZamowieniaSzczegoly zamowieniaSzczegoly) {
		getZamowieniaSzczegolies().add(zamowieniaSzczegoly);
		zamowieniaSzczegoly.setUser(this);

		return zamowieniaSzczegoly;
	}

	public ZamowieniaSzczegoly removeZamowieniaSzczegoly(ZamowieniaSzczegoly zamowieniaSzczegoly) {
		getZamowieniaSzczegolies().remove(zamowieniaSzczegoly);
		zamowieniaSzczegoly.setUser(null);

		return zamowieniaSzczegoly;
	}

}