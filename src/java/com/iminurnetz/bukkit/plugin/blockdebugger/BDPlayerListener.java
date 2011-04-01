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
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.material.MaterialData;

public class BDPlayerListener extends PlayerListener {

    private final BlockDebuggerPlugin plugin;

    public BDPlayerListener(BlockDebuggerPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block block = event.getClickedBlock();
        BlockFace face = event.getBlockFace();
        Material m = block.getType();
        String type = m + "(" + block.getData() + ")";
        MaterialData md = m.getNewData(block.getData());
        if (md != null) {
            type = md.toString();
        }

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
}
