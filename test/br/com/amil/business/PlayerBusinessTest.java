package br.com.amil.business;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

import br.com.amil.beans.Match;
import br.com.amil.beans.Player;

public class PlayerBusinessTest {

	private PlayerBusiness playerBusiness = new PlayerBusiness();
	
	@Test
	public void testProcessPlayerLogOldPlayer() {
		Match match = generateMatch();
		playerBusiness.processPlayerLog("23/04/2013 15:36:04 - Paulo killed Pedro using M16", match);
		
		Player playerKiller = match.getPlayerByName("Paulo");
		Player deadPlayer = match.getPlayerByName("Pedro");
		assertEquals(Integer.valueOf(1), playerKiller.getKills());
		assertEquals(Integer.valueOf(0), playerKiller.getDeaths());
		assertEquals(Integer.valueOf(0), deadPlayer.getKills());
		assertEquals(Integer.valueOf(1), deadPlayer.getDeaths());
	}
	
	@Test
	public void testProcessPlayerLogNewPlayer() {
		Match match = generateMatch();
		playerBusiness.processPlayerLog("23/04/2013 15:36:04 - Bruno killed Ezequiel using AK47", match);
		
		Player playerKiller = match.getPlayerByName("Bruno");
		Player deadPlayer = match.getPlayerByName("Ezequiel");
		assertEquals(Integer.valueOf(1), playerKiller.getKills());
		assertEquals(Integer.valueOf(0), playerKiller.getDeaths());
		assertEquals(Integer.valueOf(0), deadPlayer.getKills());
		assertEquals(Integer.valueOf(1), deadPlayer.getDeaths());
		assertEquals(Integer.valueOf(6), Integer.valueOf(match.getPlayers().size()));
	}
	
	@Test
	public void testProcessPlayerLogDeadByWorld() {
		Match match = generateMatch();
		playerBusiness.processPlayerLog("23/04/2013 15:36:33 - <WORLD> killed João by DROWN", match);
		
		Player deadPlayer = match.getPlayerByName("João");
		assertEquals(Integer.valueOf(0), deadPlayer.getKills());
		assertEquals(Integer.valueOf(1), deadPlayer.getDeaths());
	}
	
	@Test
	public void testProcessPlayerLogStreak() {
		Match match = generateMatch();
		playerBusiness.processPlayerLog("23/04/2013 15:36:04 - Bruno killed Ezequiel using AK47", match);
		playerBusiness.processPlayerLog("23/04/2013 15:36:05 - Bruno killed Ezequiel using AK47", match);
		playerBusiness.processPlayerLog("23/04/2013 15:36:06 - Bruno killed Ezequiel using AK47", match);
		playerBusiness.processPlayerLog("23/04/2013 15:36:07 - Ezequiel killed Bruno using AK47", match);
		playerBusiness.processPlayerLog("23/04/2013 15:36:08 - Bruno killed Ezequiel using AK47", match);
		playerBusiness.processPlayerLog("23/04/2013 15:36:09 - Bruno killed Ezequiel using AK47", match);
		
		Player player = match.getPlayerByName("Bruno");
		assertEquals(Integer.valueOf(3), player.getMaxStreak());
		assertEquals(Integer.valueOf(2), player.getStreak());
	}
	
	@Test
	public void testProcessPlayerLogKillDateTime() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		Match match = generateMatch();
		playerBusiness.processPlayerLog("23/04/2013 15:36:04 - Bruno killed Ezequiel using AK47", match);
		playerBusiness.processPlayerLog("23/04/2013 15:36:05 - Bruno killed Ezequiel using AK47", match);
		playerBusiness.processPlayerLog("23/04/2013 15:36:06 - Bruno killed Ezequiel using AK47", match);
		playerBusiness.processPlayerLog("23/04/2013 15:36:07 - Ezequiel killed Bruno using AK47", match);
		playerBusiness.processPlayerLog("23/04/2013 15:36:08 - Bruno killed Ezequiel using AK47", match);
		playerBusiness.processPlayerLog("23/04/2013 15:36:09 - Bruno killed Ezequiel using AK47", match);
		
		Player player = match.getPlayerByName("Bruno");
		assertEquals(5, player.getKillsTimes().size());
		try {
			assertEquals(formatter.parse("23/04/2013 15:36:05"), player.getKillsTimes().get(1));
			assertNotEquals(formatter.parse("23/04/2013 15:36:05"), player.getKillsTimes().get(3));
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void testSortPlayersByKills() {
		Match match = generateMatch();
		playerBusiness.processPlayerLog("23/04/2013 15:36:04 - Bruno killed Ezequiel using AK47", match);
		playerBusiness.processPlayerLog("23/04/2013 15:36:04 - Ezequiel killed Bruno using AK47", match);
		playerBusiness.processPlayerLog("23/04/2013 15:36:04 - Aldo killed Ezequiel using AK47", match);
		playerBusiness.processPlayerLog("23/04/2013 15:36:04 - Ezequiel killed Aldo using AK47", match);
		
		playerBusiness.sortPlayersByKills(match.getPlayers());
		assertEquals("Ezequiel", match.getPlayers().get(0).getName());
		assertEquals("Aldo", match.getPlayers().get(1).getName());
		assertEquals("Bruno", match.getPlayers().get(2).getName());
	}
	
	@Test
	public void testIsUnstoppablePlayer() {
		Match match = generateMatch();
		playerBusiness.processPlayerLog("23/04/2013 15:36:04 - Bruno killed Ezequiel using AK47", match);
		playerBusiness.processPlayerLog("23/04/2013 15:36:04 - Bruno killed Ezequiel using AK47", match);
		playerBusiness.processPlayerLog("23/04/2013 15:36:04 - Bruno killed Aldo using AK47", match);
		
		assertTrue(match.getPlayerByName("Bruno").isUnstoppablePlayer());
	}
	
	@Test
	public void testIsNotUnstoppablePlayer() {
		Match match = generateMatch();
		playerBusiness.processPlayerLog("23/04/2013 15:36:04 - Bruno killed Ezequiel using AK47", match);
		playerBusiness.processPlayerLog("23/04/2013 15:36:04 - Bruno killed Ezequiel using AK47", match);
		playerBusiness.processPlayerLog("23/04/2013 15:36:04 - Ezequiel killed Bruno using AK47", match);
		
		assertFalse(match.getPlayerByName("Bruno").isUnstoppablePlayer());
	}
	
	private Match generateMatch(){
		Match match = new Match("23/04/2013 15:34:22 - New match 1 has started");
		match.addPlayer(new Player("João"));
		match.addPlayer(new Player("Pedro"));
		match.addPlayer(new Player("Paulo"));
		match.addPlayer(new Player("Gustavo"));
		
		return match;
	}
}
