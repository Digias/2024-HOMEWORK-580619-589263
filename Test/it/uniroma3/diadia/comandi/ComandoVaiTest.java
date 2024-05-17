package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.*;
import it.uniroma3.diadia.*;
import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.fixture.Fixture;

public class ComandoVaiTest {

	private Stanza s1;
	private Stanza s2;
	private Comando vai;
	private Partita p;
	List<String> righeDaLeggere;
	List<String> righeDaLeggere2;
	Labirinto labirinto;
	Labirinto labirinto2;
	private IO io;

	@Before
	public void setUp() throws Exception {
		io = new IOConsole();
		s1 = new Stanza("aula 1");
		s2 = new Stanza("aula 2");
		vai = new ComandoVai();
		labirinto = Labirinto.newBuilder()
				.addStanzaIniziale("Atrio")
				.addAttrezzo("martello", 3)
				.addStanzaVincente("Biblioteca")
				.addAdiacenza("Atrio", "Biblioteca", "nord")
				.getLabirinto();
		p = new Partita(labirinto);
		vai.setIo(io);
		righeDaLeggere = new ArrayList<>();
		righeDaLeggere2 = new ArrayList<>();

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testVaiNull() {
		p.setStanzaCorrente(s1);
		vai.esegui(p);
		assertEquals(s1, p.getStanzaCorrente());
	}

	@Test
	public void testVaiDirezioneEsistente() {
		p.setStanzaCorrente(s1);
		s1.impostaStanzaAdiacente("sud-ovest", s2);
		vai.setParametro("sud-ovest");
		vai.esegui(p);
		assertEquals(s2, p.getStanzaCorrente());
	}

	@Test
	public void testVaiDirezioneInesistente() {
		p.setStanzaCorrente(s1);
		s1.impostaStanzaAdiacente("sud-ovest", s2);
		vai.setParametro("in fondo a destra");
		vai.esegui(p);
		assertNotEquals(s2, p.getStanzaCorrente());
	}

	@Test
	public void testPartitaConComandoVai() {
		righeDaLeggere.add("vai nord");

		IOSimulator io = Fixture.creaSimulazionePartitaEGiocaEasy(righeDaLeggere);
		
		assertTrue(io.hasNextMessaggio());
		assertEquals(DiaDia.MESSAGGIO_BENVENUTO, io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("Biblioteca", io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("Hai vinto!", io.nextMessaggio());
		
	}
	
	
	@Test
	public void testPartitaConComandoVaiOvest() {
		righeDaLeggere2.add("vai ovest");
		righeDaLeggere2.add("fine");

		IOSimulator io = Fixture.creaSimulazionePartitaEGiocaHard(righeDaLeggere2);
		assertTrue(io.hasNextMessaggio());
		assertEquals(DiaDia.MESSAGGIO_BENVENUTO, io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("Studio", io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals(ComandoFine.MESSAGGIO_FINE, io.nextMessaggio());
	}
	
	@Test
	public void testPartitaConComandoVaiOvestEst() {
		righeDaLeggere2.add("vai ovest");
		righeDaLeggere2.add("vai est");
		righeDaLeggere2.add("fine");

		IOSimulator io = Fixture.creaSimulazionePartitaEGiocaHard(righeDaLeggere2);
		assertTrue(io.hasNextMessaggio());
		assertEquals(DiaDia.MESSAGGIO_BENVENUTO, io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("Studio", io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("Atrio", io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals(ComandoFine.MESSAGGIO_FINE, io.nextMessaggio());
	}
}


