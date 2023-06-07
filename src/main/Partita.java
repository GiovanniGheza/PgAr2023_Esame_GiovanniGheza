package main;

import java.util.ArrayList;

public class Partita {
	private Mappa mappa;
	private Mappa mappaEffettiva;
	private Personaggio personaggio;
	
	public Partita(Mappa mappa, Personaggio personaggio) {
		this.mappa = mappa;
		mappaEffettiva = new Mappa(mappa);
		this.personaggio = personaggio;
		this.personaggio.setPosizione(mappa.getIdInizio());
	}
	
	//*****
	
	public Personaggio getPersonaggio() {
		return personaggio;
	}
	
	public Mappa getMappa() {
		return mappa;
	}
	
	public String getNomeMostroAffrontato() {
		Essere m = mappa.getCitta(personaggio.getPosizione()).getMostro();
		if(m != null)
			return m.getNome();
		else
			return "";
	}
	
	public String getNomeDiDoveMiTrovo() {
		return mappa.getCitta(personaggio.getPosizione()).getNome();
	}
	
	public TipiNodi getTipoNodoInCuiMiTrovo() {
		return mappa.getCitta(personaggio.getPosizione()).getTipo();
	}
	
	public int getNumeroDiNodiInCuiPossoAndare() {
		System.out.print(personaggio.getPosizione());
		return mappaEffettiva.getNumeroStradeDi(personaggio.getPosizione());
	}
	
	public ArrayList<Integer> getNodiInCuiPossoAndare(){
		ArrayList<Integer> nodi = mappaEffettiva.getIdStradeDi(personaggio.getPosizione());
		
		return nodi;
	}
	
	//*****

	public void setMappa(Mappa mappa) {
		this.mappa = mappa;
		mappaEffettiva = new Mappa(mappa);
	}
	
	public void resetPostMorte() {
		personaggio.resetPostMorte(mappa.getIdInizio());
	}
	
	public void setPosizionePersonaggio(int id) {
		personaggio.setPosizione(id);
	}
	
	//*****
	public void checkVicoliCechi() {
		ArrayList<Integer> al = mappaEffettiva.getIdNodiConUnSoloCollegamento(personaggio.getPosizione());
		
		while(al.size() != 0) {
			mappaEffettiva.removeCitta(al);
			al = mappaEffettiva.getIdNodiConUnSoloCollegamento(personaggio.getPosizione());
		}
	}
	
	public boolean isPersonaggioVivo() {
		return personaggio.isVivo();
	}
	
	public void removeTentativo() {
		personaggio.removeTentativo();
	}
	
	public void addPunti(int p) {
		personaggio.addPunti(p);
	}
	
	//*****
	
	public void muovi(int id) {
		mappaEffettiva.rimuoviCitta(personaggio.getPosizione());
		personaggio.setPosizione(id);
	}
	
	public ArrayList<String> combatti() {
		ArrayList<String> stringeDeiTurni = new ArrayList<String>();
		
		Essere nemico = mappaEffettiva.getCitta(personaggio.getPosizione()).getMostro();
		
		for(int i = 0; nemico.isVivo() && personaggio.isVivo(); i++) {
			if(i % 2 == 0)
				stringeDeiTurni.add(attacco(personaggio, nemico));
			else
				stringeDeiTurni.add(attacco(nemico, personaggio));
		}
		
		if(!nemico.isVivo())
			stringeDeiTurni.add("Congratulazioni! Sei riuscito a sconfiggere il mostro!");
		else
			stringeDeiTurni.add("MORTE");
		
		return stringeDeiTurni;
	}
	
	public String attacco(Essere essAtt, Essere essDef) {
		
		essDef.danneggia(essAtt.getAttacco());
		
		return essAtt.getNome() + " e' riuscito a ferire " + essDef.getNome() + " di " + essAtt.getAttacco() + " HP";
	}
	
	public String eseguiModificaStatistiche() {
		
		int v = personaggio.getVita();
		int var = personaggio.applicaVariazione();
			
		//TODO mettere frasi diverse se la stat aumenta o diminuisce
		if(v != personaggio.getVita())
			return "La magia del luogo ti ha avuto variare la vita di " + var;
		else
			return "La magia del luogo ti ha avuto variare l'attacco di " + var;
	}
	
	//*****
	
	public String personaggioToString() {
		return personaggio.toString();
	}
	
}