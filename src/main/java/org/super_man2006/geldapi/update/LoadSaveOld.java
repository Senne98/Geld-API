package org.super_man2006.geldapi.update;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import org.bukkit.Bukkit;
import org.super_man2006.geldapi.Geld_API;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.UUID;

public class LoadSaveOld {

    public static void read() {
        File file = new File(Geld_API.plugin.getDataFolder(), "GeldLib/currency.json");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        if(!file.canRead()) { return; }
        try {
            JsonReader reader = new JsonReader(new FileReader(file));
            HashMap<UUID, Long> balance = gson.fromJson(reader, new TypeToken<HashMap<UUID, Long>>(){}.getType());

            Update.balanceOld = balance;

        } catch (FileNotFoundException e) {
            Bukkit.getLogger().warning("Failed to load balances");
            throw new RuntimeException(e);
        } catch (JsonSyntaxException e) {
            throw new JsonSyntaxException(e);
        }
    }
}
