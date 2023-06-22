package GUI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.JPanel;

import logika.Igra;
import logika.Tocka;
import logika.Vodja;
import javax.swing.Timer;

@SuppressWarnings("serial")

public class Platno extends JPanel implements MouseListener, MouseMotionListener, KeyListener {

	protected Igra igra;
	protected int polmer;
	public String poteza_racunalnika;

	
	public Color barva_belih_kamenckov;
	public Color barva_crnih_kamenckov;
	public Color barva_crt;
	public Color barva_odzadja;
	public Color barva_gumbov;
	public Color barva_napisov;
	public int tezavnost_racunalnika;

	public Platno(int sirina, int visina) {
		super();
		this.barva_belih_kamenckov = Color.WHITE;
		this.barva_crnih_kamenckov = Color.black;
		this.barva_crt = Color.black;
		this.barva_odzadja = Color.orange;
		this.barva_gumbov = Color.GRAY;
		this.barva_napisov = Color.CYAN;
		this.tezavnost_racunalnika = 20;
		polmer = 0;
		this.igra = null;
		poteza_racunalnika = "";
		setPreferredSize(new Dimension(sirina, visina));
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
		setFocusable(true);

	}

	public void nastaviIgro(Igra igra) {
		this.igra = igra;
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		super.paintComponent(g);
		int dimenzija_igre = 9;
		if (igra != null) {
			dimenzija_igre = igra.dimenzija_igre;
		}
		double velikost_okna = Math.min(getWidth(), getHeight());
		double odmik_od_strani = velikost_okna / 15;
		double sirina_kvadrata = (velikost_okna - 2 * odmik_od_strani) / (dimenzija_igre - 1);
		polmer = (int) (sirina_kvadrata / 1.1);
		int polmer_spodnji = (int) (sirina_kvadrata / 2);
		int debelina = (int) (sirina_kvadrata / 13);

		// IZRIS ČRT IN PIK NA POLJU
		for (int i = 0; i < dimenzija_igre; i++) {
			g.setColor(barva_crt);
			g2.setStroke(new BasicStroke(debelina));
			g.drawLine((int) (odmik_od_strani + i * (velikost_okna - 2 * odmik_od_strani) / (dimenzija_igre - 1)),
					(int) (odmik_od_strani),
					(int) (odmik_od_strani + i * (velikost_okna - 2 * odmik_od_strani) / (dimenzija_igre - 1)),
					(int) (velikost_okna - odmik_od_strani));

			g.drawLine((int) (odmik_od_strani),
					(int) (odmik_od_strani + i * (velikost_okna - 2 * odmik_od_strani) / (dimenzija_igre - 1)),
					(int) (velikost_okna - odmik_od_strani),
					(int) (odmik_od_strani + i * (velikost_okna - 2 * odmik_od_strani) / (dimenzija_igre - 1)));
		}
		if (dimenzija_igre % 4 != 0 && dimenzija_igre % 2 != 0) {
			g.setColor(barva_crt);

			g.fillOval(
					(int) (odmik_od_strani
							+ ((dimenzija_igre / 2) + 0.5) * (velikost_okna - 2 * odmik_od_strani) / (dimenzija_igre)
							- polmer_spodnji / 2),
					(int) (odmik_od_strani
							+ ((dimenzija_igre / 2) + 0.5) * (velikost_okna - 2 * odmik_od_strani) / (dimenzija_igre)
							- polmer_spodnji / 2),
					polmer_spodnji, polmer_spodnji);

			g.fillOval((int) (odmik_od_strani + (((dimenzija_igre / 2)) / 2) * sirina_kvadrata - polmer_spodnji / 2),
					(int) (odmik_od_strani
							+ ((dimenzija_igre / 2) + 0.5) * (velikost_okna - 2 * odmik_od_strani) / (dimenzija_igre)
							- polmer_spodnji / 2),
					polmer_spodnji, polmer_spodnji);
			g.fillOval(
					(int) (velikost_okna - odmik_od_strani - (((dimenzija_igre / 2)) / 2) * sirina_kvadrata
							- polmer_spodnji / 2),
					(int) (odmik_od_strani
							+ ((dimenzija_igre / 2) + 0.5) * (velikost_okna - 2 * odmik_od_strani) / (dimenzija_igre)
							- polmer_spodnji / 2),
					polmer_spodnji, polmer_spodnji);

			g.fillOval((int) (odmik_od_strani + (((dimenzija_igre / 2)) / 2) * sirina_kvadrata - polmer_spodnji / 2),
					(int) (odmik_od_strani + (((dimenzija_igre / 2)) / 2) * sirina_kvadrata - polmer_spodnji / 2),
					polmer_spodnji, polmer_spodnji);
			g.fillOval(
					(int) (velikost_okna - odmik_od_strani - (((dimenzija_igre / 2)) / 2) * sirina_kvadrata
							- polmer_spodnji / 2),
					(int) (velikost_okna - odmik_od_strani - (((dimenzija_igre / 2)) / 2) * sirina_kvadrata
							- polmer_spodnji / 2),
					polmer_spodnji, polmer_spodnji);

			g.fillOval((int) (odmik_od_strani + (((dimenzija_igre / 2)) / 2) * sirina_kvadrata - polmer_spodnji / 2),
					(int) (velikost_okna - odmik_od_strani - (((dimenzija_igre / 2)) / 2) * sirina_kvadrata
							- polmer_spodnji / 2),
					polmer_spodnji, polmer_spodnji);
			g.fillOval(
					(int) (velikost_okna - odmik_od_strani - (((dimenzija_igre / 2)) / 2) * sirina_kvadrata
							- polmer_spodnji / 2),
					(int) (odmik_od_strani + (((dimenzija_igre / 2)) / 2) * sirina_kvadrata - polmer_spodnji / 2),
					polmer_spodnji, polmer_spodnji);

			g.fillOval(
					(int) (odmik_od_strani
							+ ((dimenzija_igre / 2) + 0.5) * (velikost_okna - 2 * odmik_od_strani) / (dimenzija_igre)
							- polmer_spodnji / 2),
					(int) (odmik_od_strani + (((dimenzija_igre / 2)) / 2) * sirina_kvadrata - polmer_spodnji / 2),
					polmer_spodnji, polmer_spodnji);
			g.fillOval(
					(int) (odmik_od_strani
							+ ((dimenzija_igre / 2) + 0.5) * (velikost_okna - 2 * odmik_od_strani) / (dimenzija_igre)
							- polmer_spodnji / 2),
					(int) (velikost_okna - odmik_od_strani - (((dimenzija_igre / 2)) / 2) * sirina_kvadrata
							- polmer_spodnji / 2),
					polmer_spodnji, polmer_spodnji);
		}
		if (igra != null) {
			Map<String, Tocka> mozna_polja = izracunaj_koordinate_tock(igra.mozna_polja);

			// IZRIS KAMENČKOV NA POLJU
			for (Tocka t : mozna_polja.values()) {

				if (t.zasedenost == "Beli") {
					g.setColor(barva_belih_kamenckov);
					g.fillOval((int) (t.xx - polmer / 2), (int) (t.yy - polmer / 2), polmer, polmer);
					g.setColor(barva_crt);
					g.drawOval((int) (t.xx - polmer / 2), (int) (t.yy - polmer / 2), polmer, polmer);
				} else if (t.zasedenost == "Crni") {
					g.setColor(barva_crnih_kamenckov);
					g.fillOval((int) (t.xx - polmer / 2), (int) (t.yy - polmer / 2), polmer, polmer);
					g.setColor(barva_crt);
					g.drawOval((int) (t.xx - polmer / 2), (int) (t.yy - polmer / 2), polmer, polmer);
				}

			}
		}

		// IZRIS NAPISOV NA POLJU
		g.setColor(barva_napisov);
		if ((igra.preveri_igro().equals("ZMAGA BELI") && igra.pravila_igre.equals("CAPTURE"))
				|| igra.zmaga.equals("Beli")) {
			g.setFont(new Font("TimesRoman", Font.PLAIN, round(velikost_okna / 15)));
			g2.drawString("Zmaga beli!", round(velikost_okna / 3), round(odmik_od_strani - 0.2 * odmik_od_strani));
			if (igra.pravila_igre.equals("GO")) {
				g.setFont(new Font("TimesRoman", Font.PLAIN, round(velikost_okna / 25)));
				g2.drawString("Točke črni: " + igra.score_crni + "            " + "Točke beli: " + igra.score_beli,
						round(velikost_okna / 5), round(odmik_od_strani * 15));
			}
		} else if ((igra.preveri_igro().equals("ZMAGA CRNI") && igra.pravila_igre.equals("CAPTURE"))
				|| igra.zmaga.equals("Crni")) {
			g.setFont(new Font("TimesRoman", Font.PLAIN, round(velikost_okna / 15)));
			g2.drawString("Zmaga črni!", round(velikost_okna / 3), round(odmik_od_strani - 0.2 * odmik_od_strani));
			if (igra.pravila_igre.equals("GO")) {
				g.setFont(new Font("TimesRoman", Font.PLAIN, round(velikost_okna / 25)));
				g2.drawString("Točke črni: " + igra.score_crni + "            " + "Točke beli: " + igra.score_beli,
						round(velikost_okna / 5), round(odmik_od_strani * 14.9));
			}
		} else if (igra.zmaga.equals("IZENAČENO")) {
			g.setFont(new Font("TimesRoman", Font.PLAIN, round(velikost_okna / 15)));
			g2.drawString("Izenačeno", round(velikost_okna / 3), round(odmik_od_strani - 0.2 * odmik_od_strani));
			if (igra.pravila_igre.equals("GO")) {
				g.setFont(new Font("TimesRoman", Font.PLAIN, round(velikost_okna / 25)));
				g2.drawString("Točke črni: " + igra.score_crni + "            " + "Točke beli: " + igra.score_beli,
						round(velikost_okna / 5), round(odmik_od_strani * 14.9));
			}
		}
		// IZRIS GUMBOV NA POLJU
		if (igra.pravila_igre.equals("GO")) {
			g.setFont(new Font("TimesRoman", Font.PLAIN, (int) (odmik_od_strani * 5 / 11)));
			g2.setStroke(new BasicStroke(debelina * 1 / 2));
			g2.setColor(barva_gumbov);
			g2.drawRect((int) (odmik_od_strani / 10), (int) (odmik_od_strani / 10), (int) (2.4 * odmik_od_strani),
					(int) (odmik_od_strani * 5 / 10));
			g2.drawString("PRESKOČI", (int) (odmik_od_strani / 9 + odmik_od_strani * 5 / 100),
					(int) (odmik_od_strani / 10 + odmik_od_strani * 9 / 20 - odmik_od_strani * 2 / 100));
			g2.drawRect((int) (velikost_okna - 2 * odmik_od_strani - odmik_od_strani * 2.8 / 10),
					(int) (odmik_od_strani / 10), (int) (2.2 * odmik_od_strani), (int) (odmik_od_strani * 5 / 10));
			g2.drawString("PREDAJA", (int) (velikost_okna - 1.95 * odmik_od_strani - odmik_od_strani * 2.8 / 10),
					(int) (odmik_od_strani / 10 + odmik_od_strani * 9 / 20 - odmik_od_strani * 2 / 100));
		}
		if (igra.pravila_igre.equals("GO") && !igra.zmaga.equals("Crni") && !igra.zmaga.equals("Beli")
				&& !igra.preveri_igro().equals("ZMAGA CRNI") && !igra.preveri_igro().equals("ZMAGA BELI")
				&& !igra.zmaga.equals("IZENAČENO")) {
			g.setFont(new Font("TimesRoman", Font.PLAIN, (int) (odmik_od_strani * 5 / 11)));

			g2.setColor(barva_gumbov);
			g2.drawRect((int) (velikost_okna / 2.5), (int) (velikost_okna * 95 / 100), (int) (2.8 * odmik_od_strani),
					(int) (odmik_od_strani * 5 / 10));
			g2.drawString("OVREDNOTI", (int) (velikost_okna / 2.47),
					(int) (velikost_okna * 94.5 / 100 + odmik_od_strani * 5 / 10));
		}
		if (igra.igramo && !igra.vrsta_igre.equals("ČČ")) {
			g2.setColor(barva_gumbov);
			g.setFont(new Font("TimesRoman", Font.PLAIN, round(velikost_okna / 30)));
			g2.drawString(igra.poteza_racunalnika, round(velikost_okna / 3),
					round(odmik_od_strani - 0.2 * odmik_od_strani));
		}
		if (igra.igramo && igra.pravila_igre.equals("GO")) {
			
			g.setFont(new Font("TimesRoman", Font.PLAIN, round(velikost_okna / 25)));
			g2.setColor(barva_crnih_kamenckov);
			g2.drawString("Točke črni: " + igra.score_crni ,
					round(velikost_okna / 9), round(odmik_od_strani * 14.9));
			g2.setColor(barva_belih_kamenckov);
			g2.drawString("                                                 " + "Točke beli: " + igra.score_beli,
					round(velikost_okna / 9), round(odmik_od_strani * 14.9));
		}
		revalidate();
		repaint();
	}

	// FUNKCIJA KI IZRAČUNA KOORDINATE ZA KAMENČEK NA POLJU V PRIMERU KLIKA
	public Map<String, Tocka> izracunaj_koordinate_tock(Map<String, Tocka> mozna_polja) {
		int dimenzija_igre = igra.dimenzija_igre;
		double velikost_okna = Math.min(getWidth(), getHeight());
		double odmik_od_strani = velikost_okna / 15;
		double sirina_kvadrata = (velikost_okna - 2 * odmik_od_strani) / (dimenzija_igre - 1);
		for (Tocka t : mozna_polja.values()) {
			t.xx = (int) (odmik_od_strani + sirina_kvadrata * (t.x - 1));
			t.yy = (int) (odmik_od_strani + sirina_kvadrata * (t.y - 1));
		}
		return mozna_polja;
	}

	private int round(double x) {
		return (int) (x + 0.5);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	// FUKCIJA GLEDE NA KLIK NA POLJE DODA KAMENČEK NA POLJE
	// V DRUGEM DELU FUNKCIJA POSKRBI, DA SE NE ZGODI ISTI TRENUTEK KLIK NA GUMB IN
	// KLIK NA POLJE
	@Override
	public void mouseClicked(MouseEvent e) {
		if (!igra.igra_clovek)
			return;
		if (igra != null && igra.igramo) {
			int dimenzija_igre = igra.dimenzija_igre;
			double velikost_okna = Math.min(getWidth(), getHeight());
			double odmik_od_strani = velikost_okna / 15;
			double sirina_kvadrata = (velikost_okna - 2 * odmik_od_strani) / (dimenzija_igre - 1);
			int x = e.getX();
			int y = e.getY();
			int koordinata_x = 0;
			int koordinata_y = 0;
			for (int i = 0; i < dimenzija_igre; i++) {
				if ((i) * sirina_kvadrata + (int) (odmik_od_strani - sirina_kvadrata / 2) <= x
						&& x <= (i + 1) * sirina_kvadrata + (int) (odmik_od_strani - sirina_kvadrata / 2)) {
					koordinata_x = i + 1;
				}
				if ((i) * sirina_kvadrata + (int) (odmik_od_strani - sirina_kvadrata / 2) <= y
						&& y <= (i + 1) * sirina_kvadrata + (int) (odmik_od_strani - sirina_kvadrata / 2)) {
					koordinata_y = i + 1;
				}
			}
			repaint();
			if (koordinata_x >= 1 && koordinata_x <= dimenzija_igre && koordinata_y >= 1
					&& koordinata_y <= dimenzija_igre && y >= odmik_od_strani / 10 + odmik_od_strani * 5 / 10
					&& y <= velikost_okna * 95 / 100) {
				if (igra.mozna_polja.get("(" + koordinata_x + ", " + koordinata_y + ")").zasedenost == null) {
					igra.mozna_polja.get("(" + koordinata_x + ", " + koordinata_y + ")").zasedenost = "Beli";
					repaint();
					igra.mozna_polja.get("(" + koordinata_x + ", " + koordinata_y + ")").zasedenost = null;
					Vodja vodja = new Vodja();
					vodja.dodaj_figuro(koordinata_x, koordinata_y, igra);
				}
			}
			if (igra.pravila_igre.equals("GO") && x >= odmik_od_strani / 10
					&& x <= odmik_od_strani / 10 + 2.4 * odmik_od_strani && y >= odmik_od_strani / 10
					&& y <= odmik_od_strani / 10 + odmik_od_strani * 5 / 10) {

				igra.stevilo_preskokov = igra.stevilo_preskokov + 1;
				if (igra.stevilo_preskokov >= 2)
					igra.preveri_igro_go();
				if (igra.igralec_na_vrsti.equals("Crni"))
					igra.igralec_na_vrsti = "Beli";
				else
					igra.igralec_na_vrsti = "Crni";
				if ((igra.vrsta_igre.equals("RČ") || igra.vrsta_igre.equals("ČR")) && igra.igra_clovek == true) {

					Vodja vodja = new Vodja();
					vodja.dodaj_figuro(1, 1, igra);
				}

			}
			if (igra.pravila_igre.equals("GO") && x >= velikost_okna - 2 * odmik_od_strani - odmik_od_strani * 2.8 / 10
					&& x <= velikost_okna - 2 * odmik_od_strani - odmik_od_strani * 2.8 / 10 + 2.2 * odmik_od_strani
					&& y >= odmik_od_strani / 10 && y <= odmik_od_strani / 10 + odmik_od_strani * 5 / 10) {
				igra.igramo = false;
				if (igra.igralec_na_vrsti.equals("Crni"))
					igra.zmaga = "Beli";
				else
					igra.zmaga = "Crni";
			}
			if (igra.pravila_igre.equals("GO") && x >= velikost_okna / 2.5
					&& x <= velikost_okna / 2.5 + 2.8 * odmik_od_strani && y >= velikost_okna * 95 / 100
					&& y <= velikost_okna * 95 / 100 + odmik_od_strani * 5 / 10) {
				igra.igramo = false;
				igra.preveri_igro_go();
			}
			repaint();
		}
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
