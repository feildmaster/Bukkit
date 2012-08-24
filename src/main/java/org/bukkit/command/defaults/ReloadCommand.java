package org.bukkit.command.defaults;

import java.util.Arrays;
import org.bukkit.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class ReloadCommand extends BukkitCommand {
    public ReloadCommand(String name) {
        super(name);
        this.description = "Reloads the server configuration and plugins";
        this.usageMessage = "/reload [soft]";
        this.setPermission("bukkit.command.reload");
        this.setAliases(Arrays.asList("rl"));
    }

    @Override
    public boolean execute(CommandSender sender, String currentAlias, String[] args) {
        if (!testPermission(sender)) return true;

        if (args.length == 0) {
            Bukkit.reload();
        } else {
            Bukkit.reload(args[0].equalsIgnoreCase("soft"));
        }

        sender.sendMessage(ChatColor.GREEN + "Reload complete.");

        return true;
    }
}
