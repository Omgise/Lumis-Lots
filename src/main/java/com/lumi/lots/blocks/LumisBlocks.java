package com.lumi.lots.blocks;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Random;

import com.lumi.lots.blocks.BlockDropsHandler.*;

public class LumisBlocks extends Block {
    private final BlockTickHandler tickHandler;
    private final DropAmountFortuneHandler dropAmountFortuneHandler;
    private final DropAmountHandler dropAmountHandler;
    private final DropTypeHandler dropTypeHandler;
    private final DropMultiItemsHandler dropMultipleItemsHandler;

    private static Material[] materials = {Material.air, Material.anvil, Material.cactus, Material.cake, Material.carpet, Material.circuits, Material.clay, Material.cloth, Material.coral, Material.craftedSnow, Material.dragonEgg, Material.fire, Material.glass, Material.gourd, Material.grass, Material.ground, Material.ice, Material.iron, Material.lava, Material.leaves, Material.packedIce, Material.piston, Material.plants, Material.portal, Material.redstoneLight, Material.rock, Material.sand, Material.snow, Material.sponge, Material.tnt, Material.vine, Material.water, Material.web, Material.wood};
    private static SoundType[] sounds = {Block.soundTypeAnvil, Block.soundTypeCloth, Block.soundTypeGlass, Block.soundTypeGrass, Block.soundTypeGravel, Block.soundTypeLadder, Block.soundTypeMetal, Block.soundTypePiston, Block.soundTypeSand, Block.soundTypeSnow, Block.soundTypeStone, Block.soundTypeWood};
    private static CreativeTabs[] tabs = {CreativeTabs.tabAllSearch, CreativeTabs.tabBlock, CreativeTabs.tabBrewing, CreativeTabs.tabCombat, CreativeTabs.tabDecorations, CreativeTabs.tabFood, CreativeTabs.tabInventory, CreativeTabs.tabMaterials, CreativeTabs.tabMisc, CreativeTabs.tabRedstone, CreativeTabs.tabTools, CreativeTabs.tabTransport};

    public LumisBlocks(int material, String name, float hardness, float resistance, String harvestTool, int harvestLevel, int sound, int tab, boolean ticks, BlockTickHandler tickHandler, DropAmountFortuneHandler dropAmountFortuneHandler, DropAmountHandler dropAmountHandler, DropTypeHandler dropTypeHandler, DropMultiItemsHandler dropMultipleItemsHandler) {
        super(materials[material]);
        name = name.toLowerCase().replace(" ", "_");
        this.setBlockName(name);
        this.setBlockTextureName(String.format("lumis_lots:%s", name));
        this.setHardness(hardness);
        this.setResistance(resistance);
        this.setHarvestLevel(harvestTool, harvestLevel);
        this.setStepSound(sounds[sound]);
        if (tab > -1) {
            this.setCreativeTab(tabs[tab]);
        }
        if (ticks) {
            this.setTickRandomly(true);
        }
        this.tickHandler = tickHandler;
        this.dropAmountFortuneHandler = dropAmountFortuneHandler;
        this.dropAmountHandler = dropAmountHandler;
        this.dropTypeHandler = dropTypeHandler;
        this.dropMultipleItemsHandler = dropMultipleItemsHandler;
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random rand) {
        if (tickHandler != null && !world.isRemote) {
            tickHandler.onTick(world, x, y, z, rand);
        }
    }

    @Override
    public int quantityDroppedWithBonus(int fortune, Random rand) {
        if (dropAmountFortuneHandler != null) {
            return dropAmountFortuneHandler.onDropFortune(fortune, rand);
        } else {
            return 1;
        }
    }

    @Override
    public int quantityDropped(Random rand) {
        if (dropAmountHandler != null) {
            return dropAmountHandler.onDrop(rand);
        } else {
            return 1;
        }
    }

    @Override
    public Item getItemDropped(int metadata, Random rand, int fortune) {
        if (dropTypeHandler != null) {
            return dropTypeHandler.onGetDropItem(metadata, rand, fortune);
        } else {
            return super.getItemDropped(metadata, rand, fortune);
        }
    }

    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        if (dropMultipleItemsHandler != null) {
            return dropMultipleItemsHandler.onGetMultiDropItems(world, x, y, z, metadata, fortune);
        } else {
            return super.getDrops(world, x, y, z, metadata, fortune);
        }
    }
}
