package de.rexlNico.GAME.listeners;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

import de.rexlNico.GAME.Methodes.Var;

public class build implements Listener{

	public static HashMap<Player, Location> teleporter = new HashMap<>();
	
	@EventHandler
	public void on(BlockBreakEvent e){
		if(e.getPlayer().getGameMode() == GameMode.CREATIVE){
			
		}else{
			e.setCancelled(true);
		}
	}
	@EventHandler
	public void on(BlockPlaceEvent e){
		if(e.getBlock().getType().equals(Material.ENDER_PORTAL_FRAME)){
			e.setCancelled(true);
			Player p = e.getPlayer();
			if(teleporter.containsKey(p)){
				teleporter.remove(p);
				teleporter.put(p, e.getBlock().getLocation());
			}else{
				teleporter.put(p, e.getBlock().getLocation());
			}
		}else if(e.getBlock().getType().equals(Material.TNT)){
			if(Var.canBuild == true){
				Location loc = e.getBlock().getLocation();
				Location loc2 = new Location(loc.getWorld(), loc.getX()+0.5, loc.getY(), loc.getZ()+0.5);
				loc.getBlock().setType(Material.AIR);
				Bukkit.getWorld(loc.getWorld().getName()).spawnEntity(loc2, EntityType.PRIMED_TNT);
			}else{
				e.setCancelled(true);
			}
		}else{
			if(e.getPlayer().getGameMode() == GameMode.CREATIVE){
				
			}else{
				e.setCancelled(true);
			}
		}
	}
	@EventHandler
	public void onExplode(EntityExplodeEvent e) {
		e.blockList().clear();
	}
	
	
	@EventHandler
	public void on(PlayerPickupItemEvent e){
		Player p = e.getPlayer();
		if(e.getItem().getType().equals(Material.IRON_SWORD)){
			
		}
	}
	
}
