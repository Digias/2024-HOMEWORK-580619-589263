package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public interface Comando {

	public void esegui(Partita partita);

	void setParametro(String parametro);

	String getParametro();

	public void setIo(IO io);

	public String getNome();
}

