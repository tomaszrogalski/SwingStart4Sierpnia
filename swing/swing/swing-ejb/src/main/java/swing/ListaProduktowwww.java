package swing;
//package swing;
//
//import java.awt.BorderLayout;
//import java.awt.GridLayout;
//import java.awt.TextField;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.sql.SQLException;
//
//import javax.swing.JButton;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.JTable;
//import javax.swing.table.DefaultTableModel;
//
//import swing.ListaKlientow.Dol;
//import swing.ListaKlientow.Tabela;
//
//public class ListaProduktow extends Start {
//
//	class Tabela extends JPanel {
//
//		Tabela() throws SQLException {
//
//			DefaultTableModel model = new Polaczenia().StworzSelectProdukty();
//
//			JTable table = new JTable(model);
//			this.add(new JScrollPane(table));
//		}
//	}
//
//	class Dol extends JPanel {
//
//		Dol() {
//			setLayout(new GridLayout(1, 3));
//
//			TextField textId = new TextField();
//			textId.setText("ID");
//
//			JButton wybierz = new JButton();
//			wybierz.setText("Wybierz produkt");
//			wybierz.addActionListener(new ActionListener() {
//
//				@Override
//				public void actionPerformed(ActionEvent e) {
//					DodajPozycje.wybierzProdukt(textId.getText());
//					System.out.println("Dodano produkt do pozycji o id: "+textId.getText());
//					DodajPozycje.dodajDoTextAreaPozycja(textId.getText());
//					//DodajPozycje.dodajDoTextAreaPozycjaname!
//
//				}
//			});
//
//			JButton cofnij = new JButton();
//			cofnij.setText("Cofnij");
//			cofnij.addActionListener(new ActionListener() {
//
//				@Override
//				public void actionPerformed(ActionEvent e) {
//
//					dispose();
//				}
//			});
//
//			add(textId);
//			add(wybierz);
//			add(cofnij);
//
//		}
//	}
//
//	public ListaProduktow(String title) throws SQLException {
//		setTitle(title);
//		this.add(new Tabela(), BorderLayout.CENTER);
//
//		this.pack();
//
//		this.add(new Dol(), BorderLayout.SOUTH);
//
//	}
//
//}