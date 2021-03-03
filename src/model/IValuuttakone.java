package model;

public interface IValuuttakone {

	public abstract String[] getVaihtoehdot();

	public abstract double muunna(int mistaIndeksi, int mihinIndeksi, double maara);

}
