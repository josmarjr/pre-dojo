package br.com.amil.beans;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

public class PlayerTest {

	@Test
	public void testIsKillingSpree() {
		assertTrue(generatePlayer().isKillingSpree());
	}
	
	@Test
	public void testIsNotKillingSpree() {
		Player player = generatePlayer();
		player.getKillsTimes().remove(5);
		assertFalse(player.isKillingSpree());
	}
	
	@Test
	public void testGetWeaponByNameSuccess() {
		assertNotNull(generatePlayer().getWeaponByName("AK47"));
	}
	
	@Test
	public void testGetWeaponByNameNotSuccess() {
		assertNull(generatePlayer().getWeaponByName("M16"));
	}

	private Player generatePlayer() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		Player player = new Player("João");
		player.setMaxStreak(3);
		player.setStreak(1);
		player.setKills(8);
		player.setDeaths(2);
		try {
			player.addKillTime(formatter.parse("23/04/2013 15:36:04"));
			player.addKillTime(formatter.parse("23/04/2013 15:37:04"));
			player.addKillTime(formatter.parse("23/04/2013 15:37:10"));
			player.addKillTime(formatter.parse("23/04/2013 15:37:15"));
			player.addKillTime(formatter.parse("23/04/2013 15:37:40"));
			player.addKillTime(formatter.parse("23/04/2013 15:38:04"));
			player.addKillTime(formatter.parse("23/04/2013 15:42:04"));
			player.addKillTime(formatter.parse("23/04/2013 15:43:04"));
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		player.addWeapon(new Weapon("AK47"));
		return player;
		
	}
}
