package br.com.amil.beans;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Match {

	private Integer matchNumber;
	private List<Player> players;

	public Match(){}
	
	public Match(String logRow){
		String[] matchArray = logRow.split(" ");
		this.matchNumber = Integer.parseInt(matchArray[5]);
	}
	public Integer getNameMatch() {
		return matchNumber;
	}

	public void setNameMatch(Integer nameMatch) {
		this.matchNumber = nameMatch;
	}

	public List<Player> getPlayers() {
		if (players == null)
			players = new ArrayList<Player>();
		return players;
	}

	public void addPlayer(Player player) {
		getPlayers().add(player);
	}
	
	public Player getPlayerByName (String name){
		Iterator<Player> players = getPlayers().iterator();
		while (players.hasNext()){
			Player oldPlayer = players.next();
			if (oldPlayer.getName().equals(name))
				return oldPlayer;
		}
		return null;
	}
}
