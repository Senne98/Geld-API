package org.super_man2006.geldapi.event;

import org.super_man2006.geldapi.event.balance.BalanceChangeEvent;

public interface Listener extends java.util.EventListener {
    void EventOccurred(Event evt);

    void EventOccurred(BalanceChangeEvent evt);
}

