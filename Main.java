import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

/* Created by Dylan Holloway
 * Website: www.dylanholloway.tk
 * Email: info@dylanholloway.tk
 * Date: 2014/08/05
 * Target: Minecraft CraftBukkit 1.7.2
 */

public class Main extends JavaPlugin {
	public Main plugin;

	public void onEnable()
	{
		Logger.getLogger("Minecraft").info("DAH Teleporter started.");
	}
	
	public void onDisable()
	{
		Logger.getLogger("Minecraft").info("DAH Teleporter stopped.");
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(!(sender instanceof Player)) {
			Logger.getLogger("Minecraft").warning(ChatColor.RED+"You can not use this command as console.");
			return false;
		}
		
		Player player = Bukkit.getPlayer(sender.getName());
		
		if(args.length < 1) {
			player.sendMessage(ChatColor.RED+"Usage: /tp <name> or /here <name>");
			return false;
		}
		
		if(Bukkit.getPlayer(args[0]) instanceof OfflinePlayer || Bukkit.getPlayer(args[0]) == null) {
			player.sendMessage(ChatColor.RED+args[0]+" is offline at the moment.");
			return false;
		}
		
		if(label.equalsIgnoreCase("tp")) {
			if(sender.hasPermission("dah.teleport.cmd.tp") || sender.isOp())
			{
				player.teleport(Bukkit.getPlayer(args[0]));
				player.sendMessage(ChatColor.YELLOW+"You teleported to "+Bukkit.getPlayer(args[0]).getName());
				Bukkit.getPlayer(args[0]).sendMessage(player.getName()+" teleported to you.");
				return true;
			} else {
				player.sendMessage(ChatColor.RED+"You do not have permission for this command.");
				return false;
			}
		}
		else if(label.equalsIgnoreCase("here")) {
			if(sender.hasPermission("dah.teleport.cmd.here") || sender.isOp())
			{
				player.teleport(Bukkit.getPlayer(args[0]));
				player.sendMessage("You teleported "+Bukkit.getPlayer(args[0]).getName()+" to your location.");
				Bukkit.getPlayer(args[0]).sendMessage(ChatColor.YELLOW+player.getName()+" grabbed your collar!");
				return true;
			} else {
				player.sendMessage(ChatColor.RED+"You do not have permission for this command.");
				return false;
			}
		}
		
		return false;
	}
}
