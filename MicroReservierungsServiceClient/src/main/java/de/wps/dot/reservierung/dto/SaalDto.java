package de.wps.dot.reservierung.dto;

public class SaalDto {

	private SitznummerDto[][] saalLayout;
	private String name;
	private String id;

	public SaalDto() { }

	public void setSaalLayout(SitznummerDto[][] saalLayout) {
		this.saalLayout = saalLayout;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getReihenBreite() {
		return saalLayout[0].length;
	}

	public int getReihen() {
		return saalLayout.length;
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}
}
