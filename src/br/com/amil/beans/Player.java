package br.com.amil.beans;

public class Player {

	private String name;
	private Integer kills;
	private Integer deaths;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getKills() {
		return kills;
	}
	public void setKills(Integer kills) {
		this.kills = kills;
	}
	public Integer getDeaths() {
		return deaths;
	}
	public void setDeaths(Integer deaths) {
		this.deaths = deaths;
	}
}
