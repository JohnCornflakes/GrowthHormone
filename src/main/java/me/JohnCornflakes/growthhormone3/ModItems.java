package me.JohnCornflakes.growthhormone3;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModItems {
    public static final Identifier growthHormoneId = Identifier.of(Growthhormone3.MOD_ID, "growth_hormone");
    public static final RegistryKey<Item> growthHormoneKey = RegistryKey.of(RegistryKeys.ITEM, growthHormoneId);
    public static final Item.Settings growthHormoneSettings = new Item.Settings().registryKey(growthHormoneKey);
    public static final Item GROWTH_HORMONE = registerItem(growthHormoneKey, growthHormoneSettings);



    public static Item registerItem(RegistryKey<Item> itemRegistryKey, Item.Settings itemSettings) {
        return Registry.register(Registries.ITEM, itemRegistryKey, new Item(itemSettings));
    }



    public static void registerModItems() {
        Growthhormone3.LOGGER.info("Registering items for Growth Hormone mod...");
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries -> {
            entries.add(GROWTH_HORMONE);
        });
    }
}
