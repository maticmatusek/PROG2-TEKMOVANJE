import GUI.Okno;
import logika.Igra;
import logika.Kloniraj;
import logika.Tocka;
import logika.Vodja;
import splosno.Poteza;

public class Test {
	
	public static void main(String[] args) {
		Igra igra = new Igra();
//		Vodja vodja = new Vodja();
//		Boolean hah = igra.odigraj(new Poteza(0,0));
//		igra.igralec_na_vrsti = "Beli";
//		Boolean halh = igra.odigraj(new Poteza(1,1));
//		igra.igralec_na_vrsti = "Crni";
//		Boolean hahs = igra.odigraj(new Poteza(3,3));
//		Igra klon = Kloniraj.kloniraj(igra);
//		klon.preveri_skupine();
//		igra.preveri_skupine();
//		System.out.println(klon.skupine_beli);
//		System.out.println(klon.skupine_crni);
//		System.out.println(klon.skupine_prazni );
//		System.out.println("-----------");
//		
//		System.out.println(igra.skupine_beli);
//		System.out.println(igra.skupine_crni);
		
		Okno okno = new Okno();
		
		okno.pack();
		
		okno.setVisible(true);
		
		okno.platno.nastaviIgro(igra);


	}
}
