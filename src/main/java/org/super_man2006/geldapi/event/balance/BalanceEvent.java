package org.super_man2006.geldapi.event.balance;

import org.jetbrains.annotations.NotNull;
import org.super_man2006.geldapi.event.Event;

import java.util.UUID;

/**
 * Represents a balance related event.
 */
public abstract class BalanceEvent extends Event {
    protected Long balance;

    public BalanceEvent(@NotNull final Long theBalance, @NotNull UUID theUuid) {
        super(theUuid);
        balance = theBalance;
    }

    public final UUID getUuid() {
        return (UUID) super.getSource();
    }

    /**
     * Gets the Balance involved in this event.
     *
     * @return The Balance involved in this event
     */
    @NotNull
    public final Long getBalance() {
        return balance;
    }
}