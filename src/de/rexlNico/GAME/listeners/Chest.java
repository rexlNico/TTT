package de.rexlNico.GAME.listeners;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import de.rexlNico.GAME.GameStates.IngameState;
import de.rexlNico.GAME.Methodes.Var;

public class Chest implements Listener{
	
	public static ArrayList<Location> chests = new ArrayList<>();
	public static ArrayList<Location> enderchests = new ArrayList<>();
	public static ArrayList<Player> ec = new ArrayList<>();
	public static void resetChest(){
		if(!chests.isEmpty()){
			for(int i = 0; i< chests.size(); i++){
				chests.get(i).getBlock().setType(Material.CHEST);
			}
		}
		if(!enderchests.isEmpty()){
			for(int i = 0; i< enderchests.size(); i++){
				enderchests.get(i).getBlock().setType(Material.ENDER_CHEST);
			}
		}
	}
	
	@EventHandler
	public void on(PlayerInteractEvent e){
		Player p = e.getPlayer();
		if(Var.chests == true){
			if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
				if(e.getClickedBlock().getType().equals(Material.CHEST)){
					e.setCancelled(true);
					if(p.getInventory().contains(Material.STONE_SWORD)){
						
					}else if(p.getInventory().contains(Material.BOW) || p.getInventory().contains(Material.ARROW)){
							p.getInventory().addItem(new ItemStack(Material.STONE_SWORD));
							chests.add(e.getClickedBlock().getLocation());
							e.getClickedBlock().setType(Material.AIR);
							
					}else if(p.getInventory().contains(Material.WOOD_SWORD)){
						p.getInventory().addItem(new ItemStack(Material.BOW));
						p.getInventory().addItem(new ItemStack(Material.ARROW, 32));
						chests.add(e.getClickedBlock().getLocation());
						e.getClickedBlock().setType(Material.AIR);
						
					}else{
						p.getInventory().addItem(new ItemStack(Material.WOOD_SWORD));
						chests.add(e.getClickedBlock().getLocation());
						e.getClickedBlock().setType(Material.AIR);
					}
					
					
					
				}else if(e.getClickedBlock().getType().equals(Material.ENDER_CHEST)){
					e.setCancelled(true);
					if(Var.enderchest == true){
						if(p.getInventory().contains(Material.IRON_SWORD)){
						}else{
								p.getInventory().addItem(new ItemStack(Material.IRON_SWORD));
								enderchests.add(e.getClickedBlock().getLocation());
								e.getClickedBlock().setType(Material.AIR);
						}
					}else{
						p.sendMessage(Var.pr+"§cDu kannst die Kiste erst öffnen wen PvP aktiviert ist!");
					}
				}else if(e.getClickedBlock().getType().equals(Material.TRAPPED_CHEST)){
					e.setCancelled(true);
					Block b = e.getClickedBlock();
					b.setType(Material.AIR);
					Location loc2 = new Location(b.getLocation().getWorld(), b.getLocation().getX()+0.5, b.getLocation().getY(), b.getLocation().getZ()+0.5);
					TNTPrimed tnt = (TNTPrimed) b.getLocation().getWorld().spawnEntity(loc2, EntityType.PRIMED_TNT);
					tnt.setFuseTicks(2);
				}
			}
		}
	}
	
}
