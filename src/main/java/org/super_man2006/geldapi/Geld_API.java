package org.super_man2006.geldapi;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.super_man2006.geldapi.commands.Balance;
import org.super_man2006.geldapi.commands.Test;
import org.super_man2006.geldapi.json.Currency;

import java.io.File;
import java.util.HashMap;
import java.util.UUID;

public final class Geld_API extends JavaPlugin {

    public static HashMap<UUID, Long> balance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().sendMessage(Component.text("[Geld-API]" , NamedTextColor.GRAY).append(
                Component.text("enabling plugin" , NamedTextColor.GREEN)));

        if(!currencyFile.exists()) {
            saveResource("GeldLib/currency.json", false);
            Component logText = Component.text("[Geld-API] generated currency.json");
            getServer().sendMessage(logText);
        }
        currencyFileStatic = currencyFile;
        Currency.read();

        //commands
        getCommand("balance").setExecutor(new Balance());

        //PaperEvents
        getServer().getPluginManager().registerEvents(new org.super_man2006.geldapi.player.Balance(), this);

        getServer().sendMessage(Component.text("[Geld-API]" , NamedTextColor.GRAY).append(
                Component.text("plugin enabled" , NamedTextColor.GREEN)));
    }

    private File currencyFile = new File(getDataFolder(), "GeldLib/currency.json");
    public File getCurrencyFile() {
        return currencyFile;
    }
    public static File currencyFileStatic;
    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getServer().sendMessage(Component.text("[Geld-API]" , NamedTextColor.GRAY).append(
                Component.text("disabling plugin" , NamedTextColor.RED)));

        //Save logic
        Currency.write();


        getServer().sendMessage(Component.text("[Geld-API]" , NamedTextColor.GRAY).append(
                Component.text("plugin disabled" , NamedTextColor.RED)));
    }
}
