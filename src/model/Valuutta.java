package model;

import javax.persistence.*;
/**
 * @author Ammar Daham
 */
 
@Entity
@Table(name ="valuutta")
public class Valuutta {

	
	@Id
	@Column
	private String tunnus;
	
	@Column
	private String nimi;
	
	@Column
	private double vaihtokurssi;
	
	public Valuutta() {
		
	}
	
	public Valuutta(String tunnus, String nimi, double vaihtokurssi) {
		this.tunnus = tunnus;
		this.vaihtokurssi = vaihtokurssi;
		this.nimi = nimi;
	}
	
	public String getTunnus() {
	
		return this.tunnus.toUpperCase();
		
	}

	public void setTunnus(String tunnus) {
		this.tunnus = tunnus.toUpperCase();
	}

	public double getVaihtokurssi() {
		return vaihtokurssi;
	}

	public void setVaihtokurssi(double vaihtokurssi) {
		this.vaihtokurssi = vaihtokurssi;
	}

	public String getNimi() {
		return  this.nimi;	
	}

	public void setNimi(String nimi) {
		this.nimi = nimi;
	}	
	
}
