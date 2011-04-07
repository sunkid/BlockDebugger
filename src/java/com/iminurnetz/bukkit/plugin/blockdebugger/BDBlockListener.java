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
    }

    @Override
    public void onBlockRedstoneChange(BlockRedstoneEvent event) {
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
    }

}
