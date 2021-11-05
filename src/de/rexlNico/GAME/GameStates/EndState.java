package de.rexlNico.GAME.GameStates;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import de.rexlNico.GAME.Main.Main;
import de.rexlNico.GAME.listeners.Chest;

public class EndState extends GameState{

	private int TaskID;
	private int sec = 15;
	@SuppressWarnings("deprecation")
	@Override
	public void init() {
		
		TaskID = Bukkit.getScheduler().scheduleAsyncRepeatingTask(Main.getPlugin(), new Runnable() {
			
			@Override
			public void run() {
				
				switch (sec) {
				case 15: case 10: case 5: case 4: case 3: case 2:
					Bukkit.broadcastMessage("§cDer Server restartet in §6"+sec+" §cSekunden!");
					break;
					
				case 1:
					Bukkit.broadcastMessage("§cDer Server restartet in §6einer §cSekunde!");
					break;
					
				case 0:
					Bukkit.broadcastMessage("§cDer Server restartet jetzt!");
					reset();
					break;
				}
				sec--;
				
			}
		}, 0, 20*1);
		
	}

	public void reset(){
		Bukkit.getScheduler().cancelTask(TaskID);
		sec = 15;
		for(Player a : Bukkit.getOnlinePlayers()){
			ByteArrayDataOutput out = ByteStreams.newDataOutput();
			out.writeUTF("Connect");
			out.writeUTF("lobby-01");
			a.sendPluginMessage(Main.getPlugin(), "BungeeCord", out.toByteArray());
		}
		Chest.resetChest();
		Bukkit.reload();
		
	}
	
	@Override
	public void end() {
		// TODO Auto-generated method stub
		
	}

}
