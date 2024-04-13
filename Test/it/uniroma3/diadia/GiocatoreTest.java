package it.uniroma3.diadia;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.giocatore.Giocatore;

public class GiocatoreTest {
	private Giocatore player;
	
	@Before
	public void setUp() {
		this.player = new Giocatore();
	}
	
	//SET-GET NOME
	@Test
	public void testNome() {
		this.player.setNome("Franco");
		assertEquals("Franco", this.player.getNome());
	}
	
	//SET-GET CFU
	@Test
	public void testCfu() {
		this.player.setCfu(20);
		assertEquals(20, this.player.getCfu());
	}
	
	@Test
	public void testRemoveCfu() {
		this.player.setCfu(20);
		this.player.removeCfu();
		assertEquals(19, this.player.getCfu());
	}
	
	@Test
	public void testAddCfu() {
		this.player.setCfu(20);
		this.player.addCfu();
		assertEquals(21, this.player.getCfu());
	}
	
	//SET-GET BORSA
	@Test
	public void testBorsa() {
		Borsa borsa = new Borsa();
		this.player.setBorsa(borsa);
		assertEquals(borsa, this.player.getBorsa());
	}
	
}