package magicalsculpture;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import scala.collection.mutable.HashTable;

@Mod.EventBusSubscriber(modid = MagicalSculpture.MODID)
public class LootTables {
    private final static ResourceLocation dungeon = new ResourceLocation(MagicalSculpture.MODID,"inject/simple_dungeon");
    private final static ResourceLocation nether_bridge = new ResourceLocation(MagicalSculpture.MODID,"inject/nether_bridge");
    private final static ResourceLocation blaze = new ResourceLocation(MagicalSculpture.MODID,"inject/blaze");
    private final static ResourceLocation elder_guardian = new ResourceLocation(MagicalSculpture.MODID,"inject/elder_guardian");
    private final static ResourceLocation fishing = new ResourceLocation(MagicalSculpture.MODID,"inject/fishing");

    private static LootPool loadLootPool(ResourceLocation resLootTable) {
        String[] strings = resLootTable.getResourcePath().split("/");
        String entryName = MagicalSculpture.MODID + "_" +strings[strings.length-1];
        LootEntry entry = new LootEntryTable(resLootTable, 1, 0, new LootCondition[0], entryName); // weight doesn't matter since it's the only entry in the pool. Other params set as you wish.
        return new LootPool(new LootEntry[] {entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), entryName); // Other params set as you wish.
    }

    public static void registerLootTables() {
        LootTableList.register(dungeon);
    }

    // Inject custom loot tables
    @SubscribeEvent
    public static void lootLoad(LootTableLoadEvent evt) {
        if (!evt.getName().getResourceDomain().equals("minecraft"))
            return;

        LootTable lootTable = evt.getTable();
        String tableFileName = evt.getName().getResourcePath();

        if (tableFileName.equals("chests/simple_dungeon"))
            lootTable.addPool(loadLootPool(dungeon));
        else if (tableFileName.equals("chests/nether_bridge"))
            lootTable.addPool(loadLootPool(nether_bridge));
        else if (tableFileName.equals("entities/blaze"))
            lootTable.addPool(loadLootPool(blaze));
        else if (tableFileName.equals("entities/elder_guardian"))
            lootTable.addPool(loadLootPool(elder_guardian));
        else if (tableFileName.equals("gameplay/fishing/treasure"))
            lootTable.addPool(loadLootPool(fishing));
    }
}
