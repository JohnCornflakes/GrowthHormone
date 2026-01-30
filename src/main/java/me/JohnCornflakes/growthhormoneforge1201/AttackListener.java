package me.JohnCornflakes.growthhormoneforge1201;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "growthhormoneforge", bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class AttackListener {
    @SubscribeEvent
    public static void onPlayerAttackEntity(AttackEntityEvent event) {
        Player player = event.getEntity();
        Entity entity = event.getTarget();
        ItemStack mainHand = player.getMainHandItem();

        if (mainHand.getItem() != Growthhormoneforge1201.GROWTH_HORMONE.get()) {
            return;
        }

        boolean successfulUse = false;
        boolean isCreative = player.isCreative();

        if (entity instanceof Villager villager && Config.worksOnVillagers) {
            if (villager.isBaby()) {
                villager.setBaby(false);
                successfulUse = true;
            }
        } else if (entity instanceof Animal animal) {
            if (animal.isBaby()) {
                animal.setBaby(false);
                successfulUse = true;
            }
        } else if (entity instanceof Zombie zombie && Config.worksOnZombieTypes) {
            if (zombie.isBaby()) {
                zombie.setBaby(false);
                successfulUse = true;
            }
        } else if (entity instanceof Piglin piglin && Config.worksOnPiglins) {
            if (piglin.isBaby()) {
                piglin.setBaby(false);
                successfulUse = true;
            }
        }

        if (successfulUse && !isCreative) {
            mainHand.setCount(mainHand.getCount() - 1);
        }


    }


}
