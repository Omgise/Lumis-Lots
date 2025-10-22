package com.lumi.lots;

import com.lumi.lots.blocks.BlockBuilder;
import com.lumi.lots.blocks.BlockDropsHandler.DropMultiItemsHandler;
import com.lumi.lots.blocks.BlockTickHandler;
import com.lumi.lots.gui.MovementHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Random;

@Mod(modid = LumisCore.MOD_ID, version = LumisCore.MOD_VERSION)
public class LumisCore
{
    public static final String MOD_ID = "lumis_lots";
    public static final String MOD_VERSION = "@VERSION@";
    private static final Logger logger = LogManager.getLogger(MOD_ID);

    @EventHandler
    public void init(FMLInitializationEvent event) {
        System.out.println("Lumi says \"Hello Forge world!\"");
        logger.info("Lumi says \"Hello Forge world!\"");
        if (event.getSide().isClient()) {
            FMLCommonHandler.instance().bus().register(new MovementHandler());
        }
    }

    @Mod.Instance(MOD_ID)
    public static LumisCore INSTANCE;

    public static Block testBlock;
    public static Block trueBlock;
    public static Block falseBlock;

    public static Block compostingDirt;
    public static Block compostedDirt;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        //Blocks
        testBlock = new BlockBuilder()
                .setName("Test Block")
                .setMaterial(7)
                .setSound(1)
                .setTab(0)
                .build();
        trueBlock = new BlockBuilder()
                .setName("True Block")
                .setMaterial(7)
                .setSound(1)
                .setTab(0)
                .build();
        falseBlock = new BlockBuilder()
                .setName("False Block")
                .setMaterial(7)
                .setSound(1)
                .setTab(0)
                .build();

        compostingDirt = new BlockBuilder()
                .setName("Composting Dirt")
                .setMaterial(15)
                .setSound(4)
                .setTab(4)
                .setResistance(0.5f)
                .setHardness(0.5f)
                .setHarvestTool("shovel")
                .setTicking(true)
                .setTickHandler(new BlockTickHandler() {
                    @Override
                    public void onTick(World world, int x, int y, int z, Random rand) {
                        if (world.isAirBlock(x, y + 1, z)) {
                            boolean nearbyWater = false;
                            int chance = 0;
                            for (int i = x - 1; i <= x + 1; i++) {
                                for (int j = z - 1; j <= z + 1; j++) {
                                    if (world.getBlock(i, y, j).isAssociatedBlock(Blocks.water)) {
                                        nearbyWater = true;
                                        break;
                                    }
                                }
                                if (nearbyWater) {
                                    break;
                                }
                            }
                            if (world.isRaining() || nearbyWater) {
                                chance = rand.nextInt(10);
                            } else {
                                chance = rand.nextInt(25);
                            }
                            if (chance == 0) {
                                world.setBlock(x, y, z, compostedDirt);
                            }
                        }
                    }
                })
                .build();

        compostedDirt = new BlockBuilder()
                .setName("Composted Dirt")
                .setMaterial(15)
                .setSound(3)
                .setTab(4)
                .setResistance(0.5f)
                .setHardness(0.5f)
                .setHarvestTool("shovel")
                .setDropMultipleItemsHandler(new DropMultiItemsHandler() {
                    @Override
                    public ArrayList<ItemStack> onGetMultiDropItems(World world, int x, int y, int z, int metadata, int fortune) {
                        ArrayList<ItemStack> drops = new ArrayList<>();
                        drops.add(new ItemStack(Blocks.dirt, 1, 0));
                        for (int i = 0; i < ((3 + world.rand.nextInt(3)) + Math.floor(fortune*1.5)); i++) {
                            drops.add(new ItemStack(Items.dye, 1, 15));
                        }
                        return drops;
                    }
                })
                .build();

        GameRegistry.registerBlock(testBlock, "test_block");
        GameRegistry.registerBlock(trueBlock, "true_block");
        GameRegistry.registerBlock(falseBlock, "false_block");

        GameRegistry.registerBlock(compostingDirt, "composting_dirt");
        GameRegistry.registerBlock(compostedDirt, "composted_dirt");

        //Tags
        Block[] compostableBlocks = {Blocks.pumpkin, Blocks.melon_block, Blocks.cactus, Blocks.hay_block, Blocks.vine, Blocks.waterlily, Blocks.red_mushroom, Blocks.brown_mushroom, Blocks.nether_wart, Blocks.yellow_flower};
        for (Block block : compostableBlocks) {
            OreDictionary.registerOre("compostable", new ItemStack(block));
        }
        for (int i = 0; i < 4; i++) { //Tree variants
            OreDictionary.registerOre("compostable", new ItemStack(Blocks.sapling, 1, i));
            OreDictionary.registerOre("compostable", new ItemStack(Blocks.leaves, 1, i));
            OreDictionary.registerOre("compostable", new ItemStack(Blocks.leaves2, 1, i));
        }
        for (int i = 0; i < 9; i++) { //Flower variants
            OreDictionary.registerOre("compostable", new ItemStack(Blocks.red_flower, 1, i));
        }
        for (int i = 0; i < 3; i++) { //Grass variants
            OreDictionary.registerOre("compostable", new ItemStack(Blocks.tallgrass, 1, i));
        }
        for (int i = 0; i < 6; i++) { //Double plant varials
            OreDictionary.registerOre("compostable", new ItemStack(Blocks.double_plant, 1, i));
        }
        Item[] compostableItems = {Items.rotten_flesh, Items.apple, Items.bread, Items.chicken, Items.porkchop, Items.beef, Items.egg, Items.wheat, Items.wheat_seeds, Items.melon_seeds, Items.pumpkin_seeds, Items.melon, Items.potato, Items.baked_potato, Items.reeds};
        for (Item item : compostableItems) {
            OreDictionary.registerOre("compostable", new ItemStack(item, 1, OreDictionary.WILDCARD_VALUE));
        }
        for (int i = 0; i < 4; i++) { //Fish variants
            OreDictionary.registerOre("compostable", new ItemStack(Items.fish, 1, i));
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
