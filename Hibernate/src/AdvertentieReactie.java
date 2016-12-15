import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class AdvertentieReactie {
	@Id @GeneratedValue
	private Long id;
	private String tekst;
	private Long datum;
	
	@ManyToOne(cascade=CascadeType.ALL)
    private Gebruiker gebruiker;
	
	@ManyToOne(cascade=CascadeType.ALL)
    private Advertentie advertentie;
	
	public AdvertentieReactie(){}
	
	public AdvertentieReactie(String tekst, Long datum, Gebruiker gebruiker, Advertentie advertentie){
		this.tekst = tekst;
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

	public String getTekst() {
		return tekst;
	}

	public void setTekst(String tekst) {
		this.tekst = tekst;
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