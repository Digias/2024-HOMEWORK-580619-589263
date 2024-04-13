package it.uniroma3.diadia;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class BorsaTest {
	private Borsa borsa;
	private Attrezzo osso;

	private Attrezzo[] sequenzaAttrezzi(String...attrezzo) {
		Attrezzo[] elenco = new Attrezzo[attrezzo.length];
		for(int i = 0; i < attrezzo.length; i++)
			elenco[i] = new Attrezzo(attrezzo[i],i);
		return elenco;
	}

	@Before
	public void setUpBorsa() {
		borsa = new Borsa();
	}

	@Before
	public void setUpAttrezzi() {		
		this.osso = new Attrezzo("osso", 1);
	}

	//ADD ATTREZZO
	@Test
	public void testAddAttrezzo_pieno() {
		Attrezzo[] elencoAttrezzi = this.sequenzaAttrezzi("a1", "a2", "a3", "a4", "a5", "a6", "a7", "a8", "a9", "10");
		for (int i = 0; i < elencoAttrezzi.length; i++)
			this.borsa.addAttrezzo(elencoAttrezzi[i]);
		assertFalse(this.borsa.addAttrezzo(osso));
	}

	@Test
	public void testAddAttrezzo_vuoto() {
		assertTrue(this.borsa.addAttrezzo(osso));
	}

	@Test
	public void testAddAttrezzo_troppoPeso() {
		Attrezzo[] elencoAttrezzi = this.sequenzaAttrezzi("a1", "a2", "a3", "a4", "a5");
		//peso oggetti uguale alla posizione oggetto
		for(int i = 0; i < elencoAttrezzi.length; i++)
			this.borsa.addAttrezzo(elencoAttrezzi[i]);
		assertFalse(this.borsa.addAttrezzo(osso));
	}

	@Test
	public void testAddAttrezzo_5Attrezzi() {
		Attrezzo[] elencoAttrezzi = this.sequenzaAttrezzi("a1", "a2", "a3", "a4", "a5");
		//peso oggetti uguale alla posizione oggetto
		for(int i = 0; i < elencoAttrezzi.length; i++)
			this.borsa.addAttrezzo(elencoAttrezzi[i]);
		assertFalse(this.borsa.addAttrezzo(osso));
	}

	//HAS ATTREZZO
	@Test
	public void testHasAttrezzo_presente() {
		Attrezzo[] elencoAttrezzi = this.sequenzaAttrezzi("a1", "a2", "a3", "a4", "a5");
		for (int i = 0; i < elencoAttrezzi.length; i++)
			this.borsa.addAttrezzo(elencoAttrezzi[i]);
		assertTrue(this.borsa.hasAttrezzo("a3"));
	}

	@Test
	public void testHasAttrezzo_nonPresente() {
		Attrezzo[] elencoAttrezzi = this.sequenzaAttrezzi("a1", "a2", "a3", "a4", "a5");
		for (int i = 0; i < elencoAttrezzi.length; i++)
			this.borsa.addAttrezzo(elencoAttrezzi[i]);
		assertFalse(this.borsa.hasAttrezzo("a9"));
	}

	//GET ATTREZZO
	@Test
	public void testGetAttrezzo_presente() {
		Attrezzo[] elencoAttrezzi = this.sequenzaAttrezzi("a1", "a2", "a3", "a4", "a5");
		for (int i = 0; i < elencoAttrezzi.length; i++)
			this.borsa.addAttrezzo(elencoAttrezzi[i]);
		assertEquals(elencoAttrezzi[2],this.borsa.getAttrezzo("a3"));
	}

	@Test
	public void testGetAttrezzo_nonPresente() {
		Attrezzo[] elencoAttrezzi = this.sequenzaAttrezzi("a1", "a2", "a3", "a4", "a5");
		for (int i = 0; i < elencoAttrezzi.length; i++)
			this.borsa.addAttrezzo(elencoAttrezzi[i]);
		assertNull(this.borsa.getAttrezzo("a9"));
	}

	//REMOVE ATTREZZO
	@Test
	public void testRemoveAttrezzo_vuoto() {
		assertNull(this.borsa.removeAttrezzo("osso"));
	}

	@Test
	public void testRemoveAttrezzo_nonTrovato() {
		Attrezzo[] elencoAttrezzi = this.sequenzaAttrezzi("a1", "a2", "a3", "a4", "a5");
		for (int i = 0; i < elencoAttrezzi.length; i++)
			this.borsa.addAttrezzo(elencoAttrezzi[i]);
		assertNull(this.borsa.removeAttrezzo("procione"));
	}

	@Test
	public void testRemoveAttrezzo_Successo() {
		Attrezzo[] elencoAttrezzi = this.sequenzaAttrezzi("a1", "a2", "a3", "a4", "a5");
		for (int i = 0; i < elencoAttrezzi.length; i++)
			this.borsa.addAttrezzo(elencoAttrezzi[i]);
		assertEquals(elencoAttrezzi[2], this.borsa.removeAttrezzo("a3"));
	}
	
	//GET PESO
	@Test
	public void testGetPeso_vuoto() {
		assertEquals(0, this.borsa.getPeso());
	}
	
	@Test
	public void testGetPeso_pieno() {
		Attrezzo[] elencoAttrezzi = this.sequenzaAttrezzi("a1", "a2", "a3", "a4", "a5");
		/**
		 * a1 peso 0
		 * a2 peso 1
		 * a3 peso 2
		 * a4 peso 3
		 * a5 peso 4
		 */
		for (int i = 0; i < elencoAttrezzi.length; i++)
			this.borsa.addAttrezzo(elencoAttrezzi[i]);
		assertEquals(10, this.borsa.getPeso());
	}
	
	//GET PESO MAX
	@Test
	public void testGetPesoMax() {
		//peso massimo impostato a 10 
		assertEquals(10, this.borsa.getPesoMax());
	}
	
	//IS EMPTY
	@Test
	public void testIsEmpty_vuoto() {
		assertTrue(this.borsa.isEmpty());
	}
	
	@Test
	public void testIsEmpty_nonVuoto() {
		Attrezzo[] elencoAttrezzi = this.sequenzaAttrezzi("a1", "a2", "a3", "a4", "a5");
		for (int i = 0; i < elencoAttrezzi.length; i++)
			this.borsa.addAttrezzo(elencoAttrezzi[i]);
		assertFalse(this.borsa.isEmpty());
	}
}
