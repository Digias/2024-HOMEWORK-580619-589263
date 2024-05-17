package it.uniroma3.diadia.comandi;

import java.util.*;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto implements Comando {
	static final public String[] ELENCO_COMANDI = {"vai", "prendi", "posa", "guarda", "aiuto", "fine"};
	private List<String> elencoComandi;
	private IO io;
	public ComandoAiuto() {
		this.elencoComandi = new ArrayList<>(Arrays.asList(ELENCO_COMANDI));
	}
	
	@Override
	public void esegui(Partita partita) {
		StringBuilder msg = new StringBuilder();
		msg.append("Puoi digitare i seguenti comandi: \n");
		for(int i=0; i< elencoComandi.size(); i++) {
			msg.append("\033[0;1m" + elencoComandi.get(i));

			switch(elencoComandi.get(i)) {
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
			case "guarda":
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
		this.io.mostraMessaggio(msg.toString());
	}

	@Override
	public void setParametro(String parametro) {

	}

	public void setMessaggio(String msg) {
	}

	@Override
	public String getParametro() {
		return null;
	}

	@Override
	public String getNome() {
		return "aiuto";
	}

	public List<String> getElencoComandi(){
		return this.elencoComandi;
	}

	@Override
	public void setIo(IO io) {
		this.io = io;
	}
}
