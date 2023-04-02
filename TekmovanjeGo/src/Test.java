import GUI.Okno;
import logika.Igra;
import logika.Kloniraj;

public class Test {
	
	public static void main(String[] args) {
		Igra igra = new Igra();
		igra.racunalnik = "Beli";
//		igra.dodaj_figuro(1, 1, "Beli");
//		igra.dodaj_figuro(1, 3, "Beli");
//		igra.dodaj_figuro(2,2 , "Beli");
//		igra.dodaj_figuro(1, 2, "Crni");
//		Tocka tocka = igra.mozna_polja.get("(5, 5)");
		
//		tocka.zasedenost = "Beli";
//		tocka.sosedi.put("Dol","Beli");
		
//		Tocka tocka1 = igra.mozna_polja.get("(5, 6)");
//		tocka1.sosedi.put("Gor","Beli");
//		tocka1.zasedenost = "Beli";
		
//		Tocka tocka2 = igra.mozna_polja.get("(1, 1)");
//		tocka2.sosedi.put("Dol","Beli");
//		tocka2.zasedenost = "Beli";
//		
//		
//		Tocka tocka3 = igra.mozna_polja.get("(1, 2)");
//		tocka3.sosedi.put("Dol","Beli");
//		tocka3.sosedi.put("Gor","Beli");
//		tocka3.zasedenost = "Beli";
//		
//		Tocka tocka4 = igra.mozna_polja.get("(1, 3)");
//		tocka4.sosedi.put("Dol","Beli");
//		tocka4.sosedi.put("Gor","Beli");
//		tocka4.zasedenost = "Beli";
//		
//		Tocka tocka5 = igra.mozna_polja.get("(1, 4)");
//		tocka5.sosedi.put("Gor","Beli");
//		tocka5.sosedi.put("Desno","Beli");
//		tocka5.zasedenost = "Beli";
//		
//		Tocka tocka6 = igra.mozna_polja.get("(9, 7)");
//		tocka6.sosedi.put("Levo","Crni");
//		tocka6.sosedi.put("Gor", "Crni");
//		tocka6.sosedi.put("Dol", "Crni");
//		tocka6.zasedenost = "Beli";
//		
//		Tocka q = igra.mozna_polja.get("(9, 6)");
//		Tocka w = igra.mozna_polja.get("(9, 8)");
//		Tocka e = igra.mozna_polja.get("(7, 7)");
//		Tocka r = igra.mozna_polja.get("(8, 7)");
//		q.zasedenost = "Crni";
//		w.zasedenost = "Crni";
//		e.zasedenost = "Crni";
//		r.zasedenost = "Crni";
//		q.sosedi.put("Gor","Crni");
//		w.sosedi.put("Dol","Crni");
//		e.sosedi.put("Desno","Crni");
//		r.sosedi.put("Levo","Crni");
		
//		igra.preveri_skupine();
//
//		System.out.println( igra.skupine_beli.toString());
//		
//		System.out.println( igra.skupine_crni.toString());
//		
	//	System.out.println(tocka);
		
		Okno okno = new Okno();
		
		okno.pack();
		
		okno.setVisible(true);
		
		okno.platno.nastaviIgro(igra);
		
		Igra igra2 = Kloniraj.kloniraj(igra);
		
		System.out.println( igra2.mozna_polja.equals(igra.mozna_polja));
		
//		for (int i = 1; i<=igra.dimenzija_igre;i++) {
//			for (int j = 1; j<=igra.dimenzija_igre;j++) {
//			      System.out.println("key: " + igra.mozna_polja.get("("+i+", "+j+")") + " value: " + igra.mozna_polja.get("("+i+", "+j+")").sosedi);	
//			}
//		    }
//		
//		System.out.println( igra.preveri_igro());
	}
}
