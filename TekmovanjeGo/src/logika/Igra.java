package logika;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import inteligenca.Inteligenca;

public class Igra {

	public String vrsta_igre;
	public  int dimenzija_igre;
	public  Map<String,Tocka> mozna_polja;
	public Set<HashSet<Tocka>> skupine_beli ; 
	public Set<HashSet<Tocka>> skupine_crni ;
	public Set<Tocka> bele_tocke;
	public Set<Tocka> crne_tocke;
	public String igralec_na_vrsti;
	public Boolean igramo;
	public String racunalnik;
	public Boolean racunalnik2;
	public Boolean igra_clovek;

			
			public Igra() {
				this.racunalnik2 = false;
				this.dimenzija_igre = 9;
				this.vrsta_igre = "ČČ";
				this.racunalnik = null;
				igra_clovek = true;
				mozna_polja = new HashMap<String,Tocka>();
				for (int i = 1; i<=dimenzija_igre;i++) {
					for (int j = 1; j<=dimenzija_igre;j++) {
						mozna_polja.put("("+i+", "+j+")",new Tocka( "("+i+", "+j+")",i,j));
						
					}
				}
				for (Tocka t : mozna_polja.values()) {
					if (t.x==1) t.sosedi.replace("Levo", "Nemore");
					if (t.x==dimenzija_igre) t.sosedi.replace("Desno", "Nemore");
					if (t.y==1) t.sosedi.replace("Gor", "Nemore");
					if (t.y==dimenzija_igre) t.sosedi.replace("Dol", "Nemore");
				}
				skupine_beli = new HashSet<HashSet<Tocka>> ();
				skupine_crni = new HashSet<HashSet<Tocka>> ();
				crne_tocke = new HashSet<Tocka>();
				bele_tocke = new HashSet<Tocka>();
				igralec_na_vrsti = "Crni";
				igramo = true;
			}
			
			public void nastavi_dimenzijo(int dimenzija) {
				this.dimenzija_igre = dimenzija;
				for (int i = 1; i<=dimenzija_igre;i++) {
					for (int j = 1; j<=dimenzija_igre;j++) {
						mozna_polja.put("("+i+", "+j+")",new Tocka( "("+i+", "+j+")",i,j));
						
					}
				}
				for (Tocka t : mozna_polja.values()) {
					if (t.x==1) t.sosedi.replace("Levo", "Nemore");
					if (t.x==dimenzija_igre) t.sosedi.replace("Desno", "Nemore");
					if (t.y==1) t.sosedi.replace("Gor", "Nemore");
					if (t.y==dimenzija_igre) t.sosedi.replace("Dol", "Nemore");
				}
			}
			
			 boolean odigraj(Poteza poteza) {
				 if (mozna_polja.get("("+poteza.x+", "+poteza.y+")").zasedenost == null) {
					 mozna_polja.get("("+poteza.x+", "+poteza.y+")").zasedenost = igralec_na_vrsti;
					 return true;}
				else return false;
			 }
			 	
			
			public void preveri_skupine(){
				skupine_beli = new HashSet<HashSet<Tocka>> ();
				skupine_crni = new HashSet<HashSet<Tocka>> ();
				crne_tocke = new HashSet<Tocka>();
				bele_tocke = new HashSet<Tocka>();
				for (Tocka t : mozna_polja.values()) {
					if (t.zasedenost == "Beli") bele_tocke.add(t);
					else if (t.zasedenost == "Crni") crne_tocke.add(t);
				}
				
				// BELE TOČKE
				
				HashSet<Tocka> neka_mnozica = new HashSet<Tocka>();
				Set<Tocka> zbiralna_mnozica = new HashSet<Tocka>();
				
				while (bele_tocke.size()!=0 || zbiralna_mnozica.size()!=0) {
					Tocka t = null;
					if (zbiralna_mnozica.size()==0) {
						if (neka_mnozica.size()!=0) skupine_beli.add(neka_mnozica);
						
						neka_mnozica = new HashSet<Tocka>();
						t = randomTockaFromSet(bele_tocke);
					}
					else {
						t = randomTockaFromSet(zbiralna_mnozica);
					}
					bele_tocke.remove(t);
					zbiralna_mnozica.remove(t);
					neka_mnozica.add(t);
					if (t.sosedi.get("Levo") == "Beli") {
						Tocka u = mozna_polja.get("("+(t.x-1)+", "+t.y+")");
						if (bele_tocke.contains(u)) zbiralna_mnozica.add(u);
						
					}
					if (t.sosedi.get("Desno") == "Beli") {
						Tocka u = mozna_polja.get("("+(t.x+1)+", "+t.y+")");
						if (bele_tocke.contains(u)) zbiralna_mnozica.add(u);	
					}
					if (t.sosedi.get("Gor") == "Beli") {
						Tocka u = mozna_polja.get("("+t.x+", "+(t.y-1)+")");
						
						if (bele_tocke.contains(u)) zbiralna_mnozica.add(u);
					}
					if (t.sosedi.get("Dol") == "Beli") {
						Tocka u = mozna_polja.get("("+t.x+", "+(t.y+1)+")");
						if (bele_tocke.contains(u)) zbiralna_mnozica.add(u);
					}
					if (zbiralna_mnozica.size()==0) {
						if (neka_mnozica.size()!=0) skupine_beli.add(neka_mnozica);
					}
				}
				
				// ČRNE TOČKE
				
						neka_mnozica = new HashSet<Tocka>();
						zbiralna_mnozica = new HashSet<Tocka>();
						
						while (crne_tocke.size()!=0 || zbiralna_mnozica.size()!=0) {
							Tocka t = null;
							if (zbiralna_mnozica.size()==0) {
								if (neka_mnozica.size()!=0) skupine_crni.add(neka_mnozica);
								
								neka_mnozica = new HashSet<Tocka>();
								t = randomTockaFromSet(crne_tocke);
							}
							else {
								t = randomTockaFromSet(zbiralna_mnozica);
							}
							crne_tocke.remove(t);
							zbiralna_mnozica.remove(t);
							neka_mnozica.add(t);
							if (t.sosedi.get("Levo") == "Crni") {
								Tocka u = mozna_polja.get("("+(t.x-1)+", "+t.y+")");
								if (crne_tocke.contains(u)) zbiralna_mnozica.add(u);
								
							}
							if (t.sosedi.get("Desno") == "Crni") {
								Tocka u = mozna_polja.get("("+(t.x+1)+", "+t.y+")");
								if (crne_tocke.contains(u)) zbiralna_mnozica.add(u);	
							}
							if (t.sosedi.get("Gor") == "Crni") {
								Tocka u = mozna_polja.get("("+t.x+", "+(t.y-1)+")");
								
								if (crne_tocke.contains(u)) zbiralna_mnozica.add(u);
							}
							if (t.sosedi.get("Dol") == "Crni") {
								Tocka u = mozna_polja.get("("+t.x+", "+(t.y+1)+")");
								if (crne_tocke.contains(u)) zbiralna_mnozica.add(u);
							}
							if (zbiralna_mnozica.size()==0) {
								if (neka_mnozica.size()!=0) skupine_crni.add(neka_mnozica);
							}
						}
			}
			
			public String preveri_igro() {
				preveri_skupine();
				for (HashSet<Tocka> skupina : skupine_crni) {
					HashSet<String> neka_mnozica = new HashSet<String>();
					for (Tocka t : skupina) {
						neka_mnozica.add(t.sosedi.get("Levo"));
						neka_mnozica.add(t.sosedi.get("Desno"));
						neka_mnozica.add(t.sosedi.get("Gor"));
						neka_mnozica.add(t.sosedi.get("Dol"));
					}
					if (!neka_mnozica.contains("Prosto")) {
						igramo =false;
						return "ZMAGA BELI";
					}
				}
				for (HashSet<Tocka> skupina : skupine_beli) {
					HashSet<String> neka_mnozica = new HashSet<String>();
					for (Tocka t : skupina) {
						neka_mnozica.add(t.sosedi.get("Levo"));
						neka_mnozica.add(t.sosedi.get("Desno"));
						neka_mnozica.add(t.sosedi.get("Gor"));
						neka_mnozica.add(t.sosedi.get("Dol"));
					}
					if (!neka_mnozica.contains("Prosto")) {
						igramo = false;
						return "ZMAGA CRNI";}
				}
				return "";
			}
			
			public static Tocka randomTockaFromSet(Set<Tocka> set) {
				List<Tocka> list = new ArrayList<>(set);

				int size = list.size();
				int randIdx = new Random().nextInt(size);

				Tocka randomElem = list.get(randIdx);
				return randomElem;
			}
			
//			public void dodaj_figuro(int x, int y, String barva) {
//				Tocka t = mozna_polja.get("("+x+", "+y+")");
//				t.zasedenost = barva;
//				if (x!=1) {
//					Tocka u = mozna_polja.get("("+(x-1)+", "+y+")");
//					u.sosedi.put("Desno", barva);
//					if (u.zasedenost != null) t.sosedi.put("Levo", u.zasedenost);
//				}
//				if (x!=dimenzija_igre) {
//					Tocka u = mozna_polja.get("("+(x+1)+", "+y+")");
//					u.sosedi.put("Levo", barva);
//					if (u.zasedenost != null) t.sosedi.put("Desno", u.zasedenost);
//				}
//				if (y!=1) {
//					Tocka u = mozna_polja.get("("+x+", "+(y-1)+")");
//					u.sosedi.put("Dol", barva);
//					if (u.zasedenost != null) t.sosedi.put("Gor", u.zasedenost);
//				}
//				if (y!=dimenzija_igre) {
//					Tocka u = mozna_polja.get("("+x+", "+(y+1)+")");
//					u.sosedi.put("Gor", barva);
//					if (u.zasedenost != null) t.sosedi.put("Dol", u.zasedenost);
//				}
//				if (igralec_na_vrsti == "Beli") igralec_na_vrsti = "Crni";
//				else igralec_na_vrsti = "Beli";
//				preveri_igro();
//				if (racunalnik == igralec_na_vrsti) {
//					Poteza poteza = Inteligenca.izberiPotezo();
//				}
//			}
			
		}
