package de.rexlNico.GAME.Methodes;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Var {

	public static File file = new File("../SuHOptions/config.yml");
	public static YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
	
	public static final String pr = "§8[§4§lS&H§8] §7",
			noperm = pr+"§cDazu hast du keine Rechte!",
			error = pr+"Bitte nutze §c/",
			console = "§4Das darf nur ein Spieler ausführen.",
			perms = "SuH.";
	
	public static boolean canBuild = false,
						  pvp = false,
						  shop = false,
						  chests = false,
						  enderchest = false;
	
	
	public static ArrayList<Player> spec = new ArrayList<>();
	public static ArrayList<Player> Traitor = new ArrayList<>();
	public static ArrayList<Player> Detectiv = new ArrayList<>();
	public static ArrayList<Player> Inno = new ArrayList<>();
	public static ArrayList<Player> playing = new ArrayList<>();
	
	
	
			
	
}
