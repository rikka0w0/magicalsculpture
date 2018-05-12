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

        // Sculpture Stone
        GameRegistry.addRecipe(new ItemStack(BlockRegistry.blockSculpture, 4, 1 ), new Object[]{
                "BBB",
                "BDB",
                "BBB",
                'D', Items.DIAMOND,
                'B', Blocks.BRICK_BLOCK
        });
    }
}
