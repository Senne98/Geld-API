package org.super_man2006.geldapi.currency;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.super_man2006.geldapi.Geld_API;

import java.util.UUID;

public class FirstJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        UUID uuid = player.getUniqueId();

        Geld_API.currencyList.forEach((key, currency) -> {
            if (currency.hasUUID(uuid)) {
                return;
            }
            currency.set(uuid, 0L);
        });
    }
}
