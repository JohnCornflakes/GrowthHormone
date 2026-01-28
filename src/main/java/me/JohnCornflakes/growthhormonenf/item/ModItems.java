package me.JohnCornflakes.growthhormonenf.item;

import me.JohnCornflakes.growthhormonenf.Growthhormonenf;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Growthhormonenf.MODID);

    public static final DeferredItem<Item> GROWTH_HORMONE = ITEMS.register("growth_hormone",
            () -> new Item(new Item.Properties()));



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
