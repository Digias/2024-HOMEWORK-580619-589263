package it.uniroma3.diadia.giocatore;

import java.util.*;
import it.uniroma3.diadia.attrezzi.*;

/**
 * 
 */

public class Borsa{

	public final static int DEFAULT_PESO_MAX_BORSA = 10;//peso massimo della borsa

	private List<Attrezzo> attrezzi;
	private int pesoMax;

	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}

	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new ArrayList<>();
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
		if(this.attrezzi.contains(attrezzo))
			return false;
		return this.attrezzi.add(attrezzo);
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
	public List<Attrezzo> getAttrezzi(){
		return this.attrezzi;
	}

	/**
	 * @param nomeAttrezzo 
	 * @return oggetto Attrezzo da nome se presente, null altrimenti
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		
		Attrezzo a;
		Iterator<Attrezzo> iteratore = this.attrezzi.iterator();
		
		while(iteratore.hasNext()) {
			a = iteratore.next();
			if(a.getNome().equals(nomeAttrezzo))
				return a;
		}
		
		return null;
	}

	/**
	 * @return del peso attuale della borsa
	 */
	public int getPeso() {
		int peso = 0;
		
		Iterator<Attrezzo> iter = this.attrezzi.iterator();
		while(iter.hasNext())
			peso += iter.next().getPeso();
		
		return peso;
	}

	/**
	 * @return true se vuoto, false altrimenti
	 */
	public boolean isEmpty() {
		return this.attrezzi.isEmpty();
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
		Attrezzo rimosso = this.getAttrezzo(attrezzo);

		if (rimosso == null)
			return null;

		if(this.attrezzi.remove(rimosso))
			return rimosso;
		return null;

	}

	/**
	 * descrive il contenuto della borsa
	 * @return descrizione della borsa
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();

		if(this.isEmpty())
			s.append("Borsa vuota");
		else {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			
			List<Attrezzo> attrezziOrdinati = new ArrayList<>();
			if(this.getPeso() == this.getPesoMax()) 
				attrezziOrdinati.addAll(this.getContenutoOrdinatoPerPeso());
			else
				attrezziOrdinati.addAll(this.getContenutoOrdinatoPerNome());
			
			Iterator<Attrezzo> iter = attrezziOrdinati.iterator();
			while(iter.hasNext()) 
				s.append(iter.next().toString() + " ");
		}

		return s.toString();
	}

	/**
	 * restituisce la descrzione della borsa
	 * @return String della descrizione della borsa
	 */
	public String getDescrizione() {
		return this.toString();
	}
	
	/**
	 * ordina il contenuto della borsa per peso
	 * se il peso è uguale ordina in base al nome
	 * restituisce una lista
	 * @return Lista di attrezzi ordinata
	 */
	public List<Attrezzo> getContenutoOrdinatoPerPeso()	{
		final List<Attrezzo> ordinato = new ArrayList<>(this.attrezzi);
		final ComparatoreAttrezzoPerPeso cmp = new ComparatoreAttrezzoPerPeso();
		Collections.sort(ordinato, cmp);
		return ordinato;
	}
	
	/**
	 * ordina il contenuto della borsa per nome
	 * se il nome è uguale ordina in base al peso
	 * restituisce un Set
	 * @return Set di attrezzi ordinato 
	 */
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){
		final SortedSet<Attrezzo> ordinato = new TreeSet<>(this.attrezzi);
		return ordinato;
	}
	
	/**
	 * raggruppa il contenuto della borsa in base al peso
	 * Key peso attrezzo
	 * Value set di attrezzi 
	 * @return Map ordinata
	 */
	public Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){
		final Map<Integer,Set<Attrezzo>> peso2attrezzi = new HashMap<>();
		for (Attrezzo a : this.attrezzi) {

			if(peso2attrezzi.containsKey(a.getPeso())){
				//questo attrezzo ha un peso che ho già visto
				//pesco il vecchio Set e aggiungo il nuovo arrivato
				final Set<Attrezzo> stessoPeso = peso2attrezzi.get(a.getPeso());
				stessoPeso.add(a);
			}
			else {
				//questo attrezzo ha un peso mai visto
				//creo nuovo Set per ospitare tutti gli attrezzi correnti e futuri con questo peso
				final Set<Attrezzo> nuovoSet = new HashSet<>();
				nuovoSet.add(a);
				peso2attrezzi.put(a.getPeso(), nuovoSet);
			}
		}
		return peso2attrezzi;
	}
	
	/**
	 * restituisce il contenuto della borsa
	 * viene ordinato per peso
	 * se peso uguale ordinato per nome
	 * @return Set di attrezzi ordinato 
	 */
	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso(){
		final SortedSet<Attrezzo> ordinato = new TreeSet<>(this.getContenutoOrdinatoPerPeso());
		return ordinato;
	}

}