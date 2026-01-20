package me.JohnCornflakes.growthhormone3;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item GROWTH_HORMONE = registerItem("growth_hormone", new Item(new Item.Settings()));

    public static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Growthhormone3.MOD_ID, name), item);
    }


    public static void registerModItems() {
        Growthhormone3.LOGGER.info("Registering items for Growth Hormone mod...");
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries -> {
            entries.add(GROWTH_HORMONE);
        });
    }
}
