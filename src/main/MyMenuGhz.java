package main;

import java.util.ArrayList;

/**
 * versione modificata di MyMenu con il fine di avere menu dinamici
 * 
 * Questa classe rappresenta un menu testuale generico a piu' voci
 * Si suppone che la voce per uscire sia sempre associata alla scelta 0 
 * e sia presentata in fondo al menu
 * 
 * Oltre alle voci passate durante la costruzione dell'oggetto � possibile aggiugere nuove voce
 * e rimuoverle a piacere
 *
 */
public class MyMenuGhz
{
  final private static String CORNICE = "\n--------------------------------";
  final private static String VOCE_USCITA = "0\tEsci";
  final private static String RICHIESTA_INSERIMENTO = "Digita il numero dell'opzione desiderata > ";

  private String titolo;
  private ArrayList<String> voci = new ArrayList<String>();

	
  public MyMenuGhz (String titolo, String [] voci)
  {
	this.titolo = titolo;
	for(String voceDaInserire: voci)
		this.voci.add(voceDaInserire);
  }
  
  public MyMenuGhz (String titolo)
  {
	this.titolo = titolo;
  }
  
  public void setTitolo(String titolo) {
	  this.titolo = titolo;
  }
  
  public void resetVoci() {
	  voci = new ArrayList<String>();
  }
  
  public int getNumeroVoci() {
	  return voci.size();
  }
  
  public void addVoce(String voce) {
	  voci.add(voce);
  }
  
  public void removeVoce(int i) {
	  voci.remove(i);
  }

  public int scegli ()
  {
	stampaMenu();
	return InputDatiGhz.leggiIntero(RICHIESTA_INSERIMENTO, 0, voci.size());	 
  }
		
  public void stampaMenu ()
  {
	System.out.println(CORNICE);
	System.out.println(titolo);
	System.out.println(CORNICE);
    for (int i=0; i<voci.size(); i++)
	 {
	  System.out.println( (i+1) + "\t" + voci.get(i));
	 }
    System.out.println();
	System.out.println(VOCE_USCITA);
    System.out.println();
  }
		
}