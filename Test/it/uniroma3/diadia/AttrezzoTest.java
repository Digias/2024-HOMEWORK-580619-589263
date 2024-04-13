package it.uniroma3.diadia;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class AttrezzoTest {
	private Attrezzo attrezzo;	
	
	@Before
	public void setUp() {
		this.attrezzo = new Attrezzo("osso", 1);
	}
	
	//GET NOME
	@Test
	public void testGetNome() {
		assertEquals("osso", this.attrezzo.getNome());
	}
	
	//GET PES
	@Test
	public void testGetPeso() {
		assertEquals(1, this.attrezzo.getPeso());
	}

}
