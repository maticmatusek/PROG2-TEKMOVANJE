package logika;


import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.swing.SwingWorker;

import inteligenca.Inteligenca;
import splosno.Poteza;

public class Vodja {
	
	

	// FUNKCIJA, KI VODI IGRO
	public static void dodaj_figuro(int x, int y, Igra igra) {
		igra.stevilo_preskokov = 0;

		// ČE IGRA RAČUNALNIK PROTI RAČUNALNIKU, ČLOVEK NE PRIDE DO POTEZE
		if (igra.vrsta_igre.equals("RR")) {
			
			racunalnik(igra);
		}
		
		else {
			// ČE JE ČLOVEK NA POTEZI:
			if (igra.igralec_na_vrsti != igra.racunalnik) {
				// 1. ODIGRAMO POTEZO
				Poteza poteza = new Poteza(x-1,y-1);
				igra.odigraj(poteza);
				// 2. ZAMENJAMO IGRALCA NA VRSTI
				if (igra.igralec_na_vrsti == "Beli") igra.igralec_na_vrsti = "Crni";
				else igra.igralec_na_vrsti = "Beli";
				// 3. PREVERIMO STANJE IGRE
				igra.preveri_skupine();
				String stanje = igra.preveri_igro();
				if (igra.pravila_igre.equals("GO") ) {
					igra.igramo = true;
					if (stanje.equals("ZMAGA BELI")) {
						Set<HashSet<Tocka>> skupine = igra.najdi_skupine_za_zbrisat("Crni");
						for (HashSet<Tocka> skupina: skupine) {
							
							igra.score_beli = igra.score_beli + skupina.size();
							igra.izbrisi_skupino(skupina);
						}
						
					}
					else if (stanje.equals("ZMAGA CRNI")) {
						Set<HashSet<Tocka>> skupine = igra.najdi_skupine_za_zbrisat("Beli");
						for (HashSet<Tocka> skupina: skupine) {
							
							igra.score_crni = igra.score_crni + skupina.size();
							igra.izbrisi_skupino(skupina);
						}
					}
				}
				// ČE IGRAMO PROTI RAČUNALNIKU, IGRA RAČUNALNIK NAPREJ
				if (igra.vrsta_igre != "ČČ" && igra.preveri_igro().equals("")) {
					igra.igra_clovek = false;
					racunalnik(igra);
				}
				
			}
			// ČE IGRO ZAČNE RAČUNALNIK KLIČEMO INTELIGENCO
			if (igra.igralec_na_vrsti == igra.racunalnik && igra.skupine_beli.size()==0 && igra.skupine_crni.size()==0) {
				
				igra.igra_clovek = false;
				Inteligenca inteligenca = new Inteligenca("Matic");
			
				Igra igra1 = Kloniraj.kloniraj(igra);
				Poteza poteza1 = inteligenca.izberiPotezo(igra1);
				
				igra.odigraj(poteza1);
				// ZAMENJAMO IGRALCA IN PREVERIMO IGRO
				if (igra.igralec_na_vrsti.equals("Beli")) igra.igralec_na_vrsti = "Crni";
				else igra.igralec_na_vrsti = "Beli";
				igra.preveri_skupine();
				String stanje = igra.preveri_igro();
				if (igra.pravila_igre.equals("GO") ) {
					igra.igramo = true;
					if (stanje.equals("ZMAGA BELI")) {
						Set<HashSet<Tocka>> skupine = igra.najdi_skupine_za_zbrisat("Crni");
						for (HashSet<Tocka> skupina: skupine) {
							
							igra.score_beli = igra.score_beli + skupina.size();
							igra.izbrisi_skupino(skupina);
							
						}
						
					}
					else if (stanje.equals("ZMAGA CRNI")) {
						Set<HashSet<Tocka>> skupine = igra.najdi_skupine_za_zbrisat("Beli");
						for (HashSet<Tocka> skupina: skupine) {
							
							igra.score_crni = igra.score_crni + skupina.size();
							igra.izbrisi_skupino(skupina);
							
						}
					}
				}
				
				igra.igra_clovek = true;
			
				}
			else if (igra.igralec_na_vrsti == igra.racunalnik && igra.igra_clovek == true) {
				
				racunalnik(igra);
			}
		}
		
		
	}

	// POSKRBI DA RAČUNALNIK IZVEDE POTEZO
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
						
							igra.score_beli = igra.score_beli + skupina.size();
							igra.izbrisi_skupino(skupina);
							
						}
						
					}
					else if (stanje.equals("ZMAGA CRNI")) {
						Set<HashSet<Tocka>> skupine = igra.najdi_skupine_za_zbrisat("Beli");
						for (HashSet<Tocka> skupina: skupine) {
						
							igra.score_crni = igra.score_crni + skupina.size();
							igra.izbrisi_skupino(skupina);
							
						}
					}
				}
			}
		}
		else if (igra.igramo){
			igra.igra_clovek = false;
			Inteligenca inteligenca = new Inteligenca("Matic");
			
			
			SwingWorker<Void, Void> worker = new SwingWorker<Void, Void> () {
			@Override
			protected Void doInBackground() {
			try {TimeUnit.SECONDS.sleep(1);} catch (Exception e) {};
			return null;
			}
			@Override
			protected void done () {


				Poteza poteza1 = inteligenca.izberiPotezo(igra);
				igra.odigraj(poteza1);
				if (igra.igralec_na_vrsti.equals("Beli")) igra.igralec_na_vrsti = "Crni";
				else igra.igralec_na_vrsti = "Beli";
				igra.igra_clovek = true;
				igra.preveri_skupine();
				String stanje = igra.preveri_igro();
				if (igra.pravila_igre.equals("GO") ) {
					igra.igramo = true;
					if (stanje.equals("ZMAGA BELI")) {
						Set<HashSet<Tocka>> skupine = igra.najdi_skupine_za_zbrisat("Crni");
						for (HashSet<Tocka> skupina: skupine) {
					
							igra.score_beli = igra.score_beli + skupina.size();
							igra.izbrisi_skupino(skupina);
							
						}
						
					}
					else if (stanje.equals("ZMAGA CRNI")) {
						Set<HashSet<Tocka>> skupine = igra.najdi_skupine_za_zbrisat("Beli");
						for (HashSet<Tocka> skupina: skupine) {
							
							igra.score_crni = igra.score_crni + skupina.size();
							igra.izbrisi_skupino(skupina);
							
						}
					}
				}

			}
			};
			worker.execute();
			
		}
	}
	
	
}
