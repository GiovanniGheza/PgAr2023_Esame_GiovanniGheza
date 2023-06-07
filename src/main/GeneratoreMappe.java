package main;

public abstract class GeneratoreMappe {
	
	private static final String STR[] = {
			"una cattedrale",
			"un campo",
			"una taverna",
			"una piazza cittadina",
			"delle rovine",
			"un bosco",
			"una pineta",
			"una collina",
			"un monte"
	};
	
	public static Mappa generaV1(int dim) {
		Mappa mappa = new Mappa();
		mappa.addNodo(new Nodo("Inizio", 1, new int[]{2,4}, TipiNodi.INIZIO));
		
		for (int i = 2; i < dim; i++) {
			
			int a[] = {NumeriCasualiGhz.estraiIntero(1, dim)};
			TipiNodi t = (NumeriCasualiGhz.tiraMoneta())? TipiNodi.MOSTRO : TipiNodi.MODIFICA_STATISTICA;
			String s = STR[NumeriCasualiGhz.estraiIntero(0, STR.length - 1)];
			
			if(t == TipiNodi.MOSTRO)
				mappa.addNodo(new Nodo(s, i, a, Essere.generaMostro()));
			else
				mappa.addNodo(new Nodo(s, i, a));
		}
		
		mappa.addNodo(new Nodo("Fine", 7, new int[]{6}, TipiNodi.FINE));
		
		mappa.checkForAltriPercorsi();
		
		return mappa;
	}
	
	public static Mappa generaV2(int dim) {
		Mappa mappa = new Mappa();
		mappa.addNodo(new Nodo("Inizio", 1, new int[]{2}, TipiNodi.INIZIO));
		
		int i;
		
		for (i = 2; i < dim-1; i++) {
			
			if(NumeriCasualiGhz.tiraMoneta() && i < dim - 5) { //dritto
				String s = STR[NumeriCasualiGhz.estraiIntero(0, STR.length - 1)];
				mappa.addNodo(new Nodo(s, i, new int[]{i-1,i+1}));
			} else { //biforcazione
				creaBiforcazione(mappa, i - 1);
				i+=3;
			}
		}
		
		while(mappa.getAgglomerato().size() < dim - 1) {
			String s = STR[NumeriCasualiGhz.estraiIntero(0, STR.length - 1)];
			mappa.addNodo(new Nodo(s, i, new int[]{i-1,i+1}));
			i++;
		}
		
		mappa.addNodo(new Nodo("Fine", dim, new int[]{dim - 1}, TipiNodi.FINE));
		
		mappa.checkForAltriPercorsi();
		
		return mappa;
	}
	
	public static Mappa creaBiforcazione(Mappa m, int lastAdd) {
		String s = STR[NumeriCasualiGhz.estraiIntero(0, STR.length - 1)];
		m.addNodo(new Nodo(s, lastAdd + 1, new int[]{lastAdd,lastAdd + 2, lastAdd + 3}));
		s = STR[NumeriCasualiGhz.estraiIntero(0, STR.length - 1)];
		m.addNodo(new Nodo(s, lastAdd + 2, new int[]{lastAdd + 1,lastAdd + 4}));
		s = STR[NumeriCasualiGhz.estraiIntero(0, STR.length - 1)];
		m.addNodo(new Nodo(s, lastAdd + 3, new int[]{lastAdd + 1,lastAdd + 4}));
		s = STR[NumeriCasualiGhz.estraiIntero(0, STR.length - 1)];
		m.addNodo(new Nodo(s, lastAdd + 4, new int[]{lastAdd + 2,lastAdd + 3, lastAdd + 5}));
		
		return m;
	}
	
	public static Mappa generaBase() {
		Mappa mappa = new Mappa();
		mappa.addNodo(new Nodo("Inizio", 1, new int[]{2}, TipiNodi.INIZIO));
		mappa.addNodo(new Nodo("una cattedrale", 2, new int[]{1,3,4}));
		mappa.addNodo(new Nodo("un bosco", 3, new int[]{2,5}, Essere.generaMostro()));
		mappa.addNodo(new Nodo("Brescia", 4, new int[]{2,5}, Essere.generaMostro()));
		mappa.addNodo(new Nodo("una taverna", 5, new int[]{3,4,6}));
		mappa.addNodo(new Nodo("un pascolo", 6, new int[]{5,7},Essere.generaMostro()));
		mappa.addNodo(new Nodo("Fine", 7, new int[]{6}, TipiNodi.FINE));
		
		mappa.checkForAltriPercorsi();
		
		return mappa;
	}
}