package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe che rappresenta un'insieme di Citta
 * @author Giovanni Gheza
 */
public class Mappa {

	//l'insieme delle citta' come mappa la cui chiave e' l'identificativo della citta'
	private Map<Integer,Nodo> agglomerato;
	private int idInizio;
	private int idFine;

	/**
	 * Costruttore vuoto
	 */
	public Mappa() {
		agglomerato = new HashMap<Integer,Nodo>();
	}

	/**
	 * Costruttore che copia un altro agglomerato urbano
	 * @param mapp - l'agglomerato da copiare
	 */
	public Mappa(Mappa mapp) {
		this.agglomerato = new HashMap<Integer,Nodo>();
		
		for(Nodo n: mapp.getAgglomerato().values())
			agglomerato.put(n.getID(),n);
		
		idInizio = mapp.getIdInizio();
		idFine = mapp.getIdFine();
		
		checkForAltriPercorsi();
	}

	/**
	 * Costruttore che crea un'agglomerato partendo da una citta'
	 * @param citta - la citta
	 */
	public Mappa(Nodo citta) {
		agglomerato = new HashMap<Integer,Nodo>();
		agglomerato.put(citta.getID(), citta);
	}

	/**
	 * Aggiunge una citta all'agglomerato
	 * @param citta - citta' da aggiungere
	 * @return vero se la citta' e' stata aggiunta correttamente, falso se la citta' e' gia' presente
	 */
	public boolean addNodo(Nodo citta) {
		if(agglomerato.containsKey(citta.getID()))
			return false;
		
		checkForAltriPercorsi();

		agglomerato.put(citta.getID(), citta);
		
		if(citta.getTipo() == TipiNodi.INIZIO)
			idInizio = citta.getID();
		
		if(citta.getTipo() == TipiNodi.FINE) {
			citta.setMostro(Essere.generaCammo());
			idFine = citta.getID();
		}
		
		checkForAltriPercorsi();

		return true;
	}
	

	
	public void checkForAltriPercorsi() {
		for(Nodo n1: agglomerato.values()) {
			for(int id: n1.getIdCollegamenti()) {
				for(Nodo n2: agglomerato.values()) {
					if(n1.getID() != n2.getID())
						if(id == n2.getID())
							n2.addCollegamento(id);
				}
			}
		}
		
		for(Nodo n1: agglomerato.values()) {
			for(int id: n1.getIdCollegamenti()) {
				if(id == n1.getID()) {
					n1.removeId(id);
					break;
				}
			}
		}
	}

	/**
	 * Rimuove la citta scelta
	 * @param id - l'dentificativo della citta da togliere
	 */
	public void rimuoviCitta(int id) {
		agglomerato.remove(id);
		
		//rimozione del collegamento tra questa e le altre citta'
		for(Nodo c : agglomerato.values()) {
			if(c.getIdCollegamenti().contains(id))
				c.removeId(id);
		}
	}
	
	public void removeCitta(ArrayList<Integer> al) {
		for(int id: al) {
			agglomerato.remove(id);
			
			//rimozione del collegamento tra questa e le altre citta'
			for(Nodo c : agglomerato.values()) {
				if(c.getIdCollegamenti().contains(id))
					c.removeId(id);
			}
		}
	}

	public Map<Integer, Nodo> getAgglomerato() {
		return agglomerato;
	}

	/**
	 * Prende una citta' dato l'id
	 * @param ID - l'ID della citta'
	 * @return la citta' in questione
	 */
	public Nodo getCitta(int ID) {
		return agglomerato.get(ID);
	}
	
	public ArrayList<Integer> getIdStradeDi(int ID){
		return agglomerato.get(ID).getIdCollegamenti();
	}
	
	public int getNumeroStradeDi(int ID) {
		return agglomerato.get(ID).getNumeroCollegamenti();
	}
	
	public int getIdInizio() {
		return idInizio;
	}
	
	public int getIdFine() {
		return idFine;
	}
	
	public ArrayList<Integer> getIdNodiConUnSoloCollegamento(){
		ArrayList<Integer> al = new ArrayList<Integer>();
		for(Nodo c : agglomerato.values()) {
			if(c.getIdCollegamenti().size() == 1)
				if(c.getTipo() != TipiNodi.FINE && c.getTipo() != TipiNodi.INIZIO)
					al.add(c.getID());
		}
		return al;
	}
	
	public ArrayList<Integer> getIdNodiConUnSoloCollegamento(int idDaRisparmiare){
		ArrayList<Integer> al = new ArrayList<Integer>();
		for(Nodo c : agglomerato.values()) {
			if(c.getIdCollegamenti().size() == 1)
				if(c.getTipo() != TipiNodi.FINE && c.getTipo() != TipiNodi.INIZIO)
					if(c.getID()!= idDaRisparmiare) {
						System.out.print("ANFONFAK " + c.getID() + " " + idDaRisparmiare);
						al.add(c.getID());
					}
		}
		return al;
	}

	/**
	 * Controlla se l'agglomerato contiene una citta'
	 * @param id - l'id della citta' 
	 * @return vero se c'e' la citta', falso altrienti
	 */
	public boolean contieneCitta(int id) {
		return agglomerato.containsKey(id);
	}

	/**
	 * controlla se l'agglomerato e' semza citta'
	 * @return vero se l'agglomerato non contiene citta'
	 */
	public boolean isVuoto() {
		return agglomerato.isEmpty();
	}

	/**
	 * Controlla se la prima citta' inserita e' collegata con la seconda
	 * @param ID1 - l'id della citta' da cui si parte
	 * @param ID2 - l'id della citta' a cui si arriva
	 * @return vero se il collegamento e' presente
	 */
	public boolean checkPresenzaStrada(int ID1, int ID2) {
		return agglomerato.get(ID1).isCollegataCon(ID2);
	}
}