package it.uniroma3.diadia.giocatore;
/**
 * 
 */
public class Giocatore {
	
	private int cfu; //variabile d'istanza che definisce i cfu
	private String nome; //variabile d'istanza che definisce il nome del giocatore
	private Borsa borsa; //variabile d'istanza ad una classe borsa che gestisce la borsa del giocatore
	
	public Giocatore() {
		this.borsa = new Borsa();
	}
	
	// CFU
	/**
	 * @return the cfu
	 */
	public int getCfu() {
		return cfu;
	}
	
	/**
	 * @param cfu the cfu to set
	 */
	public void setCfu(int cfu) {
		this.cfu = cfu;
	}
	
	public void removeCfu() {
		this.cfu--;
	}
	
	public void addCfu() {
		this.cfu++;
	}
	
	// NOME
	/**
	 * @return the name
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	// BORSA
	/**
	 * @return the inventario
	 */
	public Borsa getBorsa() {
		return borsa;
	}
	
	/**
	 * @param inventario the inventario to set
	 */
	public void setBorsa(Borsa borsa) {
		this.borsa = borsa;
	}
	
	public String toString() {
		StringBuilder out = new StringBuilder();
		out.append("Nome: " + this.nome + "\n");
		out.append("CFU: " + this.cfu + "\n");
		return out.toString();
	}
	
	public String getDescrizione() {
		return this.toString();
	}
	
}