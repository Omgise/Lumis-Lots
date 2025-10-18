package com.lumi.lots;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod(modid = LumisCore.MOD_ID, version = LumisCore.MOD_VERSION)
public class LumisCore
{
    public static final String MOD_ID = "lumis_lots";
    public static final String MOD_VERSION = "@VERSION@";

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // some example code
        System.out.println("DIRT BLOCK >> "+Blocks.dirt.getUnlocalizedName());
    }

    @Mod.Instance(MOD_ID)
    public static LumisCore INSTANCE;

    public static Block testBlock;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        testBlock = new LumisBlocks("testBlock");
        GameRegistry.registerBlock(testBlock, "testBlock");
    }
}
