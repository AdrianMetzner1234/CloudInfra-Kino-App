package de.wps.dot.reservierung;

import java.util.ArrayList;
import java.util.List;

public class Saal {

	private final Sitznummer[][] saalLayout;
	private final String name;
	private final String id;

	public Saal(String name, int reihenBreite, int reihen) {
		super();
		saalLayout = erzeugeSitze(reihen, reihenBreite);
		this.name = name;
		id = name.toLowerCase().replaceAll("ß", "ss").replaceAll(" ", "_");
	}

	public String getName() {
		return name;
	}

	public int getReihenBreite() {
		return saalLayout[0].length;
	}

	public int getReihen() {
		return saalLayout.length;
	}

	public String getId() {
		return id;
	}

	public Sitznummer[][] getLayout() {
		return saalLayout;
	}
	
	public List<Sitznummer> getSitze() {
		List<Sitznummer> result = new ArrayList<>();
		for (Sitznummer[] sitznummern : saalLayout) {
			//noinspection ManualArrayToCollectionCopy
			for (Sitznummer sitznummer : sitznummern) {
				//noinspection UseBulkOperation
				result.add(sitznummer);
			}
		}
			
		return result;
	}

	private Sitznummer[][] erzeugeSitze(int reihen, int reihenBreite) {
		Sitznummer[][] sitze = new Sitznummer[reihen][reihenBreite];

		for (int reihe = 0; reihe < reihen; reihe++) {
			for (int sitzNummer = 1; sitzNummer <= reihenBreite; sitzNummer++) {
				sitze[reihe][sitzNummer-1] = Sitznummer.von(reihe, sitzNummer);
			}
		}
		return sitze;
	}

}
