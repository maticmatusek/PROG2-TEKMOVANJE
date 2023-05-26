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

public class Inteligenca extends splosno.KdoIgra{
	
	public String ime;

	public Inteligenca(String ime) {
		super(ime);
		this.ime = "Matic";
	}

	private int round(double x) {
		return (int)(x+0.5);
	}
	
	public Poteza izberiPotezo(Igra igra) {
		
		
		
        // Check if the game is still ongoing
        if (!igra.igramo) {
            return null;
        }

        
		igra.preveri_skupine();
		String igram_barvo = igra.igralec_na_vrsti;
		if (igra.skupine_beli.size() == 0 && igra.skupine_crni.size() == 0) {
		return new Poteza( round(igra.dimenzija_igre/2)-1, round(igra.dimenzija_igre/2)-1 );
			
		}
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
						if (t.sosedi.get("Levo") == "Prosto" ) {
							return new Poteza(t.x-1-1,t.y-1);
						}
						if (t.sosedi.get("Desno") == "Prosto" ) {
							return new Poteza(t.x-1+1,t.y-1);
						}
						if (t.sosedi.get("Gor") == "Prosto" ) {
							return new Poteza(t.x-1,t.y-1-1);
						}
						if (t.sosedi.get("Dol") == "Prosto" ) {
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
						if (t.sosedi.get("Levo") == "Prosto" ) {
							return new Poteza(t.x-1-1,t.y-1);
						}
						if (t.sosedi.get("Desno") == "Prosto" ) {
							return new Poteza(t.x-1+1,t.y-1);
						}
						if (t.sosedi.get("Gor") == "Prosto" ) {
							return new Poteza(t.x-1,t.y-1-1);
						}
						if (t.sosedi.get("Dol") == "Prosto" ) {
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
						if (t.sosedi.get("Levo") == "Prosto" ) {
							return new Poteza(t.x-1-1,t.y-1);
						}
						if (t.sosedi.get("Desno") == "Prosto" ) {
							return new Poteza(t.x-1+1,t.y-1);
						}
						if (t.sosedi.get("Gor") == "Prosto" ) {
							return new Poteza(t.x-1,t.y-1-1);
						}
						if (t.sosedi.get("Dol") == "Prosto" ) {
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
						if (t.sosedi.get("Levo") == "Prosto" ) {
							return new Poteza(t.x-1-1,t.y-1);
						}
						if (t.sosedi.get("Desno") == "Prosto" ) {
							return new Poteza(t.x-1+1,t.y-1);
						}
						if (t.sosedi.get("Gor") == "Prosto") {
							return new Poteza(t.x-1,t.y-1-1);
						}
						if (t.sosedi.get("Dol") == "Prosto" ) {
							return new Poteza(t.x-1,t.y-1+1);
						}
					}
				}
			}
		}
        
        // Get the current player
        String currentPlayer = igra.igralec_na_vrsti;

        
        Set<Poteza> mozne_poteze = new HashSet<Poteza>();
       
        
        
        System.out.println(mozne_poteze);
        System.out.println(mozne_poteze.size());
        
        igra.preveri_skupine();
        if (igra.skupine_beli.size()!=0 || igra.skupine_crni.size()!=0) {
        	mozne_poteze = igra.prosta_polja_okoli_skupine("Beli");
        	Set<Poteza> lala = igra.prosta_polja_okoli_skupine("Crni");
        	Boolean papa = mozne_poteze.addAll(lala); ;
        }
        else {
        	for(Tocka t: igra.mozna_polja.values()) {
            	if (t.zasedenost != null) {
            	}
            	else if ( t.x == 1 | t.y == 1 | t.x == igra.dimenzija_igre | t.y == igra.dimenzija_igre) {
            		
            	}
            	else mozne_poteze.add(new Poteza(t.x-1,t.y-1));
            }
        }
        
        
        // Get all possible moves
        List<Poteza> possibleMoves = new ArrayList<>(mozne_poteze);

        
        
        // If there are no possible moves, return null
        if (possibleMoves.isEmpty()) {
            return null;
        }

        // Perform Monte Carlo simulations
        int simulations = 400;
        Map<Poteza, Integer> moveWins = new HashMap<>();
        for (Poteza move : possibleMoves) {
            int wins = 0;
            for (int i = 0; i < simulations; i++) {
                // Clone the game state
                Igra clonedGame = Kloniraj.kloniraj(igra);
                // Make a move in the cloned game
                clonedGame.vrsta_igre = "RR";
              //  System.out.println("Igra: "+ clonedGame.igralec_na_vrsti);
               // System.out.println("Poteza: "+ move);
                clonedGame.odigraj(move);
                if (clonedGame.igralec_na_vrsti.equals("Beli")) clonedGame.igralec_na_vrsti = "Crni";
    			else clonedGame.igralec_na_vrsti = "Beli";

                // Perform random simulations until the end of the game
                
                
                
                Set<Poteza> mozne_poteze1 = new HashSet<Poteza>();
                

                clonedGame.preveri_skupine();
                if (clonedGame.skupine_beli.size()!=0 || clonedGame.skupine_crni.size()!=0) {
                	mozne_poteze1 = clonedGame.prosta_polja_okoli_skupine("Beli");
                	Boolean trala = mozne_poteze1.addAll(clonedGame.prosta_polja_okoli_skupine("Crni"));
                }
                else {
                	for(Tocka t: clonedGame.mozna_polja.values()) {
                    	if (t.zasedenost != null) {
                    		
                    	}
                    	else mozne_poteze1.add(new Poteza(t.x-1,t.y-1));
                    }
                }
                
                // Get all possible moves
                
                
                Poteza trenutna_poteza = null;
                
                while (clonedGame.igramo) {
                	
                	List<Poteza> possibleMovesSim = new ArrayList<>(mozne_poteze1);
                    
                    
                    
   

                    if (possibleMovesSim.isEmpty()) {
                        break;
                    }

                    // Choose a random move
                    Poteza randomMove = possibleMovesSim.get(new Random().nextInt(possibleMovesSim.size()));

                    // Make the move
                 //   System.out.println("Igra: "+ clonedGame.igralec_na_vrsti);
                //    System.out.println("Poteza: "+ randomMove);
                    clonedGame.odigraj(randomMove);
                    clonedGame.preveri_skupine();
                    if (clonedGame.skupine_beli.size()!=0 && clonedGame.skupine_crni.size()!=0) {
                    	mozne_poteze1 = clonedGame.prosta_polja_okoli_skupine("Beli");
                    	Boolean trala = mozne_poteze1.addAll(clonedGame.prosta_polja_okoli_skupine("Crni"));
                    }
                    
                    
                
                	
                	
                    if (clonedGame.preveri_igro().equals("ZMAGA CRNI")  && currentPlayer.equals("Crni")) wins++;
                    if (clonedGame.preveri_igro().equals("ZMAGA BELI") && currentPlayer.equals("Beli")) wins++;
                    if (clonedGame.igralec_na_vrsti.equals("Beli")) clonedGame.igralec_na_vrsti = "Crni";
        			else clonedGame.igralec_na_vrsti = "Beli";
                    Set<Poteza> za_unijo = clonedGame.dodaj_poteze(randomMove);
                    Boolean trala = mozne_poteze1.addAll(za_unijo);
                }
                
                
                // Check the winner
                
               // System.out.println("kdo zmaga : " + clonedGame.preveri_igro());

               
            }
            moveWins.put(move, wins);

        }

        // Choose the move with the highest number of wins
        Poteza bestMove = null;
        int bestWins = -1;
        for (Map.Entry<Poteza, Integer> entry : moveWins.entrySet()) {
            if (entry.getValue() > bestWins) {
                bestMove = entry.getKey();
                bestWins = entry.getValue();
            }
        }

        System.out.println("best move : " + bestMove);
        return bestMove;
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public Poteza izberiPotezo1(Igra igra) {
		igra.preveri_skupine();
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
						if (t.sosedi.get("Levo") == "Prosto" ) {
							return new Poteza(t.x-1-1,t.y-1);
						}
						if (t.sosedi.get("Desno") == "Prosto" ) {
							return new Poteza(t.x-1+1,t.y-1);
						}
						if (t.sosedi.get("Gor") == "Prosto" ) {
							return new Poteza(t.x-1,t.y-1-1);
						}
						if (t.sosedi.get("Dol") == "Prosto" ) {
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
						if (t.sosedi.get("Levo") == "Prosto" ) {
							return new Poteza(t.x-1-1,t.y-1);
						}
						if (t.sosedi.get("Desno") == "Prosto" ) {
							return new Poteza(t.x-1+1,t.y-1);
						}
						if (t.sosedi.get("Gor") == "Prosto" ) {
							return new Poteza(t.x-1,t.y-1-1);
						}
						if (t.sosedi.get("Dol") == "Prosto" ) {
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
						if (t.sosedi.get("Levo") == "Prosto" ) {
							return new Poteza(t.x-1-1,t.y-1);
						}
						if (t.sosedi.get("Desno") == "Prosto" ) {
							return new Poteza(t.x-1+1,t.y-1);
						}
						if (t.sosedi.get("Gor") == "Prosto" ) {
							return new Poteza(t.x-1,t.y-1-1);
						}
						if (t.sosedi.get("Dol") == "Prosto" ) {
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
						if (t.sosedi.get("Levo") == "Prosto" ) {
							return new Poteza(t.x-1-1,t.y-1);
						}
						if (t.sosedi.get("Desno") == "Prosto" ) {
							return new Poteza(t.x-1+1,t.y-1);
						}
						if (t.sosedi.get("Gor") == "Prosto") {
							return new Poteza(t.x-1,t.y-1-1);
						}
						if (t.sosedi.get("Dol") == "Prosto" ) {
							return new Poteza(t.x-1,t.y-1+1);
						}
					}
				}
			}
		}
		Set<HashSet<Tocka>> skupek = igra.skupine_beli;
		if (igram_barvo == "Crni") {
			skupek = igra.skupine_crni;
		}
			for (HashSet<Tocka> skupina : skupek) {
				Tocka nova = new Tocka("bla",100,110);
				for (Tocka t : skupina) {
					int counter = 0;
					Boolean gor = false;
					Boolean dol = false;
					Boolean levo = false;
					Boolean desno = false;
					if (t.sosedi.get("Levo") == "Prosto") {
						counter++;
						levo = true;
					}
					if (t.sosedi.get("Desno") == "Prosto") {
						counter++;
						desno = true;
					}
					if (t.sosedi.get("Gor") == "Prosto") {
						counter++;
						gor = true;
					}
					if (t.sosedi.get("Dol") == "Prosto") {
						counter++;
						dol = true;
					}
					if (counter == 4) {
						if (igra.mozna_polja.get("("+t.x+", "+(t.y+1)+")").sosedi.get("Dol")=="Prosto") {
							return new Poteza(t.x-1,t.y-1+1);
						}
						if (igra.mozna_polja.get("("+(t.x+1)+", "+t.y+")").sosedi.get("Desno")=="Prosto") {
							return new Poteza(t.x-1+1,t.y-1);
						}
						if (igra.mozna_polja.get("("+(t.x-1)+", "+t.y+")").sosedi.get("Levo")=="Prosto") {
							return new Poteza(t.x-1-1,t.y-1);
						}
						if (igra.mozna_polja.get("("+t.x+", "+(t.y-1)+")").sosedi.get("Gor")=="Prosto") {
							return new Poteza(t.x-1,t.y-1-1);
						}
					}
					if (counter == 3) {
						if (dol && igra.mozna_polja.get("("+t.x+", "+(t.y+1)+")").sosedi.get("Dol")=="Prosto") {
							return new Poteza(t.x-1,t.y-1+1);
						}
						if (desno && igra.mozna_polja.get("("+(t.x+1)+", "+t.y+")").sosedi.get("Desno")=="Prosto") {
							return new Poteza(t.x-1+1,t.y-1);
						}
						if (levo && igra.mozna_polja.get("("+(t.x-1)+", "+t.y+")").sosedi.get("Levo")=="Prosto" ) {
							return new Poteza(t.x-1-1,t.y-1);
						}
						if (gor && igra.mozna_polja.get("("+t.x+", "+(t.y-1)+")").sosedi.get("Gor")=="Prosto" ) {
							return new Poteza(t.x-1,t.y-1-1);
						}
					}
				}
			}
		
		// zaenkrat random
		HashSet<Tocka> nakljucna_mnozica = new HashSet<Tocka>();
		for (Tocka t : igra.mozna_polja.values()) {
			if (t.zasedenost == null && t.x!=1 && t.x!=igra.dimenzija_igre && t.y!=1 && t.y!=igra.dimenzija_igre) {
				nakljucna_mnozica.add(t);
				
			}
		}
		Tocka nakljucna = Igra.randomTockaFromSet(nakljucna_mnozica);
		return new Poteza(nakljucna.x-1,nakljucna.y-1);
	}
	
}