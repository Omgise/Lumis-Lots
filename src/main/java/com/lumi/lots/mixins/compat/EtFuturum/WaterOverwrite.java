package com.lumi.lots.mixins.compat.EtFuturum;

import com.lumi.lots.LumisCore;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDynamicLiquid;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(BlockDynamicLiquid.class)
public abstract class WaterOverwrite extends BlockLiquid {
    protected WaterOverwrite(Material p_i45413_1_) {
        super(p_i45413_1_);
    }

    /**
     * @author LumiLovesYou
     * @reason Stone generators generate deepslate in EtFuturum
     */
    @Inject(
            method = "updateTick", //func_149674_a
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/World;setBlock(IIILnet/minecraft/block/Block;)Z", //func_147449_b
                    shift = At.Shift.AFTER
            ),
            cancellable = false
    )
    public void updateTick_dev(World w, int x, int y, int z, Random r, CallbackInfo c) {
        updateTick(w, x, y, z);
    }

    @Inject(
            method = "func_149674_a",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/World;func_147449_b(IIILnet/minecraft/block/Block;)Z",
                    shift = At.Shift.AFTER
            ),
            cancellable = false
    )
    public void updateTick_prod(World w, int x, int y, int z, Random r, CallbackInfo c) {
        updateTick(w, x, y, z);
    }

    public void updateTick(World world, int x, int y, int z) {
        if (world.getBlock(x, y - 1, z) == Blocks.stone) {
            if (y <= LumisCore.config.etFuturumDeepslateYLevel && LumisCore.INSTANCE.etFuturumInstalled) {
                Block deepslate = GameRegistry.findBlock("etfuturum", "deepslate");
                world.setBlock(x, y - 1, z, deepslate);
            }
        }
    }
}
