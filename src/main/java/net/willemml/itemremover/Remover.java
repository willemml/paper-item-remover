package net.willemml.itemremover;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Remover implements Listener {
    @EventHandler
    public void event(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPermission("itemremover.ignored")) {
            checkInv(player.getInventory());
        }
    }

    public void checkInv(Inventory inventory) {
        for (ItemStack ri : ItemRemover.itemsArr) {
            if (inventory.contains(ri.getType())) {
                inventory.remove(ri.getType());
            }
        }
    }
}
