package it.uniroma3.diadia;

import java.io.FileNotFoundException;
import java.util.Scanner;

import it.uniroma3.diadia.ambienti.*;
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

	public static final String MESSAGGIO_BENVENUTO = ""+
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
		this.console = console;
	}
	
	public DiaDia(Labirinto labirinto,IO console) {
		this.labirinto = labirinto;
		this.partita = new Partita(this.labirinto);
		this.console = console;
	}

	public void gioca(){
		String istruzione; 
		
		this.console.mostraMessaggio(MESSAGGIO_BENVENUTO);
		do {
			istruzione = this.console.leggiRiga();
		}while (!processaIstruzione(istruzione) );

	} 

	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione){
		Comando comandoDaEseguire;
		FabbricaDiComandiRiflessiva factory = new FabbricaDiComandiRiflessiva(this.console);
		
		comandoDaEseguire = factory.costruisciComando(istruzione);
		
		comandoDaEseguire.esegui(this.partita);
		if (this.partita.vinta())
			this.console.mostraMessaggio("Hai vinto!");
		if (!this.partita.giocatoreIsVivo())
			this.console.mostraMessaggio("Hai esaurito i CFU...");
		return this.partita.isFinita();
	} 

	public static void main(String[] argc){
		Scanner scanner = new Scanner(System.in);
		IO io = new IOConsole(scanner);
		//IO io = new IOSimulator();

		Labirinto labirinto = null;
		try {
			labirinto = Labirinto.newBuilder("labirinto5.txt").getLabirinto();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (FormatoFileNonValidoException e) {
			e.printStackTrace();
		}

		DiaDia gioco = new DiaDia(labirinto, io);
		gioco.gioca();
		scanner.close();
	}
	
}