package main;

public class Personaggio extends Essere {
	
	private int punteggio = 0;
	private int tentativi = 10;
	private int posizione;
	
	public Personaggio(String nome, int posizione) {
		super(nome, 20, 5, -5, 10, -3, 3);
		this.posizione = posizione;
	}

	public int getPunteggio() {
		return punteggio;
	}

	public int getTentativi() {
		return tentativi;
	}

	public int getPosizione() {
		return posizione;
	}
	
	//*****
	
	public void reset() {
		
	}
	
	public void setPunteggio(int punteggio) {
		this.punteggio = punteggio;
	}

	public void setTentativi(int tentativi) {
		this.tentativi = tentativi;
	}

	public void setPosizione(int posizione) {
		this.posizione = posizione;
	}
	
	public int addPunti(int p) {
		return punteggio += p;
	}
	
	public int removeTentativo() {
		return tentativi--;
	}
	
	public String toString() {
		StringBuffer str = new StringBuffer(getNome());
		
		str.append("\n");
		
		str.append("Punteggio: " + punteggio);
		
		str.append("\n");
		
		str.append("Tentativi: " + tentativi);
		
		str.append("\n");
		
		str.append("HP: " + getVita());
		
		str.append("\n");
		
		str.append("Attacco: " + getAttacco());
		
		str.append("\n");
		
		return str.toString();
	}

	public void resetPostMorte(int p) {
		setVita(20);
		setAttacco(5);
		setPosizione(p);
	}
}