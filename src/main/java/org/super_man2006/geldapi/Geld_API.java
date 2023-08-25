package org.super_man2006.geldapi;

import org.apache.commons.io.FileUtils;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;
import org.super_man2006.geldapi.bStats.Metrics;
import org.super_man2006.geldapi.commands.Balance;
import org.super_man2006.geldapi.currency.Currency;
import org.super_man2006.geldapi.currency.FirstJoin;
import org.super_man2006.geldapi.json.LoadSave;
import org.super_man2006.geldapi.update.Update;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public final class Geld_API extends JavaPlugin {

    public static HashMap<NamespacedKey, Currency> currencyList = new HashMap<>();
    public static File currencyFile;
    public static File versionFile;
    public static JavaPlugin plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;

        versionFile = new File(getDataFolder(), "version.txt");
        currencyFile = new File(getDataFolder(), "currency.json");

        saveResource("currency.json", false);
        ConfigurationSerialization.registerClass(Currency.class);
        LoadSave.read();

        if (!versionFile.exists()) {
            if (new File(getDataFolder(), "GeldLib/currency.json").exists()) {
                Update.update();
                new File(getDataFolder(), "GeldLib/currency.json").delete();
                try {
                    FileUtils.deleteDirectory(new File(getDataFolder(), "GeldLib"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        //bStats
        int pluginId = 19200; // <-- Replace with the id of your plugin!
        Metrics metrics = new Metrics(this, pluginId);

        //commands
        getCommand("balance").setExecutor(new Balance());

        //Events
        getServer().getPluginManager().registerEvents(new FirstJoin(), this);
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
        LoadSave.write();
    }
}
