package com.klnsyf.battleroyale;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.SystemUtils;
import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.klnsyf.battleroyale.battlefield.BattlefieldHandler;
import com.klnsyf.battleroyale.commands.Commands;
import com.klnsyf.battleroyale.initialization.Listeners;
import com.klnsyf.battleroyale.messages.Messages;
import com.klnsyf.battleroyale.settings.Settings;
import com.klnsyf.battleroyale.utils.UpdateChecker;

public class BattleRoyale extends JavaPlugin {
	public static BattleRoyale plugin;
	public static Server server;
	public static File dataFolder;
	public static Settings settings;
	public final static String prefix = "[��6Battle Royale��r] ";

	@Override
	public void onEnable() {
		plugin = this;

		BattleRoyale.server = getServer();
		BattleRoyale.dataFolder = getDataFolder();
		BattleRoyale.settings = new Settings();

		saveDefaultConfig();
		if (!new File(dataFolder, "\\language\\en_US.yml").exists()) {
			saveResource("language\\en_US.yml", false);
		}
		if (!new File(dataFolder, "\\configuration\\default.yml").exists()) {
			saveResource("configuration\\default.yml", false);
		}

		if (!SystemUtils.isJavaVersionAtLeast(1.8f)) {
			throw new IllegalStateException("[Battle Royale] Required Java Version is at least 1.8!");
		}

		final String version = getServer().getClass().getPackage().getName().replace("org.bukkit.craftbukkit.", "");
		final List<String> compatible = Arrays.asList("v1_9_R1", "v1_9_R2", "v1_10_R1", "v1_11_R1", "v1_12_R1");
		if (!(compatible.contains(version))) {
			getServer().getPluginManager().disablePlugin(this);
			throw new IllegalStateException("[Battle Royale] Required Minecraft Server Version is at least 1.9!");
		}

		if (Settings.isCheckUpdate) {
			new BukkitRunnable() {
				@Override
				public void run() {
					new UpdateChecker().checkUpdate();
					this.cancel();
				}
			}.runTaskAsynchronously(this);
		}

		if (!Messages.language.exists()) {
			try {
				throw new FileNotFoundException("\n		 [Battle Royale] Can't find language file with name "
						+ Settings.language
						+ "\n		 [Battle Royale] Please make sure the language file has completely installed under the right path"
						+ "\n 		 [Battle Royale] Language will automatically switch to en_US");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} finally {
				Settings.language = Settings.DEFAULT_LANGUAGE;
			}
		}

		for (String str : this.getDescription().getCommands().keySet()) {
			getCommand(str).setExecutor(new Commands());
		}

		new Listeners().initListeners();
		new BattlefieldHandler();
	}

}
