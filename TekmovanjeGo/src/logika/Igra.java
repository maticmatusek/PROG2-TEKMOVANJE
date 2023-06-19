package logika;


import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import inteligenca.Inteligenca;
import splosno.Poteza;

public class Igra implements Cloneable{

	public String poteza_racunalnika;
	public String zmaga;
	public String vrsta_igre;
	public  int dimenzija_igre;
	public  Map<String,Tocka> mozna_polja;
	public Set<HashSet<Tocka>> skupine_beli ; 
	public Set<HashSet<Tocka>> skupine_crni ;
	public Set<HashSet<Tocka>> skupine_prazni ;
	public Set<Tocka> bele_tocke;
	public Set<Tocka> crne_tocke;
	public Set<Tocka> prazne_tocke;
	public String igralec_na_vrsti;
	public Boolean igramo;
	public String racunalnik;
	public Boolean racunalnik2;
	public Boolean igra_clovek;
	public String pravila_igre;
	public int score_beli;
	public int score_crni;
	public int stevilo_preskokov;
	public int tezavnost_racunalnika;

			
			public Igra() {
				this.poteza_racunalnika = "";
				this.tezavnost_racunalnika = 20;
				this.stevilo_preskokov = 0;
				this.score_beli = 0;
				this.score_crni = 0;
				this.pravila_igre= "GO";
				this.racunalnik2 = false;
				this.dimenzija_igre = 19;
				this.vrsta_igre = "ČČ";
				this.racunalnik = "";
				igra_clovek = true;
				mozna_polja = new HashMap<String,Tocka>();
				// OB USTVARITVI IGRE SE NAREDI 19X19 IGRALNO POLJE, KI NISO VEČ ALI MANJ VSE INFORMACIJE
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
				zmaga = "";
			}
			
			// FUNKCIJA NASTAVI DIMENZIJO IGRE V PRIMERU KASNEJŠE SPREMENITVE OZIROMA NASTAVITVE NOVE IGRE
			// POSKRBI DA SE IGRALNO POLJE NA NOVO ZGENERIRA
			public void nastavi_dimenzijo(int dimenzija) {
				this.dimenzija_igre = dimenzija;
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
			}
			
			// FUNKCIJA POSTAVI KAMNČEK NA POLJE IN POSODOBI INFORMACIJE NA IGRALNEM POLJU
			 public boolean odigraj(Poteza poteza) {
				 if (mozna_polja.get("("+(poteza.x() + 1 )+", "+(poteza.y()+1)+")").zasedenost == null) {
					 int x = poteza.x() +1 ;
					 int y = poteza.y() +1 ;
					 Tocka t = mozna_polja.get("("+x+", "+y+")");
					 String barva = igralec_na_vrsti;
					 t.zasedenost = igralec_na_vrsti;
						if (x!=1) {
							Tocka u = mozna_polja.get("("+(x-1)+", "+y+")");
							u.sosedi.put("Desno", barva);
							if (u.zasedenost != null) t.sosedi.put("Levo", u.zasedenost);
						}
						if (x!=dimenzija_igre) {
							Tocka u = mozna_polja.get("("+(x+1)+", "+y+")");
							u.sosedi.put("Levo", barva);
							if (u.zasedenost != null) t.sosedi.put("Desno", u.zasedenost);
						}
						if (y!=1) {
							Tocka u =mozna_polja.get("("+x+", "+(y-1)+")");
							u.sosedi.put("Dol", barva);
							if (u.zasedenost != null) t.sosedi.put("Gor", u.zasedenost);
						}
						if (y!=dimenzija_igre) {
							Tocka u = mozna_polja.get("("+x+", "+(y+1)+")");
							u.sosedi.put("Gor", barva);
							if (u.zasedenost != null) t.sosedi.put("Dol", u.zasedenost);
						}
					 
					 return true;
					 }
				else return false;
			 }
			 	
			// FUNKCIJA DEFINIRA VSE SKUPINE KAMENČKOV IN TUDI PRAZNIH PRESEČIŠČ, KI SE DRŽIJO SKUPAJ
			public void preveri_skupine(){
				skupine_beli = new HashSet<HashSet<Tocka>> ();
				skupine_crni = new HashSet<HashSet<Tocka>> ();
				prazne_tocke = new HashSet<Tocka>();
				skupine_prazni = new HashSet<HashSet<Tocka>> ();
				crne_tocke = new HashSet<Tocka>();
				bele_tocke = new HashSet<Tocka>();
				for (Tocka t : mozna_polja.values()) {
					if (t.zasedenost == null) prazne_tocke.add(t);
					else if (t.zasedenost.equals("Beli")) bele_tocke.add(t);
					else if (t.zasedenost.equals("Crni")) crne_tocke.add(t);
					
					
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
					if (t.sosedi.get("Levo").equals("Beli")) {
						Tocka u = mozna_polja.get("("+(t.x-1)+", "+t.y+")");
						if (bele_tocke.contains(u)) zbiralna_mnozica.add(u);
						
					}
					if (t.sosedi.get("Desno").equals("Beli")) {
						Tocka u = mozna_polja.get("("+(t.x+1)+", "+t.y+")");
						if (bele_tocke.contains(u)) zbiralna_mnozica.add(u);	
					}
					if (t.sosedi.get("Gor").equals("Beli")) {
						Tocka u = mozna_polja.get("("+t.x+", "+(t.y-1)+")");
						
						if (bele_tocke.contains(u)) zbiralna_mnozica.add(u);
					}
					if (t.sosedi.get("Dol").equals("Beli")) {
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
							if (t.sosedi.get("Levo").equals("Crni")) {
								Tocka u = mozna_polja.get("("+(t.x-1)+", "+t.y+")");
								if (crne_tocke.contains(u)) zbiralna_mnozica.add(u);
								
							}
							if (t.sosedi.get("Desno").equals("Crni")) {
								Tocka u = mozna_polja.get("("+(t.x+1)+", "+t.y+")");
								if (crne_tocke.contains(u)) zbiralna_mnozica.add(u);	
							}
							if (t.sosedi.get("Gor").equals("Crni")) {
								Tocka u = mozna_polja.get("("+t.x+", "+(t.y-1)+")");
								
								if (crne_tocke.contains(u)) zbiralna_mnozica.add(u);
							}
							if (t.sosedi.get("Dol").equals("Crni")) {
								Tocka u = mozna_polja.get("("+t.x+", "+(t.y+1)+")");
								if (crne_tocke.contains(u)) zbiralna_mnozica.add(u);
							}
							if (zbiralna_mnozica.size()==0) {
								if (neka_mnozica.size()!=0) skupine_crni.add(neka_mnozica);
							}
						}
						
						// PRAZNE TOČKE
						
						neka_mnozica = new HashSet<Tocka>();
						zbiralna_mnozica = new HashSet<Tocka>();
						
						while (prazne_tocke.size()!=0 || zbiralna_mnozica.size()!=0) {
							Tocka t = null;
							if (zbiralna_mnozica.size()==0) {
								if (neka_mnozica.size()!=0) skupine_prazni.add(neka_mnozica);
								
								neka_mnozica = new HashSet<Tocka>();
								t = randomTockaFromSet(prazne_tocke);
							}
							else {
								t = randomTockaFromSet(zbiralna_mnozica);
							}
							prazne_tocke.remove(t);
							zbiralna_mnozica.remove(t);
							neka_mnozica.add(t);
							if (t.sosedi.get("Levo") != "Crni" && t.sosedi.get("Levo") != "Beli" ) {
								Tocka u = mozna_polja.get("("+(t.x-1)+", "+t.y+")");
								if (prazne_tocke.contains(u)) zbiralna_mnozica.add(u);
								
							}
							if (t.sosedi.get("Desno") != "Crni" && t.sosedi.get("Desno") != "Beli" ) {
								Tocka u = mozna_polja.get("("+(t.x+1)+", "+t.y+")");
								if (prazne_tocke.contains(u)) zbiralna_mnozica.add(u);	
							}
							if (t.sosedi.get("Gor") != "Crni" && t.sosedi.get("Levo") != "Gor" ) {
								Tocka u = mozna_polja.get("("+t.x+", "+(t.y-1)+")");
								
								if (prazne_tocke.contains(u)) zbiralna_mnozica.add(u);
							}
							if (t.sosedi.get("Dol") != "Crni" && t.sosedi.get("Dol") != "Beli" ) {
								Tocka u = mozna_polja.get("("+t.x+", "+(t.y+1)+")");
								if (prazne_tocke.contains(u)) zbiralna_mnozica.add(u);
							}
							if (zbiralna_mnozica.size()==0) {
								if (neka_mnozica.size()!=0) skupine_prazni.add(neka_mnozica);
							}
						}
						
						
			}
			
			// FUNKCIJA PREVERI STANJE IGRE CAPTURE GO
			// UPORABNA TUDI PRI IGRI GO ZA PREVERJANJE, ČE JE POTREBNO SKUPINO IZBRISATI
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
			
			// FUNKCIJA VRNE NAKLJUČEN ELEMENT IN MNOŽICE TOČK
			public static Tocka randomTockaFromSet(Set<Tocka> set) {
				List<Tocka> list = new ArrayList<>(set);

				int size = list.size();
				int randIdx = new Random().nextInt(size);

				Tocka randomElem = list.get(randIdx);
				return randomElem;
			}
			

			// FUNKCIJA POIŠČE PROSTA POLJA OKOLI SKUPIN DOLOČENE BARVE
			public Set<Poteza> prosta_polja_okoli_skupine(String barva) {
				Set<Poteza>okoli_skupin = new HashSet<>();
				preveri_skupine();
				Set<HashSet<Tocka>> skupine = new HashSet<HashSet<Tocka>>();
				if (barva.equals("Beli")) skupine = skupine_beli;
				else skupine = skupine_crni;
				for (Set<Tocka> s:skupine) {
					for (Tocka t:s) {
						if (t.sosedi.get("Levo").equals("Prosto")) {
							okoli_skupin.add(new Poteza(t.x-2,t.y-1));
						}
						if (t.sosedi.get("Desno").equals("Prosto")) {
							okoli_skupin.add(new Poteza(t.x,t.y-1));
						}
						if (t.sosedi.get("Gor").equals("Prosto")) {
							okoli_skupin.add(new Poteza(t.x-1,t.y-2));
						}
						if (t.sosedi.get("Dol").equals("Prosto")) {
							okoli_skupin.add(new Poteza(t.x-1,t.y));
						}
					}
				}
				return okoli_skupin;
			}
			
			// FUNKCIJA VRNE MNOŽICO PROSTIH POLJ, OKOLI VHODNEGA POLJA
			public Set<Poteza> dodaj_poteze(Poteza poteza){
				Set<Poteza>izhod = new HashSet<>();
				int x = poteza.x()+1;
				int y = poteza.y()+1;
				if (x+1 <= dimenzija_igre && x+1>=1 &&mozna_polja.get("("+(x+1)+", "+y+")").equals("Prazno") ) izhod.add(new Poteza(x,y-1));
				if (x-1 <= dimenzija_igre && x-1>=1 &&mozna_polja.get("("+(x-1)+", "+y+")").equals("Prazno") ) izhod.add(new Poteza(x-2,y-1));
				if (y+1 <= dimenzija_igre && y+1>=1 &&mozna_polja.get("("+x+", "+(y+1)+")").equals("Prazno") ) izhod.add(new Poteza(x-1,y));
				if (y-1 <= dimenzija_igre && y-1>=1 &&mozna_polja.get("("+x+", "+(y-1)+")").equals("Prazno") ) izhod.add(new Poteza(x-1,y-2));
				return izhod;
			}
			
			// FUNKCIJA OVREDNOTI KONČNO STANJE IGRE GO
			public void preveri_igro_go() {
				igramo = false;
				preveri_skupine();
				
				for (HashSet<Tocka> skupina : skupine_prazni) {
					HashSet<String> preverjanje = new HashSet<String>();
					for (Tocka t : skupina) {
						preverjanje.add(t.sosedi.get("Levo"));
						preverjanje.add(t.sosedi.get("Desno"));
						preverjanje.add(t.sosedi.get("Gor"));
						preverjanje.add(t.sosedi.get("Dol"));
					}
					if (!preverjanje.contains("Beli") && preverjanje.contains("Crni")) {score_crni = score_crni + skupina.size();}
					else if (!preverjanje.contains("Crni") && preverjanje.contains("Beli")) {score_beli = score_beli + skupina.size();};
				}
				
				
				if (score_beli > score_crni) {
					zmaga = "Beli";
			
				}
				else if (score_beli < score_crni) zmaga = "Crni";
				else zmaga = "IZENAČENO";
			}
			
			// FUNKCIJA IZBRIŠE SKUPINO, KI JE BILA ZAVZETA V IGRI GO
			public void izbrisi_skupino(HashSet<Tocka> skupina) {
				for (Tocka t : skupina) {
					t.zasedenost = null;
					if (t.x+1 <= dimenzija_igre && mozna_polja.get("("+(t.x+1)+", "+t.y+")").sosedi.get("Levo")!="Nemore") mozna_polja.get("("+(t.x+1)+", "+t.y+")").sosedi.replace("Levo","Prosto");
					if (t.x-1 >= 1 && mozna_polja.get("("+(t.x-1)+", "+t.y+")").sosedi.get("Desno")!="Nemore") mozna_polja.get("("+(t.x-1)+", "+t.y+")").sosedi.replace("Desno","Prosto");
					if (t.y+1 <= dimenzija_igre && mozna_polja.get("("+t.x+", "+(t.y+1)+")").sosedi.get("Gor")!="Nemore") mozna_polja.get("("+t.x+", "+(t.y+1)+")").sosedi.replace("Gor","Prosto");
					if (t.y-1 >= 1 && mozna_polja.get("("+t.x+", "+(t.y-1)+")").sosedi.get("Dol")!="Nemore") mozna_polja.get("("+t.x+", "+(t.y-1)+")").sosedi.replace("Dol","Prosto");
				}
			}
		
			// FUNKCIJA NAJDE SKUPINO, KI JE BILA ZAVZETA V IGRI GO
			public Set<HashSet<Tocka>> najdi_skupine_za_zbrisat(String barva){
				preveri_skupine();
				String zasedenost = null;
				Set<HashSet<Tocka>> skupine = new HashSet<HashSet<Tocka>>();
				Set<HashSet<Tocka>> skupine_barve = new HashSet<HashSet<Tocka>>();
				if (barva.equals("Beli")) {
					skupine_barve = skupine_beli;
					zasedenost = "Crni";
				}
				else {
					skupine_barve = skupine_crni;
					zasedenost = "Beli";
				}
				
				for (HashSet<Tocka> skupina : skupine_barve ) {
					HashSet<String> preverjanje = new HashSet<String>();
					for (Tocka t : skupina) {
						preverjanje.add(t.sosedi.get("Levo"));
						preverjanje.add(t.sosedi.get("Desno"));
						preverjanje.add(t.sosedi.get("Gor"));
						preverjanje.add(t.sosedi.get("Dol"));
					}
					if (preverjanje.contains(zasedenost) && !preverjanje.contains("Prosto")) {
						skupine.add(skupina);
					}
				}
				return skupine;
			}
		}

