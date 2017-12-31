package com.klnsyf.battleroyale.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.klnsyf.battleroyale.BattleRoyaleSetup;
import com.klnsyf.battleroyale.battleroyale.BattleRoyale;
import com.klnsyf.battleroyale.configuration.Config;
import com.klnsyf.battleroyale.events.WorldBorderStopShrinkEvent;
import com.klnsyf.battleroyale.utils.ActionbarMessage;

public class WorldBorderStopShrink implements Listener {
	private BattleRoyale battleRoyale;
	private BattleRoyaleSetup plugin;
	private Config config;

	public WorldBorderStopShrink(BattleRoyale battleRoyale) {
		this.battleRoyale = battleRoyale;
		this.plugin = battleRoyale.getPlugin();
		this.config = battleRoyale.getConfig();
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onWorldBorderStopShrink(WorldBorderStopShrinkEvent event) {
		battleRoyale.getWorld().setGameRuleValue("naturalRegeneration", "false");
		plugin.getServer().getConsoleSender().sendMessage("[��6Battle Royale��r] ��cWorld Border has stopped shrinking");
		for (Player player : config.getPlayers()) {
			player.sendTitle("[��6 Battle Royale��r]", "-= ��cDeath Match��r =-", 10, 20, 10);
			new ActionbarMessage(plugin).sendActionbarMessage(player, "��c��lWorld Border Has Stopped Shrinking");
		}
	}

	public BattleRoyale getBattleRoyale() {
		return battleRoyale;
	}

	public void setBattleRoyale(BattleRoyale battleRoyale) {
		this.battleRoyale = battleRoyale;
	}
}
