//Left unused because of creative mode menu issues
package com.lumi.lots.mixins.gui.GuiTextField;

import net.minecraft.client.gui.GuiTextField;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

//import static com.lumi.lots.gui.TextFieldFocusChecks.isTextFocused;

@Mixin(GuiTextField.class)
public class TextFieldFocusedOverwrite {
    @Shadow
    private boolean isFocused;

    @Shadow
    private int cursorCounter;

    /**
     * @author LumiLovesYou
     * @reason Global isFocused value
     */
    @Overwrite
    public void setFocused(boolean value) {
        if (value && !this.isFocused) {
            this.cursorCounter = 0;
        }

        this.isFocused = value;
        //isTextFocused = value;
    }
}