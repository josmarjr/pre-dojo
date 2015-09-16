package br.com.amil.beans;

public class Weapon {

	private String name;
	private Integer numberOfKills = 0;
	
	public Weapon(){}
	
	public Weapon (String name){
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getNumberOfKills() {
		return numberOfKills;
	}
	public void setNumberOfKills(Integer numberOfKills) {
		this.numberOfKills = numberOfKills;
	}
}
