package swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Label;

import javax.swing.JButton;
import javax.swing.JPanel;

public class DodanieFaktury extends Start {

	public DodanieFaktury(String title) {
		setTitle(title);

		setLayout(new BorderLayout());
		/////////////////////////// GORA///////////////////////////

		JPanel panelGora = new JPanel();

		panelGora.setLayout(new BorderLayout());

		JButton prawyGorny = new JButton();
		prawyGorny.setText("prawyGorny");
		prawyGorny.setPreferredSize(new Dimension(400, 40));

		Label lewyGorny = new Label();
		lewyGorny.setText("lewyGorny");

		panelGora.add(lewyGorny, BorderLayout.WEST);
		panelGora.add(prawyGorny, BorderLayout.EAST);

		/////////////////////////// DOL////////////////////////////

		JPanel panelDol = new JPanel();

		panelDol.setLayout(new BorderLayout());

		JButton prawyDolny = new JButton();
		prawyDolny.setText("prawyDolny");
		prawyDolny.setPreferredSize(new Dimension(200, 40));

		JButton dolSrodek = new JButton();
		dolSrodek.setText("dolSrodek");
		dolSrodek.setPreferredSize(new Dimension(100, 40));

		panelDol.add(dolSrodek, BorderLayout.WEST);
		panelDol.add(prawyDolny, BorderLayout.EAST);

		//////////////////////////////////////////////////////////

		add(panelGora, BorderLayout.NORTH);

		add(tabela2na3("Nazwa", "NIP", "ADRES"), BorderLayout.CENTER);

		add(panelDol, BorderLayout.SOUTH);

	}

}
