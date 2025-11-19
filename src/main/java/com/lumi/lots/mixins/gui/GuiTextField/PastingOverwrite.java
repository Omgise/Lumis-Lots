package com.lumi.lots.mixins.gui.GuiTextField;

import com.lumi.lots.LumisCore;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.lumi.lots.LumisCore.*;

@Mixin(GuiTextField.class)
public abstract class PastingOverwrite {
    @Shadow(remap = false)
    public abstract void func_146202_e();

    @Shadow(remap = false)
    public abstract void func_146199_i(int p_146199_1_);

    @Shadow(remap = false)
    public abstract String func_146207_c();

    @Shadow(remap = false)
    public abstract void func_146191_b(String p_146191_1_);

    @Shadow(remap = false)
    private boolean field_146226_p;

    @Shadow(remap = false)
    private boolean field_146213_o;

    @Inject(
        method = "func_146201_a",
        at = @At("HEAD"),
        cancellable = true,
        remap = false
    )
    public void textboxKeyTyped(char typedKey, int keyCode, CallbackInfoReturnable<Boolean> cir) {
        if (!LumisCore.config.metaKeyPasting) return;
        if (!this.field_146213_o) return;
        if (Keyboard.isKeyDown(Keyboard.KEY_LMETA) || Keyboard.isKeyDown(Keyboard.KEY_RMETA)) {
            if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
                this.func_146202_e();
                this.func_146199_i(0);
                cir.setReturnValue(true);
                cir.cancel();
            } else if (Keyboard.isKeyDown(Keyboard.KEY_C)) {
                GuiScreen.setClipboardString(this.func_146207_c());
                cir.setReturnValue(true);
                cir.cancel();
            }  else if (Keyboard.isKeyDown(Keyboard.KEY_V)) {
                if (this.field_146226_p) {
                    this.func_146191_b(GuiScreen.getClipboardString());
                }
                cir.setReturnValue(true);
                cir.cancel();
            } else if (Keyboard.isKeyDown(Keyboard.KEY_X)) {
                GuiScreen.setClipboardString(this.func_146207_c());
                if (this.field_146226_p) {
                    this.func_146191_b("");
                }
                cir.setReturnValue(true);
                cir.cancel();
            }
        }
    }
}
