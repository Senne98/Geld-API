package org.super_man2006.geldapi.update;

import org.bukkit.NamespacedKey;
import org.super_man2006.geldapi.Geld_API;
import org.super_man2006.geldapi.currency.Currency;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class Update {

    public static HashMap<UUID, Long> balanceOld = new HashMap<>();
    public static void update() {
        LoadSaveOld.read();

        if (balanceOld != null) {
            Currency currency = Currency.Currency(new NamespacedKey(Geld_API.plugin, "coins"));
            balanceOld.forEach((uuid, balance) -> {
                currency.add(uuid, balance);
            });
        }

        try {
            FileWriter writer = new FileWriter(Geld_API.versionFile);

            String version = Geld_API.plugin.getPluginMeta().getVersion();

            writer.write(version);
            writer.flush();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
