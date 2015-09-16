package br.com.amil.business;

import java.util.Iterator;

import br.com.amil.beans.Match;
import br.com.amil.beans.Player;
import br.com.amil.utils.FileUtil;


public class RankingBusiness {

	MatchBusiness matchBusiness = new MatchBusiness();
	
	StringBuilder sb;
	
	public void generateRanking(String logPath) {
		matchBusiness.createMatches(FileUtil.readFileRows(logPath));
		printMatches(matchBusiness.getMatches().iterator());
	}
	
	private void printMatches (Iterator<Match> matches) {
		while(matches.hasNext()){
			Match match = matches.next();
			sb = new StringBuilder();
			System.out.println(sb.append("Match number ").append(match.getNameMatch()).toString());
			printPlayers(match.getPlayers().iterator());
			printWinnerWeapon(match);
		}
	}

	private void printWinnerWeapon(Match match) {
		Player winner = match.getPlayers().get(0);
		sb = new StringBuilder();
		System.out.println(sb.append("Favorite weapon of ").append(winner.getName()).append(": ").append(winner.getWeapons().get(0).getName()).toString());
	}
	
	private void printPlayers (Iterator<Player> players) {
		while(players.hasNext()){
			Player player = players.next();
			StringBuilder playerScore = new StringBuilder();
			playerScore.append(player.getName()).append("\t| Kills ").append(player.getKills()).append("\t| Deaths ").append(player.getDeaths()).append("\t| Max streak ").append(player.getMaxStreak());
			if (player.isUnstoppablePlayer())
				playerScore.append("\t UNSTOPPABLE");
			System.out.println(playerScore.toString());
		}
	}
}
