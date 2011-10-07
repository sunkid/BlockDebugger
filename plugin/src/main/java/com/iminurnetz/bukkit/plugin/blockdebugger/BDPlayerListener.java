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

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
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

        if (player.getItemInHand().getType() == Material.STICK) {
            event.setCancelled(true);
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

    @Override
    public void onPlayerGameModeChange(PlayerGameModeChangeEvent event) {
        plugin.log("Game mode changed for " + event.getPlayer().getName() + " " + event.getPlayer().getGameMode() + " > " + event.getNewGameMode());
    }

    @Override
    public void onPlayerChangedWorld(PlayerChangedWorldEvent event) {
        plugin.log(event.getPlayer().getName() + " joined " + event.getPlayer().getWorld().getName() + " coming from " + event.getFrom().getName());
    }
}
