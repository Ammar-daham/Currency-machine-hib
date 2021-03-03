 package controller;

import model.IValuuttakone;
import model.Valuuttakone;
import view.IValuuttakoneenUI;
import view.ValuuttakoneenGUI;

public class ValuuttaKoneenOhjain implements IValuuttakoneenOhjain {
	
	ValuuttakoneenGUI gui;
	Valuuttakone valuuttakone;
	
	public ValuuttaKoneenOhjain(Valuuttakone valuuttakone, ValuuttakoneenGUI gui ) {
		// TODO Auto-generated constructor stub
		this.gui = gui;
		this.valuuttakone = valuuttakone;
	}

	@Override
	public void muunnos() {
		// TODO Auto-generated method stub
		int lahtoIndeksi = gui.getLahtoIndeksi();
		int kohdeIndeksi = gui.getKohdeIndeksi();
		double maara = gui.getMäärä();
		double muunnettu = valuuttakone.muunna(lahtoIndeksi, kohdeIndeksi, maara);

		gui.setTulos(muunnettu);
	}

	@Override
	public String[] getValuutat() {
		String[] valuutat = valuuttakone.getVaihtoehdot();
		return valuutat;
	}

}
