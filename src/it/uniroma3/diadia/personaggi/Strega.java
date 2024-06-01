package it.uniroma3.diadia.personaggi;

import java.util.List;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio {

	private static final String MESSAGGIO_SALUTATA = "Solo perche' mi hai salutato, non ti mando in uno scantinato!";
	private static final String MESSAGGIO_NON_SALUTATA = "Sei proprio una brutta persona, vai nella stanza con meno attrezzi!";

	public Strega(String nome, String presentaz) {
		super(nome, presentaz);
	}

	@Override
	public String agisci(Partita partita) {
		List<Stanza> stanzeAdiacenti = partita.getStanzaCorrente().getListStanzeAdiacenti();
		Stanza maxAttrezzi = null;
		Stanza minAttrezzi = null;
		
		for (int i = 0; i < stanzeAdiacenti.size(); i++) {
			if(stanzeAdiacenti.get(i) != partita.getLabirinto().getStanzaVincente()) {
				maxAttrezzi = stanzeAdiacenti.get(i);
				minAttrezzi = stanzeAdiacenti.get(i);
			}
		}
		
		for(Stanza s : stanzeAdiacenti) {
			if(s != null) {
				if(s != partita.getLabirinto().getStanzaVincente()){
					if(s.getNumeroAttrezzi() > maxAttrezzi.getNumeroAttrezzi())
						maxAttrezzi = s;
					if(s.getNumeroAttrezzi() < minAttrezzi.getNumeroAttrezzi())
						minAttrezzi = s;
				}
			}
		}

		if(this.haSalutato()) {
			partita.setStanzaCorrente(maxAttrezzi);
			return MESSAGGIO_SALUTATA;
		}
		partita.setStanzaCorrente(minAttrezzi);
		return MESSAGGIO_NON_SALUTATA;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		return "AHAHAHAHAHAHHA";
	}

	@Override
	public boolean equals(Object o) {
		if(this.getClass() != o.getClass()) return false;
		Strega that = (Strega) o;
		return this.getNome().equals(that.getNome()) && this.getPresentazione().equals(that.getPresentazione());
	}

}
