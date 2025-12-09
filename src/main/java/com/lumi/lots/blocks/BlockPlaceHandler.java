package com.lumi.lots.blocks;

import net.minecraft.world.World;

public class BlockPlaceHandler {
    public interface OnBlockAddedHandler {
        void onBlockAdded(World world, int x, int y, int z);
    }
}