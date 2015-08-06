package swing;

import java.awt.TextField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class Polaczenia {

	final String JDBC_DRIVER = "org.postgresql.Driver";
	final String DB_URL = "jdbc:postgresql://localhost:5433/cwiczenie2";
	final String USER = "postgres";
	final String PASS = "adminLWW";
	java.sql.Connection lacznosc = Start.lacznosc;
	Statement zapytania = null;

	public void StworzInsertKlient(TextField textNip, TextField textKod, TextField textMiejscowosc,
			TextField textNrDomu, TextField textUlica, TextField textImie, TextField textNazwisko) throws SQLException {

		String sql = new String();

		sql = "INSERT INTO swing.Klient(nip,kod_pocztowy,miejscowosc,nr_domu,ulica,imie,nazwisko)" + "VALUES('"
				+ textNip.getText() + "','" + textKod.getText() + "','" + textMiejscowosc.getText() + "','"
				+ textNrDomu.getText() + "','" + textUlica.getText() + "','" + textImie.getText() + "','"
				+ textNazwisko.getText() + "');";

		ObslugaInserta(sql);

	}

	public DefaultTableModel StworzSelectProdukty() throws SQLException {

		String sql = new String();

		sql = "select swing.produkt.id as \"ID\",swing.produkt.nazwa as \"NAZWA\", swing.produkt.vat as \"VAT\" from swing.produkt where swing.produkt.pozycja_id is NULL";
		return ObslugaSelectaModel(sql);

	}

	public DefaultTableModel StworzSelectFaktura() throws SQLException {

		String sql = new String();

		sql = "select swing.faktura.id as \"FAKTURA ID\", swing.pozycja.id as \"POZYCJA ID\", swing.produkt.id as \"PRODUKT ID\",swing.produkt.nazwa as \"NAZWA\"			from swing.faktura left join (swing.pozycja left join swing.produkt on swing.pozycja.id=swing.produkt.pozycja_id) on swing.faktura.id=swing.pozycja.faktura_id;";

		return ObslugaSelectaModel(sql);

	}

	public Produkt StworzSelectProdukt(String id) throws SQLException {

		String sql = new String();

		sql = "Select swing.produkt.nazwa, swing.produkt.vat from swing.produkt where id='" + id + "'";

		ResultSet rezultatZapytania = null;
		try {
			lacznosc.setAutoCommit(false);
			this.zapytania = lacznosc.createStatement();
			rezultatZapytania = zapytania.executeQuery(sql);
			lacznosc.commit();

			System.err.println("SELECT zrobiony");

		} catch (SQLException e) {

			try {
				lacznosc.rollback();
				System.err.println(e.getMessage());
				System.err.println("BLAD! Wykonuje ROLLBACK'a");

			} catch (SQLException e1) {
				System.err.println("Blad podczas robienia ROLLBACK'a");
			}
		}
		Produkt produkt = null;
		while (rezultatZapytania.next()) {
			String nazwa = rezultatZapytania.getString(1);
			System.out.println(nazwa);
			String vat = rezultatZapytania.getString(2);/// to sprawdzic
			produkt = new Produkt(nazwa, vat);
		}
		return produkt;
	}

	public DefaultTableModel StworzSelectKlient() throws SQLException {

		String sql = new String();

		sql = "Select * from swing.klient";

		return ObslugaSelectaModel(sql);

	}

	public void ObslugaInserta(String sql) throws SQLException {

		try {
			lacznosc.setAutoCommit(false);
			this.zapytania = lacznosc.createStatement();
			this.zapytania.execute(sql);
			lacznosc.commit();
			System.err.println("INSERT zrobiony");

		} catch (SQLException e) {

			try {
				lacznosc.rollback();
				System.err.println(e.getMessage());
				System.err.println("BLAD! Wykonuje ROLLBACK'a");

			} catch (SQLException e1) {
				System.err.println("Blad podczas robienia ROLLBACK'a");
			}
		}

		this.zapytania.close();

	}

	public DefaultTableModel ObslugaSelectaModel(String sql) throws SQLException {

		ResultSet rezultatZapytania = null;
		try {
			lacznosc.setAutoCommit(false);
			this.zapytania = lacznosc.createStatement();
			rezultatZapytania = zapytania.executeQuery(sql);
			lacznosc.commit();

			System.err.println("SELECT zrobiony");

		} catch (SQLException e) {

			try {
				lacznosc.rollback();
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
		this.zapytania.close();
		return new DefaultTableModel(rows, columnNames);

	}

	public Integer ObslugaSelectaKolumnaId(String sql) throws SQLException {

		ResultSet rezultatZapytania = null;
		try {
			lacznosc.setAutoCommit(false);
			this.zapytania = lacznosc.createStatement();
			rezultatZapytania = zapytania.executeQuery(sql);
			lacznosc.commit();

			System.err.println("SELECT zrobiony");

		} catch (SQLException e) {

			try {
				lacznosc.rollback();
				System.err.println(e.getMessage());
				System.err.println("BLAD! Wykonuje ROLLBACK'a");

			} catch (SQLException e1) {
				System.err.println("Blad podczas robienia ROLLBACK'a");
			}
		}
		int id = 0;
		while (rezultatZapytania.next()) {
			id = rezultatZapytania.getInt(0); /// to sprawdzic
			System.out.println(id);
		}

		return id;

	}

	Connection OtworzPolaczenie() {

		java.sql.Connection lacznosc = null;
		try {
			lacznosc = DriverManager.getConnection(DB_URL, USER, PASS);

		} catch (SQLException e) {
			System.out.println("Blad przy ladowaniu sterownika bazy!");
			System.exit(1);
		}
		return lacznosc;
	}

	void ZaladujSterownik() {
		try {
			Class.forName("org.postgresql.Driver").newInstance();
		} catch (Exception e) {
			System.out.println("Blad przy ladowaniu sterownika bazy!");
			System.exit(1);
		}

	}

	void ZamknijPolaczenie() {
		try {
			this.lacznosc.close();
		} catch (SQLException e) {
			System.out.println("Blad przy zamykaniu polaczenia " + e.toString());
			System.exit(4);
		}
	}

	public int StworzSelectIdFaktura(TextField textNumer) throws SQLException {
		String sql = new String();
		sql = "select swing.faktura.id from swing.faktura where numer='" + textNumer.getText() + "'";
		return ObslugaSelectaKolumnaId(sql);

	}

	public int StworzSelectIdPozycja() throws SQLException {
		String sql = new String();
		sql = " SELECT swing.pozycja.id FROM swing.pozycja WHERE ID IN (SELECT MAX(ID) swing.pozycja);";
		return ObslugaSelectaKolumnaId(sql);

	}

	public void StorzInsertFaktura(TextField textNumer, String klient, List<ArrayList<Produkt>> listaPozycji)
			throws SQLException {

		Integer idFakturaInt = StworzSelectIdFaktura(textNumer);
		String idFaktura = idFakturaInt.toString();
System.out.println(idFaktura);
		String sql1 = new String();
		sql1 = "Insert into swing.faktura(numer,klient_id) values('" + textNumer.getText() + "','" + klient + "');";

		ObslugaInserta(sql1);

		listaPozycji.size();
		for (ArrayList<Produkt> listaProduktow : listaPozycji) {
			String sql2 = new String();
			sql2 = "insert into swing.pozycja(faktura_id) values('" + idFaktura + "');";
			ObslugaInserta(sql2);
			Integer idPozycjaInt = StworzSelectIdPozycja();
			String idPozycja = idPozycjaInt.toString();
			for (Produkt produkt : listaProduktow) {
				String sql3 = new String();
				sql3 = "insert into swing.produkt(nazwa,vat,pozycja_id) values('" + produkt.getNazwa() + "''"
						+ produkt.getVat() + "''" + idPozycja + "');";
				ObslugaInserta(sql3);
			}
		}
	}
}
