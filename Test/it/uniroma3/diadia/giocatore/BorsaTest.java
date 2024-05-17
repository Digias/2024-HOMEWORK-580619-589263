package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class BorsaTest {
	private Borsa borsa;
	private Attrezzo osso;

	private Attrezzo[] sequenzaAttrezzi(String...attrezzo) {
		Attrezzo[] elenco = new Attrezzo[attrezzo.length];
		for(int i = 0; i < attrezzo.length; i++)
			elenco[i] = new Attrezzo(attrezzo[i], i);
		return elenco;
	}
	
	private Attrezzo[] sequenzaAttrezzi_stessoPeso(String...attrezzo) {
		Attrezzo[] elenco = new Attrezzo[attrezzo.length];
		for(int i = 0; i < attrezzo.length; i++)
			elenco[i] = new Attrezzo(attrezzo[i], 2);
		return elenco;
	}
	
	private Attrezzo[] sequenzaAttrezziContrario(String...attrezzo) {
		Attrezzo[] elenco = new Attrezzo[attrezzo.length];
		for(int i = 0; i < attrezzo.length; i++)
			elenco[i] = new Attrezzo(attrezzo[i],attrezzo.length -1 - i);
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

	//ORDINATO PER PESO
	@Test	//pesi oggetti tutti diversi
	public void testGetContenutoOrdinatoPerPeso_pesoDiverso() {
		//crea un array di oggetti con peso da lunghezza-1 a -1
		Attrezzo[] elencoAttrezzi = this.sequenzaAttrezziContrario("a1", "a2", "a3");	
		for (int i = 0; i < elencoAttrezzi.length; i++)
			this.borsa.addAttrezzo(elencoAttrezzi[i]);
		
		//lista di oggetti ordinati per peso poi per nome
		List<Attrezzo> actual = this.borsa.getContenutoOrdinatoPerPeso();
		Iterator<Attrezzo> iteratore = actual.iterator();
		
		//assert per verificare le dimensioni uguali
		assertEquals(elencoAttrezzi.length, actual.size());
		int i = 0;
		while(iteratore.hasNext()) {
			//assert per verificare che gli attrezzi siano nell'ordine giusto
			assertEquals(elencoAttrezzi[elencoAttrezzi.length - 1 - i], iteratore.next());
			i++;
		}
	}
	
	@Test	//pesi oggetti uguale quindi ordinato per nome
	public void testGetContenutoOrdinatoPerPeso_pesoUguale() {
		//crea un array di oggetti con peso da lunghezza-1 a -1
		Attrezzo[] elencoAttrezzi = this.sequenzaAttrezziContrario("a1", "a2", "a3");
		for (int i = 0; i < elencoAttrezzi.length; i++)
			this.borsa.addAttrezzo(elencoAttrezzi[i]);
		
		//peso osso uguale a 1
		this.borsa.addAttrezzo(osso);
		
		//lista di oggetti ordinati per peso poi per nome
		List<Attrezzo> actual = this.borsa.getContenutoOrdinatoPerPeso();
		
		//assert per verificare le dimensioni uguali
		assertEquals(elencoAttrezzi.length + 1, actual.size());
		
		//assert per verificare che gli attrezzi siano nell'ordine giusto
		assertEquals(elencoAttrezzi[0], actual.get(3)); //a1
		assertEquals(elencoAttrezzi[1], actual.get(1)); //a2
		assertEquals(osso, actual.get(2));				//osso
		assertEquals(elencoAttrezzi[2], actual.get(0)); //a3
	}
	
	@Test	//borsa vuota
	public void testGetContenutoOrdinatoPerPeso_borsaVuota() {	
		//lista di attrezzi ordinati per peso poi per nome
		List<Attrezzo> actual = this.borsa.getContenutoOrdinatoPerPeso();
		
		//assert per verificare le dimensioni uguali
		assertEquals(0, actual.size());
		
		//assert per verificare che gli attrezzi siano nell'ordine giusto
		assertEquals(this.borsa.getContenutoOrdinatoPerPeso(), actual);
	}
	
	@Test	//un solo attrezzo da ordinare
	public void testGetContenutoOrdinatoPerPeso_unAttrezzo() {
		this.borsa.addAttrezzo(osso);		
		
		//lista di oggetti ordinati per peso poi per nome
		List<Attrezzo> actual = this.borsa.getContenutoOrdinatoPerPeso();
		
		//assert per verificare le dimensioni uguali
		assertEquals(1, actual.size());
		
		//assert per verificare che gli attrezzi siano nell'ordine giusto
		assertEquals(osso, actual.get(0));		
	}
	
	@Test	//molti oggetti da ordinare per peso diverso
	public void testGetContenutoOrdinatoPerPeso_moltiAttrezzi() {
		//crea un array di oggetti con peso da lunghezza-1 a -1
		Attrezzo[] elencoAttrezzi = this.sequenzaAttrezziContrario("a1", "a2", "a3","a4", "a5");
		for (int i = 0; i < elencoAttrezzi.length; i++)
			this.borsa.addAttrezzo(elencoAttrezzi[i]);

		//lista di attrezzi ordinati per peso poi per nome
		List<Attrezzo> actual = this.borsa.getContenutoOrdinatoPerPeso();
		Iterator<Attrezzo> iteratore = actual.iterator();
		
		//assert per verificare le dimensioni uguali
		assertEquals(elencoAttrezzi.length, actual.size());
		int i = 0;
		while(iteratore.hasNext()) {
			//assert per verificare che gli attrezzi siano nell'ordine giusto
			assertEquals(elencoAttrezzi[elencoAttrezzi.length - 1 - i], iteratore.next());
			i++;
		}		
	}
	
	//ORDINATO PER NOME
	@Test	//oggetti con nome diverso 
	public void testGetContenutoOrdinatoPerNome_Diverso() {
		//crea un array di oggetti con peso da lunghezza-1 a -1
		Attrezzo[] elencoAttrezzi = this.sequenzaAttrezziContrario("a1", "a2", "a3");
		for (int i = 0; i < elencoAttrezzi.length; i++)
			this.borsa.addAttrezzo(elencoAttrezzi[i]);
		
		//set di attrezzi ordinati per nome e poi per peso
		SortedSet<Attrezzo> actual = this.borsa.getContenutoOrdinatoPerNome();
		Iterator<Attrezzo> iteratore = actual.iterator();
		
		//assert per verificare le dimensioni uguali
		assertEquals(elencoAttrezzi.length, actual.size());
		int i = 0;
		while(iteratore.hasNext()) {
			//assert per verificare che gli attrezzi siano nell'ordine giusto
			assertEquals(elencoAttrezzi[i], iteratore.next());
			i++;
		}
	}
	
	@Test	//oggetti con nomi uguali quindi ordinato per peso
	public void testGetContenutoOrdinatoPerNome_Uguale() {
		//crea un array di oggetti con peso da lunghezza-1 a -1
		Attrezzo[] elencoAttrezzi = this.sequenzaAttrezziContrario("a3", "a2", "a3");
		for (int i = 0; i < elencoAttrezzi.length; i++)
			this.borsa.addAttrezzo(elencoAttrezzi[i]);
		
		//set di attrezzi ordinati per nome e poi per peso
		SortedSet<Attrezzo> actual = this.borsa.getContenutoOrdinatoPerNome();
		Iterator<Attrezzo> iteratore = actual.iterator();
		
		//assert per verificare le dimensioni uguali
		assertEquals(elencoAttrezzi.length, actual.size());
		
		//assert per verificare che gli attrezzi siano nell'ordine giusto
		assertEquals(elencoAttrezzi[1], iteratore.next());
		assertEquals(elencoAttrezzi[2], iteratore.next());
		assertEquals(elencoAttrezzi[0], iteratore.next());
	}
	
	@Test	//borsa vuota
	public void testGetContenutoOrdinatoPerNome_borsaVuota() {	
		//set di attrezzi ordinati per nome e poi per peso
		SortedSet<Attrezzo> actual = this.borsa.getContenutoOrdinatoPerNome();
		
		//assert per verificare le dimensioni uguali
		assertEquals(0, actual.size());
		
		//assert per verificare che gli attrezzi siano nell'ordine giusto
		assertEquals(this.borsa.getContenutoOrdinatoPerNome(), actual);
	}
	
	@Test	//un oggetto
	public void testGetContenutoOrdinatoPerNome_unAttrezzo() {
		this.borsa.addAttrezzo(osso);	
		
		//set di attrezzi ordinati per nome e poi per peso
		SortedSet<Attrezzo> actual = this.borsa.getContenutoOrdinatoPerNome();
		Iterator<Attrezzo> iter = actual.iterator();
		
		//assert per verificare le dimensioni uguali
		assertEquals(1, actual.size());
		
		//assert per verificare che gli attrezzi siano nell'ordine giusto
		assertEquals(osso, iter.next());		
	}
	
	@Test	//ordinamento per nome di molti attrezzi
	public void testGetContenutoOrdinatoPerNome_moltiAttrezzi() {
		//crea un array di oggetti con peso da lunghezza-1 a -1
		Attrezzo[] elencoAttrezzi = this.sequenzaAttrezziContrario("a1", "a2", "a3","a4");
		for (int i = 0; i < elencoAttrezzi.length; i++)
			this.borsa.addAttrezzo(elencoAttrezzi[i]);

		//set di attrezzi ordinati per nome e poi per peso
		SortedSet<Attrezzo> actual = this.borsa.getContenutoOrdinatoPerNome();
		Iterator<Attrezzo> iteratore = actual.iterator();
		
		//assert per verificare le dimensioni uguali
		assertEquals(elencoAttrezzi.length, actual.size());
		
		int i = 0;
		while(iteratore.hasNext()) {
			//assert per verificare che gli attrezzi siano nell'ordine giusto
			assertEquals(elencoAttrezzi[i], iteratore.next());
			i++;
		}		
	}
	
	//SET ORDINATO PER PESO
	@Test	//oggetti con peso diverso
	public void testGetSortedSetOrdinatoPerPeso_Diverso() {
		//crea un array di oggetti con peso da lunghezza-1 a -1
		Attrezzo[] elencoAttrezzi = this.sequenzaAttrezziContrario("a1", "a2", "a3");
		for (int i = 0; i < elencoAttrezzi.length; i++)
			this.borsa.addAttrezzo(elencoAttrezzi[i]);
		
		//Set di oggetti ordinati per peso poi per nome
		SortedSet<Attrezzo> actual = this.borsa.getSortedSetOrdinatoPerPeso();
		Iterator<Attrezzo> iteratore = actual.iterator();
		
		//assert per verificare le dimensioni uguali
		assertEquals(elencoAttrezzi.length, actual.size());
		int i = 0;
		while(iteratore.hasNext()) {
			//assert per verificare che gli attrezzi siano nell'ordine giusto
			assertEquals(elencoAttrezzi[i], iteratore.next());
			i++;
		}
	}
	
	@Test	//oggetti con peso uguale quindi ordinati per nome
	public void testGetSortedSetOrdinatoPerPeso_Uguale() {
		//crea un array di oggetti con peso da lunghezza-1 a -1
		Attrezzo[] elencoAttrezzi = this.sequenzaAttrezziContrario("a3", "a2", "a3");
		for (int i = 0; i < elencoAttrezzi.length; i++)
			this.borsa.addAttrezzo(elencoAttrezzi[i]);
		
		//Set di oggetti ordinati per peso poi per nome
		SortedSet<Attrezzo> actual = this.borsa.getSortedSetOrdinatoPerPeso();
		Iterator<Attrezzo> iteratore = actual.iterator();
		
		//assert per verificare le dimensioni uguali
		assertEquals(elencoAttrezzi.length, actual.size());
		
		//assert per verificare che gli attrezzi siano nell'ordine giusto
		assertEquals(elencoAttrezzi[1], iteratore.next());
		assertEquals(elencoAttrezzi[2], iteratore.next());
		assertEquals(elencoAttrezzi[0], iteratore.next());
	}
	
	@Test	//borsa vuota
	public void testGetSortedSetOrdinatoPerPeso_borsaVuota() {	
		//Set di oggetti ordinati per peso poi per nome
		SortedSet<Attrezzo> actual = this.borsa.getSortedSetOrdinatoPerPeso();
		
		//assert per verificare le dimensioni uguali
		assertEquals(0, actual.size());
		
		//assert per verificare che gli attrezzi siano nell'ordine giusto
		assertEquals(this.borsa.getSortedSetOrdinatoPerPeso(), actual);
	}
	
	@Test	//un oggetto
	public void testGetSortedSetOrdinatoPerPeso_unAttrezzo() {
		this.borsa.addAttrezzo(osso);	
		
		//Set di oggetti ordinati per peso poi per nome
		SortedSet<Attrezzo> actual = this.borsa.getSortedSetOrdinatoPerPeso();
		Iterator<Attrezzo> iter = actual.iterator();
		
		//assert per verificare le dimensioni uguali
		assertEquals(1, actual.size());
		
		//assert per verificare che gli attrezzi siano nell'ordine giusto
		assertEquals(osso, iter.next());		
	}
	
	@Test	//molti oggetti ordinati per peso 
	public void testGetSortedSetOrdinatoPerPeso_moltiAttrezzi() {
		//crea un array di oggetti con peso da lunghezza-1 a -1
		Attrezzo[] elencoAttrezzi = this.sequenzaAttrezziContrario("a1", "a2", "a3","a4");
		for (int i = 0; i < elencoAttrezzi.length; i++)
			this.borsa.addAttrezzo(elencoAttrezzi[i]);

		//Set di oggetti ordinati per peso poi per nome
		SortedSet<Attrezzo> actual = this.borsa.getSortedSetOrdinatoPerPeso();
		Iterator<Attrezzo> iteratore = actual.iterator();
		
		//assert per verificare le dimensioni uguali
		assertEquals(elencoAttrezzi.length, actual.size());
		int i = 0;
		while(iteratore.hasNext()) {
			//assert per verificare che gli attrezzi siano nell'ordine giusto
			assertEquals(elencoAttrezzi[i], iteratore.next());
			i++;
		}		
	}
	
	@Test	//molti oggetti ordinati per peso 
	public void testGetSortedSetOrdinatoPerPeso_pesoUgualeMaDistinti() {
		//crea un array di oggetti con peso da lunghezza-1 a -1
		Attrezzo[] elencoAttrezzi = this.sequenzaAttrezzi_stessoPeso("a4", "a3","a2","a1");
		for (Attrezzo a : elencoAttrezzi)
			this.borsa.addAttrezzo(a);

		//Set di oggetti ordinati per peso poi per nome
		SortedSet<Attrezzo> actual = this.borsa.getSortedSetOrdinatoPerPeso();
		Iterator<Attrezzo> iteratore = actual.iterator();
		
		//assert per verificare le dimensioni uguali
		assertEquals(elencoAttrezzi.length, actual.size());
		int i = 0;
		while(iteratore.hasNext()) {
			//assert per verificare che gli attrezzi siano nell'ordine giusto
			assertEquals(elencoAttrezzi[elencoAttrezzi.length-1 - i], iteratore.next());
			i++;
		}		
	}
	
	//MAP CONTENUTO RAGGRUPPATO PER PESO
	@Test
	public void testGetContenutoRaggruppatoPerPeso_borsaVuota() {
		Map<Integer,Set<Attrezzo>> raggruppato = this.borsa.getContenutoRaggruppatoPerPeso();
		assertTrue(raggruppato.isEmpty());
	}
	
	@Test
	public void testGetContenutoRaggruppatoPerPeso_borsaUnAttrezzo() {
		this.borsa.addAttrezzo(osso);
		Map<Integer,Set<Attrezzo>> raggruppato = this.borsa.getContenutoRaggruppatoPerPeso();
		assertEquals(1, raggruppato.size());
		assertTrue(raggruppato.get(osso.getPeso()).contains(osso));
	}
	
	@Test
	public void testGetContenutoRaggruppatoPerPeso_dueAttrezziPesoDiverso() {
		Attrezzo[] elencoAttrezzi = this.sequenzaAttrezzi("a1","a2");
		for(Attrezzo a : elencoAttrezzi)
			this.borsa.addAttrezzo(a);
		
		Map<Integer,Set<Attrezzo>> raggruppato = this.borsa.getContenutoRaggruppatoPerPeso();
		assertEquals(elencoAttrezzi.length, raggruppato.size());
	}
	
	@Test
	public void testGetContenutoRaggruppatoPerPeso_dueAttrezziPesoUguale() {
		Attrezzo[] elencoAttrezzi = this.sequenzaAttrezzi_stessoPeso("a1", "a2", "a3");
		for(Attrezzo a : elencoAttrezzi)
			this.borsa.addAttrezzo(a);
		
		Map<Integer,Set<Attrezzo>> raggruppato = this.borsa.getContenutoRaggruppatoPerPeso();
		assertEquals(1, raggruppato.size());
	}
	
	@Test
	public void testGetContenutoRaggruppatoPerPeso_moltiAttrezzi() {
		Attrezzo[] elencoAttrezzi = this.sequenzaAttrezzi("a1", "a2", "a3", "a4");
		for(Attrezzo a : elencoAttrezzi)
			this.borsa.addAttrezzo(a);
		
		this.borsa.addAttrezzo(osso);
		
		Map<Integer,Set<Attrezzo>> raggruppato = this.borsa.getContenutoRaggruppatoPerPeso();
		assertEquals(4, raggruppato.size());
	}
	
}
