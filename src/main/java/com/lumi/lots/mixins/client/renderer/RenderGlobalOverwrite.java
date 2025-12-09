package com.lumi.lots.mixins.client.renderer;

import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.lumi.lots.LumisCore.lumiRand;

@Mixin(RenderGlobal.class)
public class RenderGlobalOverwrite {
    @Shadow
    private WorldClient field_72769_h; //theWorld

    @Inject(
        method = "func_72706_a(Lnet/minecraft/entity/player/EntityPlayer;IIIII)V",
        at = @At("HEAD"),
        cancellable = true,
        remap = false
    )
    //Goes Player, case, x, y, z, block
    public void playAuxSFX(EntityPlayer p_72706_1_, int p_72706_2_, int p_72706_3_, int p_72706_4_, int p_72706_5_, int p_72706_6_, CallbackInfo ci) {
        if (p_72706_2_ > 2006) {
            ci.cancel();
            switch (p_72706_2_) {
                case 2007:
                    this.field_72769_h.playSound((double) p_72706_3_ + 0.5D, (double) p_72706_4_ + 0.5D, (double) p_72706_5_ + 0.5D, "random.fizz", 5.0F, 1.0F, false);
                    for (int i = 0; i < 12; i++) {
                        this.field_72769_h.spawnParticle("smoke", p_72706_3_ + lumiRand.nextDouble(), p_72706_4_ + lumiRand.nextDouble(), p_72706_5_ + lumiRand.nextDouble(), 0.0D, 0.1D, 0.0D);
                    }
                    break;
            }
        }
    }
}
