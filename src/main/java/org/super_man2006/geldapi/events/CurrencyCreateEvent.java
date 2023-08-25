package org.super_man2006.geldapi.events;

import net.kyori.adventure.text.Component;
import org.bukkit.NamespacedKey;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.super_man2006.geldapi.currency.Currency;

public class CurrencyCreateEvent extends Event {

    private static final HandlerList HANDLER_LIST = new HandlerList();
    private NamespacedKey key;
    private Component name;
    private Component symbol;
    private Currency currency;

    public CurrencyCreateEvent(NamespacedKey key, Component name, Component symbol, Currency currency) {
        this.key = key;
        this.name = name;
        this.symbol = symbol;
        this.currency = currency;
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

    public Currency getCurrency() {
        return currency;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }
}
