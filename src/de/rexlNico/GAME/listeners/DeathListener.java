package de.rexlNico.GAME.listeners;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerKickEvent;

import de.rexlNico.GAME.Commands.shop;
import de.rexlNico.GAME.Methodes.Factory;
import de.rexlNico.GAME.Methodes.Var;

public class DeathListener implements Listener{
	
	@EventHandler
	public void on(PlayerDeathEvent e){
		Player p = e.getEntity();
		e.setDeathMessage(null);
		p.sendMessage("§cDu wurdest getötet");
		p.spigot().respawn();
		if(Var.Traitor.contains(p)){
			Var.Traitor.remove(p);
		}
		Var.playing.remove(p);
		Var.spec.add(p);
		p.setGameMode(GameMode.SPECTATOR);
	}
	
	@EventHandler
	public void on(EntityDamageByEntityEvent e){
		Player ph = (Player) e.getDamager();
		Player phd = (Player) e.getEntity();
		
		if(Var.pvp == false){
			e.setCancelled(true);
		}else{
			if(Var.spec.contains(ph)){
				e.setCancelled(true);
			}else{
				if(phd.isDead()){
					if(Var.Traitor.contains(phd)){
						shop.updateCoins(phd, 2);
					}else if(Var.Detectiv.contains(phd)){
						shop.updateCoins(phd, 2);
					}
				}
				if(phd.getHealth() < 3){
					phd.teleport(Factory.getConfigLocation("KZ", Var.cfg));
					phd.setHealth(20);
					for(int i = 20; i>0; i++){
						phd.setHealth(i);
					}
				}
			}
		}
		
	}
	

}
