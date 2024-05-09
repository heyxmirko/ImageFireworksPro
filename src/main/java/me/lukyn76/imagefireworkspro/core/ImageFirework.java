package me.lukyn76.imagefireworkspro.core;

import me.lukyn76.imagefireworkspro.ImageFireworksPro;
import org.bukkit.*;
import org.bukkit.scheduler.BukkitTask;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class ImageFirework {

    private String name;
    private String imageName;
    private int customModelData;
    private int displayTime;
    private int flightDuration;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public int getCustomModelData() {
        return customModelData;
    }

    public void setCustomModelData(int customModelData) {
        this.customModelData = customModelData;
    }

    public int getDisplayTime() {
        return displayTime;
    }

    public void setDisplayTime(int displayTime) {
        this.displayTime = displayTime;
    }

    public int getFlightDuration() {
        return flightDuration;
    }

    public void setFlightDuration(int flightDuration) {
        this.flightDuration = flightDuration;
    }

    public void explode(Location location, double yawRotation) throws IOException {
        displayImage(location, yawRotation);
    }



    private void displayImage(Location explodeLocation, double yawRotation) throws IOException {
        ImageFireworksPro plugin = ImageFireworksPro.getInstance();

        File imageFile = new File(plugin.getDataFolder(), "images/"+imageName);
        if (!imageFile.exists()) {
            plugin.getLogger().warning("Image file not found: " + imageName);
            return;
        }


        BufferedImage image = ImageIO.read(imageFile);
        int width = image.getWidth();
        int height = image.getHeight();

        double yawRadians = Math.toRadians(yawRotation);

        BukkitTask taskId = Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, () -> {
            for(int x = 0; x < width; x++) {
                for(int y = 0; y < height; y++) {
                    int color = image.getRGB(x, y);
                    int alpha = (color >> 24) & 0xff;
                    if(alpha != 0) {
                        int red = (color >> 16) & 0xff;
                        int green = (color >> 8) & 0xff;
                        int blue = color & 0xff;

                        double offsetX = x / 10.0 - width / 20.0;
                        double offsetY = -y / 10.0 + height / 10.0 / 4.0;
                        double offsetZ = 0.0;

                        double rotatedX = offsetX * Math.cos(yawRadians) - offsetZ * Math.sin(yawRadians);
                        double rotatedZ = offsetX * Math.sin(yawRadians) + offsetZ * Math.cos(yawRadians);

                        Location location = explodeLocation.clone().add(rotatedX, offsetY, rotatedZ);
                        Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB(red, green, blue), 1);
                        Objects.requireNonNull(explodeLocation.getWorld()).spawnParticle(Particle.REDSTONE, location, 0, 0, 0, 0, dust);
                    }
                }
            }
        }, 0L, 3L);

        Bukkit.getScheduler().runTaskLater(plugin, taskId::cancel, displayTime * 20L);

    }
}
