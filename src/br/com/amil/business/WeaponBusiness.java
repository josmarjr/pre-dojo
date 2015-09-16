package br.com.amil.business;

import br.com.amil.beans.Player;
import br.com.amil.beans.Weapon;

public class WeaponBusiness {
	
	public Weapon getOrCreateWeapon(String weaponName, Player player){
		Weapon weapon = player.getWeaponByName(weaponName);
		if (weapon == null){
			weapon = new Weapon(weaponName);
			player.addWeapon(weapon);
		}
		return weapon;
	}

}
