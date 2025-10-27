package com.lumi.lots.gui;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;

import java.lang.reflect.Field;
import java.util.List;

public class TextFieldFocus {
    public static boolean isTextFocused = false;

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) return;

        boolean isFocused = false;
        GuiScreen gui = Minecraft.getMinecraft().currentScreen;

        if (gui != null) {
            for (Field field : gui.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                try {
                    Object obj = field.get(gui);
                    if (obj instanceof GuiTextField && ((GuiTextField) obj).isFocused()) {
                        isFocused = true;
                        break;
                    }

                    if (obj instanceof List) {
                        for (Object listObject : (List<?>) obj) {
                            if (listObject instanceof GuiTextField && ((GuiTextField) listObject).isFocused()) {
                                isFocused = true;
                                break;
                            }
                        }
                    }
                } catch (IllegalAccessException ignored) {}
            }
        }
        isTextFocused = isFocused;
    }
}
