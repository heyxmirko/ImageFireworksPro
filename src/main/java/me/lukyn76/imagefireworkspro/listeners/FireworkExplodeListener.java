package me.lukyn76.imagefireworkspro.listeners;

import me.lukyn76.imagefireworkspro.core.ImageFirework;
import me.lukyn76.imagefireworkspro.util.ConfigManager;
import org.bukkit.Location;
import org.bukkit.entity.Firework;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FireworkExplodeEvent;
import org.bukkit.inventory.meta.FireworkMeta;

import java.io.IOException;

public class FireworkExplodeListener implements Listener {


    @EventHandler
    public void onFireworkExplode(FireworkExplodeEvent event) throws IOException {
        Firework firework = event.getEntity();
        FireworkMeta fireworkMeta = firework.getFireworkMeta();
        if (!fireworkMeta.hasCustomModelData()) return;
        int customModelData = fireworkMeta.getCustomModelData();

        ImageFirework imageFirework = ConfigManager.getImageFirework(customModelData);
        if (imageFirework == null) return;

        Location explodeLocation = event.getEntity().getLocation();
        double yawRotation = explodeLocation.getYaw();

        imageFirework.explode(explodeLocation, yawRotation);
    }
}
