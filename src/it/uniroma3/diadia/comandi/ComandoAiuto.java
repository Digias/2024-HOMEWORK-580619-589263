package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoAiuto implements Comando {
	private String[] elencoComandi;
	private String messaggio;

	public ComandoAiuto(String[] elenco) {
		this.elencoComandi = elenco;
	}
	
	@Override
	public void esegui(Partita partita) {
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
		this.setMessaggio(msg.toString());
	}

	@Override
	public void setParametro(String parametro) {

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return "aiuto";
	}

}
