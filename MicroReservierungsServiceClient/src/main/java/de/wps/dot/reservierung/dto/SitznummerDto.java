package de.wps.dot.reservierung.dto;

public class SitznummerDto {
	private int sitzreihe;
	private int stuhlnummer;

	public SitznummerDto(int sitzreihe, int stuhlnummer) {
		this.sitzreihe = sitzreihe;
		this.stuhlnummer = stuhlnummer;
	}
	
	public static SitznummerDto von(int sitzreihe, int stuhlnummer) {
		return new SitznummerDto(sitzreihe, stuhlnummer);
	}
	
	public int getSitzreihe() {
		return sitzreihe;
	}
	public int getStuhlnummer() {
		return this.stuhlnummer;
	}

	public void setSitzreihe(int value) {
		sitzreihe = value;
	}

	public void setStuhlnummer(int value) {
		stuhlnummer = value;
	}

	public static SitznummerDto von(String sitzreihe, int stuhlnummer) {
		char c = sitzreihe.charAt(0);
		int i = (int)c - (int)('A');
		return new SitznummerDto(i, stuhlnummer);
	}

	public String getName() {
		String result = "" + (char)('A' + sitzreihe) + (stuhlnummer);

		return result;
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
		SitznummerDto other = (SitznummerDto) obj;
		if (sitzreihe != other.sitzreihe)
			return false;
		if (stuhlnummer != other.stuhlnummer)
			return false;
		return true;
	}
}
