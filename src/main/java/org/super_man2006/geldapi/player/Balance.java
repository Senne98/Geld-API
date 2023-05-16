package org.super_man2006.geldapi.player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.super_man2006.geldapi.Geld_API;
import org.super_man2006.geldapi.event.EventListener;
import org.super_man2006.geldapi.event.balance.BalanceChangeEvent;

import java.util.HashMap;
import java.util.UUID;

public class Balance implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        UUID uuid = player.getUniqueId();
        if (Geld_API.balance == null) {
            HashMap<UUID, Long> temp = new HashMap<>();
            temp.put(uuid, 0L);
            Geld_API.balance = temp;
            return;
        }
        if (Geld_API.balance.get(uuid) != null && Geld_API.balance.containsKey(uuid)) {
            return;
        }
        Geld_API.balance.put(uuid, 0L);
    }

    public static Long get(UUID uuid) {
        if (Geld_API.balance.get(uuid) == null) {
            return 0L;
        }
        return Geld_API.balance.get(uuid);
    }

    public static void set(UUID uuid, Long amount) {
        EventListener.fireBalanceChangeEvent(new BalanceChangeEvent(amount, uuid, Geld_API.balance.get(uuid), BalanceChangeEvent.Type.SET));
        Geld_API.balance.replace(uuid, amount);
    }

    public static void add(UUID uuid, Long amount) {
        new BalanceChangeEvent(amount, uuid, Geld_API.balance.get(uuid), BalanceChangeEvent.Type.ADD);
        Geld_API.balance.replace(uuid, Geld_API.balance.get(uuid) + amount);
    }
}
