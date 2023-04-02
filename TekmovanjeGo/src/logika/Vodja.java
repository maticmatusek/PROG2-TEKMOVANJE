package logika;


import java.util.concurrent.TimeUnit;

import javax.swing.SwingWorker;

import inteligenca.Inteligenca;

public class Vodja {
	
	public static void dodaj_figuro(int x, int y, Igra igra) {
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
			
			
			SwingWorker<Void, Void> worker = new SwingWorker<Void, Void> () {
				@Override
				protected Void doInBackground() {
				try {TimeUnit.SECONDS.sleep(1);} catch (Exception e) {};
				return null;
				}
				@Override
				protected void done () {

				

					Inteligenca inteligenca = new Inteligenca("Matic");
					inteligenca.igra = igra;
					Poteza poteza = inteligenca.izberiPotezo(igra);
					dodaj_figuro(poteza.x,poteza.y,igra);
					
					
				}
				};
				worker.execute();
			
		}
		else igra.igra_clovek = true;
	}
	
}
