package de.rexlNico.GAME.Commands;

import java.io.File;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import de.rexlNico.GAME.Methodes.Factory;
import de.rexlNico.GAME.Methodes.ItemBuilder;
import de.rexlNico.GAME.Methodes.Var;

public class setSpawn implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player){
			Player p = (Player)sender;
			if(label.equalsIgnoreCase("setSpawn")){
				if(p.hasPermission(Var.perms+"setspawns")){
					if(args.length == 1){
						if(args[0].equalsIgnoreCase("spawn")){
							Factory.CreateConfigLocation(p.getLocation(), Var.cfg, Var.file, "Spawn");
							p.sendMessage(Var.pr+"§aDu hast den Spawn §6Spawn §agesetzt");
						}else if(args[0].equalsIgnoreCase("Map")){
							Factory.CreateConfigLocation(p.getLocation(), Var.cfg, Var.file, "Map");
							p.sendMessage(Var.pr+"§aDu hast den Spawn §6Map §agesetzt");
						}else if(args[0].equalsIgnoreCase("spec")){
							Factory.CreateConfigLocation(p.getLocation(), Var.cfg, Var.file, "Spec");
							p.sendMessage(Var.pr+"§aDu hast den Spawn §6Spec §agesetzt");
						}else if(args[0].equalsIgnoreCase("kz")){
							Factory.CreateConfigLocation(p.getLocation(), Var.cfg, Var.file, "KZ");
							p.sendMessage(Var.pr+"§aDu hast den Spawn §6Spec §agesetzt");
						}else{
							p.sendMessage(Var.error+"setspawn <spawn/map/spec>");
						}
					}else{
						p.sendMessage(Var.error+"setspawn <spawn/map/spec>");
					}
				}else{
					p.sendMessage(Var.noperm);
				}
			}else if(label.equalsIgnoreCase("setChest")){
				if(args.length == 1){
					if(args[0].equalsIgnoreCase("normal")){
						p.getInventory().addItem(new ItemBuilder(Material.SKULL_ITEM, 1, 3).setSkullOwner("Zealock").setName("Normale Kiste").build());
						p.playSound(p.getLocation(), Sound.DIG_SNOW, 1, 1);
					}else if(args[0].equalsIgnoreCase("ender")){
						p.getInventory().addItem(new ItemBuilder(Material.SKULL_ITEM, 1, 3).setSkullOwner("Torias_Dax").setName("Ender Kiste").build());
						p.playSound(p.getLocation(), Sound.DIG_SNOW, 1, 1);
					}else{
						p.sendMessage(Var.error+"setChest <normal/ender>");
					}
				}else{
					p.sendMessage(Var.error+"setChest <normal/ender>");
				}
			}
		}else{
			sender.sendMessage(Var.console);
		}
		return false;
	}

}


// setspawn <spawn/map/spec>
