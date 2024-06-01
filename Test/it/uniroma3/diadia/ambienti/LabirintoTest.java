package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.FormatoFileNonValidoException;

public class LabirintoTest {
	private Labirinto labirinto;
	
	@Before
	public void setUp() throws FileNotFoundException, FormatoFileNonValidoException {
		this.labirinto = Labirinto.newBuilder("labirinto2.txt").getLabirinto();
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