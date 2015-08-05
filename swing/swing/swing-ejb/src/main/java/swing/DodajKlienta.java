package swing;

import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JPanel;

public class DodajKlienta extends Start {

	class Lewo extends JPanel {

		Lewo() {
			setLayout(new GridLayout(8, 1));

			Label labelNip = new Label();
			Label labelKod = new Label();
			Label labelMiejscowosc = new Label();
			Label labelNrDomu = new Label();
			Label labelUlica = new Label();
			Label labelImie = new Label();
			Label labelNazwisko = new Label();

			labelNip.setText("Nip");
			labelKod.setText("Kod");
			labelMiejscowosc.setText("Miejscowosc");
			labelNrDomu.setText("NrDomu");
			labelUlica.setText("Ulica");
			labelImie.setText("Imie");
			labelNazwisko.setText("Nazwisko");

			add(labelNip);
			add(labelImie);
			add(labelNazwisko);
			add(labelKod);
			add(labelMiejscowosc);
			add(labelNrDomu);
			add(labelUlica);
		}
	}

	class Prawo extends JPanel {

		Prawo() {
			setLayout(new GridLayout(8, 1));

			TextField textNip = new TextField();
			TextField textKod = new TextField();
			TextField textMiejscowosc = new TextField();
			TextField textNrDomu = new TextField();
			TextField textUlica = new TextField();
			TextField textImie = new TextField();
			TextField textNazwisko = new TextField();

			JButton dodaj = new JButton();
			dodaj.setText("Dodaj");

			add(textNip);
			add(textImie);
			add(textNazwisko);
			add(textKod);
			add(textMiejscowosc);
			add(textNrDomu);
			add(textUlica);
			add(dodaj);

			dodaj.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					String sql = new String();

					sql = "INSERT INTO swing.Klient(nip,kod_pocztowy,miejscowosc,nr_domu,ulica,imie,nazwisko)"
							+ "VALUES('" + textNip.getText() + "','" + textKod.getText() + "','"
							+ textMiejscowosc.getText() + "','" + textNrDomu.getText() + "','" + textUlica.getText()
							+ "','" + textImie.getText() + "','" + textNazwisko.getText() + "');";
					try {
						polaczenieDodajJDBC(sql);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			});
		}
	}

	public DodajKlienta(String title) {
		setTitle(title);

		setLayout(new GridLayout(1, 2));
		add(new Lewo());
		add(new Prawo());

	}
}