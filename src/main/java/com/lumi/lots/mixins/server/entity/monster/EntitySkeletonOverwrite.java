package com.lumi.lots.mixins.server.entity.monster;

import com.lumi.lots.LumisCore;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.init.Items;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntitySkeleton.class)
public abstract class EntitySkeletonOverwrite extends Entity {
    public EntitySkeletonOverwrite(World p_i1582_1_) {
        super(p_i1582_1_);
    }

    @Shadow
    public abstract int func_82202_m(); //getSkeletonType

    /**
     * @author LumiLovesYou
     * @reason Modify wither skeleton drops
     */
    @Inject(
        method = "func_70628_a",
        at = @At("HEAD"),
        cancellable = true,
        remap = false
    )
    public void dropFewItems(boolean p_70628_1_, int looting, CallbackInfo ci) {
        if (func_82202_m() == 1) {
            int k;
            for (k = 0; k < this.rand.nextInt(3 + looting); k++) {
                dropItem(LumisCore.witherBone, 1);
            }
            for (k = 0; k < this.rand.nextInt(3 + looting) - 1; k++)
            {
                this.dropItem(Items.coal, 1);
            }
            ci.cancel();
        }
    }
}
