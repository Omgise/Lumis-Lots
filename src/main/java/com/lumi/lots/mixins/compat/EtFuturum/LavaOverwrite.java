package com.lumi.lots.mixins.compat.EtFuturum;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import com.lumi.lots.config.Config;
import com.lumi.lots.LumisCore;

@Mixin(BlockLiquid.class)
public abstract class LavaOverwrite extends Block {

    @Shadow
    protected abstract void func_149799_m(World p_149799_1_, int p_149799_2_, int p_149799_3_, int p_149799_4_);

    protected LavaOverwrite(Material p_i45413_1_) {
        super(p_i45413_1_);
    }

    /**
     * @author LumiLovesYou
     * @reason Cobble generators generate cobbled deepslate in EtFuturum
     */
    @Overwrite
    public void func_149805_n(World world, int x, int y, int z) {
        if (world.getBlock(x, y, z) == this)
        {
            if (this.blockMaterial == Material.lava)
            {
                boolean flag = false;

                if (flag || world.getBlock(x, y, z - 1).getMaterial() == Material.water)
                {
                    flag = true;
                }

                if (flag || world.getBlock(x, y, z + 1).getMaterial() == Material.water)
                {
                    flag = true;
                }

                if (flag || world.getBlock(x - 1, y, z).getMaterial() == Material.water)
                {
                    flag = true;
                }

                if (flag || world.getBlock(x + 1, y, z).getMaterial() == Material.water)
                {
                    flag = true;
                }

                if (flag || world.getBlock(x, y + 1, z).getMaterial() == Material.water)
                {
                    flag = true;
                }

                if (flag)
                {
                    int l = world.getBlockMetadata(x, y, z);

                    if (l == 0)
                    {
                        world.setBlock(x, y, z, Blocks.obsidian);
                    }
                    else if (l <= 4)
                    {
                        if (y <= LumisCore.config.etFuturumDeepslateYLevel && LumisCore.INSTANCE.etFuturumInstalled) {
                            Block deepslate = GameRegistry.findBlock("etfuturum", "cobbled_deepslate");
                            world.setBlock(x, y, z, deepslate);
                        } else {
                            world.setBlock(x, y, z, Blocks.cobblestone);
                        }
                    }

                    this.func_149799_m(world, x, y, z);
                }
            }
        }
    }
}
