package com.lumi.lots.blocks;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.block.material.Material;

public class LumisBlocks extends Block {
    private static Material[] materials = {Material.air, Material.anvil, Material.cactus, Material.cake, Material.carpet, Material.circuits, Material.clay, Material.cloth, Material.coral, Material.craftedSnow, Material.dragonEgg, Material.fire, Material.glass, Material.gourd, Material.grass, Material.ground, Material.ice, Material.iron, Material.lava, Material.leaves, Material.packedIce, Material.piston, Material.plants, Material.portal, Material.redstoneLight, Material.rock, Material.sand, Material.snow, Material.sponge, Material.tnt, Material.vine, Material.water, Material.web, Material.wood};
    private static SoundType[] sounds = {Block.soundTypeAnvil, Block.soundTypeCloth, Block.soundTypeGlass, Block.soundTypeGrass, Block.soundTypeGravel, Block.soundTypeLadder, Block.soundTypeMetal, Block.soundTypePiston, Block.soundTypeSand, Block.soundTypeSnow, Block.soundTypeStone, Block.soundTypeWood};
    private static CreativeTabs[] tabs = {CreativeTabs.tabAllSearch, CreativeTabs.tabBlock, CreativeTabs.tabBrewing, CreativeTabs.tabCombat, CreativeTabs.tabDecorations, CreativeTabs.tabFood, CreativeTabs.tabInventory, CreativeTabs.tabMaterials, CreativeTabs.tabMisc, CreativeTabs.tabRedstone, CreativeTabs.tabTools, CreativeTabs.tabTransport};

    public LumisBlocks(int material, String name, float hardness, float resistance, int sound, int tab) {
        super(materials[material]);
        name = name.toLowerCase().replace(" ", "_");
        this.setBlockName(name);
        this.setBlockTextureName(String.format("lumis_lots:%s", name));
        this.setHardness(hardness);
        this.setResistance(resistance);
        this.setStepSound(sounds[sound]);
        if (tab > -1) {
            this.setCreativeTab(tabs[tab]);
        }
    }
}
