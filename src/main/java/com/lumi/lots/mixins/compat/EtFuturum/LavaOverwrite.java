package com.lumi.lots.mixins.compat.EtFuturum;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import com.lumi.lots.LumisCore;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlockLiquid.class)
public abstract class LavaOverwrite extends Block {
    protected LavaOverwrite(Material p_i45413_1_) {
        super(p_i45413_1_);
    }

    /**
     * @author LumiLovesYou
     * @reason Cobble generators generate cobbled deepslate in EtFuturum
     */
    @Inject(
        method = "func_149805_n",
        at = @At("TAIL"),
        cancellable = true
    )
    public void func_149805_n(World world, int x, int y, int z, CallbackInfo ci) {
        if (world.getBlock(x,y,z) != Blocks.cobblestone) {
            return;
        }
        if (y <= LumisCore.config.etFuturumDeepslateYLevel && LumisCore.INSTANCE.etFuturumInstalled) {
            Block deepslate = GameRegistry.findBlock("etfuturum", "cobbled_deepslate");
            world.setBlock(x, y, z, deepslate);
            ci.cancel();
        }
    }
}