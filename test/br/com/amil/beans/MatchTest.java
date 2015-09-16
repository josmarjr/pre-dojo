package br.com.amil.beans;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class MatchTest {

	@Test
	public void testGetPlayerByNameSuccess() {
		Match match = generateMatch();
		assertNotNull(match.getPlayerByName("João"));
	}
	
	@Test
	public void testGetPlayerByNameNull() {
		Match match = generateMatch();
		assertNull(match.getPlayerByName("Joaquim"));
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
