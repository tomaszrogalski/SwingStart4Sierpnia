package swing;

import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;

public class DodajKlienta extends Start {

	public DodajKlienta(String title) {
		setTitle(title);
		setLayout(new GridLayout(1,1));
		add(tabela2na3("Nazwa", "NIP", "ADRES"));
		
	}

}
