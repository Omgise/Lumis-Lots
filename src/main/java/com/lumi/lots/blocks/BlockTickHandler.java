package com.lumi.lots.blocks;

import net.minecraft.world.World;

import java.util.Random;

public interface BlockTickHandler {
    void onTick(World world, int x, int y, int z, Random rand);
}
