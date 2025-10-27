package com.lumi.lots.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemMetaDataHandler {
    public interface RegisterIconsHandler {
        void onRegisterIcons(IIconRegister register);
    }

    public interface IconFromMetadataHandler {
        IIcon onGetIconFromMetadata(int metadata);
    }

    public interface CombinedMetadataHandler {
        void onRegisterIcons(IIconRegister register);
        IIcon onGetIconFromMetadata(int metadata);
        String onGetUnlocalisedName(ItemStack stack);
    }
}
