package com.klnsyf.battleroyale.listeners;

import org.bukkit.Difficulty;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.klnsyf.battleroyale.BattleRoyale;
import com.klnsyf.battleroyale.battlefield.BattlefieldHandler;
import com.klnsyf.battleroyale.events.BattleEndEvent;

public class BattleEnd implements Listener {
	private final BattleRoyale plugin = BattleRoyale.plugin;
	private final Server server = BattleRoyale.server;

	public BattleEnd() {
		server.getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onBattleEnd(BattleEndEvent event) {
		if (event.getWinner() != null) {
			for (Player player : BattlefieldHandler.battlefields.get(event.getWorld()).players) {
				player.sendTitle("[��6Battle Royale��]", "-= Winner: ��d" + event.getWinner().getName() + "��r =-", 10, 80,
						10);
			}
		} else {
			server.getConsoleSender().sendMessage("[��6Battle Royale��r] ��cGame has been forced to terminate");
		}
		event.getWorld().setDifficulty(Difficulty.PEACEFUL);
		BattlefieldHandler.battlefields.remove(event.getWorld());
	}

}
