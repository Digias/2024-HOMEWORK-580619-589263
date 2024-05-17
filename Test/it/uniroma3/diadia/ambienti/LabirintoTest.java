package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class LabirintoTest {
	private Labirinto labirinto;
	
	@Before
	public void setUp() {
		labirinto = new Labirinto();
	}
	
	//GET INGRESSO
	@Test
	public void testGetIngresso() {
		assertEquals("Atrio", this.labirinto.getStanzaIniziale().getNome());
	}
	
	//GET STANZA VINCENTE
	@Test
	public void testGetStanzaVincente() {
		assertEquals("Biblioteca", this.labirinto.getStanzaVincente().getNome());
	}
	
}