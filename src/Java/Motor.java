package Java;

public class Motor {

	// Klassevariabler
	private int motornummer;
	private int slagvolum;
	private int hk;

	// Konstruktør
	public Motor(int motornummer, int slagvolum, int hk) {
		this.motornummer = motornummer;
		this.slagvolum = slagvolum;
		this.hk = hk;
	}

	// Lager en toString for Motor
	@Override
	public String toString() {
		return "Motornummer = " + motornummer + ", Slagvolum = " + slagvolum + ", Hestekrefter = " + hk + "]] \n";
	}

}
