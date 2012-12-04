package org.bukkit.command.defaults;

import java.util.List;

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.google.common.collect.ImmutableList;
import org.apache.commons.lang.StringUtils;
import org.bukkit.entity.Player;

public class StopCommand extends VanillaCommand {
    public StopCommand() {
        super("stop");
        this.description = "Stops the server";
        this.usageMessage = "/stop [message...]";
        this.setPermission("bukkit.command.stop");
    }

    @Override
    public boolean execute(CommandSender sender, String currentAlias, String[] args) {
        if (!testPermission(sender)) return true;

        String stopMessage = this.createString(args, 0);

        StringBuilder broadcastMessage = new StringBuilder();
        broadcastMessage.append("Stopping the server...");
        if (StringUtils.isNotEmpty(stopMessage)) {
            broadcastMessage.append("Reason: ").append(stopMessage);
        }

        Command.broadcastCommandMessage(sender, broadcastMessage.toString());
        Bukkit.shutdown();

        if (StringUtils.isNotEmpty(stopMessage)) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.kickPlayer(stopMessage);
            }
        }

        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
        Validate.notNull(sender, "Sender cannot be null");
        Validate.notNull(args, "Arguments cannot be null");
        Validate.notNull(alias, "Alias cannot be null");

        return ImmutableList.of();
    }
}
