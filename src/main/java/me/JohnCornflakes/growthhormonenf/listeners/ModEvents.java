package me.JohnCornflakes.growthhormonenf.listeners;

import me.JohnCornflakes.growthhormonenf.Config;
import me.JohnCornflakes.growthhormonenf.Growthhormonenf;
import me.JohnCornflakes.growthhormonenf.item.ModItems;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;
import net.neoforged.neoforge.items.wrapper.PlayerInvWrapper;

public class ModEvents {

    @SubscribeEvent
    public static void onPlayerAttackEntity(AttackEntityEvent event) {
        Player player = event.getEntity();
        Entity target = event.getTarget();
        boolean creative = false;
        ItemStack mainHand = player.getMainHandItem();
        int mainHandCount = mainHand.getCount();


        if (mainHand.getItem() != ModItems.GROWTH_HORMONE.get()) {
            return;
        }

        if (player.isCreative()) {
            creative = true;
        }

        boolean successfulUse = false;

        if (target instanceof Villager villager) {
            if (villager.isBaby() && Config.worksOnVillagers) {
                villager.setBaby(false);
                successfulUse = true;
            }

        } else if (target instanceof Animal animal) {
            if (animal.isBaby()) {
                animal.setBaby(false);
                successfulUse = true;
            }

        } else if (target instanceof Piglin piglin) {
            if (piglin.isBaby() && Config.worksOnPiglins) {
                piglin.setBaby(false);
                successfulUse = true;
            }

        } else if (target instanceof Zombie zombie) {
            if (zombie.isBaby() && Config.worksOnZombieTypes) {
                zombie.setBaby(false);
                successfulUse = true;
            }

        }

        if (!creative && successfulUse) {
            mainHand.setCount(mainHandCount - 1);
        }

        return;
    }
}
