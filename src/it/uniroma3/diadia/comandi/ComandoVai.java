package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai extends AbstractComando {
	
	private Direzione direzione;
	private final static String NOME = "vai";

	/**
	 * esecuzione del comando
	 */
	@Override
	public void esegui(Partita partita) {
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Stanza prossimaStanza = null;
		if (this.direzione == null) {
			this.getIo().mostraMessaggio("Dove vuoi andare? Devi specificare una direzione");
		}

		prossimaStanza = stanzaCorrente.getStanzaAdiacente(this.direzione);
		if (prossimaStanza == null) {
			this.getIo().mostraMessaggio("Direzione inesistente");
			return;
		}

		partita.setStanzaCorrente(prossimaStanza);
		this.getIo().mostraMessaggio(partita.getStanzaCorrente().getNome());
		partita.getGiocatore().removeCfu();
	}
	
	@Override
	public void setParametro(String parametro) {
		this.direzione = Direzione.valueOf(parametro);
	}
	
	@Override
	public String getParametro() {
		return this.direzione.toString();
	}
	
	@Override
	public String getNome() {
		return NOME;
	}

}