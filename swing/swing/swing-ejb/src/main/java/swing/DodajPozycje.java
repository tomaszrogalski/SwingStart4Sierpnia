package swing;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

public class DodajPozycje extends Start {

	static ArrayList<Produkt> listaProduktow = new ArrayList<Produkt>();
	static JTextArea Arena = null;

	class Gora extends JPanel {
		public Gora() throws SQLException {
			setLayout(new GridLayout(1, 2));

			this.add(new GoraArena());
			this.add(new GoraListaProduktow());
		}
	}

	class GoraArena extends JPanel {
		public GoraArena() {
			Arena = new JTextArea();

			add(Arena);
		}

	}

	class GoraListaProduktow extends JPanel {

		class Tabela extends JPanel {

			Tabela() throws SQLException {

				DefaultTableModel model = new Polaczenia().StworzSelectProdukty();

				JTable table = new JTable(model);
				this.add(new JScrollPane(table));
			}
		}

		class Dol extends JPanel {

			Dol() {
				setLayout(new GridLayout(1, 2));

				TextField textId = new TextField();
				textId.setText("ID");

				JButton wybierz = new JButton();
				wybierz.setText("Wybierz produkt");
				wybierz.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							listaProduktow.add(new Polaczenia().StworzSelectProdukt(textId.getText()));
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						System.out.println("Dodano produkt do pozycji o id: " + textId.getText());
						DodajPozycje.dodajDoTextAreaPozycja(textId.getText());
						// DodajPozycje.dodajDoTextAreaPozycjaname!

					}
				});

				add(textId);
				add(wybierz);

			}
		}

		public GoraListaProduktow() throws SQLException {

			this.add(new Tabela(), BorderLayout.CENTER);

			setSize(300, 300);

			this.add(new Dol(), BorderLayout.SOUTH);

		}

	}

	class Dol extends JPanel {

		Dol() {
			setLayout(new GridLayout(1, 1));

			JButton dodaj = new JButton();
			dodaj.setText("Dodaj");
			dodaj.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					new DodajFakture("DodajFakture");
					DodajFakture.wybierzPozycje(listaProduktow);
					dispose();
				}
			});

			add(dodaj);

		}
	}

	public DodajPozycje(String title) throws SQLException {
		setTitle(title);
		this.add(new Gora(), BorderLayout.CENTER);
		setSize(1000, 600);
		this.add(new Dol(), BorderLayout.SOUTH);

	}

	public static void dodajDoTextAreaPozycja(String text) {
		Arena.append(text + "\n");
	}

}