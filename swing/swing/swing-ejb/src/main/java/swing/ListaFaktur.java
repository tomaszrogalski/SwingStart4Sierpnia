package swing;

import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ListaFaktur extends Start {

	public ListaFaktur(String title) {
		setTitle(title);
		String[] kolumny = new String[] { "N", "KL", "AD" };

		String[][] wartosci = new String[][] { { "jakisN", "jakisKL", "jakisAD" }, };

		JTable table = new JTable(wartosci, kolumny);

		this.add(new JScrollPane(table));

		this.pack();

		//klawisz cofnij
	}
}
