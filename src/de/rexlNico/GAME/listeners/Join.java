package de.rexlNico.GAME.listeners;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.rexlNico.GAME.GameStates.GameStateHandler;
import de.rexlNico.GAME.GameStates.LobbyState;
import de.rexlNico.GAME.Methodes.Factory;
import de.rexlNico.GAME.Methodes.Var;

public class Join implements Listener{
	
	@SuppressWarnings("static-access")
	@EventHandler
	public void on(PlayerJoinEvent e){
		Player p = e.getPlayer();
		e.setJoinMessage(null);
		
		if(Var.playing.size() == LobbyState.MAX_PLAYERS){
			p.kickPlayer("§4Der Server ist voll!");
			return;
		}
		
		if(GameStateHandler.getGamestate() instanceof LobbyState){
			p.teleport(Factory.getConfigLocation("Spawn", Var.cfg));
			Var.playing.add(p);
			Var.Inno.add(p);
			
			
			
			
			LobbyState ls = (LobbyState) GameStateHandler.getGamestate();
			
			Bukkit.broadcastMessage(Var.pr+"Der Spieler §a"+p.getDisplayName()+"§7 ist dem Spiel beigetreten. §r §8[§c"+Var.playing.size()+"§8/§c"+LobbyState.MAX_PLAYERS+"§8]");
			p.setLevel(0);
			p.getInventory().setHelmet(null);
			p.getInventory().setLeggings(null);
			p.getInventory().setChestplate(null);
			p.getInventory().setBoots(null);
			p.setGameMode(GameMode.SURVIVAL);
			p.getInventory().clear();
			
			
			if(Var.playing.size() >= LobbyState.MIN_PLAYERS){
				if(ls.getCountdown().isRunning == false){
					ls.getCountdown().stopIdle();
					ls.getCountdown().start();
				}
			}
			if(Var.playing.size() < LobbyState.MIN_PLAYERS){
				if(ls.getCountdown().isIdelind == false){
					ls.getCountdown().idle();
				}
			}
			
			
		}else{
			p.kickPlayer("§8[§4ERROR§8] §cErrorID§8: §e1");
		}
	}
	
}
