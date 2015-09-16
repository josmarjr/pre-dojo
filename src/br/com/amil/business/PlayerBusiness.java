package br.com.amil.business;

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
			return this;
		}
		
		public void addDeath(){
			this.player.setDeaths(this.player.getDeaths() + 1);
		}
		
		public void withWeapon(String weaponName){
			Weapon weapon = weaponBusiness.getOrCreateWeapon(weaponName, this.player);
			weapon.setNumberOfKills(weapon.getNumberOfKills() +1);
		}
	}
	
}
