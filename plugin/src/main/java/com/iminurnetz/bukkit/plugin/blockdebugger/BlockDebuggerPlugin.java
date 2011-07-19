/**
 * LICENSING
 * 
 * This software is copyright by sunkid <sunkid@iminurnetz.com> and is
 * distributed under a dual license:
 * 
 * Non-Commercial Use:
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * Commercial Use:
 *    Please contact sunkid@iminurnetz.com
 */
package com.iminurnetz.bukkit.plugin.blockdebugger;

import java.util.logging.Level;

import org.bukkit.Instrument;
import org.bukkit.Location;
import org.bukkit.Note;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Category;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.PluginManager;

import com.iminurnetz.bukkit.plugin.BukkitPlugin;
import com.iminurnetz.bukkit.plugin.util.MessageUtils;
import com.iminurnetz.bukkit.util.InventoryUtil;
import com.iminurnetz.bukkit.util.Item;
import com.iminurnetz.bukkit.util.LocationUtil;
import com.iminurnetz.util.StringUtils;

public class BlockDebuggerPlugin extends BukkitPlugin {

    @Override
    public void enablePlugin() throws Exception {
        PluginManager pm = getServer().getPluginManager();
        for (Type t : Type.values()) {
            Category c = t.getCategory();
            try {
                switch (c) {
                case PLAYER:
                    pm.registerEvent(t, new BDPlayerListener(this), Priority.Monitor, this);
                    break;
                case BLOCK:
                    pm.registerEvent(t, new BDBlockListener(this), Priority.Monitor, this);
                    break;
                case LIVING_ENTITY:
                    pm.registerEvent(t, new BDEntityListener(this), Priority.Monitor, this);
                    break;
                case VEHICLE:
                    break;
                case SERVER:
                    break;
                case WORLD:
                    break;
                }
            } catch (Exception e) {
                log(Level.INFO, t.name() + " is not supported!", e);
            }
        }
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
                
                itemString = itemString.replace(" " + n, ":" + n);
            }
            
            try {
                item = new Item(itemString);
            } catch (InstantiationException e) {
                return false;
            }
            
            InventoryUtil.giveItems(player, item.getStack());
        } else if (command.getName().equalsIgnoreCase("focus")) {
            toggleFocus();
            this.currentPlayer = player;
        } else if (command.getName().equalsIgnoreCase("heal")) {
            player.setHealth(20);
        } else if (command.getName().equalsIgnoreCase("tone")) {
            MessageUtils.send(player, "Playing note #" + args[0]);
            Note note = new Note(Byte.parseByte(args[0]));
            MessageUtils.send(player, "Note is " + note.getTone());
            player.playNote(player.getLocation(), Instrument.PIANO, note);
        }
        
        return true;
    }
    
    private Player currentPlayer;
    private boolean focused = false;
    
    private final double FOCUS_DISTANCE = 20D;
    
    private void toggleFocus() {
        focused = !focused;
    }
    
    protected void log(Location loc, String msg) {
        if (focused) {
            if (currentPlayer != null) {
                Location pLoc = currentPlayer.getLocation();
                if (LocationUtil.distance(loc, pLoc) < FOCUS_DISTANCE) {
                    log(msg);
                }
            }
        } else {
            log(msg);
        }
    }
}