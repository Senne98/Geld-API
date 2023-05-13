package org.super_man2006.geldapi.event.balance;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class BalanceChangeEvent extends BalanceEvent{

    final Long oldBalance;
    final Type type;
    public BalanceChangeEvent(@NotNull Long theBalance, @NotNull UUID uuid, @NotNull Long oldBalance, @NotNull Type type) {
        super(theBalance, uuid);
        this.oldBalance = oldBalance;
        this.type = type;
    }

    public Long getOldBalance() {
        return oldBalance;
    }

    public Type getType() {
        return type;
    }

    public enum Type{
        SET,
        ADD
    }
}
