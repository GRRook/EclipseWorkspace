import java.util.Date;

import javax.persistence.criteria.Order;

import org.hibernate.Session;

import entities.Klas;
import entities.Student;

public class App {
	private static Gebruiker gebruiker1;
	private static Gebruiker gebruiker2;
	private static Gebruiker gebruiker3;
	
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();  
        
        //opdracht5_4_1(session);
        //opdracht5_4_2(session);
        //opdracht5_4_3(session);
        opdracht5_4_4(session);
        

        session.getTransaction().commit();
        HibernateUtil.getSessionFactory().close();
    }
    
    private static void opdracht5_4_4(Session session){
    	Advertentie advertentie1 = (Advertentie) HibernateUtil.getSessionFactory().getCurrentSession()
		        .createQuery("from Advertentie where naam = ?")
		        .setString(0, "Daihatsu")
		        .list().get(0);	
    	
    	Gebruiker gebruiker12 = (Gebruiker) HibernateUtil.getSessionFactory().getCurrentSession()
		        .createQuery("from Gebruiker where id = ?")
		        .setInteger(0, 6)
		        .list().get(0);	
    	
    	Gebruiker gebruiker13 = (Gebruiker) HibernateUtil.getSessionFactory().getCurrentSession()
		        .createQuery("from Gebruiker where id = ?")
		        .setInteger(0, 7)
		        .list().get(0);	
    	
    	Date date = new Date();
		Long datum = date.getTime(); 
    	AdvertentieReactie reactie1 = new AdvertentieReactie("Reageer is op mijn bod..", datum, gebruiker12, advertentie1);
    	AdvertentieReactie reactie2 = new AdvertentieReactie("Bedankt ik maak het geld over.", datum, gebruiker13, advertentie1);
    	
    	session.save(reactie1);
    	session.save(reactie2);
    }
    
    private static void opdracht5_4_3(Session session){
    	Advertentie advertentie1 = (Advertentie) HibernateUtil.getSessionFactory().getCurrentSession()
		        .createQuery("from Advertentie where naam = ?")
		        .setString(0, "Daihatsu")
		        .list().get(0);	
    	
    	Categorie parentCategorie = new Categorie("Vervoersmiddelen");
    	Categorie categorie = new Categorie("Personenauto", advertentie1, parentCategorie);
    	session.save(categorie);
    	
    }
    
    public static void opdracht5_4_2(Session session){		
    	gebruiker2 = new Gebruiker("Andre", "Zwart", "Email@Email", "Wachtwoord");
		gebruiker3 = new Gebruiker("Haro", "Thierrij", "Email@Adres", "Wachtwoord");		
		session.save(gebruiker2);
		session.save(gebruiker3);
		
		Advertentie advertentie1 = (Advertentie) HibernateUtil.getSessionFactory().getCurrentSession()
		        .createQuery("from Advertentie where naam = ?")
		        .setString(0, "Daihatsu")
		        .list().get(0);		
    	
		if (advertentie1.getNaam().equals("Daihatsu")) {
			Date date = new Date();
			Long datum = date.getTime(); 
			Bod bod1 = new Bod(1250, datum, gebruiker2, advertentie1);
			Bod bod2 = new Bod(1300, datum, gebruiker3, advertentie1);
			session.save(bod1);
			session.save(bod2);
		}   

		Bod hoogsteBod = (Bod) HibernateUtil.getSessionFactory().getCurrentSession()
		        .createQuery("from Bod order by prijs desc")
		        .list().get(0);
		
		BetalingsGegevens bg = new BetalingsGegevens(hoogsteBod.getGebruiker().getAchternaam(), hoogsteBod.getGebruiker().getId().toString());
		session.save(bg);		
    } 
    
    private static void opdracht5_4_1(Session session){   
    	gebruiker1 = new Gebruiker("Gerben", "Rook", "Email", "Wachtwoord");
    	session.save(gebruiker1); 
    	
    	Date date = new Date();
    	Long startDatum = date.getTime();   	
    	Advertentie advertentie1 = new Advertentie("Daihatsu", "Super koopje", 1200, true, startDatum, gebruiker1);    	    	   
    	session.save(advertentie1);    	
    }

      

    private static void getStudentTest() {
        Student barack = (Student) HibernateUtil.getSessionFactory().getCurrentSession()
                .createQuery("from Student where voornaam = ? and achternaam = ?")
                .setString(0, "Barack")
                .setString(1, "Obama")
                .list().get(0);
        System.out.println(barack);


        if (! barack.getAchternaam().equals("Obama")) {
            throw new IllegalStateException("Achternaam klopt niet!");
        }
        if (! barack.getKlas().getKlasCode().equals("inf1a")) {
            throw new IllegalStateException("Klas klopt niet");
        }
    }
}