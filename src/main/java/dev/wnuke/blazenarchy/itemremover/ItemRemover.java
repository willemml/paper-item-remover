package dev.wnuke.blazenarchy.itemremover;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

/**
 * Blazenarchy's Item Remover, automatically removes items from player inventories.
 *
 * @author wnuke
 */

public final class ItemRemover extends JavaPlugin {

    public static final String VERSION = "1.0.0";
    public static final String PREFIX = ChatColor.RED + "[" + ChatColor.AQUA + "ItemRemover" + ChatColor.RED + "]" + ChatColor.WHITE + " ";
    public static FileConfiguration CONFIG;
    public String[] itemsStr;
    public static ArrayList<ItemStack> itemsArr = new ArrayList<>();

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        this.getCommand("itemremover").setExecutor(new ItemRemoverCommand());
        CONFIG = this.getConfig();
        itemsStr = CONFIG.getStringList("itemsToRemove").toArray(new String[0]);
        itemsArr.clear();
        for (String i : itemsStr) {
            try {
                itemsArr.add(new ItemStack(Material.getMaterial(i)));
            } catch (NullPointerException err) {
                getLogger().info(i + " is not a valid item.");
            }
        }
        getServer().getPluginManager().registerEvents(new Remover(), this);
        getLogger().info("Loaded Item Remover version " + VERSION + " by wnuke.");
    }

    @Override
    public void onDisable() {
        getLogger().info("Disabling Item Remover...");
    }

    private class ItemRemoverCommand implements CommandExecutor {
        @Override
        public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            sender.sendMessage(PREFIX + "List of items that will be removed:");
            if (itemsStr.length > 0) {
                for (String item : itemsStr) {
                    sender.sendMessage("  - " + item);
                }
            } else {
                sender.sendMessage("  There are no items in the list.");
            }
            return true;
        }
    }
}
