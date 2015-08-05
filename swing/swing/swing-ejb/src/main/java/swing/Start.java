package swing;

import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Start extends JFrame {

	public Start() {
		setSize(600, 300);
		setLocation(400, 100);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

	}

	public static void main(String[] args) throws SQLException {
		new ListaKlientow("ListaKlientow");
		new DodajKlienta("DodajKlienta");

	}

	public void polaczenieDodajJDBC(String sql) throws SQLException {

		final String JDBC_DRIVER = "org.postgresql.Driver";
		final String DB_URL = "jdbc:postgresql://localhost:5433/cwiczenie2";
		final String USER = "postgres";
		final String PASS = "adminLWW";

		////////////////////////// Sterownik /////////////////////////

		try {
			Class.forName("org.postgresql.Driver").newInstance();
		} catch (Exception e) {
			System.out.println("Blad przy ladowaniu sterownika bazy!");
			System.exit(1);
		}

		////////////////////////// Otwieranie ////////////////////////

		java.sql.Connection polaczenie = null;
		try {
			polaczenie = DriverManager.getConnection(DB_URL, USER, PASS);

		} catch (SQLException e) {
			System.out.println("Blad przy ladowaniu sterownika bazy!");
			System.exit(1);
		}

		/////////////////////////// Dzialania ////////////////////////

		Statement zapytania = null;

		try {
			polaczenie.setAutoCommit(false);
			zapytania = polaczenie.createStatement();
			zapytania.execute(sql);
			polaczenie.commit();
			System.err.println("INSERT zrobiony");

		} catch (SQLException e) {

			try {
				polaczenie.rollback();
				System.err.println(e.getMessage());
				System.err.println("BLAD! Wykonuje ROLLBACK'a");

			} catch (SQLException e1) {
				System.err.println("Blad podczas robienia ROLLBACK'a");
			}
		}

		////////////////////////// Zamykanie ////////////////////////

		try {
			zapytania.close();
			polaczenie.close();
		} catch (SQLException e) {
			System.out.println("Blad przy zamykaniu polaczenia " + e.toString());
			System.exit(4);
		}

		/////////////////////////////////////////////////////////////
	}

	public DefaultTableModel polaczenieOdbierzJDBC(String sql) throws SQLException {

		final String JDBC_DRIVER = "org.postgresql.Driver";
		final String DB_URL = "jdbc:postgresql://localhost:5433/cwiczenie2";
		final String USER = "postgres";
		final String PASS = "adminLWW";

		////////////////////////// Sterownik /////////////////////////

		try {
			Class.forName("org.postgresql.Driver").newInstance();
		} catch (Exception e) {
			System.out.println("Blad przy ladowaniu sterownika bazy!");
			System.exit(1);
		}

		////////////////////////// Otwieranie ////////////////////////

		java.sql.Connection polaczenie = null;
		try {
			polaczenie = DriverManager.getConnection(DB_URL, USER, PASS);

		} catch (SQLException e) {
			System.out.println("Blad przy ladowaniu sterownika bazy!");
			System.exit(1);
		}

		/////////////////////////// Dzialania ////////////////////////

		Statement zapytania = null;

		ResultSet rezultatZapytania = null;
		try {
			polaczenie.setAutoCommit(false);
			zapytania = polaczenie.createStatement();
			rezultatZapytania = zapytania.executeQuery(sql);
			polaczenie.commit();

			System.err.println("SELECT zrobiony");

		} catch (SQLException e) {

			try {
				polaczenie.rollback();
				System.err.println(e.getMessage());
				System.err.println("BLAD! Wykonuje ROLLBACK'a");

			} catch (SQLException e1) {
				System.err.println("Blad podczas robienia ROLLBACK'a");
			}
		}
		ResultSetMetaData metaData = rezultatZapytania.getMetaData();
		int numberOfColumns = metaData.getColumnCount();
		Vector columnNames = new Vector();

		for (int column = 0; column < numberOfColumns; column++) {
			columnNames.addElement(metaData.getColumnLabel(column + 1));
		}

		Vector rows = new Vector();

		while (rezultatZapytania.next()) {
			Vector newRow = new Vector();

			for (int i = 1; i <= numberOfColumns; i++) {
				newRow.addElement(rezultatZapytania.getObject(i));
			}

			rows.addElement(newRow);
		}

		////////////////////////// Zamykanie ////////////////////////

		try

		{
			zapytania.close();
			polaczenie.close();
		} catch (

		SQLException e)

		{
			System.out.println("Blad przy zamykaniu polaczenia " + e.toString());
			System.exit(4);
		}

		/////////////////////////////////////////////////////////////
		return new DefaultTableModel(rows, columnNames);

	}

}