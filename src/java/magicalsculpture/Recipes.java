package magicalsculpture;

import net.minecraft.block.BlockRedFlower;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Recipes {
    public static void registerRecipes() {
        // Sculpture Chisel
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.itemChisel, 1), new Object[]{
                "...",
                ".O.",
                "I..",
                'O', Blocks.OBSIDIAN,
                'I', Items.IRON_INGOT
        });

        // Sculpture base
        GameRegistry.addRecipe(new ItemStack(BlockRegistry.blockSculpture, 4, 0 ), new Object[]{
                "SSS",
                "SCS",
                "SSS",
                'S', Blocks.STONE,
                'C', Blocks.CHEST
        });

        // Sculpture Stone
        GameRegistry.addRecipe(new ItemStack(BlockRegistry.blockSculpture, 4, 1 ), new Object[]{
                "SSS",
                "SDS",
                "SSS",
                'S', Blocks.STONE,
                'D', Items.DIAMOND
        });

        // Reverser
        GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistry.itemReverser,1), new ItemStack(ItemRegistry.itemRelic, 1, 29), Items.GLOWSTONE_DUST, Items.REDSTONE);

    }
}
