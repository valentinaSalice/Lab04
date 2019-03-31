package it.polito.tdp.lab04.model;

import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	private CorsoDAO dao=new CorsoDAO();
	private StudenteDAO studDao=new StudenteDAO();
	
	private List<Corso> corsi=dao.getTuttiICorsi();
	private List<Studente> studenti=studDao.getTuttiGliStudenti();
	

	public List<Corso> getCorsi() {
		return corsi;
	}
	
	public Studente getStudente(int matricola) {
		
		
		for(Studente s: studenti) {
			
			if(s.getMatricola()==matricola) {
				
				return s;
			}
		}
		
		return null;
	}
	
	public List<Studente> informazioniCorso(String nomeCorso) {
		
		for(Corso c:corsi) {
			
			if(c.getNome().compareTo(nomeCorso)==0) {
				
				List<Studente>iscritti=dao.getStudentiIscrittiAlCorso(c);
				return iscritti;
			}
				
		}
			
		
		
		return null;
	}
	
	
	public List<Corso> informazioniStudente(int matricola){
		
		for(Studente s: studenti) {
			
			if(s.getMatricola()==matricola) {
				
				List<Corso> corsi=studDao.getCorsiDelloStudente(s);
				return corsi;
			}
		}
		
		return null;
	}
	
	public boolean iscrittoAlCorso(int matricola, String nomeCorso) {
		
		List<Studente>iscritti=this.informazioniCorso(nomeCorso);
		
		if(iscritti!=null) {
			
			for(Studente s: iscritti) {
				
				if(s.getMatricola()==matricola) {
					
					return true;
				}
					
			}
		}
		
		return false;
		
	}


	
	
	
	

}
