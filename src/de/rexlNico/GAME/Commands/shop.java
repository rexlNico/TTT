package de.rexlNico.GAME.Commands;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import de.rexlNico.GAME.Methodes.ItemBuilder;
import de.rexlNico.GAME.Methodes.Var;

public class shop implements CommandExecutor, Listener{

	public static HashMap<Player, Integer> punkte = new HashMap<>();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
		if(sender instanceof Player){
			Player p = (Player) sender;
			if(Var.shop == true){
				if(Var.Traitor.contains(p)){
					if(punkte.containsKey(p)){
					}else{
						punkte.put(p, 2);
					}
					Inventory inv = Bukkit.createInventory(null, InventoryType.HOPPER, "§4Traitor shop §5Punkte§8: §6"+punkte.get(p));
					inv.addItem(new ItemBuilder(Material.MONSTER_EGG, 1, 50).setName("§cCreeperpfeile").setLore("§7Kosten§8: §b2").build());
					inv.addItem(new ItemBuilder(Material.INK_SACK, 1, 1).setName("§cHeiltränke").setLore("§7Kosten§8: §b2 /n§7Heilt dich").build());
					inv.addItem(new ItemBuilder(Material.TNT, 1).setName("§cTnT").setLore("§7Kosten§8: §b3").build());
					inv.addItem(new ItemBuilder(Material.TRAPPED_CHEST, 1).setName("§cFakekiste").setLore("§7Kosten§8: §b4 /n§7Explodiert beim öffnen").build());
					inv.addItem(new ItemBuilder(Material.ENDER_PORTAL_FRAME, 1).setName("§cTeleporter").setLore("§7Kosten§8: §b5 /n§7Rechtsklick zum Punkt setzen. /n§7Linksklick zum porten.").build());
					
					p.openInventory(inv);
					
				}else if(Var.Detectiv.contains(p)){
					if(punkte.containsKey(p)){
					}else{
						punkte.put(p, 2);
					}
					Inventory inv = Bukkit.createInventory(null, InventoryType.HOPPER, "§1Detectiv shop §5Punkte§8: §6"+punkte.get(p));
					inv.addItem(new ItemBuilder(Material.BEACON, 1).setName("§cHealth station").setLore("§7Kosten§8: §b2 /n§7Heilt alle Spieler in einem Radius").build());
					inv.addItem(new ItemBuilder(Material.BONE, 1).setName("§cHunde").setLore("§7Kosten§8: §b3").build());
					inv.addItem(new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 15).setName("").build());
					inv.addItem(new ItemBuilder(Material.EYE_OF_ENDER, 1).setName("§cSwapper").setLore("§7Kosten§8: §b3 /n§7Tauscht die position von dir und einem anderen Spieler").build());
					inv.addItem(new ItemBuilder(Material.BOW, 1).setName("§cOneshot Bogen").setLore("§7Kosten§8: §b5 /n§7Benutzt: §10§7/§12").build());
					
					p.openInventory(inv);
					
				}
			}
		}else{
			sender.sendMessage(Var.console);
		}
		return false;
	}
	
	public void shopItem(Player p, int kosten, ItemStack item){
		if(punkte.get(p) >= kosten){
			int pk = punkte.get(p) - kosten;
			punkte.remove(p);
			punkte.put(p, pk);
			p.closeInventory();
			p.getInventory().addItem(item);
		}
	}
	public static void updateCoins(Player p, int points){
			int pk = punkte.get(p) + points;
			punkte.remove(p);
			punkte.put(p, pk);
	}
	
	@EventHandler
	public void on(InventoryClickEvent e){
		Player p = (Player) e.getWhoClicked();
		if(e.getInventory().getName().equals("§4Traitor shop §5Punkte§8: §6"+punkte.get(p))){
			e.setCancelled(true);
			if(e.getCurrentItem().getType().equals(Material.MONSTER_EGG)){
				shopItem(p, 2, new ItemBuilder(Material.MONSTER_EGG, 5, 50).build());
			}else if(e.getCurrentItem().getType().equals(Material.INK_SACK)){
				shopItem(p, 2, new ItemBuilder(Material.INK_SACK, 3, 1).build());
			}else if(e.getCurrentItem().getType().equals(Material.TNT)){
				shopItem(p, 3, new ItemBuilder(Material.TNT, 2).build());
			}else if(e.getCurrentItem().getType().equals(Material.TRAPPED_CHEST)){
				shopItem(p, 4, new ItemBuilder(Material.TRAPPED_CHEST, 1).build());
			}else if(e.getCurrentItem().getType().equals(Material.ENDER_PORTAL_FRAME)){
				shopItem(p, 5, new ItemBuilder(Material.ENDER_PORTAL_FRAME, 1).setLore("§7Rechtsklick zum Punkt setzen. /n§7Linksklick zum porten.").build());
			}
		}else if(e.getInventory().getName().equals("§1Detectiv shop §5Punkte§8: §6"+punkte.get(p))){
			e.setCancelled(true);
			if(e.getCurrentItem().getType().equals(Material.BEACON)){
				shopItem(p, 2, new ItemBuilder(Material.BEACON, 1).build());
			}else if(e.getCurrentItem().getType().equals(Material.BONE)){
				if(punkte.get(p) >= 3){
					int pk = punkte.get(p) - 3;
					punkte.remove(p);
					punkte.put(p, pk);
					p.closeInventory();
					@SuppressWarnings("deprecation")
					Wolf w1 = (Wolf) p.getWorld().spawnCreature(p.getLocation(), CreatureType.WOLF);
					w1.setOwner(p);
					w1.setTamed(true);
					Wolf w2 = (Wolf) p.getWorld().spawnCreature(p.getLocation(), CreatureType.WOLF);
					w2.setOwner(p);
					w2.setTamed(true);
				}
				
			}else if(e.getCurrentItem().getType().equals(Material.EYE_OF_ENDER)){
				shopItem(p, 3, new ItemBuilder(Material.EYE_OF_ENDER, 1).build());
			}else if(e.getCurrentItem().getType().equals(Material.BOW)){
				shopItem(p, 5, new ItemBuilder(Material.BOW, 1).setLore("§7Benutzt: §10§7/§12").build());
			}
		}
	}
	@EventHandler
	public void on(PlayerInteractEvent e){
		if(e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.LEFT_CLICK_BLOCK)){
			
		}
	}

}
