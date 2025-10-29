package com.lumi.lots.gui.TextFieldFocusChecks;

import net.minecraft.client.Minecraft;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class TextFieldFocusNEI {
    public static boolean isNEISearchFocused() {
        try {
            Minecraft mc = Minecraft.getMinecraft();
            if (mc.currentScreen == null) {
                return false;
            }

            Class<?> textFieldClass = Class.forName("codechicken.nei.TextField");
            Method focusedMethod = textFieldClass.getMethod("focused");

            String[] neiClasses = {
                    "codechicken.nei.LayoutManager",
                    "codechicken.nei.ItemPanel",
                    "codechicken.nei.LayoutStyle"
            };

            for (String className : neiClasses) {
                try {
                    Class<?> clazz = Class.forName(className);
                    for (Field field : clazz.getDeclaredFields()) {
                        if (java.lang.reflect.Modifier.isStatic(field.getModifiers())) {
                            field.setAccessible(true);
                            Object obj = field.get(null);

                            if (textFieldClass.isInstance(obj)) {
                                Boolean isFocused = (Boolean) focusedMethod.invoke(obj);
                                if (isFocused != null && isFocused) {
                                    return true;
                                }
                            }
                        }
                    }
                } catch (ClassNotFoundException ignore) {}
            }
        } catch (Exception ignore) {}

        return false;
    }
}
