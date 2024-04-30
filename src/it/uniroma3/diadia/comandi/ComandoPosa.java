package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa implements Comando {
	private String object;
	private String messaggio;

	public ComandoPosa() {
	}

	
	// Esecuzione del comando
	@Override
	public void esegui(Partita partita) {
		StringBuilder msg = new StringBuilder();
		boolean vuoto = partita.getGiocatore().getBorsa().isEmpty();
		if(!vuoto) { //controllo se ci sono attrezzi nella borsa

			if(this.object == null) { //controllo se è stato scritto un attrezzo da prendere
				msg.append("Quale oggetto della tua borsa vuoi posare? \nEcco un elenco:");
				for(Attrezzo a : partita.getGiocatore().getBorsa().getAttrezzi())//stampo elenco di attrezzi nella borsa
					if(a != null)
						msg.append(a.getDescrizione());
				//this.setParametro(this.console.leggiRiga()); //leggo attrezzo scelto
			}else {	

				if(partita.getGiocatore().getBorsa().hasAttrezzo(this.object)) //controllo se attrezzo è presente
					if(partita.getStanzaCorrente().addAttrezzo(partita.getGiocatore().getBorsa().removeAttrezzo(this.object)))//controllo se possibile mettere attrezzo nella stanza
						msg.append(this.object + " rimosso dal tuo inventario \nEcco una descrizione della tua borsa adesso: \n" + partita.getGiocatore().getBorsa().getDescrizione() + "\n");
					else //riga 209
						//nessuna rimozione per via di un fallimento nell'aggiunta nella stanza 
						msg.append("Impossibile inserire oggetto nella stanza!\n");
				else //riga 208
					msg.append("Attrezzo non presente nella tua borsa\n");
			}
		}else //riga 196
			msg.append("Non ci sono attrezzi nella tua borsa\n");

		this.setMessaggio(msg.toString());
	}

	@Override
	public void setParametro(String parametro) {
		this.object = parametro;
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
		return this.object;
	}

	@Override
	public String getNome() {
		return "posa";
	}
	
}
