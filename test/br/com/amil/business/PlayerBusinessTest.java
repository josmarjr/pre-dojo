package br.com.amil.business;

import static org.junit.Assert.assertEquals;

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
	
	private Match generateMatch(){
		Match match = new Match("23/04/2013 15:34:22 - New match 1 has started");
		match.addPlayer(new Player("João"));
		match.addPlayer(new Player("Pedro"));
		match.addPlayer(new Player("Paulo"));
		match.addPlayer(new Player("Gustavo"));
		
		return match;
	}
}
