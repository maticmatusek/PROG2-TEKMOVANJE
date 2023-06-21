package inteligenca;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import GUI.Okno;
import logika.Igra;
import logika.Kloniraj;
import splosno.Poteza;
import logika.Tocka;
import logika.Vodja;

public class Inteligenca extends splosno.KdoIgra {

	public String ime;

	public Inteligenca(String ime) {
		super(ime);
		this.ime = "Matic";
	}

	private int round(double x) {
		return (int) (x + 0.5);
	}

	public Poteza izberiPotezo(Igra igra) {

		if (!igra.igramo) {
			return null;
		}

		// ČE NA POLJU NI KAMENČKOV ZAČNE CCA NA SREDINI
		igra.preveri_skupine();
		String igram_barvo = igra.igralec_na_vrsti;
		if (igra.skupine_beli.size() == 0 && igra.skupine_crni.size() == 0) {

			return new Poteza(round(igra.dimenzija_igre / 2) - 1, round(igra.dimenzija_igre / 2) - 1);

		}
		// SKLOP POSKRBI, DA SE GRUPA KAMENČKOV BRANI, ČE JE GRUPA ATARI
		// POLEG TEGA TUDI POSKRBI ZA NAPAD, ČE JE NASPROTNIKOVA GRUPA V ATARIJU
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
							Igra klonirana_igra = Kloniraj.kloniraj(igra);
							klonirana_igra.odigraj(new Poteza(t.x - 1 - 1, t.y - 1));
							Set<HashSet<Tocka>> skupine = klonirana_igra.najdi_skupine_za_zbrisat("Beli");

							if (skupine.size() == 0) {

								return new Poteza(t.x - 1 - 1, t.y - 1);
							}

						}
						if (t.sosedi.get("Desno") == "Prosto") {
							Igra klonirana_igra = Kloniraj.kloniraj(igra);
							klonirana_igra.odigraj(new Poteza(t.x - 1 + 1, t.y - 1));
							Set<HashSet<Tocka>> skupine = klonirana_igra.najdi_skupine_za_zbrisat("Beli");

							if (skupine.size() == 0) {

								return new Poteza(t.x - 1 + 1, t.y - 1);
							}
						}
						if (t.sosedi.get("Gor") == "Prosto") {
							Igra klonirana_igra = Kloniraj.kloniraj(igra);
							klonirana_igra.odigraj(new Poteza(t.x - 1, t.y - 1 - 1));
							Set<HashSet<Tocka>> skupine = klonirana_igra.najdi_skupine_za_zbrisat("Beli");

							if (skupine.size() == 0) {

								return new Poteza(t.x - 1, t.y - 1 - 1);
							}
						}
						if (t.sosedi.get("Dol") == "Prosto") {
							Igra klonirana_igra = Kloniraj.kloniraj(igra);
							klonirana_igra.odigraj(new Poteza(t.x - 1, t.y - 1 + 1));
							Set<HashSet<Tocka>> skupine = klonirana_igra.najdi_skupine_za_zbrisat("Beli");

							if (skupine.size() == 0) {

								return new Poteza(t.x - 1, t.y - 1 + 1);
							}
						}
					}
				}
			}
		} else {
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
							Igra klonirana_igra = Kloniraj.kloniraj(igra);
							klonirana_igra.odigraj(new Poteza(t.x - 1 - 1, t.y - 1));
							Set<HashSet<Tocka>> skupine = klonirana_igra.najdi_skupine_za_zbrisat("Crni");

							if (skupine.size() == 0) {

								return new Poteza(t.x - 1 - 1, t.y - 1);
							}
						}
						if (t.sosedi.get("Desno") == "Prosto") {
							Igra klonirana_igra = Kloniraj.kloniraj(igra);
							klonirana_igra.odigraj(new Poteza(t.x - 1 + 1, t.y - 1));
							Set<HashSet<Tocka>> skupine = klonirana_igra.najdi_skupine_za_zbrisat("Crni");

							if (skupine.size() == 0) {

								return new Poteza(t.x - 1 + 1, t.y - 1);
							}
						}
						if (t.sosedi.get("Gor") == "Prosto") {
							Igra klonirana_igra = Kloniraj.kloniraj(igra);
							klonirana_igra.odigraj(new Poteza(t.x - 1, t.y - 1 - 1));
							Set<HashSet<Tocka>> skupine = klonirana_igra.najdi_skupine_za_zbrisat("Crni");

							if (skupine.size() == 0) {

								return new Poteza(t.x - 1, t.y - 1 - 1);
							}
						}
						if (t.sosedi.get("Dol") == "Prosto") {
							Igra klonirana_igra = Kloniraj.kloniraj(igra);
							klonirana_igra.odigraj(new Poteza(t.x - 1, t.y - 1 + 1));
							Set<HashSet<Tocka>> skupine = klonirana_igra.najdi_skupine_za_zbrisat("Crni");

							if (skupine.size() == 0) {

								return new Poteza(t.x - 1, t.y - 1 + 1);
							}
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
							Igra klonirana_igra = Kloniraj.kloniraj(igra);
							klonirana_igra.odigraj(new Poteza(t.x - 1 - 1, t.y - 1));
							Set<HashSet<Tocka>> skupine = klonirana_igra.najdi_skupine_za_zbrisat("Beli");

							if (skupine.size() == 0) {

								return new Poteza(t.x - 1 - 1, t.y - 1);
							}

						}
						if (t.sosedi.get("Desno") == "Prosto") {
							Igra klonirana_igra = Kloniraj.kloniraj(igra);
							klonirana_igra.odigraj(new Poteza(t.x - 1 + 1, t.y - 1));
							Set<HashSet<Tocka>> skupine = klonirana_igra.najdi_skupine_za_zbrisat("Beli");

							if (skupine.size() == 0) {

								return new Poteza(t.x - 1 + 1, t.y - 1);
							}
						}
						if (t.sosedi.get("Gor") == "Prosto") {
							Igra klonirana_igra = Kloniraj.kloniraj(igra);
							klonirana_igra.odigraj(new Poteza(t.x - 1, t.y - 1 - 1));
							Set<HashSet<Tocka>> skupine = klonirana_igra.najdi_skupine_za_zbrisat("Beli");

							if (skupine.size() == 0) {

								return new Poteza(t.x - 1, t.y - 1 - 1);
							}
						}
						if (t.sosedi.get("Dol") == "Prosto") {
							Igra klonirana_igra = Kloniraj.kloniraj(igra);
							klonirana_igra.odigraj(new Poteza(t.x - 1, t.y - 1 + 1));
							Set<HashSet<Tocka>> skupine = klonirana_igra.najdi_skupine_za_zbrisat("Beli");

							if (skupine.size() == 0) {

								return new Poteza(t.x - 1, t.y - 1 + 1);
							}
						}
					}
				}
			}
		} else {
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
							Igra klonirana_igra = Kloniraj.kloniraj(igra);
							klonirana_igra.odigraj(new Poteza(t.x - 1 - 1, t.y - 1));
							Set<HashSet<Tocka>> skupine = klonirana_igra.najdi_skupine_za_zbrisat("Crni");

							if (skupine.size() == 0) {

								return new Poteza(t.x - 1 - 1, t.y - 1);
							}
						}
						if (t.sosedi.get("Desno") == "Prosto") {
							Igra klonirana_igra = Kloniraj.kloniraj(igra);
							klonirana_igra.odigraj(new Poteza(t.x - 1 + 1, t.y - 1));
							Set<HashSet<Tocka>> skupine = klonirana_igra.najdi_skupine_za_zbrisat("Crni");

							if (skupine.size() == 0) {

								return new Poteza(t.x - 1 + 1, t.y - 1);
							}
						}
						if (t.sosedi.get("Gor") == "Prosto") {
							Igra klonirana_igra = Kloniraj.kloniraj(igra);
							klonirana_igra.odigraj(new Poteza(t.x - 1, t.y - 1 - 1));
							Set<HashSet<Tocka>> skupine = klonirana_igra.najdi_skupine_za_zbrisat("Crni");

							if (skupine.size() == 0) {

								return new Poteza(t.x - 1, t.y - 1 - 1);
							}
						}
						if (t.sosedi.get("Dol") == "Prosto") {
							Igra klonirana_igra = Kloniraj.kloniraj(igra);
							klonirana_igra.odigraj(new Poteza(t.x - 1, t.y - 1 + 1));
							Set<HashSet<Tocka>> skupine = klonirana_igra.najdi_skupine_za_zbrisat("Crni");

							if (skupine.size() == 0) {

								return new Poteza(t.x - 1, t.y - 1 + 1);
							}
						}
					}
				}
			}
		}

		// ZA MONTE CARLO METODO DEFINIRAMO KOT MOZNA POLJA ZGOLJ TISTE NE ZASEDENE
		// POZICIJE OKOLI SVOJIH IN NASPROTNIKOVIH GRUP

		String currentPlayer = igra.igralec_na_vrsti;

		Set<Poteza> mozne_poteze = new HashSet<Poteza>();

		igra.preveri_skupine();
		if (igra.skupine_beli.size() != 0 || igra.skupine_crni.size() != 0) {
			mozne_poteze = igra.prosta_polja_okoli_skupine("Beli");
			Set<Poteza> lala = igra.prosta_polja_okoli_skupine("Crni");
			Boolean papa = mozne_poteze.addAll(lala);
			;
		} else {
			for (Tocka t : igra.mozna_polja.values()) {
				if (t.zasedenost != null) {
				} else if (t.x == 1 | t.y == 1 | t.x == igra.dimenzija_igre | t.y == igra.dimenzija_igre) {

				} else
					mozne_poteze.add(new Poteza(t.x - 1, t.y - 1));
			}
		}

		List<Poteza> possibleMoves = new ArrayList<>(mozne_poteze);

		if (possibleMoves.isEmpty()) {
			return null;
		}

		// ZAČETEK MONTE CARLA
		int simulations = igra.tezavnost_racunalnika ;

		Map<Poteza, Integer> moveWins = new HashMap<>();

		for (Poteza move : possibleMoves) {
			int wins = 0;
			for (int i = 0; i < simulations; i++) {
				Igra clonedGame = Kloniraj.kloniraj(igra);
				clonedGame.pravila_igre = "CAPTURE";

				clonedGame.vrsta_igre = "RR";

				clonedGame.odigraj(move);
				if (clonedGame.igralec_na_vrsti.equals("Beli"))
					clonedGame.igralec_na_vrsti = "Crni";
				else
					clonedGame.igralec_na_vrsti = "Beli";

				// POSODOBIMO MOŽNE POTEZE GLEDE NA TO KATERE POTEZE SO NA NOVO OKOLI GRUP
				Set<Poteza> mozne_poteze1 = new HashSet<Poteza>();
				clonedGame.preveri_skupine();
				if (clonedGame.skupine_beli.size() != 0 || clonedGame.skupine_crni.size() != 0) {
					mozne_poteze1 = clonedGame.prosta_polja_okoli_skupine("Beli");
					Boolean trala = mozne_poteze1.addAll(clonedGame.prosta_polja_okoli_skupine("Crni"));
				} else {
					for (Tocka t : clonedGame.mozna_polja.values()) {
						if (t.zasedenost != null) {

						} else
							mozne_poteze1.add(new Poteza(t.x - 1, t.y - 1));
					}
				}

				Poteza trenutna_poteza = null;

				while (clonedGame.igramo) {
					for (Poteza p : mozne_poteze1) {

						if (!(igra.mozna_polja.get("(" + (p.x() + 1) + ", " + (p.y() + 1) + ")").zasedenost == null)) {
							mozne_poteze.remove(p);
						}
					}

					List<Poteza> possibleMovesSim = new ArrayList<>(mozne_poteze1);

					if (possibleMovesSim.isEmpty()) {

						break;

					}

					Poteza randomMove = possibleMovesSim.get(new Random().nextInt(possibleMovesSim.size()));

					clonedGame.odigraj(randomMove);

					clonedGame.preveri_skupine();
					if (clonedGame.skupine_beli.size() != 0 && clonedGame.skupine_crni.size() != 0) {
						mozne_poteze1 = clonedGame.prosta_polja_okoli_skupine("Beli");
						Boolean trala = mozne_poteze1.addAll(clonedGame.prosta_polja_okoli_skupine("Crni"));
					}

					for (Poteza p : mozne_poteze1) {
						if (!(igra.mozna_polja.get("(" + (p.x() + 1) + ", " + (p.y() + 1) + ")").zasedenost == null)) {
							mozne_poteze.remove(p);
						}
					}

					// ČE IGRO ZMAGAMO DOBI POTEZA S KATERO SMO ZAČELI TOČKO
					if (clonedGame.preveri_igro().equals("ZMAGA CRNI") && currentPlayer.equals("Crni")) {

						wins++;
						Set<HashSet<Tocka>> skupine = igra.najdi_skupine_za_zbrisat("Beli");

					}
					if (clonedGame.preveri_igro().equals("ZMAGA BELI") && currentPlayer.equals("Beli")) {

						wins++;

						Set<HashSet<Tocka>> skupine = igra.najdi_skupine_za_zbrisat("Crni");

					}
					if (clonedGame.igralec_na_vrsti.equals("Beli"))
						clonedGame.igralec_na_vrsti = "Crni";
					else
						clonedGame.igralec_na_vrsti = "Beli";
					Set<Poteza> za_unijo = clonedGame.dodaj_poteze(randomMove);
					Boolean trala = mozne_poteze1.addAll(za_unijo);
				}

			}
			moveWins.put(move, wins);

		}

		// TISTA ZAČETNA POTEZA Z NAJVEČ ZMAG SE IZVRŠI
		Poteza bestMove = null;
		int bestWins = -1;
		for (Map.Entry<Poteza, Integer> entry : moveWins.entrySet()) {
			Igra klonirana_igra = Kloniraj.kloniraj(igra);
			klonirana_igra.odigraj(entry.getKey());
			Set<HashSet<Tocka>> skupine = klonirana_igra.najdi_skupine_za_zbrisat(currentPlayer);

			
			if (entry.getValue() > bestWins && skupine.size() == 0) {
				bestMove = entry.getKey();
				bestWins = entry.getValue();
			}
		}
		if (bestMove == null) {
			
		}
		else {
			igra.poteza_racunalnika = "Računalnik igra x= " + (bestMove.x() + 1) + "  y= " + (bestMove.y() + 1);
		}
		
		return bestMove;
	}

}