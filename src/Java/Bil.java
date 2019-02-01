package Java;

import java.util.ArrayList;

public class Bil {

	// Klassevariabler
	private String regnr;
	private String fabrikat;
	private String modell;
	private Motor motor;
	private Eier eier;

	// Oppretter ArrayList for Heftelse
	private ArrayList<Heftelse> heftelser = new ArrayList<>();


	// Konstruktør
	public Bil(String regnr, String fabrikat, String modell, Motor motor) {
		this.regnr = regnr;
		this.fabrikat = fabrikat;
		this.modell = modell;
		this.motor = motor;

	}

	// Legger til ny heftelse under bilen
	public void nyHeftelse(String dato, String type){
		heftelser.add(new Heftelse(dato,type));
	}



	// Lager setter og getter for Eier, samt getter for regnr og heftelser lista
	public void setEier(Eier eier) {
		this.eier = eier;
	}

	public Eier getEier(){
		return this.eier;
	}
	
	public String getRegnr(){
		return this.regnr;
	}

	public ArrayList<Heftelse> getHeftelser() {
		return heftelser;
	}

	// Lager en toString for Bil
	@Override
	public String toString() {
		return "Bil: [Regnr = " + regnr + ", Fabrikat = " + fabrikat + ", Modell = " + modell + ", [Motor: " + motor;
	}
	
	
	
}
