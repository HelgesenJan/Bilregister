package oblig;

import java.text.Collator;
import java.util.ArrayList;

public class Eier implements Comparable<Eier> {

	// Klassevariabler
	private String personnr;
	private String navn;
	private String adresse;
	private String tlf;
	ArrayList<Bil> biler = new ArrayList<>();

    private final static Collator nyKollator = Collator.getInstance();


	// Konstruktør
	public Eier(String personnr, String navn, String adresse, String tlf) {
		this.personnr = personnr;
		this.navn = navn;
		this.adresse = adresse;
		this.tlf = tlf;
	}

	// Lager getter for personnr
	public String getPersonnr(){
		return this.personnr;
	}

	public ArrayList<Bil> getBiler(){
		return biler;
	}


	// Lager compareTo for å sammenligne personnr til sortering av eier
	public int compareTo(Eier denAndre) {
		return nyKollator.compare(this.personnr, denAndre.getPersonnr());
	}


	// Lager toString for Eier
	@Override
	public String toString() {
		return "\nEier: [Personnr = " + personnr + ", Navn = " + navn + ", Adresse = " + adresse + ", Telefon = " + tlf + "]\n";
	}
}
