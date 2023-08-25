package org.super_man2006.geldapi.currency;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.jetbrains.annotations.NotNull;
import org.super_man2006.geldapi.Geld_API;
import org.super_man2006.geldapi.events.BalanceChangeEvent;
import org.super_man2006.geldapi.events.CurrencyCreateEvent;
import org.super_man2006.geldapi.events.CurrencyRemoveEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Currency implements ConfigurationSerializable {
    private static HashMap<UUID, Long> balance = new HashMap<>();
    private Component name;
    private Component symbol;
    private NamespacedKey key;

    public Currency(NamespacedKey key, Component name, Component symbol) {
        if (Geld_API.currencyList != null && Geld_API.currencyList.containsKey(key)) {
            return;
        }

        CurrencyCreateEvent e = new CurrencyCreateEvent(key, name, symbol, this);
        e.callEvent();

        this.key = key;
        this.name = name;
        this.symbol = symbol;
        Geld_API.currencyList.put(key, this);
    }

    public static Currency Currency(NamespacedKey key) {
        return Geld_API.currencyList.get(key);
    }

    public Boolean hasUUID(UUID uuid) {
        if (balance.containsKey(uuid)) {
            return true;
        }
        return false;
    }

    public void remove() {
        CurrencyRemoveEvent e = new CurrencyRemoveEvent(key, name, symbol);
        e.callEvent();
        Geld_API.currencyList.remove(key);
    }

    public Component getName() {
        return name;
    }

    public void setName(Component name) {
        this.name = name;
    }

    public Component getSymbol() {
        return symbol;
    }

    public void setSymbol(Component symbol) {
        this.symbol = symbol;
    }

    public Long get(UUID uuid) {
        if (balance.get(uuid) == null) {
            return 0L;
        }
        return balance.get(uuid);
    }
    public NamespacedKey getKey() {
        return key;
    }

    public void setKey(NamespacedKey key) {
        this.key = key;
    }

    public void set(UUID uuid, Long amount) {
        if (!balance.containsKey(uuid)) {
            balance.put(uuid, 0L);
        }

        BalanceChangeEvent event = new BalanceChangeEvent(this, uuid, BalanceChangeEvent.BalanceChangeType.SET, balance.get(uuid), amount);
        if (event.callEvent()) {
            balance.replace(uuid, amount);
        }
    }

    public void add(UUID uuid, Long amount) {
        if (!balance.containsKey(uuid)) {
            balance.put(uuid, 0L);
        }

        BalanceChangeEvent event = new BalanceChangeEvent(this, uuid, BalanceChangeEvent.BalanceChangeType.ADD, balance.get(uuid), balance.get(uuid) + amount);
        if (event.callEvent()) {
            balance.replace(uuid, balance.get(uuid) + amount);
        }
    }

    @Override
    public @NotNull Map<String, Object> serialize() {
        Map<String, Object> data = new HashMap<>();
        data.put("balance", balance);
        data.put("name", GsonComponentSerializer.gson().serialize(name));
        data.put("symbol", GsonComponentSerializer.gson().serialize(symbol));
        data.put("key", key.asString());
        return data;
    }

    public Currency(Map<String, Object> data) {
        CurrencyCreateEvent e = new CurrencyCreateEvent(key, name, symbol, this);
        e.callEvent();

        balance = (HashMap<UUID, Long>) data.get("balance");
        name = GsonComponentSerializer.gson().deserialize((String) data.get("name"));
        symbol = GsonComponentSerializer.gson().deserialize((String) data.get("symbol"));
        key = NamespacedKey.fromString((String) data.get("key"));
    }
}
