package logika;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Kloniraj {

	@SuppressWarnings("removal")
	public static Igra kloniraj(Igra igra1) {
			Igra igra2 = new Igra();
			  String vrsta_igre2 = new String(igra1.vrsta_igre);
			  int dimenzija_igre2 = new Integer(igra1.dimenzija_igre);
			  Map<String,Tocka> mozna_polja2 = new HashMap<String,Tocka>(igra1.mozna_polja);
			  Set<HashSet<Tocka>> skupine_beli2 = new HashSet<HashSet<Tocka>>(igra1.skupine_beli);
			  Set<HashSet<Tocka>> skupine_crni2 = new HashSet<HashSet<Tocka>>(igra1.skupine_crni);
			  Set<Tocka> bele_tocke2 = new HashSet<Tocka>(igra1.bele_tocke);
			  Set<Tocka> crne_tocke2 = new HashSet<Tocka>(igra1.crne_tocke);
			  String igralec_na_vrsti2 = new String(igra1.igralec_na_vrsti);
			  Boolean igramo2 = new Boolean(igra1.igramo);
			  String racunalnik2 = new String(igra1.racunalnik);
			  Boolean racunalnik22 = new Boolean(igra1.racunalnik2);
		 	  Boolean igra_clovek2 = new Boolean(igra1.igra_clovek);
		 	  igra2.vrsta_igre = vrsta_igre2;
		 	  igra2.dimenzija_igre = dimenzija_igre2;
		 	  igra2.mozna_polja = mozna_polja2;
		 	  igra2.skupine_beli = skupine_beli2;
		 	  igra2.skupine_crni = skupine_crni2;
		 	  igra2.bele_tocke = bele_tocke2;
		 	  igra2.crne_tocke = crne_tocke2;
		 	  igra2.igralec_na_vrsti = igralec_na_vrsti2;
		 	  igra2.igramo = igramo2;
		 	  igra2.racunalnik = racunalnik2;
		 	  igra2.racunalnik2 = racunalnik22;
		 	  igra2.igra_clovek = igra_clovek2;
		 	  
		 	  
			return igra2;
	}
}
