package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */

public class Partita {

	static final private int CFU_INIZIALI = 20;
	
	private Giocatore giocatore;
	private Labirinto labirinto;
	private Stanza stanzaCorrente;
	private boolean finita;
	
	public Partita(Labirinto labirinto){
		this.labirinto = labirinto;
		this.stanzaCorrente = this.labirinto.getStanzaIniziale();
		this.finita = false;
		this.giocatore = new Giocatore();
		this.giocatore.setCfu(CFU_INIZIALI);
	}
	
	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}
	
	/**
	 * 
	 * @return della stanza in cui si trova il giocatore
	 */
	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}

	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return this.stanzaCorrente == this.labirinto.getStanzaVincente();
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || (this.giocatore.getCfu() == 0);
	}

	/**
	 * Imposta la partita come finita
	 */
	public void setFinita() {
		this.finita = true;
	}

	/**
	 * @return the player
	 */
	public Giocatore getGiocatore() {
		return giocatore;
	}

	/**
	 * @return the labirinto
	 */
	public Labirinto getLabirinto() {
		return labirinto;
	}
	
}