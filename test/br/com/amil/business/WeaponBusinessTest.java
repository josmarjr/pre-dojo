package br.com.amil.business;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.amil.beans.Player;
import br.com.amil.beans.Weapon;

public class WeaponBusinessTest {
	
	private WeaponBusiness weaponBusiness = new WeaponBusiness();

	@Test
	public void testGetOrCreateWeaponWithRegisteredWeapon() {
		Weapon weapon = new Weapon("AK47");
		weapon.setNumberOfKills(2);
		Player player = new Player("José");
		player.addWeapon(weapon);
		
		assertSame(weapon, weaponBusiness.getOrCreateWeapon("AK47", player));
		assertEquals(1, player.getWeapons().size());
	}
	
	@Test
	public void testGetOrCreateWeaponWithNewWeapon() {
		Weapon weapon = new Weapon("AK47");
		weapon.setNumberOfKills(2);
		Player player = new Player("José");
		player.addWeapon(weapon);
		
		assertNotNull(weaponBusiness.getOrCreateWeapon("Pistol", player));
		assertNotSame(weapon, weaponBusiness.getOrCreateWeapon("Pistol", player));
		assertEquals(2, player.getWeapons().size());
	}

}
