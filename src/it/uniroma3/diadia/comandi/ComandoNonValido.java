package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoNonValido implements Comando {
	private IO io;
	
	public ComandoNonValido(){
	}

	@Override
	public void esegui(Partita partita) {
		this.io.mostraMessaggio("Comando non valido");
	}

	@Override
	public void setParametro(String parametro) {
		
	}
	
	@Override
	public void setIo(IO io) {
		this.io = io;
	}

	public void setMessaggio(String msg) {
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
