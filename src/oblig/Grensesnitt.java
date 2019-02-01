package oblig;

import javax.swing.*;

public class Grensesnitt {

	// Lager stringutskrifter for hver case
	final private String[] HOVEDMENY = {"Bil","Eier","Avslutt"};
	final private String[] BILMENY = {"Ny bil","Finn bil","Slett bil","Registrer heftelse","Tilbake"};
	final private String[] EIERMENY = {"Ny eier","Finn eier","Alle eiere sortert på personnr", "Alle eiere og bilene de eier","Tilbake"};

	private Bilregister reg = new Bilregister();

	// Lager switcher for GUI og looper dem

	// Hovedmeny
	public void meny() {
		boolean fortsett = true;
		while(fortsett) {
			int valg = JOptionPane.showOptionDialog(null, "Gjør et valg", "Bilregister", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, HOVEDMENY, HOVEDMENY[2]);
			switch(valg) {
				case 0: bil();
					break;
				case 1: eier();
					break;
				case 2: fortsett = false;
			} 	
		}
	}
	
	// Bilmeny
	private void bil(){
		boolean fortsett = true;
		while(fortsett) {
			int valg = JOptionPane.showOptionDialog(null, null, "Bilregister - Bil", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, BILMENY, BILMENY[4]);
			switch(valg) {
				case 0: nyBilGui();
					break;
				case 1: finnBilGui();
					break;
				case 2: slettBilGui();
					break;
				case 3: nyHeftelseGui();
					break;
				case 4: fortsett = false;
			} 	
		}
	}
	
	// Eiermeny
	private void eier(){
		boolean fortsett = true;
		while(fortsett) {
			int valg = JOptionPane.showOptionDialog(null, null, "Bilregister - Eier", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, EIERMENY, EIERMENY[4]);
			switch(valg) {
				case 0: nyEierGui();
					break;
				case 1: finnEierGui();
					break;
				case 2: eiereSortertGUI();
					break;
				case 3:	alleBilerTilEiereGUI();
					break;
				case 4: fortsett = false;
			} 	
		}
	}

	// GUI metode for å lage ny bil
	private void nyBilGui(){

		// Lager textfields
		JTextField regnr = new JTextField(10);
		JTextField fabrikat = new JTextField(10);
		JTextField modell = new JTextField(10);
		JTextField motornummer = new JTextField(10);
		JTextField slagvolum = new JTextField(10);
		JTextField hk = new JTextField(10);

		// Oppretter panelet og fyller det med labels og verdier som skal legges til
		JPanel nyBilGuiPanel = new JPanel();
		nyBilGuiPanel.add(new JLabel("Registreringsnummer: "));
		nyBilGuiPanel.add(regnr);
		nyBilGuiPanel.add(new JLabel("Fabrikat : "));
		nyBilGuiPanel.add(fabrikat);
		nyBilGuiPanel.add(new JLabel("Modell : "));
		nyBilGuiPanel.add(modell);
		nyBilGuiPanel.add(new JLabel("Motornummer : "));
		nyBilGuiPanel.add(motornummer);
		nyBilGuiPanel.add(new JLabel("Slagvolum : "));
		nyBilGuiPanel.add(slagvolum);
		nyBilGuiPanel.add(new JLabel("Hestekrefter : "));
		nyBilGuiPanel.add(hk);


		int resultat = JOptionPane.showConfirmDialog(null, nyBilGuiPanel, "Informasjon: ", JOptionPane.OK_CANCEL_OPTION);
		if (resultat == JOptionPane.OK_OPTION) {

			// Parser verdiene for Motor
			int motornr = Integer.parseInt(motornummer.getText());
			int slagvl = Integer.parseInt(slagvolum.getText());
			int hkk = Integer.parseInt(hk.getText());

			Bil bilen = reg.lagBil(regnr.getText().toUpperCase(), fabrikat.getText(), modell.getText(), motornr, slagvl, hkk);


			boolean fortsett = true;

			while (fortsett){
				String personnr = JOptionPane.showInputDialog(null,"Hvem eier denne bilen?");
				Eier pers = reg.finnEier(personnr);
				if(pers == null){
					JOptionPane.showMessageDialog(null, "Fant ikke eier");
				}else{
					fortsett = false;
					bilen.setEier(pers);
					JOptionPane.showMessageDialog(null,"Bilen ble registrert og knyttet til: " + pers.toString());
				}
			}
		}
	}

	//GUI metode for å finne bil
	private void finnBilGui(){
		String regnr = JOptionPane.showInputDialog(null,"Registreringsnummer");
		Bil bilen = reg.finnBil(regnr);
		if(bilen == null){
			JOptionPane.showMessageDialog(null, "Fant ikke bil");
		}else{
			JOptionPane.showMessageDialog(null, "Fant bilen: \n" + bilen.toString());
		}
	}



	// GUI metode for ny heftelse på oppgitt bil
	private void nyHeftelseGui() {

		// Lager textfields
		JTextField regnr = new JTextField(10);
		JTextField dato = new JTextField(10);
		JTextField type = new JTextField(10);

		// Oppretter panelet og fyller det med labels og verdier
		JPanel nyBilGuiPanel = new JPanel();
		nyBilGuiPanel.add(new JLabel("Regnr: "));
		nyBilGuiPanel.add(regnr);
		nyBilGuiPanel.add(new JLabel("Dato: "));
		nyBilGuiPanel.add(dato);
		nyBilGuiPanel.add(new JLabel("Type : "));
		nyBilGuiPanel.add(type);

		int resultat = JOptionPane.showConfirmDialog(null, nyBilGuiPanel, "Informasjon: ", JOptionPane.OK_CANCEL_OPTION);
		if (resultat == JOptionPane.OK_OPTION) {
			Bil bilen = reg.finnBil(regnr.getText());

			if(bilen == null){
				JOptionPane.showMessageDialog(null,"fant ikke bil");
			}else{
				bilen.nyHeftelse(dato.getText(),type.getText());
				JOptionPane.showMessageDialog(null,"Heftelsen " + bilen.getHeftelser() + ", \ner blitt registrert på bilen: \n" + bilen);
			}
		}
	}



	// GUI metode for å slette en angitt bil
	private void slettBilGui(){
		String regnr = JOptionPane.showInputDialog(null,"Registreringsnummer");
		Bil bilen = reg.finnBil(regnr);
		if(reg.slettBil(regnr)){
			JOptionPane.showMessageDialog(null, "Bil slettet: \n" + bilen);
		}else{
			JOptionPane.showMessageDialog(null, "Fant ikke bil");
		}
	}

	// GUI metode for å lage ny eier
	private void nyEierGui(){

		// Lager textfields
		JTextField personnr = new JTextField(10);
		JTextField navn = new JTextField(10);
		JTextField adresse = new JTextField(10);
		JTextField tlf = new JTextField(10);

		// Oppretter panelet og fyller det med labels og verdier
		JPanel nyEierGuiPanel = new JPanel();
		nyEierGuiPanel.add(new JLabel("Personnummer: "));
		nyEierGuiPanel.add(personnr);
		nyEierGuiPanel.add(new JLabel("Navn: "));
		nyEierGuiPanel.add(navn);
		nyEierGuiPanel.add(new JLabel("Adresse: "));
		nyEierGuiPanel.add(adresse);
		nyEierGuiPanel.add(new JLabel("Telefon: "));
		nyEierGuiPanel.add(tlf);

		int resultat = JOptionPane.showConfirmDialog(null, nyEierGuiPanel, "Informasjon: ", JOptionPane.OK_CANCEL_OPTION);
		if (resultat == JOptionPane.OK_OPTION) {
			reg.lagEier(personnr.getText(), navn.getText(), adresse.getText(), tlf.getText());
			//JOptionPane.showMessageDialog(null, reg.getEiere().toString());

			// Satt inputene i en string og printet den ut istedenfor å bare printe ut alle eiere. Da får bruker vite hva som nettopp ble registrert
			String str = "Eier ble registrert: \nEier: [Personnr = " + personnr.getText() + ", Navn = " + navn.getText() + ", Adresse = " + adresse.getText() + ", Telefon = " + tlf.getText() + "]";
			JOptionPane.showMessageDialog(null, str);

		}
	}


	// GUI metode for å finne eier
	private void finnEierGui(){
		String personnr = JOptionPane.showInputDialog(null,"Personnummer: ");
		Eier eieren = reg.finnEier(personnr);
		if(eieren == null){
			JOptionPane.showMessageDialog(null, "Fant ikke eier");
		}else{
			JOptionPane.showMessageDialog(null, "Fant eier: " + eieren.toString());
		}
	}

	// GUI metode for å liste ut alle eiere sortert
	private void eiereSortertGUI(){
		JOptionPane.showMessageDialog(null, reg.eiereSortert());
	}

	// GUI metode for å se alle eiere og bilene de eier
	private void alleBilerTilEiereGUI(){
		JOptionPane.showMessageDialog(null, reg.alleBilerTilEiere());
	}
}