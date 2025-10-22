package com.lumi.lots.blocks;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class BlockToolHandler {
    public interface ToolEffectiveHandler {
        boolean onCheckToolEffective(String type, int metadata);
    }

    public interface PlayerRelativeBlockHardnessHandler {
        float onGetPlayerRelativeBlockHardness(EntityPlayer player, float speed);
    }
}
