package com.lumi.lots;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.block.material.Material;

public class LumisBlocks extends Block {
    public LumisBlocks(String name) {
        super(Material.rock);
        this.setBlockName(name);
        this.setBlockTextureName("lumis_lots:test");
        this.setHardness(1.0F);
        this.setResistance(5.0F);
        this.setStepSound(Block.soundTypeStone);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }
}
