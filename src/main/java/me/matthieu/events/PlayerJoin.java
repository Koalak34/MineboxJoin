package me.matthieu.events;

import com.viaversion.viaversion.api.Via;
import com.viaversion.viaversion.api.ViaAPI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.List;

public class PlayerJoin implements Listener {

    ViaAPI api = Via.getAPI();

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        int version = api.getPlayerVersion(p);
        if(version == 759 || version == 760 || version == 761 && p.hasPlayedBefore()){
            p.getInventory().setItem(4, createBook(p));
            p.getInventory().setHeldItemSlot(4);

            p.openBook(p.getInventory().getItemInMainHand());
        }

    }

    public ItemStack createBook(Player player) {
        var item = new ItemStack(Material.WRITTEN_BOOK, 1);
        var meta = (BookMeta) item.getItemMeta();

        meta.setDisplayName("§eConseil");
        meta.setAuthor("§l"+player.getName());
        meta.setTitle("Chasse au trésor");

        var mainPage = "§lBienvenue " + player.getName() + "\n\n§bVersion conseillée: 1.18.2";

        meta.setPages(List.of(mainPage));
        item.setItemMeta(meta);

        return item;
    }


}
