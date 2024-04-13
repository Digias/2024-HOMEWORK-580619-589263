package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il metodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";

	//final: il vaore della costante non si può più modificare dopo inizializzazione
	//static: la costante è condivisa da tutte le istanze della classe e puo' essere acceduta senza creare un'istanza della classe stessa
	//private: altre classi non possono accedere direttamente
	static final private String[] elencoComandi = {"vai", "prendi","posa", "info", "aiuto", "fine"}; //

	private IOConsole console;
	private Partita partita; 
	private Labirinto labirinto;

	public DiaDia() {
		this.labirinto = new Labirinto();
		this.partita = new Partita(this.labirinto);
		this.console = new IOConsole();
	}

	public void gioca() {		
		this.console.mostraMessaggio("Inserisci il tuo nome? \n");

		String nome; //nome da attribuire al giocatore
		do		
			nome = this.console.leggiRiga(); //leggo il nome del giocatore
		while ((nome == null));

		this.partita.getGiocatore().setNome(nome); // Set del nome del giocatore

		this.console.mostraMessaggio("Benvenuto " + this.partita.getGiocatore().getNome() + "\n");
		this.console.mostraMessaggio(MESSAGGIO_BENVENUTO);

		String istruzione; 
		do		
			istruzione = this.console.leggiRiga();
		while (!processaIstruzione(istruzione));
	}   

	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire = new Comando(istruzione);
		if(comandoDaEseguire.getNome() == null)
			return false;

		switch (comandoDaEseguire.getNome()) {
		case "fine":
			this.fine();
			return true;

		case "vai":
			this.vai(comandoDaEseguire.getParametro());
			break;

		case "prendi":
			this.prendi(comandoDaEseguire.getParametro());
			break;

		case "posa":
			this.posa(comandoDaEseguire.getParametro());
			break;

		case "info":
			this.info(comandoDaEseguire.getParametro());
			break;

		case "aiuto":
			this.aiuto();
			break;

		default:
			this.console.mostraMessaggio("Comando sconosciuto\n");
		}
		if (this.partita.vinta()) {
			this.console.mostraMessaggio("Hai vinto!");
			return true;
		} else
			return false;
	}   

	// Implementazioni dei comandi dell'utente

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		StringBuilder msg = new StringBuilder();
		msg.append("Puoi digitare i seguenti comandi: \n");
		for(int i=0; i< elencoComandi.length; i++) {
			msg.append("\033[0;1m" + elencoComandi[i]);
			
			switch(elencoComandi[i]) {
			case "vai":
				msg.append("\033[0m: Inserisci una direzione e ti sposti nella stanza della direzione indicata\n");
				break;
			case "fine":
				msg.append("\033[0m: Esci dal gioco\n");
				break;
			case "prendi":
				msg.append("\033[0m: Inserisci un oggetto presente nella stanza e metti l'oggetto nella tua borsa\n");
				break;
			case "posa":
				msg.append("\033[0m: Inserisci un oggetto presente nella borsa e poggi l'oggetto nella stanza\n");
				break;
			case "info":
				msg.append("\033[0m: Inserisci quali informazioni vuoi avere e ricevi una descrizione\n");
				break;
			case "aiuto":
				msg.append("\033[0m: Descrizione di tutti i comandi che puoi utilizzare\n");
				break;
			default:
				msg.append("\033[0m: descrizione comando ancora da inserire\n");
				break;
			}
		}
		this.console.mostraMessaggio(msg.toString());
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		/*
		if(direzione == null) {
			this.console.mostraMessaggio("In che direzione vuoi andare ?"); //file originale ("dove vuoi andare")
			direzione = this.console.leggiRiga();
		}
		 */
		if(direzione == null) {
			this.console.mostraMessaggio("Ecco un elenco delle stanze adiacenti: \n" + this.partita.getStanzaCorrente().getAllStanzeAdiacenti());
			direzione = this.console.leggiRiga();
		}

		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			this.console.mostraMessaggio("Direzione inesistente");
		else {
			this.partita.setStanzaCorrente(prossimaStanza);
			//int cfu = this.partita.getPlayer().getCfu();
			this.partita.getGiocatore().removeCfu();
		}
		this.console.mostraMessaggio("\nDescrizione della stanza corrente: \n" + this.partita.getStanzaCorrente().getDescrizione());
	}

	/**
	 * Il giocatore prende un attrezzo dalla stanza se ci sono degli oggetti
	 * @param object nome dell'attrezzo che si vuole raccogliere
	 */
	private void prendi(String object) {
		boolean vuoto = this.partita.getStanzaCorrente().isEmpty();
		if(!vuoto) { //controllo se ci sono attrezzi nella stanza

			if(object == null) { //controllo se è stato scritto un attrezzo da prendere
				this.console.mostraMessaggio("Quale attrezzo vuoi prendere? \nEcco un elenco degli attrezzi presenti nella stanza: ");
				for(Attrezzo attre : this.partita.getStanzaCorrente().getAttrezzi()) //stampo elenco di attrezzi nella stanza
					if(attre != null)
						this.console.mostraMessaggio(attre.getDescrizione());

				object = this.console.leggiRiga(); //leggo attrezzo scelto
			}

			if(this.partita.getStanzaCorrente().hasAttrezzo(object)) //controllo se attrezzo è presente
				if(this.partita.getGiocatore().getBorsa().addAttrezzo(this.partita.getStanzaCorrente().getAttrezzo(object))) //controllo se possibile mettere attrezzo in inventario
					if(this.partita.getStanzaCorrente().removeAttrezzo(object)) //rimuovi da stanza con controllo di eventuali errori
						//aggiungi a inventario
						this.console.mostraMessaggio(object + " inserito nel tuo inventario\n");
					else{ //riga 179
						//nessuna aggiunta per via di un fallimento nella rimozione dalla stanza
						this.console.mostraMessaggio("Rimozione non riuscita\n");
						this.partita.getGiocatore().getBorsa().removeAttrezzo(object);
					}
				else //riga 178
					//nessuna aggiunta per via di un fallimento nell'aggiunta alla borsa
					this.console.mostraMessaggio("NON PUOI PRENDERE L'ATTREZZO! \nEcco il contenuto della tua borsa:\n" + this.partita.getGiocatore().getBorsa().getDescrizione());
			else //riga 177
				this.console.mostraMessaggio("Attrezzo non presente nella stanza!\n");
		}else //riga 166
			this.console.mostraMessaggio("Non ci sono attrezzi in questa stanza\n");
	}
	
	/**
	 * Comando posa che lascia un determinato ogetto dalla borsa nella stanza
	 * @param object attrezzo che si vuole posare dalla borsa
	 */
	private void posa(String object) {
		boolean vuoto = this.partita.getGiocatore().getBorsa().isEmpty();
		if(!vuoto) { //controllo se ci sono attrezzi nella borsa

			if(object == null) { //controllo se è stato scritto un attrezzo da prendere
				this.console.mostraMessaggio("Quale oggetto della tua borsa vuoi posare? \nEcco un elenco:");
				for(Attrezzo a : this.partita.getGiocatore().getBorsa().getAttrezzi())//stampo elenco di attrezzi nella borsa
					if(a != null)
						this.console.mostraMessaggio(a.getDescrizione());
				object = this.console.leggiRiga(); //leggo attrezzo scelto
			}	
			
			if(this.partita.getGiocatore().getBorsa().hasAttrezzo(object)) //controllo se attrezzo è presente
				if(this.partita.getStanzaCorrente().addAttrezzo(this.partita.getGiocatore().getBorsa().removeAttrezzo(object)))//controllo se possibile mettere attrezzo nella stanza
						this.console.mostraMessaggio(object + " rimosso dal tuo inventario \nEcco una descrizione della tua borsa adesso: \n" + this.partita.getGiocatore().getBorsa().getDescrizione() + "\n");
				else //riga 209
					//nessuna rimozione per via di un fallimento nell'aggiunta nella stanza 
					this.console.mostraMessaggio("Impossibile inserire oggetto nella stanza!\n");
			else //riga 208
				this.console.mostraMessaggio("Attrezzo non presente nella tua borsa\n");
		}else //riga 196
			this.console.mostraMessaggio("Non ci sono attrezzi nella tua borsa\n");
	}
	
	/**
	 * Comando che stampa la descrizione di alcuni oggetti di cui si può richiedere delle informazioni
	 * @param object stringa che rappresenta l'oggetto di cui si vogliono sapere le informazioni
	 */
	private void info(String object) {
		if(object == null) { //controllo se è stato scritto il oggetto di cui si vogliono informazioni
			this.console.mostraMessaggio("Di quali informazioni hai bisogno?\n");
			this.console.mostraMessaggio("Borsa \nGiocatore \nStanza \n"); //elenco degli oggetti di cui si possono ricevere informazioni

			object = this.console.leggiRiga(); //leggo oggetto di cui si richiedono informazioni
		}

		StringBuilder msg = new StringBuilder();//creao una stringa che stamperà il messaggio con le informazioni

		switch (object) {
		case "borsa":
			msg.append(this.partita.getGiocatore().getBorsa().getDescrizione()); //aggiungo descrizione della borsa alla stringa
			break;
		case "giocatore":
			msg.append(this.partita.getGiocatore().getDescrizione()); //aggiungo descrizione del giocatore alla stringa
			break;
		case "stanza":
			msg.append(this.partita.getStanzaCorrente().getDescrizione()); //aggiungo descrizione della stanza alla stringa
			break;
		default:
			msg.append("Informazioni non disponibili per: " + object);
			break;
		}
		msg.append("\n");
		this.console.mostraMessaggio(msg.toString()); //stampa delle informazioni
	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		this.console.mostraMessaggio("Grazie di aver giocato!"); // si desidera smettere
	}

	public static void main(String[] argc) {
		DiaDia gioco = new DiaDia();
		gioco.gioca();
	}

}