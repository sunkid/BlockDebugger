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

import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockCanBuildEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.block.SignChangeEvent;

public class BDBlockListener extends BlockListener {

    private final BlockDebuggerPlugin plugin;

    public BDBlockListener(BlockDebuggerPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void onBlockDamage(BlockDamageEvent event) {
    }

    @Override
    public void onBlockCanBuild(BlockCanBuildEvent event) {
    }

    @Override
    public void onBlockFromTo(BlockFromToEvent event) {
    }

    @Override
    public void onBlockIgnite(BlockIgniteEvent event) {
    }

    @Override
    public void onBlockPhysics(BlockPhysicsEvent event) {
    }

    @Override
    public void onBlockPlace(BlockPlaceEvent event) {
        plugin.log(event.getPlayer() + " " + event.getEventName() + " " + event.getBlock().getState().getData());
        plugin.dumpStack();
    }

    @Override
    public void onBlockRedstoneChange(BlockRedstoneEvent event) {
        plugin.log("RedstoneEvent (" + event.getOldCurrent() + " -> " + event.getNewCurrent() + ") at: " + event.getBlock());
    }

    @Override
    public void onLeavesDecay(LeavesDecayEvent event) {
    }

    @Override
    public void onSignChange(SignChangeEvent event) {
    }

    @Override
    public void onBlockBurn(BlockBurnEvent event) {
    }

    @Override
    public void onBlockBreak(BlockBreakEvent event) {
        plugin.log("Block " + (event.isCancelled() ? "not " : "") + "broken: " + event.getBlock());
    }

}
