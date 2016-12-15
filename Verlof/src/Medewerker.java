import java.util.Date;

public class Medewerker {
	private String voornaam;
	private String achternaam;
	private Date geboortedatum;
	private int leeftijd;
	private String woonplaats;
	private int dienstjaren;
	private String geslacht;
	private String ethniciteit;
	private String afdeling;
	private boolean onregelmatigDienstrooster;
	private Rating rating;
	
	public Medewerker(String voornaam, String achternaam, Date geboortedatum, int leeftijd, String woonplaats, int dienstjaren, String geslacht, String ethniciteit, String afdeling, boolean onregelmatigDienstrooster){
		this.voornaam = voornaam;
		this.achternaam = achternaam;
		this.geboortedatum = geboortedatum;
		this.leeftijd = leeftijd;
		this.woonplaats = woonplaats;
		this.dienstjaren = dienstjaren;
		this.geslacht = geslacht;
		this.ethniciteit = ethniciteit;
		this.afdeling = afdeling;
		this.onregelmatigDienstrooster = onregelmatigDienstrooster;
		this.rating = new Rating(0.0);
	}
	
	public Medewerker getMedewerker(){
		return this;
	}

	public String getVoornaam() {
		return voornaam;
	}

	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}

	public String getAchternaam() {
		return achternaam;
	}

	public void setAchternaam(String achternaam) {
		this.achternaam = achternaam;
	}

	public Date getGeboortedatum() {
		return geboortedatum;
	}

	public void setGeboortedatum(Date geboortedatum) {
		this.geboortedatum = geboortedatum;
	}

	public int getLeeftijd() {
		return leeftijd;
	}

	public void setLeeftijd(int leeftijd) {
		this.leeftijd = leeftijd;
	}

	public String getWoonplaats() {
		return woonplaats;
	}

	public void setWoonplaats(String woonplaats) {
		this.woonplaats = woonplaats;
	}

	public int getDienstjaren() {
		return dienstjaren;
	}

	public void setDienstjaren(int dienstjaren) {
		this.dienstjaren = dienstjaren;
	}

	public Rating getRatingMedewerker() {
		return rating;
	}

	public String getGeslacht() {
		return geslacht;
	}

	public void setGeslacht(String geslacht) {
		this.geslacht = geslacht;
	}

	public String getEthniciteit() {
		return ethniciteit;
	}

	public void setEthniciteit(String ethniciteit) {
		this.ethniciteit = ethniciteit;
	}

	public String getAfdeling() {
		return afdeling;
	}

	public void setAfdeling(String afdeling) {
		this.afdeling = afdeling;
	}

	public boolean isOnregelmatigDienstrooster() {
		return onregelmatigDienstrooster;
	}

	public void setOnregelmatigDienstrooster(boolean onregelmatigDienstrooster) {
		this.onregelmatigDienstrooster = onregelmatigDienstrooster;
	}
}
