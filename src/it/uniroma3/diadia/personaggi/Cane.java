package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.*;

public class Cane extends AbstractPersonaggio {

	public static final String ATTREZZO_PREFERITO = "osso";
	public static final String MESSAGGIO_ATTACCA = "Adesso ti mordo!!!!\n";
	public static final String MESSAGGIO_REGALA_ATTREZZO = "Ho fatto cadere un attrezzo!!\n";


	public Cane(String nome, String presentazione) {
		super(nome, presentazione);
	}

	@Override
	public String agisci(Partita partita) {
		partita.getGiocatore().removeCfu();
		return MESSAGGIO_ATTACCA;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		if(attrezzo == null || !ATTREZZO_PREFERITO.equals(attrezzo.getNome())) {
			this.agisci(partita);
			return "La prossima volta dammi qualcosa di buono\n";
		}

		partita.getStanzaCorrente().addAttrezzo(new Attrezzo("collare", 2));
		return MESSAGGIO_REGALA_ATTREZZO;
	}

	@Override
	public boolean equals(Object o) {
		if(this.getClass() != o.getClass()) return false;
		Cane that = (Cane) o;
		return this.getNome().equals(that.getNome()) && this.getPresentazione().equals(that.getPresentazione());
	}

}
