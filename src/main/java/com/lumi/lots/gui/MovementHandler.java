package com.lumi.lots.gui;

import com.lumi.lots.LumisCore;
import cpw.mods.fml.client.GuiIngameModOptions;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.client.gui.inventory.GuiEditSign;
import net.minecraft.client.settings.KeyBinding;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;

public class MovementHandler {
    private static final Logger logger = LogManager.getLogger(LumisCore.MOD_ID);

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) {
            return;
        }

        Minecraft mc = Minecraft.getMinecraft();

        if (mc.currentScreen != null && !mc.isGamePaused()) {
            boolean notInIllegalWindow = true;
            Class<?>[] illegalGui = {GuiChat.class, GuiCommandBlock.class, GuiEditSign.class, GuiIngameModOptions.class};
            for (Class<?> guiClass : illegalGui) {
                if (guiClass.isInstance(mc.currentScreen)) {
                    notInIllegalWindow = false;
                    break;
                }
            }
            if (notInIllegalWindow) {
                checkAndRunKey(mc.gameSettings.keyBindForward);
                checkAndRunKey(mc.gameSettings.keyBindBack);
                checkAndRunKey(mc.gameSettings.keyBindLeft);
                checkAndRunKey(mc.gameSettings.keyBindRight);
                checkAndRunKey(mc.gameSettings.keyBindJump);
                checkAndRunKey(mc.gameSettings.keyBindSneak);
                checkAndRunKey(mc.gameSettings.keyBindSprint);
                checkAndRunKey(mc.gameSettings.keyBindChat);
            }
        }
    }

    public void checkAndRunKey(KeyBinding key) {
        KeyBinding.setKeyBindState(key.getKeyCode(), Keyboard.isKeyDown(key.getKeyCode()));
    }
}
