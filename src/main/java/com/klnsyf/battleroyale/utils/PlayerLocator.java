package com.klnsyf.battleroyale.utils;

import java.util.ArrayList;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.klnsyf.battleroyale.BattleRoyaleSetup;

public class PlayerLocator {
	private BattleRoyaleSetup plugin;

	public PlayerLocator(BattleRoyaleSetup plugin) {
		this.plugin = plugin;
	}

	public void playerLocate(Player sender, ArrayList<Player> players, boolean isHideName, boolean isSkipSender) {
		for (Player player : players) {
			if (isSkipSender && sender == player) {
				continue;
			}
			String s = "Player";
			if (!isHideName) {
				s = player.getName();
			}
			int dis = (int) sender.getLocation().distance(player.getLocation());
			String c;
			if (dis >= 800) {
				c = "��a";
			} else if (dis >= 500) {
				c = "��e";
			} else if (dis >= 200) {
				c = "��6";
			} else {
				c = "��4";
			}
			sender.sendMessage("��7>> ��d " + s + " ��b>��f " + (int) player.getLocation().getX() + ", "
					+ (int) player.getLocation().getZ() + " (" + c + dis + "��am)");
		}
	}

	public void playerLocate(CommandSender sender, ArrayList<Player> players) {
		for (Player player : players) {
			sender.sendMessage("��7>> ��d " + player.getName() + " ��b>��f " + (int) player.getLocation().getX() + ", "
					+ (int) player.getLocation().getZ());
		}

	}

	public BattleRoyaleSetup getPlugin() {
		return plugin;
	}

	public void setPlugin(BattleRoyaleSetup plugin) {
		this.plugin = plugin;
	}
}
