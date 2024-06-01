package it.uniroma3.diadia.comandi;

import java.util.*;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto extends AbstractComando {
	static final public String[] ELENCO_COMANDI = {"vai", "aiuto", "prendi", "posa", "guarda", "saluta", "interagisci", "regala", "fine"};
	private List<String> elencoComandi;
	private final static String NOME = "aiuto";
	
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
			case "saluta":
				msg.append("\033[0m: Saluta un personaggio nella stanza\n");
				break;
			case "interagisci":
				msg.append("\033[0m: Interagisce con un personaggio presente nella stanza\n");
				break;
			case "regala":
				msg.append("\033[0m: Regala un attrezzo ad un personaggio\n");
				break;
			default:
				msg.append("\033[0m: descrizione comando ancora da inserire\n");
				break;
			}
		}
		this.getIo().mostraMessaggio(msg.toString());
	}

	public List<String> getElencoComandi(){
		return this.elencoComandi;
	}
	
	@Override
	public String getNome() {
		return NOME;
	}

}
