/*
 * All rights by DomeDD (2018)
 * You are allowed to modify this code
 * You are allowed to use this code in your plugins for private projects
 * You are allowed to publish your plugin including this code as long as your plugin is for free and as long as you mention me (DomeDD) 
 * You are NOT allowed to claim this plugin (BetterNick) as your own
 * You are NOT allowed to publish this plugin (BetterNick) or your modified version of this plugin (BetterNick)
 * 
 */
package de.domedd.betternick.addons.essentialschat;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import de.domedd.betternick.BetterNick;
import de.domedd.betternick.api.betternickapi.BetterNickAPI;

public class EssentialsChatHook implements Listener {

	private BetterNick pl;
	
	public EssentialsChatHook(BetterNick main) {
		this.pl = main;
	}
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		String format = e.getFormat();
		if(BetterNickAPI.getApi().isPlayerNicked(p)) {
			format = format.replace("{NICKPREFIX}", pl.getConfig().getString("Nick Options.Chat Prefix").replace("&", "�"));
			format = format.replace("{NICKSUFFIX}", pl.getConfig().getString("Nick Options.Chat Suffix").replace("&", "�"));
			format = format.replace("{NICKNAME}", BetterNickAPI.getApi().getNickName(p));
		} else {
			format = format.replace("{NICKPREFIX}", pl.chat.getPlayerPrefix(p).replace("&", "�"));
			format = format.replace("{NICKSUFFIX}", pl.chat.getPlayerSuffix(p).replace("&", "�"));
			format = format.replace("{NICKNAME}", p.getName());
		}
		e.setFormat(format);
	}
}
