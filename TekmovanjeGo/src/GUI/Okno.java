package GUI;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import inteligenca.Inteligenca;
import logika.Igra;
import logika.Poteza;
import logika.Vodja;

@SuppressWarnings("serial")

public class Okno extends JFrame implements ActionListener{
	public Platno platno;
	private JMenuItem menu_nova_igra;

	public Okno() {
		super();
		setTitle("Go");
		platno = new Platno(800,800);
		add(platno);
		platno.setBackground(platno.barva_odzadja);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JMenuBar menubar = new JMenuBar();
		setJMenuBar(menubar);
		
		JMenu menu_igra = dodajMenu(menubar, "Igra");
		menu_nova_igra = dodajMenuItem(menu_igra, "Nova igra");
	}
	
	private JMenu dodajMenu(JMenuBar menubar, String naslov) {
		JMenu menu = new JMenu(naslov);
		menubar.add(menu);
		return menu;
	}
	
	private JMenuItem dodajMenuItem(JMenu menu, String naslov) {
		JMenuItem menuitem= new JMenuItem(naslov);
		menu.add(menuitem);
		menuitem.addActionListener(this);
		return menuitem;
	}


	@SuppressWarnings("static-access")
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object objekt = e.getSource();
		if (objekt == menu_nova_igra) {
			JTextField nn = new JTextField();
			JTextField mm = new JTextField();
			JComponent[] polja = {
					new JLabel("Vnesi velikost igralne plošče:"),nn, new JLabel("Vnesi način igranja(ČČ - človek proti človeku, ČR/RČ - človek proti računalniku, RR - računalnik proti računalniku)"),mm
			};
			int izbira = JOptionPane.showConfirmDialog(this, polja, "Vnos", JOptionPane.OK_CANCEL_OPTION);
			if (izbira == JOptionPane.OK_OPTION && nn.getText().matches("\\d+")  && mm.getText().matches("ČČ|RR|RČ|ČR")) {
				if (Integer.parseInt(nn.getText())>=5) {
					Igra igra = new Igra();
					igra.nastavi_dimenzijo(Integer.parseInt(nn.getText())); 
					if (mm.getText().matches("RČ")) {
						igra.racunalnik = "Crni";
						igra.igra_clovek = false;
					}
					if (mm.getText().matches("ČR")) igra.racunalnik = "Beli";
					platno.nastaviIgro(igra);
					if (mm.getText().matches("RČ")) {
						Inteligenca inteligenca = new Inteligenca("Matic");
						Poteza poteza = inteligenca.izberiPotezo(igra);
						Vodja vodja = new Vodja();
						vodja.dodaj_figuro(poteza.x, poteza.y, igra);
					}
					if (mm.getText().matches("RR")) {
						igra.racunalnik2 = true;
						igra.igra_clovek = false;
						Inteligenca inteligenca = new Inteligenca("Matic");
						Poteza poteza = inteligenca.izberiPotezo(igra);
						Vodja vodja = new Vodja();
						vodja.dodaj_figuro(poteza.x, poteza.y, igra);
					}
				}
				else {JOptionPane.showMessageDialog(null, "Premajhna igralna plošča!", "ERROR", JOptionPane.WARNING_MESSAGE);}
			}
			else if (izbira != JOptionPane.CANCEL_OPTION && izbira != JOptionPane.CLOSED_OPTION) {
				JOptionPane.showMessageDialog(null, "Vnos je napačen!", "ERROR", JOptionPane.WARNING_MESSAGE);
			}
			
		}
//		else if (objekt == menuShrani) {
//			JFileChooser dialog = new JFileChooser();
//			int izbira = dialog.showSaveDialog(this); // this se nanaša na to okno kjer stvar živi oz. razred in dokler je chooser odprt nemoremo nič delat
//			if (izbira == JFileChooser.APPROVE_OPTION) {
//				String ime = dialog.getSelectedFile().getPath();
//				platno.graf.shrani(ime);
//			}
//		}
		repaint();
	}

}
