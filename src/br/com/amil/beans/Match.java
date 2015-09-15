package br.com.amil.beans;

import java.util.ArrayList;
import java.util.List;

public class Match {

	private Integer matchNumber;
	private List<Player> players;

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
		this.players.add(player);
	}
}
