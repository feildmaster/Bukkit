package org.bukkit.command.defaults;

import java.util.List;

import org.apache.commons.lang.Validate;

import org.bukkit.command.CommandSender;

import com.google.common.collect.ImmutableList;

public class SeedCommand extends VanillaCommand {
    public SeedCommand() {
        super("seed");
        this.description = "Shows the world seed";
        this.usageMessage = "/seed";
        this.setPermission("bukkit.command.seed");
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!testPermission(sender)) return true;

        long seed = getWorld(sender).getSeed();
        sender.sendMessage("Seed: " + seed);

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
