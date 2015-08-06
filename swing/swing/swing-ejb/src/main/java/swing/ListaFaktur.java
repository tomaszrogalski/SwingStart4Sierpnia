package swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ListaFaktur extends Start {

	class Tabela extends JPanel {

		Tabela() throws SQLException {

			DefaultTableModel model = new Polaczenia().StworzSelectFaktura();

			JTable table = new JTable(model);
			this.add(new JScrollPane(table));
		}
	}

	class Dol extends JPanel {

		Dol() {
			setLayout(new GridLayout(1,1));

			JButton wybierz = new JButton();
			wybierz.setText("DODAJ FAKTURE");

			wybierz.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					new DodajFakture("DodajFakture");
					dispose();
					
				}
			});

			add(wybierz);

		}
	}

	public ListaFaktur(String title) throws SQLException {
		setTitle(title);

		this.add(new Tabela(), BorderLayout.CENTER);

		this.pack();

		this.add(new Dol(), BorderLayout.SOUTH);

	}

}
