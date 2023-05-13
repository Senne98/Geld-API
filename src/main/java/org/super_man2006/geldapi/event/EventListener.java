package org.super_man2006.geldapi.event;

import org.super_man2006.geldapi.event.balance.BalanceChangeEvent;

import java.util.ArrayList;
import java.util.List;

public class EventListener {
    private static List<Listener> listeners = new ArrayList<Listener>();

    public static void addCustomListener(Listener listener) {
        listeners.add(listener);
    }

    public static void removeCustomListener(Listener listener) {
        listeners.remove(listener);
    }

    public static void fireEvent(Event event) {
        for (Listener listener : listeners) {
            listener.EventOccurred(event);
        }
    }

    public static void fireBalanceChangeEvent(BalanceChangeEvent event) {
        for (Listener listener : listeners) {
            listener.EventOccurred(event);
        }
    }
}
