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
