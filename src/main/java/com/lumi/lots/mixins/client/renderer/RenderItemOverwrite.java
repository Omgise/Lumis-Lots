package com.lumi.lots.mixins.client.renderer;

import com.lumi.lots.LumisCore;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.minecraftforge.client.IItemRenderer.ItemRenderType.EQUIPPED;


@Mixin(ItemRenderer.class)
public class RenderItemOverwrite {
    /**
     * @author LumiLovesYou
     * @reason To hide the item in the player's hand
     */
    @Inject(
        method = "func_78439_a(Lnet/minecraft/client/renderer/Tessellator;FFFFIIF)V",
        at = @At("HEAD"),
        cancellable = true,
        remap = false
    )
    private static void renderItemIn2D(Tessellator p_78439_0_, float p_78439_1_, float p_78439_2_, float p_78439_3_, float p_78439_4_, int p_78439_5_, int p_78439_6_, float p_78439_7_, CallbackInfo ci) {
        if (LumisCore.config.firstPersonModel) {
            ci.cancel();
        }
    }

    @Redirect(
        method = "renderItem(Lnet/minecraft/entity/EntityLivingBase;Lnet/minecraft/item/ItemStack;ILnet/minecraftforge/client/IItemRenderer$ItemRenderType;)V",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/RenderBlocks;renderBlockAsItem(Lnet/minecraft/block/Block;IF)V",
            ordinal = 1
        ),
        remap = false
    )
    private void renderItem(RenderBlocks instance, Block f1, int f2, float f3) {
        if (LumisCore.config.firstPersonModel) {
            return;
        }
        instance.renderBlockAsItem(f1, f2, f3);
    }
}
