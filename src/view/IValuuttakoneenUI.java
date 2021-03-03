package view;
/**
 * @author silas
 */

public interface IValuuttakoneenUI {
	public abstract int getLahtoIndeksi();
	public abstract int getKohdeIndeksi();
	public abstract double getMäärä();
	public abstract void setTulos(double määrä);
}
