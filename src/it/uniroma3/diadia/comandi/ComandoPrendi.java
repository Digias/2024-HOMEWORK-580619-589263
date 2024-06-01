package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi extends AbstractComando {
	private String object;
	private final static String NOME = "prendi";
	
	// Esecuzione del comando
	@Override
	public void esegui(Partita partita) {
		StringBuilder msg = new StringBuilder();
		boolean vuoto = partita.getStanzaCorrente().isEmpty();

		if(!vuoto) { //controllo se ci sono attrezzi nella stanza

			if(this.object == null) { //controllo se è stato scritto un attrezzo da prendere
				msg.append("Quale attrezzo vuoi prendere? \nEcco un elenco degli attrezzi presenti nella stanza: ");
				for(Attrezzo attre : partita.getStanzaCorrente().getAttrezzi()) //stampo elenco di attrezzi nella stanza
					if(attre != null)
						msg.append(attre.getDescrizione());

				//this.setParametro(this.console.leggiRiga()); //leggo attrezzo scelto
			}else {

				if(partita.getStanzaCorrente().hasAttrezzo(this.object)) //controllo se attrezzo è presente
					if(partita.getGiocatore().getBorsa().addAttrezzo(partita.getStanzaCorrente().getAttrezzo(this.object))) //controllo se possibile mettere attrezzo in inventario
						if(partita.getStanzaCorrente().removeAttrezzo(this.object)) //rimuovi da stanza con controllo di eventuali errori
							//aggiungi a inventario
							msg.append(this.object + " inserito nel tuo inventario\n");
						else{ //riga 179
							//nessuna aggiunta per via di un fallimento nella rimozione dalla stanza
							msg.append("Rimozione non riuscita\n");
							partita.getGiocatore().getBorsa().removeAttrezzo(this.object);
						}
					else //riga 178
						//nessuna aggiunta per via di un fallimento nell'aggiunta alla borsa
						msg.append("NON PUOI PRENDERE L'ATTREZZO! \nEcco il contenuto della tua borsa:\n" + partita.getGiocatore().getBorsa().getDescrizione());
				else //riga 177
					msg.append("Attrezzo non presente nella stanza!\n");
			}

		}else //riga 166
			msg.append("Non ci sono attrezzi in questa stanza\n");
		this.getIo().mostraMessaggio(msg.toString());
	}

	@Override
	public void setParametro(String parametro) {
		this.object = parametro;
	}

	@Override
	public String getParametro() {
		return this.object;
	}
	
	@Override
	public String getNome() {
		return NOME;
	}

}
