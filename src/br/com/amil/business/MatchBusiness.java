package br.com.amil.business;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.com.amil.beans.Match;

public class MatchBusiness {
	
	private PlayerBusiness playerBusiness = new PlayerBusiness();
	
	private List<Match> matches = new ArrayList<Match>();

	public void createMatches(List<String> fileRows) {
		Iterator<String> itFileRows = fileRows.iterator(); 
		while (itFileRows.hasNext()){
			String row = itFileRows.next();
			if (row.toLowerCase().contains("started")){
				matches.add(new Match(row));
				processMatchLog(itFileRows);
			}
		}
	}
	
	private void processMatchLog(Iterator<String> matchRows){
		Match activeMatch = matches.get(matches.size() - 1);
		String row;
		while (((row = matchRows.next()).split(" ")).length > 7){
			playerBusiness.processPlayerLog(row, activeMatch);
		}
	}
	
	public List<Match> getMatches (){
		return this.matches;
	}
}
