package logika;


import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.swing.SwingWorker;

import inteligenca.Inteligenca;
import splosno.Poteza;

public class Vodja {
	
	

	
	public static void dodaj_figuro(int x, int y, Igra igra) {
		igra.stevilo_preskokov = 0;
		System.out.println(igra.igralec_na_vrsti);
		System.out.println(igra.racunalnik);
		if (igra.vrsta_igre.equals("RR")) {
			
			racunalnik(igra);
		}
		else {
			System.out.println("2");
			if (igra.igralec_na_vrsti != igra.racunalnik) {
				System.out.println("tulelele");
				Poteza poteza = new Poteza(x-1,y-1);
				igra.odigraj(poteza);
				if (igra.igralec_na_vrsti == "Beli") igra.igralec_na_vrsti = "Crni";
				else igra.igralec_na_vrsti = "Beli";
				igra.preveri_skupine();
				String stanje = igra.preveri_igro();
				if (igra.pravila_igre.equals("GO") ) {
					igra.igramo = true;
					if (stanje.equals("ZMAGA BELI")) {
						Set<HashSet<Tocka>> skupine = igra.najdi_skupine_za_zbrisat("Crni");
						for (HashSet<Tocka> skupina: skupine) {
							System.out.println("SCORE BEL: " +skupina.size() );
							igra.score_beli = igra.score_beli + skupina.size();
							igra.izbrisi_skupino(skupina);
						}
						
					}
					else if (stanje.equals("ZMAGA CRNI")) {
						Set<HashSet<Tocka>> skupine = igra.najdi_skupine_za_zbrisat("Beli");
						for (HashSet<Tocka> skupina: skupine) {
							System.out.println("SCORE ČRN: " +skupina.size() );
							igra.score_crni = igra.score_crni + skupina.size();
							igra.izbrisi_skupino(skupina);
						}
					}
				}
				System.out.println(igra.vrsta_igre);
				if (igra.vrsta_igre != "ČČ" && igra.preveri_igro().equals("")) {
					System.out.println("tule");
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
				igra.preveri_skupine();
				String stanje = igra.preveri_igro();
				if (igra.pravila_igre.equals("GO") ) {
					igra.igramo = true;
					if (stanje.equals("ZMAGA BELI")) {
						Set<HashSet<Tocka>> skupine = igra.najdi_skupine_za_zbrisat("Crni");
						for (HashSet<Tocka> skupina: skupine) {
							System.out.println("SCORE BEL: " +skupina.size() );
							igra.score_beli = igra.score_beli + skupina.size();
							igra.izbrisi_skupino(skupina);
							
						}
						
					}
					else if (stanje.equals("ZMAGA CRNI")) {
						Set<HashSet<Tocka>> skupine = igra.najdi_skupine_za_zbrisat("Beli");
						for (HashSet<Tocka> skupina: skupine) {
							System.out.println("SCORE ČRN: " +skupina.size() );
							igra.score_crni = igra.score_crni + skupina.size();
							igra.izbrisi_skupino(skupina);
							
						}
					}
				}
				
				igra.igra_clovek = true;
			
				}
			else if (igra.igralec_na_vrsti == igra.racunalnik) {
				racunalnik(igra);
			}
		}
		
		
	}

	
	public static void racunalnik(Igra igra) {

		if (igra.vrsta_igre.equals("RR")) {
			while (igra.preveri_igro().equals("")) {
				igra.igra_clovek = false;
				Inteligenca inteligenca = new Inteligenca("Matic");
				Poteza poteza1 = inteligenca.izberiPotezo(igra);
				igra.odigraj(poteza1);
				if (igra.igralec_na_vrsti.equals("Beli")) igra.igralec_na_vrsti = "Crni";
				else igra.igralec_na_vrsti = "Beli";
				igra.preveri_skupine();
				String stanje = igra.preveri_igro();
				if (igra.pravila_igre.equals("GO") ) {
					igra.igramo = true;
					if (stanje.equals("ZMAGA BELI")) {
						Set<HashSet<Tocka>> skupine = igra.najdi_skupine_za_zbrisat("Crni");
						for (HashSet<Tocka> skupina: skupine) {
							System.out.println("SCORE BEL: " +skupina.size() );
							igra.score_beli = igra.score_beli + skupina.size();
							igra.izbrisi_skupino(skupina);
							
						}
						
					}
					else if (stanje.equals("ZMAGA CRNI")) {
						Set<HashSet<Tocka>> skupine = igra.najdi_skupine_za_zbrisat("Beli");
						for (HashSet<Tocka> skupina: skupine) {
							System.out.println("SCORE ČRN: " +skupina.size() );
							igra.score_crni = igra.score_crni + skupina.size();
							igra.izbrisi_skupino(skupina);
							
						}
					}
				}
			}
		}
		else {
			igra.igra_clovek = false;
			Inteligenca inteligenca = new Inteligenca("Matic");
			Poteza poteza1 = inteligenca.izberiPotezo(igra);
			igra.odigraj(poteza1);
			if (igra.igralec_na_vrsti.equals("Beli")) igra.igralec_na_vrsti = "Crni";
			else igra.igralec_na_vrsti = "Beli";
			igra.preveri_skupine();
			String stanje = igra.preveri_igro();
			if (igra.pravila_igre.equals("GO") ) {
				igra.igramo = true;
				if (stanje.equals("ZMAGA BELI")) {
					Set<HashSet<Tocka>> skupine = igra.najdi_skupine_za_zbrisat("Crni");
					for (HashSet<Tocka> skupina: skupine) {
						System.out.println("SCORE BEL: " +skupina.size() );
						igra.score_beli = igra.score_beli + skupina.size();
						igra.izbrisi_skupino(skupina);
						
					}
					
				}
				else if (stanje.equals("ZMAGA CRNI")) {
					Set<HashSet<Tocka>> skupine = igra.najdi_skupine_za_zbrisat("Beli");
					for (HashSet<Tocka> skupina: skupine) {
						System.out.println("SCORE ČRN: " +skupina.size() );
						igra.score_crni = igra.score_crni + skupina.size();
						igra.izbrisi_skupino(skupina);
						
					}
				}
			}
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
