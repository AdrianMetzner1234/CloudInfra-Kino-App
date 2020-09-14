package de.wps.dot.reservierung.entität;

import static org.springframework.util.Assert.isTrue;

import javax.persistence.Embeddable;

@Embeddable
public class Sitznummer {
	private int sitzreihe;
	private int stuhlnummer;

	protected Sitznummer () {

	}

	private Sitznummer(int sitzreihe, int stuhlnummer) {
		this.sitzreihe = sitzreihe;
		this.stuhlnummer = stuhlnummer;
	}
	
	public static Sitznummer von(int sitzreihe, int stuhlnummer) {
		return new Sitznummer(sitzreihe, stuhlnummer);
	}

	public static Sitznummer von(String sitzreihe, int stuhlnummer) {
		char c = sitzreihe.charAt(0);
		int i = (int)c - (int)('A');
		return new Sitznummer(i, stuhlnummer);
	}

	public static Sitznummer von(String sitznummer) {
		isTrue(istValide(sitznummer), "Ungültige Sitznummer");
		
		return von(sitznummer.substring(0,1), Integer.parseInt(sitznummer.substring(1,1)));
	}

	public String getName() {
		String result = "" + (char)('A' + sitzreihe) + (stuhlnummer);

		return result;
	}

	public static boolean istValide(String sitznummer){
		if(sitznummer.substring(0,1).charAt(0) < 'A' || sitznummer.substring(0,1).charAt(0) > 'Z')
		{
			return false;
		}
		
		try {
			Integer.parseInt(sitznummer.substring(1, sitznummer.length()));
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + sitzreihe;
		result = prime * result + stuhlnummer;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sitznummer other = (Sitznummer) obj;
		if (sitzreihe != other.sitzreihe)
			return false;
		if (stuhlnummer != other.stuhlnummer)
			return false;
		return true;
	}
}
