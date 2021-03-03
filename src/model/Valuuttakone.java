package model;

import java.util.ArrayList;

/**
 * @author Ammar Daham
 */

public class Valuuttakone implements IValuuttakone {
	
	private ArrayList<Valuutta> valuutat = new ArrayList<>();
	
	private ValuuttaAccessObject valuuttaDAO;
	
	public Valuuttakone() {
		valuuttaDAO = new ValuuttaAccessObject();
		Valuutta[] valuuttaArr = valuuttaDAO.readValuutat();
		for(Valuutta v : valuuttaArr) {
			valuutat.add(v);
		}
	}
	
	

	
	public String[] getVaihtoehdot() {
		String[] valuuttataulu = new String[valuutat.size()];
		for(int i = 0; i < valuutat.size(); i++) {
			valuuttataulu[i] = valuutat.get(i).getNimi();
		}
		return valuuttataulu;
	}


	public double muunna(int mistaIndeksi, int mihinIndeksi, double maara) {
		double tulos = maara/(valuutat.get(mistaIndeksi).getVaihtokurssi());
		
		tulos *= valuutat.get(mihinIndeksi).getVaihtokurssi();
		
		return tulos;
	}


	

}
