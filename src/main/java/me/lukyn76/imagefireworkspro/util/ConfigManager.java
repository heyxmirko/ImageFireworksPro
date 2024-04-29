package me.lukyn76.imagefireworkspro.util;

import me.lukyn76.imagefireworkspro.ImageFireworksPro;
import me.lukyn76.imagefireworkspro.core.ImageFirework;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;
import java.util.Set;

public class ConfigManager {

    static ImageFireworksPro plugin = ImageFireworksPro.getInstance();


    public static ImageFirework getImageFirework(int customModelData) {
        ConfigurationSection fireworksSection = plugin.getConfig().getConfigurationSection("Fireworks");
        if (fireworksSection == null) return null; // No fireworks configuration found

        for (String key : fireworksSection.getKeys(false)) {
            ConfigurationSection fireworkSection = fireworksSection.getConfigurationSection(key);

            if (fireworkSection != null && fireworkSection.getInt("customModelData") == customModelData) {
                ImageFirework firework = new ImageFirework();
                firework.setName(fireworkSection.getString("name"));
                firework.setImageName(fireworkSection.getString("imageName"));
                firework.setCustomModelData(fireworkSection.getInt("customModelData"));

                return firework;
            }
        }
        return null;
    }

    public static ImageFirework getImageFirework(String id) {
        ConfigurationSection fireworksSection = plugin.getConfig().getConfigurationSection("Fireworks");
        if (fireworksSection == null) return null; // No fireworks configuration found
        ConfigurationSection fireworkSection = fireworksSection.getConfigurationSection(id);
        if (fireworkSection == null) return null;

        ImageFirework firework = new ImageFirework();
        firework.setName(fireworkSection.getString("name"));
        firework.setImageName(fireworkSection.getString("imageName"));
        firework.setCustomModelData(fireworkSection.getInt("customModelData"));

        return firework;
    }

    public static Set<String> getAvailableImageFireworks() {
        ConfigurationSection fireworksSection = plugin.getConfig().getConfigurationSection("Fireworks");
        if (fireworksSection == null) return null; // No fireworks configuration found
        return fireworksSection.getKeys(false);
    }

    public static void reloadConfig() {
        plugin.reloadConfig();
    }
}
