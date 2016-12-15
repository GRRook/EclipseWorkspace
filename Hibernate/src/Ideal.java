import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;



@Entity
public class Ideal {
	@Id @GeneratedValue
	private Long id;
	private Integer vervalMaand = null;
	private Integer vervalJaar = null;
	
	public Ideal(Integer vervalMaand, Integer vervalJaar){
		this.vervalMaand = vervalMaand;
		this.vervalJaar = vervalJaar;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getVervalMaand() {
		return vervalMaand;
	}
	public void setVervalMaand(Integer vervalMaand) {
		this.vervalMaand = vervalMaand;
	}
	public Integer getVervalJaar() {
		return vervalJaar;
	}
	public void setVervalJaar(Integer vervalJaar) {
		this.vervalJaar = vervalJaar;
	}	
}
