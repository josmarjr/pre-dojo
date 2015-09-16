package br.com.amil.business;

import java.util.Iterator;

import br.com.amil.beans.Match;
import br.com.amil.beans.Player;
import br.com.amil.utils.FileUtil;


public class RankingBusiness {

	MatchBusiness machBusiness = new MatchBusiness();
	
	public void generateRanking(String logPath) {
		machBusiness.createMatches(FileUtil.readFileRows(logPath));
		printMatches(machBusiness.getMatches().iterator());
	}
	
	private void printMatches (Iterator<Match> matches) {
		while(matches.hasNext()){
			Match match = matches.next();
			System.out.println("Match number ".concat(match.getNameMatch().toString()));
			printPlayers(match.getPlayers().iterator());
		}
	}
	
	private void printPlayers (Iterator<Player> players) {
		while(players.hasNext()){
			Player player = players.next();
			System.out.println(player.getName().concat("\t| Kills ").concat(player.getKills().toString()).concat("\t| Deaths ").concat(player.getDeaths().toString()));
		}
	}
}
