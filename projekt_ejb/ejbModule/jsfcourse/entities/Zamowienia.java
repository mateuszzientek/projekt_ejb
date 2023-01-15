package jsfcourse.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the zamowienia database table.
 * 
 */
@Entity
@NamedQuery(name="Zamowienia.findAll", query="SELECT z FROM Zamowienia z")
public class Zamowienia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_zamowienia")
	private int idZamowienia;

	private int calkowitaCena;

	private int calkowitaIlosc;

	@Temporal(TemporalType.DATE)
	private Date data;

	//bi-directional many-to-one association to Status
	@ManyToOne
	private Status status;

	//bi-directional many-to-one association to User
	@ManyToOne
	private User user;

	//bi-directional many-to-one association to ZamowieniaSzczegoly
	@OneToMany(mappedBy="zamowienia")
	private List<ZamowieniaSzczegoly> zamowieniaSzczegolies;

	public Zamowienia() {
	}

	public int getIdZamowienia() {
		return this.idZamowienia;
	}

	public void setIdZamowienia(int idZamowienia) {
		this.idZamowienia = idZamowienia;
	}

	public int getCalkowitaCena() {
		return this.calkowitaCena;
	}

	public void setCalkowitaCena(int calkowitaCena) {
		this.calkowitaCena = calkowitaCena;
	}

	public int getCalkowitaIlosc() {
		return this.calkowitaIlosc;
	}

	public void setCalkowitaIlosc(int calkowitaIlosc) {
		this.calkowitaIlosc = calkowitaIlosc;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<ZamowieniaSzczegoly> getZamowieniaSzczegolies() {
		return this.zamowieniaSzczegolies;
	}

	public void setZamowieniaSzczegolies(List<ZamowieniaSzczegoly> zamowieniaSzczegolies) {
		this.zamowieniaSzczegolies = zamowieniaSzczegolies;
	}

	public ZamowieniaSzczegoly addZamowieniaSzczegoly(ZamowieniaSzczegoly zamowieniaSzczegoly) {
		getZamowieniaSzczegolies().add(zamowieniaSzczegoly);
		zamowieniaSzczegoly.setZamowienia(this);

		return zamowieniaSzczegoly;
	}

	public ZamowieniaSzczegoly removeZamowieniaSzczegoly(ZamowieniaSzczegoly zamowieniaSzczegoly) {
		getZamowieniaSzczegolies().remove(zamowieniaSzczegoly);
		zamowieniaSzczegoly.setZamowienia(null);

		return zamowieniaSzczegoly;
	}

}