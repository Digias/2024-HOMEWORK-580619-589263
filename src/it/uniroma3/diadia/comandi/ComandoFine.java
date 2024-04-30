package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public class ComandoFine implements Comando {
	private String messaggio;

	public ComandoFine() {
		new IOConsole();
	}
	@Override
	public void esegui(Partita partita) {
		this.setMessaggio("Grazie di aver giocato!"); // si desidera smettere
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
		return "fine";
	}

}
