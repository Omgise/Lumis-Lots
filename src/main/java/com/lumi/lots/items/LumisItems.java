package com.lumi.lots.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import com.lumi.lots.items.ItemMetaDataHandler.*;
import net.minecraft.world.World;

import static com.lumi.lots.LumisCore.tabs;
import com.lumi.lots.items.ItemUseHandler.OnItemUseHandler;

public class LumisItems extends Item {
    private OnItemUseHandler itemUseHandler = null;
    private RegisterIconsHandler registerIconsHandler = null;
    private IconFromMetadataHandler iconFromMetadataHandler = null;
    private CombinedMetadataHandler combinedMetadataHandler = null;
    private IIcon[] icons;

    public LumisItems(String name, int creativeTab, boolean hasSubtypes, OnItemUseHandler itemUseHandler, RegisterIconsHandler registerIconsHandler, IconFromMetadataHandler iconFromMetadataHandler, CombinedMetadataHandler combinedMetadataHandler) {
        super();
        name = name.toLowerCase().replace(" ", "_");
        this.setUnlocalizedName(name);
        if (creativeTab > -1) {
            this.setCreativeTab(tabs[creativeTab]);
        }
        this.itemUseHandler = itemUseHandler;
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

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        if (combinedMetadataHandler != null) {
            return combinedMetadataHandler.onGetUnlocalisedName(stack);
        } else {
            return super.getUnlocalizedName(stack);
        }
    }

    @Override
    public boolean onItemUse(ItemStack item, EntityPlayer player, World world, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
        if (itemUseHandler != null) {
            return itemUseHandler.onItemUse(item, player, world, p_77648_4_,  p_77648_5_,  p_77648_6_,  p_77648_7_,  p_77648_8_, p_77648_9_, p_77648_10_);
        } else {
            return super.onItemUse(item, player, world, p_77648_4_,  p_77648_5_,  p_77648_6_,  p_77648_7_,  p_77648_8_, p_77648_9_, p_77648_10_);
        }
    }
}
