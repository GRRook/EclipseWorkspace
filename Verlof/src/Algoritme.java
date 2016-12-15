import java.util.Date;

/*Medewerker
	voornaam
	achternaam
	geboortedatum
	burgerlijkestaat
	rang_schaal
	uitzending
	leeftijd
	woonplaats
	dienstjaren
	kans op ziekte (in %) deze maand
	geslacht
	ethniciteit
	afdeling
	onregelmatig dienstrooster
	
	medisch
		roken
		diabetes
		hartaanval 
		bloeddruk
		cholestrol
		lengte
		gewicht
		bmi
	
	verlof
  		datum_tijd_van
  		datum_tijd_tm
  		type
  		uren
  		dagen
  		
	  */		
	  		
  		
public class Algoritme {	
	
	public double calculate(Medewerker medewerker){
		
		if (medewerker.isOnregelmatigDienstrooster()){
			medewerker.getRatingMedewerker().increaseRating(90.5);
		}
		if (medewerker.getGeslacht() == "M"){
			medewerker.getRatingMedewerker().increaseRating(10.0);
		}
		
		return medewerker.getRatingMedewerker().getRating();
	}
	
}

