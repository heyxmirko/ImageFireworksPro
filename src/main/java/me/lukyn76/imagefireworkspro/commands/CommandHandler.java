package me.lukyn76.imagefireworkspro.commands;

import me.lukyn76.imagefireworkspro.ImageFireworksPro;
import me.lukyn76.imagefireworkspro.core.ImageFirework;
import me.lukyn76.imagefireworkspro.util.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CommandHandler implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {


        switch (args[0].toLowerCase()) {
            case "reload":
                ConfigManager.reloadConfig();
                sender.sendMessage("§aConfig reloaded!");
                break;
            case "give":
                if (args.length < 2) {
                    sender.sendMessage("§cUsage: /imagefireworkspro give <player> <firework> <amount>");
                    break;
                }

                Player player = ImageFireworksPro.getInstance().getServer().getPlayer(args[1]);
                if (player == null) {
                    sender.sendMessage("§cPlayer not found!");
                    break;
                }

                String fireworkName = args[2];
                ImageFirework imageFirework = ConfigManager.getImageFirework(fireworkName);
                if (imageFirework != null) {

                    int amount = 1;
                    if (args.length == 4) {
                        amount = Integer.parseInt(args[3]);
                    }
                    ItemStack firework = new ItemStack(Material.FIREWORK_ROCKET, amount);
                    FireworkMeta fireworkMeta = (FireworkMeta) firework.getItemMeta();

                    fireworkMeta.setDisplayName(imageFirework.getName());
                    fireworkMeta.setCustomModelData(imageFirework.getCustomModelData());

                    FireworkEffect effect = FireworkEffect.builder()
                            .with(FireworkEffect.Type.BURST)
                            .withColor(Color.BLACK)
                            .build();
                    fireworkMeta.addEffect(effect);

                    if (imageFirework.getFlightDuration() > 0) {
                        fireworkMeta.setPower(imageFirework.getFlightDuration());
                    }

                    firework.setItemMeta(fireworkMeta);

                    player.getInventory().addItem(firework);
                    sender.sendMessage("§aFirework given!");

                } else {
                    sender.sendMessage("§cFirework not found!");
                }
                break;

            default:
                sender.sendMessage("§cUsage: /imagefireworkspro <action>");
                break;
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
        if (args.length == 1) {
            return List.of("reload", "give");
        } else if (args.length == 2) {
            return new ArrayList<>(Bukkit.getOnlinePlayers().stream().map(Player::getName).toList());

        } else if (args.length == 3) {
            return new ArrayList<>(Objects.requireNonNull(ConfigManager.getAvailableImageFireworks()));
        }else if (args.length == 4) {
            return List.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
        }
        return null;
    }
}
