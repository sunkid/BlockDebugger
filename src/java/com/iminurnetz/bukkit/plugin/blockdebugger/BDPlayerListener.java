package com.iminurnetz.bukkit.plugin.blockdebugger;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.material.Diode;
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
        
        if (md instanceof Diode) {
            Diode d = new Diode(Material.DIODE_BLOCK_OFF);
            for (BlockFace f : Arrays.asList(BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST)) {
                d.setFacingDirection(f);
                d.setDelay(1);
                plugin.log("Setting direction to " + f + " gives " + d);
                for (int delay = 0; delay < 6; delay++) {
                    d.setDelay(delay);
                    plugin.log("Setting delay to " + delay + " results in " + d);
                }
            }
        }
    }
}
