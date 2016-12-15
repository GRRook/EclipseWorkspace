

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Advertentie {
	@Id @GeneratedValue
	private Long id;
	private String naam;
	private String beschrijving;
	private Integer startPrijs;
	private Boolean actief;
	private Long startDatum;
	
	@ManyToOne(cascade=CascadeType.ALL)
    private Gebruiker gebruiker;
	
	public Advertentie(){}
	
	public Advertentie(String naam, String beschrijving, Integer startPrijs, Boolean actief, Long startDatum, Gebruiker gebruiker){
		this.naam = naam;
		this.beschrijving = beschrijving;
		this.startPrijs = startPrijs;
		this.actief = actief;
		this.startDatum = startDatum;
		this.gebruiker = gebruiker;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getBeschrijving() {
		return beschrijving;
	}

	public void setBeschrijving(String beschrijving) {
		this.beschrijving = beschrijving;
	}

	public Integer getStartPrijs() {
		return startPrijs;
	}

	public void setStartPrijs(Integer startPrijs) {
		this.startPrijs = startPrijs;
	}

	public Boolean getActief() {
		return actief;
	}

	public void setActief(Boolean actief) {
		this.actief = actief;
	}

	public Long getStartDatum() {
		return startDatum;
	}

	public void setStartDatum(Long startDatum) {
		this.startDatum = startDatum;
	}

	public Gebruiker getGebruiker() {
		return gebruiker;
	}

	public void setGebruiker(Gebruiker gebruiker) {
		this.gebruiker = gebruiker;
	}
}
