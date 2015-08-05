package swing;

import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ListaKlientow extends Start {
	//listafaktur powinna dziedziczyc po liscie?
	//zrobic klase a listaFaktur i listaKlientow i dodac dziedzicza po niej?

	public ListaKlientow(String title) {
		setTitle(title);
		
		String[] kolumny = new String[] { "NAZWA", "NIP", "ADRES" };

		String[][] wartosci = new String[][] { { "jakasNAZWA", "jakisNIP", "jakisADRES" }, };

		JTable table = new JTable(wartosci, kolumny);

		this.add(new JScrollPane(table));

		this.pack();
		//klawisz cofnij
	}
}
