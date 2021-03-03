package model;
import java.sql.*;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * @author Ammar Daham
 */

public class ValuuttaAccessObject implements IValuuttaDAO {
	
	private Valuutta valuutta;
	private Valuutta[] valuutat;
	private SessionFactory istuntotehdas = null;
	private Transaction transaktio = null;

	public ValuuttaAccessObject() {
		try {
			istuntotehdas = new Configuration().configure().buildSessionFactory();
		}catch(Exception e) {
			System.err.println("Istuntotehtaan luonti ei onnistunut: " + e.getMessage());
			System.exit(-1);
		}
	}

	@Override
	public boolean createValuutta(Valuutta valuutta) {
		 transaktio = null;
		try (Session istunto = istuntotehdas.openSession()) {
			if (valuutta != null) {
				transaktio = istunto.beginTransaction();
				istunto.saveOrUpdate(valuutta);
				transaktio.commit();
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			if (transaktio != null)
				transaktio.rollback();
			System.err.println("Virhe!");
		}

		return false;
	}

	@Override
	public Valuutta readValuutta(String tunnus) {
		transaktio = null;
		try(Session istunto = istuntotehdas.openSession()){
			transaktio = istunto.beginTransaction();
			valuutta = new Valuutta();
			istunto.load(valuutta, tunnus);
			istunto.getTransaction().commit();
		}catch(Exception e) {
			System.err.println("Virhe!");
		}
		return valuutta;
	}

	@Override
	public Valuutta[] readValuutat() {
		int index = 0;
		try (Session istunto = istuntotehdas.openSession()) {
			transaktio = istunto.beginTransaction();
			List<Valuutta> valuuttaLista = istunto.createQuery("from Valuutta").getResultList();
			valuutat = new Valuutta[valuuttaLista.size()];
			
			for (Valuutta v : valuuttaLista) {
				valuutat[index] = v;
				index++;
			}
		}catch(Exception e) {
			System.err.println("Virhe!");
		}
		return valuutat;
	}

	@Override
	public boolean updateValuutta(Valuutta valuutta) {
		try (Session istunto = istuntotehdas.openSession()) {

			String nimi = valuutta.getNimi();
			double kurssi = valuutta.getVaihtokurssi();

			istunto.beginTransaction();

			valuutta = (Valuutta)istunto.get(Valuutta.class, valuutta.getTunnus());

			if (valuutta != null) {
				valuutta.setNimi(nimi);
				valuutta.setVaihtokurssi(kurssi);
			} else {
				System.out.println("Päivitys epäonnistui.");
				return false;
			}

			istunto.getTransaction().commit();

			return true;

		} catch (Exception e) {
			System.err.println("Virhe!");
			return false;
		}
	}

	@Override
	public boolean deleteValuutta(String tunnus) {
		valuutta = readValuutta(tunnus);
		
		try (Session istunto = istuntotehdas.openSession()) {
			
			istunto.beginTransaction();
			
			if (valuutta != null) {
				istunto.delete(valuutta);
			} else {
				return false;
			}

			istunto.getTransaction().commit();

			return true;

		} catch (Exception e) {
			System.err.println("Virhe!");
			return false;
		}
	}

	

}
