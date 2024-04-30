package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends StanzaProtected{

	private String lockedDirection;
	private String key;
	
	//Costruttore
	public StanzaBloccata(String nome, String lockedDirection, String key) {
		super(nome);
		this.lockedDirection = lockedDirection;
		this.key = key;
	}
	
	@Override
	public StanzaProtected getStanzaAdiacente(String direzione) {
		if(this.lockedDirection == null || this.key == null || this.hasAttrezzo(key))
			return super.getStanzaAdiacente(direzione);
		
		return this;
	}

	@Override
	public String toString() {
		if(this.lockedDirection == null || this.key == null || this.hasAttrezzo(key))
			return super.toString();
		

		StringBuilder risultato = new StringBuilder();
		risultato.append(this.nome);
		risultato.append("\nUscite: ");
		for (String direzione : this.direzioni)
			if (direzione != null && direzione.equals(this.lockedDirection))
				risultato.append("\nLocked direction: " + direzione);
			else
				risultato.append("\nUnlocked direction" + direzione);
		risultato.append("\nAttrezzi nella stanza: ");
		
		if(!this.isEmpty()) 
			for (int i = 0; i < this.numeroAttrezzi; i++) {
				if(this.attrezzi[i] != null) 
					risultato.append(this.attrezzi[i].getDescrizione()+" ");
			}
		else
			risultato.append("Non ci sono attrezzi nella stanza\n");
		return risultato.toString();
	}
	
	@Override
	public String getDescrizione() {
		return this.toString();
	}

}
