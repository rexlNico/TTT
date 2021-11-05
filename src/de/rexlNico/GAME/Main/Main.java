package de.rexlNico.GAME.Main;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import de.rexlNico.GAME.Commands.Start;
import de.rexlNico.GAME.Commands.setSpawn;
import de.rexlNico.GAME.Commands.shop;
import de.rexlNico.GAME.GameStates.GameState;
import de.rexlNico.GAME.GameStates.GameStateHandler;
import de.rexlNico.GAME.listeners.Chest;
import de.rexlNico.GAME.listeners.DeathListener;
import de.rexlNico.GAME.listeners.Join;
import de.rexlNico.GAME.listeners.Quit;
import de.rexlNico.GAME.listeners.build;

public class Main extends JavaPlugin{

	private static Main plugin;
	private PluginManager pm = Bukkit.getPluginManager();
	
	
	@Override
	public void onEnable() {
		plugin = this;	
		Bukkit.getConsoleSender().sendMessage("###############");
		Bukkit.getConsoleSender().sendMessage("# §4TTT §aGeladen§7 #");
		Bukkit.getConsoleSender().sendMessage("###############");
		this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		Load();
		new GameStateHandler();
		GameStateHandler.setGameState(GameState.LOBBY_STATE);
	}
	public static Main getPlugin() {
		return plugin;
	}
	
	private void Load(){
		//Commands
		RegCommand("start", new Start());
		RegCommand("setspawn", new setSpawn());
		RegCommand("setchest", new setSpawn());
		RegCommand("shop", new shop());
		//Listeners
		pm.registerEvents(new Join(), this);
		pm.registerEvents(new Chest(), this);
		pm.registerEvents(new Quit(), this);
		pm.registerEvents(new build(), this);
		pm.registerEvents(new DeathListener(), this);
		pm.registerEvents(new shop(), this);
	}
	
	private void RegCommand(String name,CommandExecutor exe){
		getCommand(name).setExecutor(exe);
	}
	
}
