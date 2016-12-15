import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;



@Entity
public class Categorie {
	@Id @GeneratedValue
	private Long id;
	private String naam;
	
	@ManyToOne(cascade=CascadeType.ALL)
    private Advertentie advertentie;
	
	@ManyToOne(cascade=CascadeType.ALL)
    private Categorie categorie;
	
	public Categorie(){}
	
	public Categorie(String naam){
		this.naam = naam;
	}
	
	public Categorie(String naam, Advertentie advertentie, Categorie parentCategorie){
		this.naam = naam;
		this.advertentie = advertentie;
		this.categorie = parentCategorie;
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

	public Advertentie getAdvertentie() {
		return advertentie;
	}

	public void setAdvertentie(Advertentie advertentie) {
		this.advertentie = advertentie;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}	
}
