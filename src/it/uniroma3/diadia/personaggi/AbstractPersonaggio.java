package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public abstract class AbstractPersonaggio {
	private String nome;
	private boolean haSalutato;
	private String presentazione;

	public AbstractPersonaggio(String nome, String presentazione) {
		this.nome = nome;
		this.haSalutato = false;
		this.presentazione = presentazione;
	}

	public String getNome() {
		return this.nome;
	}
	
	public String getPresentazione() {
		return this.presentazione;
	}

	public boolean haSalutato() {
		return this.haSalutato;
	}

	public String saluta() {
		StringBuilder risposta = new StringBuilder("Ciao, io sono ");

		risposta.append(this.getNome()+".");
		if (!haSalutato)

			risposta.append(this.presentazione);

		else

			risposta.append("Ci siamo gia' presentati!");

		this.haSalutato = true;
		return risposta.toString();
	}

	abstract public String agisci(Partita partita);
	
	abstract public String riceviRegalo(Attrezzo attrezzo, Partita partita);

	@Override
	public String toString() {
		return this.getNome();
	}

}
