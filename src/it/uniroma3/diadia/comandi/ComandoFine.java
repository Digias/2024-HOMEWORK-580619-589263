package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoFine extends AbstractComando {

	public final static String MESSAGGIO_FINE = "Grazie di aver giocato!";
	private final static String NOME = "fine";

	@Override
	public void esegui(Partita partita) {
		partita.setFinita();
		this.getIo().mostraMessaggio(MESSAGGIO_FINE);  // si desidera smettere

	}
	
	@Override
	public String getNome() {
		return NOME;
	}

}
