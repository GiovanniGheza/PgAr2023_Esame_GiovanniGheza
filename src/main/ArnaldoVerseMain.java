package main;

public class ArnaldoVerseMain {

	public static void main(String[] args) {
		//costruttore personaggio
		Personaggio personaggio = new Personaggio("", 0);
		
		//costruzione mappa base
		Mappa mappa = new Mappa();
		mappa.addNodo(new Nodo("Inizio", 1, new int[]{2,4}, TipiNodi.INIZIO));
		mappa.addNodo(new Nodo("una cattedrale", 2, new int[]{1}));
		//mappa.addNodo(new Nodo("un bosco", 3, new int[]{2}, Essere.generaMostro()));
		mappa.addNodo(new Nodo("Brescia", 4, new int[]{6,1,2}, Essere.generaMostro()));
		//mappa.addNodo(new Nodo("una taverna", 5, new int[]{2}));
		mappa.addNodo(new Nodo("un pascolo", 6, new int[]{4,7},Essere.generaMostro()));
		mappa.addNodo(new Nodo("Fine", 7, new int[]{6}, TipiNodi.FINE));
		
		mappa.checkForAltriPercorsi();
		
		Partita partita = new Partita(mappa,personaggio);
		
		EsecutorePartita esecutore = new EsecutorePartita(partita);
		
		try {
			esecutore.esegui();
		} catch (InterruptedException e) {
			System.out.println("Attenzione: programma terminato durante un'attesa");
		}
	}
}