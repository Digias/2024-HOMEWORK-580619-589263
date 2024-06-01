package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoRegala extends AbstractComando {
	
	private final static String NOME = "regala";
	@Override
	public void esegui(Partita partita) {
		Attrezzo regalo = partita.getGiocatore().getBorsa().getAttrezzo(this.getParametro());
		if(regalo != null) {
			this.getIo().mostraMessaggio(partita.getStanzaCorrente().getPersonaggio().riceviRegalo(regalo, partita));
			partita.getGiocatore().getBorsa().removeAttrezzo(regalo.getNome());
		}
		this.getIo().mostraMessaggio("Attrezzo non presente nella tua borsa!\n");
	}

	@Override
	public String getNome() {
		return NOME;
	}
	
}
