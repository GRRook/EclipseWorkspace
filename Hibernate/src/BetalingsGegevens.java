import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class BetalingsGegevens {
	@Id @GeneratedValue
	private Long id;
	private String eigenaarNaam;
	private String nummer;
	
	public BetalingsGegevens(String eigenaarNaam, String nummer){
		this.eigenaarNaam = eigenaarNaam;
		this.nummer = nummer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEigenaarNaam() {
		return eigenaarNaam;
	}

	public void setEigenaarNaam(String eigenaarNaam) {
		this.eigenaarNaam = eigenaarNaam;
	}

	public String getNummer() {
		return nummer;
	}

	public void setNummer(String nummer) {
		this.nummer = nummer;
	}
}