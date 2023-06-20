package logika;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Kloniraj {

	// CLASS ZA KLONIRANJE IGRE

	@SuppressWarnings("removal")
	public static Igra kloniraj(Igra igra1) {
		int dimenzija_igre = new Integer(igra1.dimenzija_igre);
		Igra igra2 = new Igra();
		String vrsta_igre2 = new String(igra1.vrsta_igre);
		int dimenzija_igre2 = new Integer(igra1.dimenzija_igre);
		Map<String, Tocka> mozna_polja2 = new HashMap<String, Tocka>();

		Set<Tocka> pomozna = new HashSet<Tocka>();
		for (Tocka t : igra1.mozna_polja.values()) {
			int x = new Integer(t.x);
			int y = new Integer(t.y);
			String ime = new String(t.ime);
			Tocka u = new Tocka(ime, x, y);
			if (t.zasedenost == null) {

			} else
				u.zasedenost = new String(t.zasedenost);

			u.sosedi = new HashMap<String, String>(t.sosedi);
			mozna_polja2.put(u.ime, u);
		}

		String igralec_na_vrsti2 = new String(igra1.igralec_na_vrsti);
		Boolean igramo2 = new Boolean(igra1.igramo);
		String racunalnik2 = new String(igra1.racunalnik);
		Boolean racunalnik22 = new Boolean(igra1.racunalnik2);
		Boolean igra_clovek2 = new Boolean(igra1.igra_clovek);
		igra2.vrsta_igre = vrsta_igre2;
		igra2.dimenzija_igre = dimenzija_igre2;
		igra2.mozna_polja = mozna_polja2;
		igra2.igralec_na_vrsti = igralec_na_vrsti2;
		igra2.igramo = igramo2;
		igra2.racunalnik = racunalnik2;
		igra2.racunalnik2 = racunalnik22;
		igra2.igra_clovek = igra_clovek2;
		igra2.pravila_igre = new String(igra1.pravila_igre);
		igra2.score_beli = new Integer(igra1.score_beli);
		igra2.score_crni = new Integer(igra1.score_crni);
		igra2.zmaga = new String(igra1.zmaga);
		igra2.stevilo_preskokov = new Integer(igra1.stevilo_preskokov);
		igra2.tezavnost_racunalnika = new Integer(igra1.tezavnost_racunalnika);
		igra2.preveri_skupine();

		return igra2;
	}
}
