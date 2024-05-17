package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccataTest {
	private String[] elencoDirezioni = {"nord", "sud", "ovest", "est"};
	private StanzaBloccata stanza1;
	private Stanza stanza2;
	String[] direzioni = {"nord", "est", "ovest", "sud"};

	private Attrezzo[] sequenzaAttrezzi(String...attrezzo) {
		Attrezzo[] elenco = new Attrezzo[attrezzo.length];
		for(int i = 0; i < attrezzo.length; i++)
			elenco[i] = new Attrezzo(attrezzo[i],i);

		return elenco;
	}
	
	private Stanza[] sequenzaStanze(String... stanza) {
		Stanza[] elencoStanze = new Stanza[stanza.length];
		for(int i = 0; i < stanza.length; i++)
			elencoStanze[i] = new Stanza(stanza[i]) ;

		return elencoStanze;
	}

	@Before
	public void setUpStanza() {
		stanza1 = new StanzaBloccata("Stanza1", "nord","chiave");
		stanza2 = new Stanza("Stanza1");
	}

	@Before
	public void setUpAttrezzi() {		
		new Attrezzo("spada", 5);
	}

	@Test
	public void testGetStanzaAdiacente_nonBloccata() {
		Attrezzo[] elencoAttrezzi = sequenzaAttrezzi("lanterna", "spada", "chiave");
		Stanza[] elencoStanze = sequenzaStanze("StanzaBloccata", "Stanza2", "Stanza3");
		for(int i = 0; i < elencoAttrezzi.length; i++) {
			stanza1.addAttrezzo(elencoAttrezzi[i]);
		}
		
		for(int i = 0; i < elencoStanze.length; i++) {
			stanza1.impostaStanzaAdiacente(elencoDirezioni[i], elencoStanze[i]);
		}
		
		assertEquals(elencoStanze[0],stanza1.getStanzaAdiacente("nord"));
	}
	
	@Test
	public void testGetStanzaAdiacente_bloccata() {
		Attrezzo[] elencoAttrezzi = sequenzaAttrezzi("lanterna", "spada", "osso");
		Stanza[] elencoStanze = sequenzaStanze("StanzaBloccata", "Stanza2", "Stanza3");
		for(int i = 0; i < elencoAttrezzi.length; i++) {
			stanza1.addAttrezzo(elencoAttrezzi[i]);
		}
		
		for(int i = 0; i < elencoStanze.length; i++) {
			stanza1.impostaStanzaAdiacente(elencoDirezioni[i], elencoStanze[i]);
		}
		
		assertEquals(stanza1, stanza1.getStanzaAdiacente("nord"));
	}

	@Test
	public void testGetDescrizione_nonBloccata() {
		Attrezzo[] elencoAttrezzi = sequenzaAttrezzi("lanterna", "spada", "chiave");
		Stanza[] elencoStanze = sequenzaStanze("StanzaBloccata", "Stanza2", "Stanza3");
		StanzaBloccata stanzaNull = new StanzaBloccata("Stanza1", "nord", "chiave");
		
		for(int i = 0; i < elencoAttrezzi.length; i++) {
			stanzaNull.addAttrezzo(elencoAttrezzi[i]);
			stanza2.addAttrezzo(elencoAttrezzi[i]);
		}
		
		for(int i = 0; i < elencoStanze.length; i++) {
			stanzaNull.impostaStanzaAdiacente(elencoDirezioni[i], elencoStanze[i]);
			stanza2.impostaStanzaAdiacente(elencoDirezioni[i], elencoStanze[i]);
		}
		
		assertEquals(stanza2.getDescrizione(),stanzaNull.getDescrizione());
	}
	
	@Test
	public void testGetDescrizione_keyNull() {
		Attrezzo[] elencoAttrezzi = sequenzaAttrezzi("lanterna", "spada");
		Stanza[] elencoStanze = sequenzaStanze("StanzaBloccata", "Stanza2", "Stanza3");
		StanzaBloccata stanzaNull = new StanzaBloccata("Stanza1", "nord", null);
		
		for(int i = 0; i < elencoAttrezzi.length; i++) {
			stanzaNull.addAttrezzo(elencoAttrezzi[i]);
			stanza2.addAttrezzo(elencoAttrezzi[i]);
		}
		
		for(int i = 0; i < elencoStanze.length; i++) {
			stanzaNull.impostaStanzaAdiacente(elencoDirezioni[i], elencoStanze[i]);
			stanza2.impostaStanzaAdiacente(elencoDirezioni[i], elencoStanze[i]);
		}
		
		assertEquals(stanza2.getDescrizione(),stanzaNull.getDescrizione());
	}
	
	@Test
	public void testGetDescrizione_directionNull() {
		Attrezzo[] elencoAttrezzi = sequenzaAttrezzi("lanterna", "spada");
		Stanza[] elencoStanze = sequenzaStanze("StanzaBloccata", "Stanza2", "Stanza3");
		
		StanzaBloccata stanzaNull = new StanzaBloccata("Stanza1", null, "chiave");
		for(int i = 0; i < elencoAttrezzi.length; i++) {
			stanzaNull.addAttrezzo(elencoAttrezzi[i]);
			stanza2.addAttrezzo(elencoAttrezzi[i]);
		}
		
		for(int i = 0; i < elencoStanze.length; i++) {
			stanzaNull.impostaStanzaAdiacente(elencoDirezioni[i], elencoStanze[i]);
			stanza2.impostaStanzaAdiacente(elencoDirezioni[i], elencoStanze[i]);
		}
		
		assertEquals(stanza2.getDescrizione(),stanzaNull.getDescrizione());
	}
}
