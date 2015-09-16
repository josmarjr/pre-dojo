package br.com.amil.business;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.com.amil.beans.Match;
import br.com.amil.beans.Player;
import br.com.amil.beans.Weapon;

public class PlayerBusiness {
	
	private WeaponBusiness weaponBusiness = new WeaponBusiness();

	public void processPlayerLog(String logRow, Match playerMatch){
		String[] playerData = logRow.split(" ");
		if (!playerData[3].equals("<WORLD>")){
			getOrCreatePlayer(playerData[3], playerMatch).addKill().withWeapon(playerData[7]);
		}
		getOrCreatePlayer(playerData[5], playerMatch).addDeath();
	}
	
	public void sortPlayersByKills(List<Player> players){
		Collections.sort(players, new Comparator<Player>() {
	        
			@Override
	        public int compare(Player firstPlayer, Player secondPlayer)
	        {
				if (secondPlayer.getKills().compareTo(firstPlayer.getKills()) != 0)
					return  secondPlayer.getKills().compareTo(firstPlayer.getKills());
				if (firstPlayer.getDeaths().compareTo(secondPlayer.getDeaths()) != 0)
					return firstPlayer.getDeaths().compareTo(secondPlayer.getDeaths());
				return firstPlayer.getName().compareTo(secondPlayer.getName());
	        }
	    });
	}
	
	private PlayerHandle getOrCreatePlayer (String playerName, Match playerMatch){
		Player player = playerMatch.getPlayerByName(playerName);
		if (player == null){
			player = new Player(playerName);
			playerMatch.addPlayer(player);
		}
		return new PlayerHandle(player);
	}
	
	private class PlayerHandle{
		
		Player player;
		
		public PlayerHandle(Player player){
			this.player = player;
		}
		
		public PlayerHandle addKill(){
			this.player.setKills(this.player.getKills() + 1);
			this.player.setStreak(this.player.getStreak() +1);
			if (this.player.getStreak() > this.player.getMaxStreak())
				this.player.setMaxStreak(this.player.getStreak());
			return this;
		}
		
		public void addDeath(){
			this.player.setDeaths(this.player.getDeaths() + 1);
			this.player.setStreak(0);
		}
		
		public void withWeapon(String weaponName){
			Weapon weapon = weaponBusiness.getOrCreateWeapon(weaponName, this.player);
			weapon.setNumberOfKills(weapon.getNumberOfKills() +1);
			weaponBusiness.sortWeaponsByKills(this.player.getWeapons());
		}
	}
	
}
