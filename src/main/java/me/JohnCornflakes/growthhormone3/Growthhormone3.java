package me.JohnCornflakes.growthhormone3;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.mob.PiglinEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.passive.DolphinEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Growthhormone3 implements ModInitializer {

    public static final String MOD_ID = "growthhormone";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static ModConfig CONFIG;

    @Override
    public void onInitialize() {
        LOGGER.info("Initializing Growth Hormone mod for fabric 1.21.0...");
        ModItems.registerModItems();

        CONFIG = ModConfig.load();

        AttackEntityCallback.EVENT.register(((playerEntity, world, hand, entity, entityHitResult) -> {
            if (entity.getEntityWorld().isClient()) {
                return ActionResult.PASS;
            }
            if (playerEntity.getMainHandStack().getItem() != ModItems.GROWTH_HORMONE) {
                return ActionResult.PASS;
            }
            boolean playerInCreative = playerEntity.isCreative();
            boolean successfulUse = false;
            if (entity instanceof VillagerEntity) {
                VillagerEntity vEntity = (VillagerEntity) entity;
                if (Growthhormone3.CONFIG.worksOnVillagers && vEntity.isBaby()) {
                    vEntity.setBaby(false);
                    successfulUse = true;
                }
            } else if (entity instanceof PassiveEntity) {
                if (((PassiveEntity) entity).isBaby()) {
                    ((PassiveEntity) entity).setBaby(false);
                    successfulUse = true;
                }
            } else if (entity instanceof ZombieEntity) {
                ZombieEntity zEntity = (ZombieEntity) entity;
                if (Growthhormone3.CONFIG.worksOnZombieTypes && zEntity.isBaby()) {
                    zEntity.setBaby(false);
                    successfulUse = true;
                }
            } else if (entity instanceof PiglinEntity) {
                PiglinEntity pEntity = (PiglinEntity) entity;
                if (Growthhormone3.CONFIG.worksOnPiglins && pEntity.isBaby()) {
                    pEntity.setBaby(false);
                    successfulUse = true;
                }
            }

            if (successfulUse && !playerInCreative) {
                ItemStack gh = playerEntity.getMainHandStack();
                gh.setCount(gh.getCount()-1);
            }
            return ActionResult.PASS;
        }));

    }


}
