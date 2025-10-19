package com.lumi.lots;

import com.lumi.lots.blocks.BlockBuilder;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

@Mod(modid = LumisCore.MOD_ID, version = LumisCore.MOD_VERSION)
public class LumisCore
{
    public static final String MOD_ID = "lumis_lots";
    public static final String MOD_VERSION = "@VERSION@";

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // some example code
        System.out.println("DIRT BLOCK >> "+Blocks.dirt.getUnlocalizedName());
    }

    @Mod.Instance(MOD_ID)
    public static LumisCore INSTANCE;

    public static Block testBlock;
    public static Block compostingDirt;
    public static Block compostedDirt;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        //Blocks
        testBlock = new BlockBuilder()
                .setName("Test Block")
                .setSound(1)
                .setMaterial(7)
                .setTab(1)
                .build();

        compostingDirt = new BlockBuilder()
                .setName("Composting Dirt")
                .setMaterial(15)
                .setSound(3)
                .setTab(4)
                .setResistance(0.5f)
                .setHardness(0.5f)
                .build();

        compostedDirt = new BlockBuilder()
                .setName("Composted Dirt")
                .setMaterial(15)
                .setSound(4)
                .setTab(4)
                .setResistance(0.5f)
                .setHardness(0.5f)
                .build();

        GameRegistry.registerBlock(testBlock, "test_block");
        GameRegistry.registerBlock(compostingDirt, "composting_dirt");
        GameRegistry.registerBlock(compostedDirt, "composted_dirt");

        //Tags
        Block[] compostableBlocks = {Blocks.pumpkin, Blocks.melon_block, Blocks.cactus, Blocks.hay_block, Blocks.vine, Blocks.waterlily, Blocks.sapling, Blocks.leaves, Blocks.leaves2, Blocks.double_plant, Blocks.tallgrass, Blocks.red_mushroom, Blocks.brown_mushroom, Blocks.reeds, Blocks.nether_wart, Blocks.red_flower};
        for (int i = 0; i < compostableBlocks.length; i++) {
            OreDictionary.registerOre("compostable", new ItemStack(compostableBlocks[i], 1, OreDictionary.WILDCARD_VALUE));
        }
        Item[] compostableItems = {Items.rotten_flesh, Items.apple, Items.bread, Items.fish, Items.chicken, Items.porkchop, Items.beef, Items.egg, Items.wheat, Items.wheat_seeds, Items.melon_seeds, Items.pumpkin_seeds, Items.melon, Items.potato, Items.baked_potato};
        for (int i = 0; i < compostableItems.length; i++) {
            OreDictionary.registerOre("compostable", new ItemStack(compostableItems[i], 1, OreDictionary.WILDCARD_VALUE));
        }

        //Recipes
        GameRegistry.addRecipe(new ShapedOreRecipe(
                new ItemStack(compostingDirt, 1),
                "AAA", "ABA", "AAA",
                'A', "compostable",
                'B', Blocks.dirt
        ));
    }
}
