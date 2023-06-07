package main;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class EsecutorePartita {
	private static final int PUNTI_VITTORIA = 10;
	private Mappa mappaDiBase;
	private Partita partita;
	private boolean continuaGioco = true;
	private boolean continuaApplicazione = true;
	//TODO: questo:
	private MyMenuGhz menu = new MyMenuGhz("ARNALDO_VERSE"
			, new String[]{"Inizia una partita nel mondo", "Cambia il nome del personaggio",
							"Visualizza Personaggio"});
	private MyMenuGhz menuMovimento = new MyMenuGhz("Dove vuoi dirigerti viandante?");
	private MyMenuGhz menuMondi = new MyMenuGhz("Sei di fronte al monolite dell'Arnaldo-verse, scegli un mondo in cui essere trasportato"
							, new String[]{"Mondo del F", "Mondo A", "Mondo Pino"});
	private ArrayList<Integer> mondiGiaVisitati = new ArrayList<Integer>();
	private int mondoInQuestione;
	
	EsecutorePartita(Partita p){
		partita = p;
		mappaDiBase = partita.getMappa();
	}
	
	public void esegui() throws InterruptedException {
		
		eseguiInizio();
		
		
		while(continuaApplicazione) {
			switch(menu.scegli()) {
			case 1:
				eseguiPartita();
				break;
			case 2:
				cambiaNome();
				break;
			case 3:
				stampaPersonaggio();
				break;
			case 0:
				eseguiAddio();
				break;
			}
		}
	}
	
	private void eseguiInizio() {
		System.out.println("ARNALDO-VERSE\n\n");
		System.out.println("Lasciati trasportare in un universo d'avventure, spada in mano e vai!");
		
		if(InputDatiGhz.yesOrNo("\nPronto a cominciare?")) {
			System.out.println("Bene andiamo!");
		} else {
			System.out.println("Troppo tardi per voltarsi indietro!");
		}
		
		System.out.println("\nIn primis, come ti chiami?");
		cambiaNome();
	}
	
	private void cambiaNome() {
		String str = InputDatiGhz.leggiStringaNonVuota("Inserisci il nome del personaggio: ");
		partita.getPersonaggio().setNome(str);
	}
	
	public void stampaPersonaggio() {
		System.out.println(partita.personaggioToString());
	}
	
	private void eseguiPartita() throws InterruptedException {
		eseguiSetUpGioco();
		while(continuaGioco) {
			eseguiGioco();
		}
	}
	
	public void eseguiSceltaMondo() {
		int n = menuMondi.scegli();
		if( n == 0) {
			continuaGioco = false;
			return;
		}
		mondoInQuestione = n;
	}
	
	private void eseguiSetUpGioco() {
		continuaGioco = true;
		partita.setMappa(GeneratoreMappe.generaV2(7));
		partita.resetPostMorte();
		
		//PercorsoSuggerito ps = new PercorsoSuggerito(GeneratoreMappe.generaBase());
		
		//idSuggeriti = ps.dijkstraMagic(partita.getMappa().getIdInizio());
		
		//In attesa di fare il generatore di mappe
		/*if(NumeriCasualiGhz.estraiIntero(0, 5) < 0)
			partita.setMappa(mappaDiBase);
		else
			partita.setMappa(GeneratoreMappe.generaV1(7));*/
		
	}
	
	private void eseguiGioco() throws InterruptedException {
		
		eseguiMovimento();

		switch(partita.getTipoNodoInCuiMiTrovo()) {
		case FINE:
			eseguiFine();
			break;
		case MOSTRO:
			eseguiCombattimento();
			break;
		case MODIFICA_STATISTICA:
			eseguiModificaStatistiche();
			break;
		default:
			return;
		}
	}
	
	private void eseguiMovimento() {
		menuMovimento.setTitolo(partita.personaggioToString() + "\n\nDove andare?");
		menuMovimento.resetVoci();
		
		for(int i = 0; i < partita.getNumeroDiNodiInCuiPossoAndare(); i++) {
			menuMovimento.addVoce("Luogo " + partita.getNodiInCuiPossoAndare().get(i));
		}
		
		int decisione = menuMovimento.scegli();
		
		if(decisione == 0) {
			continuaGioco = false;
			return;
		}
		
		int prossimoId = partita.getNodiInCuiPossoAndare().get(decisione - 1);
		
		partita.muovi(prossimoId);
		partita.checkVicoliCechi();
		
		System.out.println("Ti metti in marcia.");
		
	}
	
	private void eseguiCombattimento() throws InterruptedException {
		ArrayList<String> cosaSuccesse = partita.combatti();
		
		TimeUnit.SECONDS.sleep(1);
		
		System.out.println("Cammina cammina e arrivi a " + partita.getNomeDiDoveMiTrovo() + ".");
		
		TimeUnit.SECONDS.sleep(3);
		
		System.out.println("Oh no! Ti sei imbattutto in un "
							+ partita.getNomeMostroAffrontato()
							+ ", preparati a combattere!\n");
		
		for(String src : cosaSuccesse) {
			
			TimeUnit.SECONDS.sleep(2);
			
			if(src == "MORTE") {
				eseguiBruttaFine();
				return;
			}
			
			System.out.println(src);
		}
	}
	
	private void eseguiModificaStatistiche() throws InterruptedException {
		String str = partita.eseguiModificaStatistiche();
		
		TimeUnit.SECONDS.sleep(1);
		
		System.out.println("Cammina cammina e arrivi a " + partita.getNomeDiDoveMiTrovo() + ".");
		
		TimeUnit.SECONDS.sleep(3);
		
		System.out.println(str);
		
		if(!partita.isPersonaggioVivo()) {
			
			TimeUnit.SECONDS.sleep(2);
			
			System.out.println("La magia del luogo era troppo intensa per la anima martoriata, la sua forza "
					+ "ti ha ucciso.");
		}
	}
	
	private void eseguiFine() throws InterruptedException {
		if(mondiGiaVisitati.contains(mondoInQuestione));
		
		eseguiCombattimento();
		System.out.println("Congratulazioni! Hai raggiunto la tua meta!");
		System.out.println("Sconfiggendo Cammo hai guadagnato " + PUNTI_VITTORIA + " punti");
		continuaGioco = false;
	}
	
	private void eseguiBruttaFine() throws InterruptedException {
		System.out.println("Sei morto.");
		continuaGioco = false;
		partita.getPersonaggio().removeTentativo();
		if(InputDatiGhz.yesOrNo("Iniziare subito un altra partita?")) {
			eseguiPartita();
		}
	}
	
	private void eseguiAddio() {
		System.out.println("Ciao ciao " + partita.getPersonaggio().getNome() + ".");
		continuaApplicazione = false;
	}
}