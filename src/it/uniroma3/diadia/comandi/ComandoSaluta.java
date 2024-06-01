package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoSaluta extends AbstractComando{
private final static String NOME = "saluta";

	@Override
	public void esegui(Partita partita) {
		if(partita.getStanzaCorrente().getPersonaggio() == null) 
			this.getIo().mostraMessaggio("Chi stai salutando? Qui ci sei solo tu\n");
		else
			this.getIo().mostraMessaggio(partita.getStanzaCorrente().getPersonaggio().saluta());
	}
	
	@Override
	public String getNome() {
		return NOME;
	}
}
