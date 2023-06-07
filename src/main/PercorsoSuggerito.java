package main;

import java.util.ArrayList;
import java.util.Collection;

public class PercorsoSuggerito {
	//la mappa delle Ande o forse delle Alpi?
		//la mappa che contiene tutte le citta'
		Mappa mappa;

		/**
		 * Costruttore
		 * @param mappa - la mappa su cui ci si sposta
		 */
		public PercorsoSuggerito(Mappa mappa) {
			this.mappa = mappa;
		}

		/**
		 * Calcola la Rotta piu' corta per raggiungere le Rovine Perdute
		 * @param veicolo - il veicolo con cui si viaggia
		 * @return la Rotta per raggiungere le Rovine Perdute
		 */
		public ArrayList<Integer> dijkstraMagic(int idInizio) {
			
			ArrayList<Integer> rotta = new ArrayList<Integer>();

			//tabella con i nodi, la loro distanza dall'origine e il nodo da cui provengono
			Mappa tabella = new Mappa(mappa);
			//insieme Q dei nodi da visitare
			Mappa cittaDaVisitare = new Mappa(tabella);
			//le Rovine Perdute sono sempre l'ultima citta' dell'elenco
			int rovinePerduteID = cittaDaVisitare.getAgglomerato().size() - 1;
			//il Campo Base e' sempre la prima citta' dell'elenco
			int campoBaseID = idInizio;
			//il costo del carburante tra una citta' e l'altra
			double costoCarburante = 0;
			//se ho trovato le rovine
			boolean hoTrovatoLeRovine = false;

			//cerco le Rovine Perdute e calcolo la distanza avanzando
			//questo e' l'argoritmo di Dijkstra
			while(!cittaDaVisitare.isVuoto() && !hoTrovatoLeRovine) {
				//prendo la citta' piu' vicina al campo base, non gia' visitata
				Nodo c =  getPiuVicinaAlCampo((cittaDaVisitare.getAgglomerato().values()));

				//guardo le citta' vicine
				for(int ID: c.getIdCollegamenti()) {
					//se non ho gia' visitato la citta' vicina
					if(cittaDaVisitare.contieneCitta(ID)) {
						//sto controllando verso quella citta'
						Nodo cittaCheStoControllando = tabella.getAgglomerato().get(ID);
						//calcolo quanto mi costa il viaggio
						costoCarburante = Nodo.DISTANZA_TRA_NODI;
						//calcolo la distanza dal campo base
						double distanzaDalCampo = c.getDistanzaDalCampo() + costoCarburante;

						//se la distanza dal campo calcolata ora e' minore dalla distanza gia' calcolata o quella 
						//di default la sostituisco e aggiorno
						if(distanzaDalCampo < cittaCheStoControllando.getDistanzaDalCampo()){
							cittaCheStoControllando.setDistanzaDalCampo(distanzaDalCampo);
							cittaCheStoControllando.setUltimoNodoID(c.getID());
						}

						//se sono arrivato alle Rovine Perdute e' inutile controllara altre citta che magari non
						//ho ancora visitato, quindi esco
						if(cittaCheStoControllando.getID() == rovinePerduteID){
							hoTrovatoLeRovine = true;
							break;
						}
					}
				}

				//dopo aver visitato una citta la devo rimuovere dall'insieme delle citta da visitare
				cittaDaVisitare.rimuoviCitta(c.getID());
			}

			//incomincio dalle rovine perdute
			int cittaInCuiMiTrovoID = rovinePerduteID;

			//finche' non sono al campo base continuo ad avvicinarmi
			while(cittaInCuiMiTrovoID != campoBaseID){
				//aggiungo la citta alla rotta, visto che so andando dalla fina all'inizio devo inserire le citta
				//non in fondo ma in cima alla rotta
				rotta.add(0, cittaInCuiMiTrovoID);
				//torno indietro
				cittaInCuiMiTrovoID = tabella.getCitta(cittaInCuiMiTrovoID).getUltimaCittaID();
			}

			//aggiungo il campo base visto che esco dal ciclo prima di poterlo inserire
			rotta.add(0,cittaInCuiMiTrovoID);

			//ritorno alla rotta
			return rotta;
		}
		
		private Nodo getPiuVicinaAlCampo(Collection<Nodo> cittaDaVisitare) {
			Nodo cittaPiuVicina = cittaDaVisitare.toArray(new Nodo[0])[0];
			double dist = Double.POSITIVE_INFINITY;

			for(Nodo c : cittaDaVisitare) {
				if(c.getDistanzaDalCampo() < dist){
					cittaPiuVicina = c;
					dist = cittaPiuVicina.getDistanzaDalCampo();
				}
			}

			return cittaPiuVicina;
		}
}