package it.uniroma3.diadia.ambienti;

import java.util.*;
import java.util.Map.Entry;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author docente di POO 
 * @see Attrezzo
 * @version base
 */

public class StanzaProtected {
	
	static final private int NUMERO_MASSIMO_DIREZIONI = 4;
	static final private int NUMERO_MASSIMO_ATTREZZI = 10;

	protected String nome;
	protected List<Attrezzo> attrezzi;
	protected Map<String,StanzaProtected> stanzeAdiacenti;
	
	public Map<String, StanzaProtected> getStanzeAdiacenti() {
		return stanzeAdiacenti;
	}

	public void setStanzeAdiacenti(Map<String, StanzaProtected> stanzeAdiacenti) {
		this.stanzeAdiacenti = stanzeAdiacenti;
	}

	/**
	 * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
	 * @param nome il nome della stanza
	 */
	public StanzaProtected(String nome) {
		this.nome = nome;
		this.attrezzi = new ArrayList<>(NUMERO_MASSIMO_ATTREZZI);
		this.stanzeAdiacenti = new HashMap<>();
	}
	
	public StanzaProtected getStanza() {
		return this;
	}
	
	/**
	 *  Verifica il numero di attrezzi possibili da inserire
	 * @return numero di attrezzi che posso ancora inserire
	 */
	public int getNumeroAttrezziPossibili() {
		return NUMERO_MASSIMO_ATTREZZI - this.attrezzi.size();
	}

	/**
	 * Imposta una stanza adiacente.
	 *
	 * @param direzione direzione in cui sara' posta la stanza adiacente.
	 * @param stanza stanza adiacente nella direzione indicata dal primo parametro.
	 */
	public void impostaStanzaAdiacente(String direzione, StanzaProtected stanza) {
		if(this.stanzeAdiacenti.size() < NUMERO_MASSIMO_DIREZIONI)
			this.stanzeAdiacenti.put(direzione, stanza);
	}

	/**
	 * Restituisce la stanza adiacente nella direzione specificata
	 * @param direzione
	 */
	public StanzaProtected getStanzaAdiacente(String direzione) {
		return this.stanzeAdiacenti.get(direzione);
	}

	/**
	 * Restituisce un elenco delle stanze adiacenti
	 * @return elenco delle stanze adiacenti
	 */

	public String getAllStanzeAdiacenti() {
		StringBuilder stanza = new StringBuilder();
		for (Entry<String, StanzaProtected> entry : this.stanzeAdiacenti.entrySet()) 
			stanza.append(entry.getValue() + "Direzione: " + entry.getKey() + "\n");
		return stanza.toString();
	}

	/**
	 * Restituisce la nome della stanza.
	 * @return il nome della stanza
	 */
	public String getNome() {
		return this.nome;
	}
	
	/**
	 * Restituisce la collezione di attrezzi presenti nella stanza.
	 * @return la collezione di attrezzi nella stanza.
	 */
	public List<Attrezzo> getAttrezzi() {
		return this.attrezzi;
	}
	
	/**
	 * Controlla se array degli attrezzi della stanza è vuoto
	 * @return true se vuoto, false altrimenti
	 */
	public boolean isEmpty() {
		return this.attrezzi.isEmpty();
	}
	
	/**
	 * restituisca una lista contenente le direzioni delle stanze adiacenti
	 * @return lista delle direzioni
	 */
	public List<String> getDirezioni() {
		final List<String> listaDirezioni= new ArrayList<>();
		listaDirezioni.addAll(this.stanzeAdiacenti.keySet());
		return listaDirezioni;
	}
	
	/**
	 * restituisce una mappa contente le stanze adiacenti
	 * chiave String direzione 
	 * valore Stanza stanza adiacente
	 * @return map delle stanze adiacenti
	 */
	public Map<String,StanzaProtected> getMapStanzeAdiacenti() {
		return this.stanzeAdiacenti;
	}

	/**
	 * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
	 * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		if(this.isEmpty())
			return null;

		Iterator<Attrezzo> iteratore = this.attrezzi.iterator();
		while(iteratore.hasNext()) {
			Attrezzo a = iteratore.next();
			if(a.getNome().equals(nomeAttrezzo))
				return a;
		}
		return null;
	}
	
	/**
	 * Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	 * @return true se l'attrezzo esiste nella stanza, false altrimenti.
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo) != null;
	}
	
	/**
	 * Mette un attrezzo nella stanza.
	 * @param attrezzo l'attrezzo da mettere nella stanza.
	 * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		return this.attrezzi.add(attrezzo);
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(String attrezzo) {
		Attrezzo rimosso = this.getAttrezzo(attrezzo);
		if(rimosso == null)
			return false;

		return this.attrezzi.remove(rimosso);
	}

	/**
	 * Restituisce la descrizione della stanza.
	 * @return la descrizione della stanza
	 */
	public String getDescrizione() {
		return this.toString();
	}

	/**
	 * Restituisce una rappresentazione stringa di questa stanza,
	 * stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	 * @return la rappresentazione stringa
	 */
	public String toString() {
		StringBuilder risultato = new StringBuilder();
		risultato.append(this.nome);

		risultato.append("\nUscite: ");
		for (Entry<String,StanzaProtected> entry: this.stanzeAdiacenti.entrySet())
			risultato.append("\n" + entry.getKey());

		risultato.append("\nAttrezzi nella stanza: ");
		if(!this.isEmpty()) 
			for (Attrezzo a : this.attrezzi) {
				risultato.append(a.getDescrizione()+" ");
			}
		else
			risultato.append("Non ci sono attrezzi nella stanza\n");
		return risultato.toString();
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(nome);
	}

	@Override
	public boolean equals(Object obj) {
		
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StanzaProtected that = (StanzaProtected) obj;
		return this.getNome().equals(that.getNome());
	}

}
