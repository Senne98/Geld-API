package org.super_man2006.geldapi.event;

public class Event extends java.util.EventObject {
    public Event(Object source) {
        super(source);
    }

    @Override
    public String toString() {
        return "My Custom Event";
    }
}

