package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoGuarda implements Comando {
	private String object;
	private String messaggio;

	public ComandoGuarda() {

	}

	@Override
	public void esegui(Partita partita) {
		StringBuilder msg = new StringBuilder();//creao una stringa che stamperà il messaggio con le informazioni
		
		if(this.object == null) { //controllo se è stato scritto il oggetto di cui si vogliono informazioni
			msg.append("Di quali informazioni hai bisogno?\n");
			msg.append("Borsa \nGiocatore \nStanza \n"); //elenco degli oggetti di cui si possono ricevere informazioni

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
			msg.append("\n");
		}
		this.setMessaggio(msg.toString()); //stampa delle informazioni
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
	public String getMessaggio() {
		return this.messaggio;
	}

	/* 
	 * get parametro
	 */
	@Override
	public String getParametro() {
		// TODO Auto-generated method stub
		return this.object;
	}

	/**
	 * get nome
	 */
	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return "guarda";
	}
	
	/*
	 * set messaggio di output
	 */
	public void setMessaggio(String msg) {
		this.messaggio = msg;
	}
}
