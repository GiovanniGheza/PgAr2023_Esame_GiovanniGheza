package main;

public class Essere {
	private String nome;
	
	private int vita;
	private int attacco;
	
	private Range variazioneVita;
	private Range variazioneAttacco;
	
	public Essere() {
		this.nome = "Mostro";
		this.vita = 12;
		this.attacco = 3;
		this.variazioneVita = new Range(-5,5);
		this.variazioneAttacco = new Range(-2,2);
	}
	
	public Essere(String nome, int vita, int attacco, Range variazioneVita, Range variazioneAttacco) {
		this.nome = nome;
		this.vita = vita;
		this.attacco = attacco;
		this.variazioneVita = variazioneVita;
		this.variazioneAttacco = variazioneAttacco;
	}
	
	public Essere(String nome, int vita, int attacco, int minVarVita, int maxVarVita, int minVarAttacco, int maxVarAttacco) {
		this.nome = nome;
		this.vita = vita;
		this.attacco = attacco;
		this.variazioneVita = new Range(minVarVita,maxVarVita);
		this.variazioneAttacco = new Range(minVarAttacco,maxVarAttacco);
	}

	public String getNome() {
		return nome;
	}
	
	public int getVita() {
		return vita;
	}
	
	public int getAttacco() {
		return attacco;
	}
	
	public Range getVariazioneVita() {
		return variazioneVita;
	}
	
	public Range getVariazioneAttacco() {
		return variazioneAttacco;
	}
	
	public boolean isVivo() {
		return vita > 0;
	}
	
	//*****

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setVita(int vita) {
		this.vita = vita;
	}
	
	public void variaVita(int val) {
		vita += val;
	}
	
	public void danneggia(int dan) {
		vita -= dan;
	}

	public void setAttacco(int attacco) {
		this.attacco = attacco;
	}
	
	public void variaAttacco(int val) {
		attacco += val;
	}

	public void setVariazioneVita(Range variazioneVita) {
		this.variazioneVita = variazioneVita;
	}
	
	public void setVariazioneVita(int min, int max) {
		this.variazioneVita.reset(min, max);
	}

	public void setVariazioneAttacco(Range variazioneAttacco) {
		this.variazioneAttacco = variazioneAttacco;
	}
	
	public void setVariazioneAttacco(int min, int max) {
		this.variazioneAttacco.reset(min, max);
	}
	
	//*****
	/**
	 * Fa variare la vita o l'attacco dell'essere
	 * @return la variazione
	 */
	public int applicaVariazione() {
		int var;
		if(NumeriCasualiGhz.tiraMoneta()) {
			var = variazioneVita.getRandom();
			vita += var;
		} else {
			var = variazioneAttacco.getRandom();
			attacco += var;
			if(attacco <= 0) {
				attacco = 1;
				return attacco - 1;
			}
		}
		
		return var;
	}
	
	//*****
	public static Essere generaMostro() {
		Essere e = new Essere();
		e.applicaVariazione();
		return e;
	}
	
	public static Essere generaCammo() {
		Essere e = new Essere("Cammo",18,4,-5,5,-4,4);
		e.applicaVariazione();
		return e;
	}
}