package Java;

public class Heftelse {

	// Klassevariabler
	private String dato;
	private String type;

	// Konstruktør
	public Heftelse(String dato, String type) {
		this.dato = dato;
		this.type = type;
	}

	// Lager toString for Heftelse
	@Override
	public String toString() {
		return "Heftelse: Dato = " + dato + ", Type = " + type;
	}
	
}
