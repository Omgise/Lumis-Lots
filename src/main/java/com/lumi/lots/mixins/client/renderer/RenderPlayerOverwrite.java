package com.lumi.lots.mixins.client.renderer;

import com.lumi.lots.LumisCore;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(RenderPlayer.class)
public class RenderPlayerOverwrite {
    @Shadow
    public ModelBiped field_77109_a; //modelBipedMain

    /**
     * @author LumiLovesYou
     * @reason To hide the player's hand
     */
    @Overwrite(remap = false)
    public void func_82441_a(EntityPlayer player) //renderFirstPersonArm
    {
        if (LumisCore.config.firstPersonModel) {
            //What in the world goes here
        } else {
            float f = 1.0F;
            GL11.glColor3f(f, f, f);
            this.field_77109_a.onGround = 0.0F;
            this.field_77109_a.setRotationAngles(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, player);
            this.field_77109_a.bipedRightArm.render(0.0625F);
        }
    }

    @Inject(
        method = "func_77029_c(Lnet/minecraft/client/entity/AbstractClientPlayer;F)V",
        at = @At("HEAD"),
        cancellable = true,
        remap = false
    )
    protected void renderEquippedItems(AbstractClientPlayer p_77029_1_, float p_77029_2_, CallbackInfo ci) {
        if (LumisCore.config.firstPersonModel) {
            ci.cancel();
        }
    }
}
