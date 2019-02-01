package Java;

import java.util.*;
import java.lang.StringBuilder;

public class Bilregister{

	// Oppretter arraylister
	ArrayList<Bil> biler = new ArrayList<>();
	ArrayList<Eier> eiere = new ArrayList<>();


	// Lager konstruktør for Bilregister slik at jeg slipper å skrive inn for hver gang
	public Bilregister() {

	    // Lager biler og eiere
		lagBil("JV43432", "Volvo", "240", 5, 50, 150);
		lagBil("KV43432", "Volvo", "240", 5, 50, 150);
		lagBil("LV43432", "Volvo", "240", 5, 50, 150);
		lagEier("38016893341", "Nils Olafsen", "Osloveien 1", "98765431");
		lagEier("28016893342", "Nils Helgesen", "Osloveien 2", "98765432");
		lagEier("18016893343", "Nils Skare", "Osloveien 3", "98765433");

		// Setter eiere til biler
		biler.get(0).setEier(eiere.get(1));
		biler.get(1).setEier(eiere.get(0));
		biler.get(2).setEier(eiere.get(2));

	}


	// Lag ny eier(registrering)
	public void lagEier(String personnr, String navn, String adresse, String tlf) {
		this.eiere.add(new Eier(personnr, navn, adresse, tlf));
	}


	// Lag ny bil(registrering)
	public Bil lagBil(String regnr, String fabrikat, String modell, int motornummer, int slagvolum, int hk) {
		Bil bilen = new Bil(regnr, fabrikat, modell, new Motor(motornummer, slagvolum,hk));
		this.biler.add(bilen);
		return bilen;
	}



	// Finner bil ved å sammenligne oppgitt regnr med regnr i lista
	public Bil finnBil(String regnr){
		Bil resultat = null;
		for(int i = 0; i < biler.size(); i++){
			Bil bil = biler.get(i);
			if(bil.getRegnr().equals(regnr.toUpperCase())){
				resultat = bil;
			}
		}
		return resultat;
	}



	// Sletter bil ved å sammenligne oppgitt regnr med regnr i lista
	public Boolean slettBil(String regnr){
		Iterator<Bil> iter = biler.iterator();
		Boolean slettet = false;
		while (iter.hasNext()) {
			Bil bil = iter.next();
			if (bil.getRegnr().equals(regnr.toUpperCase())) {
				iter.remove();
				slettet = true;
			}
		}
		return slettet;
	}


	// Finner eier ved å sammenligne oppgitt personnr med personnr i lista
	public Eier finnEier(String personnr){
		Eier resultat = null;
		for(int i = 0; i < eiere.size(); i++){
			Eier eier = eiere.get(i);

			if(eier.getPersonnr().equals(personnr)){
				resultat = eier;
			}
		}
		return resultat;
	}


	// Alle eiere sortert på personnummer
    public String eiereSortert() {
		StringBuilder sb = new StringBuilder();
        Collections.sort(eiere);
		for (Eier enEier : eiere) {
                sb.append(enEier.toString());
		}
		return sb.toString();
	}


    // Finner alle eiere og bilene de eier
	public String alleBilerTilEiere(){
		StringBuilder sb = new StringBuilder();
		for(Eier eier: eiere){
			sb.append(eier.toString() + "Er eier av disse bilene: \n");
			for (Bil bil: biler){
				if(bil.getEier() != null){
					if(bil.getEier().equals(eier)) {
						sb.append(bil.toString());
					}
				}
			}
		}
		return sb.toString();
	}



}
