package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * 
 */

public class Borsa {
	
	public final static int DEFAULT_PESO_MAX_BORSA = 10;//peso massimo della borsa

	private Attrezzo[] attrezzi;
	private int numeroAttrezzi;
	private int pesoMax;

	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}

	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new Attrezzo[10]; // speriamo bastino...
		this.numeroAttrezzi = 0;
	}

	/**
	 * aggiunge un attrezzo nella borsa 
	 * rispettando il numero massimo di attrezzi e il peso massimo
	 * @param attrezzo da aggiungere alla borsa
	 * @return true se inserito correttamente, false altrimenti
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax()) 
			return false;
		if (this.numeroAttrezzi==10) 
			return false;

		this.attrezzi[this.numeroAttrezzi] = attrezzo;
		this.numeroAttrezzi++;
		return true;
	}

	/**
	 * @return del peso massimo della borsa
	 */
	public int getPesoMax() {
		return pesoMax;
	}

	/**
	 * @return elenco di attrezzi
	 */
	public Attrezzo[] getAttrezzi() {
		return this.attrezzi;
	}

	/**
	 * @param nomeAttrezzo 
	 * @return oggetto Attrezzo da nome 
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		for (int i= 0; i<this.numeroAttrezzi; i++)
			if (this.attrezzi[i].getNome().equals(nomeAttrezzo))
				a = attrezzi[i];
		return a;
	}

	/**
	 * @return del peso attuale della borsa
	 */
	public int getPeso() {
		int peso = 0;
		for (int i= 0; i<this.numeroAttrezzi; i++)
			peso += this.attrezzi[i].getPeso();
		return peso;
	}

	/**
	 * @return true se vuoto, false altrimenti
	 */
	public boolean isEmpty() {
		return this.numeroAttrezzi == 0;
	}

	/**
	 * @param nomeAttrezzo
	 * @return true se attrezzo è presente nella borsa, false altrimenti
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo) != null;
	}

	/**
	 * rimuove un attrezzo dalla borsa
	 * @param attrezzo
	 * @return true se rimozione eseguita con successo, false altrimenti
	 */
	public Attrezzo removeAttrezzo(String attrezzo) {
		// TODO da implementare
		Attrezzo a = null;
		for(int i = 0; i < this.numeroAttrezzi; i++) {
			if(this.attrezzi[i].getNome().equals(attrezzo)) { //cerco attrezzo	
				a = this.attrezzi[i];
				for(int j = i; j < this.numeroAttrezzi-1; j++) //ciclo per spostare tutti gli elementi indietro di una posizione per array compatto
					this.attrezzi[j] = this.attrezzi[j+1]; //spostamento elemento j+1 in posizione j
				
				this.attrezzi[this.numeroAttrezzi-1] = null; //ultimo elemento uguale a null per evitare ripetizione
				this.numeroAttrezzi--; //decremento il numero di attrezzi
				return a; // Attrezzo rimosso con successo
			}
		}
		return a; // Attrezzo non trovato
	}

	/**
	 * restituisce una descrizione della Borsa
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			for (int i= 0; i<this.attrezzi.length; i++)
				if(this.attrezzi[i] != null)
					s.append(attrezzi[i].toString()+" ");
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}
	
	public String getDescrizione() {
		return this.toString();
	}

}