package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaTest {
	private Stanza stanza1;
	private Attrezzo osso;
	String[] direzioni = {"nord", "est", "ovest", "sud"};

	private Stanza[] sequenzaStanze(String... stanza) {
		Stanza[] elencoStanze = new Stanza[stanza.length];
		for(int i = 0; i < stanza.length; i++)
			elencoStanze[i] = new Stanza(stanza[i]) ;

		return elencoStanze;
	}

	private Attrezzo[] sequenzaAttrezzi(String...attrezzo) {
		Attrezzo[] elenco = new Attrezzo[attrezzo.length];
		for(int i = 0; i < attrezzo.length; i++)
			elenco[i] = new Attrezzo(attrezzo[i],i);

		return elenco;
	}

	@Before
	public void setUpStanza() {
		stanza1 = new Stanza("Stanza1");
	}

	@Before
	public void setUpAttrezzi() {		
		this.osso = new Attrezzo("osso", 1);
	}

	//SET-GET STANZA ADIACENTE
	@Test 
	public void testStanzaAdiacente_vuota() {
		for(int i = 0; i < this.direzioni.length; i++)
			assertNull(this.stanza1.getStanzaAdiacente(this.direzioni[i]));
	}

	@Test
	public void testStanzaAdiancente_Piena() {
		Stanza[] elencoStanza = this.sequenzaStanze("stanza1", "stanza2", "stanza3","stanza4");
		for(int i = 0; i < 4; i++)
			this.stanza1.impostaStanzaAdiacente(this.direzioni[i], elencoStanza[i]);
		for(int i = 0; i < 4; i++)
			assertEquals(elencoStanza[i], this.stanza1.getStanzaAdiacente(this.direzioni[i]));
	}

	@Test
	public void testStanzaAdiacente_dueStanze() {
		Stanza[] elencoStanza = this.sequenzaStanze("stanza1", "stanza2");
		for(int i = 0; i < 2; i++)
			this.stanza1.impostaStanzaAdiacente(this.direzioni[i], elencoStanza[i]);
		for(int i = 0; i < 2; i++)
			assertEquals(elencoStanza[i], this.stanza1.getStanzaAdiacente(this.direzioni[i]));
	}

	//ADD ATTREZZO
	
	public void testAddAttrezzo_pieno() {
		Attrezzo[] elencoAttrezzi = this.sequenzaAttrezzi("a1", "a2", "a3", "a4", "a5", "a6", "a7", "a8", "a9", "10");
		for (int i = 0; i < elencoAttrezzi.length; i++)
			this.stanza1.addAttrezzo(elencoAttrezzi[i]);
		assertFalse(this.stanza1.addAttrezzo(osso));
	}

	@Test
	public void testAddAttrezzo_vuoto() {
		assertTrue(this.stanza1.addAttrezzo(osso));
	}

	@Test
	public void testAddAttrezzo_5Attrezzi() {
		Attrezzo[] elencoAttrezzi = this.sequenzaAttrezzi("a1", "a2", "a3", "a4", "a5");
		for (int i = 0; i < elencoAttrezzi.length; i++)
			this.stanza1.addAttrezzo(elencoAttrezzi[i]);
		assertTrue(this.stanza1.addAttrezzo(osso));
	}

	//GET NOME
	@Test
	public void testGetNome() {
		Stanza[] stanza = this.sequenzaStanze("Stanza");
		assertEquals("Stanza", stanza[0].getNome());
	}

	//HAS ATTREZZO
	@Test
	public void testHasAttrezzo_presente() {
		Attrezzo[] elencoAttrezzi = this.sequenzaAttrezzi("a1", "a2", "a3", "a4", "a5");
		for (int i = 0; i < elencoAttrezzi.length; i++)
			this.stanza1.addAttrezzo(elencoAttrezzi[i]);
		assertTrue(this.stanza1.hasAttrezzo("a3"));
	}

	@Test
	public void testHasAttrezzo_nonPresente() {
		Attrezzo[] elencoAttrezzi = this.sequenzaAttrezzi("a1", "a2", "a3", "a4", "a5");
		for (int i = 0; i < elencoAttrezzi.length; i++)
			this.stanza1.addAttrezzo(elencoAttrezzi[i]);
		assertFalse(this.stanza1.hasAttrezzo("a9"));
	}

	//GET ATTREZZO
	@Test
	public void testGetAttrezzo_presente() {
		Attrezzo[] elencoAttrezzi = this.sequenzaAttrezzi("a1", "a2", "a3", "a4", "a5");
		for (int i = 0; i < elencoAttrezzi.length; i++)
			this.stanza1.addAttrezzo(elencoAttrezzi[i]);
		assertEquals(elencoAttrezzi[2],this.stanza1.getAttrezzo("a3"));
	}

	@Test
	public void testGetAttrezzo_nonPresente() {
		Attrezzo[] elencoAttrezzi = this.sequenzaAttrezzi("a1", "a2", "a3", "a4", "a5");
		for (int i = 0; i < elencoAttrezzi.length; i++)
			this.stanza1.addAttrezzo(elencoAttrezzi[i]);
		assertNull(this.stanza1.getAttrezzo("a9"));
	}

	//REMOVE ATTREZZO
	@Test
	public void testRemoveAttrezzo_vuoto() {
		assertFalse(this.stanza1.removeAttrezzo("osso"));
	}

	@Test
	public void testRemoveAttrezzo_nonTrovato() {
		Attrezzo[] elencoAttrezzi = this.sequenzaAttrezzi("a1", "a2", "a3", "a4", "a5");
		for (int i = 0; i < elencoAttrezzi.length; i++)
			this.stanza1.addAttrezzo(elencoAttrezzi[i]);
		assertFalse(this.stanza1.removeAttrezzo("procione"));
	}

	@Test
	public void testRemoveAttrezzo_Successo() {
		Attrezzo[] elencoAttrezzi = this.sequenzaAttrezzi("a1", "a2", "a3", "a4", "a5");
		for (int i = 0; i < elencoAttrezzi.length; i++)
			this.stanza1.addAttrezzo(elencoAttrezzi[i]);
		assertTrue(this.stanza1.removeAttrezzo("a3"));
	}

	//IS EMPTY
	@Test
	public void testIsEmpty_vuoto() {
		assertTrue(this.stanza1.isEmpty());
	}

	@Test
	public void testIsEmpty_nonVuoto() {
		Attrezzo[] elencoAttrezzi = this.sequenzaAttrezzi("a1", "a2", "a3", "a4", "a5");
		for (int i = 0; i < elencoAttrezzi.length; i++)
			this.stanza1.addAttrezzo(elencoAttrezzi[i]);
		assertFalse(this.stanza1.isEmpty());
	}
	
}