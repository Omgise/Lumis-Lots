package com.lumi.lots.blocks.overwrite;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class Leaves {
    @SubscribeEvent
    public void onBlockBreakSpeed(PlayerEvent.BreakSpeed event) {
        Block block = event.block;

        if (block == Blocks.leaves || block == Blocks.leaves2) {
            if (event.entityPlayer.getCurrentEquippedItem() != null) {
                Item tool = event.entityPlayer.getCurrentEquippedItem().getItem();
                if (tool instanceof ItemHoe) {
                    System.out.println("Hoe!");
                    String material = ((ItemHoe) tool).getToolMaterialName();
                    if (material.equals("WOOD")) {
                        event.newSpeed = event.newSpeed * 3.0F;
                    } else if (material.equals("STONE")) {
                        event.newSpeed = event.newSpeed * 5.0F;
                    } else if (material.equals("GOLD") || material.equals("EMERALD") || material.equals("IRON")) {
                        event.newSpeed = event.originalSpeed * 6.0F;
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onHarvestCheck(PlayerEvent.HarvestCheck event) {
        Block block = event.block;

        if (block ==  Blocks.leaves || block == Blocks.leaves2) {
            if (event.entityPlayer.getCurrentEquippedItem() != null) {
                Item tool = event.entityPlayer.getCurrentEquippedItem().getItem();
                if (tool == Items.shears || tool instanceof ItemHoe) {
                    event.success = true;
                }
            }
        }
    }
}
