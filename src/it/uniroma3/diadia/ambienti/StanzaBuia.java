package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends StanzaProtected{
	private String shinyObject; 

	public StanzaBuia(String nome, String shinyObject) {
		super(nome);
		this.shinyObject = shinyObject;
	}

	/**
	 * @return the oggettoLucente
	 */
	public String getOggettoLucente() {
		return shinyObject;
	}

	@Override
	public String toString() {
		if(this.shinyObject == null || this.hasAttrezzo(shinyObject))
			return super.toString();
		
		return "Qui c'è buio pesto";
	}

	@Override
	public String getDescrizione() {
		return this.toString();
	}
	
}
