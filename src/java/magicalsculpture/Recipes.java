package magicalsculpture;

import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockRedFlower;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Recipes {
    public static void registerRecipes() {
        // Sculpture Chisel
        GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.itemChisel, 1),
                "...",
                ".O.",
                "I..",
                'O', Blocks.OBSIDIAN,
                'I', Items.IRON_INGOT
        );

        // Sculpture base
        GameRegistry.addShapedRecipe(new ItemStack(BlockRegistry.blockSculpture, 4, 0 ),
                "SSS",
                "SCS",
                "SSS",
                'S', Blocks.STONE,
                'C', Blocks.CHEST
        );

        // Sculpture Stone
        GameRegistry.addShapedRecipe(new ItemStack(BlockRegistry.blockSculpture, 4, 1 ),
                "SSS",
                "SDS",
                "SSS",
                'S', Blocks.STONE,
                'D', Items.DIAMOND
        );

        // Reverser
        GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistry.itemReverser,1),
                new ItemStack(ItemRegistry.itemRelic, 1, 29), Items.GLOWSTONE_DUST, Items.REDSTONE);
        for (int i=0; i<ItemRegistry.itemRelic.numOfRelic; i++)
            GameRegistry.addShapelessRecipe(new ItemStack(Items.EMERALD, 1), new ItemStack(ItemRegistry.itemRelic, 1, i), ItemRegistry.itemReverser);

        // Relic
        // 0
        GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.itemRelic, 1, 0 ),
                "G.G",
                "GGG",
                ".G.",
                'G', Items.GOLDEN_APPLE
        );

        GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.itemRelic, 1, 1 ),
                "I.I",
                "ICI",
                ".I.",
                'I', Items.IRON_INGOT,
                'C', Items.IRON_CHESTPLATE
        );

        GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.itemRelic, 1, 2 ),
                ".I.",
                "III",
                "ISI",
                'I', Items.IRON_INGOT,
                'S', Items.IRON_SWORD
        );

        GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistry.itemRelic, 1, 3),
                Items.ENDER_PEARL, Items.FEATHER);

        GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.itemRelic, 1, 4),
                ".E.",
                ".P.",
                "E.E",
                'E', Items.SPIDER_EYE,
                'P', Items.ENDER_PEARL);

        // 5
        GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistry.itemRelic, 1, 5),
                new ItemStack(Blocks.RED_FLOWER, 1, BlockFlower.EnumFlowerType.POPPY.getMeta()),
                new ItemStack(Blocks.RED_FLOWER, 1, BlockFlower.EnumFlowerType.BLUE_ORCHID.getMeta()),
                new ItemStack(Blocks.RED_FLOWER, 1, BlockFlower.EnumFlowerType.ALLIUM.getMeta()),
                new ItemStack(Blocks.RED_FLOWER, 1, BlockFlower.EnumFlowerType.ORANGE_TULIP.getMeta()),
                new ItemStack(Blocks.RED_FLOWER, 1, BlockFlower.EnumFlowerType.WHITE_TULIP.getMeta()),
                new ItemStack(Blocks.RED_FLOWER, 1, BlockFlower.EnumFlowerType.PINK_TULIP.getMeta())
        );

        GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistry.itemRelic, 1, 6),
                Blocks.BROWN_MUSHROOM,
                Blocks.RED_MUSHROOM
        );

        GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistry.itemRelic, 1, 7),
                Items.ENDER_PEARL,
                Items.CARROT
        );

        GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistry.itemRelic, 1, 8),
                Blocks.OBSIDIAN,
                Items.DIAMOND
        );

        GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistry.itemRelic, 1, 9),
                Blocks.OBSIDIAN,
                new ItemStack(ItemRegistry.itemRelic, 1, 2 )
        );

        // 10 - Dungeon 1
        // 11 - Dungeon 1
        // 12 - Dungeon 1
        // 13 - Nether_bridge 10%
        // 14 - Blaze
        // 15 - Elder guardian

        // 16
        GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistry.itemRelic, 1, 16),
                new ItemStack(ItemRegistry.itemRelic, 1, 14),
                new ItemStack(ItemRegistry.itemRelic, 1, 15)
        );

        // 17
        GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.itemRelic, 1, 17),
                "RRR",
                "RGR",
                "RRR",
                'R', Items.ROTTEN_FLESH,
                'G', Items.GOLDEN_APPLE
        );

        // 18 - Dungeon 1
        // 19 - Dungeon 2
        // 20 - Dungeon 2
        // 21 - Dungeon 2

        // 22
        GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistry.itemRelic, 1, 22),
                new ItemStack(ItemRegistry.itemRelic, 1, 21),
                new ItemStack(ItemRegistry.itemRelic, 1, 21)
        );

        // 23 - Dungeon 2

        // 24
        GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistry.itemRelic, 1, 24),
                new ItemStack(ItemRegistry.itemRelic, 1, 0),
                new ItemStack(ItemRegistry.itemRelic, 1, 23)
        );

        // 25 - Dungeon 2

        // 26
        GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.itemRelic, 1, 26),
                ".D.",
                "DCD",
                ".D.",
                'D', Items.DIAMOND,
                'C', Items.COMPASS
        );

        // 27 - Dungeon 1
        GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistry.itemRelic, 1, 27),
                Items.GOLD_INGOT,
                new ItemStack(ItemRegistry.itemRelic, 1, 46),
                new ItemStack(ItemRegistry.itemRelic, 1, 1)
        );

        // 28
        GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistry.itemRelic, 1, 28),
                new ItemStack(ItemRegistry.itemRelic, 1, 1),
                new ItemStack(ItemRegistry.itemRelic, 1, 18),
                new ItemStack(ItemRegistry.itemRelic, 1, 1)
        );

        // 29 - Dungeon 2

        // 30
        GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.itemRelic, 1, 30),
                "GSG",
                "GUG",
                "GDG",
                'G', Items.GOLD_INGOT,
                'S', new ItemStack(ItemRegistry.itemRelic, 1, 45),
                'U', new ItemStack(ItemRegistry.itemRelic, 1, 29),
                'D', new ItemStack(ItemRegistry.itemRelic, 1, 46)
        );

        // 31
        GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistry.itemRelic, 1, 31),
                new ItemStack(ItemRegistry.itemRelic, 1, 3),
                new ItemStack(ItemRegistry.itemRelic, 1, 7),
                new ItemStack(ItemRegistry.itemRelic, 1, 47),
                new ItemStack(ItemRegistry.itemRelic, 1, 29)
        );

        // 32 - Dungeon 1
        // 33 - Dungeon 1
        // 34 - Dungeon 1
        // 35 - Dungeon 3
        // 36 - Dungeon 3
        // 37 - Dungeon 2

        // 38
        GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.itemRelic, 1, 38),
                "W.W",
                "WFW",
                "WIW",
                'W', new ItemStack(Blocks.WOOL, 1, EnumDyeColor.BLACK.getMetadata()),
                'F', new ItemStack(ItemRegistry.itemRelic, 1, 3),
                'I', new ItemStack(ItemRegistry.itemRelic, 1, 12)
        );

        // 39
        GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.itemRelic, 1, 39),
                ".ID",
                ".SG",
                "S..",
                'I', new ItemStack(ItemRegistry.itemRelic, 1, 12),
                'D', Items.DIAMOND,
                'S', Items.STICK,
                'G', new ItemStack(ItemRegistry.itemRelic, 1, 0)
        );

        // 40
        GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.itemRelic, 1, 40),
                ".D.",
                "FCF",
                ".F.",
                'F', new ItemStack(ItemRegistry.itemRelic, 1, 3),
                'D', Items.DIAMOND,
                'C', Items.CLOCK
        );

        // 41
        GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.itemRelic, 1, 41),
                "NPN",
                "NGN",
                "NNN",
                'P', new ItemStack(ItemRegistry.itemRelic, 1, 48),
                'G', new ItemStack(ItemRegistry.itemRelic, 1, 13),
                'N', Items.GOLD_NUGGET
        );

        // 42 - Fishing 0.4
        // 43 - Fishing 0.4
        // 44 - Fishing 0.4
        // 45 - Fishing 0.8
        // 46 - Fishing 0.8
        // 47 - Fishing 0.8
        // 48 - Fishing 0.8

        // 49
        GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistry.itemRelic, 1, 49),
                new ItemStack(ItemRegistry.itemRelic, 1, 45),
                new ItemStack(ItemRegistry.itemRelic, 1, 46),
                new ItemStack(ItemRegistry.itemRelic, 1, 47),
                new ItemStack(ItemRegistry.itemRelic, 1, 48)
        );
    }
}
