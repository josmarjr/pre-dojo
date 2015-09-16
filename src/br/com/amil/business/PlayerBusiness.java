package br.com.amil.business;

import br.com.amil.beans.Match;
import br.com.amil.beans.Player;

public class PlayerBusiness {

	public void processPlayerLog(String logRow, Match playerMatch){
		String[] playerData = logRow.split(" ");
		if (!playerData[3].equals("<WORLD>")){
			getOrCreatePlayer(playerData[3], playerMatch).addKill();//.withWeapon();
		}
		getOrCreatePlayer(playerData[5], playerMatch).addDeath();
	}
	
	private Player getOrCreatePlayer (String playerName, Match playerMatch){
		Player player = playerMatch.getPlayerByName(playerName);
		if (player == null){
			player = new Player(playerName);
			playerMatch.addPlayer(player);
		}
		return player;
	}
	
}
