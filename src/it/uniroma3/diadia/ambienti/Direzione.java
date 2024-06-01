package it.uniroma3.diadia.ambienti;

public enum Direzione {
	nord, est, sud, ovest;
	
	@Override
    public String toString() {
        return name(); // Restituisce il nome della direzione come è dichiarato
    }
}
