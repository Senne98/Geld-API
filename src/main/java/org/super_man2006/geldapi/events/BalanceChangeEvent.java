package org.super_man2006.geldapi.events;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.super_man2006.geldapi.currency.Currency;

import java.util.UUID;

public class BalanceChangeEvent extends Event implements Cancellable {
    private static final HandlerList HANDLER_LIST = new HandlerList();
    private Currency currency;
    private UUID uuid;
    private BalanceChangeType type;
    private Long oldBalance;
    private Long newBalance;
    private boolean cancelled;

    public BalanceChangeEvent(Currency currency, UUID uuid, BalanceChangeType type, Long OldBalance, Long newBalance) {
        this.currency = currency;
        this.uuid = uuid;
        this.type = type;
        this.oldBalance = OldBalance;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public Currency getCurrency() {
        return currency;
    }

    public BalanceChangeType getType() {
        return type;
    }

    public Long getOldBalance() {
        return oldBalance;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Long getNewBalance() {
        return newBalance;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public enum BalanceChangeType {
        SET,
        ADD
    }
}
