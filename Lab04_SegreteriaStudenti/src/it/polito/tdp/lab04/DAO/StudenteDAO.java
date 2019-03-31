package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	
	public List<Studente> getTuttiGliStudenti() {

		final String sql = "SELECT * FROM studente";

		List<Studente> studenti = new LinkedList<Studente>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				int matricola = rs.getInt("matricola");
				String cognome=rs.getString("cognome");
				String nome = rs.getString("nome");
				String cds=rs.getString("CDS");

				System.out.println(matricola + " " + nome + " " + cognome+" "+cds);

				// Crea un nuovo JAVA Bean Studente
				
				Studente s=new Studente(matricola, nome, cognome, cds);
				
				// Aggiungi il nuovo oggetto Studente alla lista corsi
				
				studenti.add(s);
			}
			
			conn.close();

			return studenti;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}
	
	
	/*
	 * Ottengo tutti i corsi a cui è iscritto lo studente
	 */
	public List<Corso> getCorsiDelloStudente(Studente studente) {
		
		final String sql="SELECT * FROM iscrizione, corso WHERE corso.codins=iscrizione.codins AND matricola=?";
		
		List<Corso> corsi = new ArrayList<Corso>();
		
		try {
			
			Connection conn=ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, studente.getMatricola());

			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				
				Corso corso=new Corso(rs.getString("codins"),rs.getInt("crediti"), rs.getString("nome"),rs.getInt("pd"));
				corsi.add(corso);
			}
			
			conn.close();
			
		}catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
		
		return corsi;
		// TODO
	}




	



}
