import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Bod {
	@Id @GeneratedValue
	private Long id;
	private Integer prijs;
	private Long datum;
	
	@ManyToOne(cascade=CascadeType.ALL)
    private Gebruiker gebruiker;
	
	@ManyToOne(cascade=CascadeType.ALL)
    private Advertentie advertentie;
	
	public Bod(){}
	
	public Bod(Integer prijs, Long datum, Gebruiker gebruiker, Advertentie advertentie){
		this.prijs = prijs;
		this.datum = datum;
		this.gebruiker = gebruiker;
		this.advertentie = advertentie;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getPrijs() {
		return prijs;
	}

	public void setPrijs(Integer prijs) {
		this.prijs = prijs;
	}

	public Long getDatum() {
		return datum;
	}

	public void setDatum(Long datum) {
		this.datum = datum;
	}

	public Gebruiker getGebruiker() {
		return gebruiker;
	}

	public void setGebruiker(Gebruiker gebruiker) {
		this.gebruiker = gebruiker;
	}

	public Advertentie getAdvertentie() {
		return advertentie;
	}

	public void setAdvertentie(Advertentie advertentie) {
		this.advertentie = advertentie;
	}
}