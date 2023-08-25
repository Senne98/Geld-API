package org.super_man2006.geldapi.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.super_man2006.geldapi.Geld_API;

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
            player.sendMessage(Component.text("Your balances:").color(NamedTextColor.GREEN));
            Geld_API.currencyList.forEach((key, currency) -> {
                Component name = currency.getName();
                String balance = currency.get(player.getUniqueId()).toString();
                player.sendMessage(name.append(Component.text(": " + balance)).color(NamedTextColor.GREEN));
            });
        }
        return true;
    }
}
