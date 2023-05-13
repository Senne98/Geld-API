package org.super_man2006.geldapi.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Balance implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Component msgServer = Component.text("This command can only be used by players!").color(NamedTextColor.RED);
            sender.sendMessage(msgServer);
            return true;
        }

        if (cmd.getName().equalsIgnoreCase("balance")) {
            Player player = (Player) sender;
            Long balance = org.super_man2006.geldapi.player.Balance.get(player.getUniqueId());
            player.sendMessage(Component.text("Your balance: " + balance.toString()).color(NamedTextColor.GREEN));
        }
        return true;
    }
}
