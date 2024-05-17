package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoGuarda implements Comando {
	private String object;
	private IO io;


	public ComandoGuarda() {

	}

	@Override
	public void esegui(Partita partita) {
		StringBuilder msg = new StringBuilder();//creao una stringa che stamperà il messaggio con le informazioni
		
		if(this.object == null) { //controllo se è stato scritto il oggetto di cui si vogliono informazioni
			msg.append("Di quali informazioni hai bisogno?\n");
			msg.append("Borsa \nGiocatore \nStanza \n"); //elenco degli oggetti di cui si possono ricevere informazioni
			//this.io.mostraMessaggio(msg.toString());
			//this.setParametro(this.console.leggiRiga()); //leggo oggetto di cui si richiedono informazioni
			
		}else {
			
			switch (this.object) {
			case "borsa":
				msg.append(partita.getGiocatore().getBorsa().getDescrizione()); //aggiungo descrizione della borsa alla stringa
				break;
			case "giocatore":
				msg.append(partita.getGiocatore().getDescrizione()); //aggiungo descrizione del giocatore alla stringa
				break;
			case "stanza":
				msg.append(partita.getStanzaCorrente().getDescrizione()); //aggiungo descrizione della stanza alla stringa
				break;
			default:
				msg.append("Informazioni non disponibili per: " + this.object);
				break;
			}
			this.io.mostraMessaggio("\n");
		}
		//this.setMessaggio(msg.toString()); //stampa delle informazioni
	}

	/*
	 * set parametro 
	 */
	@Override
	public void setParametro(String parametro) {
		this.object = parametro;
	}

	/*
	 * get messaggio output
	 */
	@Override
	public void setIo(IO io) {
		this.io = io;
	}

	/* 
	 * get parametro
	 */
	@Override
	public String getParametro() {
		return this.object;
	}

	/**
	 * get nome
	 */
	@Override
	public String getNome() {
		return "guarda";
	}
	
	/*
	 * set messaggio di output
	 */
	public void setMessaggio(String msg) {
	}
	
}
