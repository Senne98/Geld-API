package org.super_man2006.geldapi.json;

import com.google.common.reflect.TypeToken;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.super_man2006.geldapi.Geld_API;
import org.super_man2006.geldapi.utils.Base64;

import java.io.*;
import java.util.HashMap;

public class LoadSave {

    private static File file;

    public static void write() {
        file = Geld_API.currencyFile;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        if (Geld_API.currencyList == null) {
            return;
        }

        HashMap<String, String> encodedMap = new HashMap<>();
        Geld_API.currencyList.forEach((namespacedKey, currency) -> {
            encodedMap.put(namespacedKey.asString(), Base64.encode(currency));
        });

        try {
            JsonWriter writer = new JsonWriter(new FileWriter(file));
            gson.toJson(encodedMap, new TypeToken<HashMap<String, String>>(){}.getType(), writer);
            writer.flush();

        } catch (IOException e) {
            Bukkit.getLogger().warning("Failed to save balances");
            throw new RuntimeException(e);
        }
    }

    public static void read() {
        file = Geld_API.currencyFile;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        if(!file.canRead()) {
            return;
        }

        try {
            JsonReader reader = new JsonReader(new FileReader(file));
            HashMap<String, String> encodedMap = gson.fromJson(reader, new TypeToken<HashMap<String, String>>(){}.getType());
            encodedMap.forEach((key, currency) -> {
                try {
                    Geld_API.currencyList.put(NamespacedKey.fromString(key), (org.super_man2006.geldapi.currency.Currency) Base64.decode(currency));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

        } catch (FileNotFoundException e) {
            Bukkit.getLogger().warning("Failed to load balances");
            throw new RuntimeException(e);
        } catch (JsonSyntaxException e) {
            throw new JsonSyntaxException(e);
        }
    }
}
