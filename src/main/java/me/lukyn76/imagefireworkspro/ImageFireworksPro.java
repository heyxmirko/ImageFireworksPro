package me.lukyn76.imagefireworkspro;

import me.lukyn76.imagefireworkspro.listeners.FireworkExplodeListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class ImageFireworksPro extends JavaPlugin {

    private static ImageFireworksPro instance;

    @Override
    public void onEnable() {
        instance = this;
        createFoldersAndFiles();
        registerListeners();

        getLogger().info("Plugin has been loaded!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin has been unloaded!");
    }

    private void createFoldersAndFiles() {
        saveDefaultConfig();
        if (!new File(getDataFolder(), "images").exists()) {
            new File(getDataFolder(), "images").mkdir();
        }
    }


    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new FireworkExplodeListener(), this);
        getLogger().info("Listeners registered!");
    }

    public static ImageFireworksPro getInstance() {
        return instance;
    }
}
