package swing;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JFrame;

public class Start extends JFrame {

	public static java.sql.Connection lacznosc = null;

	public Start() {

		Polaczenia klasaPolaczenia = new Polaczenia();

		klasaPolaczenia.ZaladujSterownik();

		lacznosc = klasaPolaczenia.OtworzPolaczenie();

		setSize(600, 300);
		setLocation(400, 100);
		setVisible(true);
		setResizable(false);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				klasaPolaczenia.ZamknijPolaczenie();
				System.exit(0);
			}
		});

	}

	public static void main(String[] args) throws SQLException {

		// komponentach Swing powinny byæ realizowane przez wyró¿niony w¹tek
		// obs³ugi zdarzeñ
		// javax.swing.SwingUtilities.invokeLater(new Runnable() {
		// public void run() {
		// }
		// });

		new ListaFaktur("ListaFaktur");

		// new ListaKlientow("ListaKlientow");
		//
		// new ListaProduktow("ListaProduktow");
		//
		// new DodajFakture("DodajFakture");

	}
}