package com.lumi.lots.blocks;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Random;

public class BlockDropsHandler {
    public interface DropAmountFortuneHandler{
        int onDropFortune(int fortune, Random rand);
    }

    public interface DropAmountHandler {
        int onDrop(Random rand);
    }

    public interface DropTypeHandler {
        Item onGetDropItem (int metadata, Random rand,int fortune);
    }

    public interface DropMultiItemsHandler {
        ArrayList<ItemStack> onGetMultiDropItems(World world, int x, int y, int z, int metadata, int fortune);
    }
}
