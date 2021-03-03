 
import java.util.Scanner;
import model.*;

public class TekstiMain {
	static IValuuttaDAO valuuttaDAO = new ValuuttaAccessObject();
	static Scanner scanner = new Scanner(System.in);
	private static Valuutta valuutta;
	private static Valuutta[] valuutat;

	public static void listaaValuutat() {
		valuutat = valuuttaDAO.readValuutat();

		for (Valuutta v : valuutat) {
			System.out.println(v.getTunnus() +  ", " + v.getNimi() + ", " + v.getVaihtokurssi());
		}

	}

	public static void lisaaValuutta() {
		System.out.print("Anna valuutan tunnus: ");
		String tunnus = scanner.nextLine();
		
		
		System.out.print("Anna valuutan nimi: ");
		String nimi = scanner.nextLine(); 
		
		System.out.print("Anna valuutan vaihtokurssi: ");
		double vaihtokurssi = Double.parseDouble(scanner.nextLine());
		
		
		valuutta = new Valuutta(tunnus, nimi, vaihtokurssi);
		
		if(valuuttaDAO.createValuutta(valuutta)) {
			System.out.println("Valuutta lisätty tietokantaan");
		}else {
			System.out.println("valuutan lisääminen epäonnistui");
		}

	}

	public static void paivitaValuutta() {
		
		System.out.print("Anna valuutan tunnus: ");
		String tunnus = scanner.nextLine().toUpperCase();
		
		valuutta = valuuttaDAO.readValuutta(tunnus);
		
		System.out.println("Haluatko muuttaa valuutan " + valuutta.getNimi() + " nimen, vaihtokurssin, vai molemat?(n/v/m)");
		
		char vastaus = scanner.nextLine().toLowerCase().charAt(0);

		switch(vastaus) {
		case 'n': 
			System.out.print("Syötä uusi nimi ");
			valuutta.setNimi(scanner.nextLine());
			break;
		
		case 'v':
			System.out.print("Syötä uusi vaihtokurssi: ");
			try {
				valuutta.setVaihtokurssi(Double.parseDouble(scanner.nextLine()));
			}catch(NumberFormatException e) {
				System.err.println("Syötä numeroita");
			}
			break;
			
		case 'm':
			System.out.print("Syötä uusi nimi: ");
			valuutta.setNimi(scanner.nextLine());
			
			System.out.print("Syötä uusi vaitokurssi: ");
			try {
				valuutta.setVaihtokurssi(Double.parseDouble(scanner.nextLine()));
			}catch(NumberFormatException e) {
				System.err.println("Syötä numeroita");
			}
			break;
		
		default:
			System.out.println("Yritä uudestaan");
		}
		if(valuuttaDAO.updateValuutta(valuutta)) {
			System.out.println("Valuutan tiedot päivitetty");
		}else {
			System.out.println("Tiedon päivitys epäonnistui");
		}
		
	}

	public static void poistaValuutta() {
		
		System.out.print("Anna valuutan tunnus: ");
		String tunnus = scanner.nextLine().toUpperCase();
		
		if(valuuttaDAO.deleteValuutta(tunnus)) {
			System.out.println("Tiedot poistettu");
		}else {
			System.out.println("poistaminen epäonnistui.");
		}

	}

	public static void main(String[] args) {
		char valinta;
		final char CREATE = 'C', READ = 'R', UPDATE = 'U', DELETE = 'D', QUIT = 'Q';
		
		do {
			System.out.print("Valintasi: ");
			valinta = (scanner.nextLine().toUpperCase()).charAt(0);
			switch (valinta) {
			case READ:  
				listaaValuutat();
				break;
			case CREATE:
				lisaaValuutta();
				break;
			case UPDATE:
				paivitaValuutta();
				break;
			case DELETE:
				poistaValuutta();
				break;
			}
		} while (valinta != QUIT);
	}
}
