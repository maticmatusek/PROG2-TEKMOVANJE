package logika;


import java.util.HashSet;
import java.util.concurrent.TimeUnit;

import javax.swing.SwingWorker;

import inteligenca.Inteligenca;
import splosno.Poteza;

public class Vodja {
	
	

	
	public static void dodaj_figuro(int x, int y, Igra igra) {
		if (igra.vrsta_igre.equals("RR")) {
			racunalnik(igra);
		}
		else {
			if (igra.igralec_na_vrsti != igra.racunalnik) {
				Poteza poteza = new Poteza(x-1,y-1);
				igra.odigraj(poteza);
				if (igra.igralec_na_vrsti == "Beli") igra.igralec_na_vrsti = "Crni";
				else igra.igralec_na_vrsti = "Beli";
				igra.preveri_skupine();
				igra.preveri_igro();
				if (igra.vrsta_igre != "ČČ" && igra.preveri_igro().equals("")) {
					igra.igra_clovek = false;
					racunalnik(igra);
				}
				
			}
			if (igra.igralec_na_vrsti == igra.racunalnik && igra.skupine_beli.size()==0 && igra.skupine_crni.size()==0) {
				igra.igra_clovek = false;
				
				Inteligenca inteligenca = new Inteligenca("Matic");
			
				Igra igra1 = Kloniraj.kloniraj(igra);
				Igra igra2 = igra1.clone();
				Poteza poteza1 = inteligenca.izberiPotezo(igra2);
				
				igra.odigraj(poteza1);
				if (igra.igralec_na_vrsti.equals("Beli")) igra.igralec_na_vrsti = "Crni";
				else igra.igralec_na_vrsti = "Beli";
				igra.preveri_igro();
				
				igra.igra_clovek = true;
			
				}
		}
		
		
	}

	
	public static void racunalnik(Igra igra) {

		if (igra.vrsta_igre.equals("RR")) {
			while (igra.preveri_igro().equals("")) {
				System.out.println("ZAKAJ");
				igra.igra_clovek = false;
				System.out.println("heh");
				Inteligenca inteligenca = new Inteligenca("Matic");
				System.out.println("hah");
				Poteza poteza1 = inteligenca.izberiPotezo(igra);
				System.out.println(poteza1);
				igra.odigraj(poteza1);
				if (igra.igralec_na_vrsti.equals("Beli")) igra.igralec_na_vrsti = "Crni";
				else igra.igralec_na_vrsti = "Beli";
				igra.preveri_igro();
				System.out.println(igra.preveri_igro());
			}
		}
		else {
			igra.igra_clovek = false;
			System.out.println("heh");
			Inteligenca inteligenca = new Inteligenca("Matic");
			System.out.println("hah");
			Poteza poteza1 = inteligenca.izberiPotezo(igra);
			System.out.println(poteza1);
			igra.odigraj(poteza1);
			if (igra.igralec_na_vrsti.equals("Beli")) igra.igralec_na_vrsti = "Crni";
			else igra.igralec_na_vrsti = "Beli";
			igra.preveri_igro();
			System.out.println(igra.preveri_igro());
			igra.igra_clovek = true;
		}
	}
	
	
	
	public static void dodaj_figuro2(int x, int y, Igra igra) {
		Tocka t = igra.mozna_polja.get("("+x+", "+y+")");
		String barva = igra.igralec_na_vrsti;
		t.zasedenost = igra.igralec_na_vrsti;
		if (x!=1) {
			Tocka u = igra.mozna_polja.get("("+(x-1)+", "+y+")");
			u.sosedi.put("Desno", barva);
			if (u.zasedenost != null) t.sosedi.put("Levo", u.zasedenost);
		}
		if (x!=igra.dimenzija_igre) {
			Tocka u = igra.mozna_polja.get("("+(x+1)+", "+y+")");
			u.sosedi.put("Levo", barva);
			if (u.zasedenost != null) t.sosedi.put("Desno", u.zasedenost);
		}
		if (y!=1) {
			Tocka u = igra.mozna_polja.get("("+x+", "+(y-1)+")");
			u.sosedi.put("Dol", barva);
			if (u.zasedenost != null) t.sosedi.put("Gor", u.zasedenost);
		}
		if (y!=igra.dimenzija_igre) {
			Tocka u = igra.mozna_polja.get("("+x+", "+(y+1)+")");
			u.sosedi.put("Gor", barva);
			if (u.zasedenost != null) t.sosedi.put("Dol", u.zasedenost);
		}
		if (igra.igralec_na_vrsti == "Beli") igra.igralec_na_vrsti = "Crni";
		else igra.igralec_na_vrsti = "Beli";
		igra.preveri_igro();
		
		

		
		if ((igra.racunalnik == igra.igralec_na_vrsti && igra.igramo) || (igra.racunalnik2 && igra.igramo)) {
			igra.igra_clovek = false;
			


					Inteligenca inteligenca = new Inteligenca("Matic");
					Poteza poteza = inteligenca.izberiPotezo(igra);

					dodaj_figuro2(poteza.x()+1,poteza.y()+1,igra);
					

		}
		else igra.igra_clovek = true;
	}
	
}
