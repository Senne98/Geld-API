package org.super_man2006.geldapi.events;

import net.kyori.adventure.text.Component;
import org.bukkit.NamespacedKey;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class CurrencyRemoveEvent extends Event {

    private static final HandlerList HANDLER_LIST = new HandlerList();
    private NamespacedKey key;
    private Component name;
    private Component symbol;

    public CurrencyRemoveEvent(NamespacedKey key, Component name, Component symbol) {
        this.key = key;
        this.name = name;
        this.symbol = symbol;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    public NamespacedKey getKey() {
        return key;
    }

    public Component getName() {
        return name;
    }

    public Component getSymbol() {
        return symbol;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }
}
