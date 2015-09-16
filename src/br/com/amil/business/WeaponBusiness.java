package br.com.amil.business;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
	
	public void sortWeaponsByKills(List<Weapon> weapons){
		
		Collections.sort(weapons, new Comparator<Weapon>() {
	        
			@Override
	        public int compare(Weapon firstWeapon, Weapon secondWeapon)
	        {
				if (secondWeapon.getNumberOfKills().compareTo(firstWeapon.getNumberOfKills()) != 0)
					return  secondWeapon.getNumberOfKills().compareTo(firstWeapon.getNumberOfKills());
				return firstWeapon.getName().compareTo(secondWeapon.getName());
	        }
	    });
	}

}
