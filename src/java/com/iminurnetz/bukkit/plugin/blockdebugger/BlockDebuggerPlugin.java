package com.iminurnetz.bukkit.plugin.blockdebugger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;

import com.iminurnetz.bukkit.plugin.BukkitPlugin;
import com.iminurnetz.bukkit.plugin.util.MessageUtils;
import com.iminurnetz.bukkit.util.Item;
import com.iminurnetz.util.StringUtils;

public class BlockDebuggerPlugin extends BukkitPlugin {

    @Override
    public void enablePlugin() throws Exception {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvent(Type.PLAYER_INTERACT, new BDPlayerListener(this), Priority.Monitor, this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) {
            MessageUtils.send(sender, "Command only available in-game");
            return true;
        }
        
        Player player = (Player) sender;
        
        if (command.getName().equalsIgnoreCase("get")) {
            if (args.length == 0) {
                return false;
            }

            int n = 1;
            int words = args.length - 1;
            String itemString = StringUtils.join(" ", args);
            Item item = null;
            if (words > 0) {
                try {
                    n = Integer.valueOf(args[words]);
                } catch (NumberFormatException e) {
                    // ignored
                }
                
                itemString = itemString.replace(" " + n, "");
            }
            
            try {
                item = new Item(itemString);
            } catch (InstantiationException e) {
                return false;
            }
            
            byte d = 0;
            if (item.getData() != null) {
                d = item.getData().getData();
            }
            
            ItemStack stack = new ItemStack(item.getMaterial(), n, (short) 0, d);
            player.getWorld().dropItemNaturally(player.getLocation(), stack);
        }
        return true;
    }
}