package me.lukyn76.imagefireworkspro;

import me.lukyn76.imagefireworkspro.commands.CommandHandler;
import me.lukyn76.imagefireworkspro.listeners.FireworkExplodeListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class ImageFireworksPro extends JavaPlugin {

    private static ImageFireworksPro instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        createFoldersAndFiles();
        registerListeners();
        registerCommands();

        getLogger().info("Plugin has been loaded!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin has been unloaded!");
    }

    private void createFoldersAndFiles() {
        if (!new File(getDataFolder(), "images").exists()) {
            new File(getDataFolder(), "images").mkdir();
        }

        File exampleImage = new File(getDataFolder(), "images/dog.png");
        if (!exampleImage.exists()) {
            saveResource("images/dog.png", false);
        }
    }


    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new FireworkExplodeListener(), this);
        getLogger().info("Listeners registered!");
    }

    private void registerCommands() {
        this.getCommand("imagefireworkspro").setExecutor(new CommandHandler());
        this.getCommand("imagefireworkspro").setTabCompleter(new CommandHandler());
    }

    public static ImageFireworksPro getInstance() {
        return instance;
    }
}
