package dev.api.entite;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COMPTE")
public class Compte {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
	@Column(name = "BANQUE")
	private String banque;
	@Column(name = "BIC")
	private String bic;
	@Column(name = "IBAN")
	private String iban;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBanque() {
		return banque;
	}

	public void setBanque(String banque) {
		this.banque = banque;
	}

	public String getBic() {
		return bic;
	}

	public void setBic(String bic) {
		this.bic = bic;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

}
