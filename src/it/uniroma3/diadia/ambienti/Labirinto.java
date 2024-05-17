package it.uniroma3.diadia.ambienti;

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
	
	public static LabirintoBuilder newBuilder() {
		return new LabirintoBuilder();
	}
	
    public Stanza getStanzaVincente() {
		return stanzaVincente;
	}

	public void setStanzaVincente(Stanza stanzaVincente) {
		this.stanzaVincente = stanzaVincente;
	}
	
	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}
	
	public void setStanzaIniziale(Stanza stanzaIniziale) {
		this.stanzaIniziale = stanzaIniziale;
	}
	
}

/*
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
this.setStanzaIniziale(atrio);
this.setStanzaVincente(biblioteca);
}

*/