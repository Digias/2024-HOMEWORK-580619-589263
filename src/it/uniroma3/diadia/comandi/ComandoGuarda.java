package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoGuarda extends AbstractComando {
	private String object;
	private final static String NOME = "guarda";

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
			msg.append("\n"); //stampa delle informazioni
		}
		this.getIo().mostraMessaggio(msg.toString()); 
	}


	/*
	 * set parametro 
	 */
	@Override
	public void setParametro(String parametro) {
		this.object = parametro;
	}

	/* 
	 * get parametro
	 */
	@Override
	public String getParametro() {
		return this.object;
	}
	
	@Override
	public String getNome() {
		return NOME;
	}
	
}
