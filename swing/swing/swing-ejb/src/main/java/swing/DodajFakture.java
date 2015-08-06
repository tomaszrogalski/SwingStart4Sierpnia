package swing;

import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

public class DodajFakture extends Start {

	static String klient = new String();
	static List<ArrayList<Produkt>> listaPozycji = new ArrayList<ArrayList<Produkt>>();

	class LewaStronaOkienka extends JPanel {

		LewaStronaOkienka() {
			setLayout(new GridLayout(4, 1));

			Label labelNumer = new Label();
			Label labelKlient = new Label();
			Label labelPozycja = new Label();

			labelNumer.setText("Numer");
			labelKlient.setText("Klient");
			labelPozycja.setText("Poycja");

			add(labelNumer);
			add(labelKlient);
			add(labelPozycja);

		}
	}

	class PrawaStronaOkienka extends JPanel {

		PrawaStronaOkienka() {
			setLayout(new GridLayout(4, 1));

			TextField textNumer = new TextField();
			JButton wybierzKlienta = new JButton();
			wybierzKlienta.setText("Wybierz Klienta");
			JButton wybierzPozycje = new JButton();
			wybierzPozycje.setText("Wybierz Pozycje");

			JButton dodajFakture = new JButton();
			dodajFakture.setText("Dodaj");

			add(textNumer);
			add(wybierzKlienta);
			add(wybierzPozycje);
			add(dodajFakture);

			wybierzKlienta.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						new ListaKlientow("ListaKlientow");
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					dispose();
					//nie zamknac tylko ukryc
				}
			});

			wybierzPozycje.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						new DodajPozycje("DodajPozycje");
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					dispose();
					//nie zamknac tylko ukryc
				}
			});
			dodajFakture.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					
					try {
						new Polaczenia().StorzInsertFaktura(textNumer,klient,listaPozycji);
					} catch (SQLException e2) {

						e2.printStackTrace();
					}
					
					
					
					try {
						new ListaFaktur("ListaFaktur");
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					dispose();
					//nie zamknac tylko ukryc
				}
			});
		}
	}

	public DodajFakture(String title) {
		setTitle(title);

		setLayout(new GridLayout(1, 2));
		add(new LewaStronaOkienka());
		add(new PrawaStronaOkienka());
	}

	public static void wybierzKlienta(String klientID) {
		klient = klientID;
	}

	public static void wybierzPozycje(ArrayList<Produkt> listaProduktow) {

		listaPozycji.add(listaProduktow);
	}

	public void StorzStringFaktura() {
		String id = new String();
	}
}
