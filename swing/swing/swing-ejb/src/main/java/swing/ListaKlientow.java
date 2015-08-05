package swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ListaKlientow extends Start {

	class Tabela extends JPanel {
		
		Tabela() throws SQLException {
			String sql = new String();

			sql = "Select * from swing.klient";

			DefaultTableModel model = polaczenieOdbierzJDBC(sql);

			JTable table = new JTable(model);
			this.add(new JScrollPane(table));
		}
	}

	class Dol extends JPanel {
		
		Dol() {
			setLayout(new FlowLayout());

			TextField textId = new TextField();
			textId.setText("ID");
			textId.setPreferredSize(new Dimension(220, 30));

			JButton wybierz = new JButton();
			wybierz.setText("Wybierz klienta");
			wybierz.setPreferredSize(new Dimension(220, 30));
			wybierz.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

				}
			});

			add(textId);
			add(wybierz);

		}
	}

	public ListaKlientow(String title) throws SQLException {
		setTitle(title);

		setLayout(new BorderLayout());

		this.add(new Tabela(), BorderLayout.CENTER);

		this.pack();

		this.add(new Dol(), BorderLayout.SOUTH);

	}

}
