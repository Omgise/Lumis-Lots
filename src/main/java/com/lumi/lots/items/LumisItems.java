package com.lumi.lots.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

import com.lumi.lots.items.ItemMetaDataHandler.*;

public class LumisItems extends Item {
    private RegisterIconsHandler registerIconsHandler = null;
    private IconFromMetadataHandler iconFromMetadataHandler = null;
    private CombinedMetadataHandler combinedMetadataHandler = null;
    private IIcon[] icons;

    public LumisItems(String name, boolean hasSubtypes, RegisterIconsHandler registerIconsHandler, IconFromMetadataHandler iconFromMetadataHandler, CombinedMetadataHandler combinedMetadataHandler) {
        super();
        name = name.toLowerCase().replace(" ", "_");
        this.setUnlocalizedName(name);
        if (hasSubtypes) {
            this.hasSubtypes = true;
            if (combinedMetadataHandler != null) {
                this.combinedMetadataHandler = combinedMetadataHandler;
            } else {
                this.registerIconsHandler = registerIconsHandler;
                this.iconFromMetadataHandler = iconFromMetadataHandler;
            }
        } else {
            this.setTextureName(String.format("lumis_lots:%s", name));
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister register) {
        if (combinedMetadataHandler != null) {
            combinedMetadataHandler.onRegisterIcons(register);
        } else if (registerIconsHandler != null) {
            registerIconsHandler.onRegisterIcons(register);
        } else {
            super.registerIcons(register);
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIconFromDamage(int meta) {
        if (combinedMetadataHandler != null) {
            return combinedMetadataHandler.onGetIconFromMetadata(meta);
        } else if (iconFromMetadataHandler != null) {
            return iconFromMetadataHandler.onGetIconFromMetadata(meta);
        } else {
            return super.getIconFromDamage(meta);
        }
    }
}
