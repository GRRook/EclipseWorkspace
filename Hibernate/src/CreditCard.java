import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;



@Entity
public class CreditCard {
	@Id @GeneratedValue
	private Long id;
	private String bankNaam = null;
	
	public CreditCard(String bankNaam){
		this.bankNaam = bankNaam;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getBankNaam() {
		return bankNaam;
	}

	public void setBankNaam(String bankNaam) {
		this.bankNaam = bankNaam;
	}
}
