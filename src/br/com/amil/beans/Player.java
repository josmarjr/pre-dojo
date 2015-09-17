package br.com.amil.beans;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Player {

	private String name;
	private Integer kills = 0;
	private Integer deaths = 0;
	private Integer streak = 0;
	private Integer maxStreak = 0;
	private List<Weapon> weapons = new ArrayList<Weapon>();
	private List<Date> killsTimes = new ArrayList<Date>();
	
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
	
	public List<Weapon> getWeapons() {
		return weapons;
	}

	public void addWeapon(Weapon weapon) {
		this.weapons.add(weapon);
	}
	
	public Integer getStreak() {
		return streak;
	}

	public void setStreak(Integer streak) {
		this.streak = streak;
	}

	public Integer getMaxStreak() {
		return maxStreak;
	}

	public void setMaxStreak(Integer maxStreak) {
		this.maxStreak = maxStreak;
	}
	
	public boolean isUnstoppablePlayer() {
		return this.deaths == 0;
	}

	public List<Date> getKillsTimes() {
		return killsTimes;
	}

	public void addKillTime(Date killTime) {
		this.killsTimes.add(killTime);
	}
	
	
	public boolean isKillingSpree() {
		for (int i = 4 ; i < this.killsTimes.size() ; i ++){
			Calendar priorDate = Calendar.getInstance();
			priorDate.setTime(this.killsTimes.get(i-4));
			priorDate.add(Calendar.MINUTE, 1);
			if (!priorDate.getTime().before(this.killsTimes.get(i)))
				return true;
		}
		return false;
	}

	public Weapon getWeaponByName (String name){
		Iterator<Weapon> weaponsIterator = this.weapons.iterator();
		while (weaponsIterator.hasNext()){
			Weapon registeredWeapon = weaponsIterator.next();
			if (registeredWeapon.getName().equals(name))
				return registeredWeapon;
		}
		return null;
	}
}
