package main;

import java.util.ArrayList;

/**
 * Questa classe rappresenta una citta, ovvero un nodo nel grafo di AgglomeratoCittadino
 * @author Gheza Giovanni
 *
 */
public class Nodo {
    
    //nome
    private String nome;
    
    //identificativo
    private int ID;
    
    //tipo del nodo
    private TipiNodi tipo;
    
    //se e' un vicolo ceco
    private boolean ceco;
    
    //citta' con cui si e' collegata
    private ArrayList<Integer> collegamenti = new ArrayList<Integer>();
    
    //distanza dal campo base
    private double distanzaDalCampo = Double.POSITIVE_INFINITY;
    
    //la distanza dall'ultima citta'
    public static final double DISTANZA_TRA_NODI = 1;
    
    //l'ultima citta' da cui si e' passati per raggiungere questa venendo dal campo base
    private int ultimaCittaID = -1;
    
    //il mostro nella casella
    private Essere mostro;

    /**
     * Costruttore vuoto, per default crea un nodo con un mostro
     */
    public Nodo() {
        this.nome = "";
        this.ID = 0;
        
        this.collegamenti = new ArrayList<Integer>();
        this.mostro = new Essere();
        tipo = TipiNodi.MOSTRO;
    }

    /**
     * Costruttore completo con mostro
     * 
     * @param x - posizione x
     * @param y - posizione y
     * @param h - elevazione
     * @param nome - nome della citta
     * @param ID - identificativo
     * @param collegamenti - gli id delle citta adiacenti
     * @param mostro - il mostro in agguato
     */
    public Nodo(String nome, int ID, ArrayList<Integer> collegamenti, Essere mostro) {
        this.nome = nome;
        this.ID = ID;
        this.collegamenti = collegamenti;
        
        this.mostro = mostro;
        tipo = TipiNodi.MOSTRO;
        ceco = true;
    }
    
    public Nodo(String nome, int ID, int[] collegamenti, Essere mostro) {
        this.nome = nome;
        this.ID = ID;
        
        for(Integer coll: collegamenti)
    		this.collegamenti.add(coll);
        
        this.mostro = mostro;
        tipo = TipiNodi.MOSTRO;
        ceco = true;
    }
    
    /**
     * Costruttore completo per il tipo modifica statstica
     * 
     * @param x - posizione x
     * @param y - posizione y
     * @param h - elevazione
     * @param nome - nome della citta
     * @param ID - identificativo
     * @param collegamenti - gli id delle citta adiacenti
     */
    public Nodo(String nome, int ID, ArrayList<Integer> collegamenti) {
        this.nome = nome;
        this.ID = ID;
        this.collegamenti = collegamenti;
        
        this.tipo = TipiNodi.MODIFICA_STATISTICA;
        ceco = true;
    }
    
    public Nodo(String nome, int ID, int[] collegamenti) {
        this.nome = nome;
        this.ID = ID;
        
        for(Integer coll: collegamenti)
    		this.collegamenti.add(coll);
        
        this.tipo = TipiNodi.MODIFICA_STATISTICA;
        ceco = true;
    }
    
    /**
     * Costruttore completo con la specifica del tipo di nodo esplicita
     * 
     * @param x - posizione x
     * @param y - posizione y
     * @param h - elevazione
     * @param nome - nome della citta
     * @param ID - identificativo
     * @param collegamenti - gli id delle citta adiacenti
     * @param tipo - il tipo del nodo
     */
    public Nodo(String nome, int ID, ArrayList<Integer> collegamenti, TipiNodi tipo) {
        this.nome = nome;
        this.ID = ID;
        this.collegamenti = collegamenti;
        
        this.tipo = tipo;
        ceco = true;
    }
    
    public Nodo(String nome, int ID, int[] collegamenti, TipiNodi tipo) {
        this.nome = nome;
        this.ID = ID;
        
        for(Integer coll: collegamenti)
    		this.collegamenti.add(coll);
        
        this.tipo = tipo;
        ceco = true;
    }

    /**
     * Prende il nome della citta'
     * @return il nome della citta'
     */
    public String getNome() {
        return nome;
    }

    /**
     * Prende l'id della citta'
     * @return l'id della citta'
     */
    public int getID() {
        return ID;
    }

    /**
     * Prende la distanza dal campo base della citta'
     * @return la distanza dal campo base della citta'
     */
    public double getDistanzaDalCampo() {
        return distanzaDalCampo;
    }

    /**
     * Prende la distanza che c'e' tra questa citta' e la citta' da cui si arriva venendo dal campo base
     * @return la distanza dall'ultima citta'
     */
    public double getDistanzaDallUltimaCitta() {
    	return DISTANZA_TRA_NODI;
    }
    
    /**
     * Ritorna tutti gli id delle citta' a cui e' collegata
     * @return l'ArrayList con tutti gi id delle citta' adiacenti
     */
    public ArrayList<Integer> getIdCollegamenti() {
        return collegamenti;
    }
    
    public int getNumeroCollegamenti(){
    	return collegamenti.size();
    }

    /**
     * Prende l'id della citta' da cui si arriva venendo dal campo base
     * @return l'id dell'ultima citta'
     */
    public int getUltimaCittaID() {
        return ultimaCittaID;
    }
    
    /**
     * Prende il tipo del nodo
     * @return il tipo del nodo
     */
    public TipiNodi getTipo() {
		return tipo;
	}
    
    /**
     * Prende il mostro del nodo
     * @return il mostro del nodo
     */
    public Essere getMostro() {
    	return mostro;
    }

	public boolean isCeco() {
		return ceco;
	}

	
	/**
     * Setta tutti i valori base della citta, esclusi i collegamenti, in un'unico metodo
     * 
     * @param x - posizione x
     * @param y - posizione y
     * @param h - elevazione
     * @param nome - nome della citta
     * @param ID - identificativo
     */
    public void set(String nome, int ID){
        this.nome = nome;
        this.ID = ID;
    }

    /**
     * Setta la distanza che c'e' tra il campo base e la citta in questione
     * @param dist - distanza tra campo base  citta
     */
    public void setDistanzaDalCampo(double dist) {
        this.distanzaDalCampo = dist;
    }

    /**
     * Setta qual e' la citta subito prima venendo dal campo base
     * @param ID - l'identificativo della citta prima
     */
    public void setUltimoNodoID(int ID) {
        this.ultimaCittaID = ID;
    }
    
    /**
     * Setta il tipo della casella
     * @param tipo
     */
    public void setTipo(TipiNodi tipo) {
		this.tipo = tipo;
	}
    
    /**
     * Setta la cecita'
     * @param ceco
     */
    public void setCeco(boolean ceco) {
		this.ceco = ceco;
	}
    
    public void setMostro(Essere m) {
    	if(!m.getNome().equals("Cammo"))
    		this.tipo = TipiNodi.MOSTRO;
    	
    	this.mostro = m;
    }
    
    public void removeId(int id) {
    	for(Integer i: collegamenti) {
    		if(i == id) {
    			collegamenti.remove(i);
    			return;
    		}
    	}
    }

    /**
     * Aggiunge un collegamento verso una citta
     * @param ID - l'identificativo della citta verso cui il collegamento va
     */
    public void addCollegamento(int ID) {
    	if(!collegamenti.contains(ID))
    		collegamenti.add(ID);
    }

    /**
     * Controlla se la citta e' collegata con un'altra
     * @param ID - lidentificativo dell'altra citta
     * @return vero se a citta e' collegata con l'altra, falso altrimenti
     */
    public boolean isCollegataCon(int ID) {
        return collegamenti.contains(ID);
    }

    /**
     * Controlla se due citta' hanno nome o ID uguale
     * @param c - l'altra citta'
     * @return vero se le citta' hanno nome o ID uguale, falso se uno dei due e' uguale
     */
    public boolean checkNominativi(Nodo c) {
        return c.getNome() == nome
            || c.getID() == ID;
    }

	/**
	 * Funzione toString
	 */
	public String toString() {
		return "[nome=" + nome + ", ID=" + ID + ", collegamenti="
				+ collegamenti + ", distanzaDalCampo=" + distanzaDalCampo + ", ultimoNodoID=" + ultimaCittaID + "]";
	}
}