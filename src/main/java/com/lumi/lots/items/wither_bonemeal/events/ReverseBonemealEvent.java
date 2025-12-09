package com.lumi.lots.items.wither_bonemeal.events;

import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerEvent;

@Cancelable
@Event.HasResult
public class ReverseBonemealEvent extends PlayerEvent {
    public World world;
    public Block block;
    public int x;
    public int y;
    public int z;

    public ReverseBonemealEvent(EntityPlayer player, World world, Block block, int x, int y, int z)
    {
        super(player);
        this.world = world;
        this.block = block;
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
