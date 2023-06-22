package GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import inteligenca.Inteligenca;
import logika.Igra;
import splosno.Poteza;
import logika.Vodja;

@SuppressWarnings("serial")

public class Okno extends JFrame implements ActionListener {
	public Platno platno;
	private JMenuItem menu_nova_igra;
	private JMenuItem navodila_igre;
	private JMenuItem menu_barva_belih_kamenckov;
	private JMenuItem menu_barva_crnih_kamenckov;
	private JMenuItem menu_barva_odzadja;
	private JMenuItem menu_barva_crt;
	private JMenuItem menu_barva_gumbov;
	private JMenuItem menu_barva_napisov;
	private JMenuItem menu_tezavnost;
	public Color barva_belih_kamenckov;
	public Color barva_crnih_kamenckov;
	public Color barva_crt;
	public Color barva_odzadja;
	public Color barva_gumbov;
	public Color barva_napisov;
	public int tezavnost_racunalnika;

	public Okno() {
		super();
		this.barva_belih_kamenckov = Color.WHITE;
		this.barva_crnih_kamenckov = Color.black;
		this.barva_crt = Color.black;
		this.barva_odzadja = Color.orange;
		this.barva_gumbov = Color.GRAY;
		this.barva_napisov = Color.CYAN;
		this.tezavnost_racunalnika = 20;
		setTitle("Go");
		platno = new Platno(800, 800);
		add(platno);
		platno.setBackground(platno.barva_odzadja);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JMenuBar menubar = new JMenuBar();
		setJMenuBar(menubar);

		JMenu menu_igra = dodajMenu(menubar, "Igra");
		menu_nova_igra = dodajMenuItem(menu_igra, "Nova igra");
		navodila_igre = dodajMenuItem(menu_igra, "Navodila igre");

		JMenu menu_nastavitve = dodajMenu(menubar, "Nastavitve");
		menu_barva_belih_kamenckov = dodajMenuItem(menu_nastavitve, "Barva belih kamenčkov");
		menu_barva_crnih_kamenckov = dodajMenuItem(menu_nastavitve, "Barva črnih kamenčkov");
		menu_barva_odzadja = dodajMenuItem(menu_nastavitve, "Barva odzadja");
		menu_barva_crt = dodajMenuItem(menu_nastavitve, "Barva črt na polju");
		menu_barva_gumbov = dodajMenuItem(menu_nastavitve, "Barva gumbov");
		menu_barva_napisov = dodajMenuItem(menu_nastavitve, "Barva končnih napisov");
		menu_tezavnost = dodajMenuItem(menu_nastavitve, "Težavnost računalnika");
		
	}

	private JMenu dodajMenu(JMenuBar menubar, String naslov) {
		JMenu menu = new JMenu(naslov);
		menubar.add(menu);
		return menu;
	}

	private JMenuItem dodajMenuItem(JMenu menu, String naslov) {
		JMenuItem menuitem = new JMenuItem(naslov);
		menu.add(menuitem);
		menuitem.addActionListener(this);
		return menuitem;
	}

	@SuppressWarnings("static-access")
	@Override
	public void actionPerformed(ActionEvent e) {

		Object objekt = e.getSource();

		// MENU ZA USTVARITI NOVO IGRO
		if (objekt == menu_nova_igra) {

			JComboBox<String> proti_komu_igramo = new JComboBox<>(
					new String[] { "ČLOVEK-ČLOVEK", "ČLOVEK-RAČUNALNIK", "RAČUNALNIK-ČLOVEK" });
			JComboBox<String> katera_igra = new JComboBox<>(new String[] { "GO", "CAPTURE GO" });
			JComboBox<String> velikost_plosce = new JComboBox<>(new String[] { "9X9", "13X13", "19X19" });

			JComponent[] polja = {

					new JLabel("Izberi nasprotnika:"), proti_komu_igramo, new JLabel("Izberi vrsto igre:"), katera_igra,
					new JLabel("Izberi velikost plošče:"), velikost_plosce };

			int izbira = JOptionPane.showConfirmDialog(this, polja, "Izberi možnosti igranja", JOptionPane.OK_CANCEL_OPTION);

			if (izbira == JOptionPane.OK_OPTION) {

				Igra igra = new Igra();
				igra.tezavnost_racunalnika = tezavnost_racunalnika;

				String katero_igro = (String) katera_igra.getSelectedItem();
				if (katero_igro.equals("GO"))
					igra.pravila_igre = "GO";
				else if (katero_igro.equals("CAPTURE GO"))
					igra.pravila_igre = "CAPTURE";

				String plosca = (String) velikost_plosce.getSelectedItem();
				if (plosca.equals("9X9"))
					igra.nastavi_dimenzijo(9);
				else if (plosca.equals("19X19"))
					igra.nastavi_dimenzijo(19);
				else if (plosca.equals("13X13"))
					igra.nastavi_dimenzijo(13);

				platno.nastaviIgro(igra);

				String proti_komu = (String) proti_komu_igramo.getSelectedItem();
				if (proti_komu.equals("ČLOVEK-ČLOVEK")) {
					igra.vrsta_igre = "ČČ";
					igra.racunalnik = null;
					igra.igra_clovek = true;
				} else if (proti_komu.equals("ČLOVEK-RAČUNALNIK")) {
					igra.vrsta_igre = "ČR";
					igra.racunalnik = "Beli";
					igra.igra_clovek = true;
				} else if (proti_komu.equals("RAČUNALNIK-ČLOVEK")) {
					igra.vrsta_igre = "RČ";
					igra.racunalnik = "Crni";
					igra.igra_clovek = false;
					Vodja vodja = new Vodja();
					vodja.dodaj_figuro(0, 0, igra);
				}

			}

		}

		// MENUJI ZA BARVO KAMENČKOV, ...
		else if (objekt == menu_barva_crnih_kamenckov) {
			Color barva = JColorChooser.showDialog(this, "Izberi barvo črnih kamenčkov", platno.barva_crnih_kamenckov);
			if (barva != null) {
				platno.barva_crnih_kamenckov = barva;
				repaint();
			}
		} else if (objekt == menu_barva_belih_kamenckov) {
			Color barva = JColorChooser.showDialog(this, "Izberi barvo belih kamenčkov", platno.barva_belih_kamenckov);
			if (barva != null) {
				platno.barva_belih_kamenckov = barva;
				repaint();
			}
		} else if (objekt == menu_barva_odzadja) {
			Color barva = JColorChooser.showDialog(this, "Izberi barvo odzadja", platno.barva_odzadja);
			if (barva != null) {
				platno.barva_odzadja = barva;
				platno.setBackground(barva);
				repaint();
			}
		} else if (objekt == menu_barva_napisov) {
			Color barva = JColorChooser.showDialog(this, "Izberi barvo napisov", platno.barva_napisov);
			if (barva != null) {
				platno.barva_napisov = barva;
				repaint();
			}
		} else if (objekt == menu_barva_gumbov) {
			Color barva = JColorChooser.showDialog(this, "Izberi barvo gumbov", platno.barva_gumbov);
			if (barva != null) {
				platno.barva_gumbov = barva;
				repaint();
			}
		} else if (objekt == menu_barva_crt) {
			Color barva = JColorChooser.showDialog(this, "Izberi barvo črt", platno.barva_crt);
			if (barva != null) {
				platno.barva_crt = barva;
				repaint();
			}
		}

		// MENU ZA TEŽAVNOST
		else if (objekt == menu_tezavnost) {
			JSlider slider = new JSlider(1, 1000);

			slider.setPaintTrack(true);
			slider.setPaintTicks(true);
			slider.setPaintLabels(true);
			slider.setMajorTickSpacing(100);
			slider.setMinorTickSpacing(10);

			JLabel valueLabel = new JLabel("Težavnost računalnika želim nastaviti na: " + slider.getValue());

			JComponent[] polja = { valueLabel, slider, new JLabel(
					"Večje kot je število pametnejši bo računalnik, vendar bo potreboval več časa za odgovor; prav tako če je večje igralno polje (Začetna nastavitev je 20).") };

			slider.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent e) {
					int sliderValue = slider.getValue();
					valueLabel.setText("Težavnost računalnika želim nastaviti na: " + sliderValue);
				}
			});

			int izbira = JOptionPane.showConfirmDialog(this, polja, "Izberi težavnost računalnika", JOptionPane.OK_CANCEL_OPTION);

			if (izbira == JOptionPane.OK_OPTION) {
				platno.tezavnost_racunalnika = slider.getValue();
				tezavnost_racunalnika = slider.getValue();
				platno.igra.tezavnost_racunalnika = tezavnost_racunalnika;
			}
		}
		
		else if (objekt == navodila_igre) {
			
			JComponent[] polja = {  
					
					new JLabel("                                                            NAVODILA GO"),
					new JLabel("S prvo potezo prične igralec s črnimi kamenčki in nato si igralca poteze izmenjujeta."),
					new JLabel("Cilj igre je zavzeti čim večjo površino igralne plošče. Prazno presečišče pripada nekemu"),
					new JLabel("igralcu če vse možne poti po praznih presečiščih dosežejo le kamenčke tega istega igralca."),
					new JLabel("Vsako tako presečišče igralcu prinese eno točko, prav tako pa mu točke prinesejo zavzeti"),
					new JLabel("nasprotnikovi kamenčki, kar je možno doseči tako, da grupi nasprotnikovih kamenčkov"),
					new JLabel("zapremo vsa možna polja okoli njih in jim tako preprečimo kakršno koli nadaljno širitev."),
					new JLabel("V temu primeru so nasprotnikovi kamenčki izbrisani iz igralnega polja. Igra se konča ob"),
					new JLabel("predaji, ali ko oba igralca zaporedoma preskočita potezo ali pa ob kliku na gumb ovrednoti"),
					new JLabel("igro. V slednjih dveh primerih se v izračun končnega stanja igralčevih točk doda točka za"),
					new JLabel("vsak kamenček svoje barve, ki je na igralnem polju."),
					new JLabel("-------------------------------------------------------------------------------------------------------------------------------"),
					new JLabel("                                                    NAVODILA CAPTURE GO"),
					new JLabel("S prvo potezo prične igralec s črnimi kamenčki in nato si igralca poteze izmenjujeta."),
					new JLabel("Cilj igre je zavzeti eno skupino nasprotnikovih kamenčkov, kar je možno doseči tako,"),
					new JLabel("da grupi nasprotnikovih kamenčkov zapremo vsa možna polja okoli njih in jim tako preprečimo"),
					new JLabel("kakršno koli nadaljno širitev. Le tako se igra konča, saj ne dovoljujemo preskakovanje potez.")
			};


			int izbira = JOptionPane.showConfirmDialog(this, polja, "Navodila", JOptionPane.PLAIN_MESSAGE );

		}

		repaint();
	}

}
