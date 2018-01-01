package com.klnsyf.battleroyale.listeners;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.klnsyf.battleroyale.BattleRoyaleSetup;
import com.klnsyf.battleroyale.battleroyale.BattleRoyale;
import com.klnsyf.battleroyale.configuration.Config;
import com.klnsyf.battleroyale.events.GameJoinEvent;

public class GameJoin implements Listener {
	private BattleRoyale battleRoyale;
	private BattleRoyaleSetup plugin;
	private Config config;

	public GameJoin(BattleRoyale battleRoyale) {
		this.battleRoyale = battleRoyale;
		this.plugin = battleRoyale.getPlugin();
		this.config = battleRoyale.getConfig();
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onPlayerJoin(GameJoinEvent event) {
		if (battleRoyale.getState() == 1) {
			ArrayList<Player> players = config.getPlayers();
			if (players.contains(event.getPlayer())) {
				event.getPlayer()
						.sendMessage("[��6Battle Royale��r] ��cError: You have already joined into this battlefield");
			} else {
				if (players.size() == config.getSpreadLocation().size()) {
					event.getPlayer().sendMessage("[��6Battle Royale��r] ��cThis battlefield is full");
				} else {
					players.add(event.getPlayer());
					config.setPlayers(players);
					battleRoyale.getPlugin().getServer().getConsoleSender()
							.sendMessage("[��6Battle Royale��r] ��d" + event.getPlayer().getName()
									+ " ��ajoin into battle field ��b" + battleRoyale.getWorld().getName() + " ("
									+ players.size() + "/" + config.getSpreadLocation().size() + ")");
					for (Player player : battleRoyale.getPlugin().getServer().getOnlinePlayers()) {
						player.sendMessage("[��6Battle Royale��r] ��d" + event.getPlayer().getName()
								+ " ��ajoin into battle field ��b" + battleRoyale.getWorld().getName() + " ("
								+ players.size() + "/" + config.getSpreadLocation().size() + ")");
					}
					sendBattleFieldInfo(event.getPlayer());
				}
			}
		}
	}

	private void sendBattleFieldInfo(Player player) {
		player.sendMessage("[��6Battle Royale��r] ��aBattlefield Information");
		player.sendMessage("��7>> ��aMax Health: ��b" + config.getMaxHealth());
		player.sendMessage("��7>> ��aWorldborder: ��b" + config.getMaxRadius() + "��a downto ��b" + config.getMinRadius()
				+ "��a (��b" + config.getShrinkSpeed() + " ��am/s)");
		if (config.isProtectAnimal()) {
			player.sendMessage("��7>> ��aAnimals are ��binvulnerable��a");
		}
		if (config.isAutoMelt()) {
			player.sendMessage("��7>> ��aOre will be ��bautomatically��a melted into ingots");
		}
		if (config.getCompassMode()) {
			player.sendMessage("��7>> ��a��bRight click��a to interact with compass");
		} else {
			player.sendMessage("��7>> ��a��bDrop out��a to interact with compass");
		}
		player.sendMessage("��7>> ��aSneak and drop out ��b" + config.getAccelerateItem()
				+ "��a to accelerafe the world border's shrinking as another ��b" + config.getAccelerateSpeed()
				+ " ��am/s for ��b" + (int) (config.getAccelerateTicks() / 20) + "��a seconds");
		player.sendMessage(
				"��7>> ��aSneak and right click a ��bGOLD_BLOCK��a with a stick to summon a blaze with brewing stand and nether wart");
	}

	public BattleRoyale getBattleRoyale() {
		return battleRoyale;
	}

	public void setBattleRoyale(BattleRoyale battleRoyale) {
		this.battleRoyale = battleRoyale;
	}

}
