package de.rexlNico.GAME.GameStates;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.EntityEffect;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import de.rexlNico.GAME.Main.Main;
import de.rexlNico.GAME.Methodes.Factory;
import de.rexlNico.GAME.Methodes.Var;

public class IngameState extends GameState{
	
	private int TaskID;
	private int endTimer;
	private int GameTime;
	public static int Gsec = 60;
	public static int min = 15;
	public static int sec = 31;
	private Random r = new Random();
	
	@SuppressWarnings("deprecation")
	@Override
	public void init() {
		setTeam();
		//teleport
		for(Player a : Var.playing){
			a.teleport(Factory.getConfigLocation("Map", Var.cfg));
			a.playSound(a.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
			for(int i = 0; i<50; i++){
				a.playEffect(EntityEffect.WOLF_SMOKE);
			}
		}	
		// /shop aktivieren
		//damage an
		TaskID = Bukkit.getScheduler().scheduleAsyncRepeatingTask(Main.getPlugin(), new Runnable() {
			
			@Override
			public void run() {
				
				sec--;
				for(Player a : Bukkit.getOnlinePlayers()){
					a.setLevel(sec);
				}
				switch (sec) {
				case 30: case 15: case 10: case 5: case 4: case 3: case 2:
					for(Player a : Bukkit.getOnlinePlayers()){
						a.playSound(a.getLocation(), Sound.LEVEL_UP, 1, 1);
					}
					Bukkit.broadcastMessage(Var.pr+"§cAlle Spieler können sich in §6"+sec+" §cSekunden angreifen!");
					break;
				case 1:
					for(Player a : Bukkit.getOnlinePlayers()){
						a.playSound(a.getLocation(), Sound.LEVEL_UP, 1, 1);
					}
					Bukkit.broadcastMessage(Var.pr+"§cAlle Spieler können sich in §6einer §cSekunde angreifen!");
					break;
				case 0:
					Var.pvp = true;
					Var.enderchest = true;
					Bukkit.broadcastMessage(Var.pr+"§cAlle Spieler können sich §6jetzt §cangreifen!");
					Bukkit.getScheduler().cancelTask(TaskID);
					sec = 31;
					break;
				}
				
			}
		}, 0, 20*1);
		
		
		endTimer = Bukkit.getScheduler().scheduleAsyncRepeatingTask(Main.getPlugin(), new Runnable() {
			
			@Override
			public void run() {
				
				
				if(Var.Traitor.isEmpty()){
					Bukkit.broadcastMessage(Var.pr+"§cDie §eJuden §chaben gewonnen!");
					for(Player a: Bukkit.getOnlinePlayers()){
						a.sendTitle("§aGewonnen", "§eJuden");
					}
					Bukkit.getScheduler().cancelTask(endTimer);
					Bukkit.getScheduler().cancelTask(GameTime);
					GameStateHandler.setGameState(GameState.END_STATE);
				}
				
			}
		}, 1, 1);
		GameTime = Bukkit.getScheduler().scheduleAsyncRepeatingTask(Main.getPlugin(), new Runnable() {
			
			@Override
			public void run() {
				
				switch (min) {
				case 20:
					Bukkit.broadcastMessage(Var.pr+"§cDer §4Führer §chat gewonnen!");
					for(Player a: Bukkit.getOnlinePlayers()){
						a.sendTitle("§aGewonnen", "§4Führer");
					}
					break;
				case 15: case 10: case 5: case 4: case 3: case 2:
					Bukkit.broadcastMessage(Var.pr+"§7Das Spiel endet in §e"+min+" §7Minuten.");
					break;
					
				case 1:
					Bukkit.broadcastMessage(Var.pr+"§7Das Spiel endet in §e"+Gsec+" §7Sekunden.");
					break;
				}
				
				if(min >= 1){
					min--;
				}else{
					Gsec--;
				}
				
				switch (Gsec) {
				case 30: case 20: case 15: case 10: case 5: case 4: case 3: case 2:
					Bukkit.broadcastMessage(Var.pr+"§7Das Spiel endet in §e"+Gsec+" §7Sekunden.");
					break;

				case 1:
					Bukkit.broadcastMessage(Var.pr+"§7Das Spiel endet in §eeiner §7Sekunde.");
					break;
					
				case 0:
					Bukkit.getScheduler().cancelTask(endTimer);
					Bukkit.getScheduler().cancelTask(GameTime);
					GameStateHandler.setGameState(GameState.END_STATE);
					break;
				}
				
			}
		}, 0, 20*60);
		
	}

	@Override
	public void end() {
		// TODO Auto-generated method stub
		Bukkit.getScheduler().cancelTask(TaskID);
		Var.pvp = false;
		Var.canBuild = false;
		Var.chests = false;
		Var.shop = false;
		Var.enderchest = false;
	}
	
	private void setTeam(){
		
			int t = r.nextInt(Var.Inno.size());
			Var.Traitor.add(Var.Inno.get(t));
			Var.Inno.remove(t);
			

		
		for(Player a : Var.Traitor){
			a.sendMessage(Var.pr+"§eDu bist der §4Führer");
		}
		
	}
	
	
	
}
