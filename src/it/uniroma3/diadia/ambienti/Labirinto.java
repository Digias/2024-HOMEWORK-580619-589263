package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * 	Introdurre la classe Labirinto
	– ha la responsabilità di creare il labirinto, di memorizzare la
		stanza iniziale (entrata) e quella finale (uscita)
	– aggiungere un riferimento ad un'istanza di Labirinto nella
		classe Partita (che ovviamente dovrà essere liberata dalle
		responsabilità spostate nella nuova classe)
 */
public class Labirinto {

	private Stanza stanzaVincente;
	private Stanza stanzaIniziale;

	public Labirinto() {
		this.creaStanze();
	}

	public void creaStanze() {
		// crea gli attrezzi
		Attrezzo lanterna = new Attrezzo("lanterna",3);
		Attrezzo osso = new Attrezzo("osso",1);
		Attrezzo spada = new Attrezzo("spada", 5);
		Attrezzo piccone = new Attrezzo("piccone", 4);

		// crea stanze del labirinto
		Stanza atrio = new Stanza("Atrio");
		Stanza aulaN11 = new Stanza("Aula N11");
		Stanza aulaN10 = new Stanza("Aula N10");
		Stanza laboratorio = new Stanza("Laboratorio Campus");
		Stanza biblioteca = new Stanza("Biblioteca");

		// collega le stanze
		atrio.impostaStanzaAdiacente("nord", biblioteca);
		atrio.impostaStanzaAdiacente("est", aulaN11);
		atrio.impostaStanzaAdiacente("sud", aulaN10);
		atrio.impostaStanzaAdiacente("ovest", laboratorio);
		aulaN11.impostaStanzaAdiacente("est", laboratorio);
		aulaN11.impostaStanzaAdiacente("ovest", atrio);
		aulaN10.impostaStanzaAdiacente("nord", atrio);
		aulaN10.impostaStanzaAdiacente("est", aulaN11);
		aulaN10.impostaStanzaAdiacente("ovest", laboratorio);
		laboratorio.impostaStanzaAdiacente("est", atrio);
		laboratorio.impostaStanzaAdiacente("ovest", aulaN11);
		biblioteca.impostaStanzaAdiacente("sud", atrio);

		// pone gli attrezzi nelle stanze
		aulaN10.addAttrezzo(lanterna);
		atrio.addAttrezzo(osso);
		atrio.addAttrezzo(spada);
		atrio.addAttrezzo(lanterna);
		atrio.addAttrezzo(piccone);

		// il gioco comincia nell'atrio
		this.stanzaIniziale = atrio;  
		this.stanzaVincente = biblioteca;
	}
	
	public Stanza getStanzaVincente() {
		return stanzaVincente;
	}

	/**
	 * @return the stanzaIniziale
	 */
	public Stanza getStanzaIniziale() {
		return stanzaIniziale;
	}
	
}