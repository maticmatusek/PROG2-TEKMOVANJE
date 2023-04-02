package inteligenca;

import java.util.HashMap;
import java.util.HashSet;

import logika.Igra;
import logika.Poteza;
import logika.Tocka;
import logika.Vodja;

public class Inteligenca extends splosno.KdoIgra{
	
	public String ime;
	public Igra igra;

	public Inteligenca(String ime) {
		super(ime);
		this.ime = "Matic";
	}

	public Poteza izberiPotezo(Igra igra) {
		igra.preveri_skupine();
		this.igra = igra;
		String igram_barvo = igra.igralec_na_vrsti;
		if (igram_barvo == "Beli") {
			
			for (HashSet<Tocka> skupina : igra.skupine_crni) {
				int counter = 0;
				for (Tocka t : skupina) {
					if (t.sosedi.get("Levo") == "Prosto") {
						counter++;
					}
					if (t.sosedi.get("Desno") == "Prosto") {
						counter++;
					}
					if (t.sosedi.get("Gor") == "Prosto") {
						counter++;
					}
					if (t.sosedi.get("Dol") == "Prosto") {
						counter++;
					}
				}
				if (counter == 1) {
					for (Tocka t : skupina) {
						if (t.sosedi.get("Levo") == "Prosto") {
							return new Poteza(t.x-1-1,t.y-1);
						}
						if (t.sosedi.get("Desno") == "Prosto") {
							return new Poteza(t.x-1+1,t.y-1);
						}
						if (t.sosedi.get("Gor") == "Prosto") {
							return new Poteza(t.x-1,t.y-1-1);
						}
						if (t.sosedi.get("Dol") == "Prosto") {
							return new Poteza(t.x-1,t.y-1+1);
						}
					}
				}
			}
		}
		else {
			for (HashSet<Tocka> skupina : igra.skupine_beli) {
				int counter = 0;
				for (Tocka t : skupina) {
					if (t.sosedi.get("Levo") == "Prosto") {
						counter++;
					}
					if (t.sosedi.get("Desno") == "Prosto") {
						counter++;
					}
					if (t.sosedi.get("Gor") == "Prosto") {
						counter++;
					}
					if (t.sosedi.get("Dol") == "Prosto") {
						counter++;
					}
				}
				if (counter == 1) {
					for (Tocka t : skupina) {
						if (t.sosedi.get("Levo") == "Prosto") {
							return new Poteza(t.x-1-1,t.y-1);
						}
						if (t.sosedi.get("Desno") == "Prosto") {
							return new Poteza(t.x-1+1,t.y-1);
						}
						if (t.sosedi.get("Gor") == "Prosto") {
							return new Poteza(t.x-1,t.y-1-1);
						}
						if (t.sosedi.get("Dol") == "Prosto") {
							return new Poteza(t.x-1,t.y-1+1);
						}
					}
				}
			}
		}
		if (igram_barvo == "Beli") {
			
			for (HashSet<Tocka> skupina : igra.skupine_beli) {
				int counter = 0;
				for (Tocka t : skupina) {
					if (t.sosedi.get("Levo") == "Prosto") {
						counter++;
					}
					if (t.sosedi.get("Desno") == "Prosto") {
						counter++;
					}
					if (t.sosedi.get("Gor") == "Prosto") {
						counter++;
					}
					if (t.sosedi.get("Dol") == "Prosto") {
						counter++;
					}
				}
				if (counter == 1) {
					for (Tocka t : skupina) {
						if (t.sosedi.get("Levo") == "Prosto") {
							return new Poteza(t.x-1-1,t.y-1);
						}
						if (t.sosedi.get("Desno") == "Prosto") {
							return new Poteza(t.x-1+1,t.y-1);
						}
						if (t.sosedi.get("Gor") == "Prosto") {
							return new Poteza(t.x-1,t.y-1-1);
						}
						if (t.sosedi.get("Dol") == "Prosto") {
							return new Poteza(t.x-1,t.y-1+1);
						}
					}
				}
			}
		}
		else {
			for (HashSet<Tocka> skupina : igra.skupine_crni) {
				int counter = 0;
				for (Tocka t : skupina) {
					if (t.sosedi.get("Levo") == "Prosto") {
						counter++;
					}
					if (t.sosedi.get("Desno") == "Prosto") {
						counter++;
					}
					if (t.sosedi.get("Gor") == "Prosto") {
						counter++;
					}
					if (t.sosedi.get("Dol") == "Prosto") {
						counter++;
					}
				}
				if (counter == 1) {
					for (Tocka t : skupina) {
						if (t.sosedi.get("Levo") == "Prosto") {
							return new Poteza(t.x-1-1,t.y-1);
						}
						if (t.sosedi.get("Desno") == "Prosto") {
							return new Poteza(t.x-1+1,t.y-1);
						}
						if (t.sosedi.get("Gor") == "Prosto") {
							return new Poteza(t.x-1,t.y-1-1);
						}
						if (t.sosedi.get("Dol") == "Prosto") {
							return new Poteza(t.x-1,t.y-1+1);
						}
					}
				}
			}
		}
		// zaenkrat random
		HashSet<Tocka> nakljucna_mnozica = new HashSet<Tocka>();
		for (Tocka t : igra.mozna_polja.values()) {
			if (t.zasedenost == null) {
				nakljucna_mnozica.add(t);
			}
		}
		Tocka nakljucna = Igra.randomTockaFromSet(nakljucna_mnozica);
		return new Poteza(nakljucna.x-1,nakljucna.y-1);
	}
	
	public static Boolean preveri_mozne_izide(Poteza poteza, Igra igra) {
		igra.preveri_skupine();
		igra.racunalnik2=true;
		String igram_barvo = igra.igralec_na_vrsti;
		Vodja vodja = new Vodja();
		vodja.dodaj_figuro(poteza.x, poteza.y, igra);
		
		igra.preveri_skupine();
		if (igra.preveri_igro() == "ZMAGA BELI" && igram_barvo == "Beli") {
			return true;
		}
		if (igra.preveri_igro() == "ZMAGA CRNI" && igram_barvo == "Crni") {
			return true;
		}
		if (igra.preveri_igro() == "ZMAGA CRNI" && igram_barvo == "Beli") {
			return false;
		}
		if (igra.preveri_igro() == "ZMAGA BELI" && igram_barvo == "Crni") {
			return false;
		}
		igra.preveri_skupine();
		Inteligenca inteligenca = new Inteligenca("Matic");
		Poteza poteza1 = inteligenca.izberiPotezo(igra);
		vodja.dodaj_figuro(poteza1.x, poteza1.y, igra);
		igra.preveri_skupine();
		if (igra.preveri_igro() == "ZMAGA BELI" && igram_barvo == "Crni") {
			return true;
		}
		if (igra.preveri_igro() == "ZMAGA CRNI" && igram_barvo == "Beli") {
			return true;
		}
		if (igra.preveri_igro() == "ZMAGA CRNI" && igram_barvo == "Crni") {
			return false;
		}
		if (igra.preveri_igro() == "ZMAGA BELI" && igram_barvo == "Beli") {
			return false;
		}
		igra.preveri_skupine();
		Poteza poteza2 = inteligenca.izberiPotezo(igra);
		vodja.dodaj_figuro(poteza2.x, poteza2.y, igra);
		igra.preveri_skupine();
		if (igra.preveri_igro() == "ZMAGA BELI" && igram_barvo == "Beli") {
			return true;
		}
		if (igra.preveri_igro() == "ZMAGA CRNI" && igram_barvo == "Crni") {
			return true;
		}
		if (igra.preveri_igro() == "ZMAGA CRNI" && igram_barvo == "Beli") {
			return false;
		}
		if (igra.preveri_igro() == "ZMAGA BELI" && igram_barvo == "Crni") {
			return false;
		}
		return true;
	}
	
}
