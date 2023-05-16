package org.super_man2006.geldapi.json;

import com.google.common.reflect.TypeToken;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.super_man2006.geldapi.Geld_API;

import java.io.*;
import java.util.HashMap;
import java.util.UUID;

public class Currency {

    private static File file;

    private static void file() {
        file = Geld_API.currencyFileStatic;
    }

    public static void write() {
        file();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        if (Geld_API.balance == null) {
            return;
        }
        try {
            JsonWriter writer = new JsonWriter(new FileWriter(file));
            gson.toJson(Geld_API.balance, new TypeToken<HashMap<UUID, Long>>(){}.getType(), writer);
            writer.flush();

        } catch (IOException e) {
            Bukkit.getLogger().warning("Failed to save balances");
            throw new RuntimeException(e);
        }
    }

    public static void read() {
        file();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        if(!file.canRead()) { return; }
        try {
            JsonReader reader = new JsonReader(new FileReader(file));
            HashMap<UUID, Long> balance = gson.fromJson(reader, new TypeToken<HashMap<UUID, Long>>(){}.getType());

            Geld_API.balance = balance;

        } catch (FileNotFoundException e) {
            Bukkit.getLogger().warning("Failed to load balances");
            throw new RuntimeException(e);
        } catch (JsonSyntaxException e) {
            throw new JsonSyntaxException(e);
        }
    }
}
