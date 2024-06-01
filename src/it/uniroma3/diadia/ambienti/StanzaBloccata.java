package it.uniroma3.diadia.ambienti;

import java.util.*;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccata extends Stanza{

	private Direzione lockedDirection;
	private String key;

	//Costruttore
	public StanzaBloccata(String nome, Direzione direzione, String key) {
		super(nome);
		this.lockedDirection = direzione;
		this.key = key;
	}

	@Override
	public Stanza getStanzaAdiacente(Direzione direzione) {
		if(this.lockedDirection == null || this.key == null || this.hasAttrezzo(key))
			return super.getStanzaAdiacente(direzione);

		return this;
	}

	@Override
	public String toString() {
		if(this.lockedDirection == null || this.key == null || this.hasAttrezzo(key))
			return super.toString();


		StringBuilder risultato = new StringBuilder();
		risultato.append(this.getNome());
		risultato.append("\nUscite: ");
		Iterator<Direzione> iter = this.getDirezioni().iterator();
		while(iter.hasNext()) {
			Direzione d = iter.next();
			if (d != null && d.equals(this.lockedDirection))
				risultato.append("\nLocked direction: " + d);
			else
				risultato.append("\nUnlocked direction" + d);
		}
		risultato.append("\nAttrezzi nella stanza: ");

		if(!this.isEmpty()) {
			Iterator<Attrezzo> i = this.getAttrezzi().iterator();
			while(i.hasNext())
				risultato.append(i.next().getDescrizione()+" ");
		}else
			risultato.append("Non ci sono attrezzi nella stanza");
		
		risultato.append("\n");
		
		if(this.getPersonaggio() == null)
			risultato.append("Non ci sono altri personaggi\n");
		else
			risultato.append("Non sei solo nella stanza!\n");
		
		return risultato.toString();
	}

	@Override
	public String getDescrizione() {
		return this.toString();
	}

}
