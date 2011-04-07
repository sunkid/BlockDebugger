package com.iminurnetz.bukkit.plugin.blockdebugger;

import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.entity.EntityListener;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;

public class BDEntityListener extends EntityListener {

    private final BlockDebuggerPlugin plugin;

    public BDEntityListener(BlockDebuggerPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void onCreatureSpawn(CreatureSpawnEvent event) {
    }

    @Override
    public void onEntityCombust(EntityCombustEvent event) {
    }

    @Override
    public void onEntityDamage(EntityDamageEvent event) {
        plugin.log(event.getEntity().getLocation(), event.getEntity().toString() + (event.isCancelled() ? " not" : "") + " damaged by " + event.toString() + "(" + event.getCause() + ") for " + event.getDamage());
    }

    @Override
    public void onEntityExplode(EntityExplodeEvent event) {
    }

    @Override
    public void onExplosionPrime(ExplosionPrimeEvent event) {
    }

    @Override
    public void onEntityDeath(EntityDeathEvent event) {
    }

    @Override
    public void onEntityTarget(EntityTargetEvent event) {
    }

    @Override
    public void onEntityInteract(EntityInteractEvent event) {
    }
}
