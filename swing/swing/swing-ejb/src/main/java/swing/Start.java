package swing;

import javax.swing.JFrame;

public class Start extends JFrame {

	public static void main(String[] args) {
		new ListaKlientow("ListaKlientow");
	}

	public Start() {
		setSize(600, 300);
		setLocation(400, 100);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
	}
}