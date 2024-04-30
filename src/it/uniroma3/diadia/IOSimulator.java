package it.uniroma3.diadia;

import java.util.Random;

public class IOSimulator implements IO{
	static private String[] comandi = {"vai nord", " vai sud", "vai ovest", "vai est",
										"prendi lanterna", "prendi osso", "prendi spada", 
										"posa lanterna", "posa osso", "posa spada", 
										"guarda stanza", "guarda giocatore", "guarda borsa",
										"aiuto", "fine"};
	private Random generator;
	
	public IOSimulator() {
		this.generator = new Random();
	}

	@Override
	public void mostraMessaggio(String messaggio) {
		// TODO Auto-generated method stub
		System.out.println(messaggio);
	}

	@Override
	public String leggiRiga() {
		// TODO Auto-generated method stub
		return comandi[this.generator.nextInt(comandi.length)];
	}
	
}
