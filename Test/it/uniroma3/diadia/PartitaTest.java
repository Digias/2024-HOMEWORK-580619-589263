package it.uniroma3.diadia;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;

public class PartitaTest {
	private Partita partita;
	private Labirinto labirinto;	

	@Before
	public void setUp() {
		this.labirinto = new LabirintoBuilder()
				.addStanzaIniziale("Atrio")
				.addAttrezzo("martello", 3)
				.addStanzaVincente("Biblioteca")
				.addAdiacenza("Atrio", "Biblioteca", "nord")
				.getLabirinto();
		this.partita = new Partita(this.labirinto);
	}
	
	//IS VINTA
	@Test
	public void testVintaFalse() {
		assertFalse(this.partita.vinta());
	}
	
	@Test
	public void testVintaTrue() {
		Stanza stanzaVincente = this.partita.getLabirinto().getStanzaVincente();
		this.partita.setStanzaCorrente(stanzaVincente);
		assertTrue(this.partita.vinta());
	}
	
	//SET-GET STANZA CORRENTE
	@Test
	public void testStanzaCorrente() {
		Stanza stanzaCorrente = new Stanza("Stanza corrente");
		this.partita.setStanzaCorrente(stanzaCorrente);
		assertEquals(stanzaCorrente, this.partita.getStanzaCorrente());
	}
	
	//IS FINITA
	@Test
	public void testIsFinita_true() {
		Stanza stanzaVincente = this.partita.getLabirinto().getStanzaVincente();
		this.partita.setStanzaCorrente(stanzaVincente);
		assertTrue(this.partita.isFinita());
	}
	
	@Test
	public void testIsFinita_false() {
		Stanza stanzaCorrente = new Stanza("Stanza corrente");
		this.partita.setStanzaCorrente(stanzaCorrente);
		assertFalse(this.partita.isFinita());
	}
	
}