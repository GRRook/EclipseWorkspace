import java.util.Date;

public class Program {
	public static void main(String[] args){
		Medewerker mdw = new Medewerker("Gerben", "Rook", new Date(), 28, "Waddinxveen", 5, "M", "Nederlands", "DMO", true);
		Algoritme alg = new Algoritme();	
	
		System.out.println(alg.calculate(mdw));
	
	}
}
