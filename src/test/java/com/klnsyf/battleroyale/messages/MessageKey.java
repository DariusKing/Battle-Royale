package com.klnsyf.battleroyale.messages;

public enum MessageKey {

	// Hello, %PLAYER
	TEST_MESSAGE("TEST_MESSAGE", "%PLAYER"),

	// ��6=================[��b HELP��6 ]=================
	COMMANDS_HELP("COMMANDS.HELP"),

	// ��aTips: You can use ��6/br ��aas aliases of ��6/battleroyale
	COMMANDS_TIP("COMMANDS.TIP"),

	// ��aUndefinded SubCommand: ��c%COMMAND
	COMMANDS_UNDEFINDED("COMMANDS.UNDEFINDED", "%COMMAND"),

	// ��cCan't find world with name: ��b%WORLDNAME
	COMMANDS_UNDEFINDED_WORLD("COMMANDS.UNDEFINDED_WORLD", "%WORLDNAME"),

	// ��cWorld ��b%WORLDNAME ��chas been preseted
	COMMANDS_WORLD_PRESETTED("COMMANDS.WORLD_PRESETTED", "%WORLDNAME"),

	// ��cWorld ��b%WORLDNAME ��chasn't been preseted
	COMMANDS_WORLD_UNPRESETTED("COMMANDS.WORLD_UNPRESETTED", "%WORLDNAME"),

	// ��cCan't find configuration file with name: ��b%CONFIGNAME
	BATTLEFIELD_PRESET_UNDEFINDED_CONFIGURATIONFILE("BATTLEFIELD_PRESET.UNDEFINDED_CONFIGURATIONFILE", "%CONFIGNAME"),

	// ��aBattlefield ��b%WORLDNAME ��ahas successfully presetted with configuration
	// ��b%CONFIGNAME
	BATTLEFIELD_PRESET_SUCCESS_CONSOLE("BATTLEFIELD_PRESET.SUCCESS_CONSOLE", "%WORLDNAME", "%CONFIGNAME"),

	// ��aBattlefield ��b%WORLDNAME ��ahas been presetted\n ��7>> ��aYou can join this
	// battlefield by command: ��6/battleroyale join ��b%WORLDNAME
	BATTLEFIELD_PRESET_SUCCESS_PLAYER("BATTLEFIELD_PRESET.SUCCESS_PLAYER", "%WORLDNAME"),

	// ��cYou have already join battlefield ��b%WORLDNAME
	PLAYER_JOIN_BATTLEFIELD_JOINED("PLAYER_JOIN_BATTLEFIELD.JOINED", "%WORLDNAME"),

	// ��aPlayer ��d%PLAYERNAME has joined battlefield ��b%WORLDNAME
	PLAYER_JOIN_BATTLEFIELD_SUCCESS("PLAYER_JOIN_BATTLEFIELD.SUCCESS", "%PLAYERNAME", "%WORLDNAME"),
	;
	private String key;
	private String[] tags;

	private MessageKey(String key, String... tags) {
		this.key = key;
		this.tags = tags;
	}

	public String getKey() {
		return key;
	}

	public String[] getTags() {
		return tags;
	}

	@Override
	public String toString() {
		return key;
	}

}
