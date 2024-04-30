package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public interface Comando {
	/**
	 * Esecuzione comando
	 */
	public void esegui(Partita partita);
	
	/**
	 * set parametro del comando
	 */
	public void setParametro(String parametro);
	
	/**
	 * get paramentro del comando 
	 */
	public String getParametro();
	
	/**
	 * get nome del comando
	 */
	public String getNome();
	
	/**
	 * restituisci messaggio di output
	 */
	public String getMessaggio();
	
	
}
