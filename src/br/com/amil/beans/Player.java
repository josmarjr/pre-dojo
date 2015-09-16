package br.com.amil.beans;

public class Player {

	private String name;
	private Integer kills = 0;
	private Integer deaths = 0;
	
	public Player (){}
	
	public Player (String name) {
		this.name = name;
	}
	
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
	
	public void addKill() {
		this.kills++;
	}
	
	public void addDeath() {
		this.deaths++;
	}
}
