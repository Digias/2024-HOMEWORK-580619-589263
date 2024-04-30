package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.comandi.*;


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
	 //

	private IO console;
	private Partita partita; 
	private Labirinto labirinto;

	public DiaDia(IO console) {
		this.labirinto = new Labirinto();
		this.partita = new Partita(this.labirinto);
		this.console = console;
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
		Comando comandoDaEseguire;
		FabbricaDiComandiFisarmonica factory = new FabbricaDiComandiFisarmonica();
		
		comandoDaEseguire = factory.costruisciComando(istruzione);
		comandoDaEseguire.esegui(this.partita);
		this.console.mostraMessaggio(comandoDaEseguire.getMessaggio());
		
		if (this.partita.vinta())
			System.out.println("Hai vinto!");
			if (!this.partita.giocatoreIsVivo())
			System.out.println("Hai esaurito i CFU...");
			return this.partita.isFinita();
	}   

	public static void main(String[] argc) {
		IO io = new IOConsole();
		//IO io = new IOsimulator();
		DiaDia gioco = new DiaDia(io);
		gioco.gioca();
	}

}