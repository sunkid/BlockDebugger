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

import java.util.Arrays;
import java.util.logging.Level;

import org.bukkit.Instrument;
import org.bukkit.Material;
import org.bukkit.Note;
import org.bukkit.Note.Tone;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerInventoryEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.material.MaterialData;

import com.iminurnetz.bukkit.plugin.util.MessageUtils;
import com.iminurnetz.bukkit.util.LocationUtil;

public class BDPlayerListener extends PlayerListener {

    private final BlockDebuggerPlugin plugin;

    public BDPlayerListener(BlockDebuggerPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void onPlayerInteract(PlayerInteractEvent event) {
        final Player player = event.getPlayer();

        final Block block = event.getClickedBlock();

        if (block == null)
            return;

        if (block.getType() == Material.NOTE_BLOCK && event.getAction() == Action.LEFT_CLICK_BLOCK) {
            
            Thread t = new Thread(new Runnable() {
                public void run() {
                    for (byte n = 0; n <= 24; n++) {
                        Note note = new Note(n);
                        MessageUtils.send(player, "Note is " + note.getTone() + (note.isSharped() ? "#" : ""));
                        player.playNote(block.getLocation(), Instrument.PIANO, note);
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }

                    byte b = 0;
                    for (byte octave = 0; octave < 3; octave++) {
                        for (Tone t : Tone.values()) {
                            boolean sharp = false;
                            for (int n = 0; n < 2; n++) {
                                try {
                                    Note note = new Note(octave, t, sharp);
                                    MessageUtils.send(player, "Note is " + note.getTone() + (note.isSharped() ? "#" : "") + note.getOctave() + " v. " + t + (sharp ? "#" : "") + octave);
                                    player.playNote(block.getLocation(), Instrument.PIANO, note);
                                    try {
                                        Thread.sleep(100);
                                    } catch (InterruptedException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                    }

                                    note = new Note(b++);

                                    MessageUtils.send(player, "Note is " + note.getTone() + (note.isSharped() ? "#" : ""));
                                    player.playNote(block.getLocation(), Instrument.PIANO, note);
                                    try {
                                        Thread.sleep(200);
                                    } catch (InterruptedException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                    }
                                } catch (Exception e) {
                                    plugin.log(Level.SEVERE, "error: " + t + (sharp ? "#" : "") + octave, e);
                                }

                                sharp = !sharp;
                            }
                        }
                    }

                }
            });
            t.start();
            event.setCancelled(true);
            return;
        }

        BlockFace face = event.getBlockFace();
        Material m = block.getType();
        String type = m + "(" + block.getData() + ")";
        MaterialData md = m.getNewData(block.getData());
        if (md != null) {
            type = md.toString();
        }

        plugin.log(player.getLocation().toString() + " facing " + LocationUtil.getDirection(player.getLocation()));
        plugin.log(player.getName() + " " + event.getAction() + " " + type + " at " + block.getLocation() + " on " + face);

        if (block.isBlockPowered()) {
            String indirect = block.isBlockIndirectlyPowered() ? " indirectly " : "";
            plugin.log("Block is " + indirect + "powered to " + block.getBlockPower());
            for (BlockFace f : Arrays.asList(BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST, BlockFace.UP, BlockFace.DOWN)) {
                if (block.isBlockFacePowered(f)) {
                    plugin.log("Power on " + f + " is " + block.getBlockPower(f));
                }
            }
        }

    }

    @Override
    public void onPlayerJoin(PlayerJoinEvent event) {
    }

    @Override
    public void onPlayerQuit(PlayerQuitEvent event) {
    }

    @Override
    public void onPlayerKick(PlayerKickEvent event) {
    }

    @Override
    public void onPlayerChat(PlayerChatEvent event) {
    }

    @Override
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
    }

    @Override
    public void onPlayerMove(PlayerMoveEvent event) {
    }

    @Override
    public void onPlayerTeleport(PlayerTeleportEvent event) {
    }

    @Override
    public void onPlayerRespawn(PlayerRespawnEvent event) {
    }

    @Override
    public void onPlayerLogin(PlayerLoginEvent event) {
    }

    @Override
    public void onPlayerEggThrow(PlayerEggThrowEvent event) {
    }

    @Override
    public void onPlayerAnimation(PlayerAnimationEvent event) {
    }

    @Override
    public void onInventoryOpen(PlayerInventoryEvent event) {
    }

    @Override
    public void onItemHeldChange(PlayerItemHeldEvent event) {
    }

    @Override
    public void onPlayerDropItem(PlayerDropItemEvent event) {
    }

    @Override
    public void onPlayerPickupItem(PlayerPickupItemEvent event) {
    }

    @Override
    public void onPlayerToggleSneak(PlayerToggleSneakEvent event) {
    }

    @Override
    public void onPlayerBucketFill(PlayerBucketFillEvent event) {
    }

    @Override
    public void onPlayerBucketEmpty(PlayerBucketEmptyEvent event) {
    }
}
