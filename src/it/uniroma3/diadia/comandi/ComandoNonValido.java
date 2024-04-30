package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoNonValido implements Comando {
	private String messaggio;
	
	public ComandoNonValido(){
	}

	@Override
	public void esegui(Partita partita) {
		this.setMessaggio("Comando non valido");
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
		return null;
	}

	@Override
	public String getNome() {
		return "Non Valido";
	}

}
