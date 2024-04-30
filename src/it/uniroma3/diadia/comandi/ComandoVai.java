package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai implements Comando {
	
	private String direzione;
	private String messaggio;

	public ComandoVai() {
	}

	/**
	 * esecuzione del comando
	 */
	@Override
	public void esegui(Partita partita) {
		// qui il codice per cambiare stanza …
		StringBuilder msg = new StringBuilder();
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Stanza prossimaStanza = null;

		if(direzione == null) {
			msg.append("Ecco un elenco delle stanze adiacenti: \n" + stanzaCorrente.getAllStanzeAdiacenti());
			//this.setParametro(this.console.leggiRiga());
		}else {

			prossimaStanza = stanzaCorrente.getStanzaAdiacente(direzione);
			if (prossimaStanza == null) {
				msg.append("Direzione inesistente");
				return;
			}

			partita.setStanzaCorrente(prossimaStanza);
			partita.getGiocatore().removeCfu();

			msg.append("\nDescrizione della stanza corrente: \n" + stanzaCorrente.getDescrizione());
		}
		this.setMessaggio(msg.toString());
	}

	@Override
	public void setParametro(String parametro) {
		this.direzione = parametro;
	}

	@Override
	public String getMessaggio() {
		return this.messaggio;
	}

	public void setMessaggio(String msg) {
		this.messaggio = msg;
	}

	@Override
	public String getParametro() {
		return this.direzione;
	}

	@Override
	public String getNome() {
		return "vai";
	}
	
}
